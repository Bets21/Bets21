package businessLogic;
//hola
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.User;
import domain.Employee;
import domain.Event;
import domain.Option;
import domain.Person;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation() {
        System.out.println("Creating BLFacadeImplementation instance");
        ConfigXML c = ConfigXML.getInstance();

        if (c.getDataBaseOpenMode().equals("initialize")) {
            dbManager = new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
            dbManager.initializeDB();
        } else
              dbManager=new DataAccess();
        dbManager.close();
    }
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		if(this.dbManager==null) {
			System.out.println("LA DATAACCESS ES UN GILIPOLLAS");
		}
		dbManager=da;
		if(this.dbManager==null) {
			System.out.println("LA DATAACCESS ES UN GILIPOLLAS");
		}
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
    @Override
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
   @Override 
   @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
    @Override
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	   @WebMethod
	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	   @Override
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
    
    @Override
    @WebMethod
    public void storeUser(String pName, String pSurname, String pUsername, String pPassword, int pAdina) {
    	User user = new User(pName, pSurname, pUsername,pPassword,pAdina);
    	
    	this.dbManager.open(false);
    	//if(this.dbManager.getAdminbyUserName(pUsername)==null)
    		if(this.dbManager.getEmployeebyUserName(pUsername)==null)
    			if(this.dbManager.getUserbyUserName(pUsername)==null) {
    				this.dbManager.storeUser(pName,  pSurname,  pUsername,  pPassword,pAdina);
    			}else {
    				throw new javax.persistence.RollbackException();
    			}
    	this.dbManager.close();
    }
    @Override
    @WebMethod
    public void storeUserWithInvitation(String pName, String pSurname, String pUsername, String pPassword, int pAdina,String invitedBy) {
    	User user = new User(pName, pSurname, pUsername,pPassword,pAdina,invitedBy);	
    	this.dbManager.open(false);
    	//if(this.dbManager.getAdminbyUserName(pUsername)==null)
    		if(this.dbManager.getEmployeebyUserName(pUsername)==null)
    			if(this.dbManager.getUserbyUserName(pUsername)==null) {
    				this.dbManager.storeUserWithInvitation(pName,  pSurname,  pUsername,  pPassword,pAdina,invitedBy);
    			}else {
    				throw new javax.persistence.RollbackException();	
    			}
    	this.dbManager.close();
    }
    @WebMethod
    @Override
    public void storeEmployee(String pName, String pSurname, String pUsername, String pPassword) {
    	Employee emp = new Employee( pName,  pSurname,  pUsername,  pPassword);
    	
    	this.dbManager.open(false);
    	//if(this.dbManager.getAdminbyUserName(pUsername)==null)
    		if(this.dbManager.getEmployeebyUserName(pUsername)==null)
    			if(this.dbManager.getUserbyUserName(pUsername)==null) {
    	this.dbManager.storeEmployee(pName,  pSurname,  pUsername,  pPassword);
    			}else {
    				throw new javax.persistence.RollbackException();
    				
    			}
    	this.dbManager.close();
    }
    /*public void storeAdmin(String pName, String pSurname, String pUsername, String pPassword) {
    	Admin admin = new Admin( pName,  pSurname,  pUsername,  pPassword);
    	
    	this.dbManager.open(false);
    	if(this.dbManager.getAdminbyUserName(pUsername)==null)
    		if(this.dbManager.getEmployeebyUserName(pUsername)==null)
    			if(this.dbManager.getUserbyUserName(pUsername)==null) {
    	this.dbManager.storeAdmin(pName,  pSurname,  pUsername,  pPassword);
    			}else {
    				throw new javax.persistence.RollbackException();
    				
    			}
    			
    	this.dbManager.close();
    }*/
    @Override
    @WebMethod
    public boolean isLogin(String username, String password) {
    	
    	this.dbManager.open(false);
    	Person p =dbManager.getPersonbyUserName(username);
    	this.dbManager.close();
		boolean b ;
		if(p==null ) {
			System.out.println("This User Dosen't exist");
			b=false;
		}else {
			if(p.getPassword().compareTo(password)==0) {
				b=true;
				System.out.println("LOGGED");
			}else {
				System.out.println("The password is incorrect");
				b=false;
			}
		}
		
		return b;
	}
    @Override
    @WebMethod
    public boolean loginUser(String username, String password) {
    	
    	this.dbManager.open(false);
    	User u =dbManager.getUserbyUserName(username);
    	this.dbManager.close();
		boolean b ;
		if(u==null )
			b=false;
		else {
			if(u.getAdina()>17)
			b=u.getPassword().compareTo(password)==0;
			else {
				b=false;
			}
		}
    	
		return b;
	}
    @Override
    @WebMethod
    public boolean loginEmployee(String username, String password) {
    	this.dbManager.open(false);
		Employee emp =dbManager.getEmployeebyUserName(username);
		
		boolean b ;
		if(emp==null )
			b=false;
		else {
			if(emp.getPassword().compareTo(password)==0)
				b=true;
			else {
				b=false;
			}
		}
		dbManager.close();
		return b;
	}
	/*public boolean loginAdmin(String username, String password) {
		this.dbManager.open(false);
		Admin admin =dbManager.getAdminbyUserName(username);
		
		boolean b ;
		if(admin==null) {
			b=false;
		System.out.println("IS NULL");
		}else {
			if(admin.getPassword().compareTo(password)==0)
				b=true;
			else {
				b=false;
			}
		}
		dbManager.close();
		return b;
	}
	public boolean isAdmin(String Username) {
		this.dbManager.open(false);
		Admin admin =dbManager.getAdminbyUserName(Username);
		
		boolean b ;
		if(admin==null ) {
			b=false;
	}else {
		b=true;
	}
		this.dbManager.close();
		return b;
	}*/
    @Override
    @WebMethod
	public boolean isEmployee(String Username) {
		this.dbManager.open(false);
		Employee emp =dbManager.getEmployeebyUserName(Username);
		
		boolean b ;
		if(emp==null ) {
			b=false;
	}else {
		b=true;
	}
		this.dbManager.close();
		return b;
	}
    @Override
    @WebMethod
	public boolean isUser(String Username) {
		this.dbManager.open(false);
		User u =dbManager.getUserbyUserName(Username);
		
		boolean b ;
		if(u==null ) {
			b=false;
	}else {
		b=true;
	}
		this.dbManager.close();
		return b;
	}
    @Override
    @WebMethod
	public void storeEvent(String description, Date date) {
		this.dbManager.open(false);
		dbManager.storeEvent(description, date);
		this.dbManager.close();
	}
    @Override
    @WebMethod
	public void deleteEvent(int eventNumber) {
		this.dbManager.open(false);
		dbManager.deleteEvent(eventNumber);
		this.dbManager.close();
	}


	   @WebMethod
	public User getUserbyUserName(String username1) {
		this.dbManager.open(false);
		User user = dbManager.getUserbyUserName(username1);
		this.dbManager.close();
		return user;
	}
	@Override
	   @WebMethod
	public void updateBalance(String username, double amount) {
		this.dbManager.open(false);
		this.dbManager.updateBalance(username, amount);
		this.dbManager.close();
	}
	   @Override
	   @WebMethod
	public void storeOption(Question q, String description, double odd) {
		this.dbManager.open(false);
		this.dbManager.storeOption(q,description,odd);
		this.dbManager.close();
	}
	   @Override
	   @WebMethod
	public void setResult(int questionNumber, Option result) {
		this.dbManager.open(false);
		this.dbManager.setResult(questionNumber,result);
		this.dbManager.close();
	}
	   @Override
	   @WebMethod
	public void makeBet(User user, Vector<Option> optionList, Double amount) {
		this.dbManager.open(false);
		this.dbManager.makeBet(user, optionList, amount);
		this.dbManager.close();
	}

	@Override
	   @WebMethod
	public void addInvitedTo(String username, String username2, String amount) {
		this.dbManager.open(false);
		this.dbManager.addInvitedTo(username,username2,amount);
		this.dbManager.close();
	}
	@Override
	   @WebMethod
	public void sendRequest(String username, String username2) {
		this.dbManager.open(false);
		this.dbManager.sendRequest(username,username2);
		this.dbManager.close();
	}
	   @Override
	   @WebMethod
	public void acceptRequest(String username, String username2) {
		this.dbManager.open(false);
		this.dbManager.acceptRequest(username,username2);
		this.dbManager.close();
	}
	   @Override
	   @WebMethod
		public void rejectRequest(String username, String username2) {
			this.dbManager.open(false);
			this.dbManager.rejectRequest(username,username2);
			this.dbManager.close();
		}

	@Override
	 @WebMethod
	public void UpdateUserbyUserName(String Username, User u) {
		this.dbManager.open(false);
		u=this.dbManager.UpdateUserbyUserName(Username,u);
		this.dbManager.close();
		
	}

	@Override
	 @WebMethod
	public Vector<String> getInvitedTo(String Username) {
		this.dbManager.open(false);
		Vector<String> List=this.dbManager.getInvitedTo(Username);
		if(List!=null)System.out.println(List.size());
		this.dbManager.close();
		return List;
	}

	@Override
	 @WebMethod
	public Vector<String> getRequest(String Username) {
		this.dbManager.open(false);
		Vector<String> List=this.dbManager.getRequest(Username);
		if(List!=null)System.out.println(List.size());
		this.dbManager.close();
		return List;
	}

	@Override
	 @WebMethod
	public Vector<String> getCopyYou(String Username) {
		this.dbManager.open(false);
		Vector<String> List=this.dbManager.getCopyYou(Username);
		if(List!=null)System.out.println(List.size());
		this.dbManager.close();
		return List;
	}

	@Override
	 @WebMethod
	public Vector<String> getYouCopy(String Username) {
		this.dbManager.open(false);
		Vector<String> List=this.dbManager.getYouCopy(Username);
		if(List!=null)System.out.println(List.size());
		this.dbManager.close();
		return List;
	}
	@WebMethod
    public void stopCopying(String username, String username2) {
        this.dbManager.open(false);
        this.dbManager.stopCopying(username,username2);
        this.dbManager.close();
    }
	 
}


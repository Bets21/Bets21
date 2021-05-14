package dataAccess;

//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Bet;
import domain.Employee;
import domain.Event;
import domain.InvitedTo;
import domain.Option;
import domain.Person;
import domain.Question;
import domain.Transaction;
import domain.User;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
		 new DataAccess(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			
			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month+1,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28));
			
			Employee employee=new Employee("e","e","e","e");
			User user=new User("Be�at","Sarasua","bsarasua","pass",19);
			Question q0;
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q0=ev1.addQuestion("¿Cuántos goles se marcarán?",3);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q0=ev1.addQuestion("How many goals will be scored in the match?",3);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q0=ev1.addQuestion("Zenbat gol sartuko dira?",3);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6); 
			db.persist(q0);
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);	
			
			db.persist(employee);
			db.persist(user);
			
			db.getTransaction().commit();
			this.storeOption(q1, "ATHLETIC", 1.9);
			this.storeOption(q1, "DRAW", 2);
			this.storeOption(q1, "ATLETICO", 2.2);
			
			this.storeOption(q2, "Williams", 2);
			this.storeOption(q2, "Asier Villalibre", 1.65);
			this.storeOption(q2, "Raul Garcia", 1.3);
			this.storeOption(q2, "Joao Felix", 1.2);
			this.storeOption(q2, "Luis Suarez", 1.12);
			this.storeOption(q2, "Marcos LLorente", 1.79);
			
			this.storeOption(q0, "1", 2);
			this.storeOption(q0, "2", 1.65);
			this.storeOption(q0, "3", 1.45);
			this.storeOption(q0, "4", 1.25);
			this.storeOption(q0, "5", 1.12);
			this.storeOption(q0, "6+", 1.0);
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	

public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);
		
	}
	public void storeUser(String pName, String pSurname, String pUsername, String pPassword, int pAdina) {
	db.getTransaction().begin();
	User user = new User(pName, pSurname, pUsername,pPassword,pAdina);
	db.persist(user);
	 db.getTransaction().commit();
	 System.out.println("Gordeta " + pUsername);
	}
	
	public void storeUserWithInvitation(String pName, String pSurname, String pUsername, String pPassword, int pAdina,String invitedBy) {
		db.getTransaction().begin();
		User user = new User(pName, pSurname, pUsername,pPassword,pAdina,invitedBy);
		db.persist(user);
		 db.getTransaction().commit();
		 System.out.println("Gordeta " + pUsername);
	}
	
	public void storeEmployee(String pName, String pSurname, String pUsername, String pPassword) {
	db.getTransaction().begin();
	Employee Emp = new Employee(pName, pSurname, pUsername,pPassword);
	db.persist(Emp);
	 db.getTransaction().commit();
	 System.out.println("Gordeta " + pUsername);
	}
	/*public void storeAdmin(String pName, String pSurname, String pUsername, String pPassword) {
	db.getTransaction().begin();
	Admin admin = new Admin(pName, pSurname, pUsername,pPassword);
	db.persist(admin);
	 db.getTransaction().commit();
	 System.out.println("Gordeta " + pUsername);
	}
	public Admin getAdminbyUserName(String Username) {
		return db.find(Admin.class,Username);
	}*/
	public static EntityManager getDb() {
		return db;
	}
	
	public static void setDb(EntityManager db) {
		DataAccess.db = db;
	}
	
	public static EntityManagerFactory getEmf() {
		return emf;
	}
	
	public static void setEmf(EntityManagerFactory emf) {
		DataAccess.emf = emf;
	}
	
	public Employee getEmployeebyUserName(String Username) {		
		return db.find(Employee.class,Username);
		
		
	}
	public User UpdateUserbyUserName(String Username, User u) {		
		u=db.find(User.class,Username);
		return u;
	}
	
	public Vector<String> getInvitedTo(String Username) {		
		User u=db.find(User.class,Username);
		Vector<String> invitedTo=u.getInvitedTo();
		if(invitedTo!=null)System.out.println(invitedTo.size());
		return invitedTo;
	}
	public Vector<String> getRequest(String Username) {		
		User u=db.find(User.class,Username);
		Vector<String> Request =u.getRequests();
		if(Request!=null)System.out.println(Request.size());
		return Request;
	}
	public Vector<String> getCopyYou(String Username) {		
		User u=db.find(User.class,Username);
		Vector<String> copyYou =u.getCopyYou();
		if(copyYou!=null)System.out.println(copyYou.size());
		return copyYou;
	}
	public Vector<String> getYouCopy(String Username) {		
		User u=db.find(User.class,Username);
		Vector<String> Youcopy =u.getYouCopy();
		if(Youcopy!=null)System.out.println(Youcopy.size());
		return Youcopy;
	}
	
	public User getUserbyUserName(String Username) {
		return db.find(User.class,Username);
	 }
	
	

	
	public int getIndex() {
		TypedQuery<Event> query = db.createQuery("SELECT MAX ev.eventNumber FROM Event ev",Event.class);   
		List<Event> index = query.getResultList();
		int result = index.get(0).getEventNumber();
		return result;
	}
	
	public void storeEvent(String description, Date date) {
		db.getTransaction().begin();
		Event event = new Event(description, date);
		db.persist(event);
		db.getTransaction().commit();
		System.out.println("Gordeta "+description);
		
	}
	
	public void deleteEvent(int eventNumber) {
		Event event = db.find(Event.class,eventNumber);
		db.getTransaction().begin();
		db.remove(event);
		db.getTransaction().commit();
	}
	
	public void updateBalance(String username, double amount) {
		User user = getUserbyUserName(username);
		double currentBalance = user.getUnekoSaldoa();
		Transaction t = new Transaction(user, amount);
		db.getTransaction().begin();
		user.setUnekoSaldoa(currentBalance+amount);
		user.addTransaction(t);
		db.getTransaction().commit();
	    System.out.println("Balance has been added");
	}
	
	public void updateBalanceWin(String username, double amount) {
		User user = getUserbyUserName(username);
		double currentBalance = user.getUnekoSaldoa();
		Transaction t = new Transaction(user, amount);
		user.setUnekoSaldoa(currentBalance+amount);
		user.addTransaction(t);
	    System.out.println("Balance has been added");
	}
	
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public void storeOption(Question q, String description, double odd) {
		db.getTransaction().begin();
		Question question = db.find(Question.class,q.getQuestionNumber());
		Option option = new Option(description,odd,q);
		question.addOption(option);
		db.persist(question);
		db.getTransaction().commit();
		System.out.println("Option has been added");
	}

	public void setResult(int questionNumber, Option result) {
		Question q = db.find(Question.class, questionNumber);
		db.getTransaction().begin();
		q.setResult(result);
		Vector<Bet> obets;
		Vector<Option> qoptions = q.getOptions();
		Transaction t = null;
		for (Option o: qoptions) {
			if (o.getBets()!=null) {
				Vector<Bet> betsResult = o.getBets();
				if (o.getDescription().equals(result.getDescription())) {
					o.setResult("Won");
					for (Bet b: betsResult) {
						b.addWin();
						if (b.getOptionList().size()==1) {
							b.setResult("Won");
							Double amount = b.getBetAmount();
							Vector<Option> winOptionList = b.getOptionList();
							Double odd = 1.0;
							for (Option winOption: winOptionList) {
								odd=odd*winOption.getOdd();
								System.out.println(winOption.getOdd()+"..."+odd);
							}
							User u = b.getUser();
							System.out.println(u.getUsername());
							Double comission = 1.0;
							if(u.getCopyYou() != null) {
								comission = 1+u.getCopyYou().size()*0.1;
							}
							this.updateBalanceWin(u.getUsername(), amount*odd*comission);
							t = new Transaction(u, amount*odd);
							db.persist(t);
						}else {
							if (b.getWins()==b.getOptionList().size()-1) {
								b.setResult("Won");
								Double amount = b.getBetAmount();
								Double odd = 1.0;
								Vector<Option> winOptionList = b.getOptionList();
								for (Option winOption: winOptionList) {
									odd=odd*winOption.getOdd();
									System.out.println(winOption.getOdd()+"..."+odd);
								}
								User u = b.getUser();
								this.updateBalanceWin(u.getUsername(), amount*odd);
								t = new Transaction(u, amount*odd);
								db.persist(t);
							}
						}
						
					}
				}else {
					o.setResult("Lost");
					for (Bet b: betsResult) {
						b.setResult("Lost");
					}
				}
			}
		}
		q.setOptions(qoptions);
		db.persist(q);
		db.getTransaction().commit();
		System.out.println("Result added succesfully");
	}

	public void makeBet(User user, Vector<Option> optionList, Double amount) {
		User u = db.find(User.class, user.getUsername());
		Vector<String> CopyYou = u.getCopyYou();
		if (CopyYou != null) {
			for (String copyusername: CopyYou) {
				User copyuser = db.find(User.class, copyusername);
				if (copyuser.getUnekoSaldoa()>=amount) {
					System.out.println(copyuser.getUsername()+" copied the bet.");
					makeBet(copyuser, optionList, amount);
					updateBalance(copyuser.getUsername(), -amount);
				}
			}
		}
		Vector<Option> newOptionList = new Vector<Option>();
		for (Option option: optionList) {
			Option o = db.find(Option.class, option.getId());
			newOptionList.add(o);
		}
		
		db.getTransaction().begin();
		Vector<Bet> bets = u.getBets();
		Bet b = new Bet(newOptionList, amount, u);
		u.addBet(b);
		for (Option o: newOptionList) {
			o.addBet(b);
		}
		db.getTransaction().commit();
	}

	public void addInvitedTo(String username, String username2, String amount) {
		User u = db.find(User.class, username);
		double amount1 = Double.parseDouble(amount);
		double amount2 = amount1*0.25;
		db.getTransaction().begin();
		u.addInvitedTo(username2);
		u.addInvitedTo(Double.toString(amount1)+"�");
		u.addInvitedTo(Double.toString(amount2)+"�");
		db.getTransaction().commit();
	}

	public void sendRequest(String username, String username2) {
		User u = db.find(User.class, username2);
		db.getTransaction().begin();
		if (u.getRequests()==null) {
			u.addRequests(username);
			System.out.println("Request sent.");
		}else {
			if (u.getRequests().contains(username) || u.getCopyYou().contains(username)) {
				System.out.println("The request has been already sent");
			}else if (u.getYouCopy().contains(username)) {
				System.out.println("You cannot copy someone who is copying you.");
			}else {
				u.addRequests(username);
				System.out.println("Request sent.");
			}
		}
		db.getTransaction().commit();
	}
	public void stopCopying(String username, String username2) {
        User u = db.find(User.class, username);
        User u2 = db.find(User.class, username2);
        db.getTransaction().begin();
        u.removeYouCopy(username2);
        u2.removeCopyYou(username);
        db.getTransaction().commit();
    }

	public void rejectRequest(String username, String username2) {
		User u = db.find(User.class, username);
		db.getTransaction().begin();
		u.removeRequests(username2);
		db.getTransaction().commit();
	}

	public void acceptRequest(String username, String username2) {
		User u = db.find(User.class, username);
		User u2 = db.find(User.class, username2);
		db.getTransaction().begin();
		u.addCopyYou(username2);
		u.removeRequests(username2);
		u2.addYouCopy(username);
		db.getTransaction().commit();
	}

	public Person getPersonbyUserName(String username) {
		return db.find(Person.class,username);
	}
}
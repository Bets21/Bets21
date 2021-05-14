package businessLogic;

import java.util.Vector;
import java.util.Date;





//import domain.Booking;
import domain.Question;
import domain.User;
import domain.Event;
import domain.Option;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();

	@WebMethod boolean isLogin(String username, String password);
	
	@WebMethod boolean loginUser(String username, String password);


	@WebMethod boolean loginEmployee(String username, String password);


	//boolean loginAdmin(String username, String password);

	@WebMethod
	void storeUser(String pName, String pSurname, String pUsername, String pPassword, int pAdina);

	@WebMethod
	public void UpdateUserbyUserName(String Username, User u);
	
	@WebMethod
	public void storeUserWithInvitation(String pName, String pSurname, String pUsername, String pPassword, int pAdina,String invitedBy);

	@WebMethod
	void storeEmployee(String pName, String pSurname, String pUsername, String pPassword);


	//void storeAdmin(String pName, String pSurname, String pUsername, String pPassword);
	@WebMethod
	boolean isUser(String Username);
	
	@WebMethod
	boolean isEmployee(String Username);
	
	//boolean isAdmin(String Username);
	@WebMethod
	void storeEvent(String description, Date date);
	
	@WebMethod
	void deleteEvent(int eventNumber);

	@WebMethod
	User getUserbyUserName(String username1);
	
	@WebMethod
	void updateBalance(String username, double amount);
	
	@WebMethod
	void storeOption(Question q, String description, double odd);
	
	@WebMethod
	void setResult(int questionNumber, Option result);

	@WebMethod
	void makeBet(User user, Vector<Option> optionList, Double amount);

	@WebMethod
	void addInvitedTo(String username, String username2, String amount);
	
	@WebMethod
	void sendRequest(String username, String username2);
	
	@WebMethod
	void acceptRequest(String username, String username2);
	
	@WebMethod
	public void rejectRequest(String username, String username2);

	@WebMethod
	public Vector<String> getInvitedTo(String Username);
	
	@WebMethod
	public Vector<String> getRequest(String Username);
	
	@WebMethod
	public Vector<String> getCopyYou(String Username);
	
	@WebMethod
	public Vector<String> getYouCopy(String Username);
	
	@WebMethod
    public void stopCopying(String username, String username2);
}

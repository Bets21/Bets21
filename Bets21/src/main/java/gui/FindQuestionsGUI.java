package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Employee;
import domain.Option;

import domain.Question;
import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class FindQuestionsGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	
	private Vector<Option> optionList = new Vector<Option>();
	
	private MainUserGUI UserGUI;
	private User updatedUser;
	private String Username=null;
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	 private static BLFacade appFacadeInterface;
		
		public static BLFacade getBusinessLogic(){
			return appFacadeInterface;
		}
		 
		public static void setBussinessLogic (BLFacade afi){
			appFacadeInterface=afi;
		}
	private DefaultTableModel tableModelBets;
	private CopyUserGUI nireFrame;
	private JScrollPane scrollPanelBets = new JScrollPane();
	private JTable tableBets;
	
	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	
	private Date today;
	private Date firstDay;
	
	private User user;
	private Employee employee;

	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	
	private double minimum = -1;
	
	private JTextField AmountField;
	private final JLabel lblBalance = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblListOfBets = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.lblListOfBets.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton btnMakeABet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MakeABet")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JTextPane textInfoMultiple = new JTextPane();

	public FindQuestionsGUI()
	{
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public FindQuestionsGUI(User user, MainUserGUI pMainUserGUI) {
		try
		{
			this.UserGUI = pMainUserGUI;
			appFacadeInterface= MainOutGUI.getBusinessLogic();
			this.Username=user.getUsername();
			this.user = appFacadeInterface.getUserbyUserName(Username);
			lblBalance.setText(ResourceBundle.getBundle("Etiquetas").getString("Balance")+": "+user.getUnekoSaldoa()+"Ä");
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public FindQuestionsGUI(Employee employee) {
		try
		{
			this.employee = employee;
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void jbInit() throws Exception
	{
		
		appFacadeInterface= MainOutGUI.getBusinessLogic();
		this.Username=user.getUsername();
		this.user = appFacadeInterface.getUserbyUserName(Username);
		lblBalance.setText(ResourceBundle.getBundle("Etiquetas").getString("Balance")+": "+user.getUnekoSaldoa()+"Ä");

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 656));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(40, 252, 406, 14);
		jLabelEvents.setBounds(295, 19, 209, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(186, 579, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		BLFacade facade = MainOutGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{
				if (tableModelQueries!=null) {
					if (tableModelQueries.getRowCount() > 0) {
					    for (int i = tableModelQueries.getRowCount() - 1; i > -1; i--) {
					    	tableModelQueries.removeRow(i);
					    }
					}
				}
				
				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					 
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar est√° 30 de enero y se avanza al mes siguiente, devolver√≠a 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este c√≥digo se dejar√° como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainOutGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
					}

					CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainOutGUI.getBusinessLogic();
						
						today = Calendar.getInstance().getTime();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(SystemColor.control);
		textPane.setForeground(Color.RED);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPane.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		textPane.setBounds(40, 221, 406, 20);
		getContentPane().add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBackground(SystemColor.control);
		textPane_1.setForeground(new Color(0, 128, 0));
		textPane_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPane_1.setText(" "); //$NON-NLS-1$ //$NON-NLS-2$
		textPane_1.setBounds(473, 385, 182, 31);
		getContentPane().add(textPane_1);
		
		JLabel lblMakeABet = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MakeABet")); //$NON-NLS-1$ //$NON-NLS-2$
		lblMakeABet.setBounds(473, 251, 92, 14);
		getContentPane().add(lblMakeABet);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(476, 302, 182, 20);
		getContentPane().add(comboBox);
		
		JLabel lblOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Option")); //$NON-NLS-1$ //$NON-NLS-2$
		lblOption.setBounds(473, 277, 117, 14);
		getContentPane().add(lblOption);
		
		JLabel lblAmount = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Amount")); //$NON-NLS-1$ //$NON-NLS-2$
		lblAmount.setBounds(476, 490, 182, 14);
		getContentPane().add(lblAmount);
		
		AmountField = new JTextField();
		AmountField.setText(" "); //$NON-NLS-1$ //$NON-NLS-2$
		AmountField.setBounds(476, 514, 182, 20);
		getContentPane().add(AmountField);
		AmountField.setColumns(10);
		
		JButton btnAddBet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddBet")); //$NON-NLS-1$ //$NON-NLS-2$
		btnAddBet.setBounds(476, 332, 182, 23);
		getContentPane().add(btnAddBet);
		lblBalance.setBounds(514, 20, 124, 14);
		
		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 277, 406, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPane_1.setVisible(false);
				lblMakeABet.setVisible(false);
				comboBox.setVisible(false);
				lblOption.setVisible(false);
				btnAddBet.setVisible(false);
				
				textPane.setText(" ");
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q);
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2)); // not shown in JTable
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		
		String column[]={"Event","Question","Option"};  
		tableModelBets = new DefaultTableModel(column, 0) {
			 @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		
		tableBets = new JTable();
		tableBets.setBounds(308, 348, 262, 109);
		scrollPanelBets.setSize(407, 128);
		scrollPanelBets.setLocation(39, 439);
		this.getContentPane().add(scrollPanelBets);
		tableBets.setModel(tableModelBets);
		scrollPanelBets.setViewportView(tableBets);
		
		getContentPane().add(lblBalance);
		
		JTextPane minAmountError = new JTextPane();
		minAmountError.setBackground(SystemColor.control);
		minAmountError.setForeground(Color.RED);
		minAmountError.setFont(new Font("Tahoma", Font.BOLD, 11));
		minAmountError.setText(" "); //$NON-NLS-1$ //$NON-NLS-2$
		minAmountError.setBounds(476, 579, 182, 30);
		getContentPane().add(minAmountError);
		lblListOfBets.setBounds(40, 411, 406, 14);
		
		getContentPane().add(lblListOfBets);
		btnMakeABet.setBounds(476, 544, 182, 25);
		
		getContentPane().add(btnMakeABet);
		textInfoMultiple.setEditable(false);
		textInfoMultiple.setBackground(SystemColor.control);
		textInfoMultiple.setFont(new Font("Tahoma", Font.ITALIC, 12));
		textInfoMultiple.setText(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.textPane_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
		textInfoMultiple.setBounds(476, 426, 182, 57);
		
		getContentPane().add(textInfoMultiple);
		
		lblMakeABet.setVisible(false);
		comboBox.setVisible(false);
		lblOption.setVisible(false);
		lblAmount.setVisible(false);
		AmountField.setVisible(false);
		textInfoMultiple.setVisible(false);
		btnAddBet.setVisible(false);
		btnMakeABet.setVisible(false);
		scrollPanelBets.setVisible(false);
		lblListOfBets.setVisible(false);
		
		tableQueries.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				comboBox.removeAllItems();
				if (user != null) {
					
					if (firstDay.before(today)) {
						textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("EventFinished"));
					} else {
						
						lblMakeABet.setVisible(true);
						comboBox.setVisible(true);
						btnAddBet.setVisible(true);
						lblOption.setVisible(true);
						
						int i=tableQueries.getSelectedRow();
						domain.Question question=(domain.Question)tableModelQueries.getValueAt(i,2); // obtain ev object
						Vector<Option> options=question.getOptions();
						
						if(question.getResult()!=null) {

						}else {
							
						}
						
						for (Option o: options) {
							System.out.println(o.getDescription());
							comboBox.addItem(o); 
						}
					}
				}
			}
		});

		btnAddBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Option option = (Option) comboBox.getSelectedItem();
				int k=tableEvents.getSelectedRow();
				domain.Event event=(domain.Event)tableModelEvents.getValueAt(k,2);
				int i=tableQueries.getSelectedRow();
				domain.Question question=(domain.Question)tableModelQueries.getValueAt(i,2);
				if (minimum > question.getBetMinimum()) {
					minimum = question.getBetMinimum();
				}
				lblAmount.setVisible(true);
				AmountField.setVisible(true);
				textInfoMultiple.setVisible(true);
				btnMakeABet.setVisible(true);
				Object[] row = {event, question, option};
				scrollPanelBets.setVisible(true);
				lblListOfBets.setVisible(true);
				
				boolean Error=false;
				Integer newOptionnum=option.getQuestion().getQuestionNumber();
				for (int j=0;j<optionList.size();j++) {
					Integer currentoptionnum=optionList.get(j).getQuestion().getQuestionNumber();
					if(currentoptionnum==newOptionnum) {
						Error=true;
					}
				}
				if(Error==false) {
				optionList.add(option);
				tableModelBets.addRow(row);
				}else {
					textPane_1.setVisible(true);
					textPane_1.setText(ResourceBundle.getBundle("Etiquetas").getString("SameQuestion"));
				}
			}
		});
		
		btnMakeABet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double amount = Double.parseDouble(AmountField.getText());
				if (amount >= minimum) {
					if (user.getUnekoSaldoa() >= amount) {
						minAmountError.setVisible(false);
						textPane_1.setVisible(true);
						appFacadeInterface.makeBet(user, optionList, amount);
						appFacadeInterface.updateBalance(user.getUsername(),-amount);
						updatedUser=appFacadeInterface.getUserbyUserName(user.getUsername());
						UserGUI.updateUser();
						textPane_1.setText(ResourceBundle.getBundle("Etiquetas").getString("BetSuccessful"));
						AmountField.setText("");
						user.setUnekoSaldoa(user.getUnekoSaldoa()-amount);
						lblBalance.setText(ResourceBundle.getBundle("Etiquetas").getString("Balance")+": "+user.getUnekoSaldoa()+"Ä");
					}else {
						textPane_1.setVisible(false);
						minAmountError.setVisible(true);
						minAmountError.setText(ResourceBundle.getBundle("Etiquetas").getString("NoMoney"));
					}
				}else {
					textPane_1.setVisible(false);
					minAmountError.setVisible(true);
					minAmountError.setText(ResourceBundle.getBundle("Etiquetas").getString("BetMinimum")+minimum+"Ä");
				}
			}
		});
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}

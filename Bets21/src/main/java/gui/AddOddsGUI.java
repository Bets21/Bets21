package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Option;
import domain.Question;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class AddOddsGUI extends JFrame {
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
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private Question selectedQuestion;
	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private JTextField textField;
	private JTextField textField_1;
	
	private Date today;
	private Date firstDay;
	private final JTextPane errorPane = new JTextPane();

	public AddOddsGUI()
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

	
	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 479));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(40, 220, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		JLabel AddOptionlbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddOdd")); //$NON-NLS-1$ //$NON-NLS-2$
		AddOptionlbl.setBounds(472, 221, 166, 13);
		getContentPane().add(AddOptionlbl);
		AddOptionlbl.setVisible(false);
		
		JLabel lblOptionDescription = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Option")); //$NON-NLS-1$ //$NON-NLS-2$
		lblOptionDescription.setBounds(472, 246, 176, 13);
		getContentPane().add(lblOptionDescription);
		lblOptionDescription.setVisible(false);
		
		JLabel lblOptionOdd = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Odd")); //$NON-NLS-1$ //$NON-NLS-2$
		lblOptionOdd.setBounds(472, 294, 45, 13);
		getContentPane().add(lblOptionOdd);
		lblOptionOdd.setVisible(false);
		
		JButton btnSetResult = new JButton(ResourceBundle.getBundle("Etiquetas").getString("PutResult")); //$NON-NLS-1$ //$NON-NLS-2$
		btnSetResult.setBounds(472, 304, 176, 21);
		getContentPane().add(btnSetResult);
		btnSetResult.setVisible(false);
		
		textField = new JTextField();
		textField.setBounds(472, 265, 176, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		textField_1 = new JTextField();
		textField_1.setBounds(472, 310, 176, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setVisible(false);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(472, 269, 176, 21);
		getContentPane().add(comboBox);
		comboBox.setVisible(false);
		
		JButton btnAddOdd = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Add")); //$NON-NLS-1$ //$NON-NLS-2$
		btnAddOdd.setBounds(472, 341, 176, 21);
		getContentPane().add(btnAddOdd);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setForeground(new Color(0, 153, 0));
		textPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		textPane.setBackground(SystemColor.control);
		textPane.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		textPane.setBounds(463, 402, 201, 30);
		getContentPane().add(textPane);
				
		jButtonClose.setBounds(new Rectangle(174, 391, 130, 30));

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
				errorPane.setText("");
				textPane.setText("");
				AddOptionlbl.setVisible(false);
				lblOptionDescription.setVisible(false);
				lblOptionOdd.setVisible(false);
				btnAddOdd.setVisible(false);
				textField.setVisible(false);
				textField_1.setVisible(false);
				btnSetResult.setVisible(false);
				comboBox.setVisible(false);
				comboBox.removeAllItems();
				
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
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
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

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 246, 406, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddOptionlbl.setVisible(false);
				lblOptionDescription.setVisible(false);
				lblOptionOdd.setVisible(false);
				btnAddOdd.setVisible(false);
				comboBox.setVisible(false);
				btnSetResult.setVisible(false);
				textField.setVisible(false);
				textField_1.setVisible(false);
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

		btnAddOdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField.getText().length() != 0 && textField_1.getText().length() != 0){
						errorPane.setText("");
						String description = textField.getText();
						Double odd = Double.parseDouble(textField_1.getText());
						//System.out.println(tableQueries.getSelectedRow()+", "+tableQueries.getSelectedColumn());
						int i=tableQueries.getSelectedRow();
						domain.Question q=(domain.Question)tableModelQueries.getValueAt(i,2);
						BLFacade facade = MainGUI.getBusinessLogic();
						facade.storeOption(q, description, odd);
						textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("AddOptionSuccessful"));
						textField.setText("");
						textField_1.setText("");
					}else {
						errorPane.setText(ResourceBundle.getBundle("Etiquetas").getString("DescriptionOddEmpty"));
					}
				}catch(java.lang.NumberFormatException ex) {
					errorPane.setText(ResourceBundle.getBundle("Etiquetas").getString("OddNumberError"));
				}
			}
			
		});
		btnAddOdd.setVisible(false);
		
		tableQueries.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (firstDay.before(today)) {
					comboBox.removeAllItems();
					AddOptionlbl.setText(ResourceBundle.getBundle("Etiquetas").getString("PutResult"));
					AddOptionlbl.setVisible(true);
					comboBox.setVisible(true);
					btnSetResult.setVisible(true);
					
					int i=tableQueries.getSelectedRow();
					domain.Question question=(domain.Question)tableModelQueries.getValueAt(i,2); // obtain ev object
					Vector<Option> options=question.getOptions();
					
					if(question.getResult()!=null) {
						lblOptionDescription.setText(ResourceBundle.getBundle("Etiquetas").getString("Result")+question.getResult().toString());
						btnSetResult.setText(ResourceBundle.getBundle("Etiquetas").getString("ChangeResult"));
					}else {
						lblOptionDescription.setText(ResourceBundle.getBundle("Etiquetas").getString("Result")+"-");
						btnSetResult.setText(ResourceBundle.getBundle("Etiquetas").getString("PutResult"));
					}
					lblOptionDescription.setVisible(true);
					
					for (Option o: options) {
						System.out.println(o.getDescription());
						comboBox.addItem(o); 
					}
				} else {
					AddOptionlbl.setText(ResourceBundle.getBundle("Etiquetas").getString("AddOdd"));
					AddOptionlbl.setVisible(true);
					lblOptionDescription.setText(ResourceBundle.getBundle("Etiquetas").getString("Option"));
					lblOptionDescription.setVisible(true);
					lblOptionOdd.setVisible(true);
					btnAddOdd.setVisible(true);
					textField.setVisible(true);
					textField_1.setVisible(true);
				}
				
			}
		});
		
		btnSetResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableQueries.getSelectedRow();
				domain.Question question=(domain.Question)tableModelQueries.getValueAt(i,2); // obtain ev object
				Option selectedResult = (Option)comboBox.getSelectedItem();
				lblOptionDescription.setText(ResourceBundle.getBundle("Etiquetas").getString("Result")+selectedResult.toString());
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.setResult(question.getQuestionNumber(),selectedResult);
				textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("ResultAdded"));
			}
		});
		
		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		errorPane.setEditable(false);
		errorPane.setBackground(SystemColor.control);
		errorPane.setForeground(new Color(255, 0, 51));
		errorPane.setFont(new Font("Tahoma", Font.BOLD, 11));
		errorPane.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		errorPane.setBounds(40, 362, 389, 19);
		
		getContentPane().add(errorPane);
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}

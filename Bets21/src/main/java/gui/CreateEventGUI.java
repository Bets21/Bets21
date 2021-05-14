package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JComboBox;
import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;

import javax.swing.JTextField;
import java.awt.Label;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

public class CreateEventGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textDescription;
	private CreateEventGUI nireFrame;
	private Date newDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEventGUI frame = new CreateEventGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateEventGUI() {
		nireFrame = this;
		this.setTitle("Create Event");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCalendar jCalendar1 = new JCalendar();
		jCalendar1.setBounds(34, 38, 225, 150);
		contentPane.add(jCalendar1);
		
		textDescription = new JTextField();
		textDescription.setBounds(281, 66, 238, 19);
		contentPane.add(textDescription);
		textDescription.setColumns(10);
		
		Label label = new Label("Description:");
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setBounds(281, 36, 91, 19);
		contentPane.add(label);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(SystemColor.control);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane.setForeground(Color.RED);
		textPane.setBounds(281, 135, 238, 30);
		contentPane.add(textPane);
		
		/*jCalendar1.addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent propertychangeevent){
				java.util.Date date = jCalendar1.getDate();
				newDate = new Date(date.getYear(),date.getMonth(),date.getDay());
			}
		});*/
		
		JButton btnCreateEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		btnCreateEvent.setBounds(281, 95, 238, 30);
		contentPane.add(btnCreateEvent);
		btnCreateEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date selectedDate = trim(new Date(jCalendar1.getCalendar().getTime().getTime()));
				String description = textDescription.getText();
				BLFacade facade = MainGUI.getBusinessLogic();
				if (selectedDate != null) {
					if (description.length()>0) {
						facade.storeEvent(description, selectedDate);
						textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("EventCreated"));
					}else {
						textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDescMissing"));
					}
				}else {
					textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("EventDateMissing"));
				}
			}
		});
		
		
		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.setBounds(224, 206, 99, 30);
		contentPane.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nireFrame.setVisible(false);
			}	
		});
	}
	
	private java.util.Date trim(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}
}

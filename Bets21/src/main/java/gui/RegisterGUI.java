package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Font;

public class RegisterGUI extends JFrame {
	private RegisterGUI nireFrame;
	private JPanel contentPane;
	private JPasswordField RepeatpasswordField;
	private JPasswordField passwordField_1;
	private JTextField UsernameField;
	private JTextField SurnameField;
	private JTextField NameField;
	private JTextField AgeCodeField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
private static BLFacade appFacadeInterface;
private JTextField InvitedByField;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		nireFrame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Name")+":");
		lblNewLabel.setBounds(120, 67, 105, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Surname")+":");
		lblNewLabel_1.setBounds(120, 93, 105, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username")+":");
		lblNewLabel_2.setBounds(120, 118, 105, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password")+":");
		lblNewLabel_3.setBounds(120, 142, 105, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RepeatPass")+":");
		lblNewLabel_4.setBounds(120, 167, 120, 14);
		contentPane.add(lblNewLabel_4);
		
		RepeatpasswordField = new JPasswordField();
		RepeatpasswordField.setBounds(250, 167, 127, 20);
		contentPane.add(RepeatpasswordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(250, 142, 127, 20);
		contentPane.add(passwordField_1);
		
		UsernameField = new JTextField();
		UsernameField.setBounds(250, 118, 127, 20);
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		SurnameField = new JTextField();
		SurnameField.setBounds(250, 93, 127, 20);
		contentPane.add(SurnameField);
		SurnameField.setColumns(10);
		
		NameField = new JTextField();
		NameField.setBounds(250, 67, 127, 20);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		JLabel AgeLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Age")+":");
		AgeLabel.setBounds(120, 192, 105, 14);
		contentPane.add(AgeLabel);
		
		JTextPane ErrorPane = new JTextPane();
		ErrorPane.setForeground(Color.RED);
		ErrorPane.setEditable(false);
		ErrorPane.setBackground(SystemColor.menu);
		ErrorPane.setBounds(132, 292, 238, 41);
		contentPane.add(ErrorPane);

		AgeCodeField = new JTextField();
		AgeCodeField.setBounds(250, 192, 127, 20);
		contentPane.add(AgeCodeField);
		AgeCodeField.setColumns(10);
		JButton RegisterButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register").toUpperCase());
		//Register botoia sakatzean
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pName=NameField.getText();
				String pSurname= SurnameField.getText();
				String pUsername = UsernameField.getText();
				String pPassword = passwordField_1.getText();
				String Rpassword = RepeatpasswordField.getText();
				String InvitedByname=InvitedByField.getText();
				User InvitedBy=null;
				if(InvitedByname.compareTo("")!=0) {
				 InvitedBy=appFacadeInterface.getUserbyUserName(InvitedByname);
				}
				try {
					int pAdina = Integer.parseInt(AgeCodeField.getText());
					if(pPassword.compareTo(Rpassword)==0){
						if(pAdina>=18) {		
							if(InvitedByname.compareTo("")==0) {
							appFacadeInterface.storeUser(pName, pSurname, pUsername, pPassword, pAdina);
							LoginGUI l1= new LoginGUI();
							l1.setBussinessLogic(appFacadeInterface);
							l1.setVisible(true);
							nireFrame.setVisible(false);
							}else {
								if(InvitedBy!=null) {
								appFacadeInterface.storeUserWithInvitation(pName, pSurname, pUsername, pPassword, pAdina,InvitedByname);
								LoginGUI l1= new LoginGUI();
								l1.setBussinessLogic(appFacadeInterface);
								l1.setVisible(true);
								nireFrame.setVisible(false);
								}else {
									ErrorPane.setText("the username that invited you doesn't exist");
								}
							}
						}else {
							ErrorPane.setText("You have to be older than 18 to register");
						}
					}else {
						ErrorPane.setText("The passwords are different");
					}
				}
				catch( NumberFormatException n) {
					ErrorPane.setText("Only numbers allowed at Age Field");
				}
				catch(javax.persistence.RollbackException q) {
					ErrorPane.setText("This Username is already in use");
				}
				catch(javax.persistence.PersistenceException w) {
					ErrorPane.setText("This Username is already in use");	
				}
			}
		});
		RegisterButton.setBounds(132, 255, 238, 30);
		contentPane.add(RegisterButton);
		
		JLabel lblNewLabel_5 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Register").toUpperCase());
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(191, 20, 127, 34);
		contentPane.add(lblNewLabel_5);
		
		InvitedByField = new JTextField();
		InvitedByField.setColumns(10);
		InvitedByField.setBounds(250, 219, 127, 20);
		contentPane.add(InvitedByField);
		
		JLabel InvitedByLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("InvitedBy"));
		InvitedByLabel.setBounds(120, 219, 105, 14);
		contentPane.add(InvitedByLabel);
		
	}
}

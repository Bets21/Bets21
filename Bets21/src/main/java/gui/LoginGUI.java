package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import businessLogic.*;
import domain.User;

import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
public class LoginGUI extends JFrame {
	private LoginGUI nireFrame;
	private JPanel contentPane;
	private JTextField UsernameField;
	
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
private static BLFacade appFacadeInterface;
	
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
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI( ) {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		nireFrame=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username")+":");
		lblNewLabel.setBounds(103, 74, 91, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password")+":");
		lblNewLabel_1.setBounds(103, 104, 74, 14);
		contentPane.add(lblNewLabel_1);
		
		UsernameField = new JTextField();
		UsernameField.setBounds(198, 72, 140, 20);
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		JLabel resultLabel = new JLabel("");
		resultLabel.setForeground(Color.RED);
		resultLabel.setBounds(106, 178, 213, 22);
		contentPane.add(resultLabel);
		
		
		JTextPane Textpane = new JTextPane();
		Textpane.setBackground(SystemColor.menu);
		Textpane.setBounds(118, 221, 220, 20);
		contentPane.add(Textpane);

		passwordField = new JPasswordField();
		passwordField.setBounds(198, 102, 140, 20);
		contentPane.add(passwordField);
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		//LOGIN botoia pultsatzean
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String Uname=UsernameField.getText();
					String Password=passwordField.getText();
					boolean Logged = appFacadeInterface.isLogin(Uname, Password);
					if (Logged==true) {
						if (appFacadeInterface.isUser(Uname) == true) {
							User user_Jframe = appFacadeInterface.getUserbyUserName(Uname);
							//System.out.println(Username1);
							MainUserGUI ma = new MainUserGUI(user_Jframe);
							ma.setBussinessLogic(appFacadeInterface);
							nireFrame.setVisible(false);
							ma.setVisible(true);
						}else if(appFacadeInterface.isEmployee(Uname) == true) {
							MainGUI ma =new MainGUI();
							ma.setBussinessLogic(appFacadeInterface);
							nireFrame.setVisible(false);
							ma.setVisible(true);
						}/*else if(appFacadeInterface.isAdmin(Username)) {
							MainGUI ma =new MainGUI();
							ma.setBussinessLogic(appFacadeInterface);
							nireFrame.setVisible(false);
							ma.setVisible(true);
						}*/
					}else {
						resultLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("AccessDenied"));
					}
				}
		});
		btnNewButton.setBounds(225, 145, 112, 23);
		contentPane.add(btnNewButton);
		//REGISTER botoia sakatzean
		JButton btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI r1 = new RegisterGUI();
				nireFrame.setVisible(false);
				r1.setVisible(true);
											}
		});
		btnNewButton_1.setBounds(103, 145, 112, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Login").toUpperCase());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(198, 21, 74, 33);
		contentPane.add(lblNewLabel_2);

		
	}
}

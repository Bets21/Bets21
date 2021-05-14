package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import domain.User;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;


public class MainUserGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private MainUserGUI nireFrame;
	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton LogoutButton;
	private JLabel lblUser;
	private JLabel lblUsername;
	private JLabel lblBalance_1;
	private JLabel lblBalance;
	private String username;
	private User user;
	private JButton btnAddBalance;
	private JButton btnCopyUser;
	private JButton btnInvitedFriends ;
	
	/**
	 * This is the default constructor
	 */
	public MainUserGUI(User user) {
		super();
		nireFrame=this;
		this.user = user;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public void updateUser() {
		User updatedUser = appFacadeInterface.getUserbyUserName(user.getUsername());
		lblBalance.setText(String.valueOf(updatedUser.getUnekoSaldoa())+" €");
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		
		this.setSize(495, 404);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getPanel());
			jContentPane.add(getLblUser());
			jContentPane.add(getLblUsername());
			jContentPane.add(getLblBalance_1());
			jContentPane.add(getLblBalance());
			
			btnAddBalance = new JButton("Add balance"); //$NON-NLS-1$ //$NON-NLS-2$
			btnAddBalance.setBounds(0, 122, 481, 63);
			btnAddBalance.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddBalanceGUI l1 = new AddBalanceGUI(user,nireFrame);
					l1.setBussinessLogic(appFacadeInterface);
					
					l1.setVisible(true);
				}
			});
			jContentPane.add(btnAddBalance);
			
			this.btnInvitedFriends = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SeeInvitedFriends"));
			btnInvitedFriends.setBounds(0, 182, 481, 63);
			jContentPane.add(btnInvitedFriends);
			btnInvitedFriends.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InvitedFriendsGUI l1 = new InvitedFriendsGUI(user);
					l1.setBussinessLogic(appFacadeInterface);
					l1.setVisible(true);
				}
			});
			jContentPane.add(btnAddBalance);
			
			this.btnCopyUser = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CopyBets")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCopyUser.setBounds(0, 243, 481, 63);
			jContentPane.add(btnCopyUser);
			btnCopyUser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					User updatedUser = appFacadeInterface.getUserbyUserName(user.getUsername());
					CopyUserGUI l1 = new CopyUserGUI(updatedUser,nireFrame);
					l1.setBussinessLogic(appFacadeInterface);
					l1.setVisible(true);
				}
			});
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(0, 62, 481, 63);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					FindQuestionsGUI a = new FindQuestionsGUI(user, nireFrame);
					a.setBussinessLogic(appFacadeInterface);
					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(151, 0, 173, 63);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.setBounds(220, 17, 81, 23);
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.setBounds(25, 17, 88, 23);
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.setBounds(112, 17, 106, 23);
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 307, 481, 58);
			panel.setLayout(null);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton());
			panel.add(getLogoutButton());
			panel.add(getRdbtnNewRadioButton_2());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		LogoutButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Logout"));
		btnAddBalance.setText(ResourceBundle.getBundle("Etiquetas").getString("AddBalance"));
		lblBalance_1.setText(ResourceBundle.getBundle("Etiquetas").getString("Balance")+": ");
		lblUser.setText(ResourceBundle.getBundle("Etiquetas").getString("User")+": ");
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		btnCopyUser.setText(ResourceBundle.getBundle("Etiquetas").getString("CopyBets"));
		btnInvitedFriends.setText(ResourceBundle.getBundle("Etiquetas").getString("SeeInvitedFriends"));
	}
	
	private JButton getLogoutButton() {
		if (LogoutButton == null) {
			LogoutButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Logout")); //$NON-NLS-1$ //$NON-NLS-2$
			LogoutButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainOutGUI l1 = new MainOutGUI();
					l1.setBussinessLogic(appFacadeInterface);
					
					l1.setVisible(true);
					nireFrame.setVisible(false);
				}
			});
			LogoutButton.setBounds(327, 14, 144, 29);
		}
		return LogoutButton;
	}
	private JLabel getLblUser() {
		if (lblUser == null) {
			lblUser = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("User")+": "); //$NON-NLS-1$ //$NON-NLS-2$
			lblUser.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblUser.setBounds(24, 17, 74, 28);
		}
		return lblUser;
	}
	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel(user.getUsername()); //$NON-NLS-1$ //$NON-NLS-2$
			lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblUsername.setBounds(108, 17, 69, 28);
		}
		return lblUsername;
	}
	private JLabel getLblBalance_1() {
		if (lblBalance_1 == null) {
			lblBalance_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Balance")+": "); //$NON-NLS-1$ //$NON-NLS-2$
			lblBalance_1.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblBalance_1.setBounds(334, 15, 58, 28);
		}
		return lblBalance_1;
	}
	private JLabel getLblBalance() {
		if (lblBalance == null) {
			lblBalance = new JLabel(String.valueOf(user.getUnekoSaldoa())+" €"); //$NON-NLS-1$ //$NON-NLS-2$
			lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblBalance.setBounds(402, 15, 69, 28);
		}
		return lblBalance;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

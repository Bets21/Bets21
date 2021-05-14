package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.Color;

public class AddBalanceGUI extends JFrame {

	private JPanel contentPane;
	private JTextField BalanceAmountField;
	private User user;
	private static BLFacade appFacadeInterface;
	private AddBalanceGUI nireFrame;
	private MainUserGUI UserGUI;
	private User updatedUser;
	private JTextField KK_NumberField;
	private JTextField KK_MonthField;
	private JTextField KK_YearField;
	private JTextField KK_CVVField;
	
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
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBalanceGUI frame = new AddBalanceGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}

	/**
	 * Create the frame.
	 */
	public AddBalanceGUI(User user, MainUserGUI pMainUserGUI) {
		nireFrame = this;
		this.UserGUI = pMainUserGUI;
		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.setBounds(72, 200, 79, 25);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nireFrame.setVisible(false);
			}
		});
		contentPane.add(btnClose);
		
		BalanceAmountField = new JTextField();
		BalanceAmountField.setBounds(148, 171, 123, 19);
		contentPane.add(BalanceAmountField);
		BalanceAmountField.setColumns(10);
		
		JLabel BalanceAmountLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Amount"));
		BalanceAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		BalanceAmountLabel.setBounds(72, 172, 66, 16);
		contentPane.add(BalanceAmountLabel);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AddBalance").toUpperCase());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(120, 20, 112, 28);
		contentPane.add(lblNewLabel);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 10));
		textPane.setForeground(new Color(255, 0, 0));
		textPane.setBackground(SystemColor.control);
		textPane.setBounds(72, 231, 199, 33);
		contentPane.add(textPane);
		
		JButton btnAdd = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddBalance"));
		btnAdd.setBounds(159, 200, 112, 25);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (KK_NumberField.getText().length()!=0) {
						int number = Integer.parseInt(KK_NumberField.getText());
						if (KK_MonthField.getText().length()!=0) {
							int month = Integer.parseInt(KK_MonthField.getText());
							if (KK_YearField.getText().length()!=0) {
								int year = Integer.parseInt(KK_YearField.getText());
								if (KK_CVVField.getText().length()!=0) {
									int cvv = Integer.parseInt(KK_CVVField.getText());
									if(BalanceAmountField.getText().length()!=0) {
										Double amount = Double.parseDouble(BalanceAmountField.getText());
										if (amount < 0) {
											textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("AmountNegative"));
										} else {
											if(user.getTransactions()==null) {
												if(user.getInvitedBy()!=null) {
												User invitedby=appFacadeInterface.getUserbyUserName(user.getInvitedBy());
											Double invitedamount=amount*0.25;
											appFacadeInterface.updateBalance(invitedby.getUsername(), invitedamount);
											appFacadeInterface.addInvitedTo(invitedby.getUsername(),user.getUsername(),Double.toString(amount));
											}
											}
											appFacadeInterface.updateBalance(user.getUsername(),amount);
											updatedUser = appFacadeInterface.getUserbyUserName(user.getUsername());
											UserGUI.updateUser();
											
											textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("BalanceAdded")+updatedUser.getUnekoSaldoa()+" €");
										}
									}else {
										textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("AmountMissing"));
									}
								}
							}else {
								textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("YearMissing"));
							}
						}else {
							textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("MonthMissing"));
						}
					}
					else {
						textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("CCMissing"));
					}
				}catch(NumberFormatException n) {
					textPane.setText(ResourceBundle.getBundle("Etiquetas").getString("NumbersAllowed"));
				}
				UserGUI.updateUser();
			}
			
		});
		contentPane.add(btnAdd);
		
		JLabel lblCurrentBalance = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CurrentBalance"));
		lblCurrentBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentBalance.setBounds(70, 148, 104, 13);
		contentPane.add(lblCurrentBalance);
		
		JLabel lblCurrentAmount = new JLabel(String.valueOf(user.getUnekoSaldoa())+" €");
		lblCurrentAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCurrentAmount.setBounds(184, 148, 74, 13);
		contentPane.add(lblCurrentAmount);
		
		KK_NumberField = new JTextField();
		KK_NumberField.setBounds(72, 74, 199, 19);
		contentPane.add(KK_NumberField);
		if (user.getKontuKorrontea()!=0) {
			KK_NumberField.setText(String.valueOf(user.getKontuKorrontea()));
		}
		KK_NumberField.setColumns(10);
		
		KK_MonthField = new JTextField();
		KK_MonthField.setBounds(72, 119, 46, 19);
		contentPane.add(KK_MonthField);
		if (user.getKK_DataHilabetea()!=0) {
			KK_MonthField.setText(String.valueOf(user.getKK_DataHilabetea()));
		}
		KK_MonthField.setColumns(10);
		
		KK_YearField = new JTextField();
		KK_YearField.setBounds(135, 119, 46, 19);
		contentPane.add(KK_YearField);
		if (user.getKK_DataUrtea()!=0) {
			KK_YearField.setText(String.valueOf(user.getKK_DataUrtea()));
		}
		KK_YearField.setColumns(10);
		
		KK_CVVField = new JTextField();
		KK_CVVField.setBounds(201, 119, 70, 19);
		contentPane.add(KK_CVVField);
		if (user.getKK_CVV()!=0) {
			KK_CVVField.setText(String.valueOf(user.getKK_CVV()));
		}
		KK_CVVField.setColumns(10);
		
		JLabel KK_MonthLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Month"));
		KK_MonthLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		KK_MonthLabel.setBounds(72, 102, 66, 13);
		contentPane.add(KK_MonthLabel);
		
		JLabel KK_YearLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Year"));
		KK_YearLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		KK_YearLabel.setBounds(135, 102, 45, 13);
		contentPane.add(KK_YearLabel);
		
		JLabel KK_CVVLabel = new JLabel("CVV:");
		KK_CVVLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		KK_CVVLabel.setBounds(201, 102, 45, 13);
		contentPane.add(KK_CVVLabel);
		
		JLabel KK_CreditNumberLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CCNumber"));
		KK_CreditNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		KK_CreditNumberLabel.setBounds(72, 58, 199, 13);
		contentPane.add(KK_CreditNumberLabel);
	}
}

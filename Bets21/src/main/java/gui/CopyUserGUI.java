package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Option;
import domain.User;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JTextField;

public class CopyUserGUI extends JFrame {

	private JPanel contentPane;
	private static BLFacade appFacadeInterface;
	private User user;
	private DefaultTableModel tableModelRequests;
	private DefaultTableModel tableModelYouCopy;
	private DefaultTableModel tableModelCopyYou;
	private CopyUserGUI nireFrame;
	private JScrollPane scrollPanelRequests = new JScrollPane();
	private JScrollPane scrollPanelYouCopy = new JScrollPane();
	private JScrollPane scrollPanelCopyYou = new JScrollPane();
	private JTable tableRequests;
	private JTable tableYouCopy;
	private JTable tableCopyYou;
	private JTextField textField;
	private MainUserGUI UserGUI;
	
	
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
					InvitedFriendsGUI frame = new InvitedFriendsGUI(user);
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
	public CopyUserGUI(User pUser, MainUserGUI pMainUserGUI) {
		appFacadeInterface= MainOutGUI.getBusinessLogic();
		UserGUI = pMainUserGUI;
		nireFrame = this;
		this.user = pUser;
		//this.user = appFacadeInterface.getUserbyUserName(user.getUsername());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCopyUsers = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CopyUserTitle"));
		
		lblCopyUsers.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCopyUsers.setBounds(239, 24, 157, 23);
		contentPane.add(lblCopyUsers);

		JTextPane textCopyUsers = new JTextPane();
		textCopyUsers.setEditable(false);
		textCopyUsers.setBackground(SystemColor.control);
		textCopyUsers.setFont(new Font("Tahoma", Font.ITALIC, 12));
		
		textCopyUsers.setText(ResourceBundle.getBundle("Etiquetas").getString("CopyUserInfo"));
				textCopyUsers.setBounds(25, 57, 545, 81);
		contentPane.add(textCopyUsers);
		
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.setEnabled(false);
		btnAccept.setBounds(25, 282, 122, 21);
		contentPane.add(btnAccept);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableRequests.getSelectedRow();
				String username2=(String) tableRequests.getValueAt(i,0);
				appFacadeInterface.acceptRequest(user.getUsername(), username2);
				tableModelRequests.removeRow(i);
				Object[] row = {username2};
				tableModelCopyYou.addRow(row);
			}
		});
		
		JButton btnReject = new JButton("Reject");
		btnReject.setEnabled(false);
		btnReject.setBounds(157, 282, 130, 21);
		contentPane.add(btnReject);
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableRequests.getSelectedRow();
				String username2=(String) tableRequests.getValueAt(i,0);
				appFacadeInterface.rejectRequest(user.getUsername(), username2);
				appFacadeInterface.UpdateUserbyUserName(user.getUsername(),user);
				UserGUI.updateUser();
				tableModelRequests.removeRow(i);
			}
		});
		
		String column[]={"Username"};  
		tableModelRequests = new DefaultTableModel(column, 0) {
			 @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		
		tableRequests = new JTable();
		tableRequests.setBounds(144, 191, 1, 1);
		scrollPanelRequests.setSize(262, 109);
		scrollPanelRequests.setLocation(25, 163);
		contentPane.add(scrollPanelRequests);
		tableRequests.setModel(tableModelRequests);
		scrollPanelRequests.setViewportView(tableRequests);
		
		//this.user = appFacadeInterface.getUserbyUserName(user.getUsername());
		Vector<String> RequestsArray = appFacadeInterface.getRequest(user.getUsername());
		if (RequestsArray != null) {
			for (int i = 0; i < RequestsArray.size(); i++){
				System.out.println(RequestsArray.get(i).toString());
				Object[] row = {RequestsArray.get(i)};
				tableModelRequests.addRow(row);
				System.out.println(RequestsArray.get(i));
			}
		}
		
		tableRequests.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				btnAccept.setEnabled(true);
				btnReject.setEnabled(true);
			}
		});
		
		JButton closeButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		closeButton.setBounds(252, 478, 85, 21);
		contentPane.add(closeButton);
		
		JLabel lblRequests = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Requests"));
		lblRequests.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRequests.setBounds(25, 140, 109, 13);
		contentPane.add(lblRequests);
		
		JLabel lblCopyAUser = new JLabel();
		lblCopyAUser.setText("Copy a User");
		lblCopyAUser.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCopyAUser.setBounds(318, 141, 109, 13);
		contentPane.add(lblCopyAUser);
		
		textField = new JTextField();
		textField.setBounds(318, 185, 252, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		lblUsername.setBounds(318, 165, 72, 13);
		contentPane.add(lblUsername);
		
		
		JLabel ErrorLabel = new JLabel("");
		ErrorLabel.setBounds(318, 246, 252, 57);
		contentPane.add(ErrorLabel);
		
		JButton btnCopyAUser = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CopyAnuser"));
		btnCopyAUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCopyAUser.setBounds(318, 214, 252, 21);
		contentPane.add(btnCopyAUser);
		btnCopyAUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Vector<String>copyyou=appFacadeInterface.getCopyYou(user.getUsername());
				String username2 = textField.getText();
				User u=appFacadeInterface.getUserbyUserName(username2);
				if(u!=null) {
				boolean Error=false;
				if(user.getUsername().compareTo(username2)!=0) {
					if(copyyou!=null) {
				for(int i=0;i<copyyou.size();i++) {
				if(copyyou.get(i).compareTo(username2)==0) {
					Error=true;
				}
				}
					}
				if(Error==false) {
					ErrorLabel.setText("Request send correctly");
				appFacadeInterface.sendRequest(user.getUsername(), username2);
				}else {
					ErrorLabel.setText("You can't copy someone who is copying you");
				}
				}else{
					ErrorLabel.setText("You can't copy yourself");
				}
			}else {
				ErrorLabel.setText("the user you are trying to copy doesn't exist");
			}
			}
		});
		
		String column2[]={"Username"};  
		tableModelCopyYou = new DefaultTableModel(column, 0) {
			 @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		
		tableCopyYou = new JTable();
		tableCopyYou.setBounds(125, 348, 262, 109);
		scrollPanelCopyYou.setSize(262, 109);
		scrollPanelCopyYou.setLocation(25, 348);
		contentPane.add(scrollPanelCopyYou);
		tableCopyYou.setModel(tableModelCopyYou);
		scrollPanelCopyYou.setViewportView(tableCopyYou);
		
		Vector<String> CopyYouArray =appFacadeInterface.getCopyYou(user.getUsername());
		if (CopyYouArray != null) {
			for (int i = 0; i < CopyYouArray.size(); i++){
				Object[] row = {CopyYouArray.get(i)};
				tableModelCopyYou.addRow(row);
			}
		}

		JLabel lblUsersCopyingYou = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UsersYouCopy"));
		lblUsersCopyingYou.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsersCopyingYou.setBounds(25, 325, 252, 13);
		contentPane.add(lblUsersCopyingYou);
		
		
		
		String column3[]={"Username"};  
		tableModelYouCopy = new DefaultTableModel(column, 0) {
			 @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		
		tableYouCopy = new JTable();
		tableYouCopy.setBounds(308, 348, 262, 109);
		scrollPanelYouCopy.setSize(262, 109);
		scrollPanelYouCopy.setLocation(308, 348);
		contentPane.add(scrollPanelYouCopy);
		tableYouCopy.setModel(tableModelYouCopy);
		scrollPanelYouCopy.setViewportView(tableYouCopy);
		
		Vector<String> YouCopyArray =appFacadeInterface.getYouCopy(user.getUsername());
		if (YouCopyArray != null) {
			for (int i = 0; i <YouCopyArray.size(); i++){
				Object[] row = {YouCopyArray.get(i)};
				tableModelYouCopy.addRow(row);
			}
		}
		
		JLabel lblRequests_1_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UsersCopyYou"));
		lblRequests_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRequests_1_1.setBounds(308, 325, 262, 13);
		contentPane.add(lblRequests_1_1);
		
		JButton btnStopCopying = new JButton(ResourceBundle.getBundle("Etiquetas").getString("StopCopying"));
        btnStopCopying.setBounds(382, 463, 122, 21);
        btnStopCopying.setEnabled(false);
        contentPane.add(btnStopCopying);
        btnStopCopying.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i=tableYouCopy.getSelectedRow();
                String username2=(String) tableYouCopy.getValueAt(i,0);
                appFacadeInterface.stopCopying(user.getUsername(), username2);
                User updatedUser = appFacadeInterface.getUserbyUserName(user.getUsername());
                UserGUI.updateUser();
                tableModelYouCopy.removeRow(i);
            }
        });
		btnStopCopying.setBounds(396, 468, 109, 23);
		contentPane.add(btnStopCopying);
	

		tableYouCopy.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                btnStopCopying.setEnabled(true);
            }
        });
		
		
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nireFrame.setVisible(false);
			}
		});
		/*Object[] row = {"data1", "data2", "data3" };
		tableModelInvited.addRow(row);*/

	}
}

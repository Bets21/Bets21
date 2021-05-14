package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.User;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JButton;

public class InvitedFriendsGUI extends JFrame {

	private JPanel contentPane;
	private static BLFacade appFacadeInterface;
	private User user;
	private DefaultTableModel tableModelInvited;
	private InvitedFriendsGUI nireFrame;
	private JScrollPane scrollPaneInvited = new JScrollPane();
	private JTable tableInvited;
	
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
	public InvitedFriendsGUI(User user) {
		nireFrame = this;
		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInvitedFriends = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("InviteFriendsTitle"));
		lblInvitedFriends.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInvitedFriends.setBounds(149, 24, 157, 23);
		contentPane.add(lblInvitedFriends);

		JTextPane txtpnIfYouInvite = new JTextPane();
		txtpnIfYouInvite.setEditable(false);
		txtpnIfYouInvite.setBackground(SystemColor.control);
		txtpnIfYouInvite.setFont(new Font("Tahoma", Font.ITALIC, 12));
		txtpnIfYouInvite.setText(ResourceBundle.getBundle("Etiquetas").getString("InviteFriendsInfo"));
		txtpnIfYouInvite.setBounds(25, 57, 390, 66);
		contentPane.add(txtpnIfYouInvite);
		
		String column[]={"User","Added balance","Money received (%25)"};  
		tableModelInvited = new DefaultTableModel(column, 0) {
			 @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		tableInvited = new JTable();
		tableInvited.setBounds(144, 191, 1, 1);
		scrollPaneInvited.setSize(390, 150);
		scrollPaneInvited.setLocation(25, 133);
		contentPane.add(scrollPaneInvited);
		tableInvited.setModel(tableModelInvited);
		scrollPaneInvited.setViewportView(tableInvited);
		
		appFacadeInterface=MainOutGUI.getBusinessLogic();
		Vector<String> invitedToArray = appFacadeInterface.getInvitedTo(user.getUsername());
		
		if(invitedToArray!=null) {
			for (int i = 0; i <invitedToArray.size()/3; i++){
				Object[] row = {invitedToArray.get((i*3)),invitedToArray.get((i*3)+1),invitedToArray.get((i*3)+2)};
				tableModelInvited.addRow(row);
			}
		}
		
		JButton closeButton = new JButton("Close");
		closeButton.setBounds(175, 293, 85, 21);
		contentPane.add(closeButton);
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nireFrame.setVisible(false);
			}
		});
		/*Object[] row = {"data1", "data2", "data3" };
		tableModelInvited.addRow(row);*/

	}
}

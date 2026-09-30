package pack1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainPage extends JFrame {

	private JPanel contentPane;
	public User activeUser;
	JLabel lblUser;
	
	public void setUser(User u) {
		lblUser.setText("Hello, "+u.getName());
		activeUser = u;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage() {
		setTitle("Main Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUser = new JLabel("");
		lblUser.setBounds(25, 28, 116, 14);
		contentPane.add(lblUser);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(308, 227, 116, 23);
		contentPane.add(btnExit);
		
		JButton btnAdmin = new JButton("Admin Page");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(activeUser.getUserRole().equals("admin")) {
					try {
						AdminPage ap;
						ap = new AdminPage();
						ap.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}else {
					JOptionPane.showMessageDialog(contentPane, "No admin rights!!");
				}
				
			}
		});
		btnAdmin.setBounds(308, 202, 116, 23);
		contentPane.add(btnAdmin);
	}
}

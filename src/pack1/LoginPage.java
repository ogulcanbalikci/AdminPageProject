package pack1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordF;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 269, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(10, 29, 75, 14);
		contentPane.add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(88, 26, 86, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(10, 64, 75, 14);
		contentPane.add(lblNewLabel_1);
		
		passwordF = new JPasswordField();
		passwordF.setBounds(88, 61, 86, 20);
		contentPane.add(passwordF);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = new String(passwordF.getPassword());
				
				DataContext ctx = new DataContext();
				try {
					
					
					if( ctx.getUsers().stream()
								  .filter(u -> u.getUsername().equals(username) &&
										  	   u.getPassword().equals(password))
								  .findFirst()
								  .isPresent()) {
						
						MainPage mp = new MainPage();
						mp.setVisible(true);
						
						mp.setUser(ctx.getUsers().stream()
								  				 .filter(u -> u.getUsername().equals(username) &&
								  						 	  u.getPassword().equals(password))
								  				 .findFirst()
								  				 .get());
						dispose();
						
					}else {
						JOptionPane.showMessageDialog(contentPane, "Login Failed!");
					}
				
				
				
				
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnLogin.setBounds(85, 127, 89, 23);
		contentPane.add(btnLogin);
	}
}

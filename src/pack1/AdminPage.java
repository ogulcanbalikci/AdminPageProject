package pack1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtNewUserId;
	private JTextField txtNewName;
	private JTextField txtNewSurname;
	private JTextField txtNewUsername;
	private JPanel panel;
	private JTable table;
	DataContext ctx = new DataContext();
	DefaultTableModel tableModel;
	private JPasswordField newPasswordField;
	private JTextField searchTxtId;
	
	
	public void fillTable() throws SQLException {
		ResultSet rs = ctx.getResultSet();
		
		tableModel.setColumnCount(0);
		tableModel.setRowCount(0);
		
		tableModel.setColumnIdentifiers(new Object[] { rs.getMetaData().getColumnName(1), 
													   rs.getMetaData().getColumnName(2),
													   rs.getMetaData().getColumnName(3),
													   rs.getMetaData().getColumnName(4),
													   rs.getMetaData().getColumnName(5),
													   rs.getMetaData().getColumnName(6),});
		while(rs.next()) {
			
			tableModel.addRow(new Object[] { rs.getInt(1),
											 rs.getString(2),
											 rs.getString(3),
											 rs.getString(4),
											 rs.getString(5),
											 rs.getString(6)  });
		}
		
		
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AdminPage() throws SQLException {
		setTitle("Admin Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Table");
		lblNewLabel.setBounds(10, 11, 74, 14);
		contentPane.add(lblNewLabel);
		
		
		
	
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				int[] rows = table.getSelectedRows();
				
				if (rows.length == 0) {
				    JOptionPane.showMessageDialog(null, 
				        "Lütfen silmek istediğiniz kullanıcıları tablodan seçin!", 
				        "Uyarı", 
				        JOptionPane.WARNING_MESSAGE);
				    return;
				}
				
				String message = (rows.length == 1) 
				    ? "Seçili kullanıcıyı silmek istediğinize emin misiniz?" 
				    : "Seçilen " + rows.length + " adet kullanıcıyı silmek istediğinize emin misiniz?";
				int answer = JOptionPane.showConfirmDialog(
				    null, 
				    message, 
				    "Silme Onayı", 
				    JOptionPane.YES_NO_OPTION, 
				    JOptionPane.WARNING_MESSAGE
				);
				
				if (answer == JOptionPane.YES_OPTION) {
				    for (int i : rows) {
				        try {
				            ctx.deleteUser(Integer.parseInt(table.getValueAt(i, 0).toString()));
				        } catch (NumberFormatException e1) {
				            e1.printStackTrace();
				        } catch (SQLException e1) {
				            e1.printStackTrace();
				        }
				    }
				    for (int i = rows.length - 1; i >= 0; i--) {
				        tableModel.removeRow(rows[i]);
				    }
				    JOptionPane.showMessageDialog(null, "Kullanıcılar başarıyla silindi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnDelete.setBounds(10, 209, 89, 23);
		contentPane.add(btnDelete);
		
		JComboBox cbUserRole = new JComboBox();
		cbUserRole.setModel(new DefaultComboBoxModel(new String[] {"admin", "normal"}));
		cbUserRole.setBounds(208, 209, 90, 22);
		contentPane.add(cbUserRole);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int [] rows = table.getSelectedRows();
				
				String newRole = cbUserRole.getSelectedItem().toString();
				
				for (int i : rows) {
					try {
						
						ctx.updateUser(Integer.parseInt( table.getValueAt(i, 0).toString() ), newRole);
						table.setValueAt(newRole, i, 5);
					
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			/*	try {
				
					fillTable();
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
				
			}
		});
		btnUpdate.setBounds(109, 209, 89, 23);
		contentPane.add(btnUpdate);
		
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(403, 26, 225, 237);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(true);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(10, 24, 46, 14);
		panel.add(lblNewLabel_1);
		
		txtNewUserId = new JTextField();
		txtNewUserId.setEditable(false);
		txtNewUserId.setText("");
		txtNewUserId.setBounds(90, 21, 46, 20);
		panel.add(txtNewUserId);
		txtNewUserId.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setBounds(10, 52, 46, 14);
		panel.add(lblNewLabel_1_1);
		
		txtNewName = new JTextField();
		txtNewName.setText("");
		txtNewName.setColumns(10);
		txtNewName.setBounds(90, 49, 86, 20);
		panel.add(txtNewName);
		
		JLabel lblNewLabel_1_2 = new JLabel("Surname");
		lblNewLabel_1_2.setBounds(10, 80, 70, 14);
		panel.add(lblNewLabel_1_2);
		
		txtNewSurname = new JTextField();
		txtNewSurname.setText("");
		txtNewSurname.setColumns(10);
		txtNewSurname.setBounds(90, 77, 86, 20);
		panel.add(txtNewSurname);
		
		JLabel lblNewLabel_1_3 = new JLabel("Username");
		lblNewLabel_1_3.setBounds(10, 108, 86, 14);
		panel.add(lblNewLabel_1_3);
		
		txtNewUsername = new JTextField();
		txtNewUsername.setText("");
		txtNewUsername.setColumns(10);
		txtNewUsername.setBounds(90, 105, 86, 20);
		panel.add(txtNewUsername);
		
		JLabel lblNewLabel_1_4 = new JLabel("Password");
		lblNewLabel_1_4.setBounds(10, 135, 86, 14);
		panel.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("User Role");
		lblNewLabel_1_4_1.setBounds(10, 160, 86, 14);
		panel.add(lblNewLabel_1_4_1);
		
		JComboBox cbNewUserRole = new JComboBox();
		cbNewUserRole.setModel(new DefaultComboBoxModel(new String[] {"admin", "normal"}));
		cbNewUserRole.setBounds(90, 160, 86, 22);
		panel.add(cbNewUserRole);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				User u = new User();
				int id = 0; 
				
				if (!txtNewUserId.getText().trim().isEmpty()) {
				    try {
				        id = Integer.parseInt(txtNewUserId.getText().trim());
				    } catch (NumberFormatException ex) {
				        JOptionPane.showMessageDialog(contentPane, "ID sadece rakamlardan oluşmalıdır!");
				        return;
				    }
				}
				u.setName(txtNewName.getText());
				u.setSurname(txtNewSurname.getText());
				u.setUsername(txtNewUsername.getText());
				String password = new String(newPasswordField.getPassword());
				u.setPassword(password);
				u.setUserRole(cbNewUserRole.getSelectedItem().toString());
				
				int newId = new DataContext().saveUser(u);
				u.setUserId(newId); 
				tableModel.addRow(new Object[] {
				    u.getUserId(), 
				    u.getName(), 
				    u.getSurname(), 
				    u.getUsername(), 
				    u.getPassword(), 
				    u.getUserRole()
				});
			}
		});
		btnSave.setBounds(100, 193, 89, 23);
		panel.add(btnSave);
		
		JButton fillTable = new JButton("Fill Table");
		fillTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fillTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		fillTable.setBounds(0, 194, 96, 20);
		panel.add(fillTable);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(90, 131, 86, 18);
		panel.add(newPasswordField);
		
		JCheckBox chckHide = new JCheckBox("");
		chckHide.setBounds(182, 129, 21, 20);
		panel.add(chckHide);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(146, 21, 79, 20);
		panel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNewUserId.setText("");
			    txtNewName.setText("");
			    txtNewSurname.setText("");
			    txtNewUsername.setText("");
			    newPasswordField.setText("");
			    
			    
			    cbNewUserRole.setSelectedIndex(0); 
			    
			    table.clearSelection();
			    
			    newPasswordField.setEchoChar('•');
			    if (chckHide != null) {
			        chckHide.setSelected(false);
			}
			}});
		
		chckHide.addActionListener(e -> {
		    if (chckHide.isSelected()) {
		        newPasswordField.setEchoChar((char) 0); 
		    } else {
		        newPasswordField.setEchoChar('•'); 
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 383, 162);
		contentPane.add(scrollPane);
		
		tableModel = new DefaultTableModel();
		
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		
		
		
		JLabel lblNewUser = new JLabel("New User");
		lblNewUser.setBounds(403, 11, 74, 14);
		contentPane.add(lblNewUser);
		
		JLabel searchId = new JLabel("search by id:");
		searchId.setBounds(10, 242, 74, 23);
		contentPane.add(searchId);
		
		searchTxtId = new JTextField();
		searchTxtId.setBounds(84, 244, 96, 18);
		contentPane.add(searchTxtId);
		searchTxtId.setColumns(10);
		
		JButton searchBtm = new JButton("Search");
		searchBtm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
			        table.setRowSorter(sorter);
			        
			        String searchText = searchTxtId.getText().trim();
			        
			        if (searchText.isEmpty()) {
			            sorter.setRowFilter(null);
			        } else {
			            sorter.setRowFilter(RowFilter.regexFilter("^" + searchText + "$", 0));
			        }
			}
		});
		searchBtm.setBounds(202, 241, 84, 20);
		contentPane.add(searchBtm);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent e) {
		        int selectedRow = table.getSelectedRow();
		        
		       
		        if (selectedRow != -1) {
		           
		            txtNewUserId.setText(table.getValueAt(selectedRow, 0).toString());
		            txtNewName.setText(table.getValueAt(selectedRow, 1).toString());
		            txtNewSurname.setText(table.getValueAt(selectedRow, 2).toString());
		            txtNewUsername.setText(table.getValueAt(selectedRow, 3).toString());
		            newPasswordField.setText(table.getValueAt(selectedRow, 4).toString());
		            
		         
		            cbNewUserRole.setSelectedItem(table.getValueAt(selectedRow, 5).toString());
		        }
		    }
		});
	}
}

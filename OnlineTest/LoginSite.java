package OnlineTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginSite extends JFrame {

	private JPanel contentPane;
	private JTextField user;
	private JTextField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSite frame = new LoginSite();
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
	public LoginSite() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 441);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Page");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setBounds(0, 60, 575, 48);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(34, 119, 138, 24);
		contentPane.add(lblNewLabel_1);
		
		user = new JTextField();
		user.setBounds(34, 154, 510, 24);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(34, 199, 165, 24);
		contentPane.add(lblNewLabel_2);
		
		pass = new JTextField();
		pass.setBounds(34, 234, 510, 24);
		contentPane.add(pass);
		pass.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinetest","root","");

					String n = user.getText();
					String p = pass.getText();
					
					if(n.isEmpty() || p.isEmpty()) {
						JOptionPane.showMessageDialog(null,"Username and Password can't be empty");
					}
					else {
						String s = "select * from register where name='" +n+"' and password='"+p+"'";
						
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(s);
						
	
						
						if(rs.next()){
							JOptionPane.showMessageDialog(null, "Are you ready for the test?");
							contentPane.removeAll();
							Q1 q = new Q1();
							q.setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Your account is not registered. Please register yourself first");
							contentPane.removeAll();
							RegisterForExam rgr = new RegisterForExam();
							rgr.setVisible(true);
						}
					}
				}
				catch(Exception x) {
					x.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(34, 289, 91, 33);
		contentPane.add(btnNewButton);
	}
}

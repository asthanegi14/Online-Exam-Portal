package OnlineTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RegisterForExam extends JFrame {

	private JPanel RegisterFrame;
	private JTextField name;
	private JTextField rollno;
	private JTextField mail;
	private JTextField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForExam frame = new RegisterForExam();
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
	public RegisterForExam() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 446);
		RegisterFrame = new JPanel();
		RegisterFrame.setBackground(new Color(173, 216, 230));
		RegisterFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(RegisterFrame);
		RegisterFrame.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register Here");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel.setBounds(0, 32, 589, 41);
		RegisterFrame.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Full Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(39, 105, 124, 20);
		RegisterFrame.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(173, 105, 314, 20);
		RegisterFrame.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Uni. Roll No.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(39, 148, 124, 20);
		RegisterFrame.add(lblNewLabel_2);
		
		rollno = new JTextField();
		rollno.setBounds(173, 148, 314, 20);
		RegisterFrame.add(rollno);
		rollno.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(40, 192, 123, 20);
		RegisterFrame.add(lblNewLabel_3);
		
		mail = new JTextField();
		mail.setBounds(173, 192, 314, 20);
		RegisterFrame.add(mail);
		mail.setColumns(10);
		
		JLabel variablex = new JLabel("Password");
		variablex.setHorizontalAlignment(SwingConstants.RIGHT);
		variablex.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		variablex.setBounds(39, 231, 123, 20);
		RegisterFrame.add(variablex);
		
		pass = new JTextField();
		pass.setBounds(173, 231, 314, 20);
		RegisterFrame.add(pass);
		pass.setColumns(10);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinetest","root","");
					
					String s = "insert into register(name , roll_no, email, password) Values(?,?,?,?)";
					
					PreparedStatement ps = conn.prepareStatement(s);
					
					String n = name.getText();
					String r = rollno.getText();
					String m = mail.getText();
					String p = pass.getText();
					
					ps.setString(1, n);
					ps.setString(2, r);
					ps.setString(3, m);
					ps.setString(4, p);
					
					ps.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Registered Successfully");
					RegisterFrame.removeAll();
					LoginSite lg = new LoginSite();
					lg.setVisible(true);
					conn.close();
				}
				catch(Exception x) {
					x.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(239, 287, 134, 33);
		RegisterFrame.add(btnNewButton);
	}

}

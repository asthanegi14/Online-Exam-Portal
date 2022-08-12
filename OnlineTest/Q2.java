package OnlineTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class Q2 extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Q2 frame = new Q2();
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
	public Q2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 454);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(188, 143, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Q2. A _____________  in a table represents a relationship among a set of");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(59, 33, 532, 31);
		contentPane.add(lblNewLabel);
		

		JLabel lblNewLabel_1 = new JLabel("values.");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(59, 69, 169, 23);
		contentPane.add(lblNewLabel_1);
		
		JRadioButton A = new JRadioButton("Column");
		buttonGroup.add(A);
		A.setBackground(new Color(188, 143, 143));
		A.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		A.setBounds(59, 116, 109, 23);
		contentPane.add(A);
		
		JRadioButton B = new JRadioButton("Key");
		buttonGroup.add(B);
		B.setBackground(new Color(188, 143, 143));
		B.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		B.setBounds(59, 166, 109, 23);
		contentPane.add(B);
		
		JRadioButton C = new JRadioButton("Row");
		buttonGroup.add(C);
		C.setBackground(new Color(188, 143, 143));
		C.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		C.setBounds(59, 213, 109, 23);
		contentPane.add(C);
		
		JRadioButton D = new JRadioButton("Entry");
		buttonGroup.add(D);
		D.setBackground(new Color(188, 143, 143));
		D.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		D.setBounds(59, 262, 109, 23);
		contentPane.add(D);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//if an option is selected then remove previous page.
				if(A.isSelected() || B.isSelected() || C.isSelected() || D.isSelected()) {
					contentPane.removeAll();
					Q3 q3 = new Q3();
					q3.setVisible(true);
					dispose();
				}
				
				//if none option is selected then print a msg
				else {
					JOptionPane.showMessageDialog(null, "Please select at least one option.");
				}

				//now trying to store your score in database
				
				try {

					//set driver
					Class.forName("com.mysql.cj.jdbc.Driver");

					//set connection
					Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinetest","root","");
					int ans=0;int i=1;

					//create a statement
					Statement stmt = conn.createStatement();
					
					//rs returns your previous score
					ResultSet rs = stmt.executeQuery("select * from answersbysdnt where id = "+i);
					
					while(rs.next()) {
						ans = rs.getInt("correct");
					}
					
					//if your answer is correct then update the value of your score in database by one
					if(C.isSelected()) {
						
						String s = "Update answersbysdnt set correct=? where id=?";
						PreparedStatement ps = conn.prepareStatement(s);
						
						ans+=1;
						ps.setInt(1,ans);
						ps.setInt(2, 1);
						
						ps.executeUpdate();
						
					}
					conn.close();
					
					//pop up a msg that answer is saved
					JOptionPane.showMessageDialog(null, "Answer saved");
				}
				catch(Exception x) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBounds(480, 340, 89, 23);
		contentPane.add(btnNewButton);
		
	}
}

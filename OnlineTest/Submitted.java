package OnlineTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class Submitted extends JFrame {

	private JPanel contentPane;
	private JTextField questions;
	private JTextField attem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Submitted frame = new Submitted();
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
	public Submitted() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 447);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(188, 143, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Test Submitted Successfully");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(28, 43, 590, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Total Questions");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(54, 126, 123, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Attempted");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(354, 126, 123, 27);
		contentPane.add(lblNewLabel_2);
		
		questions = new JTextField("10");
		questions.setBounds(211, 128, 86, 27);
		contentPane.add(questions);
		questions.setColumns(10);
		attem = new JTextField("10");
		attem.setBounds(497, 128, 86, 27);
		contentPane.add(attem);
		attem.setColumns(10);
		
		attem.setEditable(false);
		questions.setEditable(false);

		JButton btnNewButton = new JButton("View Score");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinetest","root","");
					
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from answersbysdnt where id = 1");
					
					while(rs.next()) {
						int ans = rs.getInt("correct");
						JOptionPane.showMessageDialog(null, "Your total score is = "+ans);
						contentPane.removeAll();
					}
					String s = "Update answersbysdnt set correct=? where id=?";
					PreparedStatement ps = conn.prepareStatement(s);

					ps.setInt(1,0);
					ps.setInt(2,1);
					
					ps.executeUpdate();
				}
				catch(Exception x) {
					x.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton.setBounds(259, 238, 134, 33);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Thanks For Participating");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(231, 340, 187, 27);
		contentPane.add(lblNewLabel_3);
	}

}

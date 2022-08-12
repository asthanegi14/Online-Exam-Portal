package OnlineTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class Q9 extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Q9 frame = new Q9();
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
	public Q9() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 454);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(188, 143, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Q9. Using which language can a user request information form a databse?");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel.setBounds(59, 66, 532, 31);
		contentPane.add(lblNewLabel);
		
		JRadioButton A = new JRadioButton("Query");
		buttonGroup.add(A);
		A.setBackground(new Color(188, 143, 143));
		A.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		A.setBounds(59, 116, 109, 23);
		contentPane.add(A);
		
		JRadioButton B = new JRadioButton("Relational");
		buttonGroup.add(B);
		B.setBackground(new Color(188, 143, 143));
		B.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		B.setBounds(59, 166, 109, 23);
		contentPane.add(B);
		
		JRadioButton C = new JRadioButton("Structural");
		buttonGroup.add(C);
		C.setBackground(new Color(188, 143, 143));
		C.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		C.setBounds(59, 213, 109, 23);
		contentPane.add(C);
		
		JRadioButton D = new JRadioButton("Compiler");
		buttonGroup.add(D);
		D.setBackground(new Color(188, 143, 143));
		D.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		D.setBounds(59, 262, 109, 23);
		contentPane.add(D);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(A.isSelected() || B.isSelected() || C.isSelected() || D.isSelected()) {
					contentPane.removeAll();
					Q10 q = new Q10();
					q.setVisible(true);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Please select at least one option.");
				}
				try 
				{	
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinetest","root","");
					int ans=0;int i=1;

					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from answersbysdnt where id = "+i);

					while(rs.next()) {
						ans = rs.getInt("correct");

					}
					
					if(A.isSelected()) {
						String s = "Update answersbysdnt set correct=? where id=?";
						PreparedStatement ps = conn.prepareStatement(s);

						ans+=1;
						ps.setInt(1,ans);
						ps.setInt(2, 1);
						
						ps.executeUpdate();
						
					}
					conn.close();
					JOptionPane.showMessageDialog(null, "Answer saved");
				}
				catch(Exception x) {
					JOptionPane.showMessageDialog(null, "Error with database connection");
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBounds(480, 340, 89, 23);
		contentPane.add(btnNewButton);
	}
}

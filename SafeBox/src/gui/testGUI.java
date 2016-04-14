package gui;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

import javafx.scene.image.Image;

import java.awt.Color;


public class testGUI {
	private static final String TITLE = "SafeBox";
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	
	static JPanel cards;
	static CardLayout cl;
	
	
	
	
	
	public static void main(String[] args){		
		JFrame window = new JFrame(TITLE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setSize(WIDTH, HEIGHT);
		window.setVisible(true);

		//Initialize seperate Panels
		final JPanel passCard = new JPanel();
		passCard.setForeground(new Color(255, 255, 255));
		passCard.setBackground(new Color(0, 204, 255));
		JPanel mainCard = new JPanel();
		
		//Initialize cards
		
		cards = new JPanel(new CardLayout());
		cl = (CardLayout) cards.getLayout();
		//------------------------------------------------
		
		cards.add(mainCard, "main");
		mainCard.setLayout(null);
		
		JLabel lblMaincard = new JLabel("MainCard");
		lblMaincard.setBounds(325, 9, 96, 14);
		mainCard.add(lblMaincard);
				
		JButton btnNewButton_1 = new JButton("Go to pass");
		btnNewButton_1.setBounds(338, 106, 83, 23);
		mainCard.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(cards, "password");
			}
		});
		
		//---------------------------------------------------
		
		cards.add(passCard, "password");
		passCard.setLayout(null);
		
		JLabel lblPasscard = new JLabel("SafeBox");
		lblPasscard.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasscard.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblPasscard.setBounds(321, 75, 100, 50);
		passCard.add(lblPasscard);
		
		JLabel lblSafeboxLogo = new JLabel("// SAFEBOX LOGO GOES HERE");
		lblSafeboxLogo.setBounds(321, 170, 162, 14);
		passCard.add(lblSafeboxLogo);
		
		CustomButton enterSBButton = new CustomButton("EnterSafeBoxButtonImg1.png",
											(window.getWidth() / 2) - (350 / 2), 
											(window.getHeight() / 2) - (60 / 2), 
											350,
											200,
											false);
		
		enterSBButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(cards, "main");
			}
		});
		
		passCard.add(enterSBButton);
		
		
		JButton btnNewButton_2 = new JButton("Forgot Password");
		btnNewButton_2.setBounds(321, 326, 136, 50);
		passCard.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(passCard, "This is your hint", null, JOptionPane.PLAIN_MESSAGE);
			}
		});

		
		//-------------------------------------------------
		window.getContentPane().add(cards);
		cl.show(cards, "password");
		
		
	}
}

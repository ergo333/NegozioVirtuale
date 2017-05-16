package view;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.*;

import controller.NewFrameListener;
import model.Magazzino;

/*
 * Prima schermata del programma
 * L'utente sceglie se fare il login
 * o se registrarsi
 */

public class MainFrame extends JFrame {	
	// Attributi loginPanel
	private JLabel username;
	private JLabel password;
	private JTextField usrField;
	private JPasswordField pwdField;
	
	private Magazzino magazzino;
	
	// Attributi buttonsPanel
	private JButton loginButton;
	private JButton signUpButton;
	
	private NewFrameListener listener;
	
	public MainFrame(String titolo, Magazzino magazzino) {
		super(titolo);
		
		this.username = new JLabel("Username:");
		this.password = new JLabel("Password:");
		this.usrField = new JTextField();
		this.pwdField = new JPasswordField();
		
		this.magazzino = magazzino;
		
		this.loginButton = new JButton("Login");
		this.signUpButton = new JButton("Sign Up");
		
		GridLayout mainLayout = new GridLayout(2, 1);
		JPanel mainPanel = new JPanel(mainLayout);
		
		GridLayout loginLayout = new GridLayout(2, 2);
		JPanel loginPanel = new JPanel(loginLayout);
		
		GridLayout buttonsLayout = new GridLayout(2, 1);
		JPanel buttonsPanel = new JPanel(buttonsLayout);
		buttonsPanel.setBorder(new EmptyBorder(5, 50, 0, 50));
		
		mainPanel.setBounds(50, 50, 50, 50);
		mainPanel.add(loginPanel);
		mainPanel.add(buttonsPanel);
		this.setContentPane(mainPanel);
		
		loginPanel.add(username);
		loginPanel.add(usrField);
		loginPanel.add(password);
		loginPanel.add(pwdField);
		
		buttonsPanel.add(loginButton);
		buttonsPanel.add(signUpButton);
		
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(220, 100);
		this.setVisible(true);
		
		listener = new NewFrameListener(this, magazzino);
		signUpButton.addActionListener(listener);
	}
	
	public static void main(String[] args) {
		Magazzino magazzino =  new Magazzino();
		MainFrame finestra = new MainFrame("Negozio Online", magazzino);
	}
}
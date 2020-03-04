import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class CreateAccountPanel extends JPanel
{
	
	private JPanel namePanel;
	private JPanel statusPanel;
	private JPanel usernamePanel;
	private JPanel passwordPanel;
	private JPanel buttonPanel;
	private JLabel namelbl;
	private JLabel statuslbl;
	private JLabel usernamelbl;
	private JLabel passwordlbl;
	private JLabel tittlelbl;
	private JTextField nameTxtField;
	private JTextField statusTxtField;
	private JTextField passwordTxtField;
	private JTextField usernameTxtField;
	private JButton submitbtn;
	private JPanel mainPanel;
	private SocialNetwork network;
	private CardLayout layout;
	private JLabel label;
	
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String name = nameTxtField.getText();
			String status = statusTxtField.getText();
			String username = usernameTxtField.getText();
			String password = passwordTxtField.getText();
			
			//Clearing textFileds
			nameTxtField.setText("");
			statusTxtField.setText("");
			usernameTxtField.setText("");
			passwordTxtField.setText("");
			
			
			
			network.addUserToNetwork(new UserVertexRepresentation(username, password, name, status));
			
			layout.show(mainPanel, "1");
			
		}
		
	}
	
	public CreateAccountPanel(JPanel inputMainPanel, CardLayout inputLayout, SocialNetwork inputNetwork)
	{
		network = inputNetwork;
		mainPanel = inputMainPanel;
		layout = inputLayout;
		
		tittlelbl = new JLabel("Create Account", JLabel.CENTER);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		
		this.add(tittlelbl);
		
		//Constructing userNamePanel
		usernamePanel = new JPanel();
		add(usernamePanel);
		usernamelbl = new JLabel("Username:");
		usernameTxtField = new JTextField(10);
		usernamePanel.add(usernamelbl);
		usernamePanel.add(usernameTxtField);
		
		//constructing PasswordPanel
		passwordPanel = new JPanel();
		add(passwordPanel);
		passwordlbl = new JLabel("Password:");
		passwordTxtField = new JTextField(10);
		passwordPanel.add(passwordlbl);
		passwordPanel.add(passwordTxtField);
		
		//Constructing namePanel
		namePanel = new JPanel();
		add(namePanel);
		namelbl = new JLabel("Name:       ");
		nameTxtField = new JTextField(10);
		namePanel.add(namelbl);
		namePanel.add(nameTxtField);
		
		//Constructing buttonPanel
		buttonPanel = new JPanel();
		submitbtn = new JButton("Submit");
		submitbtn.addActionListener(new ButtonListener());
		
		//Constructing statusPanel
		statusPanel = new JPanel();
		statuslbl = new JLabel("Status:     ");
		statusTxtField = new JTextField(10);
		statusPanel.add(statuslbl);
		statusPanel.add(statusTxtField);
		this.add(statusPanel);
		buttonPanel.add(submitbtn);
		this.add(buttonPanel);
		
		label = new JLabel("");
		add(label);
		this.setPreferredSize(new Dimension(450, 400));
		
		
		
		
		
	}



}

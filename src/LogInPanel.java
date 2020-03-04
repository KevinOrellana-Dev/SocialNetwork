import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class LogInPanel extends JPanel
{
	private JTextField usernameTxtField;
	private JPasswordField passwordTxtField;
	private JPanel centerPanel;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JPanel usernameSubPanel;
	private JPanel passwordSubPanel;
	private JPanel mainPanel;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblTittle;
	private JLabel logInFeedback;
	private JButton logInBtn;
	private JButton createAccountBtn;
	private SocialNetwork network;
	private CardLayout layout;
	
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == logInBtn)//if log in button was pressed
			{
				
				//Gathering information from the user
				String username =  usernameTxtField.getText();
				String password = new String(passwordTxtField.getPassword());
				
				//clearing text fields
				usernameTxtField.setText("");
				passwordTxtField.setText("");
				logInFeedback.setText("");
				
				
				int result = network.logIn(username, password);
				
				switch(result)
				{
				case 1: logInFeedback.setText("Username or Password is incorrect!");
						break;
				case 0 :logInFeedback.setText("Success!"); 
				 		mainPanel.add(new MainProfilePanel(mainPanel, layout, network), "3");
						layout.show(mainPanel, "3");
						break;
				case -1: logInFeedback.setText("Username does not exists in network!"); 
						break;
				}

				
			}
			if(e.getSource() == createAccountBtn)
			{
				layout.show(mainPanel, "2");
			}
			
		}
		
	}
	
	public LogInPanel(JPanel inputMainPanel, CardLayout inputLayout, SocialNetwork inputNetwork) {
		

		network = inputNetwork;
		mainPanel = inputMainPanel;
		layout = inputLayout;
		
		
		this.setLayout(new BorderLayout());//Setting layout of parent panel
		
		
		//Initializing Panels
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0,2,5, 20));
		usernameSubPanel = new JPanel();
		passwordSubPanel = new JPanel();
		
		
		//Adding elements to the topPannel
		lblTittle = new JLabel("Spartan Network", JLabel.CENTER);
		topPanel.add(lblTittle);
		
		//Adding elements to the usernameSubPanel
		lblUsername = new JLabel("Username");
		usernameSubPanel.add(lblUsername);
		usernameTxtField = new JTextField();
		usernameSubPanel.add(usernameTxtField);
		usernameTxtField.setColumns(10);
		
		
		//Adding elements to the passwordSubPanel
		lblPassword = new JLabel("Password");
		passwordSubPanel.add(lblPassword);
		passwordTxtField = new JPasswordField();
		passwordTxtField.setEchoChar('*');
		passwordSubPanel.add(passwordTxtField);
		passwordTxtField.setColumns(10);
		
		//Adding elements to the centerPanel
		centerPanel.add(usernameSubPanel, BorderLayout.NORTH);
		centerPanel.add(passwordSubPanel, BorderLayout.CENTER);
		logInFeedback = new JLabel("", JLabel.CENTER);
		centerPanel.add(logInFeedback, BorderLayout.SOUTH);
		
		//Adding elements to the bottomPanel
		createAccountBtn = new JButton("Create Account");
		logInBtn = new JButton("Log In");
	
		ButtonListener listener = new ButtonListener();//creating a listener for the buttons
		logInBtn.addActionListener(listener);
		createAccountBtn.addActionListener(listener);
		
		bottomPanel.add(createAccountBtn);
		bottomPanel.add(logInBtn);
		

   

		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);



		this.setPreferredSize(new Dimension(400,400));
		
		
	}
	

	
	

}

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;

public class MainProfilePanel extends JPanel
{
	private JTextField nameTextField;
	private JTextField statusTextField;
	private JLabel lblTittle;
	private JPanel CenterPanel;
	private JPanel saveProfilePanel;
	private JButton saveProfileButton;
	private JPanel namePanel;
	private JLabel lblName;
	private JPanel statusPanel;
	private JLabel lblStatus;
	private JPanel bottomPanel;
	private JButton submitActionButton;
	private SocialNetwork network;
	private CardLayout layout;
	private JPanel mainPanel;
	private JComboBox comboBox;
	private JLabel userFeedBackLbl;
	
	/**
	 * This class purpose is to handle the actions that need to be carried when the user presses a button.
	 */
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == saveProfileButton)//If "saveProfileButton is pressed
			{
				String name = nameTextField.getText();
				String status = statusTextField.getText();
				
				network.currentUserprofileChanges(name,  status);
				
				lblTittle.setText(network.getCurrentUser().getName() + " Profile");
				userFeedBackLbl.setText("Profile Saved!");
				
				
			}
			if(e.getSource() == submitActionButton)//If "submitActionButton" is pressed
			{
				String selectedString = (String)comboBox.getSelectedItem();
				
				//Code that determines what tab was selected.
				if(selectedString.equals("Log out"))
				{
					network.logOut();
					layout.show(mainPanel, "1");
				}
				if(selectedString.equals("See friend List / See friends of friend"))
				{
					mainPanel.add(new ListOfFriendsPanel(mainPanel, layout, network), "4");
					layout.show(mainPanel, "4");
				}
				if(selectedString.equals("See All Users / Add Friend"))
				{
					mainPanel.add(new AllUsersPanel(mainPanel, layout, network), "5");
					layout.show(mainPanel, "5");
				}
				if(selectedString.equals("Leave Network"))
				{
					network.removeFromNetwork();
					layout.show(mainPanel, "1");
				}
			}
		}
		
	}
	
	//constructor
	public MainProfilePanel(JPanel inputMainPanel, CardLayout inputLayout, SocialNetwork inputNetwork)
	
	{

		
		//initializing all the resources needed to work with the other classes cohesively
		network = inputNetwork;
		mainPanel = inputMainPanel;
		layout = inputLayout;
		ButtonListener listener = new ButtonListener();
		setLayout(new BorderLayout(0, 0));
		
		lblTittle = new JLabel(network.getCurrentUser().getName() + " Profile");
		lblTittle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTittle, BorderLayout.NORTH);
		
		//Constructing the Center Panel
		CenterPanel = new JPanel();
		add(CenterPanel, BorderLayout.CENTER);
		CenterPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		namePanel = new JPanel();
		CenterPanel.add(namePanel);
		
		lblName = new JLabel("Name: ");
		namePanel.add(lblName);
		
		nameTextField = new JTextField();
		nameTextField.setText(network.getCurrentUser().getName());
		namePanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		statusPanel = new JPanel();
		CenterPanel.add(statusPanel);
		
		lblStatus = new JLabel("Status: ");
		statusPanel.add(lblStatus);
		
		statusTextField = new JTextField();
		statusTextField.setText(network.getCurrentUser().getStatus());
		statusPanel.add(statusTextField);
		statusTextField.setColumns(10);
		
		userFeedBackLbl = new JLabel("");
		CenterPanel.add(userFeedBackLbl);
		userFeedBackLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		saveProfilePanel = new JPanel();
		CenterPanel.add(saveProfilePanel);
		CenterPanel.setBorder(new TitledBorder(null, "Change Profile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		saveProfileButton = new JButton("Save Profile Changes");
		saveProfileButton.addActionListener(listener);
		saveProfilePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		saveProfilePanel.add(saveProfileButton);
		
		
		
		//Constructing the buttom panel
		bottomPanel = new JPanel();
		add(bottomPanel, BorderLayout.SOUTH);
		
		comboBox = new JComboBox();
		comboBox.addItem("See All Users / Add Friend");
		comboBox.addItem("See friend List / See friends of friend");
		comboBox.addItem("Log out");
		comboBox.addItem("Leave Network");
		bottomPanel.add(comboBox);
		bottomPanel.setBorder(new TitledBorder(null, "Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		submitActionButton = new JButton("Submit Action");
		submitActionButton.addActionListener(listener);//adding an action listener
		bottomPanel.add(submitActionButton);
		this.setPreferredSize(new Dimension(450, 400));
		
		
	}

}

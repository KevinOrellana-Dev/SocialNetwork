import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;


public class AllUsersPanel extends JPanel 
{
	private JPanel centerPanel;
	private JPanel buttonPanel;
	private JPanel addFriendPanel;
	private JPanel topPanel;
	private JLabel searchUserLbl;
	private JTextArea resultsTextArea;
	private JPanel mainPanel;
	private SocialNetwork network;
	private CardLayout layout;
	private JTextField userSearchTextField;
	private JTextField addFriendTextField;
	private JButton searchBtn;
	private JButton backBtn;
	private JButton addFriendBtn;
	private JLabel userInputFeedBackLbl;
	private JLabel addUserLbl;

	
	/**
	 * This class purpose is to handle the actions that need to be carried when the user presses a button.
	 */
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == backBtn)//if backButton is pressed
			{
				resultsTextArea.setText("");
				userInputFeedBackLbl.setText("");
				userSearchTextField.setText("");
				addFriendTextField.setText("");
				layout.show(mainPanel, "3");
			}
			if(e.getSource() == searchBtn)//if "searchBtn" is pressed
			{
				String searchResult = network.searchVertex(userSearchTextField.getText());
				
				if(searchResult == null)//if userName does not exist
				{
					userInputFeedBackLbl.setText("The username entered does not exist");
					
				}
				else
				{
					resultsTextArea.setText("Search Results:\n*) " + searchResult);
					userInputFeedBackLbl.setText("Sucess!");
				}
				
			}
			if(e.getSource() == addFriendBtn) // if "addFriendBtn is pressed
			{
				boolean addedToFriendList = network.addToFriendList(addFriendTextField.getText());
				
				if(addedToFriendList)//if the friend was added successfully
				{
					userInputFeedBackLbl.setText("Friend added!");
		
				}
				else
				{
					userInputFeedBackLbl.setText("Could not add to friend list. You are probalby friends already");
				}
			}
		
			
		}
	}
		
	//Constructor
	public AllUsersPanel(JPanel inputMainPanel, CardLayout inputLayout, SocialNetwork inputNetwork) 
	{
		//Initializing a action listener that is going to be used for the buttons.
		ButtonListener listener = new ButtonListener();
		
		//initializing all the resources needed to work with the other classes cohesively
		network = inputNetwork;
		mainPanel = inputMainPanel;
		layout = inputLayout;
		setLayout(new BorderLayout(0, 0));
		
		//Creating Center Panel
		centerPanel = new JPanel();
		centerPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Results", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(centerPanel);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		//Creating JTextArea
	
		resultsTextArea = new JTextArea();
		resultsTextArea.setRows(10);
		resultsTextArea.setColumns(30);
	 	resultsTextArea.setEditable(false);
	 	JScrollPane scrollPane = new JScrollPane(resultsTextArea);
		centerPanel.add(scrollPane);
		
		userInputFeedBackLbl = new JLabel("", JLabel.CENTER);
		centerPanel.add(userInputFeedBackLbl, BorderLayout.SOUTH);
		
		
		//Filling out "resultsTextArea"
		ArrayList<String> allUsers = network.getAllUsers();
		if(!allUsers.isEmpty() && allUsers != null)
		{
			resultsTextArea.setText("All users in network: \n");
			
			for(int i = 0 ; i < allUsers.size(); i++)
			{
				resultsTextArea.append("*) " + allUsers.get(i) + "\n");
			}
		}
		
		
		//Constructing buttom panel
		buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		backBtn = new JButton("Go back");
		backBtn.addActionListener( listener);//Adding an action listener to the "backBtn"
		buttonPanel.setLayout(new BorderLayout(0, 0));
		
		addFriendPanel = new JPanel();
		buttonPanel.add(addFriendPanel, BorderLayout.NORTH);
		
		addUserLbl = new JLabel("Enter the username of the person you want to add");
		addFriendPanel.add(addUserLbl);
		
		addFriendTextField = new JTextField();
		addFriendPanel.add(addFriendTextField);
		addFriendTextField.setColumns(5);
		
		addFriendBtn = new JButton("Add");
		addFriendBtn.addActionListener( listener);
		addFriendPanel.add(addFriendBtn);
		buttonPanel.add(backBtn, BorderLayout.SOUTH);
		
		topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		
		searchUserLbl = new JLabel("Enter name of user you want to search:");
		topPanel.add(searchUserLbl);
		
		userSearchTextField = new JTextField();
		userSearchTextField.setText("");
		topPanel.add(userSearchTextField);
		userSearchTextField.setColumns(10);
		
		searchBtn = new JButton("Search");
		searchBtn.addActionListener( listener);
		topPanel.add(searchBtn);
		
		
		//filling up frinedListTextArea
		ArrayList<String> allUsersList = network.getAllUsers();
	
		
		if(!allUsersList.isEmpty() && allUsersList != null)//if the listOfFriends is not empty or null
		{
			resultsTextArea.setText("All users in the network are:\n");
			for (int i = 0; i < allUsersList.size(); i++)//print out all friends of current user
			{
				resultsTextArea.append((i +1) + ") " + allUsersList.get(i) + "\n");
			}
		}
		else//If current user does not have any friends
			
		{
		
			resultsTextArea.setText("Sorry, " + "There are no users in the network");
		}
		this.setPreferredSize(new Dimension(450, 400));
	}
	
	
	
}

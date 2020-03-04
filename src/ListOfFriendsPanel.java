import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class ListOfFriendsPanel extends JPanel
{
	private JPanel topPanel;
	private JPanel centerPanel;
	private JLabel friendsOfFriendsLbl;
	private JPanel friendsOfFriendsSubPanel;
	private JTextArea friendListTextArea;
	private JTextArea friendsOfFriendsTextArea;
	private JPanel mainPanel;
	private SocialNetwork network;
	private CardLayout layout;
	private JTextField friendsOfFriendTextField;
	private JPanel buttonPanel;
	private JButton backBtn;
	private JButton friendsOfFriendsButton;
	
	
	/**
	 * This class purpose is to handle the actions that need to be carried when the user presses a button.
	 */
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == backBtn)//if back button is pressed
			{
				friendListTextArea.setText("");
				friendsOfFriendsTextArea.setText("");
				layout.show(mainPanel, "3");
			}
			
			if(e.getSource() == friendsOfFriendsButton)//if "friendsOfFriendsButton" is pressed
			{
				ArrayList<String> friendsOfFriendList = network.viewFriendsOfFriend(friendsOfFriendTextField.getText());
				
				
				if(friendsOfFriendList != null)
				{
					
					if(!friendsOfFriendList.isEmpty() )//if friend has friends
					{
					
						friendsOfFriendsTextArea.setText("Friends:\n");
						
						for(int i = 0 ; i < friendsOfFriendList.size(); i++)
						{
							friendsOfFriendsTextArea.append("*) " + friendsOfFriendList.get(i) + "\n");
						}
					}
					else
					{
						friendsOfFriendsTextArea.setText("Could not find any friends....\nOne Possible reason is that the user that you selected does not have any friends");
					}
				}
				else
				{
					friendsOfFriendsTextArea.setText("Could not find any friends....\nOne Possible reason is that the user that you selected does not have any friends");
				}
			}
			
		}
	}
		
	public ListOfFriendsPanel(JPanel inputMainPanel, CardLayout inputLayout, SocialNetwork inputNetwork) 
	{
		
		ButtonListener listener = new ButtonListener();
		
		//initializing all the resources needed to work with the other classes cohesively
		network = inputNetwork;
		mainPanel = inputMainPanel;
		layout = inputLayout;
		setLayout(new BorderLayout(0, 0));
		
		//Creating Center Panel
		topPanel = new JPanel();
		topPanel.setBorder(new TitledBorder(null, "Friend List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(topPanel, BorderLayout.NORTH);
		
		//Creating JTextArea
	
		friendListTextArea = new JTextArea();
		friendListTextArea.setRows(5);
		friendListTextArea.setColumns(40);
	 	friendListTextArea.setEditable(false);
	 	JScrollPane scrollPane = new JScrollPane(friendListTextArea);
		topPanel.add(scrollPane);
		
		//Constructing buttom panel
		buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.SOUTH);
		backBtn = new JButton("Go back");
		backBtn.addActionListener(listener);//Adding an action listener to the "backBtn"
		buttonPanel.add(backBtn);
		
		centerPanel = new JPanel();
		centerPanel.setBorder(new TitledBorder(null, "Friends of Friend", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		friendsOfFriendsSubPanel = new JPanel();
		centerPanel.add(friendsOfFriendsSubPanel, BorderLayout.NORTH);
		
		friendsOfFriendsLbl = new JLabel("Enter username of friend to see their friends");
		friendsOfFriendsSubPanel.add(friendsOfFriendsLbl);
		
		friendsOfFriendTextField = new JTextField();
		friendsOfFriendsSubPanel.add(friendsOfFriendTextField);
		friendsOfFriendTextField.setColumns(8);
		
		friendsOfFriendsButton = new JButton("Search");
		friendsOfFriendsButton.setHorizontalAlignment(SwingConstants.RIGHT);
		friendsOfFriendsButton.addActionListener(listener);
		friendsOfFriendsSubPanel.add(friendsOfFriendsButton);
		
		friendsOfFriendsTextArea = new JTextArea();
		friendsOfFriendsTextArea.setRows(4);
		JScrollPane scrollPaneTwo = new JScrollPane(friendsOfFriendsTextArea);
		centerPanel.add(scrollPaneTwo, BorderLayout.CENTER);
		friendsOfFriendsTextArea.setColumns(40);
		
		
		//filling up frinedListTextArea
		ArrayList<String> listOfFriends = network.getListOfFriends();
	
		
		if(!listOfFriends.isEmpty() && listOfFriends != null)//if the listOfFriends is not empty or null
		{
			for (int i = 0; i < listOfFriends.size(); i++)//print out all friends of current user
			{
				friendListTextArea.append("*) " + listOfFriends.get(i) + "\n");
			}
		}
		else//If current user does not have any friends
		{
		
			friendListTextArea.setText("Sorry, " + "you do not currently have any friends...");
		}
		this.setPreferredSize(new Dimension(450, 400));
	}
	
	

}

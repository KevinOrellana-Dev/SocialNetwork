import java.lang.reflect.Array;
import java.util.*;

public class SocialNetwork {
    
	private ArrayList<UserVertexRepresentation> vertices; 
    private Map<UserVertexRepresentation, List<UserVertexRepresentation>> graphMap; //HashMap representing a the network graph
    private UserVertexRepresentation currentUser;

    //constructors
    public SocialNetwork() {
        currentUser = null;
        vertices = new ArrayList<>();
        graphMap = new HashMap<UserVertexRepresentation, List<UserVertexRepresentation>>();

    }

    /**
     * This method adds a vertex to the graphMap. This method is called whenever a new user creates an account in the network
     * @param inputVertex - a UserRepresentationVertex representing a user in the network.
     */
    private void addVertex(UserVertexRepresentation inputVertex) {
        graphMap.put(inputVertex, new LinkedList<UserVertexRepresentation>());
    }

    /**
     * This method add an edge between inputVertexOne and inputVertexTwo
     * @param inputVertexOne - a UserRepresentationVertex representing a user in the network.
     * @param inputVertexTwo - a UserRepresentationVertex representing a user in the network.
     */
    private void addEdge(UserVertexRepresentation inputVertexOne, UserVertexRepresentation inputVertexTwo) {
        graphMap.get(inputVertexOne).add(inputVertexTwo); //added it to the adjacency list of the vertex in map
    }



    /**
     * This method checks if a specific UserVertexRepresentation object is on the graph
     * @param inputVertex - UserVertexRepresentation object
     * @return - True if the inputVeterx is in the graph. False, otherwise
     */
    private boolean isInAdjacencyList(UserVertexRepresentation inputVertex) {
        boolean contains = false;
        
        for (UserVertexRepresentation temp : graphMap.keySet()) //Cycling through all of the Keys in the graphMap.
        {
            if (temp.checkUserNameMatch(inputVertex.getUserName())) {
                contains = true;
            }
        }

        return contains;
    }


    /**
     * This method finds the address of a vertex that contains a specific userName.
     * @param inputUserName - A string representing a userName.
     * @return an UserVertexRepresentation object whose username field matches "inputUserName"
     */
    private UserVertexRepresentation findAddressOf(String inputUserName) {
        UserVertexRepresentation addressOfVertex = null;

        for (UserVertexRepresentation temp : graphMap.keySet())//iterating through all the keys in the map
        {
            if (temp.checkUserNameMatch(inputUserName))//if a vertex's userName matches "inputUserName"
            {
                addressOfVertex = temp;
            }
        }
        return addressOfVertex;
    }


    /**
     * This method fetches the list of friends of a particular vertex/user in the network
     *
     * @param inputVertex - a vertex that belongs to the network
     * @return -  a ListIterator that contains the list of friends of "inputVertex"
     */

    private ListIterator<UserVertexRepresentation> listOfFriends(UserVertexRepresentation inputVertex) {
        ListIterator<UserVertexRepresentation> iterator = null;

        if (inputVertex != null)  //If there is an active user
        {
            iterator = graphMap.get(inputVertex).listIterator();

            if (iterator.hasNext()) //If the active user has friends.
            {
                return iterator;
            }
        }

        return iterator;
    }

    /**
     * This method removes a user from the network. Making sure that the user to be removed is deleted from all the user's friendlist.
     * @param inputVertex - a UserVertexRepresentation object that needs to be removed from the network
     */

    private void remove(UserVertexRepresentation inputVertex) 
    {
        if (inputVertex != null) 
        {
            List<UserVertexRepresentation> friendList;
            ListIterator<UserVertexRepresentation> iteratorOfFriendList;
            UserVertexRepresentation temp;

            Collection<List<UserVertexRepresentation>> collection = graphMap.values();//a collection containing all of the user's in the network
            Iterator<List<UserVertexRepresentation>> collectionOfList = collection.iterator();//iterator "collection"

            
            //This nested loop removes the user that is to be removed from the network of all the other user's friendList.
            
            while (collectionOfList.hasNext())//while there is a user in the network 
            {
                friendList = collectionOfList.next();//copy all of the user's friends into "friedList"
                iteratorOfFriendList = friendList.listIterator();//iterator of friendList

                while (iteratorOfFriendList.hasNext()) //while the user has friends.
                {
                    temp = iteratorOfFriendList.next();//save friend to the "temp" variable

                    if (temp.checkUserNameMatch(inputVertex.getUserName())) //If the friend has a username matching the username of the user to be removed
                    {
                        iteratorOfFriendList.remove();
                    }
                }


            }

            //finally, remove the user from the graph
            graphMap.remove(currentUser);
            currentUser = null;

        }

    }





///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////USER INTERACTION METHODS//////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method handles the actions that need to be performed when a user tries to sign in.
     *
     * @param inputUserName the userName of the vertex that the user is trying to sign into
     * @param inputPassword the password of the vertex that the user is trying to sign into
     * @return -1 --- if there is no user in the network that has the same username as "inputUserName'
     * 0 ----- if the sign in process completed successfully. Meaning that the user entered the correct userName and password
     * 1 ----- If the userName is correct but the password is incorrect
     */

    public int logIn(String inputUserName, String inputPassword) {

        UserVertexRepresentation userVertex = findAddressOf(inputUserName);

        if (userVertex == null)//If userName was not found
        {
            return -1;
        } 
        else if (userVertex.checkPassword(inputPassword)) // If userName and password are correct
        {
            currentUser = userVertex;//setting the currentUser to the vertex that the user signed into.
            return 0;
        } 
        else//if userName is correct but password is incorrect
        {
            return 1;
        }
    }

    /**
     * This method logs out a user by setting the "currentUser" vertex to null
     */
    public void logOut() 
    {
        if (currentUser != null) 
        {
            currentUser = null;
        } 

    }

    
    /**
     * This method removes a user from the network
     */
    public void removeFromNetwork()
    {
        if (currentUser != null) 
        {
            remove(currentUser);
        }
    }

    /**
     *This method determines all of the friends of the current user.
     * @return - an arrayList with all the information about all the current user's friends
     */
    public ArrayList<String> getListOfFriends() 
    {
    	//Declaring variables that are needed throughout this method.
        ArrayList<String> nameOfFriends = null;
        ListIterator<UserVertexRepresentation> friendList;
        UserVertexRepresentation friend = null;

        if (currentUser != null) 
        {
            friendList = listOfFriends(currentUser);//Getting a list of all the friends of the currentUser
            nameOfFriends = new ArrayList<>();//initializing ArrayList to store all the information of the currentUser's friends.


            while(friendList.hasNext())//while the current user has friends
            {
                friend = friendList.next();
                nameOfFriends.add("Username: " +friend.getUserName() + "  ||  Name: " + friend.getName() + "    ||  Status: " + friend.getStatus()); //adding the information of the currentUser's friend to "nameOfFriends"
            }

        }

        return nameOfFriends;
    }

    
    /**
     * This method adds a user to the network. This method will only be used when a user creates a new account.
     * @param inputVertex - a UserVertexReperesentation object that holds all of the information pertaining the user
     */
    public void addUserToNetwork(UserVertexRepresentation inputVertex)
    {
        if(!isInAdjacencyList(inputVertex))//If "inputVertex" is not on the graph already
        {
            addVertex(inputVertex);
        }


    }

    /**
     * This method adds a friend to the currentUser friends list.
     * @param inputUserName -  a String containing the username of the friend to be added.
     */
    public boolean addToFriendList(String inputUserName) 
    {
        UserVertexRepresentation friend = findAddressOf(inputUserName);//finding the address of the UserRepresentationVertex whose userName field matches "inputUserName"
        boolean addedToList = false;//flag to determining if "inputUserName" was added to the friends list of the currentUser.

        if (currentUser != null) 
        {
            if (friend != null) //if "friend" was found.
            {
            	//If "Friend" is not already friends with current user and if friend is not the same as currentUser
                if (!graphMap.get(currentUser).contains(friend) && currentUser.getUserName().equals(friend.getUserName()) == false) 
                {
                    addEdge(currentUser, friend);
                    addEdge(friend, currentUser);
                    addedToList = true;
                }
            } 

        } 
        
        return addedToList;
    }

    
    /**
     * This method determines the usernames of the all users in the network
     * @return an arrayList containing the usernames of all the users in the network. If there is no users in the network, the method will return a null ArrayList.
     */
    public ArrayList<String> getAllUsers()
    {
        ArrayList<String> networkUserNames = null;

        if(!graphMap.isEmpty())//if graph is not empty
        {
            networkUserNames = new ArrayList<>();

            for (UserVertexRepresentation temp: graphMap.keySet())
            {
                networkUserNames.add("Username: " + temp.getUserName() + "  ||  Name: " + temp.getName());//storing the information of all the users in the graph.
            }
        }

        return networkUserNames;

    }

    /**
     * This method returns an Arraylist that contains the list of friends of a specific currentUser friend.
     * @param inputUsername -  a string containing the username of the friend whose friends the user wants to see
     * @return - an arrayList containing  the friends of the user's friend. If the currentUser's friend has no friends, the method will return a null ArrayList.
     */
    public ArrayList<String>  viewFriendsOfFriend(String inputUsername)
    {
        ArrayList<String> friendsOfFriendList = null;
        UserVertexRepresentation temp = null;
        UserVertexRepresentation friendVertex = null;
      
        if(currentUser!= null)
        {
            
        	friendVertex = findAddressOf(inputUsername);//finding the address of the currentUser's friend
        	
        	if(friendVertex != null)//if the friend is found
        	{
        		//if "friendVertex" is friends with currentUser and if "friendVertex" is not equals to currentUser
        		if(graphMap.get(currentUser).contains(friendVertex) && !currentUser.getUserName().equals(friendVertex.getUserName()))
        		{
        			
        			ListIterator<UserVertexRepresentation> friendList = listOfFriends(friendVertex);//gathering a list with all of the friends of "friendVertex"
        			friendsOfFriendList = new ArrayList<>();
        			

                    while(friendList.hasNext())//while "friendVertex" has friends.
                    {
                        temp = friendList.next();

                        friendsOfFriendList.add("Username: " + temp.getUserName() + "  ||   Name: " + temp.getName());//storing the information of all the the friends of "friendVertex"
                    }
        			
        		}
        		
        	}
        	 

        }

        return friendsOfFriendList;

    }

    
    
    /**
     * This method Searches a specific username in the graphMap.
     * @param inputUsername - A string containing the username to be searched
     * @return the UserVertexRepresentation address of the found username. If the username is not found in the graph, this method will return null
     */
    public String searchVertex(String inputUsername)
    {
    	String foundVertex = null;
    	UserVertexRepresentation temp = null;
    	
    	if(currentUser != null)
    	{
    		temp = findAddressOf(inputUsername);//determining the address of "inputUserName"
    		
    		if(temp!= null)//if "inputUserName" was found on the graph
    		{
    			foundVertex = "Username: " + temp.getUserName() + "   ||   Name: " + temp.getName();
    		}
    	}
    	return foundVertex;
    }

    
    /**
     * This method returns a copy of the currentUser. 
     * It is important to note that the copy returned does not contain the password or username of the currentUser for security reasons.
     */
    
    public UserVertexRepresentation getCurrentUser() {
       
    	if(currentUser == null)
    	{
    		return null;
    	}
    	return new UserVertexRepresentation(null, null, currentUser.getName(), currentUser.getStatus());
    }
    
    
    /**
     * This method carries out the changes that the user is making to their profile
     * @param newName a string containing the new name	
     * @param newStatus a string containing the new status
     */
    public void currentUserprofileChanges(String newName, String newStatus)
    {
    	
    	currentUser.setName(newName);
    	currentUser.setStatus(newStatus);
    }



}

import javax.swing.*;

public class UserVertexRepresentation
{
    private String name;
    private  String status;
    private ImageIcon image;
    private String userName;
    private String password;

    //constructor
    public UserVertexRepresentation(String userName, String password, String name, String status)
    {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.status = status;
       
    }

    //getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getUserName() {return userName;}

    /**
     * This method determines whether "inputPassword' matches the password of this object
     * @param inputPassword - a String containing the password to be checked
     * @return true if "inputPassword' matches the password of this object. False otherwise.
     * It is important to note that this comparison IS case-sensitive
     */
    public boolean checkPassword(String inputPassword)
    {
        return password.equals(inputPassword);
    }
    
    




    /**
     * This method determines whether "inputUsername' matches the username of this object
     * @param inputUserName- a String containing the username to be checked.
     * @return true if the "inputUsername" matches the the username of this object. False otherwise.
     * It is important to not that this comparison IS NOT case-sensitive.
     */
    public boolean checkUserNameMatch (String inputUserName)
    {
       return this.userName.equalsIgnoreCase(inputUserName);
    }

    @Override
    public String toString() {
        return "UserVertexRepresentation{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", image=" + image +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

//This class is what drives the whole program. It puts together all of the classes into a working piece
public class Driver {


    public static void main(String[] args)
    {

        JFrame frame = new JFrame();//Initializing a frame
        frame.setLayout(new FlowLayout());
        
		JPanel mainPanel = new JPanel();//Initializing MainPanel.This panel will contain all of the other panels in the program.
		CardLayout layout = new CardLayout();
	    mainPanel.setLayout(layout);
	    
	    
	    SocialNetwork network = new SocialNetwork(); //Initializing an object of the SocialNetwork class
	    
	    //adding panels to MainPanel
	    mainPanel.add(new LogInPanel(mainPanel, layout,network), "1");
	    mainPanel.add(new CreateAccountPanel(mainPanel, layout, network), "2");

	    layout.show(mainPanel, "1");//Showing the first panel
	    
	    
	    frame.add(mainPanel);//adding mainPanel to the frame
	    frame.pack();//sizing the frame appropriately 
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    
	   

    }
}

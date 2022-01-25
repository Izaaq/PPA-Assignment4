import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import java.awt.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.net.*;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 

/***
 * This panel with surprise you.
 * 
 * @author Guan Yi Tang
 */
public class SurprisePanel extends ContentPanel {  

    /***
     * Constructor for SurprisePanel
     */
    public SurprisePanel(String panelName) throws FileNotFoundException{
        super(panelName);
        createSurprisePanel();
    }
    
    @Override
    public ContentPanel loadPanel(){
        return this;
    }
   
    /***
     * Creates the surprise panel.
     */
   public void createSurprisePanel() throws FileNotFoundException {         
      //Creating an image 
      Image image = new Image(new FileInputStream("pictures/internet_icon.png"));  
      
      //Setting the image view 
      ImageView imageView = new ImageView(image);
      
      //setting the fit height and width of the image view 
      // imageView.setFitHeight(455); 
      // imageView.setFitWidth(500);
      
      imageView.fitWidthProperty().bind(widthProperty().divide(3));
      imageView.fitHeightProperty().bind(heightProperty().divide(3));
      
      //Setting the preserve ratio of the image view 
      imageView.setPreserveRatio(true);  
      
      //Creating a Group object  
      Group surprisePanelRoot = new Group(imageView);  
      Button surpriseButton = new Button("", imageView);
      surpriseButton.setOnAction(this::loadWebsite);
      surprisePanelRoot.getChildren().addAll(surpriseButton);
      
      setCenter(surprisePanelRoot);
   }  
   
   /***
    * Loads a website when surprise button is pressed.
    */
   public void loadWebsite(ActionEvent event){
        try{
            Desktop.getDesktop().browse(new URI("https://jason-tjh.wixsite.com/ppa4sample"));
        }   catch(IOException e1){
            e1.printStackTrace();
        }   catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }
    
    @Override
    public boolean nextButtonAction(){
        return true;
    }
    
    @Override
    public boolean prevButtonAction(){
        return true;
    }
}
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;

/***
 * A class the represents the pane and buttons for navigation between panels.
 * 
 * @author Izaaq bin Ahmad Izham
 */
public class NavigationPane extends BorderPane{

    private Button nextButton;
    private Button prevButton;
    
    private String panelName;
    private Label panelNameLabel;

    /***
     * Creates the navigation pane.
     */
    public NavigationPane(String panelName){
        nextButton = new Button("Next >");
        prevButton = new Button("< Previous");
        disableButtons();
        
        panelNameLabel = new Label(panelName);
        panelNameLabel.setStyle("-fx-font-size: 15px");

        setPadding(new Insets(5));

        setLeft(prevButton);
        setCenter(panelNameLabel);
        setRight(nextButton);
        
        setStyle("-fx-border-color: darkgray");
    }
    
    public void setPanelName(String panelName){
        panelNameLabel.setText(panelName);
    }

    /***
     * Disables next and previous buttons.
     */
    public void disableButtons(){
        nextButton.setDisable(true);
        prevButton.setDisable(true);
    }

    /***
     * Enables next and previous buttons.
     */
    public void enableButtons(){
        nextButton.setDisable(false);
        prevButton.setDisable(false); 
    }

    /***
     * Gets the next button
     * @return The next button.
     */
    public Button getNextButton(){
        return nextButton;
    }

    /***
     * Gets the previous button
     * @return The previous button.
     */
    public Button getPrevButton(){
        return prevButton;
    }

    

}
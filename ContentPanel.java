import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/***
 * This is an abstract class of panels to be displayed in the property viewer application.
 */
public abstract class ContentPanel extends BorderPane
{
    private String panelName;
    
    public ContentPanel(String panelName){
        this.panelName = panelName;
    }
    
    public String getPanelName(){
        return panelName;
    }
    
    /***
     * What happens when the next button in the navigation pane is clicked.
     * @return true if can go to the next panel.
     */
    abstract public boolean nextButtonAction();
    
    /***
     * What happens when the previous button in the navigation pane is clicked.
     * @return true if can go to the previous panel.
     */
    abstract public boolean prevButtonAction();
    
    /***
     * Loads the panel.
     * @return The panel to be loaded.
     */
    abstract public ContentPanel loadPanel();
}

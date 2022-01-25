import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.geometry.*;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import javafx.scene.effect.DropShadow;
import javafx.collections.ObservableList;

import java.util.Map;
import java.util.Set;
import java.util.List;

/***
 * This class represents the map of boroughs as hexagonal buttons.
 * 
 * @author Mohamad Imran bin Yaacob, Guan Yi Tang
 */
public class BoroughMap extends Pane{

    private static final int PANE_PREF_HEIGHT = 720;
    private static final int PANE_PREF_WIDTH = 1080;

    private Statistics statistics;

    private int highestPropertyCount;

    /***
     * Constructs the map of the borough.
     * @param statistics The statistics of the loaded listings.
     */
    public BoroughMap(Statistics statistics){
        // settings to make map centered in window when window is resized
        setPrefSize(PANE_PREF_WIDTH, PANE_PREF_HEIGHT);
        setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);

        // customize the pane
        setPadding(new Insets(10));

        this.statistics = statistics;

        createBoroughMap();
    }

    /***
     * Creates the borough map. The borough map consists of hexagonal buttons.
     */
    private void createBoroughMap(){
        String[] boroughs = BoroughCoordinates.getBoroughs();

        for(String borough : boroughs){
            BoroughButton boroughButton = new BoroughButton(borough);
            // when button is clicked, a new window pops up. this window shows further details for properties in that borough
            boroughButton.setOnAction(event -> new PropertyInfoWindow(borough, statistics));
            
            getChildren().add(boroughButton);
        }
    }

    /***
     * This method styles the buttons whose borough they represent contains properties in the specified
     * price range.
     */
    public void styleMap(){
        ObservableList<Node> children = getChildren();
        Map<String, Integer> boroughsToPropertyCount = statistics.getBoroughToNumOfProperty();

        highestPropertyCount = statistics.getHighestPropertyCount(boroughsToPropertyCount);

        for(Node child : children){
            if(child instanceof BoroughButton){
                // casts child as a boroughButton
                BoroughButton boroughButton = (BoroughButton) child;
                // reset button's style
                boroughButton.destyleButton();

                // styles only the button with properties (colors them green, applies drop shadow)
                String boroughName = boroughButton.getBoroughName();

                if(boroughsToPropertyCount.containsKey(boroughName)){
                    int numberOfProperties = boroughsToPropertyCount.get(boroughName);

                    boroughButton.styleButton(numberOfProperties, highestPropertyCount);
                }
            }
        }
    }

}
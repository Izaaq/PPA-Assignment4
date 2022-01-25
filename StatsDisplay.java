import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.text.DecimalFormat;

/***
 * This class turns the statistics into displayable form.
 * 
 * @author Jian Han Tey
 */
public class StatsDisplay extends VBox{
    
    private Label displayNameLabel;
    private Label dataToDisplayLabel;
    // name of statistics to be displayed
    private String displayName;
    // statistic to be displayed
    private String dataToDisplay;
    private int displayCode;
    private Statistics statistics;
    
    /***
     * Constructs the statistics display.
     * @param displayCode Code to determine what to display
     * @param statistics Statistics from data loaded.
     */
    public StatsDisplay(int displayCode, Statistics statistics){
        this.statistics = statistics;
        this.displayCode = displayCode;

        getStylesheets().add("statsDisplayStyle.css");
        
        setDataToDisplay();

        // create display name label
        displayNameLabel = new Label(displayName);
        displayNameLabel.setId("name-label");

        // create data to display label
        dataToDisplayLabel = new Label(dataToDisplay);
        dataToDisplayLabel.setId("data-label");

        // --- sets up vbox ---
        // adds children
        getChildren().addAll(displayNameLabel, dataToDisplayLabel);

        setAlignment(Pos.CENTER);
        setPadding(new Insets(5));
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /***
     * Updates the statistics displayed.
     */
    public void updateDataToDisplay(){
        setDataToDisplay();
        displayNameLabel.setText(displayName);
        dataToDisplayLabel.setText(dataToDisplay);
    }

    /***
     * Determines what to display based on display code.
     */
    public void setDataToDisplay(){
        DecimalFormat df = new DecimalFormat("#.##");
        
        switch(displayCode) {
            case 0:
            displayName = "Total Available Properties";
            dataToDisplay = "" + statistics.getTotalAvailableProperties();
            break;

            case 1:
            displayName = "Average Reviews Per Property";
            dataToDisplay = "" + df.format(statistics.getAverageReviewPerProperty());
            break;

            case 2:
            displayName = "Most Expensive Borough";
            dataToDisplay = "" + statistics.getMostExpensiveBorough();
            break;

            case 3:
            displayName = "Borough With Most Reviews";
            dataToDisplay = "" + statistics.getBoroughWithMostReviews();
            break;

            case 4:
            displayName = "Total Home and Apartments";
            dataToDisplay = "" + statistics.getTotalHomeAndApartments();
            break;

            case 5:
            displayName = "Average Property Price";
            dataToDisplay = "" + df.format(statistics.getAveragePropertyPrice());
            break;

            case 6:
            displayName = "Property With Most Minimum Nights";
            dataToDisplay = "" + statistics.getPropertyWithMostMinimumNights();
            break;

            case 7:
            displayName = "Property With Most Reviews";
            dataToDisplay = "" + statistics.getPropertyWithMostReviews();
            break;
        }
    }
}
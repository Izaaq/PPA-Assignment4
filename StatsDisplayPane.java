import javafx.scene.layout.*;
import javafx.scene.control.Button;

import java.util.LinkedList;

/***
 * A class that stores the statistics displays.
 * 
 * @author Jian Han Tey
 */
public class StatsDisplayPane extends BorderPane{

    private LinkedList<StatsDisplay> statsNotDisplayed;
    private StatsDisplay statsDisplay;

    /***
     * Constructs StatsDisplayPane object.
     * @param statsNotDisplayed A linked list of statistics displays which are not displayed.
     */
    public StatsDisplayPane(LinkedList<StatsDisplay> statsNotDisplayed){
        this.statsNotDisplayed = statsNotDisplayed;

        // create next button
        Button nextButton = new Button(">");
        nextButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        nextButton.setPrefWidth(50);

        // create prev button
        Button prevButton = new Button("<");
        prevButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        prevButton.setPrefWidth(50);

        // create stats display
        statsDisplay = statsNotDisplayed.removeFirst();

        // create button events
        nextButton.setOnAction(event -> displayNextButtonAction());
        prevButton.setOnAction(event -> displayPrevButtonAction());

        // add buttons to display pane
        setLeft(prevButton);
        setRight(nextButton);
        
        GridPane.setHgrow(this, Priority.ALWAYS);
        GridPane.setVgrow(this, Priority.ALWAYS);

        setCenter(statsDisplay);
    }

    /***
     * What to do when the next button in the display pane is clicked.
     * Switches to the next display in the statsNotDisplayed list.
     */
    private void displayNextButtonAction(){
        statsNotDisplayed.addLast(statsDisplay);
        statsDisplay = statsNotDisplayed.removeFirst();
        setCenter(statsDisplay);
    }

    /***
     * What to do when the previous button in the display pane is clicked.
     * Switches to the previous display in the statsNotDisplayed list.
     */
    private void displayPrevButtonAction(){
        statsNotDisplayed.addFirst(statsDisplay);
        statsDisplay = statsNotDisplayed.removeLast();
        setCenter(statsDisplay);
    }
}
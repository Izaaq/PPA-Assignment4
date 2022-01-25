import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

/***
 * Panel that displays statistics calculated from data loaded.
 * 
 * @author Jian Han Tey
 */
public class StatsPanel extends ContentPanel{

    private static final int NUM_OF_PANELS = 4;
    private static final int NUM_OF_DISPLAYS = 8;

    private Statistics statistics;

    private LinkedList<StatsDisplay> statsNotDisplayed;
    private List<StatsDisplay> statsDisplays;

    /***
     * Constructs the statistics panel.
     * @param statistics The statistics calculated from data loaded.
     */
    public StatsPanel(String panelName, Statistics statistics){
        super(panelName);
        this.statistics = statistics;

        statsNotDisplayed = new LinkedList<>();
        statsDisplays = new ArrayList<>();

        createStatsDisplayGrid();
    }

    /***
     * Creates a 2x2 display grid, where each grid shows a different statistic.
     */
    public void createStatsDisplayGrid(){
        List<StatsDisplayPane> statsDisplayPanesList = createDisplayPanesList();
        // create stats display grid
        GridPane statsDisplayGrid = new GridPane();
        statsDisplayGrid.getChildren().addAll(statsDisplayPanesList);

        // adds display panes to the display grid
        for(int index = 0; index < NUM_OF_PANELS; ){
            for(int y = 0; y < 2; y++){
                for(int x = 0; x < 2; x++){
                    GridPane.setConstraints(statsDisplayPanesList.get(index), x, y);
                    // make the height and width of display panes proportional to the display grid
                    statsDisplayPanesList.get(index).prefWidthProperty().bind(statsDisplayGrid.widthProperty().divide(2));
                    statsDisplayPanesList.get(index).prefHeightProperty().bind(statsDisplayGrid.heightProperty().divide(2));
                    index++;
                }

            }
        }
        
        // customize grid pane
        statsDisplayGrid.setVgap(10);
        statsDisplayGrid.setHgap(10);
        statsDisplayGrid.setPadding(new Insets(10));

        setCenter(statsDisplayGrid);
    }

    private List<StatsDisplayPane> createDisplayPanesList(){
        createStatsDisplays();

        List<StatsDisplayPane> statsDisplayPanesList = new LinkedList<>();

        for(int index = 0; index < NUM_OF_PANELS; index++){
            statsDisplayPanesList.add(new StatsDisplayPane(statsNotDisplayed));
        }

        return statsDisplayPanesList;
    }

    private void createStatsDisplays(){
        for(int index = 0; index < NUM_OF_DISPLAYS; index++){
            statsNotDisplayed.add(new StatsDisplay(index, statistics));
        }
        
        statsDisplays.addAll(statsNotDisplayed);
    }

    @Override
    public boolean nextButtonAction(){
        return true;
    }

    @Override
    public boolean prevButtonAction(){
        return true;
    }

    @Override
    public ContentPanel loadPanel(){
        for(StatsDisplay display : statsDisplays){
            display.updateDataToDisplay();
        }
        return this;
    }
}
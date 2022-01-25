import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;

/***
 * This class represents the map panel.
 * 
 * @author Mohamad Imran bin Yaacob
 */
public class MapPanel extends ContentPanel{
    
    private Statistics statistics;
    
    private BoroughMap boroughMap;

    /***
     * Constructor for the map panel.
     * @statistics The statistics calculated from data loaded.
     */
    public MapPanel(String panelName, Statistics statistics){
        super(panelName);
        this.statistics = statistics;
        
        createMapPanel();
    }
    
    @Override
    public ContentPanel loadPanel(){
        boroughMap.styleMap();
        return this;
    }
    
    /***
     * Creates the map panel.
     */
    private void createMapPanel(){
        
        // create BoroughMap
        boroughMap = new BoroughMap(statistics);
        
        // create BorderPane to center map
        BorderPane mapHolder = new BorderPane(boroughMap);
        
        // create scroll pane
        ScrollPane mapScrollPane = new ScrollPane();
        // customizing the scroll pane
        mapScrollPane.setFitToWidth(true);
        mapScrollPane.setFitToHeight(true);
        mapScrollPane.setPannable(true);
        
        mapScrollPane.setContent(mapHolder);
        mapScrollPane.getStylesheets().add("styleMap.css");
        
        setCenter(mapScrollPane);
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
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*; 
import javafx.event.ActionEvent;
import javafx.collections.*;
import javafx.stage.Stage;
import javafx.geometry.*;

import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException; 

/***
 * This is the main class of the application. This class is created to start the application.
 * 
 * @author Izaaq bin Ahmad Izham, Mohamad Imran bin Yaacob
 */
public class PropertyViewer extends Application{

    private ArrayList<ContentPanel> panels;
    private NavigationPane navigationPane;

    private Statistics statistics;

    private BorderPane root;

    private int page;

    /***
     * Constructor for the Property Viewer application.
     */
    public PropertyViewer(){
        super();
        page = 0;
        panels = new ArrayList<>();
        statistics = new Statistics();
    }

    /***
     * Method is called by the JavaFX framework to start the application.
     * @param stage The primary stage.
     */
    public void start(Stage stage) throws FileNotFoundException{
        // create root
        root = new BorderPane();

        // create navigation pane
        navigationPane = new NavigationPane("Welcome Panel");

        // create
        createContentPanels();

        // create button events
        navigationPane.getNextButton().setOnAction(event -> nextButtonAction());
        navigationPane.getPrevButton().setOnAction(event -> prevButtonAction());

        // position panes
        root.setCenter(panels.get(0));
        root.setBottom(navigationPane);

        // create scene
        Scene scene = new Scene(root);

        // sets stage 
        stage.setTitle("Property Viewer");
        stage.setScene(scene);
        stage.setMaximized(true);

        // display stage 
        stage.show();

    }

    /***
     * Creates panels to be displayed. Adds it to a list of panels.
     */
    private void createContentPanels() throws FileNotFoundException{
        panels.add(new WelcomePanel("Welcome Panel", navigationPane, statistics));
        panels.add(new MapPanel("Map Panel", statistics));
        panels.add(new StatsPanel("Statistics Panel", statistics));
        panels.add(new SurprisePanel("Website Panel"));
    }

    /***
     * What happens when the next button is pressed.
     * Loads the next panel in the list of panels.
     */
    private void nextButtonAction(){
        int numberOfPages = panels.size();
        boolean success = panels.get(page).nextButtonAction();
        if(success){    // checks if it is ok to proceed
            // does not allow next page to exceep size of list of panels
            int nextPage = (page + 1) % numberOfPages;

            // load the next panel
            ContentPanel nextPanel = panels.get(nextPage).loadPanel();
            root.setCenter(nextPanel);
            navigationPane.setPanelName(nextPanel.getPanelName());

            page = nextPage;
        }
    }

    /***
     * What happens when the previous button is pressed.
     * Load the previous panel in the list of panels.
     */
    private void prevButtonAction(){
        int numberOfPages = panels.size();
        boolean success = panels.get(page).nextButtonAction();
        if(success){    // checks if it is ok to proceed
            int nextPage = (page - 1) % numberOfPages;
            // if next page is less than zero, assign it to the last page number
            if(nextPage == -1){ 
                int finalPage = numberOfPages - 1;
                nextPage = (finalPage);
            }
            // load the previous panel
            ContentPanel nextPanel = panels.get(nextPage).loadPanel();
            root.setCenter(nextPanel);
            navigationPane.setPanelName(nextPanel.getPanelName());

            page = nextPage;
        }
    }
}
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.beans.value.ObservableValue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableRow;

import java.util.ArrayList;
import java.util.List;

/***
 * This class represents a window that show additional information about a property.
 * 
 * @author Mohamad Imran bin Yaacob
 */
public class PropertyInfoWindow extends Stage{

    private String boroughName;
    private Statistics statistics;
    private TableView<AirbnbListing> propertiesTable;
    
    private HBox sortBar;
    private ChoiceBox<String> sortingChoiceBox;

    /***
     * Constructs PropertyTableView
     * @param boroughName The name of the borough.
     * @param statistics The statistics of the loaded listings.
     */
    public PropertyInfoWindow(String boroughName, Statistics statistics){

        this.boroughName = boroughName;
        this.statistics = statistics;

        // create the table
        createPropertiesTable();
        
        // create sorting bar
        createSortingBar();
        
        // customize the stage to (1) no modailty, (2) title is the name of borough
        initModality(Modality.NONE);
        setTitle(boroughName);

        // create root
        BorderPane root = new BorderPane();
        root.setCenter(propertiesTable);
        root.setTop(sortBar);

        // create scene
        Scene scene = new Scene(root);

        setScene(scene);
        show();
    }

    /***
     * Creates the table that lists all the properties.
     */
    private void createPropertiesTable(){
        // creates the table
        propertiesTable = new TableView<>();
        // set resize policy
        propertiesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        /// set table's pref width
        propertiesTable.setPrefWidth(700);
        // set row with a mouse click event
        propertiesTable.setRowFactory(tableRow -> {
        TableRow<AirbnbListing> listingRow = new TableRow<>();
        listingRow.setOnMouseClicked(event -> createMoreInfoWindow());
        
        return listingRow;
        });

        // --- creates table columns ---
        // create host name column
        TableColumn<AirbnbListing, String> hostNameColumn = new TableColumn<>("Host Name");
        hostNameColumn.setCellValueFactory(new PropertyValueFactory("host_name"));
        
        // create property price column
        TableColumn<AirbnbListing, Integer> priceColumn = new TableColumn<>("Property Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        
        // create number of reviews column
        TableColumn<AirbnbListing, Integer> numberOfReviewsColumn = new TableColumn<>("Number of Reviews");
        numberOfReviewsColumn.setCellValueFactory(new PropertyValueFactory("numberOfReviews"));
        
        // create minimum number of nights column
        TableColumn<AirbnbListing, Integer>  minimumNightsColumn = new TableColumn<>("Minimum Number of Nights");
        minimumNightsColumn.setCellValueFactory(new PropertyValueFactory("minimumNights"));
        
        // add columns to table
        propertiesTable.getColumns().setAll(hostNameColumn, priceColumn, numberOfReviewsColumn, minimumNightsColumn);
        
        propertiesTable.getItems().setAll(statistics.getListingsInBorough(boroughName));
        
    }
    
    /***
     * Creates the window the pops up to show more info about a property when a row is clicked.
     */
    private void createMoreInfoWindow(){
        AirbnbListing property = propertiesTable.getSelectionModel().getSelectedItem();
        
        Stage moreInfoWindow = new Stage();
        // set window modality
        moreInfoWindow.initModality(Modality.WINDOW_MODAL);
        moreInfoWindow.initOwner(this);
        // set window title
        moreInfoWindow.setTitle("More Info");
        
        // create a label to show more info
        Label propertyInfoLabel = new Label(property.getListingTextDisplay());
        propertyInfoLabel.setPadding(new Insets(10));
        propertyInfoLabel.setStyle("-fx-font: 16px \"Helvetica\"");
        
        // create root for moreInfoWindow
        StackPane infoWindowRoot = new StackPane(propertyInfoLabel);
        
        // create scene for moreInfoWindow
        Scene infoWindowScene = new Scene(infoWindowRoot);
        
        // sets scene
        moreInfoWindow.setScene(infoWindowScene);
        
        
        moreInfoWindow.showAndWait();
    }
    
    /***
     * Creates the sorting bar that has a label and a choice box to select how to sort the table.
     */
    private void createSortingBar(){
        // create label
        Label sortByLabel = new Label("Sort by:");
        sortByLabel.setAlignment(Pos.CENTER);
        
        // create choice box that contains sorting options
        sortingChoiceBox = new ChoiceBox<>();
        // add sorting options
        String sortByHostName = "Host Name";
        String sortByNumberOfReviews = "Number of Reviews";
        String sortByPrice = "Property Price";
        
        sortingChoiceBox.getItems().setAll(sortByHostName, sortByNumberOfReviews, sortByPrice);
        
        // add listeners to choice box
        sortingChoiceBox.getSelectionModel().selectedItemProperty().addListener(this::sortTable);
        
        // create a hbox
        sortBar = new HBox();
        // add children to hbox
        sortBar.getChildren().addAll(sortByLabel, sortingChoiceBox);
        // customize hbox
        sortBar.setSpacing(4);
        sortBar.setPadding(new Insets(10, 10, 5, 10));
    }
    
    private void sortTable(ObservableValue ov, String oldString, String newString){
        for(TableColumn column : propertiesTable.getColumns()){
            if(newString.equals(column.getText())){
                propertiesTable.getSortOrder().setAll(column);
            }
        }
    }
}
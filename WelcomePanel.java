import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.collections.FXCollections;

import java.util.List;
import java.util.ArrayList;

/***
 * This class represents the welcome panel, greeting the user when application starts.
 * 
 * @author Izaaq bin Ahmad Izham, Jian Han, Tey IMG
 */
public class WelcomePanel extends ContentPanel{

    private static final Image WELCOME_IMAGE = new Image("pictures/menu_image.jpg");

    private NavigationPane navPane;
    private Statistics statistics;

    private ComboBox<Integer> minPriceBox;
    private ComboBox<Integer> maxPriceBox;

    /***
     * Constructor for WelcomePanel.
     * @param navPane The navigation pane.
     * @statistics Statistics calculated from data loaded.
     */
    public WelcomePanel(String panelName, NavigationPane navPane, Statistics statistics){
        super(panelName);
        this.navPane = navPane;
        this.statistics = statistics;

        createWelcomePanel();
    }
    
    @Override
    public ContentPanel loadPanel(){
        return this;
    }

    /***
     * Creates the welcome panel.
     */
    private void createWelcomePanel(){
        createPriceRangePane();
        createWelcomeDisplay();
    }

    /***
     * Creates the price range for selection.
     */
    private void createPriceRangePane(){
        // creates a hbox that contains the price selection combo boxes
        HBox priceRangePane = new HBox();
        priceRangePane.setSpacing(2);
        priceRangePane.setAlignment(Pos.CENTER_RIGHT);

        createPriceBoxes(priceRangePane);

        // places the hbox at the top
        setTop(priceRangePane);
    }

    /***
     * Creates the price boxes, which users can use to select price.
     */
    private void createPriceBoxes(Pane parent){
        // create From and To labels
        Label fromLabel = new Label("From:");
        Label toLabel = new Label("To:");

        // creates a list of prices incremented by 50
        List<Integer> prices = new ArrayList<>();

        int maxPrice = 7000;

        for(int price = 0; price <= maxPrice; price += 50){
            prices.add(price);
        }   

        minPriceBox = new ComboBox(FXCollections.observableArrayList(prices));
        maxPriceBox = new ComboBox(FXCollections.observableArrayList(prices));

        // adds label and combo boxes to parent
        parent.getChildren().addAll(fromLabel, minPriceBox, toLabel, maxPriceBox);

        // add listeners to combo boxes to grey out buttons
        minPriceBox.getSelectionModel().selectedItemProperty().addListener(event -> comboBoxCheck());
        maxPriceBox.getSelectionModel().selectedItemProperty().addListener(event -> comboBoxCheck());

        // sets the margin for each children
        HBox.setMargin(fromLabel, new Insets(5));
        HBox.setMargin(minPriceBox, new Insets(5));
        HBox.setMargin(toLabel, new Insets(5));
        HBox.setMargin(maxPriceBox, new Insets(5));
    }

    /***
     * Checks to make sure there are values in the combo boxes.
     */
    private void comboBoxCheck(){
        if(!maxPriceBox.getSelectionModel().isEmpty() && !minPriceBox.getSelectionModel().isEmpty()){
            navPane.enableButtons();
        } else if(maxPriceBox.getSelectionModel().isEmpty() || minPriceBox.getSelectionModel().isEmpty()){
            navPane.disableButtons();
        }
    }

    /***
     * Creates the welcome display, which holds the welcome image.
     */
    private void createWelcomeDisplay(){
        // creates an imageview to display the welcome image
        ImageView welcomeDisplay = new ImageView(WELCOME_IMAGE);
        welcomeDisplay.setPreserveRatio(true);
        welcomeDisplay.setFitHeight(720);
        welcomeDisplay.setFitWidth(1080);

        Label welcomeDisplayLabel = new Label("", welcomeDisplay);
        welcomeDisplayLabel.setPadding(new Insets(10));

        // places the display in the center
        setCenter(welcomeDisplayLabel);
    }

    @Override
    public boolean nextButtonAction(){
        if(maxPriceBox.getValue() < minPriceBox.getValue()){
            Alert error = new Alert(AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText(null);
            error.setContentText("ERROR - 'To' value must be larger than or equal to 'From' value.");

            error.showAndWait();
            return false;
        } else {
            statistics.setPrices(minPriceBox.getValue(), maxPriceBox.getValue());
            statistics.loadListingsWithRange();
            statistics.updateStatistics();
            return true;
        }

    }
    
    @Override
    public boolean prevButtonAction(){
        return true;
    }

}

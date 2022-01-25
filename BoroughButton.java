import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.text.TextAlignment;

/***
 * This class represents a single borough as a hexagonal button.
 * 
 * @author Mohamad Imran bin Yaacob
 */
public class BoroughButton extends Button{

    private static final int BUTTON_PREF_HEIGHT = 126;
    private static final int BUTTON_PREF_WIDTH = 108;

    private String boroughName;

    /***
     * Constructs BoroughButton
     * @param boroughName The name of the borough that this button will represent.
     */
    public BoroughButton(String boroughName){
        super(boroughName.toUpperCase());
        this.boroughName = boroughName;

        setAlignment(Pos.CENTER);
        setTextAlignment(TextAlignment.CENTER);
        setWrapText(true);

        setPrefSize(BUTTON_PREF_WIDTH, BUTTON_PREF_HEIGHT);

        int x = BoroughCoordinates.getX(boroughName);
        int y = BoroughCoordinates.getY(boroughName);

        relocate(x, y);

        setDisable(true);
    }

    /***
     * This method applies styles to the button.
     * Button is colored green. The saturation depends upon the number of properties the borough the button represents have available.
     * Drop shadow is applied to the button.
     * @param numberOfProperties The number of properties available in the borough the button represents.
     * @param highestPropertyCount The highest property count in the price range.
     */
    public void styleButton(int numberOfProperties, int highestPropertyCount){
        getStyleClass().add("button-with-properties");

        // calculates the saturation value based on the number of properties
        int saturationValue = (int) (((double)numberOfProperties/(double)highestPropertyCount) * 100);

        setStyle("-fx-background-color: hsb(120, " + saturationValue + "%, 100%)");
        getStyleClass().add("button-with-properties");

        setDisable(false);
    }

    /***
     * This method removes all styles from the button, and set it to its default haxagonal shape with no colors and effects.
     * It also disables the button.
     */
    public void destyleButton(){
        setStyle(null);
        getStyleClass().setAll("button");
        setDisable(true);
    }

    /***
     * This method returns the name of the borough
     * @return The name of the borough.
     */
    public String getBoroughName(){ return boroughName;}

}
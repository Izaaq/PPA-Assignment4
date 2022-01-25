
/***
 * This class has methods that return the coordinates that allow
 * placement in a pane. 
 * This class also stores the final array of borough names
 * 
 * @author Mohamad Imran bin Yaacob, Guan Yi Tang
 */
public class BoroughCoordinates{

    /***
     * This array stores the final list of borough names.
     */
    private static final String[] boroughs = {
            "Barking and Dagenham",
            "Barnet",
            "Bexley",
            "Brent",
            "Bromley",
            "Camden",
            "City of London",
            "Croydon",
            "Ealing",
            "Enfield",
            "Greenwich",
            "Hackney",
            "Hammersmith and Fulham",
            "Haringey",
            "Harrow",
            "Havering",
            "Hillingdon",
            "Hounslow",
            "Islington",
            "Kensington and Chelsea",
            "Kingston upon Thames",
            "Lambeth",
            "Lewisham",
            "Merton",
            "Newham",
            "Redbridge",
            "Richmond upon Thames",
            "Southwark",
            "Sutton",
            "Tower Hamlets",
            "Waltham Forest",
            "Wandsworth",
            "Westminster"
        };

    private static int x;
    private static int y;

    /***
     * @return The x coordinate of the borough button
     */
    public static int getX(String borough){
        setCoordinates(borough);
        return x;
    }

    /***
     * @return The y coordinate of the borough button
     */
    public static int getY(String borough){
        setCoordinates(borough);
        return y;
    }
    
    /***
     * @return The array that holds all borough names.
     */
    public static String[] getBoroughs(){
        return boroughs;
    }

    /***
     * Sets the x and y coordinate of the borough button.
     */
    private static void setXY(int xCoordinate, int yCoordinate){
        x = xCoordinate;
        y = yCoordinate;
    }

    /***
     * Sets the coordinates x and y coordinates of boroughs. 
     * Used to layout buttons in a pane.
     */
    private static void setCoordinates(String borough){
        borough = borough.toLowerCase().trim();

        switch(borough) {
            case "barking and dagenham":
            setXY(794, 297);
            break;

            case "barnet":
            setXY(335, 99);
            break;

            case "bexley":
            setXY(737, 396);
            break;

            case "brent":
            setXY(278, 197);
            break;

            case "bromley":
            setXY(622, 594);
            break;

            case "camden":
            setXY(393, 197);
            break;

            case "city of london":
            setXY(507, 396);
            break;

            case "croydon":
            setXY(508, 594);
            break;

            case "ealing":
            setXY(221, 297);
            break;

            case "enfield":
            setXY(507, 0);
            break;

            case "greenwich":
            setXY(622, 396);
            break;

            case "hackney":
            setXY(622, 197);
            break;

            case "hammersmith and fulham":
            setXY(278, 396);
            break;

            case "haringey":
            setXY(450, 99);
            break;

            case "harrow":
            setXY(163, 197);
            break;

            case "havering":
            setXY(851, 197);
            break;

            case "hillingdon":
            setXY(106, 297);
            break;

            case "hounslow":
            setXY(163, 396);
            break;

            case "islington":
            setXY(507, 197);
            break;

            case "kensington and chelsea":
            setXY(336, 297);
            break;

            case "kingston upon thames":
            setXY(278, 594);
            break;

            case "lambeth":
            setXY(451, 495);
            break;

            case "lewisham":
            setXY(680, 495);
            break;

            case "merton":
            setXY(336, 495);
            break;

            case "newham":
            setXY(680, 297);
            break;

            case "redbridge":
            setXY(737, 197);
            break;

            case "richmond upon thames":
            setXY(221, 495);
            break;

            case "southwark":
            setXY(565, 495);
            break;

            case "sutton":
            setXY(393, 594);
            break;

            case "tower hamlets":
            setXY(565, 297);
            break;

            case "waltham forest":
            setXY(565, 99);
            break;

            case "wandsworth":
            setXY(393, 396);
            break;

            case "westminster":
            setXY(450, 297);
            break;
        }

    }
}


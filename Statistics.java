import java.util.List;
import java.util.ArrayList;
import java.util.Map;
//import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * This class keeps track of statistics based around the properties.
 * 
 * @author Izaaq bin Ahmad Izham, Mohamad Imran bin Yaacob, Jian Han Tey, Guan Yi Tang
 */
public class Statistics
{
    private static final int DEFAULT_NUMBER = 0;
    private static final String DEFAULT_STRING = "None";
    
    // instance variables 
    private AirbnbDataLoader loader;
    private ArrayList<AirbnbListing> listings;

    private int minPrice;
    private int maxPrice;

    // --- statistics stored to be displayed ---
    private int totalAvailableProperties;
    private double averageReviewPerProperty;
    private String mostExpensiveBorough;
    private String boroughWithMostReviews;
    private int totalHomeAndApartments;
    private double averagePropertyPrice;
    private String propertyWithMostMinimumNights;
    private String propertyWithMostReviews;

    /***
     * Constructor for objects of class Statistics.
     */
    public Statistics()
    {
        loader = new AirbnbDataLoader();
        listings = new ArrayList<>();
    }

    /***
     * Get the list of properties within the selected price range.
     * @return The list of properties within the selected price range.
     */
    public ArrayList<AirbnbListing> getListings(){
        return listings;
    }

    /***
     * This method sets the minimum price of the price range to list properties from.
     * @param minPrice The minimum price for the price range to list properties from.
     */
    public void setMinPrice(int minPrice){
        this.minPrice = minPrice;
    }

    /***
     * This method sets the maximum price of the price range to list properties from.
     * @param maxPrice The maximum price for the price range to list properties from.
     */
    public void setMaxPrice(int maxPrice){
        this.maxPrice = maxPrice;
    }

    /***
     * This method sets both the minimum and maximum price at the same time for convenience.
     * @param minPrice The minimum price for the price range to list properties from.
     * @param maxPrice The maximum price for the price range to list properties from.
     */
    public void setPrices(int minPrice, int maxPrice){
        setMinPrice(minPrice);
        setMaxPrice(maxPrice);
    }

    /***
     * This method loads all of the data in the SCSV file regardless of price.
     */
    public void loadListings(){
        listings = loader.load(); 
    }

    /***
     * This method loads only the data within the price range selected.
     */
    public void loadListingsWithRange(){
        listings = loader.loadFiltered(minPrice, maxPrice); 
    }

    /***
     * Recalculates all the statistics to be displayed.
     */
    public void updateStatistics(){
        // if minPrice == maxPrice == 0, set everything to default value
        if(minPrice == 0 && maxPrice == 0){
            totalAvailableProperties = DEFAULT_NUMBER;
            averageReviewPerProperty = DEFAULT_NUMBER;
            mostExpensiveBorough = DEFAULT_STRING;
            boroughWithMostReviews = DEFAULT_STRING;
            totalHomeAndApartments = DEFAULT_NUMBER;
            averagePropertyPrice = DEFAULT_NUMBER;
            propertyWithMostMinimumNights = DEFAULT_STRING;
            propertyWithMostReviews = DEFAULT_STRING;
        } else {
            totalAvailableProperties = calculateTotalAvailableProperties();
            averageReviewPerProperty = averageReviewPerProperty();
            mostExpensiveBorough = calculateMostExpensiveBorough();
            boroughWithMostReviews = calculateBoroughWithMostReviews();
            totalHomeAndApartments = totalHomeAndApartments();
            averagePropertyPrice = averagePropertyPrice();
            propertyWithMostMinimumNights = propertyWithMostMinimumNights();
            propertyWithMostReviews = propertyWithMostReviews();
        }
    }

    // --- statistics getter method ---
    public int getTotalAvailableProperties(){return totalAvailableProperties;}

    public double getAverageReviewPerProperty(){return averageReviewPerProperty;}

    public String getMostExpensiveBorough(){return mostExpensiveBorough;}

    public String getBoroughWithMostReviews(){return boroughWithMostReviews;}

    public int getTotalHomeAndApartments(){return totalHomeAndApartments;}

    public double getAveragePropertyPrice(){return averagePropertyPrice;}

    public String getPropertyWithMostMinimumNights(){return propertyWithMostMinimumNights;}

    public String getPropertyWithMostReviews(){return propertyWithMostReviews;}

    // --- displayed statistics calculation ---

    /***
     * This method calculates the total available properties within specified price range.
     */
    public int calculateTotalAvailableProperties()
    {
        ArrayList<AirbnbListing> newListings = new ArrayList<>();

        for(AirbnbListing listing : listings){
            if(listing.getAvailability365() > 0){
                newListings.add(listing);
            }
        }

        return newListings.size();
    }

    /***
     * Calculates the average reviews for one property.
     */
    public double averageReviewPerProperty()
    {
        int numberOfProperties = listings.size();
        int totalNumberOfReviews = 0;

        for(AirbnbListing listing : listings){
            totalNumberOfReviews += listing.getNumberOfReviews();
        }

        return totalNumberOfReviews/numberOfProperties;
    }

    /**
     * Calculates the most expensive borough by finding the borough with the most expensive average price.
     */
    public String calculateMostExpensiveBorough(){
        HashMap<String, Double> boroughsToAveragePrice = new HashMap<>();
        getAveragePricePerBorough(boroughsToAveragePrice);

        String maxPriceBorough = null;

        for(String borough : boroughsToAveragePrice.keySet()){
            if(maxPriceBorough == null){
                maxPriceBorough = borough;
            } else if (boroughsToAveragePrice.get(borough) > boroughsToAveragePrice.get(maxPriceBorough)){
                maxPriceBorough = borough;
            }
        }

        return maxPriceBorough;
    }

    /**
     *  calculates borough with most reviews from Airbnb data.
     */
    public String calculateBoroughWithMostReviews(){
        HashMap<String, Integer> boroughsReviewsMap = new HashMap<>();

        String mostReviewedBorough = null;

        for(AirbnbListing listing : listings) {
            if(boroughsReviewsMap.containsKey(listing.getNeighbourhood())){
                int oldReviews = boroughsReviewsMap.get(listing.getNeighbourhood());
                int newReviews = oldReviews + listing.getNumberOfReviews();
                boroughsReviewsMap.replace(listing.getNeighbourhood(), newReviews);
            }
            else {
                boroughsReviewsMap.put(listing.getNeighbourhood(), listing.getNumberOfReviews());
            }
        }

        for(String borough : boroughsReviewsMap.keySet()) {
            if(mostReviewedBorough == null) {
                mostReviewedBorough = borough;
            }
            else if(boroughsReviewsMap.get(borough) > boroughsReviewsMap.get(mostReviewedBorough)) {
                mostReviewedBorough = borough;
            }
        }

        return mostReviewedBorough;
    }

    /***
     * Calculates the total number of home and apartments.
     */
    public int totalHomeAndApartments()
    {
        int totalHomeAndApartments = 0;

        for(AirbnbListing listing : listings){
            if(listing.getRoom_type().equals("Entire home/apt")){
                totalHomeAndApartments++;
            }
        }

        return totalHomeAndApartments;
    }

    /***
     * Calculates the average property price of Home and Apartments.
     */
    public double averagePropertyPrice()
    {
        int propertyPrice = 0;
        for(AirbnbListing listing : listings)
        {
            propertyPrice += listing.getPrice();
        }

        return (double)propertyPrice / (double)listings.size();
    }

    /**
     * Calculate the name of the property with most number of nights along with the borough
     */
    public String propertyWithMostMinimumNights()
    {
        AirbnbListing counter = listings.get(0);
        for(AirbnbListing listing : listings){
            if(listing.getMinimumNights() > counter.getMinimumNights()){
                counter = listing;
            }
        }

        return counter.getName() + ", " + counter.getNeighbourhood();
    }

    /**
     * Calculates the property with the most reviews along with the borough.
     */
    public String propertyWithMostReviews()
    {
        AirbnbListing counter = listings.get(0);
        for(AirbnbListing listing : listings){
            if(listing.getNumberOfReviews() > counter.getNumberOfReviews()){
                counter = listing;
            }
        }
        return counter.getName() + ", " + counter.getNeighbourhood();
    }

    // --- calculation of statistics that are not shown ---
    /***
     * Returns a map that maps borough name to the number of properties available (availability > 0). This is not displayed
     * in the statistics panel.
     * @return A map that maps borough name to the number of properties available (availability > 0).
     */
    public Map<String, Integer> getBoroughToNumOfProperty(){
        HashMap<String, Integer> boroughsToPropertyCount = new HashMap<>();

        for(AirbnbListing listing : listings){
            if(listing.getAvailability365() > 0) {
                if(boroughsToPropertyCount.containsKey(listing.getNeighbourhood())){
                    int oldCount = boroughsToPropertyCount.get(listing.getNeighbourhood());

                    // increase property count
                    int newCount = oldCount + 1;
                    boroughsToPropertyCount.replace(listing.getNeighbourhood(), newCount);
                } else {
                    boroughsToPropertyCount.put(listing.getNeighbourhood(), 1);
                }
            }
        }

        return boroughsToPropertyCount;
    }

    /***
     * Returns the highest number of property available in a borough. Not displayed.
     * @param boroughsToPropertyCount A map that maps the boroughs to the number of properties in that borough.
     * @return The highest available property count as an int.
     */
    public int getHighestPropertyCount(Map<String, Integer> boroughsToPropertyCount){
        int highestNumberOfProperties = 0;

        for(String borough : boroughsToPropertyCount.keySet()){
            int numberOfProperties = boroughsToPropertyCount.get(borough);

            if(highestNumberOfProperties < numberOfProperties){
                highestNumberOfProperties = numberOfProperties;
            }
        }

        return highestNumberOfProperties;
    }

    /***
     * This method returns a list of available properties (availability > 0) within the price range by borough.
     * @return A list of available properties within the price range by borough.
     */
    public ArrayList<AirbnbListing> getListingsInBorough(String boroughName){
        ArrayList<AirbnbListing> boroughListings = new ArrayList<>();

        for(AirbnbListing listing : listings){
            if(listing.getAvailability365() > 0){
                String listingBoroughName = listing.getNeighbourhood();

                if(boroughName.equals(listingBoroughName)){
                    boroughListings.add(listing);
                }
            }
        }

        return boroughListings;
    }

    /***
     * Calculates the average price of properties per borough. This is not displayed.
     * @param boroughsToAveragePrice A mapping that maps the borough to the average price.
     */
    private void getAveragePricePerBorough(Map<String, Double> boroughsToAveragePrice){

        HashMap<String, Integer> boroughsToBoroughCount = new HashMap<>();

        for(AirbnbListing listing : listings){
            if(boroughsToBoroughCount.containsKey(listing.getNeighbourhood())){
                int oldCount = boroughsToBoroughCount.get(listing.getNeighbourhood());
                double oldAverage = boroughsToAveragePrice.get(listing.getNeighbourhood());

                // increase count
                int newCount = oldCount + 1;
                boroughsToBoroughCount.replace(listing.getNeighbourhood(), newCount);

                // calculate new average
                double oldTotalPrice = oldAverage * oldCount;
                double newTotalPrice = oldTotalPrice + (listing.getPrice() * listing.getMinimumNights());
                double newAverage = newTotalPrice / newCount;
                boroughsToAveragePrice.replace(listing.getNeighbourhood(), newAverage);

            } else {
                boroughsToBoroughCount.put(listing.getNeighbourhood(), 1);
                boroughsToAveragePrice.put(listing.getNeighbourhood(), (double) (listing.getPrice() * listing.getMinimumNights()));
            }

        }
    }

}

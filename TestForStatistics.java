import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestForStatistics.
 *
 * @author  Guan Yi Tang
 */
public class TestForStatistics
{
    private Statistics statistics;
    /**
     * Default constructor for test class TestForStatistics
     */
    public TestForStatistics()
    {
        statistics = new Statistics();
        statistics.setPrices(0, 7000);
        statistics.loadListingsWithRange();
        statistics.updateStatistics();
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testGetTotalAvailableProperties()
    {
        assertEquals(41941, statistics.calculateTotalAvailableProperties());
    }
    
    @Test
    public void testAverageReviewPerProperty()
    {
        assertEquals(12.0, statistics.averageReviewPerProperty(), 0.0001);
    }
    
    @Test
    public void testGetMostExpensiveBorough()
    {
        assertEquals("Richmond upon Thames", statistics.calculateMostExpensiveBorough());
    }
    
    @Test
    public void testGetBoroughWithMostReviews()
    {
        assertEquals("Westminster", statistics.calculateBoroughWithMostReviews());
    }
    
    @Test
    public void testTotalHomeAndApartments()
    {
        assertEquals(27175, statistics.totalHomeAndApartments());
    }
    
    @Test
    public void testAveragePropertyPrice()
    {
        assertEquals(96, statistics.averagePropertyPrice(), 0.1);
    }
    
    @Test
    public void testPropertyWithMostMinimumNights()
    {
        assertEquals("Large double room in East Sheen, Richmond upon Thames" , statistics.propertyWithMostMinimumNights());
    }
    
    @Test
    public void testPropertyWithMostReview()
    {
        assertEquals("SOHO W1/2 THEATRELAND DESIGNER FLAT, Westminster", statistics.propertyWithMostReviews());
    }
}           

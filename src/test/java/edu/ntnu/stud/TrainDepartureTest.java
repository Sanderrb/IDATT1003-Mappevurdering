package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrainDepartureTest {
    private TrainDeparture departure;

    @BeforeEach
    void setUp() {
        departure = new TrainDeparture("10:00", "L1", "1001", "Oslo", 1);
    }

    // Positive Tests

    //Test the getters
    @Test
    void testGetters() {
        assertEquals("10:00", departure.getDepartureTime());
        assertEquals("L1", departure.getLine());
    }

    //Test the setters
    @Test
    void testSetters() {
        departure.setDepartureTime("11:00");
        assertEquals("11:00", departure.getDepartureTime());
    }
    
    //Test the formatted departure info for display
    @Test
    void testFormattedDepartureInfo() {
        TrainDeparture departure = new TrainDeparture("10:00", "L1", "1001", "Oslo", 1);
        String expectedInfo = "| 10:00 | L1 | 1001 | Oslo | | 1 |"; 
        String actualInfo = departure.getFormattedDepartureInfo().replaceAll("\\s+", " ").trim(); 
    
        assertEquals(expectedInfo, actualInfo);
    }


    // Negative Tests

    //Test setting of invalid departure time
    @Test
    void testSetInvalidDepartureTime() {
        departure.setDepartureTime("invalid-time");
        assertNotEquals("invalid-time", departure.getDepartureTime());
    }

    //Test setting of invaliv track number
    @Test
    void testSetInvalidTrack() {
        departure.setTrack(-5);
        assertNotEquals(-5, departure.getTrack());
    }

}

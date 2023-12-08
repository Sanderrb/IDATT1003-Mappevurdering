package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class TrainDispatchSystemTest {
    private TrainDispatchSystem system;

    @BeforeEach
    void setUp() {
        system = new TrainDispatchSystem();
        system.initializeTestData();
    }

    // Positive Tests

    //Test adding and searching for a new train departure
    @Test
    void testAddAndSearchTrainDeparture() {
        TrainDeparture newDeparture = new TrainDeparture("13:00", "L2", "1006", "Bergen", 2);
        assertTrue(system.addTrainDeparture(newDeparture));
        assertNotNull(system.searchByTrainNumber("1006"));
    }

    //Test setting track for a train departure
    @Test
    void testSetTrackForDeparture() {
        assertTrue(system.setTrackForDeparture("1001", 4));
    }

    //Test adding delay to a train departure
    @Test
    void testAddDelayToDeparture() {
        assertTrue(system.addDelayToDeparture("1001", "00:30"));
    }

    //Test removing train departures before a specified time
    @Test
    void testRemoveDeparturesBefore() {
        system.removeDeparturesBefore("09:00");
        assertTrue(system.getDepartures().stream()
                  .noneMatch(d -> LocalTime.parse(d.getDepartureTime()).isBefore(LocalTime.parse("09:00"))));
    }

    // Negative Tests

    //Test adding an invalid train departure
    @Test
    void testAddInvalidTrainDeparture() {
        TrainDeparture invalidDeparture = new TrainDeparture("invalid-time", "L3", "1006", "Trondheim", 3);
        assertFalse(system.addTrainDeparture(invalidDeparture));
    }

    //Test setting track for a non-existent train departure
    @Test
    void testSetTrackForNonExistentDeparture() {
        assertFalse(system.setTrackForDeparture("9999", 3));
    }

    //Test adding delay to a non-existent train departure
    @Test
    void testAddDelayToNonExistentDeparture() {
        assertFalse(system.addDelayToDeparture("9999", "01:00"));
    }

    //Test removing train departures with an invalid time 
    @Test
    void testRemoveDeparturesWithInvalidTime() {
        assertThrows(DateTimeParseException.class, () -> {
            system.removeDeparturesBefore("invalid-time");
        });
    }

    //Test setting the current time to the past
    @Test
void testSetCurrentTimeToPast() {
    system.setCurrentTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
    String pastTime = LocalTime.now().minusHours(1).format(DateTimeFormatter.ofPattern("HH:mm"));
    assertFalse(system.setCurrentTime(pastTime));
}


}

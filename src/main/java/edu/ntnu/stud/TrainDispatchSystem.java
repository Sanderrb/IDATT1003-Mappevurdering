package edu.ntnu.stud;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrainDispatchSystem {
    private List<TrainDeparture> departures;
    private LocalTime currentTime;

public TrainDispatchSystem() {
    departures = new ArrayList<>();
    this.currentTime = LocalTime.MIDNIGHT;
}

// Returens current time if format wanted
public String getCurrentTime() {
    return currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
}

//Searches after traindepartures based on their train number
public TrainDeparture searchByTrainNumber(String trainNumber) {
    for (TrainDeparture departure : departures) {
        if (departure.getTrainNumber().equals(trainNumber)) {
            return departure;
        }
    }
    return null;
}

//Searches after traindepartures based on their destination
public List<TrainDeparture> searchByDestination(String destination) {
    return departures.stream()
            .filter(departure -> departure.getDestination().equalsIgnoreCase(destination))
            .collect(Collectors.toList());
}





}

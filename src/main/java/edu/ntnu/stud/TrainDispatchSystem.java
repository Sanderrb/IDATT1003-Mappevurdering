package edu.ntnu.stud;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
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

//Method to add a new traindeparture
public boolean addTrainDeparture(TrainDeparture departure) {
    if (departures.stream().anyMatch(d -> d.getTrainNumber().equals(departure.getTrainNumber()))) {
        return false; //It returns false if the trainnumber already exists. Therefore cant be placed in the system
    }
    departures.add(departure);
    departures.sort(Comparator.comparing(TrainDeparture::getDepartureTime));
    return true;
}

//This method sets the track for a traindeparture
public boolean setTrackForDeparture(String trainNumber, int track) {
    for (TrainDeparture departure : departures) {
        if (departure.getTrainNumber().equals(trainNumber)) {
            departure.setTrack(track);
            return true;
        }
    }
    return false;
}

//This method adds a delay for a traindeparture if needed
public boolean addDelayToDeparture(String trainNumber, String delay) {
    for (TrainDeparture departure : departures) {
        if (departure.getTrainNumber().equals(trainNumber)) {
            departure.setDelay(delay);
            return true;
        }
    }
    return false;
}

//This method updates the current time
public boolean setCurrentTime(String newTimeStr) {
    LocalTime newTime = LocalTime.parse(newTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
    if (newTime.isAfter(currentTime) || newTime.equals(currentTime)) {
        currentTime = newTime;
        return true;
    }
    return false;
}



}

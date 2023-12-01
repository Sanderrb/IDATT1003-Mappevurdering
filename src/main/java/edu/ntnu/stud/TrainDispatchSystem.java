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

//This method shows all traindepartures sorted after departuretime
public void displayDepartures() {
    System.out.println("+------------+------+-------+--------------+------------+-------+");
    System.out.println("| Avgangstid | Linje | TogNr | Destinasjon  | Forsinkelse | Spor|");
    System.out.println("+------------+------+-------+--------------+------------+-------+");
    for (TrainDeparture departure : departures) {
        System.out.println(departure.getFormattedDepartureInfo());
    }
    System.out.println("+------------+------+-------+--------------+------------+-------+");
}

//This method shows traindepartures planned after the current time
public void displayDeparturesAfterCurrentTime() {
    LocalTime current = currentTime;

    List<TrainDeparture> filteredDepartures = departures.stream()
            .filter(departure -> LocalTime.parse(departure.getDepartureTime()).isAfter(current))
            .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))
            .collect(Collectors.toList());

    if (filteredDepartures.isEmpty()) {
        System.out.println("Ingen avganger etter nåværende tidspunkt.");
    } else {
        filteredDepartures.forEach(TrainDeparture::displayInformation);
    }

}
//This method removed departures before a given time. If you change the time, departures before new time are removed
public void removeDeparturesBefore(String time) {
    LocalTime cutoff = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));

    departures.removeIf(departure -> {
        LocalTime departureTime = LocalTime.parse(departure.getDepartureTime(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime adjustedDepartureTime = departureTime;

        //This part handles the delay, if there are any
        if (!"00:00".equals(departure.getDelay()) && departure.getDelay() != null) {
            String[] delayParts = departure.getDelay().split(":");
            int delayHours = Integer.parseInt(delayParts[0]);
            int delayMinutes = Integer.parseInt(delayParts[1]);
            adjustedDepartureTime = departureTime.plusHours(delayHours).plusMinutes(delayMinutes);
        }

        //This checks if the adjusted departuretime is before cutoff
        return adjustedDepartureTime.isBefore(cutoff);
    });
}

//Here i added some simple testdata that I will use when launching the main method. I may move it to another class or file later on
public void initializeTestData() {
    addTrainDeparture(new TrainDeparture("08:30", "L1", "1001", "Oslo", 1));
    addTrainDeparture(new TrainDeparture("09:45", "L2", "1002", "Bergen", 2));
    addTrainDeparture(new TrainDeparture("10:15", "L3", "1003", "Trondheim", -1));
    addTrainDeparture(new TrainDeparture("11:00", "L1", "1004", "Oslo", 3));
    addTrainDeparture(new TrainDeparture("12:30", "L2", "1005", "Bergen", -1));

    // Here I added a delay to one of the traindepartures to check its features.
    addDelayToDeparture("1003", "00:30");
}

}



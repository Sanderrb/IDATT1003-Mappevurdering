package edu.ntnu.stud;

public class TrainDeparture {
    private String departureTime;
    private String line;
    private String trainNumber;
    private String destination;
    private int track;
    private String delay;

    // Constructor
    public TrainDeparture(String departureTime, String line, String trainNumber, String destination, int track) {
        this.departureTime = departureTime;
        this.line = line;
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.track = track;
        this.delay = "00:00"; // No delay as a standard

}
// Getters
public String getDepartureTime() {
    return departureTime;
}
public String getLine() {
    return line;
}
public String getTrainNumber() {
    return trainNumber;
}
public String getDestination() {
    return destination;
}
public int getTrack() {
    return track;
}
public String getDelay() {
    return delay;
}

// Setters
public void setDepartureTime(String departureTime) {
    if (departureTime.matches("\\d{2}:\\d{2}")) {
        this.departureTime = departureTime;
    } else {
        
    }
}

public void setLine(String line) {
    this.line = line;
}

public void setTrainNumber(String trainNumber) {
    this.trainNumber = trainNumber;
}

public void setDestination(String destination) {
    this.destination = destination;
}

public void setTrack(int track) {
    if (track > 0) { 
        this.track = track;
    } else {
    }
}

public void setDelay(String delay) {
    this.delay = delay;
}

//This method creates the right format used later in the traintable
public String getFormattedDepartureInfo() {
    String trackInfo = (track == -1) ? "-" : String.format("%2d", track);
    String delayInfo = delay.equals("00:00") ? " " : String.format("%-10s", delay);

    return String.format("| %-10s | %-4s | %-5s | %-12s | %-10s | %5s |",
            departureTime, line, trainNumber, destination, delayInfo, trackInfo);
}

//Method to show the information of the traindeparture
public void displayInformation() {
    String trackInfo = (track == -1) ? "Ikke tildelt" : Integer.toString(track);
    String delayInfo = delay.equals("00:00") ? "Ingen forsinkelse" : delay;

    System.out.println("Togavgang Informasjon:");
    System.out.println("Avgangstid: " + departureTime);
    System.out.println("Linje: " + line);
    System.out.println("Tognummer: " + trainNumber);
    System.out.println("Destinasjon: " + destination);
    System.out.println("Spor: " + trackInfo);
    System.out.println("Forsinkelse: " + delayInfo);
}

}



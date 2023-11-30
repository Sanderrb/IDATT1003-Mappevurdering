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
}

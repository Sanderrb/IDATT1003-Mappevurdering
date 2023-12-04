package edu.ntnu.stud;
import java.util.List;
import java.util.Scanner;

public class TrainDispatchApp {
  
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    TrainDispatchSystem system = new TrainDispatchSystem();

    // This will initialize the system with the testdata
    system.initializeTestData();

    while (true) {
      System.out.println("\nVelg en handling:");
      System.out.println("1: Vis togavganger");
      System.out.println("2: Legg til togavgang");
      System.out.println("3: Tildele spor til togavgang");
      System.out.println("4: Legge til forsinkelse på togavgang");
      System.out.println("5: Søk etter togavgang basert på tognummer");
      System.out.println("6: Søk etter togavgang basert på destinasjon");
      System.out.println("7: Oppdatere klokken");
      System.out.println("8: Sjekk klokken");
      System.out.println("9: Avslutte applikasjonen");
      System.out.print("Ditt valg: ");

      int choice = scanner.nextInt();
      scanner.nextLine(); 

      switch (choice) {
          case 1:
              system.displayDepartures();
              break;
          case 2:
              addTrainDeparture(system, scanner);
              break;
          case 3:
              setTrackForDeparture(system, scanner);
              break;
          case 4:
              addDelayToDeparture(system, scanner);
              break;
          case 5:
              searchByTrainNumber(system, scanner);
              break;
          case 6:
              searchByDestination(system, scanner);
              break;
          case 7:
              System.out.print("Oppgi klokkeslett (tt:mm) for å fjerne avganger før og oppdatere klokken: ");
              String time = scanner.nextLine();
              if(system.setCurrentTime(time)) {
                  system.removeDeparturesBefore(time);
                  System.out.println("Togavganger før " + time + " er fjernet.");
                  System.out.println("Klokken er satt til: " + time);
              } else {
                  System.out.println("Klokken kan ikke settes til et tidligere tidspunkt enn " + system.getCurrentTime());
              }
              break;
          case 8:
              System.out.println("Nåværende tid: " + system.getCurrentTime());
              break;
          case 9:
              System.out.println("Avslutter applikasjonen.");
              scanner.close();
              return;
          default:
              System.out.println("Ugyldig valg. Prøv igjen.");
      }
  }

}

//Method that adds a traindeparture to the system
private static void addTrainDeparture(TrainDispatchSystem system, Scanner scanner) {
  System.out.print("Oppgi avgangstid (tt:mm): ");
  String time = scanner.nextLine();
  System.out.print("Oppgi linje (eks: L1, F4): ");
  String line = scanner.nextLine();
  System.out.print("Oppgi tognummer: ");
  String number = scanner.nextLine();
  System.out.print("Oppgi destinasjon: ");
  String destination = scanner.nextLine();
  int track = -1; // Standardverdi for spor

  TrainDeparture newDeparture = new TrainDeparture(time, line, number, destination, track);
  if (system.addTrainDeparture(newDeparture)) {
      System.out.println("Togavgang lagt til!");
  } else {
      System.out.println("Togavgang med gitt nummer finnes allerede.");
  }
}

//This method gives a traindepature a track
private static void setTrackForDeparture(TrainDispatchSystem system, Scanner scanner) {
  System.out.print("Oppgi tognummer for å tildele spor: ");
  String number = scanner.nextLine();
  System.out.print("Oppgi spornummer: ");
  int track = scanner.nextInt();
  scanner.nextLine(); 

  if (system.setTrackForDeparture(number, track)) {
      System.out.println("Spor tildelt!");
  } else {
      System.out.println("Ingen togavgang funnet med gitt tognummer.");
  }
}

//Thism method adds a delay to a departure
private static void addDelayToDeparture(TrainDispatchSystem system, Scanner scanner) {
  System.out.print("Oppgi tognummer for å registrere forsinkelse: ");
  String number = scanner.nextLine();
  System.out.print("Oppgi forsinkelse (HH:MM): ");
  String delay = scanner.nextLine();

  if (system.addDelayToDeparture(number, delay)) {
      System.out.println("Forsinkelse registrert!");
  } else {
      System.out.println("Ingen togavgang funnet med gitt tognummer.");
  }
}

//This Method allows the user to search after traindeparture basen on the train number
private static void searchByTrainNumber(TrainDispatchSystem system, Scanner scanner) {
  System.out.print("Oppgi tognummer: ");
  String number = scanner.nextLine();

  TrainDeparture departure = system.searchByTrainNumber(number);
  if (departure != null) {
      departure.displayInformation();
  } else {
      System.out.println("Ingen togavgang funnet med gitt tognummer.");
  }
}

//This method allows the user to search a traindeparture based on its destination
private static void searchByDestination(TrainDispatchSystem system, Scanner scanner) {
  System.out.print("Oppgi destinasjon: ");
  String destination = scanner.nextLine();

  List<TrainDeparture> departures = system.searchByDestination(destination);
  if (!departures.isEmpty()) {
      departures.forEach(TrainDeparture::displayInformation);
  } else {
      System.out.println("Ingen togavganger funnet til gitt destinasjon.");
  }
}
}



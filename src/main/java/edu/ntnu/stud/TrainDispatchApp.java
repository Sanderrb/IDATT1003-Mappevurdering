package edu.ntnu.stud;
import java.util.List;
import java.util.Scanner;

public class TrainDispatchApp {
  
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    TrainDispatchSystem system = new TrainDispatchSystem();

    // This will initialize teh system with the testdata
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
              //addTrainDeparture(system, scanner);
              break;
          case 3:
              //setTrackForDeparture(system, scanner);
              break;
          case 4:
              //addDelayToDeparture(system, scanner);
              break;
          case 5:
              //searchByTrainNumber(system, scanner);
              break;
          case 6:
              //searchByDestination(system, scanner);
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






}

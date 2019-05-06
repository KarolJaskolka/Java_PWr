package lab03_pop;

import java.io.FileNotFoundException;
//import java.time.LocalDate;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(System.in);
		int choice = 1;
		
		App app = new App();
		app.loadData();
		showMenu();
		while(choice !=0) {		
			System.out.print("Wybór: ");
			choice = in.nextInt();		
			switch(choice) {
				case 0:
					break;
				case 1:
					app.addNewClient();
					break;
				case 2:
					app.addNewTrip();
					break;
				case 3:
					app.addNewBooking();
					break;
				case 4:
					app.editTrip();
					break;
				case 5:
					app.removeTrip();
					break;
				case 6:
					app.showMostPopularTrips();
					break;
				case 7:
					app.showClientsOrderByTrips();
					break;
				case 8:
					app.cancelBooking();
					break;
				case 9:
					app.payForBooking();
					break;
				case 10:
					app.getClientsInfo();
					break;
				case 11:
					app.getTripsInfo();
					break;
				case 12:
					app.getBookingsInfo();
					break;
				case 13:
					app.getTripsWithEmptyPlacesInfo();
					break;
				case 14:
					app.simulation();
				default:					
					break;
				
			}
		}
		
		app.saveData();
		app.closeScanner();
		in.close();
	}
	public static void showMenu() {
		System.out.println("0. Wyjdz");
		System.out.println("1. Dodaj nowego klienta");	
		System.out.println("2. Dodaj now¹ wycieczkê");	
		System.out.println("3. Dodaj now¹ rezerwacjê");	
		System.out.println("4. Edytuj wycieczkê");	
		System.out.println("5. Anuluj wycieczkê");	
		System.out.println("6. Wyœwietl wycieczki wed³ug popularnoœci");
		System.out.println("7. Wyœwietl klientów wed³ug iloœci wycieczek");	
		System.out.println("8. Anuluj rezerwacjê");	
		System.out.println("9. Op³aæ rezerwacjê");	
		System.out.println("10. Lista klientów");	
		System.out.println("11. Lista wycieczek");	
		System.out.println("12. Lista rezerwacji");	
		System.out.println("13. Wyœwietl wycieczki z wolnymi miejscami");	
		System.out.println("14. Symuluj do dnia");	
	}
}

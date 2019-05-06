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
			System.out.print("Wyb�r: ");
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
		System.out.println("2. Dodaj now� wycieczk�");	
		System.out.println("3. Dodaj now� rezerwacj�");	
		System.out.println("4. Edytuj wycieczk�");	
		System.out.println("5. Anuluj wycieczk�");	
		System.out.println("6. Wy�wietl wycieczki wed�ug popularno�ci");
		System.out.println("7. Wy�wietl klient�w wed�ug ilo�ci wycieczek");	
		System.out.println("8. Anuluj rezerwacj�");	
		System.out.println("9. Op�a� rezerwacj�");	
		System.out.println("10. Lista klient�w");	
		System.out.println("11. Lista wycieczek");	
		System.out.println("12. Lista rezerwacji");	
		System.out.println("13. Wy�wietl wycieczki z wolnymi miejscami");	
		System.out.println("14. Symuluj do dnia");	
	}
}

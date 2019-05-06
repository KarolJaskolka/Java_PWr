package lab03_pop;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App {
	
	// listy z danymi
	public ArrayList<Client> clientsList = new ArrayList<>();
	public ArrayList<Trip> tripsList  = new ArrayList<>();
	public ArrayList<Booking> bookingsList  = new ArrayList<>();
	// scanner
	Scanner in = new Scanner(System.in);
	
	public App() {}
	
	// dodanie klienta do bazy
	public void addNewClient() {
		
		String name,surname;
		System.out.print("Imie: ");
		name = in.nextLine();
		System.out.print("Nazwisko: ");
		surname = in.nextLine();
		clientsList.add(new Client(name,surname));
		
	}
	// dodanie wycieczki do bazy
	public void addNewTrip() {
		String direction,departure,arrival;
		int seats;
		
		System.out.print("Kierunek wycieczki: ");
		direction = in.nextLine();
		System.out.print("Wyjazd(YYYY-MM-DD): ");
		departure = in.nextLine();
		System.out.print("Przyjazd(YYYY-MM-DD): ");
		arrival = in.nextLine();
		System.out.print("Miejsca: ");
		seats = in.nextInt();

		tripsList.add(new Trip(direction,seats,departure,arrival));
	}
	// edycja wycieczki
	public void editTrip() {
		
		String direction,departure,arrival;
		int seats;
		getTripsInfo();
		
		int choice=1;
		System.out.print("Wybierz numer wycieczki na liscie ktora chcesz edytowac : ");
		choice = in.nextInt();
		// scanner przechodzil pierwsze nextLine po uzyciu nextInt
		in.nextLine();
		System.out.print("Kierunek wycieczki: ");
		direction = in.nextLine();
		System.out.print("Wyjazd(YYYY-MM-DD): ");
		departure = in.nextLine();
		System.out.print("Przyjazd(YYYY-MM-DD): ");
		arrival = in.nextLine();
		System.out.print("Miejsca: ");
		seats = in.nextInt();

		tripsList.get(choice-1).updateTrip(direction, seats, departure, arrival);
	}
	// dodanie rezerwacji
	public void addNewBooking() {
		
		int indexClient,indexTrip;

		getClientsInfo();
		getTripsInfo();
		
		System.out.print("Wybierz numer klienta na liœcie :");
		indexClient = in.nextInt();
		System.out.print("Wybierz numer wycieczki na liœcie :");
		indexTrip = in.nextInt();


		if(tripsList.get(indexTrip-1).areEmptyPlaces() && tripsList.get(indexTrip-1).isAvailable() == true) {
		
		bookingsList.add(new Booking(clientsList.get(indexClient-1),tripsList.get(indexTrip-1),
									tripsList.get(indexTrip-1).getDayBeforeDeparture(),false));
		
		clientsList.get(indexClient-1).updateNumberOfTrips(1);
		tripsList.get(indexTrip-1).updateUsedPlaces();
			if(tripsList.get(indexTrip-1).getUsedPlaces() == tripsList.get(indexTrip-1).getSeats()) {
				tripsList.get(indexTrip-1).setEmptyPlaces(false);
			}
		}
		else {
			System.out.println("Brak wolnych miejsc");
		}

		System.out.println("----------------");
	}
	// anulowanie wycieczki
	public void removeTrip() {
		getTripsInfo();
		int indexTrip;
		System.out.print("Wybierz numer wycieczki na liœcie która chcesz anulowac:");
		indexTrip = in.nextInt();
		int tripID = tripsList.get(indexTrip-1).getTripID();
		// anulowanie rezerwacji na usunieta wycieczke 
		// (oraz zmniejszenie iloœci wycieczek osoby z rezerwacja)
		tripsList.get(indexTrip-1).setAvailable(false);
		cancelBookings(tripID);
	}
	
	//symulacja do dnia
	public void simulation() {
		System.out.print("Podaj dzieñ(YYYY-MM-DD): ");
		String date = in.nextLine();
		LocalDate current;
		try {
			 current = LocalDate.parse(date);
		}catch(DateTimeParseException ex) {
			current = LocalDate.now();
		}
		for(Trip trip: tripsList) {
			if(trip.departure.compareTo(current) < 0) {
				trip.setAvailable(false); // wycieczka nie dostêpna juz odby³ sie wyjazd
			}
		}
		for(Booking booking: bookingsList) {
			if(booking.trip.departure.compareTo(current) < 0) {
				booking.setHappend(true); // ustawienie w rezerwacji, ¿e zarezerwowana wycieczka zosta³a odbyta
			}
		}
		System.out.println("Symulacja do dnia " + date + " zakoñczona");
	}
	
	// zap³acenie za rezerwacje
	public void payForBooking() {
		getBookingsInfo();
		int index;
		System.out.print("Wybierz numer rezerwacji na liœcie ktor¹ chcesz op³aciæ:");
		index = in.nextInt();
		bookingsList.get(index - 1).setPaid(true);
	}
	// anulowanie rezerwacji
	public void cancelBooking() {
		getBookingsInfo();
		int index;
		System.out.print("Wybierz numer rezerwacji na liœcie ktor¹ chcesz anulowaæ:");
		index = in.nextInt();
		bookingsList.get(index - 1).setCancelled(true);
	}
	// anulowanie wielu rezerwacji podczas anulowania wycieczki z listy aktywnych
	private void cancelBookings(int tripID) {
		for(Booking booking: bookingsList) {
			if(booking.trip.getTripID() == tripID) {
				booking.setCancelled(true);
				booking.client.updateNumberOfTrips(-1);
			}
		}
	}
	// wyœwietlenie wycieczek wed³ug popularnoœci
	public void showMostPopularTrips() {
		// posortowanie wed³ug zajêtych miejsc
		Collections.sort(tripsList,(first,second)-> second.getUsedPlaces() - first.getUsedPlaces());
		System.out.println("Wycieczki wed³ug popularnosci: ");
		int i=1;
		for(Trip trip : tripsList) {
			System.out.print(i+". ");
			System.out.println(trip);
			i++;
		}
		System.out.println("----------------");
		// posortowanie wed³ug ID
		Collections.sort(tripsList,(first,second)-> first.getTripID() - second.getTripID());
	}
	// wyœwietlenie klientów wed³ug iloœc wycieczek na które siê zapisali
	public void showClientsOrderByTrips() {
		// posortowanie wed³ug ilosci wycieczek
		Collections.sort(clientsList,(first,second)-> second.getNumberOfTrips() - first.getNumberOfTrips());
		System.out.println("Klienci wed³ug ilosc rezerwacji: ");
		int i=1;
		for(Client client : clientsList) {
			System.out.print(i+". ");
			System.out.println(client);
			i++;
		}
		System.out.println("----------------");
		// posortowanie wed³ug ID
		Collections.sort(clientsList,(first,second)-> first.getClientID() - second.getClientID());
	}
	// wyœwietlenie tylko wycieczek z wolnymi miejscami
	public void getTripsWithEmptyPlacesInfo() {
		System.out.println("Lista wycieczek z wolnymi miejscami :");
		int i=1;
		for(Trip trip : tripsList) {
			
			if(trip.areEmptyPlaces() == true) {
				System.out.print(i+". ");
				System.out.println(trip);
			}
			i++;
		}
		System.out.println("----------------");
	}
	// odczyt danych z plików
	public void loadData() throws FileNotFoundException {
		//pliki
		File file = new File("client.txt");
		File file2 = new File("trip.txt");
		File file3 = new File("booking.txt");
				
		Scanner in1 = new Scanner(file);
		Scanner in2 = new Scanner(file2);
		Scanner in3 = new Scanner(file3);
				
		String data = "";

		// klienci
		while(in1.hasNext()) {
			data = in1.nextLine();
			String[] dataSplit = data.split(";");
			clientsList.add(new Client(dataSplit[0],dataSplit[1]));
			data = "";
		}
		// wycieczki
		while(in2.hasNext()) {
			data = in2.nextLine();
			String[] dataSplit = data.split(";");
			tripsList.add(new Trip(dataSplit[0],Integer.parseInt(dataSplit[1]),dataSplit[2],dataSplit[3]));
			
			data = "";
		}
		// wycieczki
		while(in3.hasNext()) {
			
			data = in3.nextLine();
			String[] dataSplit = data.split(";");

			int indexClient = Integer.parseInt(dataSplit[0]);
			int indexTrip = Integer.parseInt(dataSplit[1]);
			int paid = Integer.parseInt(dataSplit[2]);
			
			boolean isPaid = false;
			if(paid == 1) {isPaid = true;}
				else {isPaid = false; }
	
			bookingsList.add(new Booking(clientsList.get(indexClient-1),tripsList.get(indexTrip-1),
										tripsList.get(indexTrip-1).getDayBeforeDeparture(),isPaid));
			
			clientsList.get(indexClient-1).updateNumberOfTrips(1);
			tripsList.get(indexTrip-1).updateUsedPlaces();
			if(tripsList.get(indexTrip-1).getUsedPlaces() == tripsList.get(indexTrip-1).getSeats()) {
				tripsList.get(indexTrip-1).setEmptyPlaces(false);
			}
			data = "";
		}
		
		in1.close();
		in2.close();
		in3.close();
	}
	// zapis danych do plików
	public void saveData() throws FileNotFoundException{
		
		PrintWriter zapis1 = new PrintWriter("client.txt");
		PrintWriter zapis2 = new PrintWriter("trip.txt");
		PrintWriter zapis3 = new PrintWriter("booking.txt");
		
		for(Client client: clientsList) {
			zapis1.println(client.getName() +";" + client.getSurname());
		}
		for(Trip trip: tripsList) {
			zapis2.println(trip.getDirection() +";" + trip.getSeats()+";" + trip.departure +";" + trip.arrival);
		}
		int paid;
		for(Booking booking: bookingsList) {
			if(booking.isPaid()) {paid=1;}
				else {paid=0;}
			zapis3.println(booking.client.getClientID() +";" + booking.trip.getTripID()+";" + paid);
		}
		
		zapis1.close();
		zapis2.close();
		zapis3.close();
	}
	// wyœwietlenie informacji o klientach
	public void getClientsInfo() {
		System.out.println("Lista klientow :");
		int i=1;
		for(Client client : clientsList) {
			System.out.print(i+". ");
			System.out.println(client);
			i++;
		}
		System.out.println("----------------");
	}
	// wyœwietlenie informacji o wycieczkach
	public void getTripsInfo() {
		System.out.println("Lista wycieczek :");
		int i=1;
		for(Trip trip : tripsList) {
			System.out.print(i+". ");
			System.out.println(trip);
			i++;
		}
		System.out.println("----------------");
	}
	// wyœwietlenie informacji o rezerwacjach
	public void getBookingsInfo() {
		System.out.println("Lista rezerwacji :");
		int i=1;
		for(Booking booking : bookingsList) {
			System.out.print(i+". ");
			System.out.println(booking);
			i++;
		}
		System.out.println("----------------");
	}
	public void closeScanner() {
		in.close();
	}
}

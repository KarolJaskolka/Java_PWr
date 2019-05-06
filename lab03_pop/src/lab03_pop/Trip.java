package lab03_pop;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
//import java.util.ArrayList;

public class Trip {
	
	private int tripID;
	
	private int seats; // miejsca
	private int usedPlaces; // zajête miejsca
	private String direction; // kierunek wycieczki
	public LocalDate departure; // data wyjazdu
	public LocalDate arrival; // data przyjazdu
	private boolean available; // czy dostêpna ( nie jeœli sie odby³a albo zosta³a usunieta z listy aktywnych)
	private boolean emptyPlaces; // czy s¹ wolne miejsca
	public static int ID=1;
	
	public Trip(String direction, int seats,  String departure, String arrival) {
		this.tripID = ID;
		this.seats = seats;
		this.direction = direction;
		try {
			this.departure = LocalDate.parse(departure); // YYYY-MM-DD
			this.arrival = LocalDate.parse(arrival); // YYYY-MM-DD
		}catch(DateTimeParseException ex) {
			// ustawienie domyœlnych dat jeœli u¿ytkownik poda³ nieprawid³owy format daty
			this.departure = LocalDate.now(); 
			this.arrival = LocalDate.now().plusDays(7);
		}

		available = true;
		emptyPlaces = true;
		usedPlaces = 0;
		ID++;
	}
	public void updateTrip(String direction, int seats,  String departure, String arrival) {
		this.seats = seats;
		this.direction = direction;
		this.departure = LocalDate.parse(departure); // YYYY-MM-DD
		this.arrival = LocalDate.parse(arrival); // YYYY-MM-DD
	}
	public int getTripID() {
		return tripID;
	}

	public void setTripID(int tripID) {
		this.tripID = tripID;
	}

	public int getUsedPlaces() {
		return usedPlaces;
	}

	public void updateUsedPlaces() {
		this.usedPlaces = usedPlaces + 1;
	}

	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public boolean areEmptyPlaces() {
		return emptyPlaces;
	}
	public void setEmptyPlaces(boolean emptyPlaces) {
		this.emptyPlaces = emptyPlaces;
	}
	public String getDayBeforeDeparture() {
		return departure.minusDays(1).toString();
	}
	public String toString() {
		return "ID: "+ tripID + " Kierunek: " + direction + " Miejsca: " + seats + " Zajete: "+ usedPlaces +" Wyjazd: " + departure 
				+ " Przyjazd: " + arrival + " Uczestnicy: " + usedPlaces + " Aktywna: " + available;
	}
}

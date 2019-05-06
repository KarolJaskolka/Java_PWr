package lab03_pop;

public class Client {
	
	private int clientID;
	private String name;
	private String surname;
	private int numberOfTrips; // iloœæ wycieczek w których bierze udzia³
	public static int ID = 1;
	
	public Client(String name, String surname) {
		this.clientID = ID;
		this.name = name;
		this.surname = surname;
		numberOfTrips = 0;
		ID++;
	}
	
	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public int getNumberOfTrips() {
		return numberOfTrips;
	}

	public void updateNumberOfTrips(int number) {
		this.numberOfTrips = numberOfTrips + number;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String toString() {
		return "ID: " + clientID + " Dane: " + name + " " + surname + " Wycieczki: " + numberOfTrips;
	}
}

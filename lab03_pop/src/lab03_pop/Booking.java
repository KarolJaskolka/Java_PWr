package lab03_pop;

import java.time.LocalDate;

public class Booking {
	
	private int bookingID;
	public Client client; 
	public Trip trip;
	public LocalDate paymentDeadline; // termin p³atnoœci
	public boolean paid; // czy zap³acono
	public boolean happend; // czy wycieczka z rezerwacji siê odby³a
	public boolean cancelled; // czy anulowano rezerwacje
	public static int ID = 1;
	
	public Booking(Client client, Trip trip, String paymentDeadline, boolean paid) {
		this.bookingID = ID;
		this.client = client;
		this.trip = trip;
		this.paid = paid;
		this.happend = false;
		this.cancelled = false;
		this.paymentDeadline = LocalDate.parse(paymentDeadline);
		ID++;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isHappend() {
		return happend;
	}

	public void setHappend(boolean happend) {
		this.happend = happend;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	public String toString() {
		return "ID: "+bookingID+" KlientID: " + client.getClientID() + " WycieczkaID: " + trip.getTripID() + 
		" Termin platnosci: " + paymentDeadline + " Zaplacono:" + paid + " Odbyta:" + happend+ " Anulowoano:" + cancelled;
	}
}

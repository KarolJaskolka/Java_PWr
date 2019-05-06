package lab06_pop;

import java.net.Socket;

public class Kurier implements Runnable {
	
	private boolean available;
	private String name;
	private String packageType;

	
	public Kurier(String name, String packageType) {
		this.name = name;
		this.packageType = packageType;
		this.available = true;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(available == false) {
				setAvailable(true);
			}
			
		}
	}
	
	public String toString() {
		return "Kurier: " + name + "  Type: " + packageType + "  status: " + available;
	}
	
	public String getPackageType() {
		return packageType;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
	
}

package lab06_pop;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class WDyspozytor extends JFrame implements Runnable {
	
	private static final int PORT = 4444;
	private ServerSocket serverSocket;
	private Socket socket;
	private Queue<String> packages = new ArrayDeque<>();
	private List<Kurier> kurierList = new ArrayList<>();
	private JToggleButton buttonListen;
	private JButton buttonSetCapacity;
	private JTextArea textArea;
	private JTextArea textStorage;
	private JTextArea textKurier;
	private JTextField textCapacity;
	private JScrollPane scroll;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	JSlider slider = new JSlider(1,9);
	private int storageCapacity = 5;
	
	public WDyspozytor() {

		setSize(815,600);
		setTitle("Dyspozytor");
		setLayout(null);
		
		buttonListen = new JToggleButton("Listen");
		buttonListen.setEnabled(false);
		buttonListen.setBounds(50, 50, 100, 25);
		buttonListen.addActionListener(e->buttonListenClicked());
		add(buttonListen);
		
		buttonSetCapacity = new JButton("Set");
		buttonSetCapacity.setBounds(350, 50, 100, 25);
		buttonSetCapacity.addActionListener(e->buttonSetCapacityClicked());
		add(buttonSetCapacity);
		
		textArea = new JTextArea("");
		//textArea.setBounds(50,100,400,400);
		scroll = new JScrollPane(textArea);
		scroll.setBounds(50,125,159,300);
		add(scroll);
		
		textStorage = new JTextArea("");
		textStorage.setBounds(250,125,150,300);
		add(textStorage);
		
		textKurier = new JTextArea("");
		textKurier.setBounds(450,125,300,300);
		add(textKurier);
		
		label1 = new JLabel("Notifications");
		label1.setBounds(100,100,100,25);
		add(label1);

		label2 = new JLabel("Storage");
		label2.setBounds(300,100,100,25);
		add(label2);
		
		label3 = new JLabel("List");
		label3.setBounds(550,100,100,25);
		add(label3);
		
		
		Font font = new Font("SansSerif",Font.BOLD,10);
		slider.setValue(5);
		slider.setBounds(175, 25, 150, 50);
		slider.setValue(5);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(4);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setFont(font);
		slider.addChangeListener(e->setCapacity());
		add(slider);
	}
	
	
	public static void main(String[] args) {
			
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					WDyspozytor window = new WDyspozytor();
					window.setVisible(true);
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public void run() {
		try {
			
			serverSocket = new ServerSocket(PORT);
			while (true) {
				
				socket = serverSocket.accept();
				showKurierList();
				InputStreamReader isr = new InputStreamReader(socket.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String text = br.readLine();
				textArea.append(text + "\n");
				PrintWriter print = new PrintWriter(socket.getOutputStream());

				switch(action(text)) {
					case 0:
						print.println("POPRZEDNIA PRZESYLKA WYSLANA, NOWA W MAGAZYNIE");
					break;
					case 1:
						print.println("PRZESYLKA WYSLANA");
						break;
					case 2:
						print.println("PRZESY£KA W MAGAZYNIE");
						break;
					case 3:
						print.println("NIE MOZNA PRZYJAC ZLECENIA");
						break;
				}
				showKurierList();
				print.flush();
				print.close();
				socket.close();

			}
		}   catch(SocketException e){
			// TODO - podczas przerywania w¹tku metoda accept zg³osi wyj¹tek
			// z wiadomoœci¹: socket closed
		}
			catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void buttonListenClicked() {
		
		kurierList.add(new Kurier("1","LETTER"));
		kurierList.add(new Kurier("2","PACKAGE"));
		kurierList.add(new Kurier("3","LETTER"));
		kurierList.add(new Kurier("4","PACKAGE"));
		
		Thread thread = new Thread(this);
		Thread thread2 = new Thread(kurierList.get(0));
		Thread thread3 = new Thread(kurierList.get(1));
		Thread thread4 = new Thread(kurierList.get(2));
		Thread thread5 = new Thread(kurierList.get(3));
		
		thread.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		
	}
	public void buttonSetCapacityClicked() {

		buttonSetCapacity.setEnabled(false);
		buttonListen.setEnabled(true);
		slider.setEnabled(false);
	}
	public void setCapacity() {
		storageCapacity = slider.getValue();
	}
	public void showKurierList() {
		textKurier.setText("");
		for(Kurier kurier : kurierList) {
			textKurier.append(kurier + "\n");
		}
	}
	public int action(String text) {
		
		String[] msg = text.split(";");
		// 0 -> paczka w magazynie + kurier dostepny
		if(isWaiting(text)) {
			return 0;
		}
		// 1 -> kurier dostepny + przychodzaca paczka
		else if(isKurierAvailable(msg[1])) {
			return 1;
		}
		// 2 -> brak kurierów + miejsce w magazynie
		else if(packages.size() < storageCapacity) {
			packages.add(text);
			textStorage.append(text + "\n");
			return 2;
		}
		// 3 -> brak kurierów + brak miejsca w magazynie
		else {
			return 3;
		}
		
	}
	public boolean isWaiting(String text) {
		
		if(packages.size() > 0) {

			String[] stg = packages.peek().split(";");
			if(isKurierAvailable(stg[1])) {

				packages.remove();
				packages.add(text);
				storageUpdate();
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}

	}
	
	public boolean isKurierAvailable(String type) {
		for(Kurier kurier : kurierList) {
			if(kurier.getPackageType().equals(type) && kurier.isAvailable() == true) {
				kurier.setAvailable(false);
				return true;
			}
		}
		return false;
	}
	
	public void storageUpdate() {
		textStorage.setText("");
		for(String str: packages) {
			textStorage.append(str + "\n");
		}
	}
	
}

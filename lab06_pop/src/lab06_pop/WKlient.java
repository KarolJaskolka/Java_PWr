package lab06_pop;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.*;

public class WKlient extends JFrame implements Runnable {
	

	private static final String HOST = "localhost";
	private static final int PORT = 4444;
	private Socket socket;
	private String name = "Name";
	private Random rand = new Random();
	
	private JToggleButton buttonStart;
	private JTextArea textArea;
	private JButton buttonConfirm;
	private JTextField  textName;
	private JScrollPane scroll;
	
	
	public WKlient() {
		
		setSize(400,600);
		setTitle("Klient");
		setLayout(null);

		buttonConfirm = new JButton("Confirm");
		buttonConfirm.setBounds(250, 50, 100, 25);
		buttonConfirm.addActionListener(e->buttonConfirmClicked());
		add(buttonConfirm);
		
		
		buttonStart = new JToggleButton("Start");
		buttonStart.setEnabled(false);
		buttonStart.setBounds(25, 50, 75, 25);
		buttonStart.addActionListener(e->buttonStartClicked());
		add(buttonStart);
		
		textArea = new JTextArea("");
		//textArea.setBounds(50,100,400,400);
		scroll = new JScrollPane(textArea);
		scroll.setBounds(25,100,325,400);
		add(scroll);
		//add(textArea);
		
		textName = new JTextField("Klient");
		textName.setBounds(125,50,100,25);
		add(textName);
	}
	
	
	public static void main(String[] args) {
			
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					WKlient window = new WKlient();
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
		while(true) {

			try {
				Thread.sleep(1250);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				socket = new Socket(HOST,PORT);
				PrintWriter print = new PrintWriter(socket.getOutputStream());
				if(rand.nextInt(2)>0) {
					print.println(name +";LETTER");
				}
				else {
					print.println(name +";PACKAGE");
				}
				
				print.flush();
				InputStreamReader isr = new InputStreamReader(socket.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String text = br.readLine();
				textArea.append(text+"\n");
				socket.close();

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	public void buttonStartClicked() {
		Thread thread = new Thread(this);
		thread.start();
	}
	public void buttonConfirmClicked() {
		name = textName.getText();
		buttonStart.setEnabled(true);
		buttonConfirm.setEnabled(false);
		textName.setEditable(false);
	}
}

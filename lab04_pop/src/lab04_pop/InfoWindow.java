package lab04_pop;

import javax.swing.*;

public class InfoWindow extends JFrame {
	
	private JLabel labelScore = new JLabel();
	
	public InfoWindow(int score,int max) {
		
		setSize(250,175);
		setLocationRelativeTo(null);
		setTitle("");
		setLayout(null);
		
		labelScore.setText("Wynik : " + score + "/" + max);
		labelScore.setBounds(75, 30, 125, 50);
		
		add(labelScore);
		
	}
}

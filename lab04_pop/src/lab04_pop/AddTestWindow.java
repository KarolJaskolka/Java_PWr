package lab04_pop;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

public class AddTestWindow extends JFrame{
	
	ArrayList<Test> tests = new ArrayList<>();
	JButton buttonAdd;
	JLabel label;
	JTextField textField;
	
	public AddTestWindow(ArrayList<Test> tests){
		
		this.tests = tests;
		setSize(450,225);
		setLocationRelativeTo(null);
		setTitle("Dodanie nowego testu");
		setLayout(null);
		
		buttonAdd = new JButton("Dodaj");
		label = new JLabel("Podaj nazwe nowego testu: ");
		textField = new JTextField();
		
		buttonAdd.setBounds(300, 100, 100, 50);
		label.setBounds(25, 25, 175, 50);
		textField.setBounds(200, 25, 200, 50);
		
		buttonAdd.addActionListener(e->addTest());
		setButtonsColor(196, 212, 239);
		add(buttonAdd);
		add(label);
		add(textField);
		
	}
	public void addTest() {
		if(textField.getText().equals("")) {
			tests.add(new Test("Nowy test"));
		}
		else {
			tests.add(new Test(textField.getText()));
		}
		dispose();
	}
	public void setButtonsColor(int r ,int g, int b) {
		buttonAdd.setBackground(new Color(r, g, b));
	}
}

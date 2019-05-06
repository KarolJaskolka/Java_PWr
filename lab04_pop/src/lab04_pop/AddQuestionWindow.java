package lab04_pop;

import java.awt.Color;

import javax.swing.*;

public class AddQuestionWindow extends JFrame {
	
	// test
	Test test;
	
	// napisy
	private JLabel labelTextQuestion;
	private JLabel labelTextA;
	private JLabel labelTextB;
	private JLabel labelTextC;
	private JLabel labelTextD;
	private JLabel labelCorrectAnswer;
	
	// miejsca do wpisania
	private JTextField textQuestion;
	private JTextField textA;
	private JTextField textB;
	private JTextField textC;
	private JTextField textD;

	// przyciski
	private JButton buttonSave;
	
	// radioButtons
	private JRadioButton radioButtonA;
	private JRadioButton radioButtonB;
	private JRadioButton radioButtonC;
	private JRadioButton radioButtonD;
	
	public AddQuestionWindow(Test test) {
		this.test = test;
		
		setSize(600,600);
		setLocationRelativeTo(null);
		setTitle("Dodanie pytania do testu :  " + test.getName());
		setLayout(null);

		labelTextQuestion = new JLabel("Pytanie: ");
		labelTextA = new JLabel("Odpowiedz A: ");
		labelTextB = new JLabel("Odpowiedz B: ");
		labelTextC = new JLabel("Odpowiedz C: ");
		labelTextD = new JLabel("Odpowiedz D: ");
		labelCorrectAnswer = new JLabel("Poprawna : ");
		// napisy
		textQuestion = new JTextField();
		textA = new JTextField();
		textB = new JTextField();
		textC = new JTextField();
		textD = new JTextField();

		// przyciski
		buttonSave = new JButton("Save");
		radioButtonA = new JRadioButton("A");
		radioButtonB = new JRadioButton("B");
		radioButtonC = new JRadioButton("C");
		radioButtonD = new JRadioButton("D");
		// dodanie do grupy
		ButtonGroup group = new ButtonGroup();
		group.add(radioButtonA);
		group.add(radioButtonB);
		group.add(radioButtonC);
		group.add(radioButtonD);
		// ustawienie na ekranie
		// labele
		labelTextQuestion.setBounds(50, 75, 100, 50);
		labelTextA.setBounds(50, 150, 100, 50);
		labelTextB.setBounds(50, 225, 100, 50);
		labelTextC.setBounds(50, 300, 100, 50);
		labelTextD.setBounds(50, 375, 100, 50);
		labelCorrectAnswer.setBounds(50, 450, 100, 50);
		// napisy
		textQuestion.setBounds(175, 75, 350, 50);
		textA.setBounds(175, 150, 350, 50);
		textB.setBounds(175, 225, 350, 50);
		textC.setBounds(175, 300, 350, 50);
		textD.setBounds(175, 375, 350, 50);

		// przyciski
		buttonSave.setBounds(425, 450, 100, 50);
		radioButtonA.setBounds(200, 450, 50, 50);
		radioButtonB.setBounds(250, 450, 50, 50);
		radioButtonC.setBounds(300, 450, 50, 50);
		radioButtonD.setBounds(350, 450, 50, 50);
		radioButtonA.setSelected(true);
		buttonSave.addActionListener(e->save());

		setButtonsColor(196, 212, 239);
		
		add(labelTextQuestion);
		add(labelTextA);
		add(labelTextB);
		add(labelTextC);
		add(labelTextD);
		add(labelCorrectAnswer);
		add(textQuestion);
		add(textA);
		add(textB);
		add(textC);
		add(textD);

		add(buttonSave);
		add(radioButtonA);
		add(radioButtonB);
		add(radioButtonC);
		add(radioButtonD);
		
	}
	// metoda zatwierdzaj¹ca dodanie nowego pytania
	public void save() {
		
		String correctAnswer = "A";
		// przechwycenie poprawnej odpowiedzi z radioButton'ów
		if(radioButtonA.isSelected()) correctAnswer = "A";
		if(radioButtonB.isSelected()) correctAnswer = "B";
		if(radioButtonC.isSelected()) correctAnswer = "C";
		if(radioButtonD.isSelected()) correctAnswer = "D";
		
		test.addQuestion(textQuestion.getText(), textA.getText(), textB.getText(), textC.getText(), textD.getText(), correctAnswer);
		dispose();
		
	}
	// metoda ustawiaj¹ca kolory przycisków
	public void setButtonsColor(int r ,int g, int b) {
		buttonSave.setBackground(new Color(r, g, b));
	}
}


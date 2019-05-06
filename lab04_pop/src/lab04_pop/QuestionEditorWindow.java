package lab04_pop;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

public class QuestionEditorWindow extends JFrame {
	
	// Wybrany test
	public Test test;

	// miejsca do wpisania
	private JTextField textQuestion;
	private JTextField textA;
	private JTextField textB;
	private JTextField textC;
	private JTextField textD;
	
	// napisy
	private JLabel labelTextQuestion;
	private JLabel labelTextA;
	private JLabel labelTextB;
	private JLabel labelTextC;
	private JLabel labelTextD;
	private JLabel labelCorrectAnswer;

	// przyciski
	private JButton buttonConfirm;
	private JButton buttonNext;
	private JButton buttonPrevious;
	
	// radioButton
	private JRadioButton radioButtonA;
	private JRadioButton radioButtonB;
	private JRadioButton radioButtonC;
	private JRadioButton radioButtonD;
	
	// numer pytania
	int index = 0;
	// poprawna odpowiedz
	private String correct;
	// grupa RadioButtonów
	ButtonGroup group; 
	
	public QuestionEditorWindow(Test test) {
		
		this.test = test;
		
		setSize(600,600);
		setTitle("Edycja pytañ z testu :  " + test.getName());
		setLayout(null);
		setLocationRelativeTo(null);
		
		textQuestion = new JTextField("");
		textA = new JTextField("");
		textB = new JTextField("");
		textC = new JTextField("");
		textD = new JTextField("");

		labelTextQuestion = new JLabel("Pytanie: ");
		labelTextA = new JLabel("Odpowiedz A: ");
		labelTextB = new JLabel("Odpowiedz B: ");
		labelTextC = new JLabel("Odpowiedz C: ");
		labelTextD = new JLabel("Odpowiedz D: ");
		labelCorrectAnswer = new JLabel("Poprawna : ");
		
		radioButtonA = new JRadioButton("A");
		radioButtonB = new JRadioButton("B");
		radioButtonC = new JRadioButton("C");
		radioButtonD = new JRadioButton("D");
		// dodanie do grupy
		group = new ButtonGroup();
		group.add(radioButtonA);
		group.add(radioButtonB);
		group.add(radioButtonC);
		group.add(radioButtonD);
		
		// ustawienie na ekranie
		labelTextQuestion.setBounds(50, 25, 100, 50);
		labelTextA.setBounds(50, 100, 100, 50);
		labelTextB.setBounds(50, 175, 100, 50);
		labelTextC.setBounds(50, 250, 100, 50);
		labelTextD.setBounds(50, 325, 100, 50);
		labelCorrectAnswer.setBounds(50, 400, 100, 50);
		
		textQuestion.setBounds(175,25,350,50);
		textA.setBounds(175,100,350,50);
		textB.setBounds(175,175,350,50);
		textC.setBounds(175,250,350,50);
		textD.setBounds(175,325,350,50);

		radioButtonA.setBounds(200, 400, 50, 50);
		radioButtonB.setBounds(250, 400, 50, 50);
		radioButtonC.setBounds(300, 400, 50, 50);
		radioButtonD.setBounds(350, 400, 50, 50);
		
		// przyciski 
		buttonConfirm = new JButton("Potwierdz");
		buttonConfirm.addActionListener(e->confirmQuestion());
		buttonConfirm.setBounds(250, 475, 100, 50);
		
		buttonNext = new JButton(">>");
		buttonNext.addActionListener(e->nextQuestion());
		buttonNext.setBounds(450, 475, 50, 50);
		
		buttonPrevious = new JButton("<<");
		buttonPrevious.addActionListener(e->previousQuestion());
		buttonPrevious.setBounds(100, 475, 50, 50);
		
		setButtonsColor(196, 212, 239);
		
		add(labelTextQuestion);
		add(labelTextA);
		add(labelTextB);
		add(labelTextC);
		add(labelTextD);
		add(labelCorrectAnswer);
		
		add(buttonConfirm);
		add(buttonPrevious);
		add(buttonNext);
		add(textQuestion);
		add(textA);
		add(textB);
		add(textC);
		add(textD);

		add(radioButtonA);
		add(radioButtonB);
		add(radioButtonC);
		add(radioButtonD);

		// za³adowanie pierwszego pytania
		loadData();
	}
	// za³adowanie pierwszego pytania
	public void loadData() {
		
		if(test.getTestSize()>0) {
			textQuestion.setText(test.listOfQuestions.get(0).getQuestion());
			textA.setText(test.listOfQuestions.get(0).getTextA());
			textB.setText(test.listOfQuestions.get(0).getTextB());
			textC.setText(test.listOfQuestions.get(0).getTextC());
			textD.setText(test.listOfQuestions.get(0).getTextD());

			setRadioButtons();
		}
		else {
			buttonConfirm.setEnabled(false);
		}

		
	}
	// nastêpne pytanie
	public void nextQuestion() {
		
		if(index != test.listOfQuestions.size()-1) {
			index++;
			textQuestion.setText(test.listOfQuestions.get(index).getQuestion());
			textA.setText(test.listOfQuestions.get(index).getTextA());
			textB.setText(test.listOfQuestions.get(index).getTextB());
			textC.setText(test.listOfQuestions.get(index).getTextC());
			textD.setText(test.listOfQuestions.get(index).getTextD());

			setRadioButtons();
		}

	}
	// poprzednie pytanie
	public void previousQuestion() {

		if(index != 0 ) {
			index--;
			textQuestion.setText(test.listOfQuestions.get(index).getQuestion());
			textA.setText(test.listOfQuestions.get(index).getTextA());
			textB.setText(test.listOfQuestions.get(index).getTextB());
			textC.setText(test.listOfQuestions.get(index).getTextC());
			textD.setText(test.listOfQuestions.get(index).getTextD());

			setRadioButtons();
		}
	}
	// metoda zatwierdzaj¹ca pytanie, dodaj¹ca do listy pytañ
	public void confirmQuestion() {
		
		if(radioButtonA.isSelected()) correct = "A";
		if(radioButtonB.isSelected()) correct = "B";
		if(radioButtonC.isSelected()) correct = "C";
		if(radioButtonD.isSelected()) correct = "D";
		
		test.listOfQuestions.set(index, new Question(textQuestion.getText(),textA.getText(),textB.getText(),textC.getText(),textD.getText(),correct));
	
	}
	// metoda ustawiaj¹ca kolory przycisków
	public void setButtonsColor(int r ,int g, int b) {
		buttonConfirm.setBackground(new Color(r, g, b));
		buttonNext.setBackground(new Color(r, g, b));
		buttonPrevious.setBackground(new Color(r, g, b));
	}
	// metoda ustawiaj¹ca zaznaczenie odpowiedniego radioButton'a
	public void setRadioButtons() {
		
		correct = test.listOfQuestions.get(index).getCorrectAnswer();

		switch(correct) {
			case "A":
				radioButtonA.setSelected(true);
				break;
			case "B":
				radioButtonB.setSelected(true);
				break;
			case "C":
				radioButtonC.setSelected(true);
				break;
			case "D":
				radioButtonD.setSelected(true);
				break;
			default:
				break;
		}
	}
}

package lab04_pop;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

public class UserWindow extends JFrame{
	
	// Wybrany test
	public Test test;
	// Wybrany u¿ytkownik
	public User user;
	// tablica wyników
	public ArrayList<Score> scores;
	// napisy
	private JLabel textQuestion;
	private JLabel textA;
	private JLabel textB;
	private JLabel textC;
	private JLabel textD;
	private JLabel checkedAnswer;
	// przyciski
	private JButton buttonA;
	private JButton buttonB;
	private JButton buttonC;
	private JButton buttonD;
	private JButton buttonNext;
	
	// numer pytania
	int index = 0;
	// wybrana odpowiedz
	String answer = "";
	// wynik
	int score = 0;
	
	public UserWindow(Test test, User user, ArrayList<Score> scores) {
		
		this.test = test;
		this.user = user;
		this.scores = scores;

		
		setSize(600,600);
		setTitle("Test : " + test.getName());
		setLayout(null);
		setLocationRelativeTo(null);
		
		textQuestion = new JLabel("Pytanie");
		textA = new JLabel("Odpowiedz A");
		textB = new JLabel("Odpowiedz B");
		textC = new JLabel("Odpowiedz C");
		textD = new JLabel("Odpowiedz D");
		checkedAnswer = new JLabel("Twoja Odpowiedz: ");
		
		textQuestion.setBounds(125,50,400,50);
		textA.setBounds(125,125,200,50);
		textB.setBounds(125,200,200,50);
		textC.setBounds(125,275,200,50);
		textD.setBounds(125,350,200,50);
		checkedAnswer.setBounds(125,425,200,50);
		
		buttonA = new JButton("A");
		buttonB = new JButton("B");
		buttonC = new JButton("C");
		buttonD = new JButton("D");
		buttonNext = new JButton("Dalej");
		
		buttonA.addActionListener(e->setAnswer("A"));
		buttonB.addActionListener(e->setAnswer("B"));
		buttonC.addActionListener(e->setAnswer("C"));
		buttonD.addActionListener(e->setAnswer("D"));
		buttonNext.addActionListener(e->nextQuestion());
		
		
		buttonA.setBounds(375, 125, 75, 50);
		buttonB.setBounds(375, 200, 75, 50);
		buttonC.setBounds(375, 275, 75, 50);
		buttonD.setBounds(375, 350, 75, 50);
		buttonNext.setBounds(375, 425, 75, 50);
		
		setButtonsColor(196, 212, 239);
		
		add(buttonNext);
		add(textQuestion);
		add(textA);
		add(textB);
		add(textC);
		add(textD);
		add(checkedAnswer);
		add(buttonA);
		add(buttonB);
		add(buttonC);
		add(buttonD);
		
		buttonNext.setEnabled(false);
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
		}
		else {
			buttonNext.setEnabled(false);
		}

		
	}
	public void nextQuestion() {
		
		if(answer.equals(test.listOfQuestions.get(index).getCorrectAnswer())) {
			score++;
		}
		
		
		index++;
		
		if(index < test.listOfQuestions.size()) {
			textQuestion.setText(test.listOfQuestions.get(index).getQuestion());
			textA.setText(test.listOfQuestions.get(index).getTextA());
			textB.setText(test.listOfQuestions.get(index).getTextB());
			textC.setText(test.listOfQuestions.get(index).getTextC());
			textD.setText(test.listOfQuestions.get(index).getTextD());
		}
		else {
			scores.add(new Score(user,test,score));
			user.addScore(test,score);
			InfoWindow window = new InfoWindow(score, test.getTestSize());
			window.setVisible(true);
			this.dispose();
		}
		buttonNext.setEnabled(false);
		checkedAnswer.setText("Twoja Odpowiedz: ");
	}
	// wybranie odpowiedzi
	public void setAnswer(String userAnswer) {
		
		this.answer = userAnswer;
		checkedAnswer.setText("Twoja Odpowiedz: "+ answer);
		buttonNext.setEnabled(true);
	}
	public void setButtonsColor(int r ,int g, int b) {
		buttonA.setBackground(new Color(r, g, b));
		buttonB.setBackground(new Color(r, g, b));
		buttonC.setBackground(new Color(r, g, b));
		buttonD.setBackground(new Color(r, g, b));
		buttonNext.setBackground(new Color(r, g, b));
	}
	
}

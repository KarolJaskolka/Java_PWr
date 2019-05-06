package lab04_pop;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class MainWindow extends JFrame {
	
	ArrayList<Test> tests = new ArrayList<>();
	ArrayList<User> users = new ArrayList<>();
	User user = new User("User");
	ArrayList<Score> scores = new ArrayList<>();
 
	// przyciski
	JButton buttonUserWindow;
	JButton buttonAddQuestionWindow;
	JButton buttonResultsWindow;
	JButton buttonLogIn;
	JButton buttonNext;
	JButton buttonPrevious;
	JButton buttonAddNewTest;
	JButton buttonQuestionEditorWindow;
	JButton buttonSave;
	
	JLabel labelTestName;
	JLabel labelTestSize;
	JLabel labelInfo;
	JTextField textFieldLogin;
	int index = 0;
	
	public MainWindow() throws FileNotFoundException {
		
		setSize(600,500);
		setTitle("Aplikacja");
		setLocationRelativeTo(null);
		setLayout(null);
		
		loadData();
		
		// inicjalizacja
		labelTestName = new JLabel();
		labelTestSize = new JLabel();
		labelInfo = new JLabel();
		labelTestName.setText("Wybrany test: " + tests.get(index).getName()); 
		labelTestSize.setText("Pytania: " + tests.get(index).getTestSize()); 
		textFieldLogin = new JTextField("");
		buttonUserWindow = new JButton("Zrób test");
		buttonAddQuestionWindow = new JButton("Dodaj pytanie");
		buttonLogIn = new JButton("Zaloguj");
		buttonNext = new JButton(">>");
		buttonPrevious = new JButton("<<");
		buttonResultsWindow = new JButton("Statystyki");
		buttonAddNewTest = new JButton("Dodaj nowy test");
		buttonQuestionEditorWindow = new JButton("Edytuj pytania");
		buttonSave = new JButton("Zapisz");

		
		
		// dodanie dzia³ania 
		buttonUserWindow.addActionListener(e->showUserWindow());
		buttonAddQuestionWindow.addActionListener(e->showAddQuestionWindow());
		buttonLogIn.addActionListener(e->showTest());
		buttonNext.addActionListener(e->showNext());
		buttonPrevious.addActionListener(e->showPrevious());
		buttonResultsWindow.addActionListener(e->showResultsWindow());
		buttonLogIn.addActionListener(e->logIn());
		buttonAddNewTest.addActionListener(e->addNewTest());
		buttonQuestionEditorWindow.addActionListener(e->showQuestionEditorWindow());
		buttonSave.addActionListener(e->{
			try {
				saveData();
			} catch (FileNotFoundException e1) {
			}
		});
		
		// ustawienie pozycji
		
		buttonLogIn.setBounds(400, 50, 125, 50);
		buttonNext.setBounds(100, 150, 50, 50);
		buttonPrevious.setBounds(50, 150, 50, 50);
		buttonUserWindow.setBounds(400, 150, 125, 50);
		
		buttonAddQuestionWindow.setBounds(225, 250, 125, 50);
		buttonAddNewTest.setBounds(50,250,125,50);
		buttonQuestionEditorWindow.setBounds(400, 250, 125, 50);
		buttonResultsWindow.setBounds(225, 350, 125, 50);
		buttonSave.setBounds(400, 350, 125, 50);
		
		labelInfo.setBounds(190,10,300,30);
		labelTestName.setBounds(190,145,300,30);
		labelTestSize.setBounds(190,175,300,30);
		textFieldLogin.setBounds(50, 50, 300, 50);
		
		
		disableButtons();
		setButtonsColor(196, 212, 239);
		
		// dodanie do okna
		add(buttonUserWindow);
		add(buttonAddQuestionWindow);
		add(buttonQuestionEditorWindow);
		add(labelTestName);
		add(labelTestSize);
		add(labelInfo);
		add(buttonLogIn);
		add(buttonNext);
		add(buttonPrevious);
		add(textFieldLogin);
		add(buttonResultsWindow);
		add(buttonAddNewTest);
		add(buttonSave);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		MainWindow window = new MainWindow();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	public boolean checkUserExists(String login) {
		for(User check : users) {
			if(check.getName().equals(login)) {
				this.user = check;
				return true;
			}
		}
		return false;
	}
	// buttonLogIn
	public void logIn() {
		
		if(textFieldLogin.getText().equals("")) {
			labelInfo.setText("Podaj login");
			labelInfo.setForeground(Color.RED);
		}
		else {
			if(!checkUserExists(textFieldLogin.getText())) {
				user = new User(textFieldLogin.getText());
				users.add(user);
			}
			labelInfo.setText("Zalogowany jako : " + textFieldLogin.getText());
			labelInfo.setForeground(Color.BLACK);
			enableButtons();
		}

	}
	
	// buttonAddTestWindow
	public void addNewTest() {
		AddTestWindow window = new AddTestWindow(tests);
		window.setVisible(true);		
	}
	
	// buttonQuestionEditorWindow
	public void showQuestionEditorWindow(){
		QuestionEditorWindow window = new QuestionEditorWindow(tests.get(index));
		window.setVisible(true);
	}
	
	// buttonUserWindow
	public void showUserWindow() {	
		if(tests.get(index).getTestSize()>0) {
		UserWindow userWindow = new UserWindow(tests.get(index),user,scores);
		userWindow.setVisible(true);
		}
	}
	
	// buttonAddQuestionWindow
	public void showAddQuestionWindow(){
		AddQuestionWindow window = new AddQuestionWindow(tests.get(index));
		window.setVisible(true);
		
	}
	
	// buttonResultsWindow
	public void showResultsWindow() {
		ResultsWindow window = new ResultsWindow(scores,users,user);
		window.setVisible(true);
	}

	// wyœwietlenie obecnie wybranego testu
	public void showTest() {
		labelTestName.setText("Wybrany test: " + tests.get(index).getName()); 
		labelTestSize.setText("Pytania: " + tests.get(index).getTestSize()); 
	}
	
	// buttonNext
	public void showNext() {	
		if(index != (tests.size()-1)) {
			index++;
			showTest();
		}
	}
	
	// buttonPrevious
	public void showPrevious() {
		if(index != 0) {
			index--;
			showTest();
		}
	}

	public void loadData() throws FileNotFoundException {
		
		//pliki
		File file = new File("users.txt");
		File file2 = new File("testsNames.txt");
		File file3 = new File("tests.txt");
		File file4 = new File("scores.txt");
						
		Scanner in1 = new Scanner(file);
		Scanner in2 = new Scanner(file2);
		Scanner in3 = new Scanner(file3);
		Scanner in4 = new Scanner(file4);
						
		String data = "";

		// u¿ytkownicy
		while(in1.hasNext()) {
			data = in1.nextLine();
			users.add(new User(data));
			data = "";
		}
		// testy
		while(in2.hasNext()) {
			data = in2.nextLine();
			tests.add(new Test(data));
			data = "";
		}
		// pytania do testów
		while(in3.hasNext()) {
			data = in3.nextLine();
			String[] dataSplit = data.split(";");
			tests.get(Integer.parseInt(dataSplit[0])).addQuestion(dataSplit[1],dataSplit[2],dataSplit[3],dataSplit[4],dataSplit[5],dataSplit[6]);
			data = "";
		}
		// wyniki
		while(in4.hasNext()) {
			data = in4.nextLine();
			String[] dataSplit = data.split(";");
			scores.add(new Score(users.get(Integer.parseInt(dataSplit[0])),tests.get(Integer.parseInt(dataSplit[1])),Integer.parseInt(dataSplit[2])));
			users.get(Integer.parseInt(dataSplit[0])).addScore(tests.get(Integer.parseInt(dataSplit[1])),Integer.parseInt(dataSplit[2]));
			data = "";
		}
		
		in1.close();
		in2.close();
		in3.close();
		in4.close();
		
	}
	public void saveData() throws FileNotFoundException {
		PrintWriter zapis1 = new PrintWriter("users.txt");
		PrintWriter zapis2 = new PrintWriter("testsNames.txt");
		PrintWriter zapis3 = new PrintWriter("tests.txt");
		PrintWriter zapis4 = new PrintWriter("scores.txt");
		
		for(User user: users) {
			zapis1.println(user.getName());
		}
		for(Test test: tests) {
			zapis2.println(test.getName());
		}

		for(Test test: tests) {
			for(int i=0;i<test.getTestSize();i++) {
			zapis3.println(test.getTestID() + ";" + test.listOfQuestions.get(i).getQuestion()
											+ ";" + test.listOfQuestions.get(i).getTextA()
											+ ";" + test.listOfQuestions.get(i).getTextB()
											+ ";" + test.listOfQuestions.get(i).getTextC()
											+ ";" + test.listOfQuestions.get(i).getTextD()
											+ ";" + test.listOfQuestions.get(i).getCorrectAnswer() + ";"
					);
			}
		}
		for(Score score: scores) {
			zapis4.println((score.user.getUserID()-1) +";" + score.test.getTestID() + ";" + score.getScore());
		}
		
		zapis1.close();
		zapis2.close();
		zapis3.close();
		zapis4.close();
	}
	public void disableButtons() {
		buttonNext.setEnabled(false);
		buttonPrevious.setEnabled(false);
		buttonUserWindow.setEnabled(false);
		buttonAddQuestionWindow.setEnabled(false);
		buttonResultsWindow.setEnabled(false);
		buttonAddNewTest.setEnabled(false);
		buttonQuestionEditorWindow.setEnabled(false);
		buttonSave.setEnabled(false);
	}
	public void enableButtons() {
		buttonAddQuestionWindow.setEnabled(true);
		buttonResultsWindow.setEnabled(true);
		buttonAddNewTest.setEnabled(true);
		buttonQuestionEditorWindow.setEnabled(true);
		buttonLogIn.setEnabled(false);
		textFieldLogin.setEnabled(false);
		buttonNext.setEnabled(true);
		buttonPrevious.setEnabled(true);
		buttonUserWindow.setEnabled(true);
		buttonSave.setEnabled(true);
	}
	public void setButtonsColor(int r ,int g, int b) {
		buttonUserWindow.setBackground(new Color(r, g, b));
		buttonAddQuestionWindow.setBackground(new Color(r, g, b));
		buttonResultsWindow.setBackground(new Color(r, g, b));
		buttonLogIn.setBackground(new Color(r, g, b));
		buttonNext.setBackground(new Color(r, g, b));
		buttonPrevious.setBackground(new Color(r, g, b));
		buttonAddNewTest.setBackground(new Color(r, g, b));
		buttonQuestionEditorWindow.setBackground(new Color(r, g, b));
		buttonSave.setBackground(new Color(r, g, b));
	}
}

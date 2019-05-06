package lab04_pop;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ResultsWindow extends JFrame {
	
	ArrayList<Score> scores = new ArrayList<>();
	ArrayList<User> users = new ArrayList<>();
	User user;
	JTable table;
	DefaultTableModel tableModel;
	
	JButton buttonUserStats;
	JButton buttonAverageAll;
	JButton buttonAllScores;
	
	public ResultsWindow(ArrayList<Score> scores, ArrayList<User> users, User user){
		this.user = user;
		this.users = users;
		this.scores = scores;
		setSize(600,600);
		setLocationRelativeTo(null);
		setTitle("Wyniki");
		setLayout(null);
		buttonAllScores  = new JButton("Wszystkie wyniki");
		buttonUserStats = new JButton("Moje wyniki");
		buttonAverageAll  = new JButton("Œrednie wyniki graczy");
		buttonUserStats.setBounds(25, 475, 175, 50);
		buttonUserStats.addActionListener(e->loadUserTable());
		buttonAllScores.addActionListener(e->loadTable());
		buttonAverageAll.setBounds(200, 475, 175, 50);
		buttonAllScores.setBounds(375, 475, 175, 50);
		buttonAverageAll.addActionListener(e->loadAverageResultTable());
		String col[] = {"UserID","UserName","TestName","Score","MaxScore"};
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);

		loadTable();
		table.setBounds(25, 50, 525, 400);
		
		setButtonsColor(196, 212, 239);
		
		add(table);
		add(buttonUserStats);
		add(buttonAverageAll);
		add(buttonAllScores);
		
	}

	public void loadTable() {
		tableModel.setRowCount(0);
		tableModel.addRow(new Object[]{"UserID","UserName","TestName","Score","MaxScore"});
		for (int i = 0; i < scores.size(); i++){
			int userID = scores.get(i).user.getUserID();
			String userName = scores.get(i).user.getName();
			String testName = scores.get(i).test.getName();
			int score = scores.get(i).getScore();
			int maxScore = scores.get(i).test.getTestSize();
			Object[] data = {userID, userName, testName, score, maxScore};
			tableModel.addRow(data);
		}
	}
	
	public void loadUserTable() {
		tableModel.setRowCount(0);
		tableModel.addRow(new Object[]{"UserID","UserName","TestName","Score","MaxScore"});
		for (int i = 0; i < scores.size(); i++){
			if(user.getUserID() == scores.get(i).user.getUserID()) {
				int userID = scores.get(i).user.getUserID();
				String userName = scores.get(i).user.getName();
				String testName = scores.get(i).test.getName();
				int score = scores.get(i).getScore();
				int maxScore = scores.get(i).test.getTestSize();
				Object[] data = {userID, userName, testName, score, maxScore};
				tableModel.addRow(data);
			}
		}
	}
	public void loadAverageResultTable() {
		tableModel.setRowCount(0);
		tableModel.addRow(new Object[]{"UserID","UserName","Average Score"});
		for (int i = 0; i < users.size(); i++){

				int userID = users.get(i).getUserID();
				String userName = users.get(i).getName();
				double avgScore = users.get(i).getAverageScore();
				Object[] data = {userID, userName, avgScore};
				tableModel.addRow(data);

		}
	}
	public void setButtonsColor(int r ,int g, int b) {
		buttonUserStats.setBackground(new Color(r, g, b));
		buttonAverageAll.setBackground(new Color(r, g, b));
		buttonAllScores.setBackground(new Color(r, g, b));
	}
}

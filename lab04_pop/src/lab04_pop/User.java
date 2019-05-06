package lab04_pop;

import java.util.ArrayList;

public class User {
	
	private int userID;
	private String name;
	public ArrayList<Score> userScores = new ArrayList<>();
	
	public static int ID = 0;
	
	public User(String name) {
		userID = ID;
		this.name = name;
		ID++;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	public void addScore(Test test, int score) {
		userScores.add(new Score(this,test,score));
	}
	public double getAverageScore() {

		int quantity = 0;
		int sum = 0;
		for(Score score : userScores) {
			sum += score.getScore();
			quantity++;
		}
		if(quantity == 0) {
			return 0.0;
		}
		else {
			return  sum/quantity;
		}
		
	}
}

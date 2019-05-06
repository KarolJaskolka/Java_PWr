package lab04_pop;

public class Score {
	
	public User user;
	public Test test;
	private int score;
	
	public Score(User user, Test test, int score) {
		this.user = user;
		this.test = test;
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}

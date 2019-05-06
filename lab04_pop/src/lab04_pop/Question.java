package lab04_pop;

public class Question {
	
	private String question;
	private String textA;
	private String textB;
	private String textC;
	private String textD;
	private String correctAnswer;
	
	public Question(String question,String textA, String textB, String textC, String textD, String correct) {

		this.question = question;
		this.textA = textA;
		this.textB = textB;
		this.textC = textC;
		this.textD = textD;
		correctAnswer = correct;

	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getTextA() {
		return textA;
	}

	public void setTextA(String textA) {
		this.textA = textA;
	}

	public String getTextB() {
		return textB;
	}

	public void setTextB(String textB) {
		this.textB = textB;
	}

	public String getTextC() {
		return textC;
	}

	public void setTextC(String textC) {
		this.textC = textC;
	}

	public String getTextD() {
		return textD;
	}

	public void setTextD(String textD) {
		this.textD = textD;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public boolean checkAnswer(String userAnswer) {
		if(userAnswer.equals(correctAnswer)) {
			return true;
		}
		else {
			return false;
		}
	}
}

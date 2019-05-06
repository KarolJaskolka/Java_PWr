package lab04_pop;

import java.util.ArrayList;

public class Test {
	
	private int testID;
	private String name;
	public ArrayList<Question> listOfQuestions = new ArrayList<>();
	public static int ID = 0;
	
	public Test(String name) {
		testID = ID;
		this.name = name;
		ID++;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getTestSize() {
		return listOfQuestions.size();
	}

	public int getTestID() {
		return testID;
	}

	public void setTestID(int testID) {
		this.testID = testID;
	}
	public void addQuestion(String question,String textA, String textB, String textC, String textD, String correct) {
		listOfQuestions.add(new Question(question,textA,textB,textC,textD,correct));
	}
}

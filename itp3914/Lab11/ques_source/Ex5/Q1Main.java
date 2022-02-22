public class Q1Main {
    public static void main(String [] args) {
		QuestionOne q1 = new QuestionOne(); //line1
		q1.A = 12; //line2
		q1.b = 12; //line3
		q1.c = 12; //line4
		q1.methodOne(12); //line5
		q1.methodOne();//line6
		System.out.println(q1.methodTwo());//line7
		q1.b = q1.methodTwo();//line8
  	}

}

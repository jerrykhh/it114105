public class Ex2c {
    public static void main(String[] args) {
        Student[] students = new Student[3];
        students[0] = new Student("Cheung Siu Ming", 310567, 87.1);
        students[1] = new Student("CNg Wai Man", 451267, 77.5);
        students[2] = new Student("Wong Sui Kai", 789014, 83.4);
        double totalScore = 0.0;
        for (Student student : students) {
            student.printDetails();
            totalScore += student.getScore();
        }
        System.out.println("Average Score = " + totalScore/students.length);

    }    
}

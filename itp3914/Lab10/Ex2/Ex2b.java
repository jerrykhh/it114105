public class Ex2b {
    public static void main(String[] args) {
        Student stud1 = new Student();
        Student stud2 = new Student();
        Student stud3 = new Student();

        stud1.setId(310567);
        stud1.setName("Cheung Siu Ming");
        stud1.setScore(87.1);

        stud2.setId(451267);
        stud2.setName("Ng Wau Man");
        stud2.setScore(77.5);

        stud3.setId(789014);
        stud3.setName("Wong Sui Kai");
        stud3.setScore(83.4);

        System.out.printf("Student: name=%s id=%d score=%.1f\n", stud1.getName(), stud1.getId(), stud1.getScore());
        System.out.printf("Student: name=%s id=%d score=%.1f\n", stud2.getName(), stud2.getId(), stud2.getScore());
        System.out.printf("Student: name=%s id=%d score=%.1f\n", stud3.getName(), stud3.getId(), stud3.getScore());
        System.out.println("Average Score =" + (stud1.getScore()+stud2.getScore()+stud3.getScore())/ 3);
    }    
}

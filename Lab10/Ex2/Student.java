public class Student {
    String name;
    int id;
    double score;

    Student(){}

    Student(String name, int id, double score) {
        this.name = name;
        this.id = id;
        this.score = score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public void printDetails(){
        System.out.println("Student: name="+name+" id=" + id + " score="+score);
    }
}

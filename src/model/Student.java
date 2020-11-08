package model;



import java.util.Objects;

public class Student {
    private String id;
    private String name;
    private int age;
    private String address;
    private double GPA;

    public Student(String id, String name, int age, String address, double GPA) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.GPA = GPA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return
                address == student.address&&
                age == student.age &&
                Double.compare(student.GPA, GPA) == 0 &&
                Objects.equals(id, student.id) &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age,address, GPA);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", GPA=" + GPA +
                '}';
    }
}

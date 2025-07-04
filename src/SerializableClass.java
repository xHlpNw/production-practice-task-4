import java.util.ArrayList;

public class SerializableClass {
    @JsonField(name = "years")
    public int age;
    @JsonField(name = "name")
    private String name;
    @JsonField(name = "group")
    private char group;
    @JsonField(name = "kilograms")
    private double weight;
    @JsonField(name = "grades")
    public ArrayList<Integer> marks;

    public SerializableClass() {
        age = 10;
        name = "Ivan";
        group = 'A';
        weight = 40.2;
        marks = null;
    }

    public SerializableClass(int age, String name, char group, double weight, ArrayList<Integer> marks) {
        this.age = age;
        this.name = name;
        this.group = group;
        this.weight = weight;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("age: %d, name: %s, group: %c, weight: %f, marks: %s",
                age, name, group, weight, marks);
    }
}

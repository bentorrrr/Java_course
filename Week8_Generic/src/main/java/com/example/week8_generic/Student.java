package com.example.week8_generic;

public class Student {
    private final String id;
    private final String firstName;
    private final String lastName;
    private double gpa;

    public Student(String id, String firstName, String lastName, double gpa) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id cannot be blank");
        if (firstName == null || firstName.isBlank()) throw new IllegalArgumentException("firstName cannot be blank");
        if (lastName == null || lastName.isBlank()) throw new IllegalArgumentException("lastName cannot be blank");

        setGpa(gpa);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public double getGpa() { return gpa; }

    public void setGpa(double gpa) {
        if(gpa < 0 || gpa > 4) throw new IllegalArgumentException("gpa must be between 0 and 4");
        this.gpa = gpa;
    }

    @Override public String toString() {
        return "%s | %s %s | %.2f".formatted(id, firstName, lastName, gpa);
    }
}

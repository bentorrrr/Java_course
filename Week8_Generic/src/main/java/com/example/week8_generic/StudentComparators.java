package com.example.week8_generic;
import java.util.Comparator;

public final class StudentComparators {
    public static final Comparator<Student> BY_ID = Comparator.comparing(Student::getId);
    public static final Comparator<Student> BY_First = Comparator.comparing(Student::getFirstName);
    public static final Comparator<Student> BY_Last = Comparator.comparing(Student::getLastName);
    public static final Comparator<Student> BY_GPA = Comparator.comparing(Student::getGpa);

    public static Comparator<Student> desc(Comparator<Student> comparator) { return comparator.reversed(); }

    private StudentComparators() { }
}

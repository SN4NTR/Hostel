package com.company.builders;

import com.company.constants.Constants;
import com.company.people.inhabitants.Student;

import java.util.List;

public class StudentBuilder {

    public void build(List<Student> studentList, int upperBorder) {
        for (int i = 0; i < upperBorder; i++) {
            int courseNumber = (int) (1 + Math.random() * Constants.COURSES_NUMBER.getValue());
            Student student = new Student();
            student.setCourse(courseNumber);
            studentList.add(student);
        }
    }
}

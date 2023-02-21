package controller;

import model.Student;
import storage.ReadAndWrite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentManager {
    List<Student> students = new ArrayList<>();

    public StudentManager() {
    }

    public StudentManager(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);
        ReadAndWrite.getInstance().writeFileStudent(students);
    }

    public void editStudent(String studentCode, Student student) {
        for (int i = 0; i < students.size(); i++) {
            if(studentCode.equals(students.get(i).getStudentCode())) {
                students.set(i, student);
                break;
            }
        }
        ReadAndWrite.getInstance().writeFileStudent(students);
    }
    public void deleteStudent(String studentCode) {
        for (Student student : students) {
            if(studentCode.equals(student.getStudentCode())) {
                students.remove(student);
                break;
            }
        }
        ReadAndWrite.getInstance().writeFileStudent(students);
    }

    public void sort() {
        Collections.sort(students);
    }
}

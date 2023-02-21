package storage;

import model.Student;

import java.util.List;

public interface IReadAndWrite {
    public void writeFileStudent(List<Student> employees);
    public List<Student> readFileStudent();
}

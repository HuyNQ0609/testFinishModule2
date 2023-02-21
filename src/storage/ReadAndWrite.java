package storage;

import model.Student;

import java.io.*;
import java.util.List;

public class ReadAndWrite implements IReadAndWrite {
    private File file;
    private ReadAndWrite() {}
    private static ReadAndWrite instance;
    public static ReadAndWrite getInstance() {
        if (instance == null) {
            instance = new ReadAndWrite();
        }
        return instance;
    }
    public ReadAndWrite(File file) {
        this.file = file;
    }
    @Override
    public void writeFileStudent(List<Student> employees) {
        file = new File("student.dat");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            objectOutputStream.writeObject(employees);
            objectOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> readFileStudent() {
        file = new File("student.dat");
        List<Student> employees;
        try {
            InputStream inputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            employees = (List<Student>) objectInputStream.readObject();
            objectInputStream.close();
            inputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }
}

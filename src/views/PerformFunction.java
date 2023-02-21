package views;

import controller.StudentManager;
import model.Student;
import storage.ReadAndWrite;

import java.util.Scanner;

public class PerformFunction {
    private final Scanner input = new Scanner(System.in);
    StudentManager studentManager = new StudentManager();

    public int choose() {
        try {
            System.out.println("Enter your choice: ");
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter again! ");
        }
        return choose();
    }

    public void showMenu() {
        do {
            System.out.println("""
                    
                    ____________________________________________________________
                   |                Student Management Program                  |
                   |                                                            |
                   |    1. View student list                                    |
                   |    2. Add new student information                          |
                   |    3. Edit student information                             |
                   |    4. Delete student information                           |
                   |    5. Sort student list                                    |
                   |    6. Read from file                                       |
                   |    7. Write to file                                        |
                   |    8. Exit                                                 |
                   |____________________________________________________________|
                   """);
            runProgram(choose());
        } while (true);
    }

    public void runProgram(int numberChoose) {
        switch (numberChoose) {
            case 1 -> printListStudent();
            case 2 -> addNewStudent();
            case 3 -> editStudent();
            case 4 -> removeStudent();
            case 5 -> showSortItem();
            case 6 -> readForFile();
            case 7 -> writeToFile();
            case 8 -> System.exit(numberChoose);
            default -> System.out.println("Please choose again!");
        }
    }

    public String enterStudentCode() {
        System.out.println("Enter student code: ");
        String studentCode = input.nextLine();
        for (Student student: studentManager.getStudents()) {
            while (true) {
                if (student.getStudentCode().equals(studentCode)) {
                    System.out.println("The student code you entered has been duplicated, please re-enter it: ");
                    studentCode = input.nextLine();
                } else {
                    break;
                }
            }
        }
        return studentCode;
    }

    public String checkStudentCodeToEdit() {
        System.out.println("Enter student code: ");
        String studentCode = input.nextLine();
        for (int i = 0; i < studentManager.getStudents().size(); i++) {
            if (studentCode.equals(studentManager.getStudents().get(i).getStudentCode())) {
                return studentCode;
            } else if (studentCode.equals("")) {
                break;
            }
        }
        System.out.println("Student ID not found!");
        return checkStudentCodeToEdit();
    }

    public String checkStudentCodeToDelete() {
        System.out.println("Enter student code: ");
        String studentCode = input.nextLine();
        for (int i = 0; i < studentManager.getStudents().size(); i++) {
            if (studentCode.equals(studentManager.getStudents().get(i).getStudentCode())) {
                System.out.println("Are you sure you want to delete?");
                String choice = input.nextLine();
                if (choice.equals("Y")) {
                    return studentCode;
                } else {
                    break;
                }
            } else if (studentCode.equals("")) {
                    break;
            }
        }
        System.out.println("Student ID not found!");
        return checkStudentCodeToDelete();
    }

    public String enterFullName() {
        System.out.println("Enter full name of student: ");
        return input.nextLine();
    }

    public int enterAge() {
        try {
            System.out.println("Enter age: ");
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter again!");
        }
        return enterAge();
    }

    public String enterSex() {
        System.out.println("Enter sex: ");
        return input.nextLine();
    }

    public String enterAddress() {
        System.out.println("Enter address of student: ");
        return input.nextLine();
    }

    public double enterMediumScore() {
        try {
            System.out.println("Enter medium score of student: ");
            return Double.parseDouble(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please enter again!");
        }
        return enterMediumScore();
    }

    /***************************************************/

    public void printListStudent() {
        for (Student student : studentManager.getStudents()) {
            System.out.println(student);
        }
    }
    public void addNewStudent() {
        studentManager.addStudent(new Student(enterStudentCode(), enterFullName(), enterAge(),
                                enterSex(), enterAddress(), enterMediumScore()));
    }

    public void editStudent() {
        studentManager.editStudent(checkStudentCodeToEdit(),new Student(enterStudentCode(), enterFullName(), enterAge(),
                enterSex(), enterAddress(), enterMediumScore()));
    }

    public void removeStudent() {
        studentManager.deleteStudent(checkStudentCodeToDelete());
    }

    public void showSortItem() {
        do {
            System.out.println("""
                    
                     _______________________________________________________
                    |         Sort students by grade point average          |
                    |                                                       |
                    |       1. Sort by average score in ascending           |
                    |       2. Sort by average score in descending          |
                    |       3. Exit.                                        |
                    |_______________________________________________________|
                    """);
            sortStudent(choose());
        } while (true);
    }

    public void sortStudent(int numberChoose) {
        switch (numberChoose) {
            case 1 -> studentManager.sort();
            case 2 -> studentManager.sort();
            case 3 -> showMenu();
            default -> System.out.println("Please enter again!");
        }
    }

    public void readForFile() {
        ReadAndWrite.getInstance().readFileStudent();
    }

    public void writeToFile() {
        ReadAndWrite.getInstance().writeFileStudent(studentManager.getStudents());
    }
}

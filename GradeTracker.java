import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Student {
    String name;
    HashMap<String, ArrayList<Double>> grades;

    Student(String name) {
        this.name = name;
        this.grades = new HashMap<>();
    }

    void addGrade(String subject, double grade) {
        grades.putIfAbsent(subject, new ArrayList<>());
        grades.get(subject).add(grade);
    }

    double calculateAverage() {
        double sum = 0;
        int count = 0;
        for (String subject : grades.keySet()) {
            for (double grade : grades.get(subject)) {
                sum += grade;
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;
    }

    String calculateLetterGrade() {
        double average = calculateAverage();
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }

    double calculateGPA() {
        double average = calculateAverage();
        if (average >= 90) return 4.0;
        else if (average >= 80) return 3.0;
        else if (average >= 70) return 2.0;
        else if (average >= 60) return 1.0;
        else return 0.0;
    }
}

public class GradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add a new student");
            System.out.println("2. Add a grade to a student");
            System.out.println("3. Calculate a student's average grade");
            System.out.println("4. Display a student's overall grade information");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.println("Enter the student's name:");
                    String name = scanner.nextLine();
                    students.add(new Student(name));
                    System.out.println("Student added.");
                    break;
                case 2:
                    System.out.println("Enter the student's name:");
                    name = scanner.nextLine();
                    Student student = findStudent(students, name);
                    if (student != null) {
                        System.out.println("Enter the subject:");
                        String subject = scanner.nextLine();
                        System.out.println("Enter the grade:");
                        double grade = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline
                        student.addGrade(subject, grade);
                        System.out.println("Grade added.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the student's name:");
                    name = scanner.nextLine();
                    student = findStudent(students, name);
                    if (student != null) {
                        double average = student.calculateAverage();
                        System.out.println("Average grade for " + name + ": " + average);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:5
                    System.out.println("Enter the student's name:");
                    name = scanner.nextLine();
                    student = findStudent(students, name);
                    if (student != null) {
                        double average = student.calculateAverage();
                        String letterGrade = student.calculateLetterGrade();
                        double gpa = student.calculateGPA();
                        System.out.println("Overall grade information for " + name + ":");
                        System.out.println("Average Grade: " + average);
                        System.out.println("Letter Grade: " + letterGrade);
                        System.out.println("GPA: " + gpa);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Student findStudent(ArrayList<Student> students, String name) {
        for (Student student : students) {
            if (student.name.equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
}

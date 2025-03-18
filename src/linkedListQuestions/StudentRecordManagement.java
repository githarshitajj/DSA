package linkedListQuestions;

class Student {
    int rollNumber;
    String name;
    int age;
    char grade;

    public Student(int rollNumber, String name, int age, char grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Roll Number: " + rollNumber + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;
    }
}

class Node {
    Student data;
    Node next;

    public Node(Student data) {
        this.data = data;
        this.next = null;
    }
}

class StudentLinkedList {
    Node head;

    public StudentLinkedList() {
        head = null;
    }

    // 1. Add Operations

    // Add at the beginning
    public void addFirst(Student student) {
        Node newNode = new Node(student);
        newNode.next = head;
        head = newNode;
    }

    // Add at the end
    public void addLast(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Add at a specific position (0-indexed)
    public void addAtPosition(Student student, int position) {
        if (position < 0) {
            System.out.println("Invalid position. Position must be non-negative.");
            return;
        }

        if (position == 0) {
            addFirst(student);
            return;
        }

        Node newNode = new Node(student);
        Node current = head;
        int count = 0;

        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }

        if (current == null) {
            System.out.println("Invalid position. Position exceeds list length.");
            return;
        }

        newNode.next = current.next;
        current.next = newNode;
    }


    // 2. Delete Operation

    // Delete by Roll Number
    public void deleteByRollNumber(int rollNumber) {
        if (head == null) {
            System.out.println("List is empty. Cannot delete.");
            return;
        }

        if (head.data.rollNumber == rollNumber) {
            head = head.next;
            System.out.println("Student with Roll Number " + rollNumber + " deleted.");
            return;
        }

        Node current = head;
        Node prev = null;
        while (current != null && current.data.rollNumber != rollNumber) {
            prev = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
            return;
        }

        prev.next = current.next;
        System.out.println("Student with Roll Number " + rollNumber + " deleted.");
    }


    // 3. Search Operation

    // Search by Roll Number
    public Student searchByRollNumber(int rollNumber) {
        Node current = head;
        while (current != null) {
            if (current.data.rollNumber == rollNumber) {
                return current.data;
            }
            current = current.next;
        }
        return null; // Student not found
    }


    // 4. Display Operation

    // Display all records
    public void displayAll() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }


    // 5. Update Operation

    // Update grade by Roll Number
    public void updateGrade(int rollNumber, char newGrade) {
        Student student = searchByRollNumber(rollNumber);
        if (student != null) {
            student.grade = newGrade;
            System.out.println("Grade updated for student with Roll Number " + rollNumber);
        } else {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
        }
    }
}


public class StudentRecordManagement {
    public static void main(String[] args) {
        StudentLinkedList studentList = new StudentLinkedList();

        // Adding some initial student records
        studentList.addLast(new Student(101, "Alice", 20, 'A'));
        studentList.addLast(new Student(102, "Bob", 21, 'B'));
        studentList.addFirst(new Student(100, "Charlie", 19, 'C'));
        studentList.addAtPosition(new Student(103, "David", 22, 'A'), 2);


        // Displaying all records
        System.out.println("Initial Student Records:");
        studentList.displayAll();

        // Deleting a student record
        studentList.deleteByRollNumber(102);
        System.out.println("\nStudent Records after deleting Roll Number 102:");
        studentList.displayAll();

        // Searching for a student record
        Student foundStudent = studentList.searchByRollNumber(101);
        if (foundStudent != null) {
            System.out.println("\nFound Student: " + foundStudent);
        } else {
            System.out.println("\nStudent not found.");
        }

        // Updating a student's grade
        studentList.updateGrade(101, 'B');
        System.out.println("\nStudent Records after updating grade for Roll Number 101:");
        studentList.displayAll();

        //Adding at a specific position
        studentList.addAtPosition(new Student(104, "Eve", 23, 'C'), 0); //Add at beginning
        System.out.println("\nStudent records after adding Eve at the beginning");
        studentList.displayAll();

        studentList.addAtPosition(new Student(105, "Mallory", 23, 'A'), 4); //Add at position 4 (index 3)
        System.out.println("\nStudent records after adding Mallory at position 4");
        studentList.displayAll();

        studentList.addAtPosition(new Student(106, "Frank", 25, 'B'), 10); // Invalid position
        studentList.addAtPosition(new Student(107, "Grace", 24, 'D'), -1);  // Invalid position
        System.out.println("\nStudent records after invalid additions (should be same):");
        studentList.displayAll();

        //Deleting a student record that doesn't exist
        studentList.deleteByRollNumber(999);
        System.out.println("\nStudent records after deleting non-existent record:");
        studentList.displayAll();
    }
}

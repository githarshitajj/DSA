package linkedListQuestions;

import java.util.Date;

class Task {
    int taskId;
    String taskName;
    int priority; // Higher value means higher priority
    Date dueDate;

    public Task(int taskId, String taskName, int priority, Date dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + ", Name: " + taskName +
                ", Priority: " + priority + ", Due Date: " + dueDate;
    }
}

class TaskNode {
    Task data;
    TaskNode next;

    public TaskNode(Task data) {
        this.data = data;
        this.next = null;
    }
}

class CircularTaskList {
    TaskNode head;
    TaskNode currentTask; // Pointer to the current task

    public CircularTaskList() {
        head = null;
        currentTask = null;
    }

    // 1. Add Operations

    // Add at the beginning
    public void addFirst(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
            newNode.next = head; // Circular
            currentTask = head; // Initialize currentTask
        } else {
            TaskNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            newNode.next = head;
            temp.next = newNode;
            head = newNode;
        }
    }

    // Add at the end
    public void addLast(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
            newNode.next = head; // Circular
            currentTask = head;
        } else {
            TaskNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head; // Circular
        }
    }

    // Add at a specific position (0-indexed)
    public void addAtPosition(Task task, int position) {
        if (position < 0) {
            System.out.println("Invalid position. Position must be non-negative.");
            return;
        }

        if (position == 0) {
            addFirst(task);
            return;
        }

        TaskNode newNode = new TaskNode(task);
        TaskNode current = head;
        int count = 0;

        // Traverse to the node *before* the desired position
        while (current != null && count < position - 1) {
            if(current.next == head){ // Check if we reached end
                break;
            }
            current = current.next;

            count++;
        }

        if (current == null) { //if position greater than list length
            System.out.println("Invalid position.  Position exceeds list length.");
            return;
        }

        //Insert at the node
        if(current.next == head && count < position -1){ // Handle adding at the end when position is exact length.
            addLast(task);
        }
        else{
            newNode.next = current.next;
            current.next = newNode;
        }
    }


    // 2. Remove Operation

    // Remove by Task ID
    public void removeById(int taskId) {
        if (head == null) {
            System.out.println("List is empty. Cannot remove.");
            return;
        }

        TaskNode current = head;
        TaskNode prev = null;

        // Find the node and its predecessor
        do {
            if (current.data.taskId == taskId) {
                break;
            }
            prev = current;
            current = current.next;
        } while (current != head); // Traverse the entire list


        if (current.data.taskId != taskId) {
            System.out.println("Task with ID " + taskId + " not found.");
            return;
        }

        // If removing the head and it's the only node
        if (current == head && current.next == head) {
            head = null;
            currentTask = null;
        }
        // If removing the head (but not the only node)
        else if (current == head) {
            TaskNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            head = head.next;
            temp.next = head;  // Update the last node's pointer
            if (currentTask == current) { //If current task is the one being deleted, move it.
                currentTask = head;
            }
        }
        // If removing any other node
        else {
            prev.next = current.next;
            if (currentTask == current) {
                currentTask = prev.next; //Update the current task pointer if deleting current task
            }
        }

        System.out.println("Task with ID " + taskId + " removed.");
    }


    // 3. View Current and Move to Next

    // View the current task
    public void viewCurrentTask() {
        if (currentTask == null) {
            System.out.println("No tasks in the scheduler.");
        } else {
            System.out.println("Current Task: " + currentTask.data);
        }
    }

    // Move to the next task
    public void moveToNextTask() {
        if (currentTask == null) {
            System.out.println("No tasks in the scheduler.");
        } else {
            currentTask = currentTask.next;
            System.out.println("Moved to the next task.");
        }
    }


    // 4. Display All Tasks

    // Display all tasks starting from the head
    public void displayAllTasks() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        TaskNode temp = head;
        System.out.println("All Tasks:");
        do {
            System.out.println(temp.data);
            temp = temp.next;
        } while (temp != head);
    }


    // 5. Search Operation

    // Search by Priority
    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        TaskNode temp = head;
        boolean found = false;
        System.out.println("Tasks with priority " + priority + ":");
        do {
            if (temp.data.priority == priority) {
                System.out.println(temp.data);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No tasks found with priority: " + priority);
        }
    }
}
public class TaskScheduler {
    public static void main(String[] args) {
        CircularTaskList taskList = new CircularTaskList();

        // Adding tasks
        taskList.addLast(new Task(1, "Write report", 2, new Date(2024, 1, 15))); // Note: Date constructor is deprecated
        taskList.addLast(new Task(2, "Prepare presentation", 3, new Date(2024, 1, 10)));
        taskList.addFirst(new Task(3, "Attend meeting", 1, new Date(2024, 1, 5)));
        taskList.addAtPosition(new Task(4, "Code review", 2, new Date(2024, 1, 12)), 2);


        // Displaying all tasks
        taskList.displayAllTasks();

        // Viewing and moving through tasks
        taskList.viewCurrentTask();
        taskList.moveToNextTask();
        taskList.viewCurrentTask();

        // Removing a task
        taskList.removeById(2);
        System.out.println("\nAfter removing task with ID 2:");
        taskList.displayAllTasks();

        // Search by priority
        System.out.println("\nSearching for tasks with priority 2");
        taskList.searchByPriority(2);

        //Add at specific position
        taskList.addAtPosition(new Task(5, "Debug code", 3, new Date(2024, 1, 18)), 0); // Add at beginning
        System.out.println("\nAfter adding task 'Debug code' at the beginning:");
        taskList.displayAllTasks();

    }
}

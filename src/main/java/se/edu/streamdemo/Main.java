package se.edu.streamdemo;

import se.edu.streamdemo.data.DataManager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager");
        //relative path, ./ means with respective to the current folder, java is able to resolve the / -> \ to the local
        DataManager dataManager = new DataManager("./data/data.txt");
        // C:/users\Jun Jie <<< absolute path - may not access by others
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing all data ...");
        printAllData(tasksData);
        printAllDataUsingStream(tasksData);

        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);
        printDeadlinesUsingStream(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines using stream: "
                + countDeadlinesUsingStream(tasksData));
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) { //if t is a deadline type, you count it
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesUsingStream(ArrayList<Task> tasks) {
        int countf = (int) tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .count();

        return countf;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("Printing data using iteration... ");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printAllDataUsingStream(ArrayList<Task> tasks) {
        System.out.println("Printing data using stream... ");
        tasks.stream()
                .forEach(System.out::println); //Perform an action on each element
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        System.out.println("Printing deadlines using streams... ");
        tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .forEach(System.out::println);
    }

}

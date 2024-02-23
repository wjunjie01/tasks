package se.edu.streamdemo;

import se.edu.streamdemo.data.DataManager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager");
        //relative path, ./ means with respective to the current folder, java is able to resolve the / -> \ to the local
        DataManager dataManager = new DataManager("./data/data.txt");
        // C:/users\Jun Jie <<< absolute path - may not access by others
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing all data ...");
        printAllData(tasksData);

        System.out.println("Printing deadlines ... (before sorting)");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

        System.out.println("Printing deadlines ... (after sorting)");
        printDeadlinesUsingStream(tasksData);

        ArrayList<Task> filteredList = filterByString(tasksData, "11"); //Filter all tasks with string 11 inside
        printAllData(filteredList);
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

    public static void printAllData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        tasks.stream()
                .filter((t) -> t instanceof Deadline) //creates a stream again and again
                .sorted((t1, t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()))
                .forEach(System.out::println); //until it reaches the end
    }

    public static ArrayList<Task> filterByString(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .collect(toList());

        return filteredList;
    }
}

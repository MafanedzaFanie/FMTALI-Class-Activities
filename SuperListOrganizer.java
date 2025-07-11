import java.util.*;

public class SuperListOrganizer {

    // Using LinkedHashMap to maintain insertion order of categories
    // and store a List of tasks for each category.
    // The List will be sorted and checked for duplicates.
    private static Map<String, List<String>> superlist = new LinkedHashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Dynamic Superlist Organizer!");
        System.out.println("Enter 'done' for category name to finish, or 'delete' to remove a task.");

        while (true) {
            System.out.print("\nEnter category name (e.g., 'Shopping', 'Homework', 'Games') or 'done'/'delete': ");
            String categoryInput = scanner.nextLine().trim();

            if (categoryInput.equalsIgnoreCase("done")) {
                break; // Exit the loop if user types "done"
            } else if (categoryInput.equalsIgnoreCase("delete")) {
                deleteTask(); // Call method to delete a task
            } else {
                // Normalize the category name (capitalize first letter)
                String formattedCategory = formatString(categoryInput);

                System.out.print("Enter tasks (comma-separated, e.g., 'buy milk, grab eggs, pick apples'): ");
                String tasksInput = scanner.nextLine().trim();

                // Split tasks by comma and trim whitespace
                String[] tasksArray = tasksInput.split(",");

                // Call addTasks method with the formatted category and tasks
                addTasks(formattedCategory, tasksArray);
            }
        }

        displaySuperlist(); // Display the final organized superlist
        scanner.close(); // Close the scanner
        System.out.println("\nThank you for using the Superlist Organizer!");
    }

    /**
     * Adds tasks to a specified category, handling formatting, duplicates, and sorting.
     *
     * @param category The category name.
     * @param tasks    A variable argument list of tasks to add.
     */
    public static void addTasks(String category, String... tasks) {
        // Get the list of tasks for the given category, or create a new one if it doesn't exist
        List<String> categoryTasks = superlist.getOrDefault(category, new ArrayList<>());

        for (String task : tasks) {
            String formattedTask = formatString(task); // Format each task
            // Prevent duplicate task entries (case-insensitive check)
            boolean isDuplicate = false;
            for (String existingTask : categoryTasks) {
                if (existingTask.equalsIgnoreCase(formattedTask)) {
                    isDuplicate = true;
                    System.out.println(String.format("  '%s' is already in '%s'. Skipping.", formattedTask, category));
                    break;
                }
            }
            if (!isDuplicate && !formattedTask.isEmpty()) { // Only add if not duplicate and not empty
                categoryTasks.add(formattedTask);
            }
        }

        // Sort tasks alphabetically within the category
        Collections.sort(categoryTasks);

        // Put the updated (or new) category tasks back into the superlist map
        superlist.put(category, categoryTasks);
        System.out.println(String.format("Tasks added/updated for category: %s", category));
    }

    /**
     * Allows the user to delete a specific task from a category.
     */
    private static void deleteTask() {
        System.out.print("Enter the category name to delete a task from: ");
        String categoryToDeleteFrom = formatString(scanner.nextLine().trim());

        if (!superlist.containsKey(categoryToDeleteFrom)) {
            System.out.println(String.format("Category '%s' not found.", categoryToDeleteFrom));
            return;
        }

        System.out.print("Enter the task name to delete from '" + categoryToDeleteFrom + "': ");
        String taskToDelete = formatString(scanner.nextLine().trim());

        List<String> tasks = superlist.get(categoryToDeleteFrom);
        boolean removed = false;
        // Iterate to find and remove the task (case-insensitive)
        Iterator<String> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            String currentTask = iterator.next();
            if (currentTask.equalsIgnoreCase(taskToDelete)) {
                iterator.remove();
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println(String.format("Task '%s' deleted from '%s'.", taskToDelete, categoryToDeleteFrom));
            // If the category becomes empty after deletion, remove the category itself
            if (tasks.isEmpty()) {
                superlist.remove(categoryToDeleteFrom);
                System.out.println(String.format("Category '%s' is now empty and has been removed.", categoryToDeleteFrom));
            }
        } else {
            System.out.println(String.format("Task '%s' not found in category '%s'.", taskToDelete, categoryToDeleteFrom));
        }
    }

    /**
     * Formats a string by capitalizing its first letter.
     *
     * @param input The string to format.
     * @return The formatted string, or an empty string if input is null/empty.
     */
    private static String formatString(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }
        String trimmedInput = input.trim();
        // Capitalize the first letter and append the rest of the string
        return trimmedInput.substring(0, 1).toUpperCase() + trimmedInput.substring(1).toLowerCase();
    }

    /**
     * Displays all categories and their tasks in a neat, formatted way.
     */
    public static void displaySuperlist() {
        if (superlist.isEmpty()) {
            System.out.println("\nYour Superlist is empty.");
            return;
        }

        StringBuilder sb = new StringBuilder("\nYour Superlist:\n");
        // Iterate through each category in the order they were added
        for (Map.Entry<String, List<String>> entry : superlist.entrySet()) {
            String category = entry.getKey();
            List<String> tasks = entry.getValue();

            sb.append(String.format("Category: %s\n", category)); // Format category line

            if (tasks.isEmpty()) {
                sb.append("  - No tasks\n"); // Indicate if category has no tasks
            } else {
                for (String task : tasks) {
                    sb.append(String.format("  - %s\n", task)); // Format each task line
                }
            }
        }
        System.out.println(sb.toString()); // Print the complete formatted string
    }
}

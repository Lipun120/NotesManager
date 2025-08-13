package TextBasedNote;

import java.io.*;
import java.util.Scanner;

public class NotesManager {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {
            System.out.println("\n===== Notes Manager =====");
            System.out.println("1. Add Note");
            System.out.println("2. View All Notes");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            if (choice == 1) {
                addNote(scanner);
            } else if (choice == 2) {
                viewNotes();
            } else if (choice == 3) {
                System.out.println("Exiting Notes Manager...");
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }

    // Write note to file
    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) { // append mode
            fw.write(note + System.lineSeparator());
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read notes from file
    private static void viewNotes() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No notes found!");
            return;
        }

        System.out.println("\n--- Your Notes ---");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(count++ + ". " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}


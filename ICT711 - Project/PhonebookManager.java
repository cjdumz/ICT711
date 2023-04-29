import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PhonebookManager {

    public static void main(String[] args) {

        // check if both files are provided as command line arguments
        if (args.length != 2) {
            System.out.println("Usage: java PhonebookManager phonebook.txt instruction.txt");
            return;
        }

        String phonebookFilename = args[0];
        String instructionFilename = args[1];

        ArrayList<String> phonebook = readPhonebook(phonebookFilename);

        if (phonebook == null) {
            System.out.println("Error: Failed to read phonebook file " + phonebookFilename);
            return;
        }

        ArrayList<String> instructions = readInstructions(instructionFilename);

        if (instructions == null) {
            System.out.println("Error: Failed to read instruction file " + instructionFilename);
            return;
        }

        ArrayList<String> results = executeInstructions(phonebook, instructions);

        if (results == null) {
            System.out.println("Error: Failed to execute instructions");
            return;
        }

        boolean success = savePhonebook(phonebookFilename, results);

        if (!success) {
            System.out.println("Error: Failed to save phonebook to file " + phonebookFilename);
            return;
        }

        System.out.println("Phonebook updated successfully.");
    }

    /**
     * Reads the phonebook file and returns an ArrayList of its contents.
     */
    private static ArrayList<String> readPhonebook(String filename) {
        ArrayList<String> phonebook = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                phonebook.add(line);
            }
            return phonebook;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads the instruction file and returns an ArrayList of its contents.
     */
    private static ArrayList<String> readInstructions(String filename) {
        ArrayList<String> instructions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                instructions.add(line);
            }
            return instructions;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Executes the instructions on the phonebook and returns an ArrayList of the results.
     */
    private static ArrayList<String> executeInstructions(ArrayList<String> phonebook, ArrayList<String> instructions) {
        ArrayList<String> results = new ArrayList<>();

        for (String instruction : instructions) {
            String[] parts = instruction.split(" ");
            String command = parts[0];

            if (command.equals("add")) {
                if (parts.length != 4) {
                    results.add("Error: Invalid add instruction " + instruction);
                } else {
                    String name = parts[1];
                    String phone = parts[2];
                    String email = parts[3];
                    String record = String.format("%-20s%-20s%-30s", name, phone, email);
                    phonebook.add(record);
                    results.add("Record added: " + record);
                }
            } else if (command.equals("delete")) {
                if (parts.length != 2) {
                    results.add("Error: Invalid delete instruction " + instruction);
                } else {
                    String name = parts[1];
                    boolean deleted = false;
                    for

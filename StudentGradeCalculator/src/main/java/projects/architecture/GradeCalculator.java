package projects.architecture;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GradeCalculator {

    public void calculate() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("===Grade Calculator===");


        System.out.print("Enter Source File Path: ");
        String sourceFile = scanner.nextLine();

        System.out.print("Enter Target File Path: ");
        String targetFile = scanner.nextLine();

        System.out.println("Processing...");

        readAndSave(sourceFile, targetFile);
        scanner.close();

    }


    public void readAndSave(String sourceFile, String targetFile) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(sourceFile)));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetFile))) {

            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split("[,:]");

                if (line.length < 4) {
                    System.out.println("Skipping malformed line: " + Arrays.toString(line));
                    continue;
                }

                int grade1 = Integer.parseInt(line[1].trim());
                int grade2 = Integer.parseInt(line[2].trim());
                int grade3 = Integer.parseInt(line[3].trim());

                bufferedWriter.write(intGradeToString(line[0].trim(), grade1, grade2, grade3));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error happened while processing file(s)");
        }
        System.out.println("Process completed successfully");
    }


    public String intGradeToString(String name, int grade1, int grade2, int grade3) {
        double finalGrade = ((double) (grade1 + grade2 + grade3)) / 3;
        if (finalGrade >= 91) return name + ": Got A\n";
        else if (finalGrade >= 81) return name + ": Got B\n";
        else if (finalGrade >= 71) return name + ": Got C\n";
        else if (finalGrade >= 61) return name + ": Got D\n";
        else if (finalGrade >= 51) return name + ": Got E\n";
        else if (finalGrade >= 41) return name + ": Got F\n";
        else return name + ": Failed\n";

    }
}

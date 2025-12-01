import java.io.*;
import java.util.*;

/**
 * Utility class for parsing student data from input files.
 * Reads student information from a formatted text file and creates
 * UniversityStudent objects.
 * 
 * @author LonghornNetwork Team
 */
public class DataParser {
    /** Array of expected field names for student records */
    private static final String[] recordKeys = {
        "Name",
        "Age",
        "Gender",
        "Year",
        "Major",
        "GPA",
        "RoommatePreferences",
        "PreviousInternships"
    };

    /**
     * Parses student information from a file and returns a list of UniversityStudent objects.
     * The input file should follow the format specified in the project documentation.
     * 
     * @param filename The path to the input file containing student data
     * @return A list of UniversityStudent objects parsed from the file
     * @throws IOException If there is an error reading the file or if the file format is invalid
     */
    public static List<UniversityStudent> parseStudents(String filename) throws IOException {
        List<UniversityStudent> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.equals("Student:")) {
                    String studentName = null;
                    int studentAge = Integer.MIN_VALUE;
                    String studentGender = null;
                    int studentYear = Integer.MIN_VALUE;
                    String studentMajor = null;
                    double studentGpa = Double.MIN_VALUE;
                    ArrayList<String> prefs = null;
                    ArrayList<String> internships = null;
                    
                    for (int i = 0; i < recordKeys.length; i++) {
                        String keyValueLine = reader.readLine().trim();
                        String[] parts = keyValueLine.split(":", 2);
                        
                        if (parts.length <= 1) {
                            System.err.println("Parsing error: Incorrect format in line: '" + keyValueLine + "'. Expected format 'Name: <value>'.");
                            return result;
                        }
                        
                        String fieldName = parts[0].trim();
                        String fieldValue = parts[1].trim();

                        if (!fieldName.equals(recordKeys[i])) {
                            System.err.println("Parsing error: Missing required field '" + recordKeys[i] + "' in student entry for " + studentName + ".");
                            return result;
                        }

                        switch (fieldName) {
                            case "Name":
                                studentName = fieldValue;
                                break;

                            case "Age":
                                try {
                                    studentAge = Integer.parseInt(fieldValue);
                                } catch (NumberFormatException e) {
                                    System.err.println("Number format error: Invalid number format for age: '" + fieldValue + "' in student entry for " + studentName + ".");
                                    return result;
                                }
                                break;

                            case "Gender":
                                studentGender = fieldValue;
                                break;

                            case "Year":
                                try {
                                    studentYear = Integer.parseInt(fieldValue);
                                } catch (NumberFormatException e) {
                                    System.err.println("Number format error: Invalid number format for year: '" + fieldValue + "' in student entry for " + studentName + ".");
                                    return result;
                                }
                                break;

                            case "Major":
                                studentMajor = fieldValue;
                                break;

                            case "GPA":
                                try {
                                    studentGpa = Double.parseDouble(fieldValue);
                                } catch (NumberFormatException e) {
                                    System.err.println("Number format error: Invalid number format for GPA: '" + fieldValue + "' in student entry for " + studentName + ".");
                                    return result;
                                }
                                break;

                            case "RoommatePreferences":
                                prefs = parseCommaSeparatedString(fieldValue);
                                break;

                            case "PreviousInternships":
                                internships = parseCommaSeparatedString(fieldValue);
                                break;

                            default:
                                System.err.println("Missing case");
                                break;
                        }
                    }
                    UniversityStudent student = new UniversityStudent(studentName, studentAge, studentGender, 
                            studentYear, studentMajor, studentGpa, prefs, internships);
                    result.add(student);
                }
            }
        } catch (IOException e) {
            throw e;
        }

        return result;
    }

    /**
     * Parses a comma-separated string into a list of trimmed strings.
     * Handles the special case where the input is "None" by returning an empty list.
     *
     * @param in the comma-separated string to parse
     * @return an ArrayList of trimmed string values, or an empty list if input is "None"
     */
    private static ArrayList<String> parseCommaSeparatedString(String input) {
        ArrayList<String> resultList = new ArrayList<>();

        if (input.equals("None")) {
            return resultList;
        }

        String[] tokens = input.split(",");
        for (String token : tokens) {
            resultList.add(token.trim());
        }
        return resultList;
    }
}

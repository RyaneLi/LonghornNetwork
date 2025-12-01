import java.util.*;

/**
 * Concrete implementation of a Student representing a university student.
 * Extends the abstract Student class and implements the connection strength
 * calculation based on roommate status, shared internships, major, and age.
 * 
 * @author LonghornNetwork Team
 */
public class UniversityStudent extends Student {
    /** Current assigned roommate */
    private UniversityStudent currentRoommate;

    /**
     * Constructs a new UniversityStudent with the specified attributes.
     * 
     * @param name The name of the student
     * @param age The age of the student
     * @param gender The gender of the student
     * @param year The academic year (1-4)
     * @param major The major field of study
     * @param gpa The grade point average
     * @param roommatePreferences List of preferred roommate names
     * @param previousInternships List of previous internship company names
     */
    public UniversityStudent(String name, int age, String gender, int year, 
                            String major, double gpa, 
                            List<String> roommatePreferences, 
                            List<String> previousInternships) {
        this.currentRoommate = null;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.year = year;
        this.major = major;
        this.gpa = gpa;
        this.roommatePreferences = roommatePreferences == null ? new ArrayList<>() : new ArrayList<>(roommatePreferences);
        this.previousInternships = previousInternships == null ? new ArrayList<>() : new ArrayList<>(previousInternships);
    }
    
    /**
     * Calculates the connection strength between this university student and another student.
     * The calculation is based on:
     * - Roommate status: +4 if they are roommates
     * - Shared internships: +3 for each shared internship
     * - Same major: +2 if they share the same major
     * - Same age: +1 if they are the same age
     * 
     * @param other The other student to calculate connection strength with
     * @return An integer representing the connection strength (0 or higher)
     */
    @Override
    public int calculateConnectionStrength(Student other) {
        int strength = 0;

        if (other instanceof UniversityStudent otherStudent) {
            // Check if they are roommates
            if (this.currentRoommate != null && this.currentRoommate.equals(otherStudent)) {
                strength += 4;
            }

            // Count shared internships
            for (String internship : this.previousInternships) {
                if (otherStudent.previousInternships.contains(internship)) {
                    strength += 3;
                }
            }

            // Check if same major
            if (this.major != null && this.major.equals(otherStudent.major)) {
                strength += 2;
            }

            // Check if same age
            if (this.age == otherStudent.age) {
                strength += 1;
            }
        }
        
        return strength;
    }
    
    /**
     * Gets the current roommate of this student.
     * 
     * @return The roommate UniversityStudent, or null if unpaired
     */
    public UniversityStudent getRoommate() {
        return currentRoommate;
    }
    
    /**
     * Sets the roommate for this student.
     * 
     * @param roommate The UniversityStudent to set as roommate
     */
    public void setRoommate(UniversityStudent roommate) {
        currentRoommate = roommate;
    }
    
    /**
     * Returns a string representation of this UniversityStudent.
     * 
     * @return A formatted string with all student attributes
     */
    @Override
    public String toString() {
        return String.format("UniversityStudent{name='%s', age=%d, gender='%s', year=%d, major='%s', GPA=%.1f, roommatePreferences=%s, previousInternships=%s}",
                name, age, gender, year, major, gpa, roommatePreferences, previousInternships);
    }
}


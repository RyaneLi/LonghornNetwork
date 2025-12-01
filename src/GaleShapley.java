import java.util.*;

/**
 * Implements the Gale-Shapley stable matching algorithm for roommate assignment.
 * Pairs students based on their roommate preferences, ensuring stable matches
 * where no two students would prefer each other over their current assignments.
 * 
 * @author LonghornNetwork Team
 */
public class GaleShapley {
    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private GaleShapley() {
    }

    /**
     * Assigns roommates to students using the Gale-Shapley stable matching algorithm.
     * Each student is matched with their highest-priority preferred roommate if
     * mutually possible. Students without preferences or with incomplete preference
     * lists may remain unpaired.
     * 
     * @param students The list of UniversityStudent objects to match
     */
    public static void assignRoommates(List<UniversityStudent> students) {
        Map<UniversityStudent, UniversityStudent> pairings = new HashMap<>();
        Map<UniversityStudent, Integer> proposalIndices = new HashMap<>();
        Map<String, UniversityStudent> nameMap = new HashMap<>();

        // Initialize maps
        for (UniversityStudent student : students) {
            nameMap.put(student.name, student);
            proposalIndices.put(student, 0);
        }

        // Initialize queue with students who have preferences
        Queue<UniversityStudent> unpairedQueue = new LinkedList<>();
        for (UniversityStudent student : students) {
            if (!student.roommatePreferences.isEmpty()) {
                unpairedQueue.offer(student);
            }
        }

        // Main matching loop
        while (!unpairedQueue.isEmpty()) {
            UniversityStudent proposer = unpairedQueue.poll();
            
            // Skip if already paired
            if (proposer.getRoommate() != null) {
                continue;
            }

            int currentIndex = proposalIndices.get(proposer);
            if (currentIndex >= proposer.roommatePreferences.size()) {
                continue;
            }

            String targetName = proposer.roommatePreferences.get(currentIndex);
            proposalIndices.put(proposer, currentIndex + 1);
            UniversityStudent target = nameMap.get(targetName);
            
            // Handle invalid preference name
            if (target == null) {
                if (proposalIndices.get(proposer) < proposer.roommatePreferences.size()) {
                    unpairedQueue.offer(proposer);
                }
                continue;
            }

            // Check if target has proposer in their preferences
            if (!target.roommatePreferences.contains(proposer.name)) {
                if (proposalIndices.get(proposer) < proposer.roommatePreferences.size()) {
                    unpairedQueue.offer(proposer);
                }
                continue;
            }

            // Handle pairing logic
            if (target.getRoommate() == null) {
                // Target is free, create pairing
                pairings.put(proposer, target);
                pairings.put(target, proposer);
                target.setRoommate(proposer);
                proposer.setRoommate(target);
            } else {
                // Target is already paired, check if proposer is preferred
                UniversityStudent existingRoommate = target.getRoommate();
                int existingIndex = target.roommatePreferences.indexOf(existingRoommate.name);
                int proposerIndex = target.roommatePreferences.indexOf(proposer.name);
                
                if (proposerIndex < existingIndex) {
                    // Proposer is preferred, break existing pairing
                    pairings.remove(existingRoommate);
                    unpairedQueue.offer(existingRoommate);
                    existingRoommate.setRoommate(null);

                    // Create new pairing
                    pairings.put(proposer, target);
                    pairings.put(target, proposer);
                    target.setRoommate(proposer);
                    proposer.setRoommate(target);
                } else {
                    // Existing roommate is preferred, keep trying
                    if (proposalIndices.get(proposer) < proposer.roommatePreferences.size()) {
                        unpairedQueue.offer(proposer);
                    }
                }
            }
        }

        // Print results
        System.out.println("\nRoommate Pairings (Gale-Shapely):");
        Set<UniversityStudent> alreadyPrinted = new HashSet<>();
        for (UniversityStudent student : pairings.keySet()) {
            UniversityStudent partner = pairings.get(student);
            if (!alreadyPrinted.contains(student) && !alreadyPrinted.contains(partner)) {
                System.out.println(student.name + " paired with " + partner.name);
                alreadyPrinted.add(student);
                alreadyPrinted.add(partner);
            }
        }
    }
}

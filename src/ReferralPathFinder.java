import java.util.*;

/**
 * Finds referral paths to students who have interned at a specific company
 * using Dijkstra's algorithm. The algorithm treats stronger connections as
 * shorter paths by inverting connection weights.
 * 
 * @author LonghornNetwork Team
 */
public class ReferralPathFinder {
    /** The student graph used for finding referral paths */
    private StudentGraph graph;
    
    /**
     * Constructs a ReferralPathFinder with the given student graph.
     * 
     * @param graph The StudentGraph representing student connections
     */
    public ReferralPathFinder(StudentGraph graph) {
        this.graph = graph;
    }

    /**
     * Finds the shortest referral path from a starting student to any student
     * who has interned at the target company. Uses Dijkstra's algorithm with
     * inverted edge weights to prioritize stronger connections.
     * 
     * @param start The starting UniversityStudent
     * @param targetCompany The name of the company to find a referral path to
     * @return A list of UniversityStudent objects representing the referral path,
     *         or an empty list if no path exists
     */
    public List<UniversityStudent> findReferralPath(UniversityStudent start, String targetCompany) {
        Map<UniversityStudent, Double> distances = new HashMap<>();
        Map<UniversityStudent, UniversityStudent> predecessors = new HashMap<>();
        Set<UniversityStudent> processed = new HashSet<>();

        // Initialize distances
        for (UniversityStudent node : graph.getAllNodes()) {
            distances.put(node, Double.MAX_VALUE);
            predecessors.put(node, null);
        }
        distances.put(start, 0.0);

        // Priority queue for Dijkstra's algorithm (using inverted weights)
        PriorityQueue<UniversityStudent> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        queue.add(start);

        while (!queue.isEmpty()) {
            UniversityStudent current = queue.poll();
            
            // Skip if already processed
            if (processed.contains(current)) {
                continue;
            }
            processed.add(current);

            // Check if current student has target company internship
            for (String internship : current.previousInternships) {
                if (internship.equalsIgnoreCase(targetCompany)) {
                    // Reconstruct path
                    List<UniversityStudent> referralPath = new ArrayList<>();
                    UniversityStudent pathNode = current;
                    while (pathNode != null) {
                        referralPath.add(pathNode);
                        pathNode = predecessors.get(pathNode);
                    }
                    Collections.reverse(referralPath);
                    return referralPath;
                }
            }

            // Explore neighbors
            for (StudentGraph.Edge edge : graph.getNeighbors(current)) {
                UniversityStudent neighbor = edge.neighbor;
                if (processed.contains(neighbor)) {
                    continue;
                }

                // Use inverted weight (stronger connection = shorter path)
                double newDistance = distances.get(current) + (1.0 / edge.weight);
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    predecessors.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return new ArrayList<>();
    }
}

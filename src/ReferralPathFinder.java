import java.util.*;

/**
 * Finds referral paths to students who have interned at a specific company
 * using Dijkstra's algorithm. The algorithm treats stronger connections as
 * shorter paths by inverting connection weights.
 * 
 * @author LonghornNetwork Team
 */
public class ReferralPathFinder {    
    /**
     * Constructs a ReferralPathFinder with the given student graph.
     * 
     * @param graph The StudentGraph representing student connections
     */
    public ReferralPathFinder(StudentGraph graph) {
        // constructor
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
        return new ArrayList<>();
    }
}

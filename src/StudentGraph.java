import java.util.*;

/**
 * Represents a weighted graph of students and their connections.
 * Uses an adjacency list structure where each student (node) maps to a list of edges
 * connecting to other students with corresponding connection strength weights.
 * This graph is used for both pod formation (Prim's algorithm) and referral path
 * finding (Dijkstra's algorithm).
 * 
 * @author LonghornNetwork Team
 */
public class StudentGraph {
    /**
     * Constructs a StudentGraph from a list of students.
     * 
     * @param students The list of UniversityStudent objects to add to the graph
     */
    public StudentGraph(List<UniversityStudent> students) {
    }
    
    /**
     * Represents an edge in the student graph, connecting two students with a weight.
     */
    public static class Edge {
        /** The neighboring student connected by this edge */
        public UniversityStudent neighbor;
        /** The connection strength weight of this edge */
        public int weight;
    }
}


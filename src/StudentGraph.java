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
     * Represents a weighted edge in the student graph connecting two students.
     */
    public class Edge {
        /** The neighboring student in this edge */
        public UniversityStudent neighbor;
        /** The weight of the connection between students */
        public int weight;

        /**
         * Create an edge record pointing to a neighboring student with the
         * provided weight.
         *
         * @param neighbor target neighbor node
         * @param weight   integer edge weight
         */
        public Edge(UniversityStudent neighbor, int weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + neighbor.name + ", " + weight + ")";
        }
    }

    /** Adjacency list representation of the student graph */
    private Map<UniversityStudent, List<Edge>> adjacencyList = new HashMap<>();

    /**
     * Constructs a StudentGraph from a list of students.
     * 
     * @param students The list of UniversityStudent objects to add to the graph
     */
    public StudentGraph(List<UniversityStudent> students) {
        // Initialize adjacency list for all students
        for (UniversityStudent student : students) {
            adjacencyList.put(student, new ArrayList<>());
        }

        // Build edges between all pairs of students
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                UniversityStudent student1 = students.get(i);
                UniversityStudent student2 = students.get(j);
                int connectionWeight = student1.calculateConnectionStrength(student2);
                
                if (connectionWeight > 0) {
                    adjacencyList.get(student1).add(new Edge(student2, connectionWeight));
                    adjacencyList.get(student2).add(new Edge(student1, connectionWeight));
                }
            }
        }
    }

    /**
     * Returns all student nodes present in the graph.
     *
     * @return a set containing all UniversityStudent nodes
     */
    public Set<UniversityStudent> getAllNodes() {
        return adjacencyList.keySet();
    }

    /**
     * Returns the neighbor list for the given student.
     *
     * @param student the student whose neighbors are requested
     * @return a list of Edge objects
     */
    public List<Edge> getNeighbors(UniversityStudent student) {
        return adjacencyList.get(student);
    }

    /**
     * Prints the graph.
     */
    public void displayGraph() {
        System.out.println("\nStudent Graph:");
        for (UniversityStudent student : adjacencyList.keySet()) {
            System.out.println(student.name + " -> " + adjacencyList.get(student));
        }
    }
}


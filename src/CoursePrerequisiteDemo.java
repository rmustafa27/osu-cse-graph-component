import components.graph.Graph;
import components.graph.Graph1L;
import components.set.Set;

/**
 * Proof-of-concept course planner represented using a Graph.
 */
public class CoursePlanner {

    /**
     * Graph where vertices are courses and edges represent prerequisite flow.
     */
    private Graph<String> prerequisites;

    /**
     * Creates an empty course planner.
     */
    public CoursePlanner() {
        this.prerequisites = new Graph1L<>();
    }

    /**
     * Adds a course if it is not already present.
     *
     * @param course
     *            the course to add
     */
    public void addCourse(String course) {
        assert course != null : "Violation of: course is not null";

        if (!this.prerequisites.containsVertex(course)) {
            this.prerequisites.addVertex(course);
        }
    }

    /**
     * Adds a prerequisite relationship.
     *
     * @param prerequisite
     *            the prerequisite course
     * @param course
     *            the course depending on the prerequisite
     */
    public void addPrerequisite(String prerequisite, String course) {
        assert prerequisite != null : "Violation of: prerequisite is not null";
        assert course != null : "Violation of: course is not null";

        this.addCourse(prerequisite);
        this.addCourse(course);
        this.prerequisites.addEdge(prerequisite, course);
    }

    /**
     * Reports whether one course can eventually lead to another.
     *
     * @param start
     *            the starting course
     * @param target
     *            the target course
     * @return true iff target is reachable from start
     */
    public boolean eventuallyLeadsTo(String start, String target) {
        assert start != null : "Violation of: start is not null";
        assert target != null : "Violation of: target is not null";
        assert this.prerequisites.containsVertex(start)
                : "Violation of: start is a known course";
        assert this.prerequisites.containsVertex(target)
                : "Violation of: target is a known course";

        return this.prerequisites.isReachable(start, target);
    }

    /**
     * Reports all courses reachable from the given course.
     *
     * @param course
     *            the starting course
     * @return all courses reachable from course
     */
    public Set<String> coursesAfter(String course) {
        assert course != null : "Violation of: course is not null";
        assert this.prerequisites.containsVertex(course)
                : "Violation of: course is a known course";

        return this.prerequisites.reachableFrom(course);
    }

    /**
     * Demonstrates the course planner use case.
     *
     * @param args
     *            command-line arguments, unused
     */
    public static void main(String[] args) {
        CoursePlanner planner = new CoursePlanner();

        planner.addPrerequisite("Software I", "Software II");
        planner.addPrerequisite("Software II", "Data Structures");
        planner.addPrerequisite("Data Structures", "Algorithms");
        planner.addPrerequisite("Foundations I", "Foundations II");
        planner.addPrerequisite("Foundations II", "Algorithms");

        System.out.println("Courses after Software I:");
        System.out.println(planner.coursesAfter("Software I"));

        System.out.println("Can Software I eventually lead to Algorithms?");
        System.out.println(planner.eventuallyLeadsTo("Software I", "Algorithms"));
    }
}
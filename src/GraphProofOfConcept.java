import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Proof-of-concept (MVP) for a directed, unweighted Graph component.
 *
 * <p>
 * This file intentionally ignores the OSU software sequence discipline layering
 * (Standard/kernel/secondary hierarchy). The purpose is to demonstrate that the
 * design is feasible and valuable from a client perspective.
 * </p>
 *
 * <p>
 * Cybersecurity/network model: vertices = hosts/services; edges = allowed
 * connections (reachability/trust).
 * </p>
 *
 * @param <V>
 *            vertex type
 */
public final class GraphProofOfConcept<V> {

    /** Adjacency list: each vertex maps to its outgoing neighbors. */
    private final Map<V, Set<V>> adj;

    /** Tracks number of edges for O(1) size(). */
    private int edgeCount;

    /** Constructs an empty graph. */
    public GraphProofOfConcept() {
        this.adj = new HashMap<>();
        this.edgeCount = 0;
    }

    /*
     * ========================= Core (kernel-like) methods
     * =========================
     */

    /** Adds vertex v if not present. */
    public void addVertex(V v) {
        requireNonNull(v, "vertex");
        this.adj.computeIfAbsent(v, k -> new HashSet<>());
    }

    /**
     * Removes vertex v if present, and removes all edges incident from/to it.
     */
    public void removeVertex(V v) {
        requireNonNull(v, "vertex");
        if (!this.adj.containsKey(v)) {
            return;
        }

        // Remove all outgoing edges from v
        Set<V> out = this.adj.get(v);
        this.edgeCount -= out.size();

        // Remove incoming edges to v
        for (Map.Entry<V, Set<V>> entry : this.adj.entrySet()) {
            V u = entry.getKey();
            if (!Objects.equals(u, v)) {
                boolean removed = entry.getValue().remove(v);
                if (removed) {
                    this.edgeCount--;
                }
            }
        }

        // Finally remove the vertex
        this.adj.remove(v);
    }

    /**
     * Adds directed edge (from -> to).
     *
     * <p>
     * Policy for PoC: vertices must already exist.
     * </p>
     */
    public void addEdge(V from, V to) {
        requireNonNull(from, "from");
        requireNonNull(to, "to");
        this.requireVertexExists(from);
        this.requireVertexExists(to);

        Set<V> nbrs = this.adj.get(from);
        if (nbrs.add(to)) {
            this.edgeCount++;
        }
    }

    /** Removes directed edge (from -> to) if present. */
    public void removeEdge(V from, V to) {
        requireNonNull(from, "from");
        requireNonNull(to, "to");
        this.requireVertexExists(from);
        this.requireVertexExists(to);

        Set<V> nbrs = this.adj.get(from);
        if (nbrs.remove(to)) {
            this.edgeCount--;
        }
    }

    /** Reports whether v is a vertex. */
    public boolean containsVertex(V v) {
        requireNonNull(v, "vertex");
        return this.adj.containsKey(v);
    }

    /** Reports whether (from -> to) is an edge. */
    public boolean containsEdge(V from, V to) {
        requireNonNull(from, "from");
        requireNonNull(to, "to");
        if (!this.adj.containsKey(from)) {
            return false;
        }
        return this.adj.get(from).contains(to);
    }

    /*
     * ============================ Convenience (secondary-like)
     * ============================
     */

    /** Returns number of vertices. */
    public int order() {
        return this.adj.size();
    }

    /** Returns number of edges. */
    public int size() {
        return this.edgeCount;
    }

    /** Returns outgoing degree of v. */
    public int outDegree(V v) {
        requireNonNull(v, "vertex");
        this.requireVertexExists(v);
        return this.adj.get(v).size();
    }

    /** Returns incoming degree of v (computed). */
    public int inDegree(V v) {
        requireNonNull(v, "vertex");
        this.requireVertexExists(v);

        int count = 0;
        for (Set<V> nbrs : this.adj.values()) {
            if (nbrs.contains(v)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the set of vertices reachable from start (including start).
     *
     * <p>
     * BFS traversal; showcases the value for "blast radius" analysis.
     * </p>
     */
    public Set<V> reachableFrom(V start) {
        requireNonNull(start, "start");
        this.requireVertexExists(start);

        Set<V> visited = new HashSet<>();
        Deque<V> q = new ArrayDeque<>();

        visited.add(start);
        q.addLast(start);

        while (!q.isEmpty()) {
            V cur = q.removeFirst();
            for (V nxt : this.adj.get(cur)) {
                if (visited.add(nxt)) {
                    q.addLast(nxt);
                }
            }
        }

        return visited;
    }

    /** Reports whether target is reachable from start. */
    public boolean isReachable(V start, V target) {
        requireNonNull(start, "start");
        requireNonNull(target, "target");
        this.requireVertexExists(start);
        this.requireVertexExists(target);
        return this.reachableFrom(start).contains(target);
    }

    /**
     * Returns a human-readable adjacency list view. Useful for debugging and
     * demo output.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph(order=").append(this.order()).append(", size=")
                .append(this.size()).append(")\n");
        for (Map.Entry<V, Set<V>> e : this.adj.entrySet()) {
            sb.append("  ").append(e.getKey()).append(" -> ")
                    .append(e.getValue()).append("\n");
        }
        return sb.toString();
    }

    /*
     * ========= Demo main =========
     */

    public static void main(String[] args) {
        GraphProofOfConcept<String> g = new GraphProofOfConcept<>();

        // --- Build a tiny "enterprise network" ---
        // Vertices (hosts/services)
        g.addVertex("Laptop");
        g.addVertex("WiFiRouter");
        g.addVertex("AD");
        g.addVertex("FileServer");
        g.addVertex("SIEM");
        g.addVertex("DB");
        g.addVertex("ProdWeb");

        // Edges (allowed connections / reachability)
        g.addEdge("Laptop", "WiFiRouter");
        g.addEdge("WiFiRouter", "AD");
        g.addEdge("AD", "FileServer");
        g.addEdge("AD", "SIEM");
        g.addEdge("FileServer", "DB");
        g.addEdge("ProdWeb", "DB");

        System.out.println("=== Initial network graph ===");
        System.out.println(g);

        // --- Show off variety of methods ---
        System.out.println(
                "containsVertex(\"SIEM\"): " + g.containsVertex("SIEM"));
        System.out.println("containsEdge(\"AD\", \"SIEM\"): "
                + g.containsEdge("AD", "SIEM"));
        System.out.println("order(): " + g.order());
        System.out.println("size(): " + g.size());
        System.out.println("outDegree(\"AD\"): " + g.outDegree("AD"));
        System.out.println("inDegree(\"DB\"): " + g.inDegree("DB"));

        // --- Security value demo: blast radius ---
        System.out.println("\n=== Blast radius if Laptop is compromised ===");
        System.out.println(
                "reachableFrom(\"Laptop\"): " + g.reachableFrom("Laptop"));
        System.out.println(
                "isReachable(Laptop, DB): " + g.isReachable("Laptop", "DB"));
        System.out.println("isReachable(Laptop, ProdWeb): "
                + g.isReachable("Laptop", "ProdWeb"));

        // --- Simulate segmentation fix (remove risky path) ---
        System.out.println(
                "\n=== Apply segmentation fix: remove AD -> FileServer ===");
        g.removeEdge("AD", "FileServer");
        System.out.println(g);
        System.out.println(
                "reachableFrom(\"Laptop\"): " + g.reachableFrom("Laptop"));

        // --- Simulate decommissioning a host (remove vertex) ---
        System.out.println("\n=== Decommission FileServer ===");
        g.removeVertex("FileServer");
        System.out.println(g);
        System.out.println("size(): " + g.size());
    }

    /*
     * ==================== Small helper methods ====================
     */

    private static void requireNonNull(Object x, String name) {
        if (x == null) {
            throw new IllegalArgumentException(name + " must not be null");
        }
    }

    private void requireVertexExists(V v) {
        if (!this.adj.containsKey(v)) {
            throw new IllegalStateException("Vertex does not exist: " + v);
        }
    }
}
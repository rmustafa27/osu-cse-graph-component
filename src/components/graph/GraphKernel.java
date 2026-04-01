package components.graph;

// import components.set.Set;
import java.util.Set;

import components.standard.Standard;

/**
 * Graph kernel component with primary methods.
 *
 * @param <V>
 *            type of graph vertices
 */
public interface GraphKernel<V> extends Standard<Graph<V>> {

    /**
     * Adds {@code v} to this graph as a vertex if it is not already present.
     *
     * @param v
     *            the vertex to add
     * @updates this
     * @requires v is not null
     * @ensures this.containsVertex(v)
     */
    void addVertex(V v);

    /**
     * Removes {@code v} from this graph if it is present. Any incident edges
     * are also removed.
     *
     * @param v
     *            the vertex to remove
     * @updates this
     * @requires v is not null
     * @ensures not this.containsVertex(v)
     */
    void removeVertex(V v);

    /**
     * Adds the directed edge ({@code from}, {@code to}) to this graph.
     *
     * @param from
     *            the source vertex
     * @param to
     *            the destination vertex
     * @updates this
     * @requires from is not null and to is not null and
     *           this.containsVertex(from) and this.containsVertex(to)
     * @ensures this.containsEdge(from, to)
     */
    void addEdge(V from, V to);

    /**
     * Removes the directed edge ({@code from}, {@code to}) from this graph if
     * it is present.
     *
     * @param from
     *            the source vertex
     * @param to
     *            the destination vertex
     * @updates this
     * @requires from is not null and to is not null
     * @ensures not this.containsEdge(from, to)
     */
    void removeEdge(V from, V to);

    /**
     * Reports whether {@code v} is a vertex in this graph.
     *
     * @param v
     *            the vertex to test
     * @return true iff {@code v} is a vertex in this graph
     * @requires v is not null
     * @ensures containsVertex = (v is a vertex in this)
     */
    boolean containsVertex(V v);

    /**
     * Reports whether the directed edge ({@code from}, {@code to}) is in this
     * graph.
     *
     * @param from
     *            the source vertex
     * @param to
     *            the destination vertex
     * @return true iff ({@code from}, {@code to}) is an edge in this graph
     * @requires from is not null and to is not null
     * @ensures containsEdge = (edge (from, to) is in this)
     */
    boolean containsEdge(V from, V to);

    /**
     * Reports the set of vertices in this graph.
     *
     * @return the set of vertices in this graph
     * @ensures vertices = set of all vertices in this
     */
    Set<V> vertices();

    /**
     * Reports the set of vertices adjacent from {@code v}.
     *
     * @param v
     *            the source vertex
     * @return the set of vertices {@code w} such that ({@code v}, {@code w}) is
     *         an edge in this graph
     * @requires v is not null and this.containsVertex(v)
     * @ensures neighbors = set of all vertices w such that this.containsEdge(v,
     *          w)
     */
    Set<V> neighbors(V v);

    /**
     * Reports the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     * @ensures order = number of vertices in this
     */
    int order();

    /**
     * Reports the number of edges in this graph.
     *
     * @return the number of edges in this graph
     * @ensures size = number of edges in this
     */
    int size();
}
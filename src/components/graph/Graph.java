package components.graph;

import components.set.Set;

/**
 * Graph enhanced component with secondary methods.
 *
 * @param <V>
 *            type of graph vertices
 */
public interface Graph<V> extends GraphKernel<V> {

    /**
     * Reports whether this graph has no vertices.
     *
     * @return true iff this graph has no vertices
     * @ensures isEmpty = (order() = 0)
     */
    boolean isEmpty();

    /**
     * Reports the number of outgoing edges from {@code v}.
     *
     * @param v
     *            the vertex whose outgoing degree is reported
     * @return the number of outgoing edges from {@code v}
     * @requires v is not null and this.containsVertex(v)
     * @ensures outDegree = number of edges (v, x) in this
     */
    int outDegree(V v);

    /**
     * Reports the number of incoming edges to {@code v}.
     *
     * @param v
     *            the vertex whose incoming degree is reported
     * @return the number of incoming edges to {@code v}
     * @requires v is not null and this.containsVertex(v)
     * @ensures inDegree = number of edges (x, v) in this
     */
    int inDegree(V v);

    /**
     * Reports the set of vertices reachable from {@code start}, including
     * {@code start}.
     *
     * @param start
     *            the starting vertex
     * @return the set of vertices reachable from {@code start}
     * @requires start is not null and this.containsVertex(start)
     * @ensures reachableFrom = set of all vertices reachable from start in this
     */
    Set<V> reachableFrom(V start);

    /**
     * Reports whether {@code target} is reachable from {@code start}.
     *
     * @param start
     *            the starting vertex
     * @param target
     *            the target vertex
     * @return true iff {@code target} is reachable from {@code start}
     * @requires start is not null and target is not null and
     *           this.containsVertex(start) and this.containsVertex(target)
     * @ensures isReachable = (target is reachable from start in this)
     */
    boolean isReachable(V start, V target);
}
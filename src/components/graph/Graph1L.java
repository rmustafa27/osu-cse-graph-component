package components.graph;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;

/**
 * {@code Graph} represented as an adjacency-list map from vertices to their
 * outgoing neighbors.
 *
 * @param <V>
 *            type of graph vertices
 *
 * @convention <pre>
 * adj is not null and
 * edgeCount >= 0 and
 * for every vertex v in adj:
 *   adj.value(v) is not null and
 *   every u in adj.value(v) is also a key in adj
 * </pre>
 *
 * @correspondence <pre>
 * this = (
 *   vertices = entries in adj,
 *   edges = { (v, u) | v is a key in adj and u is in adj.value(v) }
 * )
 * </pre>
 */
public final class Graph1L<V> extends GraphSecondary<V> {

    /**
     * Adjacency-list representation.
     */
    private Map<V, Set<V>> adj;

    /**
     * Number of edges in this graph.
     */
    private int edgeCount;

    /**
     * Creates an empty representation.
     */
    private void createNewRep() {
        this.adj = new Map1L<>();
        this.edgeCount = 0;
    }

    /**
     * No-argument constructor.
     */
    public Graph1L() {
        this.createNewRep();
    }

    @Override
    public Graph<V> newInstance() {
        return new Graph1L<>();
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void transferFrom(Graph<V> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Graph1L<?>
                : "Violation of: source is of dynamic type Graph1L<?>";

        Graph1L<V> localSource = (Graph1L<V>) source;
        this.adj = localSource.adj;
        this.edgeCount = localSource.edgeCount;
        localSource.createNewRep();
    }

    @Override
    public void addVertex(V v) {
        assert v != null : "Violation of: v is not null";

        if (!this.containsVertex(v)) {
            this.adj.add(v, new Set1L<V>());
        }
    }

    @Override
    public void removeVertex(V v) {
        assert v != null : "Violation of: v is not null";

        if (this.containsVertex(v)) {
            Set<V> outgoing = this.adj.value(v);
            this.edgeCount -= outgoing.size();

            for (Map.Pair<V, Set<V>> p : this.adj) {
                V vertex = p.key();
                Set<V> nbrs = p.value();
                if (!vertex.equals(v) && nbrs.contains(v)) {
                    nbrs.remove(v);
                    this.edgeCount--;
                }
            }

            this.adj.remove(v);
        }
    }

    @Override
    public void addEdge(V from, V to) {
        assert from != null : "Violation of: from is not null";
        assert to != null : "Violation of: to is not null";
        assert this.containsVertex(from)
                : "Violation of: this.containsVertex(from)";
        assert this.containsVertex(to)
                : "Violation of: this.containsVertex(to)";

        Set<V> nbrs = this.adj.value(from);
        if (!nbrs.contains(to)) {
            nbrs.add(to);
            this.edgeCount++;
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        assert from != null : "Violation of: from is not null";
        assert to != null : "Violation of: to is not null";

        if (this.containsVertex(from) && this.containsVertex(to)) {
            Set<V> nbrs = this.adj.value(from);
            if (nbrs.contains(to)) {
                nbrs.remove(to);
                this.edgeCount--;
            }
        }
    }

    @Override
    public boolean containsVertex(V v) {
        assert v != null : "Violation of: v is not null";

        return this.adj.hasKey(v);
    }

    @Override
    public boolean containsEdge(V from, V to) {
        assert from != null : "Violation of: from is not null";
        assert to != null : "Violation of: to is not null";

        return this.containsVertex(from) && this.adj.value(from).contains(to);
    }

    @Override
    public Set<V> vertices() {
        Set<V> result = new Set1L<>();
        for (Map.Pair<V, Set<V>> p : this.adj) {
            result.add(p.key());
        }
        return result;
    }

    @Override
    public Set<V> neighbors(V v) {
        assert v != null : "Violation of: v is not null";
        assert this.containsVertex(v) : "Violation of: this.containsVertex(v)";

        Set<V> result = new Set1L<>();
        for (V neighbor : this.adj.value(v)) {
            result.add(neighbor);
        }
        return result;
    }

    @Override
    public int order() {
        return this.adj.size();
    }

    @Override
    public int size() {
        return this.edgeCount;
    }
}
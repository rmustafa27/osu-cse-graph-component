package components.graph;

import java.util.Queue;
import java.util.Set;

// import components.queue.Queue;
// import components.queue.Queue1L;
// import components.set.Set;
// import components.set.Set1L;

import components.queue.Queue1L;
import components.set.Set1L;

/**
 * Secondary methods for {@code Graph}.
 *
 * @param <V>
 *            type of graph vertices
 */
public abstract class GraphSecondary<V> implements Graph<V> {

    @Override
    public boolean isEmpty() {
        return this.order() == 0;
    }

    @Override
    public int outDegree(V v) {
        assert v != null : "Violation of: v is not null";
        assert this.containsVertex(v) : "Violation of: this.containsVertex(v)";

        return this.neighbors(v).size();
    }

    @Override
    public int inDegree(V v) {
        assert v != null : "Violation of: v is not null";
        assert this.containsVertex(v) : "Violation of: this.containsVertex(v)";

        int count = 0;
        for (V vertex : this.vertices()) {
            if (this.containsEdge(vertex, v)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Set<V> reachableFrom(V start) {
        assert start != null : "Violation of: start is not null";
        assert this.containsVertex(
                start) : "Violation of: this.containsVertex(start)";

        Set<V> visited = new Set1L<>();
        Queue<V> frontier = new Queue1L<>();

        visited.add(start);
        frontier.enqueue(start);

        while (frontier.length() > 0) {
            V current = frontier.dequeue();
            for (V neighbor : this.neighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    frontier.enqueue(neighbor);
                }
            }
        }

        return visited;
    }

    @Override
    public boolean isReachable(V start, V target) {
        assert start != null : "Violation of: start is not null";
        assert target != null : "Violation of: target is not null";
        assert this.containsVertex(
                start) : "Violation of: this.containsVertex(start)";
        assert this.containsVertex(
                target) : "Violation of: this.containsVertex(target)";

        return this.reachableFrom(start).contains(target);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean firstVertex = true;
        for (V vertex : this.vertices()) {
            if (!firstVertex) {
                sb.append(", ");
            }
            firstVertex = false;

            sb.append(vertex).append(" -> ").append(this.neighbors(vertex));
        }

        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Graph<?>)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Graph<?> other = (Graph<?>) obj;

        if (this.order() != other.order() || this.size() != other.size()) {
            return false;
        }

        if (!this.vertices().equals(other.vertices())) {
            return false;
        }

        for (V vertex : this.vertices()) {
            if (!this.neighbors(vertex).equals(other.neighbors(vertex))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.vertices().hashCode() + this.size();
    }
}
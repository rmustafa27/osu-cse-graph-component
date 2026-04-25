package components.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

/**
 * Tests for {@code Graph1L} kernel and Standard methods.
 */
public class Graph1LTest {

    private Graph<String> createEmptyGraph() {
        return new Graph1L<>();
    }

    private Graph<String> createSmallGraph() {
        Graph<String> g = new Graph1L<>();
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addEdge("A", "B");
        g.addEdge("A", "C");
        return g;
    }

    @Test
    public void testConstructorEmpty() {
        Graph<String> g = this.createEmptyGraph();
        Graph<String> expected = this.createEmptyGraph();

        assertEquals(expected, g);
        assertEquals(0, g.order());
        assertEquals(0, g.size());
    }

    @Test
    public void testNewInstanceEmpty() {
        Graph<String> g = this.createSmallGraph();
        Graph<String> gExpected = this.createSmallGraph();

        Graph<String> result = g.newInstance();

        assertEquals(this.createEmptyGraph(), result);
        assertEquals(gExpected, g);
    }

    @Test
    public void testClearNonEmpty() {
        Graph<String> g = this.createSmallGraph();

        g.clear();

        assertEquals(this.createEmptyGraph(), g);
    }

    @Test
    public void testTransferFromNonEmptyToEmpty() {
        Graph<String> source = this.createSmallGraph();
        Graph<String> target = this.createEmptyGraph();

        Graph<String> expectedTarget = this.createSmallGraph();
        Graph<String> expectedSource = this.createEmptyGraph();

        target.transferFrom(source);

        assertEquals(expectedTarget, target);
        assertEquals(expectedSource, source);
    }

    @Test
    public void testAddVertexEmpty() {
        Graph<String> g = this.createEmptyGraph();

        Graph<String> expected = this.createEmptyGraph();
        expected.addVertex("A");

        g.addVertex("A");

        assertEquals(expected, g);
    }

    @Test
    public void testAddDuplicateVertex() {
        Graph<String> g = this.createEmptyGraph();
        g.addVertex("A");

        Graph<String> expected = this.createEmptyGraph();
        expected.addVertex("A");

        g.addVertex("A");

        assertEquals(expected, g);
    }

    @Test
    public void testRemoveVertexWithIncidentEdges() {
        Graph<String> g = this.createSmallGraph();
        g.addEdge("B", "A");

        Graph<String> expected = this.createEmptyGraph();
        expected.addVertex("B");
        expected.addVertex("C");

        g.removeVertex("A");

        assertEquals(expected, g);
    }

    @Test
    public void testAddEdge() {
        Graph<String> g = this.createEmptyGraph();
        g.addVertex("A");
        g.addVertex("B");

        Graph<String> expected = this.createEmptyGraph();
        expected.addVertex("A");
        expected.addVertex("B");
        expected.addEdge("A", "B");

        g.addEdge("A", "B");

        assertEquals(expected, g);
    }

    @Test
    public void testAddDuplicateEdge() {
        Graph<String> g = this.createEmptyGraph();
        g.addVertex("A");
        g.addVertex("B");
        g.addEdge("A", "B");

        Graph<String> expected = this.createEmptyGraph();
        expected.addVertex("A");
        expected.addVertex("B");
        expected.addEdge("A", "B");

        g.addEdge("A", "B");

        assertEquals(expected, g);
    }

    @Test
    public void testRemoveEdge() {
        Graph<String> g = this.createSmallGraph();

        Graph<String> expected = this.createEmptyGraph();
        expected.addVertex("A");
        expected.addVertex("B");
        expected.addVertex("C");
        expected.addEdge("A", "C");

        g.removeEdge("A", "B");

        assertEquals(expected, g);
    }

    @Test
    public void testContainsVertexTrueNonMutating() {
        Graph<String> g = this.createSmallGraph();
        Graph<String> expected = this.createSmallGraph();

        assertTrue(g.containsVertex("A"));
        assertEquals(expected, g);
    }

    @Test
    public void testContainsVertexFalseNonMutating() {
        Graph<String> g = this.createSmallGraph();
        Graph<String> expected = this.createSmallGraph();

        assertFalse(g.containsVertex("D"));
        assertEquals(expected, g);
    }

    @Test
    public void testContainsEdgeTrueNonMutating() {
        Graph<String> g = this.createSmallGraph();
        Graph<String> expected = this.createSmallGraph();

        assertTrue(g.containsEdge("A", "B"));
        assertEquals(expected, g);
    }

    @Test
    public void testContainsEdgeFalseNonMutating() {
        Graph<String> g = this.createSmallGraph();
        Graph<String> expected = this.createSmallGraph();

        assertFalse(g.containsEdge("B", "A"));
        assertEquals(expected, g);
    }

    @Test
    public void testVerticesNonMutating() {
        Graph<String> g = this.createSmallGraph();
        Graph<String> gExpected = this.createSmallGraph();

        Set<String> expected = new Set1L<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");

        assertEquals(expected, g.vertices());
        assertEquals(gExpected, g);
    }

    @Test
    public void testNeighborsNonMutating() {
        Graph<String> g = this.createSmallGraph();
        Graph<String> gExpected = this.createSmallGraph();

        Set<String> expected = new Set1L<>();
        expected.add("B");
        expected.add("C");

        assertEquals(expected, g.neighbors("A"));
        assertEquals(gExpected, g);
    }

    @Test
    public void testNeighborsFreshCopy() {
        Graph<String> g = this.createSmallGraph();
        Graph<String> gExpected = this.createSmallGraph();

        Set<String> neighbors = g.neighbors("A");
        neighbors.remove("B");

        assertEquals(gExpected, g);
        assertTrue(g.containsEdge("A", "B"));
    }

    @Test
    public void testOrderNonMutating() {
        Graph<String> g = this.createSmallGraph();
        Graph<String> expected = this.createSmallGraph();

        assertEquals(3, g.order());
        assertEquals(expected, g);
    }

    @Test
    public void testSizeNonMutating() {
        Graph<String> g = this.createSmallGraph();
        Graph<String> expected = this.createSmallGraph();

        assertEquals(2, g.size());
        assertEquals(expected, g);
    }
}
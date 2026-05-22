package components.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

/**
 * Tests for {@code GraphSecondary} methods.
 */
public class GraphSecondaryTest {

    private Graph<String> createEmptyGraph() {
        return new Graph1L<>();
    }

    private Graph<String> createNetworkGraph() {
        Graph<String> g = new Graph1L<>();

        g.addVertex("Laptop");
        g.addVertex("Router");
        g.addVertex("AD");
        g.addVertex("FileServer");
        g.addVertex("DB");
        g.addVertex("SIEM");

        g.addEdge("Laptop", "Router");
        g.addEdge("Router", "AD");
        g.addEdge("AD", "FileServer");
        g.addEdge("FileServer", "DB");
        g.addEdge("AD", "SIEM");

        return g;
    }

    @Test
    public void testIsEmptyTrueNonMutating() {
        Graph<String> g = this.createEmptyGraph();
        Graph<String> expected = this.createEmptyGraph();

        assertTrue(g.isEmpty());
        assertEquals(expected, g);
    }

    @Test
    public void testIsEmptyFalseNonMutating() {
        Graph<String> g = this.createNetworkGraph();
        Graph<String> expected = this.createNetworkGraph();

        assertFalse(g.isEmpty());
        assertEquals(expected, g);
    }

    @Test
    public void testOutDegreeNonMutating() {
        Graph<String> g = this.createNetworkGraph();
        Graph<String> expected = this.createNetworkGraph();

        assertEquals(2, g.outDegree("AD"));
        assertEquals(expected, g);
    }

    @Test
    public void testOutDegreeZeroNonMutating() {
        Graph<String> g = this.createNetworkGraph();
        Graph<String> expected = this.createNetworkGraph();

        assertEquals(0, g.outDegree("DB"));
        assertEquals(expected, g);
    }

    @Test
    public void testInDegreeNonMutating() {
        Graph<String> g = this.createNetworkGraph();
        Graph<String> expected = this.createNetworkGraph();

        assertEquals(1, g.inDegree("DB"));
        assertEquals(expected, g);
    }

    @Test
    public void testInDegreeZeroNonMutating() {
        Graph<String> g = this.createNetworkGraph();
        Graph<String> expected = this.createNetworkGraph();

        assertEquals(0, g.inDegree("Laptop"));
        assertEquals(expected, g);
    }

    @Test
    public void testReachableFromStartNonMutating() {
        Graph<String> g = this.createNetworkGraph();
        Graph<String> gExpected = this.createNetworkGraph();

        Set<String> expected = new Set1L<>();
        expected.add("Laptop");
        expected.add("Router");
        expected.add("AD");
        expected.add("FileServer");
        expected.add("DB");
        expected.add("SIEM");

        assertEquals(expected, g.reachableFrom("Laptop"));
        assertEquals(gExpected, g);
    }

    @Test
    public void testReachableFromMiddleNonMutating() {
        Graph<String> g = this.createNetworkGraph();
        Graph<String> gExpected = this.createNetworkGraph();

        Set<String> expected = new Set1L<>();
        expected.add("AD");
        expected.add("FileServer");
        expected.add("DB");
        expected.add("SIEM");

        assertEquals(expected, g.reachableFrom("AD"));
        assertEquals(gExpected, g);
    }

    @Test
    public void testIsReachableTrueNonMutating() {
        Graph<String> g = this.createNetworkGraph();
        Graph<String> expected = this.createNetworkGraph();

        assertTrue(g.isReachable("Laptop", "DB"));
        assertEquals(expected, g);
    }

    @Test
    public void testIsReachableFalseNonMutating() {
        Graph<String> g = this.createNetworkGraph();
        Graph<String> expected = this.createNetworkGraph();

        assertFalse(g.isReachable("DB", "Laptop"));
        assertEquals(expected, g);
    }

    @Test
    public void testEqualsTrueSameStructureNonMutating() {
        Graph<String> g1 = this.createNetworkGraph();
        Graph<String> g2 = this.createNetworkGraph();

        Graph<String> g1Expected = this.createNetworkGraph();
        Graph<String> g2Expected = this.createNetworkGraph();

        assertTrue(g1.equals(g2));
        assertEquals(g1Expected, g1);
        assertEquals(g2Expected, g2);
    }

    @Test
    public void testEqualsFalseDifferentEdgesNonMutating() {
        Graph<String> g1 = this.createNetworkGraph();
        Graph<String> g2 = this.createNetworkGraph();
        g2.removeEdge("AD", "SIEM");

        Graph<String> g1Expected = this.createNetworkGraph();
        Graph<String> g2Expected = this.createNetworkGraph();
        g2Expected.removeEdge("AD", "SIEM");

        assertFalse(g1.equals(g2));
        assertEquals(g1Expected, g1);
        assertEquals(g2Expected, g2);
    }

    @Test
    public void testHashCodeSameForEqualGraphsNonMutating() {
        Graph<String> g1 = this.createNetworkGraph();
        Graph<String> g2 = this.createNetworkGraph();

        Graph<String> g1Expected = this.createNetworkGraph();
        Graph<String> g2Expected = this.createNetworkGraph();

        assertEquals(g1.hashCode(), g2.hashCode());
        assertEquals(g1Expected, g1);
        assertEquals(g2Expected, g2);
    }

    @Test
    public void testToStringNonMutating() {
        Graph<String> g = this.createNetworkGraph();
        Graph<String> expected = this.createNetworkGraph();

        String s = g.toString();

        assertTrue(s.contains("Laptop"));
        assertTrue(s.contains("AD"));
        assertTrue(s.contains("DB"));
        assertEquals(expected, g);
    }
}
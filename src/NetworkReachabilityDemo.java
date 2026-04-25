import components.graph.Graph;
import components.graph.Graph1L;

/**
 * Demonstrates using Graph directly to model network reachability.
 */
public final class NetworkReachabilityDemo {

    private NetworkReachabilityDemo() {
    }

    public static void main(String[] args) {
        Graph<String> network = new Graph1L<>();

        network.addVertex("Laptop");
        network.addVertex("Router");
        network.addVertex("AD");
        network.addVertex("FileServer");
        network.addVertex("DB");
        network.addVertex("SIEM");

        network.addEdge("Laptop", "Router");
        network.addEdge("Router", "AD");
        network.addEdge("AD", "FileServer");
        network.addEdge("FileServer", "DB");
        network.addEdge("AD", "SIEM");

        System.out.println("Network graph:");
        System.out.println(network);

        System.out.println("Blast radius from Laptop:");
        System.out.println(network.reachableFrom("Laptop"));

        System.out.println("Can Laptop reach DB?");
        System.out.println(network.isReachable("Laptop", "DB"));
    }
}
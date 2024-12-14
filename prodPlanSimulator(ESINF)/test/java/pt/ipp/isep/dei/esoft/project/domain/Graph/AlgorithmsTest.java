package pt.ipp.isep.dei.esoft.project.domain.Graph;


import pt.ipp.isep.dei.esoft.project.domain.Graph.map.MapGraph;
import pt.ipp.isep.dei.esoft.project.domain.Graph.matrix.MatrixGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.*;
import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class MapAlgorithmsTest {

    final Graph<String, Integer> completeMap = new MapGraph<>(false);
    Graph<String, Integer> incompleteMap = new MapGraph<>(false);

    public MapAlgorithmsTest() {
    }

    @BeforeEach
    public void setUp() {

        completeMap.addVertex("Porto");
        completeMap.addVertex("Braga");
        completeMap.addVertex("Vila Real");
        completeMap.addVertex("Aveiro");
        completeMap.addVertex("Coimbra");
        completeMap.addVertex("Leiria");

        completeMap.addVertex("Viseu");
        completeMap.addVertex("Guarda");
        completeMap.addVertex("Castelo Branco");
        completeMap.addVertex("Lisboa");
        completeMap.addVertex("Faro");

        completeMap.addEdge("Porto", "Aveiro", 75);
        completeMap.addEdge("Porto", "Braga", 60);
        completeMap.addEdge("Porto", "Vila Real", 100);
        completeMap.addEdge("Viseu", "Guarda", 75);
        completeMap.addEdge("Guarda", "Castelo Branco", 100);
        completeMap.addEdge("Aveiro", "Coimbra", 60);
        completeMap.addEdge("Coimbra", "Lisboa", 200);
        completeMap.addEdge("Coimbra", "Leiria", 80);
        completeMap.addEdge("Aveiro", "Leiria", 120);
        completeMap.addEdge("Leiria", "Lisboa", 150);

        incompleteMap = completeMap.clone();

        completeMap.addEdge("Aveiro", "Viseu", 85);
        completeMap.addEdge("Leiria", "Castelo Branco", 170);
        completeMap.addEdge("Lisboa", "Faro", 280);
    }

    private void checkContentEquals(List<String> l1, List<String> l2, String msg) {
        Collections.sort(l1);
        Collections.sort(l2);
        assertEquals(l1, l2, msg);
    }

    /**
     * Test of BreadthFirstSearch method, of class Algorithms.
     */
    @Test
    public void testBreadthFirstSearch() {
        System.out.println("Test BreadthFirstSearch");

        Assertions.assertNull(Algorithms.BreadthFirstSearch(completeMap, "LX"), "Should be null if vertex does not exist");

        LinkedList<String> path = Algorithms.BreadthFirstSearch(incompleteMap, "Faro");

        assertEquals(1, path.size(), "Should be just one");

        assertEquals("Faro", path.peekFirst());

        path = Algorithms.BreadthFirstSearch(incompleteMap, "Porto");
        assertEquals(7, path.size(), "Should give seven vertices");

        assertEquals("Porto", path.removeFirst(), "BreathFirst Porto");

        LinkedList<String> expected = new LinkedList<>(Arrays.asList("Aveiro", "Braga", "Vila Real"));
        checkContentEquals(expected, path.subList(0, 3), "BreathFirst Porto");

        expected = new LinkedList<>(Arrays.asList("Coimbra", "Leiria"));
        checkContentEquals(expected, path.subList(3, 5), "BreathFirst Porto");

        expected = new LinkedList<>(Arrays.asList("Lisboa"));
        checkContentEquals(expected, path.subList(5, 6), "BreathFirst Porto");

        path = Algorithms.BreadthFirstSearch(incompleteMap, "Viseu");
        expected = new LinkedList<>(Arrays.asList("Viseu", "Guarda", "Castelo Branco"));
        assertEquals(expected, path, "BreathFirst Viseu");
    }

    /**
     * Test of DepthFirstSearch method, of class Algorithms.
     */
    @Test
    public void testDepthFirstSearch() {
        System.out.println("Test of DepthFirstSearch");

        assertNull(Algorithms.DepthFirstSearch(completeMap, "LX"), "Should be null if vertex does not exist");

        LinkedList<String> path = Algorithms.DepthFirstSearch(incompleteMap, "Faro");
        assertEquals(1, path.size(), "Should be just one");

        assertEquals("Faro", path.peekFirst());

        path = Algorithms.BreadthFirstSearch(incompleteMap, "Porto");
        assertEquals(7, path.size(), "Should give seven vertices");

        assertEquals("Porto", path.removeFirst(), "DepthFirst Porto");
        assertTrue(new LinkedList<>(Arrays.asList("Aveiro", "Braga", "Vila Real")).contains(path.removeFirst()), "DepthFirst Porto");

        path = Algorithms.BreadthFirstSearch(incompleteMap, "Viseu");
        List<String> expected = new LinkedList<>(Arrays.asList("Viseu", "Guarda", "Castelo Branco"));
        assertEquals(expected, path, "DepthFirst Viseu");
    }

    /**
     * Test minimum distance graph using Floyd-Warshall.
     */
    @Test
    public void testminDistGraph() {

        System.out.println("Test minimum distance graph using Floyd-Warshall");

        Comparator<Integer> comparator = Integer::compare;
        BinaryOperator<Integer> sum = Integer::sum;

        MatrixGraph<String, Integer> minDistGraph = Algorithms.minDistGraph(completeMap, comparator, sum);

        assertEquals(completeMap.numVertices(), minDistGraph.numVertices(), "MinDistGraph should contain the same vertices");

        for (String vertex : completeMap.vertices()) {
            assertTrue(minDistGraph.validVertex(vertex), "MinDistGraph should contain vertex: " + vertex);
        }

        // Verifica as distâncias mínimas calculadas
        assertEquals(75, minDistGraph.edge("Porto", "Aveiro").getWeight(), "Minimum distance Porto-Aveiro should be 75");
        assertEquals(335, minDistGraph.edge("Porto", "Castelo Branco").getWeight(), "Minimum distance Porto-Castelo Branco should be 335");
        assertEquals(615, minDistGraph.edge("Porto", "Faro").getWeight(), "Minimum distance Porto-Faro should be 365");

        // Verifica distâncias para vértices que não estão conectados no grafo original
        assertNotNull(minDistGraph.edge("Porto", "Faro"), "There should be no path between Porto and Faro in incomplete graph");

    }
}
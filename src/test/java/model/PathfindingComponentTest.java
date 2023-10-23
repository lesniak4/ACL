package model;

import model.world.Hex;
import model.world.Node;
import model.components.PathfindingComponent;
import model.world.World;
import model.world.WorldGraph;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;


public class PathfindingComponentTest {
    @Test
    public void djisktraTest(){
        World world = new World(null, null,null);
        int WIDTH = 700;
        double tileSize = WIDTH / (float) (Math.sqrt(3f) * 10);
        world.setTileSize(tileSize);

        WorldGraph graph = new WorldGraph();
        Node node1 = new Node(new Hex(0,0));
        Node node2 = new Node(new Hex(1,0));
        Node node3 = new Node(new Hex(1,1));
        Node node4 = new Node(new Hex(0,1));
        Node node5 = new Node(new Hex(1,2));
        Node node6 = new Node(new Hex(2,2));
        Node node7 = new Node(new Hex(6,3));

        graph.addVertex(node1, node2, node3, node4, node5, node6);

        graph.addEdge(node1, node2, true);
        graph.addEdge(node1, node3, true);
        graph.addEdge(node3, node6, true);

        graph.addEdge(node2, node4, true);
        graph.addEdge(node4, node6, true);

        graph.addEdge(node4, node7, true);
        graph.addEdge(node7, node6, true);



        world.setGraph(graph);
        PathfindingComponent cp = new PathfindingComponent(null, world);
        cp.djikstra(node1);

        assertEquals(node6.getDistance(), 2);
        assertEquals(node7.getDistance(), 3);
    }

    @Test
    public void findShortestPathTest(){

        World world = new World(null, null,null);
        int WIDTH = 700;
        double tileSize = WIDTH / (float) (Math.sqrt(3f) * 10);
        world.setTileSize(tileSize);

        WorldGraph graph = new WorldGraph();
        Node node1 = new Node(new Hex(0,0));
        Node node2 = new Node(new Hex(0,1));
        Node node3 = new Node(new Hex(0,2));
        Node node4 = new Node(new Hex(1,0));
        Node node5 = new Node(new Hex(1,1));
        Node node6 = new Node(new Hex(1,2));
        Node node7 = new Node(new Hex(2,0));
        Node node8 = new Node(new Hex(2,1));
        Node node9 = new Node(new Hex(2,2));

        graph.addVertex(node1, node2, node3, node4, node5, node6, node7, node8, node9);

        graph.addEdge(node1, node2, true);
        graph.addEdge(node1, node4, true);
        graph.addEdge(node4, node7, true);
        graph.addEdge(node7, node8, true);

        world.setGraph(graph);
        GameObject source = new GameObject(20, 20, null);
        PathfindingComponent cp = new PathfindingComponent(source, world);
        source.addComponent(cp);
        Vector2 target = new Vector2(6 * tileSize + 20,3 * tileSize + 20); // position sur la base écran (sera convertie en base hexagone...)
        cp.setTarget(target);

        cp.pathFinding();
        assertTrue(cp.getPath().isEmpty());

        graph.addEdge(node8, node9, true);

        cp.pathFinding();
        assertFalse(cp.getPath().isEmpty());
        assertEquals(4, cp.getPath().size());

    }
}

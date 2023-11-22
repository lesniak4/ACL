package model.components.ai;

import model.GameObject;
import model.components.Component;
import utils.GameConfig;
import utils.Vector2;
import model.world.Hex;
import model.world.Node;
import model.world.World;
import model.world.WorldGraph;

import java.util.*;

public class PathfindingComponent extends Component {

    private WorldGraph worldGraph;
    private boolean isMoving;
    private Vector2 target;


    public PathfindingComponent(GameObject obj, WorldGraph worldGraph) {
        super(obj);
        this.worldGraph = worldGraph;
        this.isMoving = false;
    }

    public void setTarget(Vector2 target) {
        this.target = target;
    }

    public Vector2 getTarget() {
        return target;
    }


    public void setMoving(boolean moving){
        this.isMoving = moving;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public Hex  pathFinding(){

        if(this.worldGraph == null || this.target == null || this.gameObject == null)
            return null;

        Node dst = getNodeOf(this.target);
        Node src = getNodeOf(this.gameObject);

        if(dst == null || src == null)
            return null;

        djikstra(src);
        return findShortestPath(src, dst);
    }

    public Hex findShortestPath(Node src, Node dst){

        if(dst.getDistance() == Integer.MAX_VALUE)
            return null;

        Stack<Node> stack = new Stack<>();
        Node ptr = dst;

        while(!ptr.equals(src)){
            stack.add(ptr);
            int min = ptr.getDistance();
            Node next = null;

            for (Node node : (Set<Node>) worldGraph.getEdges(ptr)){
                if(node.getDistance() == min - 1){
                    next = node;
                    break;
                }
            }
            ptr = next;
        }
        if(!stack.isEmpty()){
            return stack.pop().getHex();
        }
        return null;
    }

    public void djikstra(Node src){

        ArrayList<Node> visited = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();

        for (Node node : (Set<Node>) worldGraph.getVertex()) {
            node.setDistance(Integer.MAX_VALUE);
        }

        src.setDistance(0);
        queue.add(src);

        while (queue.size() != 0) {
            Node current = queue.remove();

            if (visited.contains(current))
                continue;

            visited.add(current);

            for (Node node : (Set<Node>) worldGraph.getEdges(current)) {

                if(visited.contains(node))
                    continue;

                if (current.getDistance() + 1 < node.getDistance()) {
                    node.setDistance(current.getDistance() + 1);
                }
                queue.add(node);
            }
        }
    }

    public Node getNodeOf(GameObject gameObject){
        if(gameObject == null)
            return null;

        Hex srcHex = getHexOfPos(gameObject.getPosition());

        if(srcHex == null)
            return null;

        Node src = (Node)worldGraph.getSingularVertex(new Node(srcHex));
        return src;
    }

    public Node getNodeOf(Vector2 pos){
        if(pos == null)
            return null;

        Hex srcHex = getHexOfPos(pos);

        if(srcHex == null)
            return null;
        Node src = (Node)worldGraph.getSingularVertex(new Node(srcHex));
        return src;
    }

    public Hex getHexOfPos(Vector2 pos){

        for (Node n : (Set<Node>)worldGraph.getVertex()){
            if(Hex.isInHex(n.getHex(), pos, GameConfig.getInstance().getTileSize())){
                return n.getHex();
            }

        }
        return null;
    }

    @Override
    public void update() {

    }
}

package lab11.graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private PriorityQueue<Node> pq;
    private class Node implements Comparable {
        private int v;
        private int priority;

        public Node(int v, int dist) {
            this.v = v;
            this.priority = dist + h(v);
        }

        @Override
        public int compareTo(Object o) {
            Node n = (Node) o;
            return this.priority - n.priority;
        }
    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int curX = maze.toX(v);
        int curY = maze.toY(v);
        int tarX = maze.toX(t);
        int tarY = maze.toY(t);
        return Math.abs(curX - tarX) + Math.abs(curY - tarY);

    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        pq = new PriorityQueue<>();
        Node start = new Node(s, distTo[s]);
        pq.add(start);
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            marked[cur.v] = true;
            announce();
            if (cur.v == t) {
                return;
            }
            for (int adj : maze.adj(cur.v)) {
                distTo[adj] = distTo[cur.v] + 1;
                edgeTo[adj] = cur.v;
                if (!marked[adj]) {
                    pq.add(new Node(adj, distTo[adj]));
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}


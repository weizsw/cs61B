package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;
    private Queue<Integer> q;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;

    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        while (!q.isEmpty()) {
            int node = q.poll();
            marked[node] = true;
            for (int w: maze.adj(node)) {
                if (!marked[w]) {
                    edgeTo[w] = node;
                    announce();
                    distTo[w] = distTo[node] + 1;
                    q.add(w);
                    if (node == t) {
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
        q = new LinkedList<>();
        q.add(s);
        bfs();
    }
}


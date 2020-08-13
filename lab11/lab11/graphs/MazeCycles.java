package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Deque<Integer> stack;

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        stack = new ArrayDeque<>();
        stack.push(0);
        distTo[0] = 0;
        edgeTo[0] = 0;
        dfs();
    }

    private void dfs() {
        while (!stack.isEmpty()) {
            int v = stack.pop();
            marked[v] = true;
            announce();
            for (int w : maze.adj(v)) {
                if (marked[w] == true && edgeTo[v] != w) {
                    return;
                } else if (marked[w]) {
                    continue;
                } else {
                    edgeTo[w] = v;
                    announce();
                    distTo[w] = distTo[v] + 1;
                    stack.push(w);
                }
            }
        }

    }
}


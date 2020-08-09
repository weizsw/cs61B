package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class Solver {
    private List<WorldState> solution = new ArrayList();
    private MinPQ<Node> pq = new MinPQ();
    private Node finish;

    private class Node implements Comparable {
        private int M;
        private int E;
        private Node prev;
        private WorldState state;

        public Node(int m, WorldState state, Node prev) {
            M = m;
            E = state.estimatedDistanceToGoal();
            this.state = state;
            this.prev = prev;
        }

        @Override
        public int compareTo(Object o) {
            if (o.getClass() == this.getClass()) {
                Node n = (Node) o;
                return (this.M + this.E) - (n.M + n.E);
            }
            return -1;
        }
    }
    public Solver(WorldState initial) {
        Node first = new Node(0, initial, null);
        pq.insert(first);
        while (!pq.isEmpty()) {
            Node curr = pq.delMin();
            if (curr.E == 0) {
                finish = curr;
//                System.out.println(finish.state);
                return;
            }
            for (WorldState neighbor : curr.state.neighbors()) {
                if (curr.prev == null || !neighbor.equals(curr.prev.state)) {
                    Node n = new Node(curr.M + 1, neighbor, curr);
                    pq.insert(n);
                }
            }
        }
    }

    public int moves() {
        return finish.M;
    }

    public Iterable<WorldState> solution() {
        Node tmp = finish;
        while (tmp != null) {
            solution.add(tmp.state);
            tmp = tmp.prev;
        }
        Collections.reverse(solution);
        return solution;
    }
}

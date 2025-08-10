package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs(int v) {
        // Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> fringe = new Queue<>();
        marked[v] = true;
        fringe.enqueue(v);

        while (!fringe.isEmpty()) {
            Integer u = fringe.dequeue();
            announce();
            if (u == t) {
                targetFound = true;
            }
            if (targetFound) {
                return;
            }
            for (Integer w : maze.adj(u)) {
                if (!marked[w]) {
                    edgeTo[w] = u;
                    distTo[w] = distTo[u] + 1;
                    announce();
                    marked[w] = true;
                    fringe.enqueue(w);
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs(s);
    }
}


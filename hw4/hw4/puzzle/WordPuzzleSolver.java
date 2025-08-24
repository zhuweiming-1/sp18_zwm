package hw4.puzzle;

import edu.princeton.cs.algs4.StdOut;

public class WordPuzzleSolver {
    /***********************************************************************
     * Test routine for your Solver class. Uncomment and run to test
     * your basic functionality.
     **********************************************************************/
    public static void main(String[] args) {
        String start = "horse";
        String goal = "nurse";

        System.out.println(System.currentTimeMillis());
        Word startState = new Word(start, goal);
        Solver solver = new Solver(startState);
        System.out.println(System.currentTimeMillis());

        StdOut.println("Minimum number of moves = " + solver.moves());
        for (WorldState ws : solver.solution()) {
            StdOut.println(ws);
        }
    }
}

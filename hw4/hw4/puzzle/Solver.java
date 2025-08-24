package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState worldState;
        private int count;
        private SearchNode pre;
        private int priority;

        public SearchNode(WorldState worldState, int count, SearchNode pre) {
            this.worldState = worldState;
            this.count = count;
            this.pre = pre;
            this.priority = count + worldState.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.priority - o.priority;
        }
    }

    private SearchNode initSearch;
    private MinPQ<SearchNode> pq = new MinPQ<>();

    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     * 构造函数用于解决该谜题，计算 moves() 和 solution() 所需的一切，以避免再次求解问题。
     * 使用 A* 算法解决谜题。假设解是存在的。
     *
     * @param initial
     */
    public Solver(WorldState initial) {
        initSearch = new SearchNode(initial, 0, null);
        pq.insert(initSearch);
        while (!pq.min().worldState.isGoal()) {
            SearchNode min = pq.delMin();
            for (WorldState neighbor : min.worldState.neighbors()) {
                if (min.pre == null || !neighbor.equals(min.pre.worldState)) {
                    pq.insert(new SearchNode(neighbor, min.count + 1, min));
                }
            }
        }
    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting at the initial WorldState.
     * 返回从初始 WorldState 开始解决该谜题所需的最少移动次数。
     *
     * @return
     */
    public int moves() {
        return pq.min().count;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState to the solution.
     * 返回一个从初始 WorldState 到解决方案的 WorldState 序列。
     *
     * @return
     */
    public Iterable<WorldState> solution() {
        List<WorldState> path = new ArrayList<>();
        Stack<WorldState> stack = new Stack();
        SearchNode searchNode = pq.min();
        while (searchNode != null) {
            stack.push(searchNode.worldState);
            searchNode = searchNode.pre;
        }
        while (!stack.isEmpty()) {
            path.add(stack.pop());
        }
        return path;
    }
}

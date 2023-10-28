public class Main {
    public static void main(String[] args) throws InterruptedException {
        int size = 20;
        int[][] row_array = {
                {5, 3, 1, 7},
                {4, 3, 6},
                {3, 3, 6},
                {2, 3, 4},
                {1, 3, 5},
                {1, 1, 5},
                {7, 5},
                {2, 3, 3, 6},
                {2, 4, 4},
                {1, 1, 2, 1, 2},
                {3, 1, 1},
                {12},
                {1, 6, 1},
                {3, 8, 1},
                {3, 5, 1},
                {4, 4, 2},
                {3, 4, 2},
                {2, 4},
                {3, 5, 1, 2},
                {2, 4, 1, 2}
        };
        Block[] rows = new Block[size];
        for (int i = 0; i < size; i++) {
            rows[i] = Block.from_array(row_array[i]);
        }
        int[][] col_array = {
                {5, 2, 1},
                {4, 3, 2},
                {3, 4},
                {2, 4, 1, 6},
                {1, 3, 6},
                {9, 3},
                {4, 1, 4},
                {7, 2, 1},
                {3, 2, 8},
                {1, 2, 8},
                {1, 1, 9},
                {1, 4, 4},
                {1, 5, 2},
                {1, 3, 3, 1},
                {9, 1, 1},
                {9, 1},
                {11, 3, 2},
                {6, 3, 2},
                {3, 1, 2},
                {3, 1, 2, 2}
        };
        Block[] cols = new Block[size];
        for (int i = 0; i < size; i++) {
            cols[i] = Block.from_array(col_array[i]);
        }
        Solver s = new Solver(rows, cols);
        Artist a = new Artist(s);
        for (int i = 0; i < 20; i++) {
            s.iterate(i);
            Thread.sleep(100);
            a.draw_game();
        }
    }
}
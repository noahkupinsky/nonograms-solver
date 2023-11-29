public class Main {
    public static void main(String[] args) throws InterruptedException {
        int size = 20;
        int[][] row_array = {
                {4, 5, 6},
                {4, 3, 4, 1},
                {1, 1, 2, 1},
                {1, 1, 3, 1},
                {4, 5},
                {2, 10},
                {1, 10},
                {1, 1, 1, 2, 2},
                {1, 6, 2},
                {4, 2, 1, 3, 2},
                {4, 1, 1, 4},
                {6, 3},
                {2, 2, 4},
                {2, 4, 3},
                {9, 1},
                {6, 3, 2},
                {1, 1, 7},
                {3, 5},
                {8, 1, 4},
                {11, 1, 3}
        };
        Block[] rows = new Block[size];
        for (int i = 0; i < size; i++) {
            rows[i] = Block.from_array(row_array[i]);
        }
        int[][] col_array = {
                {2, 5, 1},
                {2, 7, 2},
                {2, 2, 3, 2},
                {4, 1, 3, 2},
                {1, 1, 2},
                {6, 2},
                {3, 4, 2},
                {1, 3, 3, 3, 2},
                {7, 1, 3, 1, 1},
                {2, 3, 2, 4, 1},
                {2, 3, 1, 3, 3},
                {1, 6, 2, 1},
                {4, 1, 3},
                {4, 4, 3, 1},
                {7, 3, 4},
                {7, 4, 6},
                {2, 3, 2, 4},
                {2, 4},
                {1, 2, 3},
                {4, 2, 2}
        };
        Block[] cols = new Block[size];
        for (int i = 0; i < size; i++) {
            cols[i] = Block.from_array(col_array[i]);
        }
        Solver s = new Solver(rows, cols);
        Artist a = new Artist(s);
        for (int i = 0; i < 20; i++) {
            s.iterate(i);
            Thread.sleep(200);
            a.draw_game();
        }
    }
}
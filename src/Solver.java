public class Solver {
    public enum cell {YES, NO, MAYBE}
    public int size;
    private final Block[] rows;
    private final Block[] columns;
    private final cell[][] grid;

    public Solver(Block[] rows, Block[] columns) {
        this.size = rows.length;
        this.rows = rows;
        this.columns = columns;
        grid = new cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = cell.MAYBE;
            }
        }
    }

    public cell cell_at(int x, int y) {
        return grid[y][x];
    }

    public void iterate(int i) {
        sweep(i % 2 == 0);
    }
    //if sweep_rows is false, we sweep columns
    private void sweep(boolean sweep_rows) {
        Block[] block_list = sweep_rows ? rows : columns;
        for (int i = 0; i < size; i++) {
            long yes_bits = 0;
            long no_bits = 0;
            for (int j = size - 1; j >= 0; j--) {
                int y = sweep_rows ? i : j;
                int x = sweep_rows ? j : i;
                yes_bits = (yes_bits << 1) + (grid[y][x] == cell.YES ? 1 : 0);
                no_bits = (no_bits << 1) + (grid[y][x] == cell.NO ? 1 : 0);
            }
            long yes_intersect = intersect_perms(size, block_list[i], cell.YES, yes_bits, no_bits);
            long no_intersect = intersect_perms(size, block_list[i], cell.NO, yes_bits, no_bits);
            for (int j = 0; j < size; j++) {
                int y = sweep_rows ? i : j;
                int x = sweep_rows ? j : i;
                if (grid[y][x] == cell.MAYBE)
                    grid[y][x] = cell_from_longs(yes_intersect, no_intersect, j);
            }
        }
    }

    private static cell cell_from_longs(long yes_intersect, long no_intersect, int bit) {
        boolean yes_bit = get_bit(yes_intersect, bit);
        boolean no_bit = get_bit(no_intersect, bit);
        return yes_bit ? cell.YES : (no_bit ? cell.NO : cell.MAYBE);
    }

    //it is assumed when this method is called that there is at least 1 valid permutation
    private long intersect_perms(int n, Block head, cell eval_type, long yes_req, long no_req) {
        //if we are done with the list, the remaining spaces are all "no"s, which is all 0's or all 1's
        if (head == null)
            return flip_by_eval(0, eval_type);

        long block_mask = (1L << (head.length + 1)) - 1;
        long block_data = (1L << head.length) - 1;
        long use_block = flip_by_eval(block_data, eval_type) & block_mask;

        long remainder = intersect_perms(n - head.length - 1, head.next, eval_type, yes_req, no_req);
        long with_block = use_block | (remainder << (head.length + 1));
        long no_bit = flip_by_eval(0, eval_type) & 1;
        long without_block = (head.sum <= n - 1) ?
                no_bit | (intersect_perms(n - 1, head, eval_type, yes_req, no_req) << 1) : ~0;

        return require(with_block, n, eval_type, yes_req, no_req) &
                require(without_block, n, eval_type, yes_req, no_req) & ((1L << n) - 1);
    }

    private long require(long x, long bits, cell eval_type, long yes_req, long no_req) {
        yes_req = yes_req >> (size - bits);
        no_req = no_req >> (size - bits);
        long x_adj = flip_by_eval(x, eval_type);
        boolean satisfies_reqs = ((yes_req & ~x_adj) | (no_req & x_adj)) == 0;
        return satisfies_reqs ? x : ~0;
    }

    //need z such that depending on value of z, for all x, f(x, z) = x or ~x
    //Solution: (x & z) | (~x & ~z), Where z = 0 or ~0
    private static long flip_by_eval(long n, cell eval_type) {
        return (eval_type == cell.YES) ? n : ~n;
    }

    private static boolean get_bit(long z, int n) {
        return (1 & (z >> n)) == 1;
    }
}



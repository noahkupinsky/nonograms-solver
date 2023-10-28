public class Block {
    public Block next = null;
    public int length;
    public int sum;

    public Block(int length) {
        this.length = length;
        this.sum = length;
    }

    public void link(Block other) {
        next = other;
        sum = other.sum + 1 + length;
    }

    static public Block from_array(int[] arr) {
        if (arr.length == 1 && arr[0] == 0) {
            return null;
        }
        Block prev = null;
        Block cur = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            cur = new Block(arr[i]);
            if (prev != null)
                cur.link(prev);
            prev = cur;
        }
        return cur;
    }

}

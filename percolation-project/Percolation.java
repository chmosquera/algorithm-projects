
public class Percolation {


    boolean[][] sites;
    int[] connected;
    int n;
    int openSiteCount = 0;
    
    private boolean inBounds(int row, int col) {
        if ((row < n && row >= 0) && (col < n && col >= 0)) {
            return true;
        }
        return false;
    } 

    private void connect(int p, int q) {
        connected[root(p)] = root(q);       // make more efficient later, O(2n) -> O(n)
    }

    // O(2n)
    private boolean isConnected(int p, int q) {
        //      2  23 23 23
        // idx: 1  2  9  23

        return root(p) == root(q);
    }

    // O(n)
    private int root(int idx) {
        if (connected[idx] == idx) 
            return idx;        
        return root(connected[idx]);
    }

    private int getIndex(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IllegalArgumentException(
                String.format("open() was called on a site outside of the range: row=%d, col=%d")
            );
        }
        return n * row + col;
    }

    // creates n-by-n grid, with all sites initially blocked
    // (1,1) is upper-left site
    public Percolation(int n) {

        this.n = n;

        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.sites = new boolean[n][n];
        this.connected = new int[(int)Math.pow(n, 2.0)];

        for (int i = 0; i < (Math.pow(n, 2.0) - 1); i++) {
            connected[i] = i;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        
        if (!inBounds(row, col)) {
            throw new IllegalArgumentException(
                String.format("open() was called on a site outside of the range: row=%d, col=%d")
            );
        }

        if (sites[row][col] == true) {
            return;
        }

        sites[row][col] = true;
        openSiteCount++;

        int idx = getIndex(row, col);

        // Top: row - 1, col
        if (inBounds(row-1, col) && isOpen(row-1, col)) {
            connect(idx, getIndex(row-1, col));
        }
        // Right: row, col + 1
        if (inBounds(row, col+1) && isOpen(row, col+1)) {
            connect(idx, getIndex(row, col+1));
        }
        // Bottom: row + 1, col
        if (inBounds(row+1, col) && isOpen(row+1, col)) {
            connect(idx, getIndex(row+1, col));
        }
        // Left: row, col - 1
        if (inBounds(row, col-1) && isOpen(row, col-1)) {
            connect(idx, getIndex(row, col-1));
        }

    }



    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        
        if (!inBounds(row, col)) {
            throw new IllegalArgumentException(
                String.format("open() was called on a site outside of the range: row=%d, col=%d")
            );
        }        

        return sites[row][col];
    }

    // is the site (row, col) full?
    // O(n^2)
    public boolean isFull(int row, int col) {
        
        if (!inBounds(row, col)) {
            throw new IllegalArgumentException(
                String.format("open() was called on a site outside of the range: row=%d, col=%d")
            );
        }

        // for each site in first row
            // call connect

        for (int i = 0; i < n; i++) {
            if (isConnected(getIndex(0, i), getIndex(row, col))) {
                return true;
            }
        }

        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteCount;
    }

    // does the system percolate?
    // O(n^3)
    public boolean percolates() {
                
        // Loop is O(n^2)
        for (int i = 0; i < n; i++) {
            // Each call of isConnected is O(2n)
            boolean result = isFull(n-1, i);
            System.out.printf("Checking if site (%d,%d) is full: %b\n", n-1, i, result);
            
            if (result) {
                return true;
            }
        }

        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        // Percolation percolation = new Percolation(5);

        // open(int row, int col)
        // isOpen(int row, int col)
        // isFull(int row, int col)
        // numberOfOpenSites()
        // percolates()
    }
}
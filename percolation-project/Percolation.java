
public class Percolation {


    boolean[][] sites;
    int[] connected;
    int[] sizes;
    int n;
    int openSiteCount = 0;
    
    private void printSize() {
        for (int i = 0; i < sizes.length; i++) {
            if ((sizes[i]) > 1) {
                System.out.printf("Index: %d is a root with %d connections\n", i, sizes[i]);
            }
        }
    }

    private boolean inBounds(int row, int col) {
        if ((row < n && row >= 0) && (col < n && col >= 0)) {
            return true;
        }
        return false;
    } 

    // O(2logn) for finding two roots. By connected the root of smaller tree to root of larger tree, 
    //  we create a wider tree, so it takes less steps to find the root. 
    private void connect(int p, int q) {
        int root_p = root(p);
        int root_q = root(q);

        if (root_p == root_q) 
            return;

        if (sizes[root_p] <= sizes[root_q]) {            
            connected[root_p] = root_q;            
            sizes[root_q] += sizes[root_p];
            sizes[root_p] = 0;
        } else {
            connected[root_q] = root_p;
            sizes[root_p] += sizes[root_q];
            sizes[root_q] = 0;
        }
    }

    // O(2n)
    private boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }

    // O(logn)
    private int root(int idx) {
        if (connected[idx] == idx) 
            return idx;        
        return root(connected[idx]);
    }

    private int getIndex(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IllegalArgumentException(
                String.format("open() was called on a site outside of the range: row=%d, col=%d", row, col)
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
        this.sizes = new int[connected.length];

        for (int i = 0; i < (Math.pow(n, 2.0) - 1); i++) {
            connected[i] = i;
            sizes[i] = 1;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        
        if (!inBounds(row, col)) {
            throw new IllegalArgumentException(
                String.format("open() was called on a site outside of the range: row=%d, col=%d", row, col)
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
                String.format("open() was called on a site outside of the range: row=%d, col=%d", row, col)
            );
        }        

        return sites[row][col];
    }

    // is the site (row, col) full?
    // O(n^2)
    public boolean isFull(int row, int col) {
        
        if (!inBounds(row, col)) {
            throw new IllegalArgumentException(
                String.format("open() was called on a site outside of the range: row=%d, col=%d", row, col)
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
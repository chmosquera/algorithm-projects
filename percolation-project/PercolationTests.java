

public class PercolationTests {
    
    public static void main(String [] args) {
        PercolationTests percolationTest = new PercolationTests();
        // percolationTest.testOpen();
        // percolationTest.testIsFull();
        // percolationTest.testNumberOfOpenSites();
        // percolationTest.testPercolate();
        percolationTest.testPercolateComplex();


        // percolates()
    }

    public PercolationTests() {

    }


    // Test numberOfOpenSites()
    public void testNumberOfOpenSites()
    {
        Percolation P = new Percolation(5);
        boolean result = false;

        P.open(0, 0);

        // numberOfOpenSites -- assert true
        result = (P.numberOfOpenSites() == 1);
        try {
            assert result : "Test failed: 'numberOfOpenSites -- assert true'";
            System.out.printf("Test succeeded: 'numberOfOpenSites - assert true'\n");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // numberOfOpenSites -- assert false
        result = (P.numberOfOpenSites() == 0);
        try {
            assert !result : "Test failed: 'numberOfOpenSites -- assert false'";
            System.out.printf("Test succeeded: 'numberOfOpenSites - assert false'\n");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    // Tests isFull()
    public void testIsFull() {

        Percolation P = new Percolation(5);
        boolean result = false;
        P.open(0, 0);
        P.open(1, 0);
        P.open(2, 0);

        // isFull - assert true
        result = P.isFull(1, 0) && P.isFull(2, 0);
        try {            
            assert result == true : "Test failed: 'isFull - assert true'";
            System.out.printf("Test succeeded: 'isFull - assert true'\n");
        } catch (AssertionError e) {
            System.err.println(e.getMessage());
        }

        // isFull - assert false
        // Neither of these sites should be full
        result = P.isFull(1, 1) || P.isFull(2, 1);
        try {            
            assert result == false : "Test failed: 'isFull - assert false'";            
            System.out.printf("Test succeeded: 'isFull - assert false'\n");
        } catch (AssertionError e) {
            System.err.println(e.getMessage());
        }

    }

    // Tests open() and isOpen()
    public void testOpen() {
        
        Percolation P = new Percolation(5);
        boolean result = false;

        // open - throws error
        try {
            P.open(0, 5);
            System.out.println("Test failed: 'open - throws error'");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Test succeeded: 'open - throws error'");
        }

        // open - does not throw error
        try {
            P.open(0, 4);
            System.out.println("Test succeeded: 'open - does not throw error");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Test failed: 'open - does not throw error");
        }

        // isOpen - throws error
        try {
            P.open(-1, 2);
            System.out.println("Test failed: 'isOpen - throws error'");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Test succeeded: 'isOpen - throws error'");
        }

        // isOpen - assert true
        P.open(2, 2);
        result = P.isOpen(2, 2) == true;
        try {
            assert result : "Test failed: 'isOpen - assert true'";
            System.out.printf("Test succeeded: 'isOpen - assert true'\n");
        } 
        catch (AssertionError e) {
            System.err.println(e.getMessage());
        }

        // isOpen - assert false
        result = P.isOpen(1, 2);
        
        try {
            assert !result : "Test failed: 'isOpen() - assert false'";
            System.out.printf("Test succeeded: 'isOpen - assert fail'\n");
        } 
        catch (AssertionError e) {
            System.err.println(e.getMessage());
        }
    }


    public void testPercolate() {
        Percolation P = new Percolation(5);
        boolean result = false;

        P.open(0,0);
        P.open(0,1);

        P.open(0,2);
        P.open(0,3);
        P.open(0,4);

        result = P.isFull(0, 4);
        System.out.printf("isFull(0,4)? %s\n", Boolean.toString(result));

        result = P.percolates();
        try {
            assert result : "Test failed: percolate() - expected to percolate";
            System.out.printf("Test success: percolate() - assert true\n");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /*
     *   o - - - -
     *   o o o - -
     *   o - o o - 
     *   - o o o o
     *   - - - o -
     */
    public void testPercolateComplex() {
        Percolation P = new Percolation(5);
        boolean result = false;

        P.open(0,0);
        P.open(1, 0);
        P.open(1, 1);
        P.open(1, 2);
        P.open(2, 2);
        P.open(2, 0);
        P.open(2, 3);
        P.open(3, 3);
        P.open(3, 1);
        P.open(3, 2);
        P.open(3, 4);
        P.open(4, 3);

        result = P.percolates();
        try {
            assert result : "Test failed: percolate() - expected to percolate";
            System.out.printf("Test success: percolate() - assert true\n");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

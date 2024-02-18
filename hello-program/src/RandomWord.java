/******************************************************************************
 *  Compilation:  javac RandomWord.java
 *  Execution:    java RandomWord
 *
 *  Randomly selects a name from user-provided standard input.
 *
 *  % java RandomWord
 *  Michael
 *  Isabelle
 *  Jacob
 *  Liam
 *  Jessica
 *  Chantel
 *  quit
 *  The champion is Liam
 ******************************************************************************/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {

        int i = 0;
        String champion = new String();
        while (!StdIn.isEmpty()) {

            String next = StdIn.readString();
            i++;
            if (next.equals("quit")) break;

            if (StdRandom.bernoulli(1.0/i)) {
                champion = next;
            }

        }
        StdOut.println("The champion is " + champion);

    }   
}

            
            
            
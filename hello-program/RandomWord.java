/******************************************************************************
 *  Compilation:  javac RandomWord.java
 *  Execution:    java RandomWord
 *
 *  Randomly selects a word from user-provided standard input.
 *
 *  % touch headsTails.txt
 *  % echo "Heads Tails" >> headsTails.txt
 *  % java RandomWord << headsTails.txt
 ******************************************************************************/

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {

        int i = 0;
        String champion = "";
        while (!StdIn.isEmpty()) {

            String next = StdIn.readString();
            i++;
            if (next.equals("quit")) break;

            if (StdRandom.bernoulli(1.0/i)) {
                champion = next;
            }

        }
        StdOut.println(champion);

    }   
}

            
            
            
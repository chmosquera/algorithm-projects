/******************************************************************************
 *  Compilation:  javac HelloGoodbye.java
 *  Execution:    java HelloGoodbye
 *
 *  Prints "Hello" message to names provided via standard input. 
 *  Prints "Goodbye" message to names in reverse order.
 *
 *  % java HelloGoodbye 
 *    Chantel 
 *    Michael
 *    Hello Chantel and Michael.
 *    Goodbye Michael and Chantel.
 *  
 ******************************************************************************/

public class HelloGoodbye {
    public static void main(String[] args) {

        System.out.printf("Hello %s and %s.\n", args[0], args[1]);
        System.out.printf("Goodbye %s and %s.\n", args[1], args[0]);
    }
}

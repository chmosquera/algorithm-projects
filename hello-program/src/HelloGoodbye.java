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

        StringBuilder hello_msg = new StringBuilder("Hello");

        for (int idx = 0; idx < args.length; idx++) {
            hello_msg.append(" " + args[idx]);

            if (idx < args.length -1) {
                hello_msg.append(" and");
            }
        }
        hello_msg.append(".");
        System.out.println(hello_msg);

        StringBuilder bye_msg = new StringBuilder("Goodbye");
        for (int idx = args.length-1; idx >= 0; idx--) {
            bye_msg.append(" " + args[idx]);

            if (idx > 0) {
                bye_msg.append(" and");
            }
        }
        bye_msg.append(".");
        System.out.println(bye_msg);
    }
}

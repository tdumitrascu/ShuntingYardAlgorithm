package P3;

import java.util.Scanner;

public class Main {
    public static final int POSTFIX_GENERATE = 1;
    public static final int POSTFIX_EVAL = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int exercise = scanner.nextInt();
        scanner.nextLine();
        String expression = scanner.nextLine();

        switch (exercise){
            case POSTFIX_GENERATE:
                PostfixEvaluator e = new PostfixEvaluator(expression);
                System.out.println(e.getPostfix());
                break;

            case POSTFIX_EVAL:
                System.out.println(PostfixEvaluator.evaluateNotation(expression));
                break;

        }
    }
}

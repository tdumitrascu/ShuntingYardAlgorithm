package P3;

import java.sql.SQLOutput;
import java.util.Deque;
import java.util.LinkedList;

public class PostfixEvaluator {
    private String expression;
    private String postfix;
    private final Deque<Character> operatorStack;

    public PostfixEvaluator(String expression) {
        this.expression = expression.trim().replaceAll("\\s+", "");
        this.operatorStack = new LinkedList<>();

        generateNotation();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
        generateNotation();
    }

    private void generateNotation() {
        // TO DO:
        postfix = "";

        for (int i = 0; i < expression.length(); ++i) {

            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                postfix += c;
            }
            else if (c == '(') {
                operatorStack.push(c);
            }
            else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix += operatorStack.pop();
                }
                operatorStack.pop();
            }
            else {
                while (!operatorStack.isEmpty() && getPrecedence(c) <= getPrecedence(operatorStack.peek())
                                && (c == '+' || c == '-' || c == '/' || c == '*')) {
                    postfix += operatorStack.pop();
                }
                operatorStack.push(c);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfix += operatorStack.pop();
        }
    }

    public static int evaluateNotation(String postfix) {
        // TO DO:

        Deque<Integer> operandStack = new LinkedList<>();

        int result = 0;

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (Character.isDigit(c)) {
                operandStack.push(c - '0');
            } else {
                int op1 = operandStack.pop();
                if (operandStack.isEmpty()) {
                    System.out.println("Expresie postfixata gresita");
                    return -1;
                }
                int op2 = operandStack.pop();

                switch (c) {
                    case '+':
                        result = op2 + op1;
                        break;
                    case '-':
                        result = op2 - op1;
                        break;
                    case '/':
                        result = op2 / op1;
                        break;
                    case '*':
                        result = op2 * op1;
                        break;
                    case '^':
                        result = (int) Math.pow(op2,op1);
                        break;
                    default:
                        System.out.println("Expresie postfixata gresita");
                        break;
                }
                operandStack.push(result);
            }
        }
        if (!operandStack.isEmpty()) {
            System.out.println("Expresie postfixata gresita");
            return -1;
        }
        return result;
    }

    public String getPostfix() {
        return postfix;
    }

    public int getPrecedence (char op) {
        switch (op) {
            case '+':
            case '-':
                return 11;
            case '*':
            case '/':
                return 12;
            case '^':
                return 13;
            default:
                return -1;
        }
    }
}



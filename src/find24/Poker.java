/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find24;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Administrator
 */
public class Poker extends Application {

    @Override
    public void start(Stage primaryStage) {
        //创建主面板
        DivPane diyPane = new DivPane();
        Scene scene = new Scene(diyPane, 800, 480);
        int[] point = new int[4];
        //创建弹出框类
        AlertBox alert = new AlertBox();


        diyPane.find.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] stringNumber = new String[4];
                int[] intNumber = new int[4];

                //获取四个字符串形式的字符串
                for (int i = 0; i < stringNumber.length; i++) {
                    stringNumber[i] = diyPane.input[i].getText();
                }

                //检测四个字符串是否符合规范
                Boolean check = checkFourNumber(stringNumber);
                if (check == true) {
                    //将四个字符串转为int类型
                    for (int i = 0; i < stringNumber.length; i++) {
                        intNumber[i] = (int) (Double.parseDouble(stringNumber[i]));
                    }
                    if (checkFourNumberValue(intNumber) == true) {
                        String solution = findSolution(intNumber);
                        if (solution.equals("No solution")) {
                            diyPane.solution.setText("NO solution");
                            diyPane.setBottom(diyPane.foot);
                        } else {
                            diyPane.solution.setText(solution);
                            diyPane.setBottom(diyPane.foot);
                        }
                    } else {
                        alert.display("警告", "输入数字应在1-9");
                    }
                } else {
                    alert.display("警告", "请输入1-9的整数");
                }
            }
        });
        primaryStage.setTitle("MyPoker");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //检查输入的四个数是否符合规范
    public Boolean checkFourNumber(String[] input) {
        try {
            for (int i = 0; i < input.length; i++) {
                int test = (int) (Integer.parseInt(input[i]));
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //检查四个数是否是在1-9点
    public Boolean checkFourNumberValue(int[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] < 1 || input[i] > 9) {
                return false;
            }
        }
        return true;
    }

    //找到一个表达式
    public String findSolution(int[] point) {
        for (int i = 0; i < point.length; i++) {
            point[i] = point[i] % 13;
            if (point[i] == 0) {
                point[i] = 13;
            }
        }
        int a = point[0];
        int b = point[1];
        int c = point[2];
        int d = point[3];
        String noSolution = "No solution";
        String solution;
        String[] operators = {"+", "-", "*", "/"};
        int[][] allCombinations = {
                {a, b, c, d}, {d, a, b, c},
                {c, d, a, b}, {b, c, d, a}, {a, b, d, c}, {c, a, b, d},
                {d, c, a, b}, {b, d, c, a}, {a, d, c, b}, {b, a, d, c},
                {c, b, a, d}, {d, c, b, a}, {a, c, b, d}, {d, a, c, b},
                {b, d, a, c}, {c, b, d, a}, {b, a, c, d}, {d, b, a, c},
                {c, d, b, a}, {a, c, d, b}, {a, d, b, c}, {c, a, d, b},
                {b, c, a, d}, {d, b, c, a}};

        for (String firstOp : operators) {
            for (String secondOp : operators) {
                for (String thirdOp : operators) {
                    for (int[] cardNums : allCombinations) {
                        for (int i = 0; i < 4; i++) {
                            switch (i) {
                                case 0: {
                                    solution = cardNums[0] + firstOp
                                            + cardNums[1] + secondOp
                                            + cardNums[2] + thirdOp
                                            + cardNums[3];
                                    if (EvaluateExpression
                                            .evaluateExpression(solution) == 24) {
                                        return solution;
                                    }
                                }
                                case 1: {
                                    solution = "(" + cardNums[0] + firstOp
                                            + cardNums[1] + ")" + secondOp
                                            + "(" + cardNums[2] + thirdOp
                                            + cardNums[3] + ")";
                                    if (EvaluateExpression
                                            .evaluateExpression(solution) == 24) {
                                        return solution;
                                    }
                                }
                                case 2: {
                                    solution = "((" + cardNums[0] + firstOp
                                            + cardNums[1] + ")" + secondOp
                                            + cardNums[2] + ")" + thirdOp
                                            + cardNums[3];
                                    if (EvaluateExpression
                                            .evaluateExpression(solution) == 24) {
                                        return solution;
                                    }
                                }
                                case 3: {
                                    solution = cardNums[0] + firstOp + "(("
                                            + cardNums[1] + secondOp
                                            + cardNums[2] + ")" + thirdOp
                                            + cardNums[3] + ")";
                                    if (EvaluateExpression
                                            .evaluateExpression(solution) == 24) {
                                        return solution;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return noSolution;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static class EvaluateExpression {

        /**
         * Evaluate an expression
         */
        public static Double evaluateExpression(String expression) {
            // Create operandStack to store operands
            GenericStack<Double> operandStack = new GenericStack<Double>();

            // Create operatorStack to store operators
            GenericStack<Character> operatorStack = new GenericStack<Character>();

            // Extract operands and operators
            java.util.StringTokenizer tokens = new java.util.StringTokenizer(
                    expression, "()+-/*", true);

            // Phase 1: Scan tokens
            while (tokens.hasMoreTokens()) {
                String token = tokens.nextToken().trim(); // Extract a token
                if (token.length() == 0) // Blank space
                {
                    continue; // Back to the while loop to extract the next token
                } else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
                    // Process all +, -, *, / in the top of the operator stack
                    while (!operatorStack.isEmpty()
                            && (operatorStack.peek() == '+' || operatorStack.peek() == '-'
                            || operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                        processAnOperator(operandStack, operatorStack);
                    }

                    // Push the + or - operator into the operator stack
                    operatorStack.push(token.charAt(0));
                } else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
                    // Process all *, / in the top of the operator stack
                    while (!operatorStack.isEmpty()
                            && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                        processAnOperator(operandStack, operatorStack);
                    }

                    // Push the * or / operator into the operator stack
                    operatorStack.push(token.charAt(0));
                } else if (token.trim().charAt(0) == '(') {
                    operatorStack.push('('); // Push '(' to stack
                } else if (token.trim().charAt(0) == ')') {
                    // Process all the operators in the stack until seeing '('
                    while (operatorStack.peek() != '(') {
                        processAnOperator(operandStack, operatorStack);
                    }

                    operatorStack.pop(); // Pop the '(' symbol from the stack
                } else { // An operand scanned
                    // Push an operand to the stack
                    operandStack.push(new Double(token));
                }
            }

            // Phase 2: process all the remaining operators in the stack
            while (!operatorStack.isEmpty()) {
                processAnOperator(operandStack, operatorStack);
            }

            // Return the result
            return operandStack.pop();
        }

        /**
         * Process one opeator: Take an operator from operatorStack and apply it
         * on the operands in the operandStack
         */
        public static void processAnOperator(GenericStack<Double> operandStack,
                                             GenericStack<Character> operatorStack) {
            char op = operatorStack.pop();
            double op1 = operandStack.pop();
            double op2 = operandStack.pop();
            switch (op) {
                case '+':
                    operandStack.push(op2 + op1);
                    break;
                case '-':
                    operandStack.push(op2 - op1);
                    break;
                case '*':
                    operandStack.push(op2 * op1);
                    break;
                case '/': {
                    if (op1 != 0) {
                        operandStack.push(op2 / op1);
                    } else {
                        operandStack.push(0.0);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String[][] operands = getVariables(args[0]);
        String infix = getInfix(args[0]);
        String[] stringArr = infix.split(" ");
        System.out.println(calculator(stringArr, operands));
    }

    /**
     * @param strArr infix split with spaces
     * @param variables variable matrix in the infix
     * @return result of the infix
     */
    private static double calculator(String[] strArr, String[][] variables) {
        Stack<String> operators = new Stack<>();
        Stack<Double> operands = new Stack<>();
        String op = "";

        for(String str : strArr) {
            if (str.trim().equals("")) continue;

            switch(str) {
                case "sin(":
                    op = "sin";
                    break;
                case "cos(":
                    op = "cos";
                    break;
                case "abs(":
                    op = "abs";
                    break;
                case "(":
                    break;
                case ")":
                    double right = operands.pop();
                    double left = operands.pop();
                    String operator = operators.pop();
                    double value = 0;
                    switch(operator) {
                        case "+":
                            value = left + right;
                            break;
                        case "-":
                            value = left - right;
                            break;
                        case "*":
                            value = left * right;
                            break;
                        case "/":
                            value = left / right;
                            break;
                        default:
                            break;
                    }
                    switch (op) {
                        case "sin":
                            operands.push(sin(value));
                            break;
                        case "cos":
                            operands.push(cos(value));
                            break;
                        case "abs":
                            operands.push(abs(value));
                            break;
                        default:
                            operands.push(value);
                            break;
                    }
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    operators.push(str);
                    break;
                default:
                    int p = 0;
                    for (String[] variable : variables) {
                        if (str.equals(variable[0])) {
                            operands.push(Double.parseDouble(variable[1]));
                            p++;
                        }
                    }
                    if (p == 0) {
                        operands.push(Double.parseDouble(str));
                    }
                    break;
            }
        }

        return operands.pop();
    }

    /**
     * @param fileName file path to read infix
     * @return infix string
     */
    private static String getInfix(String fileName) {
        boolean next = false;
        String line = "";
        try {
            FileReader fileReader1 = new FileReader(fileName);
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            while((line = bufferedReader1.readLine()) != null) {

                if (line.isEmpty()) {
                    next = true;
                    continue;
                }
                if (next) {
                    break;
                }
            }
            bufferedReader1.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return line;
    }

    /**
     * @param fileName file path to read variable values in the infix
     * @return variable matrix
     */
    private static String[][] getVariables(String fileName) {
        int size = getVarCount(fileName);
        String[][] operands = new String[size][2];
        String line;
        try {
            FileReader fileReader1 = new FileReader(fileName);
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            int i = 0;
            while((line = bufferedReader1.readLine()) != null) {

                if (line.isEmpty()) {
//                    System.out.println("YES");
                    break;
                }
                operands[i] = line.split("=");
                i++;
//                System.out.println(line);

            }
            bufferedReader1.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return operands;
    }

    /**
     * @param fileName file path to get how many variables in the infix
     * @return variable count in the infix
     */
    private static int getVarCount(String fileName) {
        String line;
        int i = 0;
        try {
            FileReader fileReader1 = new FileReader(fileName);
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);

            while((line = bufferedReader1.readLine()) != null) {
                if (line.isEmpty()) break;
                i++;
            }

            bufferedReader1.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return i;
    }

    /**
     * @param n number to calculate factorial of
     * @return factorial of input number
     */
    private static double factorial(double n) {
        if (n == 0) return 1;
        if (n == 1) return n;
        return n * factorial(n - 1);
    }

    /**
     * @param m exponent
     * @param n base
     * @return double result
     */
    private static double exp(double m, double n) {
        double result = 1;
        for (int j = 0; j < n; j++) {
            result = result * m;
        }
        return result;
    }

    /**
     * @param num degree to calculate sin
     * @return sin value of input
     */
    private static double sin(double num) {
        double sum = 0;
        double n = 6;
        double x = (num / 180) * 3.14159265358979323846;
        for (double i = 0; i < n; i++) {
            sum = sum + (
                    exp(-1, i) * (
                            exp(x , ((2 * i) + 1)) / factorial(((2 * i) + 1))
                    )
            );
        }
        return sum;
    }

    /**
     * @param num degree to calculate cos
     * @return cos value of input
     */
    private static double cos(double num) {
        double sum = 0;
        double n = 6;
        double x = (num / 180) * 3.14159265358979323846;
        for (double i = 0; i < n; i++) {
            sum = sum + (
                    exp(-1, i) * (
                            exp(x , (2 * i)) / factorial(2 * i)
                    )
            );
        }
        return sum;
    }

    /**
     * @param num input number
     * @return absolute of input number
     */
    private static double abs(double num) {
        if (num < 0) {
            return -1 * num;
        }
        else {
            return num;
        }
    }
}

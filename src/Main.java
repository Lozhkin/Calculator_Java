import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static boolean isRoman = false;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        System.out.println(calc(string));
    }

    public static String calc(String input) throws Exception {
        String[] expression = input.split(" ");
        int[] operands = operand(expression);
        int operand1 = operands[0];
        int operand2 = operands[1];
        char operator = expression[1].charAt(0);
        int result = calculationResult(operator, operand1, operand2);

        String output = "";
        if (isRoman) {
            output = processing(result);
        } else output = "" + result;
        return output;
    }

    private static String processing(int result) throws Exception {
        StringBuilder romanResult = new StringBuilder();

        String[] romNum = { "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] vals = { 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        int count = 0;
        while(count < romNum.length)
        {
            while(result >= vals[count])
            {
                int d = result / vals[count];
                result = result % vals[count];
                for(int i=0; i<d; i++)
                    romanResult.append(romNum[count]);
            }
            count++;
        }

        if (romanResult.toString().isEmpty()) throw new Exception();

        return romanResult.toString();
    }

    private static int calculationResult(char operator, int operand1, int operand2) throws Exception {
        int output = 0;
        switch (operator) {
            case '+':
                output = operand1 + operand2;
                break;
            case '-':
                output = operand1 - operand2;
                break;
            case '*':
                output = operand1 * operand2;
                break;
            case '/':
                output = operand1 / operand2;
                break;
            default:
                throw new Exception();
        }
        return output;
    }

    private static int[] operand(String[] s) {
        int operand1;
        int operand2;
        try {
            int preOperand1 = Integer.parseInt(s[0]);
            operand1 = (preOperand1 < 11 && preOperand1 > 0) ? preOperand1 : 1/(preOperand1 - Integer.parseInt(s[0]));
            int preOperand2 = Integer.parseInt(s[2]);
            operand2 = (preOperand2 < 11 && preOperand2 > 0) ? preOperand2 : 1/(preOperand2 - Integer.parseInt(s[2]));
        } catch (NumberFormatException e) {
            isRoman = true;
            RomanNumeral rom1 = RomanNumeral.valueOf(s[0]);
            RomanNumeral rom2 = RomanNumeral.valueOf(s[2]);
            ;
            return new int[] {rom1.getArabicNumeral(), rom2.getArabicNumeral()};
        }

        return new int[] {operand1, operand2};
    }
}
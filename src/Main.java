import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {                      // Input
        System.out.println("Введите арифметическое выражение из двух целочисленных операндов и одного оператора, разделяя знаки пробелом:");
        Scanner expression = new Scanner(System.in);
        String expressionStr = expression.nextLine();
        System.out.println(calc(expressionStr));                                   // Output
    }

    public static String calc(String input) throws Exception {
        String[] expressionArray = input.split(" ");           // Split
        if (expressionArray.length != 3) {
           throw new Exception("Ошибка ввода: Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, *, /.");
        }

        boolean checkArabic = true;                                  // Init..
        boolean checkRomanNum1 = false;
        boolean checkRomanNum2 = false;
        int num1 = 0;
        int num2 = 0;
        int value = 0;
        String romanValue = "";

        try {                                                        // Check Arabic [DONE]
            num1 = Integer.parseInt(expressionArray[0]);
            num2 = Integer.parseInt(expressionArray[2]);
        } catch (NumberFormatException e) {
            checkArabic = false;
            //System.out.println("checkArabic = " + checkArabic);
            for (Numerals n : Numerals.values()) {                       // Check Roman [IN PROGRESS]
                if (expressionArray[0].equalsIgnoreCase(n.getRoman())) {
                    checkRomanNum1 = true;
                    num1 = n.getArabic();                     // num1 to arabic
                }
                if (expressionArray[2].equalsIgnoreCase(n.getRoman())) {
                    checkRomanNum2 = true;
                    num2 = n.getArabic();                 // num2 to arabic
                }
            }
            if (checkRomanNum1 && checkRomanNum2) {                     // Check both nums Roman
                checkArabic = true;
            } else {
                throw new Exception("Ошибка ввода: Используются одновременно разные системы счисления.");
            }
        }

        if (checkArabic) {
            if (num1 < 1 || num1 > 10 || num2 <1 || num2 > 10) {         // Check nums [DONE]
                throw new Exception("Ошибка ввода: Допустимы целочисленные значения от 1 до 10 (I - X).");
            }
            String arithmeticSign = expressionArray[1];               // Arithmetic [DONE]
            switch (arithmeticSign) {
                case "+" -> value = num1 + num2;
                case "-" -> value = num1 - num2;
                case "*" -> value = num1 * num2;
                case "/" -> value = num1 / num2;
                default -> {
                    throw new Exception("Ошибка ввода: Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, *, /.");
                }
            }
        }

        if (checkRomanNum1 && checkRomanNum2) {                       // Arabic value to Roman
            if (value <1) {
                throw new Exception("Ошибка вывода: В римской системе нет отрицательных чисел.");  //throw negative Roman
            }
            int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] rom = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            int tmp = 0;
            for (int i = 0; i < arab.length; i++){
                tmp = value / arab[i];
                for (int y = 0; y < tmp; y++){
                    romanValue = romanValue.concat(rom[i]);
                }
                value = value % arab[i];
            }
        }

        if (checkRomanNum1 && checkRomanNum2) {                                  // for Output
            return romanValue;
        } else {
            String arabicValue = String.valueOf(value);
            return arabicValue;
        }

    }
}






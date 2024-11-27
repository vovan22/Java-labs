public class Main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            try {
                System.out.print("Введіть перше число: ");
                double num1 = Double.parseDouble(scanner.nextLine());

                System.out.print("Введіть друге число: ");
                double num2 = Double.parseDouble(scanner.nextLine());

                System.out.print("Введіть операцію (+, -, *, /): ");
                String operation = scanner.nextLine();

                double result;

                switch (operation) {
                    case "+":
                        result = calculator.add(num1, num2);
                        break;
                    case "-":
                        result = calculator.subtract(num1, num2);
                        break;
                    case "*":
                        result = calculator.multiply(num1, num2);
                        break;
                    case "/":
                        result = calculator.divide(num1, num2);
                        break;
                    default:
                        throw new InvalidInputException("Невідома операція!");
                }

                System.out.println("Результат: " + result);

            } catch (NumberFormatException e) {
                System.out.println("Помилка: Будь ласка, введіть коректні числа.");
            } catch (InvalidInputException | ArithmeticException e) {
                System.out.println("Помилка: " + e.getMessage());
            } finally {
                System.out.println("Обробка запиту завершена.");
            }

            System.out.print("Бажаєте продовжити? (y/n): ");
            String continueChoice = scanner.nextLine();
            if (!continueChoice.equalsIgnoreCase("y")) {
                break;
            }
        }

        scanner.close();
    }
}
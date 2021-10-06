package lesson6;

import java.io.IOException;
import java.util.Scanner;
import lesson6.Controller;
import java.util.regex.Pattern;

public class UserInterfaceView {
    private Controller controller = new Controller();
    private Pattern pattern;

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите имя города: ");
            String city = scanner.nextLine();

            System.out.println("Введите 1 для прогноза на 1 день, 5 для прогноза на 5 дней. Для выхода введите 0.");
            String command = scanner.nextLine();

            //TODO* Сделать метод валидации пользовательского ввода

            if (!Pattern.matches("[1,5]","command")) {
            System.out.println("Нужно ввести 1 или 5 для получения прогноза");
            }

            if (command.equals("0")) break;

            try {
                controller.getWeather(command, city);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
package lesson1;

public class Main {
   // 1. Разобраться с имеющимся кодом.
   // 2. Добавить класс Team, который будет содержать:
   // название команды;
   // массив из четырех участников — в конструкторе можно сразу всех участников указывать);
    // метод для вывода информации о членах команды, прошедших дистанцию;
   //  метод вывода информации обо всех членах команды.
   // 3. Добавить класс Course (полоса препятствий), в котором будут находиться:
   //  массив препятствий;
   //  метод, который будет просить команду пройти всю полосу.
   //  В итоге должно получиться похожее:
   //   public static void main(String[] args) {
   //   Course c = new Course(...); // Создаем полосу препятствий
   //   Team team = new Team(...); // Создаем команду
   //   c.doIt(team); // Просим команду пройти полосу
   //   team.showResults(); // Показываем результаты

    public static void main(String[] args) {

        Course course1 = new Course(new Cross(300), new Wall(2),
                new Cross(800), new Water(5));

        Team team1 = new Team("Team 1", new Human("Лука"), new Cat("Борис"),
                new Dog("Барсик"), new Dog("Шарик"));

        Team team2 = new Team("Team 2", new Human("Степа"), new Cat("Тимка"),
                new Dog("Бука"), new Dog("Джек"));


        course1.doIt(team1);
        course1.doIt(team2);

        System.out.println("\n===== RESULTS =====\n");
        team1.showResults();
        System.out.println();
        team2.showResults();
    }
}

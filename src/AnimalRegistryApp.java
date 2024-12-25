import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AnimalRegistryApp {
    private List<Animal> animals;
    private Scanner scanner;

    private static final String FILE_PATH = "animals_data.txt";

    public AnimalRegistryApp() {
        animals = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadData();
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Реестр домашних животных ---");
            System.out.println("1. Добавить новое животное");
            System.out.println("2. Показать список команд животного");
            System.out.println("3. Обучить животное новой команде");
            System.out.println("4. Вывести список животных по дате рождения");
            System.out.println("5. Показать общее количество животных");
            System.out.println("6. Сохранить и выйти");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addAnimal();
                    break;
                case "2":
                    showCommands();
                    break;
                case "3":
                    teachCommand();
                    break;
                case "4":
                    listAnimalsByBirthDate();
                    break;
                case "5":
                    showAnimalCount();
                    break;
                case "6":
                    saveData();
                    System.out.println("Данные сохранены. До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void addAnimal() {
        System.out.println("Введите тип животного (Cat, Dog, Hamster, Horse, Camel, Donkey): ");
        String type = scanner.nextLine().trim();

        System.out.println("Введите имя животного: ");
        String name = scanner.nextLine().trim();

        System.out.println("Введите дату рождения животного (в формате yyyy-MM-dd): ");
        String birthDateInput = scanner.nextLine().trim();

        Date birthDate;
        try {
            birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateInput);
        } catch (ParseException e) {
            System.out.println("Неверный формат даты!");
            return;
        }

        Animal animal;
        switch (type.toLowerCase()) {
            case "cat" -> animal = new Cats(name, birthDate);
            case "dog" -> animal = new Dogs(name, birthDate);
            case "hamster" -> animal = new Hamsters(name, birthDate);
            case "horse" -> animal = new Horses(name, birthDate);
            case "camel" -> animal = new Camels(name, birthDate);
            case "donkey" -> animal = new Donkeys(name, birthDate);
            default -> {
                System.out.println("Неизвестный тип животного!");
                return;
            }
        };

        System.out.println("Доступные команды для " + type + ":");
        List<String> availableCommands = animal.getAvailableCommands();
        for (int i = 0; i < availableCommands.size(); i++) {
            System.out.println((i + 1) + ". " + availableCommands.get(i));
        }

        System.out.println("Введите номера команд через запятую, чтобы добавить их животному (например, 1,3): ");
        String commandInput = scanner.nextLine().trim();
        String[] commandIndexes = commandInput.split(",");

        for (String index : commandIndexes) {
            try {
                int commandIndex = Integer.parseInt(index.trim()) - 1;
                if (commandIndex >= 0 && commandIndex < availableCommands.size()) {
                    animal.getCommands().add(availableCommands.get(commandIndex));
                } else {
                    System.out.println("Неверный номер команды: " + index);
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода: " + index);
            }
        }

        animals.add(animal);
        System.out.println("Животное успешно добавлено! Текущий список команд: " + animal.getCommands());
    }

    private void showCommands() {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                System.out.println("Команды для " + animal.getName() + ": " + animal.getCommands());
                return;
            }
        }

        System.out.println("Животное с таким именем не найдено.");
    }

    private void teachCommand() {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                System.out.print("Введите новую команду: ");
                String newCommand = scanner.nextLine();
                animal.addCommand(newCommand);
                System.out.println("Животное обучено новой команде.");
                return;
            }
        }

        System.out.println("Животное с таким именем не найдено.");
    }

    private void listAnimalsByBirthDate() {
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .forEach(animal -> System.out.println(animal.getName() + " (" + animal.getType() + ") - " +
                        new SimpleDateFormat("yyyy-MM-dd").format(animal.getBirthDate())));
    }

    private void showAnimalCount() {
        System.out.println("Общее количество животных: " + Animal.getAnimalCount());
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(animals);
            oos.writeInt(Animal.getAnimalCount());
            System.out.println("Данные успешно сохранены.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            animals = (List<Animal>) ois.readObject();
            Animal.setAnimalCount(ois.readInt());
            System.out.println("Данные успешно загружены.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
        }
    }
}

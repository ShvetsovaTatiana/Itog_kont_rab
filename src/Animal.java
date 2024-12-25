import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Animal implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int animalCount = 0;

    private String name;
    private String type;
    private Date birthDate;
    private List<String> commands;

    public Animal(String name, String type, Date birthDate) {
        this.name = name;
        this.type = type;
        this.birthDate = birthDate;
        this.commands = new ArrayList<>();
        animalCount++;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public static void setAnimalCount(int count) {
        animalCount = count;
    }

    public abstract List<String> getAvailableCommands();
}

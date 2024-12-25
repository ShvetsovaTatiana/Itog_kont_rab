import java.util.Date;
import java.util.List;

public class Camels extends Animal {
    public Camels(String name, Date birthDate) {
        super(name, "Camel", birthDate);
    }

    @Override
    public List<String> getAvailableCommands() {
        return List.of("Walk", "CarryLoad", "Sit", "Run");
    }
}

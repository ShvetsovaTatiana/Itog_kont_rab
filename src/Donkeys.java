import java.util.Date;
import java.util.List;

public class Donkeys extends Animal {
    public Donkeys(String name, Date birthDate) {
        super(name, "Donkey", birthDate);
    }

    @Override
    public List<String> getAvailableCommands() {
        return List.of("Walk", "CarryLoad", "Bray", "Kick");
    }
}
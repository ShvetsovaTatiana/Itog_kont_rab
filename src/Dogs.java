import java.util.Date;
import java.util.List;

public class Dogs extends Animal {
    public Dogs(String name, Date birthDate) {
        super(name, "Dog", birthDate);
    }

    @Override
    public List<String> getAvailableCommands() {
        return List.of("Sit", "Stay", "Fetch", "Paw", "Bark", "Roll");
    }
}

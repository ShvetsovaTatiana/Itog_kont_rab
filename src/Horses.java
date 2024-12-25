import java.util.Date;
import java.util.List;

public class Horses extends Animal {
    public Horses(String name, Date birthDate) {
        super(name, "Horse", birthDate);
    }

    @Override
    public List<String> getAvailableCommands() {
        return List.of("Trot", "Canter", "Gallop", "Jump");
    }
}

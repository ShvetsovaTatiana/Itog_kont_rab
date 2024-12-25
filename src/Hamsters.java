import java.util.Date;
import java.util.List;

public class Hamsters extends Animal {
    public Hamsters(String name, Date birthDate) {
        super(name, "Hamster", birthDate);
    }

    @Override
    public List<String> getAvailableCommands() {
        return List.of("Roll", "Hide", "Spin");
    }
}

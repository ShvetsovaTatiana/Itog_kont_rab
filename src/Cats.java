import java.util.Date;
import java.util.List;

public class Cats extends Animal {
    public Cats(String name, Date birthDate) {
        super(name, "Cat", birthDate);
    }

    @Override
    public List<String> getAvailableCommands() {
        return List.of("Sit", "Pounce", "Scratch", "Meow", "Jump");
    }
}

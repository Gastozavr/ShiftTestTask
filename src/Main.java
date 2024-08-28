import Managers.FileManager;
import Utility.Filter;
import Utility.StandartConsole;

public class Main {
    public static void main(String[] args) {
        StandartConsole console = new StandartConsole();
        FileManager fileManager = new FileManager(console);
        Filter filter = new Filter(fileManager, console, args);
        filter.solve();
    }
}

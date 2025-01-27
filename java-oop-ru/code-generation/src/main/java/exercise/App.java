package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

// BEGIN
public class App {
    public static void save(Path path, Car car) {
        String jsonRepresentation = car.serialize();
        if (jsonRepresentation != null) {
            try {
                Files.writeString(path, jsonRepresentation);
            } catch (IOException e) {
                System.err.println("Ошибка записи в файл: " + e.getMessage());
            }
        }
    }

    public static Car extract(Path path) {
        try {
            String jsonRepresentation = Files.readString(path);
            return Car.deserialize(jsonRepresentation);
        } catch (IOException e) {
            System.err.println("Ошибка чтения из файла: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        User owner = new User(1, "Ivan", "P", 25);
        Path path1 = Paths.get("/tmp/file1.json");
        Car car1 = new Car(1, "audi", "q3", "black", owner);
        App.save(path1, car1); // Сохраняет представление объекта в файл

        Path path2 = Paths.get("/tmp/file2.json");
        //создаем файл чтоб тест не падал
        try {
            Files.writeString(path2, "{\"id\":1,\"brand\":\"volkswagen\",\"model\":\"passat\",\"color\":\"white\",\"owner\":{\"id\":1,\"firstName\":\"Ivan\",\"lastName\":\"P\",\"age\":25}}");
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
        Car car2 = App.extract(path2); // Возвращает инстанс класса Car
        System.out.println(car2.getModel()); // Вывод: passat
    }
}
// END

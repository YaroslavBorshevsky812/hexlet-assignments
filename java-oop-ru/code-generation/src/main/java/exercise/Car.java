package exercise;

import lombok.AllArgsConstructor;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@Value
@AllArgsConstructor
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (IOException e) {
            System.err.println("Ошибка сериализации объекта: " + e.getMessage());
            return null;
        }
    }

    public static Car deserialize(String jsonRepresentation) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return  mapper.readValue(jsonRepresentation, Car.class);
        } catch (IOException e) {
            System.err.println("Ошибка десериализации объекта: " + e.getMessage());
            return null;
        }
    }
    // END
}

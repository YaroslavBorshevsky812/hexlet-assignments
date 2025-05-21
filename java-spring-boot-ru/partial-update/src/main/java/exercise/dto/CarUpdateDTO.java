package exercise.dto;

import org.openapitools.jackson.nullable.JsonNullable;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Getter
@Setter
public class CarUpdateDTO {
    @NotNull
    private JsonNullable<String> model = JsonNullable.undefined();

    @NotNull
    private JsonNullable<String> manufacturer = JsonNullable.undefined();

    @NotNull
    private JsonNullable<Integer> enginePower = JsonNullable.undefined();
}
// END

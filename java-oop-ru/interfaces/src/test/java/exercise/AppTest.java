package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;


class AppTest {

    @Test
    void testBuildApartmentsList1() {
        List<Home> apartments = new ArrayList<>(List.of(
            new Flat(44, 3, 10),
            new Cottage(125.5, 2),
            new Flat(80, 10, 2),
            new Cottage(150, 3)
        ));

        List<String> expected = new ArrayList<>(List.of(
            "Квартира площадью 44.0 метров на 10 этаже",
            "Квартира площадью 80.0 метров на 2 этаже",
            "2 этажный коттедж площадью 125.5 метров"
        ));

        List<String> result = App.buildApartmentsList(apartments, 3);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testBuildApartmentsList2() {
        List<Home> apartments = new ArrayList<>(List.of(
            new Cottage(100, 1),
            new Flat(180, 10, 2),
            new Flat(190, 30, 5),
            new Cottage(250, 3)
        ));

        List<String> expected = new ArrayList<>(List.of(
            "1 этажный коттедж площадью 100.0 метров",
            "Квартира площадью 180.0 метров на 2 этаже",
            "Квартира площадью 190.0 метров на 5 этаже",
            "3 этажный коттедж площадью 250.0 метров"
        ));

        List<String> result = App.buildApartmentsList(apartments, 4);
        assertThat(result).isEqualTo(expected);

    }

    @Test
    void testBuildApartmentsList3() {
        List<Home> apartments = new ArrayList<>();
        List<String> expected = new ArrayList<>();
        List<String> result = App.buildApartmentsList(apartments, 10);
        assertThat(result).isEqualTo(expected);
    }

    // BEGIN
    @Test
    void testLength() {
        ReversedSequence reversed = new ReversedSequence("Hello");
        assertEquals(5, reversed.length());
        reversed = new ReversedSequence("");
        assertEquals(0, reversed.length());
        reversed = new ReversedSequence("A");
        assertEquals(1, reversed.length());
    }

    @Test
    void testCharAt() {
        ReversedSequence reversed = new ReversedSequence("Hello");
        assertEquals('o', reversed.charAt(0));
        assertEquals('l', reversed.charAt(1));
        assertEquals('l', reversed.charAt(2));
        assertEquals('e', reversed.charAt(3));
        assertEquals('H', reversed.charAt(4));

        reversed = new ReversedSequence("abc");
        assertEquals('c', reversed.charAt(0));
        assertEquals('b', reversed.charAt(1));
        assertEquals('a', reversed.charAt(2));
    }

    @Test
    void testSubSequence() {
        ReversedSequence reversed = new ReversedSequence("Hello");
        assertEquals("lle", reversed.subSequence(1, 4));
        assertEquals("ol", reversed.subSequence(0, 2));
        assertEquals("", reversed.subSequence(2, 2));
        assertEquals("o", reversed.subSequence(0, 1));
        assertEquals("H", reversed.subSequence(4, 5));
        reversed = new ReversedSequence("");
        assertEquals("", reversed.subSequence(0, 0));
    }

    // END
}

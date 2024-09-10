package types;

import com.ebanking.utils.processor.Combiner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    @Test
    void propertyWithValue() {
        var property = new Property<>("Indice", 1);
        assertEquals("Indice", property.getName());
        assertEquals(1, property.getValue());
    }

    @Test
    void propertyWithoutValue() {
        var property = new Property<Integer>("Indice");
        assertNull(property.getValue());
    }

    @Test
    void pipeline() {
        Combiner<String, String, String> combiner = Combiner.of("%s: %s"::formatted);

        var property = new Property<>("Nombre", "Zaiper");
        assertNotNull(property.getLeft(), "El nombre no debe ser nulo");
        assertNotNull(property.getRight(), "El valor no debe ser nulo");

        String formatted = combiner.apply(property);
        assertEquals("Nombre: Zaiper", formatted);

    }
    @Test
        void changeValue() {
        var property = new Property<>("Indice", 1);

        assertEquals(1, property.getValue(), "El valor inicial debería ser 1.");

        property.setValue(42);

        assertEquals(42, property.getValue(), "El valor debería haber cambiado a 42.");
    }

}
package gestionnaire2fraudes.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TupleTest {
    @Test
    public void testConstructeur() {
        Tuple tuple = new Tuple("element1", "element2");
        assertNotNull(tuple, "Le résultat doit être non null.");
        assertEquals("element1", tuple.getElement1(), "Le résultat doit être la chaîne 'element1'.");
        assertEquals("element2", tuple.getElement2(), "Le résultat doit être la chaîne 'element2'.");
    }
}

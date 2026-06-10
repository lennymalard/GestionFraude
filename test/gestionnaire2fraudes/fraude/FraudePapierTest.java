package gestionnaire2fraudes.fraude;

import gestionnaire2fraudes.utils.Tuple;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FraudePapierTest {

    @Test
    public void testConstructeur() {
        LocalDateTime dateTest = LocalDateTime.of(2026, 6, 13, 10, 45);
        Tuple dimensionsTest = new Tuple("x", "y");
        FraudePapier fraudePapier = new FraudePapier(dateTest, "Théorèmes", "Dans la trousse", dimensionsTest, true);

        assertNotNull(fraudePapier, "Le résultat doit être non null.");
        assertEquals(dateTest, fraudePapier.getDateReleve(), "Le résultat doit être la date du 13/06/2026 à 10:45.");
        assertEquals("Théorèmes", fraudePapier.getContenu(), "Le résultat doit être la chaîne de caractères 'Théorèmes'.");
        assertEquals("Dans la trousse", fraudePapier.getDescription(), "Le résultat doit être la chaîne de caractères 'Dans la trousse'.");
        assertEquals(dimensionsTest, fraudePapier.getDimensions(), "Le résultat doit être l'objet Tuple passé en paramètre.");
        assertTrue(fraudePapier.isPlie(), "Le résultat doit être vrai.");
    }
}
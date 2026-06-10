package gestionnaire2fraudes.fraude;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FraudeCalculatriceTest {

    @Test
    public void testConstructeur() {
        LocalDateTime dateTest = LocalDateTime.of(2026, 6, 10, 14, 30);
        FraudeCalculatrice fraudeCalculatrice = new FraudeCalculatrice(dateTest, "Formules mathématiques", "Caché dans le couvercle", "Casio", "Programme Python");

        assertNotNull(fraudeCalculatrice, "Le résultat doit être non null.");
        assertEquals(dateTest, fraudeCalculatrice.getDateReleve(), "Le résultat doit être la date du 10/06/2026 à 14:30.");
        assertEquals("Formules mathématiques", fraudeCalculatrice.getContenu(), "Le résultat doit être la chaîne de caractères 'Formules mathématiques'.");
        assertEquals("Caché dans le couvercle", fraudeCalculatrice.getDescription(), "Le résultat doit être la chaîne de caractères 'Caché dans le couvercle'.");
        assertEquals("Casio", fraudeCalculatrice.getMarque(), "Le résultat doit être la chaîne de caractères 'Casio'.");
        assertEquals("Programme Python", fraudeCalculatrice.getProgramme(), "Le résultat doit être la chaîne de caractères 'Programme Python'.");
    }
}
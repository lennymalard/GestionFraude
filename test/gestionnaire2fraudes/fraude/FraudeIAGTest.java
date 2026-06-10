package gestionnaire2fraudes.fraude;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FraudeIAGTest {

    @Test
    public void testConstructeur() {
        LocalDateTime dateTest = LocalDateTime.of(2026, 6, 11, 8, 0);
        FraudeIAG fraudeIAG = new FraudeIAG(dateTest, "Texte généré", "Utilisation du téléphone sous la table", "ChatGPT");

        assertNotNull(fraudeIAG, "Le résultat doit être non null.");
        assertEquals(dateTest, fraudeIAG.getDateReleve(), "Le résultat doit être la date du 11/06/2026 à 08:00.");
        assertEquals("Texte généré", fraudeIAG.getContenu(), "Le résultat doit être la chaîne de caractères 'Texte généré'.");
        assertEquals("Utilisation du téléphone sous la table", fraudeIAG.getDescription(), "Le résultat doit être la chaîne de caractères 'Utilisation du téléphone sous la table'.");
        assertEquals("ChatGPT", fraudeIAG.getNomService(), "Le résultat doit être la chaîne de caractères 'ChatGPT'.");
    }
}
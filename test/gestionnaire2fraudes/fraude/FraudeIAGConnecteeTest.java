package gestionnaire2fraudes.fraude;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FraudeIAGConnecteeTest {

    @Test
    public void testConstructeur() {
        LocalDateTime dateTest = LocalDateTime.of(2026, 6, 12, 9, 15);
        FraudeIAGConnectee fraudeIAGConn = new FraudeIAGConnectee(dateTest, "Code source complet", "Interrogation via montre connectée", "Claude", "192.168.1.50");

        assertNotNull(fraudeIAGConn, "Le résultat doit être non null.");
        assertEquals(dateTest, fraudeIAGConn.getDateReleve(), "Le résultat doit être la date du 12/06/2026 à 09:15.");
        assertEquals("Code source complet", fraudeIAGConn.getContenu(), "Le résultat doit être la chaîne de caractères 'Code source complet'.");
        assertEquals("Interrogation via montre connectée", fraudeIAGConn.getDescription(), "Le résultat doit être la chaîne de caractères 'Interrogation via montre connectée'.");
        assertEquals("Claude", fraudeIAGConn.getNomService(), "Le résultat doit être la chaîne de caractères 'Claude'.");
        assertEquals("192.168.1.50", fraudeIAGConn.getIpUtilisee(), "Le résultat doit être la chaîne de caractères '192.168.1.50'.");
    }
}
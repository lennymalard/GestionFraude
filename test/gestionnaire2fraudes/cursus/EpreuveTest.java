package gestionnaire2fraudes.cursus;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EpreuveTest {
    @Test
    public void testConstructeur(){
        Epreuve epreuve = new Epreuve("TEST", 1, 1, 2026, 8, 0, 60, Modalite.QCM);
        assertNotNull(epreuve);
        assertEquals("TEST", epreuve.getCodeUcue(), "Le résultat doit être la chaîne de caractères 'TEST'.");
        assertEquals(LocalDate.of(2026, 1, 1), epreuve.getDatePassage(), "Le résultat doit être la date 01/01/2026.");
        assertEquals(LocalTime.of(8, 0), epreuve.getHeurePassage(), "Le résultat doit être l'heure 8h59.");
        assertEquals(Duration.ofMinutes(60), epreuve.getDuree(), "Le résultat doit être 60min.");
        assertEquals(Modalite.QCM, epreuve.getModalite(), "Le résultat doit être la modalité Modalite.QCM.");
    }
}

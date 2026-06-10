package gestionnaire2fraudes.cursus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EtudiantTest {

    @Test
    public void testConstructeur() {
        Etudiant etudiant = new Etudiant("Malard", "Lenny", Cursus.E3e);

        assertNotNull(etudiant, "Le résultat doit être non null.");
        assertEquals("Malard", etudiant.getNom(), "Le résultat doit être la chaîne de caractères 'Malard'.");
        assertEquals("Lenny", etudiant.getPrenom(), "Le résultat doit être la chaîne de caractères 'Lenny'.");
        assertEquals(Cursus.E3e, etudiant.getCursus(), "Le résultat doit être Cursus.E3e.");
    }
}
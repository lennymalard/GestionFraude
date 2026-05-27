package gestionnaire2fraudes;

import gestionnaire2fraudes.cursus.Cursus;
import gestionnaire2fraudes.cursus.Etudiant;
import gestionnaire2fraudes.fraude.Calculatrice;
import gestionnaire2fraudes.fraude.Fraude;
import gestionnaire2fraudes.fraude.IAG;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestFormulaire {
    @Test
    public void constructeurFormulaireTest(){
        Formulaire form = new Formulaire();
        assertEquals(LocalDate.now(), form.getDateCreation(), "La date de création n'est pas bonne");
        assertEquals(LocalDate.now(), form.getDateModif(), "La date de modif n'est pas bonne");
        assertEquals(1, form.getId(), "L'id n'est pas le bon");
        Formulaire form2 = new Formulaire();
        assertEquals(2, form2.getId(), "L'id n'est pas le bon");
    }

    @Test
    public void ajoutFraudeursTest(){
        Formulaire form = new Formulaire();
        Etudiant etudiant = new Etudiant("Mainguet", "Marius", Cursus.E3e);
        Etudiant etudiant2 = new Etudiant("Malard", "Lenny", Cursus.E3e);
        Calculatrice fraudeCalc = new Calculatrice(LocalDateTime.now(), "Tricherie avec calculatrice", "C'est un tricheur", "Casio", "Scratch");
        IAG fraudeIag = new IAG(LocalDateTime.now(), "Tricherie avec IAG", "C'est un tricheur", "ChatGPT");
        form.ajoutFraudeurs(etudiant, fraudeCalc);
        assertTrue(form.getFraudeurs().containsKey(etudiant), "L'étudiant n'a pas été ajouté");

        form.ajoutFraudeurs(etudiant, fraudeIag);
        ArrayList<Fraude> fraudes = new ArrayList<Fraude>();
        fraudes.add(fraudeCalc);
        fraudes.add(fraudeIag);
        assertEquals(fraudes, form.getFraudeurs().get(etudiant), "Le liste de fraude n'est pas bonne");

        form.ajoutFraudeurs(etudiant2, fraudeCalc);
        assertEquals(2, form.getFraudeurs().size(), "La taille de la HashMap n'est pas bonne");
    }
}

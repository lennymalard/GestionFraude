package gestionnaire2fraudes;

import gestionnaire2fraudes.cursus.Cursus;
import gestionnaire2fraudes.cursus.Epreuve;
import gestionnaire2fraudes.cursus.Etudiant;
import gestionnaire2fraudes.cursus.Modalite;
import gestionnaire2fraudes.fraude.FraudeCalculatrice;
import gestionnaire2fraudes.fraude.Fraude;
import gestionnaire2fraudes.fraude.FraudeIAG;
import gestionnaire2fraudes.utils.Graphe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SystemeTest {

    private Systeme systeme;
    private Epreuve epreuve1;
    private Epreuve epreuve2;
    private Etudiant etudiant1;
    private Etudiant etudiant2;
    private Etudiant etudiant3;
    private Etudiant etudiant4;
    private Etudiant etudiant5;
    private Formulaire formulaire1;
    private Formulaire formulaire2;

    @BeforeEach
    public void setUp() {
        systeme = new Systeme();

        // Initialisation de 2 épreuves distinctes
        epreuve1 = new Epreuve("MATH301", 2, 6, 2026, 8, 30, 90, Modalite.EXAMEN_ECRIT);
        epreuve2 = new Epreuve("ALGO302", 3, 6, 2026, 14, 0, 120, Modalite.ORDINATEUR);

        // Création de 5 étudiants
        etudiant1 = new Etudiant("Malard", "Lenny", Cursus.E3e);
        etudiant2 = new Etudiant("Martin", "Alice", Cursus.E3a);
        etudiant3 = new Etudiant("Dubois", "Alice", Cursus.E4);
        etudiant4 = new Etudiant("Dubois", "Bob", Cursus.E2);
        etudiant5 = new Etudiant("Mainguet", "Marius", Cursus.E3e);

        // Création de 3 types de fraudes
        FraudeCalculatrice fraude1 = new FraudeCalculatrice(LocalDateTime.now(), "Formules", "Tricherie", "Casio", "Graph 35");
        FraudeCalculatrice fraude2 = new FraudeCalculatrice(LocalDateTime.now(), "Aide-mémoire", "Formules de Taylor", "TI", "Nspire");
        FraudeCalculatrice fraude3 = new FraudeCalculatrice(LocalDateTime.now(), "Antisèche", "Dans la trousse", "Casio", "Fx-92");
        FraudeIAG fraude4 = new FraudeIAG(LocalDateTime.now(), "Réponses", "Téléphone", "ChatGPT");

        // Configuration du Formulaire 1 (associé à epreuve1)
        // Ce formulaire contient 2 étudiants : etudiant1 et etudiant4
        formulaire1 = new Formulaire();
        HashMap<Etudiant, ArrayList<Fraude>> fraudeursMap1 = new HashMap<>();

        // etudiant1 a commis 1 fraude (fraude1)
        ArrayList<Fraude> fraudesList1 = new ArrayList<>();
        fraudesList1.add(fraude1);
        fraudeursMap1.put(etudiant1, fraudesList1);

        // etudiant4 a commis 1 fraude (fraude3)
        ArrayList<Fraude> fraudesList4 = new ArrayList<>();
        fraudesList4.add(fraude3);
        fraudeursMap1.put(etudiant4, fraudesList4);

        formulaire1.setFraudeurs(fraudeursMap1);

        // Configuration du Formulaire 2 (associé à epreuve2)
        // Ce formulaire contient 3 étudiants : etudiant2, etudiant3 et etudiant5
        formulaire2 = new Formulaire();
        HashMap<Etudiant, ArrayList<Fraude>> fraudeursMap2 = new HashMap<>();

        // etudiant2 a commis 2 fraudes (fraude1, fraude2)
        ArrayList<Fraude> fraudesList2 = new ArrayList<>();
        fraudesList2.add(fraude1);
        fraudesList2.add(fraude2);
        fraudeursMap2.put(etudiant2, fraudesList2);

        // etudiant3 a commis 1 fraude (fraude3)
        ArrayList<Fraude> fraudesList3 = new ArrayList<>();
        fraudesList3.add(fraude3);
        fraudeursMap2.put(etudiant3, fraudesList3);

        // etudiant5 a commis 1 fraude (fraude2)
        ArrayList<Fraude> fraudesList5 = new ArrayList<>();
        fraudesList5.add(fraude2);
        fraudeursMap2.put(etudiant5, fraudesList5);

        // etudiant1 a commis 1 fraude (fraude4)
        ArrayList<Fraude> fraudesList6 = new ArrayList<>();
        fraudesList6.add(fraude4);
        fraudeursMap1.put(etudiant1, fraudesList6);

        formulaire2.setFraudeurs(fraudeursMap2);

        // Ajout des formulaires au système global
        systeme.addFormulaire(epreuve1, formulaire1);
        systeme.addFormulaire(epreuve2, formulaire2);
    }

    @Test
    public void testFindFormulairesEtudiant() {
        // etudiant1 n'est présent que dans le formulaire 1
        ArrayList<Formulaire> result = systeme.findFormulairesEtudiant(etudiant1);
        assertEquals(1, result.size());
        assertTrue(result.contains(formulaire1));
    }

    @Test
    public void testFindFormulairesEpreuve() {
        // epreuve1 n'est associée qu'à formulaire1
        ArrayList<Formulaire> result = systeme.findFormulairesEpreuve(epreuve1);
        assertEquals(1, result.size());
        assertTrue(result.contains(formulaire1));
    }

    @Test
    public void testFindEtudiantId() {
        // Recherche par ID unique de l'étudiant 5
        Etudiant etudiantRechercheId = systeme.findEtudiant(etudiant5.getId());
        assertNotNull(etudiantRechercheId, "Le résultat doit être non null.");
        assertEquals(etudiant5, etudiantRechercheId, "Le résultat doit contenir etudiant5.");

        Etudiant resultatRechercheInconnuId = systeme.findEtudiant(100);
        assertNull(resultatRechercheInconnuId, "Le résultat doit être null.");

        // Recherche dans un formulaire sans étudiants
        Formulaire formulaireVide = new Formulaire();
        systeme.addFormulaire(new Epreuve("TEST", 1, 1, 2026, 8, 0, 60, Modalite.QCM), formulaireVide);
        assertNull(systeme.findEtudiant(100), "Le résultat doit être null.");

        Formulaire formulaireVideInit = new Formulaire();
        formulaireVideInit.setFraudeurs(new HashMap<>());
        systeme.addFormulaire(new Epreuve("TEST2", 1, 1, 2026, 9, 0, 60, Modalite.QCM), formulaireVideInit);
        assertNull(systeme.findEtudiant(100), "Le résultat doit être null.");

        Systeme systemeVide = new Systeme();
        assertNull(systemeVide.findEtudiant(100), "Le résultat doit être null.");

        // Recherche dans un systeme où le seul formulaire est vide
        Systeme systemeIsole = new Systeme();
        Formulaire formulaireIsole = new Formulaire();
        systemeIsole.addFormulaire(new Epreuve("TEST3", 1, 1, 2026, 8, 0, 60, Modalite.QCM), formulaireIsole);
        assertNull(systemeIsole.findEtudiant(etudiant1.getId()), "Le résultat doit être null.");
    }

    @Test
    public void testFindEtudiantNomPrenomCursus() {
        // Recherche par le nom, le prénom, et le cursus
        Etudiant etudiantRechercheNomPrenomCursus = systeme.findEtudiant("Malard", "Lenny", Cursus.E3e);
        assertNotNull(etudiantRechercheNomPrenomCursus, "Le résultat doit être non null.");
        assertEquals(etudiant1, etudiantRechercheNomPrenomCursus, "Le résultat doit contenir etudiant1.");

        Etudiant resultatRechercheInconnuNomPrenomCursus = systeme.findEtudiant("Manchec", "Auguste", Cursus.E3e);
        assertNull(resultatRechercheInconnuNomPrenomCursus, "Le résultat doit être null.");

        assertNull(systeme.findEtudiant("Malard", "Inconnu", Cursus.E3e), "Le prénom ne correspond pas.");
        assertNull(systeme.findEtudiant("Malard", "Lenny", Cursus.E1), "Le cursus ne correspond pas.");

        // Recherche dans un formulaire sans étudiants
        Formulaire formulaireVide = new Formulaire();
        systeme.addFormulaire(new Epreuve("TEST1", 1, 1, 2026, 8, 0, 60, Modalite.QCM), formulaireVide);
        assertNull(systeme.findEtudiant("Darde", "Romain", Cursus.E3e), "Le résultat doit être null.");

        Systeme systemeVide = new Systeme();
        assertNull(systemeVide.findEtudiant("Malard", "Lenny", Cursus.E3e), "Le résultat doit être null.");

        // Recherche dans un systeme où le seul formulaire est vide
        Systeme systemeIsole = new Systeme();
        Formulaire formulaireIsole = new Formulaire();
        systemeIsole.addFormulaire(new Epreuve("TEST3", 1, 1, 2026, 8, 0, 60, Modalite.QCM), formulaireIsole);
        assertNull(systemeIsole.findEtudiant("Malard", "Lenny", Cursus.E3e), "Le résultat doit être null.");
    }

    @Test
    public void testFindEtudiantCleValeur() {
        // 1 étudiant s'appelle "Malard" (etudiant1)
        ArrayList<Etudiant> resultatRechercheNomMalard = systeme.findEtudiant("nom", "Malard");
        assertEquals(1, resultatRechercheNomMalard.size(), "Il n'y a que un étudiant ayant le nom 'Malard'.");
        assertTrue(resultatRechercheNomMalard.contains(etudiant1), "Le résultat doit contenir etudiant1, ayant le nom 'Malard'.");

        // 2 étudiants s'appellent "Dubois" (etudiant3 et etudiant4)
        ArrayList<Etudiant> resultatRechercheNomDubois = systeme.findEtudiant("nom", "Dubois");
        assertEquals(2, resultatRechercheNomDubois.size(), "Il n'y a que deux étudiants ayant le nom 'Dubois'.");
        assertTrue(resultatRechercheNomDubois.contains(etudiant3), "Le résultat doit contenir etudiant3, ayant le nom 'Dubois'.");
        assertTrue(resultatRechercheNomDubois.contains(etudiant4),"Le résultat doit contenir etudiant4, ayant le nom 'Dubois'.");

        // 2 étudiants ont pour prénom "Alice" (etudiant2 et etudiant3)
        ArrayList<Etudiant> resultatRecherchePrenom = systeme.findEtudiant("prenom", "Alice");
        assertEquals(2, resultatRecherchePrenom.size(), "Il n'y a que deux étudiants ayant le prénom 'Alice'.");
        assertTrue(resultatRecherchePrenom.contains(etudiant2), "Le résultat doit contenir etudiant2, ayant le prénom 'Alice'.");
        assertTrue(resultatRecherchePrenom.contains(etudiant3), "Le résultat doit contenir etudaint3, ayant lel prénom 'Alice'.");

        // Recherche d'un étudiant inexistant ou de plusieurs étudiants inexistants
        ArrayList<Etudiant> resultatRechercheInconnuNom = systeme.findEtudiant("nom", "Inconnu");
        assertTrue(resultatRechercheInconnuNom.isEmpty(), "Le résultat doit être vide.");

        ArrayList<Etudiant> resultatRechercheInconnuPrenom = systeme.findEtudiant("prenom", "Inconnu");
        assertTrue(resultatRechercheInconnuPrenom.isEmpty(), "Le résultat doit être vide.");

        // Recherche avec une clé non supportée
        ArrayList<Etudiant> resultatRechercheCleInconnue= systeme.findEtudiant("clé", "Inconnu");
        assertTrue(resultatRechercheCleInconnue.isEmpty(), "Le résultat doit être vide.");

        // Recherche d'un étudiant présent dans plusieurs formulaires 
        formulaire1.getFraudeurs().put(etudiant2, new ArrayList<>());
        assertEquals(2, systeme.findEtudiant("prenom", "Alice").size(), "La taille du résultat doit être 2.");

        formulaire2.getFraudeurs().put(etudiant1, new ArrayList<>());
        assertEquals(1, systeme.findEtudiant("nom", "Malard").size(), "La taille du résultat doit être 1.");

        // Recherche dans un formulaire sans étudiants
        Formulaire formulaireVide = new Formulaire();
        formulaireVide.setFraudeurs(new HashMap<>());
        systeme.addFormulaire(new Epreuve("TEST1", 1, 1, 2026, 8, 0, 60, Modalite.QCM), formulaireVide);
        assertEquals(1, systeme.findEtudiant("nom", "Malard").size(), "La taille du résultat doit être 1.");

        Systeme systemeVide = new Systeme();
        assertTrue(systemeVide.findEtudiant("nom", "Malard").isEmpty(), "Le résultat doit être vide.");

        // Recherche dans un systeme où le seul formulaire est vide
        Systeme systemeIsole = new Systeme();
        Formulaire formulaireIsole = new Formulaire();
        systemeIsole.addFormulaire(new Epreuve("TEST3", 1, 1, 2026, 8, 0, 60, Modalite.QCM), formulaireIsole);
        assertTrue(systemeIsole.findEtudiant("nom", "Malard").isEmpty(), "Le résultat doit être vide.");
    }


    @Test
    public void testCalcNombreFormulaires() {
        // Le système contient formulaire1 et formulaire2
        assertEquals(2, systeme.calcNombreFormulaires(),"Il n'y a que 2 formulaires.");
    }

    @Test
    public void testCalcNombreEtudiants() {
        // Total de 5 étudiants distincts enregistrés dans les formulaires
        // (etudiant1, etudiant4 dans formulaire1) + (etudiant2, etudiant3, etudiant5 dans formulaire2)
        assertEquals(5, systeme.calcNombreEtudiants(), "Il n'y a que 5 étudiants.");

        // Cas où il y a déjà un étudiant dans la liste d'étudiants unique
        formulaire2.getFraudeurs().put(etudiant1, new ArrayList<>());
        assertEquals(5, systeme.calcNombreEtudiants(), "Il n'y a que 5 étudiants.");
    }

    @Test
    public void testCalcNombreFraudes() {
        // Total des fraudes :
        // Formulaire 1 : etudiant1(1) + etudiant4(1) = 2 fraudes
        // Formulaire 2 : etudiant2(2) + etudiant3(1) + etudiant5(1) = 4 fraudes
        // Total = 6 fraudes
        assertEquals(6, systeme.calcNombreFraudes(), "Le nombre total de fraudes est est 6.");
    }

    @Test
    public void testCalcMoyenneFraudesFormulaire() {
        // Formulaire 1 a 2 fraudes
        // Formulaire 2 a 4 fraudes
        // Moyenne = (2 + 4) / 2 = 3 fraudes par formulaire
        assertEquals(3.0f, systeme.calcMoyenneFraudesFormulaire(), 0.001, "La moyenne du nombre de fraudes par formulaire est 3.0 .");
    }

    @Test
    public void testCalcStdFraudesFormulaire() {
        // Moyenne = (2 + 4) / 2 = 3 fraudes par formulaire
        // Formulaire 1 a 2 fraudes : (2-3)^2 = 1
        // Formulaire 2 a 4 fraudes : (4-3)^2 = 1
        // Écart-type = (1 + 1) / 2 = 1
        assertEquals(1.0f, systeme.calcStdFraudesFormulaire(), 0.001, "L'écart-type du nombre de fraudes par formulaire est 1.0 .");
    }

    @Test
    public void testAddFraudeurGraphe() {
        systeme.addFraudeurGraphe(etudiant1);

        HashMap<Etudiant, Integer> map = systeme.getEtudiantIndiceMap();
        Graphe graphe = systeme.getGraphe();

        assertEquals(1, map.size(), "La taille du résultat doit être 1.");
        assertEquals(1, graphe.getNombreSommets(), "Le nombre de sommets doit être 1.");
        assertTrue(map.containsKey(etudiant1), "La hashmap doit contenir l'étudiant 1.");

        systeme.addFraudeurGraphe(etudiant1);

        assertEquals(1, map.size(), "La taille du résultat doit rester à 1 (après insertion doublon).");
        assertEquals(1, graphe.getNombreSommets(), "Le nombre de sommets doit rester à 1 (après insertion doublon).");
    }

    @Test
    public void testCreerLienFraudeurs() {
        systeme.addFraudeurGraphe(etudiant1);
        systeme.creerLienFraudeurs(etudiant1, etudiant2);

        systeme.setGraphe(new Graphe(0, false, new int[][]{}));
        systeme.setEtudiantIndiceMap(new HashMap<>());

        systeme.addFraudeurGraphe(etudiant2);
        systeme.creerLienFraudeurs(etudiant1, etudiant2);

        systeme.setGraphe(new Graphe(0, false, new int[][]{}));
        systeme.setEtudiantIndiceMap(new HashMap<>());

        systeme.addFraudeurGraphe(etudiant1);
        systeme.addFraudeurGraphe(etudiant2);
        systeme.creerLienFraudeurs(etudiant1, etudiant2);

        HashMap<Etudiant, Integer> map = systeme.getEtudiantIndiceMap();
        Graphe graphe = systeme.getGraphe();

        int index1 = map.get(etudiant1);
        int index2 = map.get(etudiant2);

        assertEquals(1, graphe.getGraphe()[index1][index2], "index1 vers index2 doit être à 1.");
        assertEquals(1, graphe.getGraphe()[index2][index1], "index2 vers index1 doit être à 1.");

    }

    @Test
    public void testLierFraudeursEpreuve() {
        systeme.addFraudeurGraphe(etudiant1);
        systeme.addFraudeurGraphe(etudiant2);
        systeme.addFraudeurGraphe(etudiant3);
        systeme.addFraudeurGraphe(etudiant4);
        systeme.addFraudeurGraphe(etudiant5);

        systeme.lierFraudeursEpreuve();

        HashMap<Etudiant, Integer> map = systeme.getEtudiantIndiceMap();
        Graphe graphe = systeme.getGraphe();

        int[][] matrice = graphe.getGraphe();

        int idEtudiant1 = map.get(etudiant1);
        int idEtudiant4 = map.get(etudiant4);
        assertEquals(1, matrice[idEtudiant1][idEtudiant4], "etudiant1 vers etudiant4 doit être à 1.");

        int idEtudiant2 = map.get(etudiant2);
        int idEtudiant3 = map.get(etudiant3);
        int idEtudiant5 = map.get(etudiant5);
        assertEquals(1, matrice[idEtudiant2][idEtudiant3], "etudiant2 vers etudiant3 doit être à 1.");
        assertEquals(1, matrice[idEtudiant3][idEtudiant5], "etudiant3 vers etudiant5 doit être à 1.");
        assertEquals(1, matrice[idEtudiant2][idEtudiant5], "etudiant2 vers etudiant5 doit être à 1.");

        assertEquals(0, matrice[idEtudiant1][idEtudiant2], "etudiant1 vers etudiant2 doit être à 0.");
        assertEquals(0, matrice[idEtudiant1][idEtudiant1], "etudiant1 vers etudiant1 doit être à 0.");
    }

}
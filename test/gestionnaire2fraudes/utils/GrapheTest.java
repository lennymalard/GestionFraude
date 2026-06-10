package gestionnaire2fraudes.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GrapheTest {

    private Graphe grapheOriente;
    private Graphe grapheNonOriente;

    @BeforeEach
    public void setUp() {
        int[][] matriceOriente = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        };
        grapheOriente = new Graphe(3, true, matriceOriente);

        int[][] matriceNonOriente = {
                {0, 1, 1},
                {1, 0, 0},
                {1, 0, 0}
        };
        grapheNonOriente = new Graphe(3, false, matriceNonOriente);
    }

    @Test
    public void testConstructeur() {
        assertNotNull(grapheOriente, "Le résultat doit être non null.");
        assertEquals(3, grapheOriente.getNombreSommets(), "Le résultat doit être égal à 3.");
        assertTrue(grapheOriente.getOriente(), "Le résultat doit être vrai.");
        assertEquals(1, grapheOriente.getGraphe()[0][1], "Le résultat doit être égal à 1.");

        assertEquals(3, grapheNonOriente.getNombreSommets(), "Le résultat doit être égal à 3.");
        assertFalse(grapheNonOriente.getOriente(), "Le résultat doit être faux.");
        assertEquals(1, grapheNonOriente.getGraphe()[1][0], "Le résultat doit être égal à 1.");
    }

    @Test
    public void testAjouterSommet() {
        grapheOriente.ajouterSommet();

        assertEquals(4, grapheOriente.getNombreSommets(), "Le résultat doit être égal à 4.");
        assertEquals(4, grapheOriente.getGraphe().length, "Le résultat doit être égal à 4.");
        assertEquals(4, grapheOriente.getGraphe()[0].length, "Le résultat doit être égal à 4.");

        assertEquals(1, grapheOriente.getGraphe()[0][1], "Le résultat doit être égal à 1.");
        assertEquals(0, grapheOriente.getGraphe()[3][3], "Le résultat doit être égal à 0.");
    }

    @Test
    public void testEnleverSommet() {
        grapheOriente.enleverSommet(1);

        assertEquals(2, grapheOriente.getNombreSommets(), "Le résultat doit être 2.");
        assertEquals(2, grapheOriente.getGraphe().length, "Le résultat doit être 2.");

        // Déplacement indices
        assertEquals(1, grapheOriente.getGraphe()[1][0], "Le résultat doit être 1.");
        assertEquals(0, grapheOriente.getGraphe()[0][1] , "Le résultat doit être 0.");
    }

    @Test
    public void testEnleverSommetHorsLimites() {
        grapheOriente.enleverSommet(5);
        assertEquals(3, grapheOriente.getNombreSommets(), "Le résultat doit être 3.");

        grapheOriente.enleverSommet(-1);
        assertEquals(3, grapheOriente.getNombreSommets(), "Le résultat doit être 3.");
    }

    @Test
    public void testAjouterArcOriente() {
        grapheOriente.ajouterArc(-100, 2);
        grapheOriente.ajouterArc(100, 2);
        grapheOriente.ajouterArc(0, 100);
        grapheOriente.ajouterArc(0, -100);
        grapheOriente.ajouterArc(0, 2);
        assertEquals(1, grapheOriente.getGraphe()[0][2], "Le résultat doit être 1.");
        assertEquals(1, grapheOriente.getGraphe()[2][0], "Le résultat doit être 1.");
    }

    @Test
    public void testAjouterArcNonOriente() {
        grapheNonOriente.ajouterArc(1, 2);
        assertEquals(1, grapheNonOriente.getGraphe()[1][2], "Le résultat doit être 1.");
        assertEquals(1, grapheNonOriente.getGraphe()[2][1], "Le résultat doit être 1.");
    }

    @Test
    public void testEnleverArcOriente() {
        grapheOriente.enleverArc(-100, 1);
        grapheOriente.enleverArc(100, 1);
        grapheOriente.enleverArc(0, 100);
        grapheOriente.enleverArc(0, -100);
        grapheOriente.enleverArc(0, 1);
        assertEquals(0, grapheOriente.getGraphe()[0][1], "Le résultat doit être 0.");
        assertEquals(1, grapheOriente.getGraphe()[1][2], "Le résultat doit être 1.");
    }

    @Test
    public void testEnleverArcNonOriente() {
        grapheNonOriente.enleverArc(0, 1);

        assertEquals(0, grapheNonOriente.getGraphe()[0][1], "Le résultat doit être 0.");
        assertEquals(0, grapheNonOriente.getGraphe()[1][0], "Le résultat doit être 0.");
    }

    @Test
    public void testCalculeNombreArcsEntrants() {
        // Le sommet 0 reçoit un arc du sommet 2
        assertEquals(1, grapheOriente.calculeNombreArcsEntrants(0), "Le résultat doit être 1.");
        // Le sommet 1 reçoit un arc du sommet 0
        assertEquals(1, grapheOriente.calculeNombreArcsEntrants(1), "Le résultat doit être 1.");
    }

    @Test
    public void testCalculeNombreArcsSortants() {
        // 0 -> 1
        assertEquals(1, grapheOriente.calculeNombreArcsSortants(0), "Le résultat doit être 1.");

        // Ajout pour vérifier l'incrémentation
        grapheOriente.ajouterArc(0, 2);
        assertEquals(2, grapheOriente.calculeNombreArcsSortants(0), "Le résultat doit être 2.");
    }

    @Test
    public void testParcoursProfondeur() {
        boolean[] marques = new boolean[grapheOriente.getNombreSommets()];
        grapheOriente.parcoursProfondeur(0, marques);
        assertTrue(marques[0], "Le résultat doit être vrai.");
        assertTrue(marques[1], "Le résultat doit être vrai.");
        assertTrue(marques[2], "Le résultat doit être vrai.");
    }

    @Test
    public void testCircuitExiste() {
        /* Manque le cas où marques[v] est vrai */

        boolean[] marquesOriente = new boolean[grapheOriente.getNombreSommets()];
        // 0 -> 1 -> 2 -> 0 forme un circuit
        assertTrue(grapheOriente.circuitExiste(0, marquesOriente, 0), "Le résultat doit être vrai.");

        // Graphe sans circuit
        int[][] matriceSansCircuit = {
                {0, 1, 0},
                {0, 0, 1},
                {0, 0, 0}
        };
        Graphe grapheSansCircuit = new Graphe(3, true, matriceSansCircuit);
        boolean[] marquesLineaire = new boolean[grapheSansCircuit.getNombreSommets()];

        assertFalse(grapheSansCircuit.circuitExiste(0, marquesLineaire, 0), "Le résultat doit être faux.");
    }
}
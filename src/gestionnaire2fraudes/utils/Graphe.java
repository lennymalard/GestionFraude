package gestionnaire2fraudes.utils;

/**
 * La classe Graphe represente un graphe.
 */

class Graphe {

    // attributs du graphe

    int nombreSommets;		// nombre de sommets du graphe
    boolean oriente;			// le graphe est-il oriente ou non ?
    int[][] graphe;			// la matrice carree qui va contenir le graphe
    // les dimensions de la matrice : nombreSommets x nombreSommets

    /**
     * Initialise le graphe.
     * 3 initialisations sont possibles :
     * 	- à partir du graphe qui represente une maison (oriente)
     * 	- à partir du graphe qui represente une maison (non oriente)
     * 	- à partir du graphe oriente presente en cours qui possède 8 sommets (a ete utilise notamment lors
     * 	  du parcours en profondeur)
     *
     * @param nom peut valoir au choix :
     *			- "Graphe maison (oriente)"

     * 		- "Graphe maison (non oriente)"
     * 		- "Graphe du cours"
     */
    Graphe(int nombreSommets, boolean oriente, int[][] graphe) {
        this.nombreSommets = nombreSommets;
        this.oriente = oriente;
        this.graphe = graphe;
    }

    /**
     *
     * Nous n'utiliserons que les accesseurs en lecture et nous passerons donc des accesseurs en écriture...
     */

    /**
     * Renvoie le nombre de sommets du graphe.
     *
     * @return le nombre de sommets du graphe
     */

    int getNombreSommets () {
        return this.nombreSommets;
    }

    /**
     * Indique si le graphe est orienté ou non.
     *
     * @return true si le graphe est orienté, false sinon.
     */

    boolean getOriente () {
        return this.oriente;
    }

    /**
     * Renvoie le graphe.
     *
     * @return le graphe.
     */

    int[][] getGraphe () {
        return this.graphe;
    }


    /**
     * Affiche dans la console les caracteristiques du graphe
     * puis le detail de la matrice qui contient le graphe.
     *
     */
    void affiche() {
        if (this.getOriente())
            System.out.println ("- Graphe oriente.");
        else
            System.out.println ("- Graphe non oriente.");
        System.out.println ("- Graphe ayant "+this.getNombreSommets()+ " sommet(s).");
        System.out.println ("- Matrice du graphe :");


        // affichage d'indices au dessus de la matrice afin d'y voir plus clair
        System.out.print(" ");
        for (int i=0;i<this.getNombreSommets(); i++)
            System.out.print(" "+i);
        System.out.println();

        // affichage d'un trait au dessus de la matrice pour que ça soit plus joli
        for (int i=0;i<=this.getNombreSommets(); i++)
            System.out.print("--");
        System.out.println();


        // affichage du contenu de la matrice, ligne par ligne
        for (int i=0;i<this.getNombreSommets(); i++) {
            System.out.print("| ");
            for (int j=0;j<this.getNombreSommets(); j++) {
                System.out.print(this.graphe[i][j]+" ");
            }
            System.out.println("|  "+ i);
        }


        // affichage d'un trait en dessous de la matrice pour que ça soit plus joli
        for (int i=0;i<=this.getNombreSommets(); i++)
            System.out.print("--");
        System.out.println();

    }


    /**
     * Pour un graphe oriente, calcule le nombre d'arcs qui arrivent sur un sommet donne
     * (rappel : le terme consacre est "demi degre interieur" d'un sommet)
     *
     * @param numeroSommet : le numero du sommet concerne
     * @return le nombre d'arcs entrants
     */
    int calculeNombreArcsEntrants (int numeroSommet) {
        int nombreArcs = 0;
        for (int i = 0; i<this.getNombreSommets(); i++) {
            nombreArcs += this.graphe[i][numeroSommet];
        }
        return nombreArcs;
    }

    /**
     * Pour un graphe oriente, affiche à l'écran les sommets sur lesquels aucun arc n'arrive
     * (autrement dit, de demi degré intérieur nul).
     * Rappel : en théorie des graphes, on appelle cela  les sources du graphe.
     *
     * Donc pour le problème qui nous concerne, affiche les numéros associés aux personnes
     * qui ne sont "aimées" de personne...
     *
     */
    void afficheSources () {
        for (int i=0;i<this.getNombreSommets(); i++) {
            int arcsEntrants = calculeNombreArcsEntrants(i);
            if (arcsEntrants > 0){
                System.out.println("Le sommet numéro " + i + " est une Source.");
            }
        }
    }


    /**
     * Pour un graphe oriente calcule le nombre d'arcs qui sortent d'un sommet donne
     * (le terme consacre est "demi degre exterieur" d'un sommet)
     *
     *
     * @param numeroSommet : le numero du sommet concerne
     * @return le nombre d'arcs sortants
     */

    int calculeNombreArcsSortants (int numeroSommet) {
        int nombreArcs = 0;
        for (int i = 0; i<this.getNombreSommets(); i++) {
            nombreArcs += this.graphe[numeroSommet][i];
        }
        return nombreArcs;
    }


    /**
     * Pour un graphe oriente, affiche à l'écran les sommets sur desquels aucun arc ne part
     * (autrement dit, de demi degré extérieur nul).
     * Rappel : en théorie des graphes, on appelle cela les puits du graphe.
     *
     * Donc pour le problème qui nous concerne, affiche les numéros associés aux personnes
     * qui "n'aiment" personne...
     *
     */

    void affichePuits () {
        for (int i=0;i<this.getNombreSommets(); i++) {
            int arcsEntrants = calculeNombreArcsSortants(i);
            if (arcsEntrants > 0) {
                System.out.println("Le sommet numéro " + i + " est un Puit.");
            }
        }
    }

    /**
     * Pour un graphe donne, effectue le parcours en profondeur
     *
     *
     * @param numeroSommet : le numero du sommet a partir duquel on appelle le parcours en profondeur
     * @param marques : le tableau de marques qui permet d'eviter de tourner en rond...
     */


    void parcoursProfondeur(int numeroSommet, boolean[] marques) {
        marques[numeroSommet] = true;
        System.out.print(numeroSommet + " ");

        for (int v = 0; v < this.nombreSommets; v++) {
            if (this.graphe[numeroSommet][v] == 1) {
                if (!marques[v]) {
                    parcoursProfondeur(v, marques);
                }
            }
        }
    }

    /**
     * Pour un graphe donne, va tester si le sommet numeroSommetArrivee est accessible a partir
     * du sommet numeroSommetDepart
     *
     *
     * @param numeroSommetDepart : le numero du sommet de depart
     * @param numeroSommetArrivee : le numero du sommet d'arrivee
     * @return true si le sommet est atteignable, false sinon
     */

    boolean sommetAtteignable(int numeroSommetDepart, int numeroSommetArrivee) {
        // à completer/modifier

        return false;
    }

    /**
     * Vérifie si le graphe contient un circuit.
     *
     *
     * @return true si un circuit existe dans le graphe, false sinon.
     */
    boolean circuitExiste(int numeroSommet, boolean[] marques, int sommetDepart) {
        marques[numeroSommet] = true;
        System.out.print(numeroSommet + " ");

        for (int v = 0; v < this.nombreSommets; v++) {
            if (this.graphe[numeroSommet][v] == 1) {
                if (!marques[v]) {
                    return circuitExiste(v, marques, sommetDepart);
                } else return marques[v] && v == sommetDepart;
            }
        }
        return false;
    }

}



package gestionnaire2fraudes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gestionnaire2fraudes.cursus.Cursus;
import gestionnaire2fraudes.cursus.Epreuve;
import gestionnaire2fraudes.cursus.Etudiant;
import gestionnaire2fraudes.fraude.Fraude;
import gestionnaire2fraudes.utils.Graphe;

/**
 * Classe centrale gérant les épreuves, les formulaires de fraude et la modélisation sous forme de graphe.
 */
public class Systeme {
    private HashMap<Epreuve, Formulaire> formulaires;
    private ArrayList<Epreuve> epreuves;
    private Graphe graphe;
    private HashMap<Etudiant, Integer> etudiantIndiceMap;

    /**
     * Constructeur par défaut initialisant les structures de données principales.
     */
    public Systeme(){
        this.formulaires = new HashMap<>();
        this.epreuves = new ArrayList<>();
        this.graphe = new Graphe(0, false, new int[][]{});
        this.etudiantIndiceMap = new HashMap<>();
    }

    /**
     * Retourne l'ensemble des formulaires associés à leurs épreuves respectives.
     *
     * @return La HashMap associant Epreuve et Formulaire.
     */
    public HashMap<Epreuve, Formulaire> getFormulaires() {
        return formulaires;
    }

    /**
     * Modifie l'ensemble des formulaires du système.
     *
     * @param formulaires La nouvelle HashMap.
     */
    public void setFormulaires(HashMap<Epreuve, Formulaire> formulaires) {
        this.formulaires = formulaires;
    }

    /**
     * Retourne la liste des épreuves existantes.
     *
     * @return La liste d'épreuves.
     */
    public ArrayList<Epreuve> getEpreuves() {
        return epreuves;
    }

    /**
     * Modifie la liste complète des épreuves.
     *
     * @param epreuves La nouvelle liste.
     */
    public void setEpreuves(ArrayList<Epreuve> epreuves) {
        this.epreuves = epreuves;
    }

    /**
     * Retourne le graphe modélisant les liens entre fraudeurs.
     *
     * @return L'instance du Graphe.
     */
    public Graphe getGraphe() {
        return graphe;
    }

    /**
     * Remplace le graphe actuel du système.
     *
     * @param graphe Le nouveau graphe.
     */
    public void setGraphe(Graphe graphe) {
        this.graphe = graphe;
    }

    /**
     * Retourne la HashMap entre un étudiant et son indice dans le graphe.
     *
     * @return La HashMap Etudiant/Integer.
     */
    public HashMap<Etudiant, Integer> getEtudiantIndiceMap() {
        return etudiantIndiceMap;
    }

    /**
     * Modifie la HashMap des indices des étudiants dans le Graphe.
     *
     * @param etudiantIndiceMap La nouvelle HashMap.
     */
    public void setEtudiantIndiceMap(HashMap<Etudiant, Integer> etudiantIndiceMap) {
        this.etudiantIndiceMap = etudiantIndiceMap;
    }

    /**
     * Ajoute ou met à jour un formulaire pour une épreuve spécifique.
     *
     * @param epreuve L'épreuve visée.
     * @param formulaire Le formulaire à ajouter.
     */
    public void addFormulaire(Epreuve epreuve, Formulaire formulaire){
        formulaires.put(epreuve, formulaire);
    }

    /**
     * Supprime le formulaire associé à une épreuve donnée.
     *
     * @param epreuve L'épreuve à enlever.
     */
    public void removeFormulaire(Epreuve epreuve){
        formulaires.remove(epreuve);
    }

    /**
     * Ajoute une nouvelle épreuve au système.
     *
     * @param epreuve L'épreuve à ajouter.
     */
    public void addEpreuve(Epreuve epreuve){
        epreuves.add(epreuve);
    }

    /**
     * Supprime une épreuve du système.
     *
     * @param epreuve L'épreuve à retirer.
     */
    public void removeEpreuve(Epreuve epreuve){
        epreuves.remove(epreuve);
    }

    /**
     * Recherche tous les formulaires dans lesquels un étudiant particulier est présent.
     *
     * @param etudiant L'étudiant à rechercher.
     * @return La liste des formulaires le concernant.
     */
    public ArrayList<Formulaire> findFormulairesEtudiant(Etudiant etudiant) {
        ArrayList<Formulaire> formulairesConcernes = new ArrayList<>();
        for (Formulaire formulaire : this.formulaires.values()) {
            if (formulaire.getFraudeurs().containsKey(etudiant)) {
                formulairesConcernes.add(formulaire);
            }
        }
        return formulairesConcernes;
    }

    /**
     * Recherche tous les formulaires rattachés à une épreuve donné.
     *
     * @param epreuve L'épreuve à rechercher.
     * @return La liste des formulaires trouvés.
     */
    public ArrayList<Formulaire> findFormulairesEpreuve(Epreuve epreuve) {
        /*Fonctionnalité temporaire*/
        ArrayList<Formulaire> formulairesConcernes = new ArrayList<>();
        for (Map.Entry<Epreuve, Formulaire> entry : this.formulaires.entrySet()) {
            if (entry.getKey().equals(epreuve)) {
                formulairesConcernes.add(entry.getValue());
            }
        }
        return formulairesConcernes;
    }

    /**
     * Recherche d'étudiants basée sur un système clé/valeur (ex: "nom", "Dupont").
     *
     * @param key Le critère de recherche.
     * @param value La valeur ciblée.
     * @return La liste des étudiants correspondants.
     */
    public ArrayList<Etudiant> findEtudiant(String key, String value) {
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                Etudiant etudiant = fraudeursEntry.getKey();
                switch (key) {
                    case "nom":
                        if (etudiant.getNom().equalsIgnoreCase(value) && !etudiants.contains(etudiant)) {
                            etudiants.add(etudiant);
                        }
                        break;
                    case "prenom":
                        if (etudiant.getPrenom().equalsIgnoreCase(value) && !etudiants.contains(etudiant)) {
                            etudiants.add(etudiant);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return etudiants;
    }

    /**
     * Recherche un étudiant via son identifiant unique.
     *
     * @param id L'identifiant de l'étudiant.
     * @return L'étudiant correspondant ou null.
     */
    public Etudiant findEtudiant(int id){
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                Etudiant etudiant = fraudeursEntry.getKey();
                if (etudiant.getId() == id) {
                    return etudiant;
                }
            }
        }
        return null;
    }

    /**
     * Recherche un étudiant via son Nom, Prénom et Cursus.
     *
     * @param nom Le nom de famille.
     * @param prenom Le prénom.
     * @param cursus Le cursus de l'étudiant.
     * @return L'étudiant correspondant ou null.
     */
    public Etudiant findEtudiant(String nom, String prenom, Cursus cursus){
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                Etudiant etudiant = fraudeursEntry.getKey();
                if (etudiant.getNom().equalsIgnoreCase(nom) &&
                        etudiant.getPrenom().equalsIgnoreCase(prenom) &&
                        etudiant.getCursus() == cursus) {
                    return etudiant;
                }
            }

        }
        return null;
    }

    /**
     * Calcule le nombre de formulaires présents dans le système.
     *
     * @return Le nombre de formulaires.
     */
    public int calcNombreFormulaires(){
        return formulaires.size();
    }

    /**
     * Calcule le nombre d'étudiants uniques recensés pour des fraudes.
     *
     * @return Le nombre d'étudiants distincts.
     */
    public int calcNombreEtudiants(){
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                Etudiant etudiant = fraudeursEntry.getKey();
                if  (!etudiants.contains(etudiant)){
                    etudiants.add(etudiant);
                }
            }
        }
        return  etudiants.size();
    }

    /**
     * Calcule la somme globale de toutes les fraudes enregistrées.
     *
     * @return Le nombre de fraudes totales.
     */
    public int calcNombreFraudes(){
        ArrayList<Fraude> fraudes = new ArrayList<>();
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                ArrayList<Fraude> fraudesEtudiant = fraudeursEntry.getValue();
                fraudes.addAll(fraudesEtudiant);
            }
        }
        return  fraudes.size();
    }

    /**
     * Calcule la moyenne des fraudes par formulaire.
     *
     * @return La moyenne.
     */
    public double calcMoyenneFraudesFormulaire() {
        int numFraudes = 0;
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            ArrayList<Fraude> fraudesFormulaire = new ArrayList<>();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                ArrayList<Fraude> fraudesEtudiant = fraudeursEntry.getValue();
                fraudesFormulaire.addAll(fraudesEtudiant);
            }
            numFraudes += fraudesFormulaire.size();
        }
        return (double) numFraudes / formulaires.size();
    }

    /**
     * Calcule l'écart-type du nombre de fraudes par rapport à la moyenne des formulaires.
     *
     * @return L'écart-type.
     */
    public double calcStdFraudesFormulaire(){
        double moyenne = this.calcMoyenneFraudesFormulaire();
        double sommeEcarts = 0;
        for (Map.Entry<Epreuve, Formulaire> formulairesEntry : this.formulaires.entrySet()) {
            Formulaire formulaire = formulairesEntry.getValue();
            ArrayList<Fraude> fraudesFormulaire = new ArrayList<>();
            for (Map.Entry<Etudiant, ArrayList<Fraude>> fraudeursEntry : formulaire.getFraudeurs().entrySet()) {
                ArrayList<Fraude> fraudesEtudiant = fraudeursEntry.getValue();
                fraudesFormulaire.addAll(fraudesEtudiant);
            }
            sommeEcarts += Math.pow(fraudesFormulaire.size() - moyenne, 2);
        }
        return Math.sqrt( sommeEcarts / formulaires.size());

    }

    /**
     * Ajoute un étudiant en tant que nouveau sommet dans le graphe si celui-ci n'y est pas déjà.
     *
     * @param etudiant L'étudiant à insérer.
     */
    public void addFraudeurGraphe(Etudiant etudiant){
        if (!etudiantIndiceMap.containsKey(etudiant)){
            this.graphe.ajouterSommet();
            etudiantIndiceMap.put(etudiant, this.graphe.getNombreSommets()-1);
        }
    }

    /**
     * Crée une arête dans le graphe entre deux étudiants fraudeurs.
     *
     * @param etudiant1 Le premier étudiant.
     * @param etudiant2 Le second étudiant.
     */
    public void creerLienFraudeurs(Etudiant etudiant1, Etudiant etudiant2){
        if (!etudiantIndiceMap.containsKey(etudiant1) || !etudiantIndiceMap.containsKey(etudiant2)){
            return;
        }
        int indice1 = etudiantIndiceMap.get(etudiant1);
        int indice2 = etudiantIndiceMap.get(etudiant2);
        this.graphe.ajouterArc(indice1, indice2);
    }

    /**
     * Parcourt tous les formulaires et lie dans le graphe les étudiants ayant fraudé lors de la même épreuve.
     */
    public void lierFraudeursEpreuve(){
        for (Formulaire formulaire : this.formulaires.values()){
            for (Etudiant etudiant1 : formulaire.getFraudeurs().keySet()){
                for (Etudiant etudiant2 : formulaire.getFraudeurs().keySet()) {
                    if (etudiant1.getId() < etudiant2.getId()) {
                        this.creerLienFraudeurs(etudiant1, etudiant2);
                    }
                }
            }
        }
    }

    /**
     * Affiche dans la console les correspondances des sommets puis dessine la matrice du graphe.
     */
    public void afficherGraphe(){
        System.out.println("=================== Graphe =================== ");
        for (Map.Entry<Etudiant, Integer> entry : this.etudiantIndiceMap.entrySet()) {
            Etudiant etudiant = entry.getKey();
            Integer indice = entry.getValue();
            System.out.println("Sommet " + indice + " : " + etudiant.getNom() + " " + etudiant.getPrenom() + " (ID: " + etudiant.getId() + ")");
        }
        System.out.println("==============================================\n");
        this.graphe.affiche();
        System.out.println("==============================================\n");
    }

    /**
     * Affiche dans la console les liens existant entre les différents étudiants fraudeurs.
     */
    public void afficherLiensEtudiants() {
        ArrayList<String> pairesAffichees = new ArrayList<>();

        for (Formulaire formulaire : this.formulaires.values()) {
            for (Etudiant etudiant1 : formulaire.getFraudeurs().keySet()) {
                for (Etudiant etudiant2 : formulaire.getFraudeurs().keySet()) {
                    if (etudiant1.getId() < etudiant2.getId()) {
                        String paire = etudiant1.getId() + "-" + etudiant2.getId();
                        if (!pairesAffichees.contains(paire)) {
                            pairesAffichees.add(paire);
                            System.out.println(etudiant1.getPrenom() + " " + etudiant1.getNom() + " <=> " + etudiant2.getPrenom() + " " + etudiant2.getNom());
                        }
                    }
                }
            }
        }
    }

}
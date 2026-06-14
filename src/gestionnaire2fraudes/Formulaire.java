package gestionnaire2fraudes;

import gestionnaire2fraudes.cursus.Etudiant;
import gestionnaire2fraudes.fraude.Fraude;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Représente un formulaire qui regroupe la liste des étudiants fraudeurs et les fraudes associées.
 */
public class Formulaire {
    static int numId = 0;

    private int id;
    private LocalDate dateCreation;
    private LocalDate dateModif;
    private HashMap<Etudiant, ArrayList<Fraude>> fraudeurs = new HashMap<>();

    /**
     * Constructeur général.
     *
     * @param id L'identifiant du formulaire.
     * @param dateCreation La date de création du formulaire.
     * @param dateModif La date de la dernière modification.
     */
    public Formulaire(int id, LocalDate dateCreation, LocalDate dateModif){
        this.id = id;
        this.dateCreation = dateCreation;
        this.dateModif = dateModif;
    }

    /**
     * Constructeur avec auto-incrémentation de l'ID.
     *
     * @param dateCreation La date de création.
     * @param dateModif La date de modification.
     */
    public Formulaire(LocalDate dateCreation, LocalDate dateModif) {
        this(++numId, dateCreation, dateModif);
    }

    /**
     * Constructeur par défaut assignant automatiquement l'ID et la date actuelle.
     */
    public Formulaire() {
        this(++numId, LocalDate.now(), LocalDate.now());
    }

    /**
     * Retourne la valeur actuelle du compteur d'identifiants.
     *
     * @return Le numéro d'ID.
     */
    public static int getNumId() {
        return numId;
    }

    /**
     * Modifie le compteur d'identifiants.
     *
     * @param numId Le nouveau numéro de départ.
     */
    public static void setNumId(int numId) {
        Formulaire.numId = numId;
    }

    /**
     * Retourne l'identifiant du formulaire.
     *
     * @return L'ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Modifie l'identifiant du formulaire.
     *
     * @param id Le nouvel identifiant.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne la date de création du formulaire.
     *
     * @return La date de création.
     */
    public LocalDate getDateCreation() {
        return dateCreation;
    }

    /**
     * Modifie la date de création.
     *
     * @param dateCreation La nouvelle date.
     */
    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Retourne la date de la dernière modification du formulaire.
     *
     * @return La date de modification.
     */
    public LocalDate getDateModif() {
        return dateModif;
    }

    /**
     * Modifie la date de la dernière modification.
     *
     * @param dateModif La nouvelle date.
     */
    public void setDateModif(LocalDate dateModif) {
        this.dateModif = dateModif;
    }

    /**
     * Retourne la structure de données liant les étudiants à leurs fraudes.
     *
     * @return La HashMap des fraudeurs.
     */
    public HashMap<Etudiant, ArrayList<Fraude>> getFraudeurs() {
        return fraudeurs;
    }

    /**
     * Modifie la structure contenant les étudiants et leurs fraudes.
     *
     * @param fraudeurs La nouvelle HashMap de fraudeurs.
     */
    public void setFraudeurs(HashMap<Etudiant, ArrayList<Fraude>> fraudeurs) {
        this.fraudeurs = fraudeurs;
    }

    /**
     * Ajoute un étudiant et sa fraude au formulaire.
     * Si l'étudiant y est déjà, la fraude est ajoutée à sa liste existante.
     *
     * @param etudiant L'étudiant impliqué.
     * @param fraude La fraude constatée.
     */
    public void ajoutFraudeurs(Etudiant etudiant, Fraude fraude){
        if (fraudeurs.containsKey(etudiant)){
            fraudeurs.get(etudiant).add(fraude);
        }else{
            ArrayList<Fraude> listeFraudes = new ArrayList<>();
            listeFraudes.add(fraude);
            fraudeurs.put(etudiant, listeFraudes);
        }
    }

    /**
     * Retourne la représentation textuelle du formulaire.
     *
     * @return L'identifiant et les dates formatées.
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Formulaire - ");

        str.append("Id : ");
        str.append(this.getId());

        str.append(", Date création : ");
        str.append(this.getDateCreation().getDayOfMonth());
        str.append("/");
        str.append(this.getDateCreation().getMonthValue());
        str.append("/");
        str.append(this.getDateCreation().getYear());

        str.append(", Date modification : ");
        str.append(this.getDateModif().getDayOfMonth());
        str.append("/");
        str.append(this.getDateModif().getMonthValue());
        str.append("/");
        str.append(this.getDateModif().getYear());

        return str.toString();
    }
}
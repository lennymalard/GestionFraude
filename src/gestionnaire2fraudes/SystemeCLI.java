package gestionnaire2fraudes;

import gestionnaire2fraudes.cursus.Cursus;
import gestionnaire2fraudes.cursus.Epreuve;
import gestionnaire2fraudes.cursus.Etudiant;
import gestionnaire2fraudes.cursus.Modalite;
import gestionnaire2fraudes.fraude.*;
import gestionnaire2fraudes.utils.Tuple;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

/**
 * @brief Classe gérant l'interface en ligne de commande (CLI) du système de gestion des fraudes.
 * @details Hérite de Systeme et implémente l'interface CLI pour l'interaction utilisateur.
 */
public class SystemeCLI extends Systeme implements CLI{
    //Intervalle de bonne valeur
    static String[] INTERVALCURSUS = {"E1", "E2", "E3e", "E3a", "E4", "E5"};
    static String[] INTERVALOUINON = {"o","n"};
    static Scanner scanner = new Scanner(System.in);
    String input;
    HashMap<Epreuve, Formulaire> epreuveForm = this.getFormulaires();

    /**
     * @brief Point d'entrée principal de l'application en mode console.
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args){
        SystemeCLI sys = new SystemeCLI();
        sys.start();
    }

    /**
     * @brief Démarre le système et affiche le menu principal.
     */
    @Override
    public void start(){
        display("Démarrage du système. Veuillez patienter.");
        afficheChoixMenuPrincipal();
    }

    /**
     * @brief Arrête le système et affiche un message de fin.
     */
    @Override
    public void stop(){
        display("Au revoir et à bientôt");
    }

    /**
     * @brief Affiche un message sur la sortie standard de la console.
     * @param message Le texte à afficher.
     */
    @Override
    public void display(String message){
        System.out.println(message);
    }

    /**
     * @brief Vérifie si la saisie utilisateur correspond à une valeur autorisée.
     * @param input La chaîne de caractères saisie par l'utilisateur.
     * @param bonInterval Tableau contenant les chaînes de caractères valides attendues.
     * @return true si la saisie est valide, false sinon.
     */
    private boolean rightInput(String input, String[] bonInterval){
        for (String s : bonInterval) {
            if (Objects.equals(input, s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @brief Convertit la saisie textuelle de l'utilisateur en entier.
     * @param input La chaîne de caractères à convertir.
     * @return L'entier correspondant à la chaîne.
     * @throws NumberFormatException Si la chaîne n'est pas un entier valide.
     */
    private int rightInput(String input){
        return Integer.parseInt(input);
    }

    /**
     * @brief Gère la boucle interactive et l'affichage du menu principal de l'application.
     */
    @Override
    public void afficheChoixMenuPrincipal(){
        boolean quitter = false;
        while(!quitter){
            display("--------------------------------------");
            display("Bienvenue dans le menu principal");
            display("1 - Création d'un formulaire");
            display("2 - Création d'une épreuve");
            display("3 - Modification d'un formulaire");
            display("4 - Suppression d'un formulaire");
            display("5 - Recherche et analyse");
            display("6 - Quitter le programme");

            int choix = 0;
            boolean choixMenuPrincipal = false;

            while(!choixMenuPrincipal){
                display("Entrez votre choix : ");
                input = scanner.nextLine().trim();
                try{
                    choix = rightInput(input);
                    if (choix >= 1 && choix <= 6) {
                        choixMenuPrincipal = true;
                    } else {
                        display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et 6.");
                    }

                } catch (NumberFormatException e) {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre valide.");
                }
            }
            switch(choix){
                case 1:
                    display("Vous allez créer un formulaire");
                    afficherCreationFormulaire();
                    break;
                case 2:
                    display("Vous allez créer une épreuve");
                    afficherCreationEpreuve();
                    break;
                case 3:
                    display("Vous allez modifier un formulaire");
                    afficherModifierFormulaire();
                    break;
                case 4:
                    display("Vous allez supprimer un formulaire");
                    afficherSupprimerFormulaire();
                    break;
                case 5:
                    display("Vous allez rechercher ou analyser");
                    afficheMenuRecherche();
                    break;
                case 6:
                    display("Vous quittez le programme");
                    quitter = true;
                    stop();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @brief Gère la boucle interactive et l'affichage du sous-menu de recherche et statistiques.
     */
    private void afficheMenuRecherche(){
        boolean quitterRecherche = false;
        while(!quitterRecherche){
            display("Bienvenue dans le menu rechercher et analyser");
            display("1 - Rechercher tous les formulaires associés à un étudiant");
            display("2 - Rechercher tous les formulaires associés à une épreuve");
            display("3 - Rechercher un étudiant");
            display("4 - Nombre total de formulaire enregistré dans le système");
            display("5 - Nombre total d'étudiant enregistré dans le système");
            display("6 - Nombre total de fraudes enregistré dans le système");
            display("7 - Moyenne de fraude par formulaire");
            display("8 - Écart type de fraude par formulaire");
            display("9 - Afficher les liens entre les étudiants qui ont trichés");

            int choix = 0;
            boolean choixMenuRecherche = false;

            while(!choixMenuRecherche){
                display("Entrez votre choix : ");
                input = scanner.nextLine().trim();
                try{
                    choix = rightInput(input);
                    if (choix >= 1 && choix <= 9) {
                        choixMenuRecherche = true;
                    } else {
                        display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et 9.");
                    }

                } catch (NumberFormatException e) {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre valide.");
                }
            }
            ArrayList<Formulaire> forms;
            ArrayList<Etudiant> listeEtu;
            Etudiant etu;
            StringBuilder str;
            switch(choix){
                case 1:
                    display("Vous aller renseigner un étudiant");
                    etu = afficherTrouverEtudiant();
                    display(etu.toString());
                    display("Voici les formulaires associé à l'étudiant renseigné.");
                    forms = this.findFormulairesEtudiant(etu);
                    for(Formulaire form : forms){
                        display(form.toString());
                        HashMap<Etudiant, ArrayList<Fraude>> fraudeEtu = form.getFraudeurs();
                        for(Etudiant etudiant : fraudeEtu.keySet()){
                            display("   " + etudiant.toString());
                            for(Fraude fraude : fraudeEtu.get(etudiant)){
                                display("       " + fraude.toString());
                            }
                        }
                    }
                    quitterRecherche = true;
                    break;
                case 2:
                    display("Vous allez renseigner une épreuve");
                    Epreuve epreuve = afficherTrouverEpreuve();
                    display(epreuve.toString());
                    display("Voici les formulaires associé à l'épreuve renseigné.");
                    forms = this.findFormulairesEpreuve(epreuve);
                    for(Formulaire form : forms){
                        display(form.toString());
                    }
                    quitterRecherche = true;
                    break;
                case 3:
                    boolean choixRechercheEtudiant = false;
                    display("Par quel moyen voulez vous rechercher un étudiant ");
                    display("1 - Par nom");
                    display("2 - Par prénom");
                    display("3 - Par numéro apprenant");
                    while(!choixRechercheEtudiant){
                        display("Entrez votre choix : ");
                        input = scanner.nextLine().trim();
                        try{
                            choix = rightInput(input);
                            if (choix >= 1 && choix <= 3) {
                                choixRechercheEtudiant = true;
                            } else {
                                display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et 3.");
                            }

                        } catch (NumberFormatException e) {
                            display("Entrée non acceptée, veuillez réessayer avec un chiffre valide.");
                        }
                    }
                    switch (choix) {
                        case 1:
                            rechercherParCritereTexte("nom", "Quel est le nom de l'étudiant : ");
                            break;
                        case 2:
                            rechercherParCritereTexte("prenom", "Quel est le prénom de l'étudiant : ");
                            break;
                        case 3:
                            rechercherParNumeroApprenant();
                            break;
                        default:
                            break;
                    }
                    quitterRecherche = true;
                    break;
                case 4:
                    int nbrTotalForms = this.calcNombreFormulaires();
                    str = new StringBuilder("Le nombre total de formulaire enregistré dans le système est de ");
                    str.append(nbrTotalForms);
                    display(str.toString());
                    quitterRecherche = true;
                    break;
                case 5:
                    int nbrTotalEtu = this.calcNombreEtudiants();
                    str = new StringBuilder("Le nombre total d'étudiant enregistré dans le système est de ");
                    str.append(nbrTotalEtu);
                    display(str.toString());
                    quitterRecherche = true;
                    break;
                case 6:
                    int nbrTotalFraude = this.calcNombreFraudes();
                    str = new StringBuilder("Le nombre total de fraudes enregistré dans le système est de ");
                    str.append(nbrTotalFraude);
                    display(str.toString());
                    quitterRecherche = true;
                    break;
                case 7:
                    double moyFraudeForm = this.calcMoyenneFraudesFormulaire();
                    str = new StringBuilder("La moyenne de fraude par formulaire est de ");
                    str.append(moyFraudeForm);
                    display(str.toString());
                    quitterRecherche = true;
                    break;
                case 8:
                    double ecartTypeFraudeForm = this.calcStdFraudesFormulaire();
                    str = new StringBuilder("L'écart type du nombre de fraude par formulaire est de ");
                    str.append(ecartTypeFraudeForm);
                    display(str.toString());
                    quitterRecherche = true;
                    break;
                case 9:
                    this.afficherLiensEtudiants();
                    quitterRecherche = true;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * @brief Vérifie si une chaîne de caractères contient au moins un chiffre numérique.
     * @param str La chaîne de caractères à vérifier.
     * @return true si la chaîne contient un chiffre, false sinon ou si la chaîne est nulle.
     */
    private boolean containsNumber(String str) {
        if (str == null) return false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @brief Vérifie si une chaîne de caractères est uniquement composée de chiffres.
     * @param str La chaîne de caractères à vérifier.
     * @return true si la chaîne ne contient que des chiffres, false sinon ou si la chaîne est nulle.
     */
    private boolean onlyNumber(String str){
        if (str == null) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @brief Guide l'utilisateur dans l'interface interactive pour créer un nouveau formulaire de fraude.
     * @details Propose une liste des épreuves existantes, crée le formulaire et y associe un ou plusieurs fraudeurs.
     */
    private void afficherCreationFormulaire(){
        Formulaire form = null;
        ArrayList<Epreuve> epreuves = this.getEpreuves();
        for(int i = 0; i < epreuves.size(); i++){
            StringBuilder strEpreuve = new StringBuilder();
            strEpreuve.append(i);
            strEpreuve.append(" - ");
            strEpreuve.append(epreuves.get(i).toString());
            display(strEpreuve.toString());
        }
        boolean choixEpreuve = false;
        while(!choixEpreuve){
            int choix = 0;
            display("Entrez votre choix : ");
            input = scanner.nextLine().trim();
            try{
                choix = rightInput(input);
                if (choix >= 0 && choix < epreuves.size()) {
                    choixEpreuve = true;
                    epreuveForm = this.getFormulaires();
                    if(epreuveForm.containsKey(epreuves.get(choix))){
                        display("Cette épreuve à déjà un formulaire, veuillez réessayer.");
                        choixEpreuve = false;
                    }
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et " + String.valueOf(epreuves.size()) + ".");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }
        form = new Formulaire(LocalDate.now(), LocalDate.now());
        this.addFormulaire(epreuves.get(Integer.parseInt(input)), form);
        afficherAjouterFraudeur(form);
        display("Formulaire créer");
    }

    /**
     * @brief Guide l'utilisateur pour modifier un formulaire de fraude existant en lui rajoutant des fraudeurs.
     */
    private void afficherModifierFormulaire(){

        Formulaire formAModif = null;

        display("Voici la liste des formulaires que vous pouvez modifier.");
        int count = 0;
        epreuveForm = this.getFormulaires();
        for(Epreuve epreuve : epreuveForm.keySet()){
            StringBuilder str = new StringBuilder();
            str.append(count);
            str.append(" : ");
            str.append(epreuve.toString());
            display(str.toString());
            display("    " + epreuveForm.get(epreuve).toString());
            count++;
        }
        boolean choixFormulaire = false;
        while(!choixFormulaire){
            int choix = 0;
            display("Entrez votre choix : ");
            input = scanner.nextLine().trim();
            try{
                choix = rightInput(input);
                if (choix >= 0 && choix < count) {
                    choixFormulaire = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et " + String.valueOf(count-1) + ".");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }
        int index = 0;
        for (Epreuve epreuve : epreuveForm.keySet()) {
            if (index == count-1) {
                formAModif = epreuveForm.get(epreuve);
                break;
            }
            index++;
        }
        afficherAjouterFraudeur(formAModif);
        formAModif.setDateModif(LocalDate.now());
        display("Formulaire modifier");
    }

    /**
     * @brief Guide l'utilisateur pour supprimer un formulaire de fraude du système.
     */
    private void afficherSupprimerFormulaire(){
        display("Voici la liste des formulaires que vous pouvez supprimer.");
        int count = 0;
        epreuveForm = this.getFormulaires();
        for(Epreuve epreuve : epreuveForm.keySet()){
            display(String.valueOf(count) + " : ");
            display(epreuve.toString());
            display(epreuveForm.get(epreuve).toString());
            count++;
        }
        boolean choixFormulaireSupp = false;
        while(!choixFormulaireSupp){
            int choix = 0;
            display("Entrez votre choix : ");
            input = scanner.nextLine().trim();
            try{
                choix = rightInput(input);
                if (choix >= 0 && choix < count) {
                    choixFormulaireSupp = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et " + String.valueOf(count-1) + ".");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }
        int index = 0;
        for (Epreuve epreuve : epreuveForm.keySet()) {
            if (index == count-1) {
                this.removeFormulaire(epreuve);
                break;
            }
            index++;
        }
        display("Formulaire supprimer");
    }

    /**
     * @brief Gère la saisie interactive des informations d'un fraudeur et du type de fraude commise.
     * @details Permet d'enregistrer des cas de fraude de type : Calculatrice, IAG, IAG Connectée, ou Papier, et de les ajouter au formulaire fourni.
     * @param form Le formulaire auquel la fraude et l'étudiant doivent être rattachés.
     */
    public void afficherAjouterFraudeur(Formulaire form){
        String nomEtu = null;
        String prenomEtu = null;
        Cursus cursusEtu = null;
        boolean renseignerFraudeEtu = true;
        while(renseignerFraudeEtu){
            display("Vous allez renseigner une fraude");
            boolean choixNomEtu = false;
            while(!choixNomEtu){
                display("Quel est le nom de l'étudiant : ");
                input = scanner.nextLine();
                if(containsNumber(input)){
                    display("Entrée non accepté, veuillez réessayer sans chiffre.");
                }else{
                    nomEtu = input;
                    choixNomEtu = true;
                }
            }
            boolean choixPrenomEtu = false;
            while(!choixPrenomEtu){
                display("Quel est le prenom de l'étudiant : ");
                input = scanner.nextLine();
                if(containsNumber(input)){
                    display("Entrée non accepté, veuillez réessayer sans chiffres");
                }else{
                    prenomEtu = input;
                    choixPrenomEtu = true;
                }
            }
            boolean choixCursusEtu = false;
            while(!choixCursusEtu){
                display("Quel est le cursus de l'étudiant (E1, E2, E3e, E3a, E4, E5) : ");
                input = scanner.nextLine().trim();
                if(rightInput(input, INTERVALCURSUS)){
                    cursusEtu = Cursus.valueOf(input);
                    choixCursusEtu = true;
                }else{
                    display("Entrée non accepté, veuillez réessayer");
                }
            }
            Etudiant etu = this.findEtudiant(nomEtu, prenomEtu, cursusEtu);
            if(etu == null){
                etu = new Etudiant(nomEtu, prenomEtu, cursusEtu);
            }
            display("Quel type de fraude a-t-il commis ?");
            display("1 - Fraude avec calculatrice.");
            display("2 - Fraude avec IAG.");
            display("3 - Fraude avec IAG connecté.");
            display("4 - Fraude avec papier.");
            boolean quitterSelFraude = false;
            while(!quitterSelFraude){

                int choix = 0;
                boolean choixSelFraude = false;
                while(!choixSelFraude){
                    display("Entrez votre choix : ");
                    input = scanner.nextLine().trim();
                    try{
                        choix = rightInput(input);
                        if (choix >= 1 && choix <= 4) {
                            choixSelFraude = true;
                        } else {
                            display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et 4.");
                        }

                    } catch (NumberFormatException e) {
                        display("Entrée non acceptée, veuillez réessayer avec un chiffre valide.");
                    }
                }
                String contenu;
                String description;
                switch (input){
                    case "1":
                        display("Vous avez choisi fraude avec calculatrice");
                        display("Veuillez saisir le contenu de la fraude : ");
                        contenu = scanner.nextLine();
                        display("Veuillez saisir la description de la fraude : ");
                        description = scanner.nextLine();
                        display("Veuillez saisir la marque de la calculatrice : ");
                        String marque = scanner.nextLine();
                        display("Veuillez saisir le programme de triche utilisé : ");
                        String programme = scanner.nextLine();
                        FraudeCalculatrice fraudeCalc = new FraudeCalculatrice(LocalDateTime.now(), contenu, description, marque, programme);
                        form.ajoutFraudeurs(etu, fraudeCalc);
                        this.addFraudeurGraphe(etu);
                        quitterSelFraude = true;
                        break;
                    case "2":
                        display("Vous avez choisi fraude avec IAG");
                        display("Veuillez saisir le contenu de la fraude : ");
                        contenu = scanner.nextLine();
                        display("Veuillez saisir la description de la fraude : ");
                        description = scanner.nextLine();
                        display("Veuillez saisir le nom du service utilisé : ");
                        String nomService = scanner.nextLine();
                        FraudeIAG fraudeIag = new FraudeIAG(LocalDateTime.now(), contenu, description, nomService);
                        form.ajoutFraudeurs(etu, fraudeIag);
                        this.addFraudeurGraphe(etu);
                        quitterSelFraude = true;
                        break;
                    case "3":
                        display("Vous avez choisi fraude avec IAG connecté");
                        display("Veuillez saisir le contenu de la fraude : ");
                        contenu = scanner.nextLine();
                        display("Veuillez saisir la description de la fraude : ");
                        description = scanner.nextLine();
                        display("Veuillez saisir le nom du service utilisé : ");
                        String nomServiceConnecte = scanner.nextLine();
                        display("Veuillez saisir l'ip utilisé : ");
                        String ip = scanner.nextLine();
                        FraudeIAGConnectee fraudeIagConn = new FraudeIAGConnectee(LocalDateTime.now(), contenu, description, nomServiceConnecte, ip);
                        form.ajoutFraudeurs(etu, fraudeIagConn);
                        this.addFraudeurGraphe(etu);
                        quitterSelFraude = true;
                        break;
                    case "4":
                        display("Vous avez choisi fraude avec papier");
                        display("Veuillez saisir le contenu de la fraude : ");
                        contenu = scanner.nextLine();
                        display("Veuillez saisir la description de la fraude : ");
                        description = scanner.nextLine();
                        display("Veuillez saisir la longueur du papier : ");
                        String longueur = null;
                        boolean choixLongueurPapier = false;
                        while(!choixLongueurPapier){
                            longueur = scanner.nextLine().trim();
                            if(onlyNumber(longueur)){
                                display("Entrée non accepté, veuillez réessayer");
                            }else{
                                choixLongueurPapier = true;
                            }
                        }
                        display("Veuillez saisir la largeur du papier : ");
                        String largeur = null;
                        boolean choixLargeurPapier = false;
                        while(!choixLargeurPapier){
                            largeur = scanner.nextLine().trim();
                            if(onlyNumber(largeur)){
                                display("Entrée non accepté, veuillez réessayer");
                            }else{
                                choixLargeurPapier = true;
                            }
                        }
                        display("Est ce que le papier est plié ? (o,n) : ");
                        boolean papierPlie = false;
                        while(!papierPlie){
                            String plie = scanner.nextLine().trim();
                            if(rightInput(plie, INTERVALOUINON)){
                                papierPlie = plie.equals("o");
                            }else{
                                display("Entrée non accepté, veuillez réessayer");
                            }
                        }
                        FraudePapier fraudePapier = new FraudePapier(LocalDateTime.now(), contenu, description, new Tuple(longueur, largeur), papierPlie);
                        form.ajoutFraudeurs(etu, fraudePapier);
                        this.addFraudeurGraphe(etu);
                        quitterSelFraude = true;
                        break;
                    default:
                        break;
                }
                display("Avez vous une autre fraude à renseigner ? (o,n)");
                boolean choixAutreFraude = false;
                while (!choixAutreFraude){
                    String nouvelleFraude = scanner.nextLine().trim();
                    if(rightInput(nouvelleFraude, INTERVALOUINON)){
                        if(!nouvelleFraude.equals("o")){
                            renseignerFraudeEtu = false;
                            choixAutreFraude = true;
                        }else{
                            display("Entrée non accepté, veuillez réessayer");
                        }
                    }
                }
            }
        }
    }

    /**
     * @brief Guide l'utilisateur pas à pas pour créer et configurer une nouvelle épreuve.
     * @details Demande la saisie contrôlée du code UCUE, de la date, de l'heure, de la durée ainsi que de la modalité.
     */
    public void afficherCreationEpreuve(){
        display("Entrez un code UCUE : ");
        String codeUcue = scanner.nextLine().trim();

        display("Vous allez renseigner la date de passage");
        display("Veuillez renseigner le jour de passage : ");
        int jourPassage = 0;
        boolean choixJour = false;
        while(!choixJour){
            input = scanner.nextLine().trim();
            try{
                jourPassage = rightInput(input);
                if (jourPassage >= 1 && jourPassage <= 31) {
                    choixJour = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et 31");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }

        display("Veuillez renseigner le mois de passage : ");
        int moisPassage = 0;
        boolean choixMois = false;
        while(!choixMois){
            input = scanner.nextLine().trim();
            try{
                moisPassage = rightInput(input);
                if (moisPassage >= 1 && moisPassage <= 12) {
                    choixMois = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et 12");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }

        display("Veuillez renseigner l'année de passage : ");
        int anneePassage = 0;
        boolean choixAnnee = false;
        while(!choixAnnee){
            input = scanner.nextLine().trim();
            try{
                anneePassage = rightInput(input);
                if (anneePassage >= 1900 && anneePassage <= LocalDate.now().getYear()) {
                    choixAnnee = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1900 et " + String.valueOf(LocalDate.now().getYear()));
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }

        display("Veuillez renseigner l'heure de passage (en h) : ");
        int heurePassage = 0;
        boolean choixHeure = false;
        while(!choixHeure){
            input = scanner.nextLine().trim();
            try{
                heurePassage = rightInput(input);
                if (heurePassage >= 0 && heurePassage <= 23) {
                    choixHeure = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 0 et 23");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }

        display("Veuillez renseigner les minutes de passage : ");
        int minPassage = 0;
        boolean choixMin = false;
        while(!choixMin){
            input = scanner.nextLine().trim();
            try{
                minPassage = rightInput(input);
                if (minPassage >= 0 && minPassage <= 59) {
                    choixMin = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 0 et 59");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }

        display("Veuillez renseigner la durée de l'épreuve (en min) : ");
        int duree = 0;
        boolean choixDuree = false;
        while(!choixDuree){
            input = scanner.nextLine().trim();
            try{
                duree = rightInput(input);
                if (duree >= 0) {
                    choixDuree = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre supérieur à 0.");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }

        display("Veuillez choisir la modalité de l'épreuve parmi les choix proposé : ");
        Modalite[] lesModalites = Modalite.values();
        for(int i = 0; i < lesModalites.length; i++){
            display(String.valueOf(i) + " - " + String.valueOf(lesModalites[i]));
        }
        int modaliteInt = 0;
        boolean choixModalite = false;
        while(!choixModalite){
            input = scanner.nextLine().trim();
            try{
                modaliteInt = rightInput(input);
                if (modaliteInt >= 0 && modaliteInt < lesModalites.length) {
                    choixModalite = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre supérieur à 0.");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
        }
        Modalite modalite = lesModalites[modaliteInt];
        Epreuve e = new Epreuve(codeUcue, jourPassage, moisPassage, anneePassage, heurePassage, minPassage, duree, modalite);
        this.addEpreuve(e);
        display("L'épreuve a bien été créé");
    }

    /**
     * @brief Permet de saisir l'identité et le cursus d'un étudiant pour le localiser ou le générer.
     * @return L'objet Etudiant configuré d'après les saisies utilisateur.
     */
    public Etudiant afficherTrouverEtudiant(){
        Etudiant etu = null;
        String nomEtu = null;
        String prenomEtu = null;
        Cursus cursusEtu = null;
        boolean renseignerEtu = false;
        while(!renseignerEtu){
            boolean choixNomEtu = false;
            while(!choixNomEtu){
                display("Quel est le nom de l'étudiant : ");
                input = scanner.nextLine();
                if(containsNumber(input)){
                    display("Entrée non accepté, veuillez réessayer sans chiffre.");
                }else{
                    nomEtu = input;
                    choixNomEtu = true;
                }
            }
            boolean choixPrenomEtu = false;
            while(!choixPrenomEtu){
                display("Quel est le prenom de l'étudiant : ");
                input = scanner.nextLine();
                if(containsNumber(input)){
                    display("Entrée non accepté, veuillez réessayer sans chiffres");
                }else{
                    prenomEtu = input;
                    choixPrenomEtu = true;
                }
            }
            boolean choixCursusEtu = false;
            while(!choixCursusEtu){
                display("Quel est le cursus de l'étudiant (E1, E2, E3e, E3a, E4, E5) : ");
                input = scanner.nextLine().trim();
                if(rightInput(input, INTERVALCURSUS)){
                    cursusEtu = Cursus.valueOf(input);
                    choixCursusEtu = true;
                }else{
                    display("Entrée non accepté, veuillez réessayer");
                }
            }
            etu = this.findEtudiant(nomEtu, prenomEtu, cursusEtu);
            if(etu != null){
                renseignerEtu = true;
            }else{
                display("Cette étudiant n'existe pas veuillez réessayer");
            }
        }
        return etu;
    }

    /**
     * @brief Guide l'utilisateur dans la sélection d'une épreuve existante possédant un formulaire.
     * @details Affiche la liste des épreuves et force une sélection numérique valide d'une épreuve présente dans les formulaires enregistrés.
     * @return L'objet Epreuve sélectionné par l'utilisateur.
     */
    public Epreuve afficherTrouverEpreuve(){
        ArrayList<Epreuve> epreuves = this.getEpreuves();
        for(int i = 0; i < epreuves.size(); i++){
            StringBuilder strEpreuve = new StringBuilder(i);
            strEpreuve.append(" - ");
            strEpreuve.append(epreuves.get(i).toString());
            display(strEpreuve.toString());
        }
        boolean choixEpreuve = false;
        while(!choixEpreuve){
            int choix = 0;
            display("Entrez votre choix : ");
            input = scanner.nextLine().trim();
            try{
                choix = rightInput(input);
                if (choix >= 0 && choix <= epreuves.size()) {
                    choixEpreuve = true;
                } else {
                    display("Entrée non acceptée, veuillez réessayer avec un chiffre entre 1 et " + String.valueOf(epreuves.size()) + ".");
                }
            }catch(NumberFormatException e){
                display("Entrée non acceptée, veuillez réessayer avec un chiffre");
            }
            epreuveForm = this.getFormulaires();
            if(!epreuveForm.containsKey(epreuves.get(choix))){
                display("Cette épreuve n'a pas de formulaire associé, veuillez réessayer.");
                choixEpreuve = false;
            }
        }
        return epreuves.get(Integer.parseInt(input));
    }

    /**
     * @brief Gère la recherche textuelle (Nom ou Prénom) d'un étudiant.
     * @details Effectue une saisie contrôlée pour s'assurer de l'absence de chiffres, lance la recherche selon le critère spécifié et affiche les correspondances.
     * @param critere Le type de critère textuel utilisé pour la recherche (ex: "nom" ou "prenom").
     * @param messagePrompt Le message d'invite de commande personnalisé à afficher à l'utilisateur.
     */
    private void rechercherParCritereTexte(String critere, String messagePrompt) {
        String saisie = null;
        boolean choixValide = false;

        while (!choixValide) {
            display(messagePrompt);
            saisie = scanner.nextLine().trim();

            if (containsNumber(saisie) || saisie.isEmpty()) {
                display("Entrée non acceptée, veuillez réessayer sans chiffre.");
            } else {
                choixValide = true;
            }
        }

        ArrayList<Etudiant> listeEtu = this.findEtudiant(critere, saisie);

        if (listeEtu != null && !listeEtu.isEmpty()) {
            for (Etudiant etudiant : listeEtu) {
                display(etudiant.toString());
            }
        } else {
            display("Il n'existe pas d'élève avec le " + critere + " " + saisie);
        }
    }

    /**
     * @brief Gère la recherche d'un étudiant via son numéro unique d'apprenant.
     * @details Contrôle que la saisie est purement numérique, exécute la recherche par identifiant unique et affiche l'étudiant s'il est trouvé.
     */
    private void rechercherParNumeroApprenant() {
        int numeroEtu = -1;
        boolean choixNumeroEtu = false;

        while (!choixNumeroEtu) {
            display("Quel est le numéro apprenant de l'étudiant : ");
            String input = scanner.nextLine().trim();
            try {
                numeroEtu = Integer.parseInt(input);
                choixNumeroEtu = true;
            } catch (NumberFormatException e) {
                display("Entrée non acceptée, veuillez réessayer avec un chiffre.");
            }
        }

        Etudiant etu = this.findEtudiant(numeroEtu);

        if (etu != null) {
            display(etu.toString());
        } else {
            display("Il n'existe pas d'élève avec le numéro apprenant " + numeroEtu);
        }
    }
}



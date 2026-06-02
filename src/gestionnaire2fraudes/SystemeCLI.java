package gestionnaire2fraudes;

import gestionnaire2fraudes.cursus.Cursus;
import gestionnaire2fraudes.cursus.Epreuve;
import gestionnaire2fraudes.cursus.Etudiant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class SystemeCLI implements CLI{
    //Intervalle de bonne valeur pour les différent menu
    static String[] INTERVALMENUPRINCIPAL = {"1", "2", "3", "4", "5", "6"};
    static String[] INTERVALMENURECHERCHE = {"1", "2", "3", "4", "5", "6", "7", "8"};
    static String[] INTERVALCURSUS = {"E1", "E2", "E3e", "E3a", "E4", "E5"};
    static Scanner scanner = new Scanner(System.in);
    String input;
    Systeme sys = new Systeme();


    @Override
    public void start(){
        display("Démarrage du système. Veuillez patienter.");

        isRunning();
    }

    @Override
    public void isRunning(){
        display("Bienvenue dans le menu principal");
        display("1 - Création d'un formulaire");
        display("2 - Modification d'un formulaire");
        display("3 - Recherche et analyse");
        display("4 - Suppression d'un formulaire");
        display("5 - Création d'une épreuve");
        display("6 - Quitter le programme");
        afficheMenuPrincipal();
        stop();
    }

    @Override
    public void stop(){
        display("Au revoir et à bientôt");
    }

    @Override
    public void display(String message){
        System.out.println(message);
    }

    private boolean rightInput(String input, String[] bonInterval){
        for (String s : bonInterval) {
            if (Objects.equals(input, s)) {
                return true;
            }
        }
        return false;
    }

    private void afficheMenuPrincipal(){
        boolean quitter = false;
        while(!quitter){
            display("Entrez votre choix : ");
            input = scanner.nextLine().trim();
            if(rightInput(input, INTERVALMENUPRINCIPAL)){
                switch(input){
                    case "1":
                        ArrayList<Epreuve> epreuves = sys.getEpreuves();
                        for(int i = 0; i < epreuves.size(); i++){
                            StringBuilder strEpreuve = new StringBuilder(i);
                            strEpreuve.append(" ");
                            strEpreuve.append(epreuves.get(i).toString());
                            display(strEpreuve.toString());
                        }
                        while(true){
                            display("Entrez votre choix : ");
                            input = scanner.nextLine().trim();
                            String[] bonInterval = new String[epreuves.size()];
                            for (int i = 0; i <= epreuves.size(); i++) bonInterval[i] = String.valueOf(i);
                            if(rightInput(input, bonInterval)){
                                Formulaire form = new Formulaire(LocalDate.now(), LocalDate.now());
                                sys.addFormulaire(epreuves.get(Integer.parseInt(input)), form);
                                break;
                            }else{
                                display("Entrée non accepté, veuillez réessayer");
                            }
                        }
                        display("Vous allez renseigner une fraude");
                        while(true){
                            display("Quel est le nom de l'étudiant : ");
                            input = scanner.nextLine();
                            if(containsNumber(input)){
                                display("Entrée non accepté, veuillez réessayer sans chiffre.");
                            }else{
                                String nomEtu = input;
                                break;
                            }
                        }
                        while(true){
                            display("Quel est le prenom de l'étudiant : ");
                            input = scanner.nextLine();
                            if(containsNumber(input)){
                                display("Entrée non accepté, veuillez réessayer sans chiffres");
                            }else{
                                String prenomEtu = input;
                                break;
                            }
                        }
                        while(true){
                            display("Quel est le cursus de l'étudiant (E1, E2, E3e, E3a, E4, E5) : ");
                            input = scanner.nextLine().trim();
                            if(rightInput(input, INTERVALCURSUS)){
                                Cursus cursusEtu = Cursus.valueOf(input);
                                break;
                            }else{
                                display("Entrée non accepté, veuillez réessayer");
                            }
                        }
                        Etudiant etu = sys.findEtudiant(nomEtu, prenomEtu, cursusEtu);
                        if(etu == null){
                            etu = new Etudiant(nomEtu, prenomEtu, cursusEtu);
                        }
                        //continuer avec les type de fraude à ajouté
                        display("Formulaire créer");
                        break;
                    case "2":
                        display("Vous allez modifier un formulaire");
                        break;
                    case "3":
                        display("Vous allez rechercher ou analyser");
                        afficheMenuRecherche();
                        break;
                    case "4":
                        display("Vous allez supprimer un formulaire");
                        break;
                    case "5":
                        display("Vous allez créer une épreuve");
                        break;
                    case "6":
                        display("Vous quittez le programme");
                        quitter = true;
                        break;
                    default:
                        break;
                }
            }else{
                StringBuilder messageTemp = new StringBuilder("Veuillez rentré une entrée valide parmis ces choix : ");
                for (String entree: INTERVALMENUPRINCIPAL){
                    messageTemp.append(entree);
                    messageTemp.append(" ");
                }
                display(messageTemp.toString());
            }
        }
    }

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
            display("Entrez votre choix : ");
            input = scanner.nextLine().trim();
            if(rightInput(input, INTERVALMENURECHERCHE)){
                switch(input){
                    case "1":

                        //sys.findFormulairesEtudiant()
                        break;
                    case "2":
                        display("Vous allez modifier un formulaire");
                        break;
                    case "3":
                        break;
                    case "4":
                        display("Vous allez supprimer un formulaire");
                        break;
                    case "5":
                        display("Vous allez créer une épreuve");
                        break;
                    case "6":
                        display("Vous quittez le programme");
                        break;
                    case "7":
                        display("Vous allez créer une épreuve");
                        break;
                    case "8":
                        display("Vous allez créer une épreuve");
                        break;
                    default:
                        break;
                }
            }
        }
    }



    private boolean containsNumber(String str) {
        if (str == null) return false;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}

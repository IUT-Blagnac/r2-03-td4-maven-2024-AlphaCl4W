package main;

import pile.Pile;

public class InverserTableau {

    /*
     * Programme permettant d'inverser le contenu d'un tableau d'entier en utilisant une Pile.
     * Inverser un tableau : le premier éléments devient le dernier, le deuxième devient l'avant dernier, ...
     * les opérandes sont fournis par donneesInitiales() (à modifier si besoin)
     */
    public static void main(String args[]) {

        int[] tableauDeValeurs;

        tableauDeValeurs = InverserTableau.donneesInitiales();

        System.out.println("Tableau inital : ");
        InverserTableau.afficherTableau(tableauDeValeurs);

        try {
            InverserTableau.inverserTableau(tableauDeValeurs);
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de l'inversion du tableau : " + e.getMessage());
        }

        System.out.println("Tableau inversé : ");
        InverserTableau.afficherTableau(tableauDeValeurs);
    }

    /*
     * Méthode qui utilise une Pile pour inverser le tableau
     * 
     * @param tableauDeValeurs tableau à inverser (E/S)
     */
    public static void inverserTableau(int[] pfTableauDeValeurs) throws Exception {
        Pile p;
        int pos;

        p = new Pile(pfTableauDeValeurs.length);
        for (int i = 0; i < pfTableauDeValeurs.length; i++) {
            p.empiler(pfTableauDeValeurs[i]);
        }
        pos = 0;
        while (!p.estVide()) {
            try {
                pfTableauDeValeurs[pos] = p.sommet();
                pos++;
                p.depiler();
            } catch (Exception e) {
                throw new Exception("Une erreur est survenue lors de l'inversion du tableau : " + e.getMessage());
            }
        }
    }

    /*
     * Convertit une chaine contenant une liste d'entiers séparés par des espace en
     * tableau.
     * Modifier la valeur de expr dans le code pour une autre exécution
     * 
     * @return le tableau contenant une liste d'entiers. 
     * Si une erreur de conversion de chaine en nombre a lieu au runtime : le programme est arrêté
     */
    public static int[] donneesInitiales() {
        int[] data; // liste finale des valeurs entières
        String expr; // expression initiale (liste de valeurs séparées par des espaces)
        String[] exprEclatee; // expr "éclatée" en sur séparateur espace

        expr = "a b";

        exprEclatee = expr.split(" ");
        data = new int[exprEclatee.length];
        for (int i = 0; i < exprEclatee.length; i++) {
            try {
                data[i] = Integer.parseInt(exprEclatee[i]);
            } catch (NumberFormatException nfe) { // échec de parseInt()
                System.out.println("Erreur de format");
                System.exit(1);
            }
        }
        return data;
    }

    /*
     * Affiche les valeurs d'un tableau d'entier
     * 
     * @param tableauDeValeurs tableau à afficher
     */
    public static void afficherTableau(int[] pfTableauDeValeurs) {
        String resultatAAfficher;
        resultatAAfficher = "";
        for (int i = 0; i < pfTableauDeValeurs.length; i++) {
            resultatAAfficher = resultatAAfficher + pfTableauDeValeurs[i] + ", ";
        }
        resultatAAfficher = resultatAAfficher.substring(0, resultatAAfficher.length() - 2);
        System.out.println(resultatAAfficher);
    }
}
package main;

import pile.Pile;

public class Calculette {

    /* Calcul d'une expression post-fixee.
     * Modifier la valeur de expr dans le code pour une autre execution
     */
    public static void main(String args[]) {

        Pile pileOperandes; // Pile des operandes durant le calcul
        int operande; // operande lue dans l'expression post fixee
        int operandeGauche; // operande gauche d'une operation;
        int operandeDroite; // operande droite d'une operation
        int res; // resultats intermediaires de calculs
        boolean exprOK; // Permet d'arreter le calcul si une erreur est rencontree.
        String[] exprPostFixee; // expression "eclatee" en operandes/operateurs
        String expr; // expression initiale (ex : "12 14 + 45 78 + *")

        expr = "12 45 + 45 78 + +";

        System.out.println("Expression initiale : " + expr);

        exprPostFixee = expr.split(" ");
        pileOperandes = new Pile(exprPostFixee.length);
        exprOK = true;
        try {
            for (int i = 0; i < exprPostFixee.length && exprOK; i++) {
                switch (exprPostFixee[i]) {
                    case "+":
                    case "-":
                    case "/":
                    case "*":
                        // recuperer operande droite
                        try {
                            operandeDroite = pileOperandes.sommet();
                            pileOperandes.depiler();
                        } catch (Exception e) {
                            throw new Exception("Erreur lors de la recuperation de l'operande droite : " + e.getMessage());
                        }
                        // recuperer operande gauche
                        try {
                            operandeGauche = pileOperandes.sommet();
                            pileOperandes.depiler();
                        } catch (Exception e) {
                            throw new Exception("Erreur lors de la recuperation de l'operande gauche : " + e.getMessage());
                        }

                        // faire le calcul
                        switch (exprPostFixee[i]) {
                            case "+":
                                res = operandeGauche + operandeDroite;
                                break;
                            case "-":
                                res = operandeGauche - operandeDroite;
                                break;
                            case "/":
                                res = operandeGauche / operandeDroite;
                                break;
                            case "*":
                                res = operandeGauche * operandeDroite;
                                break;
                            default:
                                throw new Exception("Operateur invalide : " + exprPostFixee[i]);
                        }
                        // empiler le resultat
                        try {
                            pileOperandes.empiler(res);
                        } catch (Exception e) {
                            throw new Exception("Erreur lors de l'empilement du resultat : " + e.getMessage());
                        }
                        break;
                    default:
                        try {
                            operande = Integer.parseInt(exprPostFixee[i]); // operande
                            pileOperandes.empiler(operande);
                        } catch (NumberFormatException nfe) {
                            exprOK = false;
                            System.out.println("Erreur durant le calcul : conversion String > int : " + nfe.getMessage());
                        }
                }
            }

            if (exprOK) {
                res = pileOperandes.sommet();
                pileOperandes.depiler();
                System.out.println("Aucune erreur detectee. Le resultat est " + res);
            } else {
                System.out.println("Erreur detectee durant le calcul");
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
        }
    }
}

package main;

import pile.Pile;

public class Calculette {

    /* Calcul d'une expression post-fixée.
     * Modifier la valeur de expr dans le code pour une autre exécution
     */
    public static void main(String args[]) {

        Pile pileOperandes; // Pile des opérandes durant le calcul
        int operande; // opérande lue dans l'expression post fixée
        int operandeGauche; // opérande gauche d'une opération;
        int operandeDroite; // opérande droite d'une opération
        int res; // résultats intermédiaires de calculs
        boolean exprOK; // Permet d'arrêter le calcul si une erreur est rencontrée.
        String[] exprPostFixee; // expression "éclatée" en opérandes/opérateurs
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
                        // récupérer opérande droite
                        try {
                            operandeDroite = pileOperandes.sommet();
                            pileOperandes.depiler();
                        } catch (Exception e) {
                            throw new Exception("Erreur lors de la récupération de l'opérande droite : " + e.getMessage());
                        }
                        // récupérer opérande gauche
                        try {
                            operandeGauche = pileOperandes.sommet();
                            pileOperandes.depiler();
                        } catch (Exception e) {
                            throw new Exception("Erreur lors de la récupération de l'opérande gauche : " + e.getMessage());
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
                                throw new Exception("Opérateur invalide : " + exprPostFixee[i]);
                        }
                        // empiler le résultat
                        try {
                            pileOperandes.empiler(res);
                        } catch (Exception e) {
                            throw new Exception("Erreur lors de l'empilement du résultat : " + e.getMessage());
                        }
                        break;
                    default:
                        try {
                            operande = Integer.parseInt(exprPostFixee[i]); // opérande
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
                System.out.println("Aucune erreur détectée. Le résultat est " + res);
            } else {
                System.out.println("Erreur détectée durant le calcul");
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
        }
    }
}

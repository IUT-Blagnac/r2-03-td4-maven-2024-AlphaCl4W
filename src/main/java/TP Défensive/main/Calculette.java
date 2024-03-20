package main;

import pile.Pile;

public class Calculette {

    /* Calcul d'une expression post-fix�e.
     * Modifier la valeur de expr dans le code pour une autre ex�cution
     */
    public static void main(String args[]) {

        Pile pileOperandes; // Pile des op�randes durant le calcul
        int operande; // op�rande lue dans l'expression post fix�e
        int operandeGauche; // op�rande gauche d'une op�ration;
        int operandeDroite; // op�rande droite d'une op�ration
        int res; // r�sultats interm�diaires de calculs
        boolean exprOK; // Permet d'arr�ter le calcul si une erreur est rencontr�e.
        String[] exprPostFixee; // expression "�clat�e" en op�randes/op�rateurs
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
                        // r�cup�rer op�rande droite
                        try {
                            operandeDroite = pileOperandes.sommet();
                            pileOperandes.depiler();
                        } catch (Exception e) {
                            throw new Exception("Erreur lors de la r�cup�ration de l'op�rande droite : " + e.getMessage());
                        }
                        // r�cup�rer op�rande gauche
                        try {
                            operandeGauche = pileOperandes.sommet();
                            pileOperandes.depiler();
                        } catch (Exception e) {
                            throw new Exception("Erreur lors de la r�cup�ration de l'op�rande gauche : " + e.getMessage());
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
                                throw new Exception("Op�rateur invalide : " + exprPostFixee[i]);
                        }
                        // empiler le r�sultat
                        try {
                            pileOperandes.empiler(res);
                        } catch (Exception e) {
                            throw new Exception("Erreur lors de l'empilement du r�sultat : " + e.getMessage());
                        }
                        break;
                    default:
                        try {
                            operande = Integer.parseInt(exprPostFixee[i]); // op�rande
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
                System.out.println("Aucune erreur d�tect�e. Le r�sultat est " + res);
            } else {
                System.out.println("Erreur d�tect�e durant le calcul");
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : " + e.getMessage());
        }
    }
}

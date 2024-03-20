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
		boolean exprOK; // Permet d'arr�tre le calcul si une erreur est rencontr�e.
		String[] exprPostFixee; // expression "�clatee" en op�randes/op�rateurs
		String expr; // expression initiale (ex : "12 14 + 45 78 + *")

		expr = "12 +";

		System.out.println("Expression intiale : "+expr);

		exprPostFixee = expr.split(" ");
		pileOperandes = new Pile(exprPostFixee.length);
		exprOK = true;

		for (int i = 0; i < exprPostFixee.length && exprOK; i++) {
			switch (exprPostFixee[i]) {
			case "+" :
			case "-" :
			case "/" :
			case "*" :
				// r�cup�rer op�rande droite
				if (pileOperandes.estVide()) {
					System.out.println("Erreur durant le calcul : manque d�op�rande !");
					System.exit(1);
				}
				operandeDroite = pileOperandes.sommet();
				pileOperandes.depiler();
	
				// r�cup�rer op�rande gauche
				if (pileOperandes.estVide()) {
					System.out.println("Erreur durant le calcul : manque d�op�rande !");
					System.exit(1);
				}
				operandeGauche = pileOperandes.sommet();
				pileOperandes.depiler();
				// faire le calcul
				res = 0;
				switch (exprPostFixee[i]) {
				case "+" : res = operandeGauche + operandeDroite; 
					break;
				case "-" : res = operandeGauche - operandeDroite; 
					break;
				case "/" : res = operandeGauche / operandeDroite; 
					break;
				case "*" : res = operandeGauche * operandeDroite; 
					break;
				}
							
				// empiler le r�sultat
				pileOperandes.empiler(res);

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
		if (!pileOperandes.estVide()) {
			System.out.println("Erreur d�tect�e durant le calcul : trop d�op�randes !");
		}
		if (exprOK) {
			res = pileOperandes.sommet();
			pileOperandes.depiler();
			System.out.println("Aucune erreur detectee. Le resultat est " + res);
		} else {
			System.out.println("Erreur detectee durant le calcul");
		}
	}
}

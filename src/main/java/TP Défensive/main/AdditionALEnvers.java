package main;

import pile.Pile;

public class AdditionALEnvers {

	/*
	 * Programme pour faire des additions " à l'envers" 
	 * Ex : si on lui donne 12 45 56, 
	 * il affichera : 
	 * 45 + 56 = 101
	 * 12 + 101 = 113
	 * Le résultat est : 113
	 * les opérandes sont fournis par donneesInitiales() (à modifier si besoin)
	 */
	public static void main(String args[]) {

		Pile pileOperandes; // Pile des opérandes durant le calcul
		int operande; // opérande lue dans l'expression
		int res; // résultats intermédiaires de calculs
		int resPrev; // résultat du calcul précédent
		int[] tabValeurs;
		
		tabValeurs = AdditionALEnvers.donneesInitiales();
		
		// Valeurs initiales
		System.out.println("Valeurs initiales");
		AdditionALEnvers.afficherTableau(tabValeurs);
		
		// Mettre les opérandes dans pileOperandes pour faire les additions "à l'envers"
		// Mettre les opérandes dans pileOperandes pour faire les additions "à l'envers"
		pileOperandes = new Pile(tabValeurs.length);
		for (int i = 0; i < tabValeurs.length; i++) {
		    try {
		        pileOperandes.empiler(tabValeurs[i]);
		    } catch (Exception e) {
		        System.out.println("Erreur lors de l'empilement : " + e.getMessage());
		    }
		}


		// Calculs à partir de pileOperandes
		try {
			res = pileOperandes.sommet();
			pileOperandes.depiler();
			resPrev = res;
			while (!pileOperandes.estVide()) {
				operande = pileOperandes.sommet();
				pileOperandes.depiler();
				res = operande + resPrev;
				System.out.println("" + operande + " + " + resPrev + " = " + res);
				resPrev = res;
			}

			System.out.println("Le résultat est : " + res);
		} catch (Exception e) {
			System.out.println("Erreur lors du calcul : " + e.getMessage());
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
		String[] exprEclatee; // expr "éclatée"en sur séparateur espace

		expr = "";

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
	 * @param pfTableauDeValeurs tableau à afficher
	 */
	public static void afficherTableau (int[] pfTableauDeValeurs) {
		String resultatAAfficher;
		resultatAAfficher = "";
		for (int i = 0; i < pfTableauDeValeurs.length; i++) {
			resultatAAfficher = resultatAAfficher + pfTableauDeValeurs[i] + ", ";
		}
		resultatAAfficher = resultatAAfficher.substring(0, resultatAAfficher.length() - 2);
		System.out.println(resultatAAfficher);
	}
}

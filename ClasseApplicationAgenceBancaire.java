package main;

import java.util.Locale;
import java.util.Scanner;

import tps.banque.Compte;
import tps.banque.AgenceBancaire;
import tps.banque.exception.ABCompteDejaExistantException;
import tps.banque.exception.ABCompteInexistantException;
import tps.banque.exception.ABCompteNullException;
import tps.banque.exception.CompteException;

public class ClasseApplicationAgenceBancaire {

	/**
	 * Affichage du menu de l'application
	 * 
	 * @param ag
	 *            AgenceBancaire pour récupérer le nom et la localisation
	 */
	public static void afficherMenu(AgenceBancaire pfAg) {
		System.out.println("Menu de " + pfAg.getNomAgence() + " (" + pfAg.getLocAgence() + ")");
		System.out.println("c - Créer un nouveau compte dans l'agence");
		System.out.println("s - Supprimer un compte de l'agence (par son numéro)");
		System.out.println("l - Liste des comptes de l'agence");
		System.out.println("v - Voir un compte (par son numéro)");
		System.out.println("p - voir les comptes d'un Propriétaire (par son nom)");
		System.out.println("d - Déposer de l'argent sur un compte");
		System.out.println("r - Retirer de l'argent sur un compte");
		System.out.println("q - Quitter");
		System.out.print("Choix -> ");
	}
	
	/**Permet d'obtenir tous les comptes d'un propriétaire
	 * 
	 * @param pfAg IN : AgenceBancaire entrée
	 * @param pfNomProprietaire IN : nom du propriétaire
	 */
	public static void comptesDUnProprietaire(AgenceBancaire pfAg, String pfNomProprietaire) {
		Compte[] tabCompte= pfAg.getComptesDe(pfNomProprietaire);
		for (int i = 0; i < tabCompte.length; i++) {
			tabCompte[i].afficher();
		}
	}
	
	/** Permet de déposer de l'argent sur un compte
	 * 
	 * @param pfAg IN : AgenceBancaire entrée
	 * @param pfNumeroCompte IN : numéro de compte sur lequel déposer
	 * @param pfMontant IN : montant à déposer
	 * @throws CompteException
	 */
	public static void deposerSurUnCompte (AgenceBancaire pfAg, String pfNumeroCompte, double pfMontant) throws CompteException {
		Compte co = pfAg.getCompte(pfNumeroCompte);
		if (co == null) {
			System.out.println("Le compte n'a pas été trouvé");
		}
		else {
			try {
				co.deposer(pfMontant);
			} catch (CompteException E) {
				System.out.println("Impossible de déposer cette somme");
			}
		}
	}
	
	/** Permet de retirer de l'argent sur un compte
	 * 
	 * @param pfAg IN : AgenceBancaire entrée
	 * @param pfNumeroCompte IN : numéro de compte sur lequel retirer
	 * @param pfMontant IN : montant à retirer
	 * @throws CompteException
	 */
	public static void retirerSurUnCompte (AgenceBancaire pfAg, String pfNumeroCompte, double pfMontant) throws CompteException {
		Compte co = pfAg.getCompte(pfNumeroCompte);
		if (co == null) {
			System.out.println("Le compte n'a pas été trouvé");
		}
		else {
			try {
				co.retirer(pfMontant);
			} catch (CompteException E) {
				System.out.println("Impossible de retirer cette somme");
			}
		}
	}

	/**
	 * Temporisation : Affiche un message et attend la frappe de n'importe quel
	 * caractère.
	 */
	public static void tempo() {
		Scanner lect;
		String s;

		lect = new Scanner(System.in);

		System.out.print("Tapez un car + return pour continuer ... ");
		s = lect.next(); // Inutile à stocker mais c'est l'usage normal ...
	}

	public static void main(String[] argv) throws ABCompteNullException, ABCompteDejaExistantException, CompteException {

		String choix;
		String nom, numero;
		boolean continuer;
		Scanner lect;
		AgenceBancaire monAg;

		lect = new Scanner(System.in);
		lect.useLocale(Locale.US);

		monAg = new AgenceBancaire("Caisse Ep", "Pibrac");

		continuer = true;
		while (continuer) {
			ClasseApplicationAgenceBancaire.afficherMenu(monAg);
			choix = lect.next();
			choix = choix.toLowerCase();
			switch (choix) {
			case "q":
				System.out.println("ByeBye");
				ClasseApplicationAgenceBancaire.tempo();
				continuer = false;
				break;

			case "c":
				System.out.print("Num compte -> ");
				numero = lect.next();
				System.out.print("Propriétaire -> ");
				nom = lect.next();
				Compte c = new Compte(numero, nom);
				try {
					monAg.addCompte(c);
					System.out.println("Le compte du client "+nom+" avec le numéro "+numero+" a bien été créé");
				} catch (ABCompteNullException E) {
					System.out.println("Ce compte ne possède aucune donnée");
				} catch (ABCompteDejaExistantException E) {
					System.out.println("Le compte que vous voulez créer existe déjà");
				}
				

				ClasseApplicationAgenceBancaire.tempo();
				break;

			case "s":
				System.out.print("Num compte -> ");
				numero = lect.next();
				try {
					monAg.removeCompte(numero);
					System.out.println("Suppression effectuée\n");
				} catch (ABCompteInexistantException e) {
					System.out.println("Numéro de compte inexistant");
					System.out.println(e.getMessage());
				}
				ClasseApplicationAgenceBancaire.tempo();
				break;

			// A CONTINUER ICI	
			case "l":
					monAg.afficher();
				ClasseApplicationAgenceBancaire.tempo();
				break;
				
			case "v":
				System.out.print("Num compte -> ");
				numero = lect.next();
				Compte co = monAg.getCompte(numero);
				if (co == null) {
					System.out.println("Le compte recherché n'existe pas ou n'a pas été trouvé");
				}
				else {
					System.out.println(co);
				}

				ClasseApplicationAgenceBancaire.tempo();
				break;
				
			case "p":
				System.out.print("Nom du propriétaire -> ");
				nom = lect.next();
				comptesDUnProprietaire(monAg, nom);

				ClasseApplicationAgenceBancaire.tempo();
				break;
				
			case "d":
				System.out.print("Num compte -> ");
				numero = lect.next();
				System.out.print("Saisissez le montant à ajouter -> ");
				double depot = lect.nextDouble();
				deposerSurUnCompte(monAg, numero ,depot);

				ClasseApplicationAgenceBancaire.tempo();
				break;
				
			case "r":
				System.out.print("Num compte -> ");
				numero = lect.next();
				System.out.print("Saisissez le montant à ajouter -> ");
				double retrait = lect.nextDouble();
				deposerSurUnCompte(monAg, numero ,retrait);

				ClasseApplicationAgenceBancaire.tempo();
				break;
				
			default:
				System.out.println("Erreur de saisie ...");
				ClasseApplicationAgenceBancaire.tempo();
				break;
			}
		}

	}

}
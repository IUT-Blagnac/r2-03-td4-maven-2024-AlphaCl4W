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
	 *            AgenceBancaire pour r�cup�rer le nom et la localisation
	 */
	public static void afficherMenu(AgenceBancaire pfAg) {
		System.out.println("Menu de " + pfAg.getNomAgence() + " (" + pfAg.getLocAgence() + ")");
		System.out.println("c - Cr�er un nouveau compte dans l'agence");
		System.out.println("s - Supprimer un compte de l'agence (par son num�ro)");
		System.out.println("l - Liste des comptes de l'agence");
		System.out.println("v - Voir un compte (par son num�ro)");
		System.out.println("p - voir les comptes d'un Propri�taire (par son nom)");
		System.out.println("d - D�poser de l'argent sur un compte");
		System.out.println("r - Retirer de l'argent sur un compte");
		System.out.println("q - Quitter");
		System.out.print("Choix -> ");
	}
	
	/**Permet d'obtenir tous les comptes d'un propri�taire
	 * 
	 * @param pfAg IN : AgenceBancaire entr�e
	 * @param pfNomProprietaire IN : nom du propri�taire
	 */
	public static void comptesDUnProprietaire(AgenceBancaire pfAg, String pfNomProprietaire) {
		Compte[] tabCompte= pfAg.getComptesDe(pfNomProprietaire);
		for (int i = 0; i < tabCompte.length; i++) {
			tabCompte[i].afficher();
		}
	}
	
	/** Permet de d�poser de l'argent sur un compte
	 * 
	 * @param pfAg IN : AgenceBancaire entr�e
	 * @param pfNumeroCompte IN : num�ro de compte sur lequel d�poser
	 * @param pfMontant IN : montant � d�poser
	 * @throws CompteException
	 */
	public static void deposerSurUnCompte (AgenceBancaire pfAg, String pfNumeroCompte, double pfMontant) throws CompteException {
		Compte co = pfAg.getCompte(pfNumeroCompte);
		if (co == null) {
			System.out.println("Le compte n'a pas �t� trouv�");
		}
		else {
			try {
				co.deposer(pfMontant);
			} catch (CompteException E) {
				System.out.println("Impossible de d�poser cette somme");
			}
		}
	}
	
	/** Permet de retirer de l'argent sur un compte
	 * 
	 * @param pfAg IN : AgenceBancaire entr�e
	 * @param pfNumeroCompte IN : num�ro de compte sur lequel retirer
	 * @param pfMontant IN : montant � retirer
	 * @throws CompteException
	 */
	public static void retirerSurUnCompte (AgenceBancaire pfAg, String pfNumeroCompte, double pfMontant) throws CompteException {
		Compte co = pfAg.getCompte(pfNumeroCompte);
		if (co == null) {
			System.out.println("Le compte n'a pas �t� trouv�");
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
	 * caract�re.
	 */
	public static void tempo() {
		Scanner lect;
		String s;

		lect = new Scanner(System.in);

		System.out.print("Tapez un car + return pour continuer ... ");
		s = lect.next(); // Inutile � stocker mais c'est l'usage normal ...
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
				System.out.print("Propri�taire -> ");
				nom = lect.next();
				Compte c = new Compte(numero, nom);
				try {
					monAg.addCompte(c);
					System.out.println("Le compte du client "+nom+" avec le num�ro "+numero+" a bien �t� cr��");
				} catch (ABCompteNullException E) {
					System.out.println("Ce compte ne poss�de aucune donn�e");
				} catch (ABCompteDejaExistantException E) {
					System.out.println("Le compte que vous voulez cr�er existe d�j�");
				}
				

				ClasseApplicationAgenceBancaire.tempo();
				break;

			case "s":
				System.out.print("Num compte -> ");
				numero = lect.next();
				try {
					monAg.removeCompte(numero);
					System.out.println("Suppression effectu�e\n");
				} catch (ABCompteInexistantException e) {
					System.out.println("Num�ro de compte inexistant");
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
					System.out.println("Le compte recherch� n'existe pas ou n'a pas �t� trouv�");
				}
				else {
					System.out.println(co);
				}

				ClasseApplicationAgenceBancaire.tempo();
				break;
				
			case "p":
				System.out.print("Nom du propri�taire -> ");
				nom = lect.next();
				comptesDUnProprietaire(monAg, nom);

				ClasseApplicationAgenceBancaire.tempo();
				break;
				
			case "d":
				System.out.print("Num compte -> ");
				numero = lect.next();
				System.out.print("Saisissez le montant � ajouter -> ");
				double depot = lect.nextDouble();
				deposerSurUnCompte(monAg, numero ,depot);

				ClasseApplicationAgenceBancaire.tempo();
				break;
				
			case "r":
				System.out.print("Num compte -> ");
				numero = lect.next();
				System.out.print("Saisissez le montant � ajouter -> ");
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
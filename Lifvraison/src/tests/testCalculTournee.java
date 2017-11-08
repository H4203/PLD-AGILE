package tests;
import org.junit.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import algorithme.CalculateurTournee;
import algorithme.Dijkstra;
import modeles.*;
import controleur.*;
import donnees.*;

public class testCalculTournee {
	
	/* variables globales */
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder builder;
	private static String cheminMoyenLyon = "./data/plan/planLyonMoyen.xml" ;
	private static String cheminFichierDLMoyen = "./data/demandeLivraison/DLmoyen10.xml" ;
	private static Document planLyonMoyen = null ;
	private static Document docDLMoyen = null ;
	private static XMLParseur parseur = null;
	private static Plan plan = null;
	private static DemandeLivraison dL = null;
	private static CalculateurTournee calculateurTournee = null;
	private static Tournee tournee = null;
	
	@BeforeEach
	public void Initialisation() throws ParseurException {
		
		try {
			builder = factory.newDocumentBuilder();
			docDLMoyen = builder.parse(new File(cheminFichierDLMoyen));
			planLyonMoyen = builder.parse(new File(cheminMoyenLyon));
			parseur = new XMLParseur();
			parseur.chargerPlan(plan, cheminMoyenLyon);
			parseur.chargerLivraison(dL, cheminFichierDLMoyen, plan.getListeIntersection());
			tournee = new Tournee(plan,dL);
		} 
		
		
		catch (final SAXException e) {
		    e.printStackTrace();
		}
		catch (final IOException e) {
			/* Le fichier choisit par l’utilisateur ne peut pas être lu */
		    e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testSupprimerLivraison() {
		
		
		
	}

}

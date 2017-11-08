package tests;
import org.junit.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.text.html.HTMLDocument.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import donnees.ParseurException;
import donnees.XMLParseur;
import modeles.DemandeLivraison;
import modeles.Plan;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testXMLParseur {
	
	/* -- DIFFERENCE SAX/DOM
	In SAX, events are triggered when the XML is being parsed. When the parser is parsing the XML, and encounters a tag starting (e.g. <something>), 
	then it triggers the tagStarted event (actual name of event might differ). Similarly when the end of the tag is met while parsing (</something>), it triggers tagEnded. 
	Using a SAX parser implies you need to handle these events and make sense of the data returned with each event.
	In DOM, there are no events triggered while parsing. The entire XML is parsed and a DOM tree (of the nodes in the XML) is generated and returned. 
	Once parsed, the user can navigate the tree to access the various data previously embedded in the various nodes in the XML.
	In general, DOM is easier to use but has an overhead of parsing the entire XML before you can start using it. */
	
	testXMLParseur() {
		
	}
	
	/* variables globales */
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder builder;
	private static String cheminFichierDLPetit = "./data/demandeLivraison/DLpetit5.xml" ;
	private static String cheminFichierDLMoyen = "./data/demandeLivraison/DLmoyen10.xml" ;
	private static String cheminFichierDLGrand = "./data/demandeLivraison/DLgrand10TW2.xml" ;
	private static String cheminPetitLyon = "./data/plan/planLyonPetit.xml" ;
	private static String cheminMoyenLyon = "./data/plan/planLyonMoyen.xml" ;
	private static String cheminGrandLyon = "./data/plan/planLyonGrand.xml" ;
	private static Document docDLPetit = null ;
	private static Document docDLMoyen = null ;
	private static Document docDLGrand = null ;
	private static Document planLyonPetit = null ;
	private static Document planLyonMoyen = null ;
	private static Document planLyonGrand = null ;
	private static XMLParseur parseur = null ;

	
	@BeforeEach
	public void Initialisation() throws ParseurException {
		
		try {
			builder = factory.newDocumentBuilder();
			docDLPetit = builder.parse(new File(cheminFichierDLPetit)); 
			docDLMoyen = builder.parse(new File(cheminFichierDLMoyen));
			docDLGrand = builder.parse(new File(cheminFichierDLGrand));
			planLyonPetit = builder.parse(new File(cheminPetitLyon));
			planLyonMoyen = builder.parse(new File(cheminMoyenLyon));
			planLyonGrand = builder.parse(new File(cheminGrandLyon));
			parseur = new XMLParseur();
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
	
	
	@Test
	public void testChargerLivraison() throws ParseurException {
		
		final Element racine = docDLGrand.getDocumentElement();
		assertEquals("demandeDeLivraisons", racine.getNodeName(), "document de livraison mal construit : pas de balise demandeDeLivraisons"); // vérifie que la balise racine correspond bien à une DL
		
		NodeList lieuxPlanLyonGrand = planLyonGrand.getElementsByTagName("noeud");
		NodeList entrepotLivraisonGrand = docDLGrand.getElementsByTagName("entrepot");
		NodeList lieuxLivraisonGrand = docDLGrand.getElementsByTagName("livraison");
		
		/* Vérifications ENTREPÔT */
		assertEquals( entrepotLivraisonGrand.getLength(), 1, "document de livraison mal construit : pas ou plusieurs entrepôts"); // vérifie qu'il y a un seul entrepôt
		boolean boolTest = false;
		for(int i=0; i<lieuxPlanLyonGrand.getLength(); i++) {
			
			if(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("adresse").getNodeValue().equals(lieuxPlanLyonGrand.item(i).getAttributes().getNamedItem("id").getNodeValue())) {
				boolTest = true;
				break;
			}
			
		}
		assertTrue("l'entrepot est introuvable sur le plan", boolTest); // vérifie que l'entrepôt dans la DL existe sur le plan
		/* Fin Vérifications ENTREPÔT */
		
		
		/* Vérifications LIEUX DE LIVRAISON */
		ArrayList<String> tousLesLieux = new ArrayList();
        
		
		for(int i=0; i<lieuxPlanLyonGrand.getLength(); i++) {
			tousLesLieux.add(lieuxPlanLyonGrand.item(i).getAttributes().getNamedItem("id").getNodeValue());
		}
		
		ArrayList<String> tousLesLieuxLivraisonGrand = new ArrayList();
		for(int i=0; i<lieuxLivraisonGrand.getLength(); i++) {
			tousLesLieux.add(lieuxLivraisonGrand.item(i).toString());
		}
		
		Set set = new HashSet() ;
        set.addAll(tousLesLieuxLivraisonGrand) ;
        ArrayList distinctTousLesLieuxLivraisonGrand = new ArrayList(set); 
        assertEquals(tousLesLieuxLivraisonGrand, distinctTousLesLieuxLivraisonGrand, "Il y a un ou plusieurs lieux à livrer en double"); /* vérifie qu'il n'y a pas de doublons pour les noeuds */
		
		boolean boolTest2 = true;
		for(int i=0; i<lieuxLivraisonGrand.getLength();i++) {
			
			if(tousLesLieux.indexOf(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("adresse").getNodeValue())==-1) {
				boolTest2=false;
				break;	
			}
			
		}
		assertTrue("l'un des lieux de livraison est introuvable sur le plan", boolTest2); // vérifie que tous les lieux de livraison dans la DL existent sur le plan
		/* Fin Vérifications LIEUX DE LIVRAISON */
		
		
		/* Vérification VALIDITE INFO DES LIVRAISONS */
		
		boolean boolTest3 = true;
		
		/* 1- pour l'entrepot */
		
		/* check validité debutPlage */
		if(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage") != null) {
				if(Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(0,2))<0 || 
				Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(0,2)) > 24 ||
				Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(3,4)) < 0 ||
				Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(3,4)) > 60 ||
				Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(5,6)) < 0 ||
				Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(5,6)) > 60)
					boolTest3 = false;
		}
		
		/* check validité finPlage */
		if(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("finPlage") != null) {
			if(Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(0,2))<0 || 
			Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(0,2)) > 24 ||
			Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(3,4)) < 0 ||
			Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(3,4)) > 60 ||
			Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(5,6)) < 0 ||
			Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(5,6)) > 60)
				boolTest3 = false;
		}
		
		/* check validité intervalle [debutPlage,finPlage] */
		if(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage") != null && entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("finPlage") != null) {
			if(Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(0,1)) > 
			Integer.parseInt(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("finPlage").getNodeValue().substring(0,1)))
				boolTest3 = false;
				
		}
		
		/* 2- pour le reste des lieux de livraison */
	
			for(int i=0; i<lieuxLivraisonGrand.getLength();i++) {
				
				if(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("debutPlage") != null) {
					if(Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(0,2))<0 || 
					Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(0,2)) > 24 ||
					Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(3,4)) < 0 ||
					Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(3,4)) > 60 ||
					Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(5,6)) < 0 ||
					Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(5,6)) > 60 )
						boolTest3 = false;
				}
			
			/* check validité finPlage */
				if(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("finPlage") != null) {
					if(Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("finPlage").getNodeValue().substring(0,2))<0 || 
						Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("finPlage").getNodeValue().substring(0,2)) > 24 ||
						Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("finPlage").getNodeValue().substring(3,4)) < 0 ||
						Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("finPlage").getNodeValue().substring(3,4)) > 60 ||
						Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("finPlage").getNodeValue().substring(5,6)) < 0 ||
						Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("finPlage").getNodeValue().substring(5,6)) > 60 )
						boolTest3 = false;
				}
			
			/* check validité intervalle [debutPlage,finPlage] */
				if(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("debutPlage") != null && lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("finPlage") != null) {
					if(Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("debutPlage").getNodeValue().substring(0,2)) > 
					Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("finPlage").getNodeValue().substring(0,2)))
						boolTest3 = false;
						
				}
			
			}
			
			assertTrue("Les informations du document de livraison sont incohérentes", boolTest3);
			/* Fin Vérification VALIDITE INFO DES LIVRAISONS */
			
			/* Vérification BONNE CONSTRUCTION DEMANDE DE LIVRAISON */
			boolean boolTest4 = true;
			Plan grandPlan = new Plan();
			parseur.chargerPlan(grandPlan,cheminGrandLyon);
			DemandeLivraison dlGrand = new DemandeLivraison();
			parseur.chargerLivraison(dlGrand,cheminFichierDLGrand, grandPlan.getListeIntersection() );
			for(int i=0; i<dlGrand.getLivraisons().size(); i++) {
				/* arrangement des chaînes de caractères correspondant aux horaires pour bien pouvoir les parser en local time*/
				String tmp1 = "";
				String tmp2 = "";
				if(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("debutPlage") != null) {
					tmp1 = lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("debutPlage").getNodeValue();
					if(tmp1.split(":")[0].length() != 2) tmp1 = "0" + tmp1.split(":")[0] + ":" + tmp1.split(":")[1] + ":" + tmp1.split(":")[2];
					if(tmp1.split(":")[1].length() != 2) tmp1 = tmp1.split(":")[0] + ":0" + tmp1.split(":")[1] + ":" + tmp1.split(":")[2];
					if(tmp1.split(":")[2].length() != 2) tmp1 = tmp1.split(":")[0] + ":" + tmp1.split(":")[1] + ":0" + tmp1.split(":")[2];
				}
				if(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("finPlage") != null) {
					tmp2 = lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("finPlage").getNodeValue();
					if(tmp2.split(":")[0].length() != 2) tmp2 = "0" + tmp2.split(":")[0] + ":" + tmp2.split(":")[1] + ":" + tmp2.split(":")[2];
					if(tmp2.split(":")[1].length() != 2) tmp2 = tmp2.split(":")[0] + ":0" + tmp2.split(":")[1] + ":" + tmp2.split(":")[2];
					if(tmp2.split(":")[2].length() != 2) tmp2 = tmp2.split(":")[0] + ":" + tmp2.split(":")[1] + ":0" + tmp2.split(":")[2];
				}
				/**/

				if(!tousLesLieux.contains(dlGrand.getLivraisons().get(i).getIntersection().getId().toString())) boolTest4 = false;
				if(dlGrand.getLivraisons().get(i).getDureeDechargement() != Integer.parseInt(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("duree").getNodeValue())) boolTest4 = false;
				if((lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("debutPlage") != null && dlGrand.getLivraisons().get(i).getPlagehoraire().getHeureDebut() != LocalTime.parse(tmp1))) boolTest4 = false;
				if(lieuxLivraisonGrand.item(i).getAttributes().getNamedItem("finPlage") != null && dlGrand.getLivraisons().get(i).getPlagehoraire().getHeureFin() != LocalTime.parse(tmp2))
					boolTest4 = false;	
				
			} /* teste la validité des infos des lieux de livraison de la DL */
			
			if(dlGrand.getEntrepot().getId() != Long.parseLong(entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("adresse").getNodeValue()))	boolTest4 = false; /* teste que l'entrepot de la DL est bien le même que celui du doc à parser */
			/* arrangement de la chaîne de caractères de l'heure de départ pour bien pouvoir la parser en local time*/
			String tmp3 = "";
			tmp3 = entrepotLivraisonGrand.item(0).getAttributes().getNamedItem("heureDepart").getNodeValue();
			if(tmp3.split(":")[0].length() != 2) tmp3 = "0" + tmp3.split(":")[0] + ":" + tmp3.split(":")[1] + ":" + tmp3.split(":")[2];
			if(tmp3.split(":")[1].length() != 2) tmp3 = tmp3.split(":")[0] + ":0" + tmp3.split(":")[1] + ":" + tmp3.split(":")[2];
			if(tmp3.split(":")[2].length() != 2) tmp3 = tmp3.split(":")[0] + ":" + tmp3.split(":")[1] + ":0" + tmp3.split(":")[2];
			/**/

			if(dlGrand.getHeureDepart() != LocalTime.parse(tmp3)) boolTest4 = false; /* teste la validité de l'heure de départ */
			assertTrue("La demande de livraison est mal construite", boolTest4);
			
					
	}
	
	
	
	@Test
	public void testChargerPlan() throws ParseurException {
		
		/* VERIFICATION BONNE CONSTRUCTION DU DOCUMENT XML */
		
		/* 1 */
		final Element racine = planLyonGrand.getDocumentElement();
		assertEquals("reseau", racine.getNodeName(), "document Plan mal construit : pas de balise reseau"); // vérifie que la balise racine correspond bien à un reseau
		
		/*2*/
		NodeList lieuxPlanLyonGrand = planLyonGrand.getElementsByTagName("noeud");
		ArrayList<String> noeudsPlanGrand = new ArrayList();
		boolean checkDoublonsNoeuds = true;
		int indice = 0;
		for(int i=0; i<lieuxPlanLyonGrand.getLength(); i++) {
			noeudsPlanGrand.add(lieuxPlanLyonGrand.item(i).getAttributes().getNamedItem("id").getNodeValue());
		}
		for(int i=0; i<lieuxPlanLyonGrand.getLength(); i++) {
			if(noeudsPlanGrand.indexOf(noeudsPlanGrand.get(i)) != noeudsPlanGrand.lastIndexOf(noeudsPlanGrand.get(i))) {
				checkDoublonsNoeuds = false;
				indice = i;
				break;
			}
		}
		assertTrue("Il y a un ou plusieurs doublons pour les noeuds, découvert à l'indice " + indice, checkDoublonsNoeuds); /* vérifie qu'il n'y a pas de doublons pour les noeuds */
        
        NodeList tronconsPlanLyonGrand = planLyonGrand.getElementsByTagName("troncon");
        ArrayList<String> aTronconsPlanLyonGrand = new ArrayList();
		boolean checkDoublonsTroncons = true;
		int indice2 = 0;
		for(int i=0; i<tronconsPlanLyonGrand.getLength(); i++) {
			String concat = "";
			concat = tronconsPlanLyonGrand.item(i).getAttributes().getNamedItem("origine").getNodeValue().concat(tronconsPlanLyonGrand.item(i).getAttributes().getNamedItem("destination").getNodeValue()).concat(tronconsPlanLyonGrand.item(i).getAttributes().getNamedItem("longueur").getNodeValue());
			aTronconsPlanLyonGrand.add(concat);
		}
		for(int i=0; i<tronconsPlanLyonGrand.getLength(); i++) {
			if(aTronconsPlanLyonGrand.indexOf(aTronconsPlanLyonGrand.get(i)) != aTronconsPlanLyonGrand.lastIndexOf(aTronconsPlanLyonGrand.get(i))) {
				checkDoublonsTroncons = false;
				indice2 = i;
				break;
			}
		}
		assertTrue("Il y a un ou plusieurs doublons pour les tronçons, découvert à l'indice " + indice2, checkDoublonsTroncons); /* vérifie qu'il n'y a pas de doublons pour les tronçons */
        
        /*3*/
        ArrayList<Long> origines = new ArrayList();
        ArrayList<Long> destinations = new ArrayList();
        ArrayList<Long> longueurs = new ArrayList();
        boolean testValeurPositive1 = true;
        boolean testValeurPositive2 = true;
        boolean testValeurPositive3 = true;
        for(int i=0; i<tronconsPlanLyonGrand.getLength();i++) {
        	testValeurPositive1 = true;
            testValeurPositive2 = true;
            testValeurPositive3 = true;
            
        	origines.add(Long.parseLong(tronconsPlanLyonGrand.item(i).getAttributes().getNamedItem("origine").getNodeValue()));
        	if(origines.get(i)<0) testValeurPositive1 = false;
        	assertTrue("Le" + i + " è tronçon a une valeur d'origine incohérente (négative)",testValeurPositive1); /* vérifie la positivité d'une valeur d'origine" */
        	
        	destinations.add(Long.parseLong(tronconsPlanLyonGrand.item(i).getAttributes().getNamedItem("destination").getNodeValue()));
        	if(destinations.get(i)<0) testValeurPositive2 = false;
        	assertTrue("Le" + i + " è tronçon a une valeur de destination incohérente (négative)",testValeurPositive2); /* vérifie la positivité d'une valeur de destination" */
        	
        	longueurs.add(Long.parseLong(tronconsPlanLyonGrand.item(i).getAttributes().getNamedItem("longueur").getNodeValue().replace(".0", "")));
        	if(longueurs.get(i)<0) testValeurPositive3 = false;
        	assertTrue("Le" + i + " è tronçon a une valeur de longueur incohérente (négative)",testValeurPositive3); /* vérifie la positivité d'une valeur d'origine" */
        }
        
        
        /* FIN VERIFICATION BONNE CONSTRUCTION DU DOCUMENT XML */
        
        
        /* VERIFICATION BONNE CONSTRUCTION DE L'OBJET PLAN */
        
        Plan grandPlan = new Plan();
        parseur.chargerPlan(grandPlan,cheminGrandLyon);
        boolean testPositiviteIdIntersection = true;
        java.util.Iterator<Long> it = grandPlan.getListeIntersection().keySet().iterator();
        int ind = 0;
        Long key;
		while(it.hasNext()) {
			key = it.next();
			ind++;
			testPositiviteIdIntersection = true;
			if(grandPlan.getListeIntersection().get(key).getId() < 0) testPositiviteIdIntersection = false;
			assertTrue("L'id de l'intersection n° " + ind + " est négative", testPositiviteIdIntersection); /* Vérification positivité Id intersections de l'objet Plan */
		}
		
		boolean testValiditeIntersection1 = true;
		boolean testValiditeIntersection2 = true;
		boolean testValiditeIntersection3 = true;
		boolean testValiditeIntersectionTronconsSortants = true;

		ArrayList<String> xNoeuds = new ArrayList();
		for(int i=0; i<lieuxPlanLyonGrand.getLength(); i++) {
			xNoeuds.add(lieuxPlanLyonGrand.item(i).getAttributes().getNamedItem("x").getNodeValue());
		}
		ArrayList<String> yNoeuds = new ArrayList();
		for(int i=0; i<lieuxPlanLyonGrand.getLength(); i++) {
			yNoeuds.add(lieuxPlanLyonGrand.item(i).getAttributes().getNamedItem("y").getNodeValue());
		}
		ArrayList<String> idNoeuds = new ArrayList();
		for(int i=0; i<lieuxPlanLyonGrand.getLength(); i++) {
			idNoeuds.add(lieuxPlanLyonGrand.item(i).getAttributes().getNamedItem("id").getNodeValue());
		}
		
		java.util.Iterator<Long> it2 = grandPlan.getListeIntersection().keySet().iterator();
		Long key2;
		for(int i=0; i < lieuxPlanLyonGrand.getLength(); i++) {
			testValiditeIntersection1 = true;
			testValiditeIntersection2 = true;
			testValiditeIntersection3 = true;
			testValiditeIntersectionTronconsSortants = true;
			
			key2 = it2.next();
			
			if(!xNoeuds.contains(grandPlan.getListeIntersection().get(key2).getX().toString())) testValiditeIntersection1 = false;
			assertTrue("L'abscisse de l'intersection n°" + i + " est invalide", testValiditeIntersection1); /* Vérification validité abscisse x intersections de l'objet Plan */
			
			if(!yNoeuds.contains(grandPlan.getListeIntersection().get(key2).getY().toString())) testValiditeIntersection2 = false;
			assertTrue("L'ordonnée de l'intersection n° " + i + " est invalide", testValiditeIntersection2); /* Vérification validité ordonnée y intersections de l'objet Plan */
			
			if(!idNoeuds.contains(grandPlan.getListeIntersection().get(key2).getId().toString())) testValiditeIntersection3 = false;
			assertTrue("L'Identifiant de l'intersection n° " + i + " est invalide", testValiditeIntersection3); /* Vérification validité id  intersections de l'objet Plan */
			
			for(int j=0; j < grandPlan.getListeIntersection().get(key2).getTronconsSortants().size(); j++) {
				testValiditeIntersectionTronconsSortants = true;
				if(grandPlan.getListeIntersection().get(key2).getTronconsSortants().get(j).getIntersectionDepart() != grandPlan.getListeIntersection().get(key2)) testValiditeIntersectionTronconsSortants = false;
				assertTrue("Le troncon sortant n° " + j+ " de l'intersection n°" + i + " est invalide", testValiditeIntersectionTronconsSortants); /* Vérification validité de la cohérence des tronconsSortants avec les intersections respectives  de l'objet Plan */
			}	
			
		}
		
		boolean testValiditeTroncons1 = true;
		boolean testValiditeTroncons2 = true;
		boolean testValiditeTroncons3 = true;
		boolean testValiditeTroncons4 = true;
		
		ArrayList<String> nrTroncons = new ArrayList();
		for(int i=0; i<tronconsPlanLyonGrand.getLength(); i++) {
			nrTroncons.add(tronconsPlanLyonGrand.item(i).getAttributes().getNamedItem("nomRue").getNodeValue());
		}
		ArrayList<String> originesTroncons = new ArrayList();
		for(int i=0; i<tronconsPlanLyonGrand.getLength(); i++) {
			originesTroncons.add(tronconsPlanLyonGrand.item(i).getAttributes().getNamedItem("origine").getNodeValue());
		}
		ArrayList<Double> longueursTroncons = new ArrayList();
		for(int i=0; i<tronconsPlanLyonGrand.getLength(); i++) {
			longueursTroncons.add(Double.parseDouble(tronconsPlanLyonGrand.item(i).getAttributes().getNamedItem("longueur").getNodeValue()));
		}
		ArrayList<String> destinationTroncons = new ArrayList();
		for(int i=0; i<tronconsPlanLyonGrand.getLength(); i++) {
			destinationTroncons.add(tronconsPlanLyonGrand.item(i).getAttributes().getNamedItem("destination").getNodeValue());
		}
		
		java.util.Iterator<Integer> it3 = grandPlan.getListeTroncons().keySet().iterator();
		Integer key3;
		for(int i=0; i < tronconsPlanLyonGrand.getLength(); i++) {
			testValiditeTroncons1 = true;
			testValiditeTroncons2 = true;
			testValiditeTroncons3 = true;
			testValiditeTroncons4 = true;
			
			key3 = it3.next();
			
			if(!nrTroncons.contains(grandPlan.getListeTroncons().get(key3).getNomDeRue())) testValiditeTroncons1 = false;
			assertTrue("Le nom de Rue du Troncon n° " + i + " est invalide", testValiditeTroncons1); /* Vérification validité du nom de Rue associé aux tronçons de l'objet Plan */
			
			if(!originesTroncons.contains(grandPlan.getListeTroncons().get(key3).getIntersectionDepart().getId().toString())) testValiditeTroncons2 = false;
			assertTrue("L'origine du Troncon n° " + i + " est invalide", testValiditeTroncons2); /* Vérification validité de l'origine associée aux tronçons de l'objet Plan */
			
			if(!longueursTroncons.contains(grandPlan.getListeTroncons().get(key3).getLongueur())) testValiditeTroncons3 = false;
			assertTrue("La longueur du Troncon n° " + i + " est invalide", testValiditeTroncons3); /* Vérification validité de la longueur associée aux tronçons de l'objet Plan */
			
	
			if(!destinationTroncons.contains(grandPlan.getListeTroncons().get(key3).getIntersectionArrive().getId().toString())) testValiditeTroncons4 = false;
			assertTrue("La destination du Troncon n° " + i + " est invalide", testValiditeTroncons4); /* Vérification validité de la destination associée aux tronçons de l'objet Plan */
			
		}
		
		/* FIN VERIFICATION BONNE CONSTRUCTION DE L'OBJET PLAN */
		
		
	}
	
	
	@AfterEach
	public void clean() {

		builder = null;
		docDLPetit = null;
		docDLMoyen = null;
		docDLGrand = null;
		planLyonPetit = null;
		planLyonMoyen = null;
		planLyonGrand = null;	
		parseur = null;
		
	}

}

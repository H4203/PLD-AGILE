package donnees;

import modeles.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParseur
{
	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder;

	public XMLParseur ()
	{
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}


	public DemandeLivraison chargerLivraison (String cheminDuFichier)
	{
		Document document = null;
		try
		{
			document= builder.parse(new File(cheminDuFichier));
		}
		catch (SAXException e)
		{
			//TODO
			e.printStackTrace();
		}
		catch (IOException e)
		{
			//TODO
			e.printStackTrace();
		}

		final Element racine = document.getDocumentElement();
		System.out.println(racine.getNodeName());

		assert (racine.getNodeName().equals("demandeDeLivraisons"));

		DemandeLivraison maDemandeDeLivraison = new DemandeLivraison();

		NodeList racineNoeuds = racine.getChildNodes();
		int nbRacineNoeuds = racineNoeuds.getLength();

		for (int i = 0; i<nbRacineNoeuds; i++) {
			if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE)
			{
				Element monElement = (Element) racineNoeuds.item(i);

				/* on trouve les infos de l'entrepot*/
				String heureDepartDecoupee[]; Long adresse;
				if (monElement.getNodeName().equals("entrepot"))
				{
			//		System.out.println(monElement.getNodeName());
				//	System.out.print("adresse : " + monElement.getAttribute("adresse"));
			//		System.out.print(" heure de depart : " + monElement.getAttribute("heureDepart"));
					
					heureDepartDecoupee = monElement.getAttribute("heureDepart").split(":");
					adresse = Long.parseLong(monElement.getAttribute("adresse"), 10); // voir format pour l'heure....
					
					//maDemandeDeLivraison.setHeureDepart(new Date(2095, 09, 15);
					maDemandeDeLivraison.setEntrepot(new Intersection());
				}
				/* on ajoute les adresses de livraison au plan*/
				Long idAdresse; int duree; //+plage horaire
				if (monElement.getNodeName().equals("livraison"))
				{
		//			System.out.println(monElement.getNodeName());
			//		System.out.print("adresse : " + monElement.getAttribute("adresse"));
				//	System.out.print(" duree : " + monElement.getAttribute("duree"));
					
					duree =  Integer.parseInt(monElement.getAttribute("duree"));
					idAdresse = Long.parseLong(monElement.getAttribute("adresse"), 10);
					
					maDemandeDeLivraison.ajouterLivraison(idAdresse, duree);
				}
			}				
		}

		return maDemandeDeLivraison;
	}

	
	public Plan chargerPlan (String cheminDuFichier)
	{
		Document document = null;
		try
		{
			document= builder.parse(new File(cheminDuFichier));
		}
		catch (SAXException e)
		{
			//TODO
			e.printStackTrace();
		}
		catch (IOException e)
		{
			//TODO
			e.printStackTrace();
		}

		final Element racine = document.getDocumentElement();
		System.out.println(racine.getNodeName());

		assert (racine.getNodeName().equals("reseau"));

		Plan monPlan = new Plan();

		NodeList racineNoeuds = racine.getChildNodes();
		int nbRacineNoeuds = racineNoeuds.getLength();

		for (int i = 0; i<nbRacineNoeuds; i++) {
			if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE)
			{
				Element monElement = (Element) racineNoeuds.item(i);

				/* on ajoute les intersections au plan*/
				Long id; int x, y;
				if (monElement.getNodeName().equals("noeud"))
				{
					System.out.println(monElement.getNodeName());
					System.out.print("id : " + monElement.getAttribute("id"));
					System.out.print(" x : " + monElement.getAttribute("x"));
					System.out.println(" y : " + monElement.getAttribute("y"));
					
					id = Long.parseLong(monElement.getAttribute("id"), 10);
					x = Integer.parseInt(monElement.getAttribute("x"));
					y = Integer.parseInt(monElement.getAttribute("y"));
					
					monPlan.ajouterIntersection(id, x, y);
				}
				/* on ajoute les troncons au plan*/
				String nomRue; Long idDepart, idArrivee; double longueur;
				if (monElement.getNodeName().equals("troncon"))
				{
					System.out.println(monElement.getNodeName());
					System.out.print("destination : " + monElement.getAttribute("destination"));
					System.out.print(" nomRue : " + monElement.getAttribute("nomRue"));
					
					nomRue = monElement.getAttribute("nomRue");
					idDepart = Long.parseLong(monElement.getAttribute("origine"), 10);
					idArrivee = Long.parseLong(monElement.getAttribute("destination"), 10);
					longueur = Double.parseDouble(monElement.getAttribute("longueur"));
					
					monPlan.ajouterTroncons(nomRue, idDepart, idArrivee, longueur);
				}
			}				
		}

		return monPlan;
	}

	public static void main (String[] args)
	{
		XMLParseur monparseur = new XMLParseur();
		
		Plan monPlan = monparseur.chargerPlan("C:\\Users\\heyhey\\Desktop\\4IF\\AGILE\\fichiersXML\\planLyonPetit.xml");
		System.out.println(monPlan.toString());
		
		DemandeLivraison maDemande = monparseur.chargerLivraison("C:\\Users\\heyhey\\Desktop\\4IF\\AGILE\\fichiersXML\\DLpetit3.xml");
		System.out.print(maDemande.toString() );
	
	}

}

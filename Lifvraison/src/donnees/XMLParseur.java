package donnees;

import modeles.*;
import vue.Fenetre;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

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

	/**
	 * charge la livraison
	 * @param cheminDuFichier chemin d'acces sur le disque du fichier XML contenant la demande de livraison
	 * @param listeIntersection les intersections du plan - la map associe les id des intersections avec leur objet Intersection
	 * @return la demande de livraisons
	 */
	public void chargerLivraison (DemandeLivraison demandelivraison, String cheminDuFichier, HashMap<Long, Intersection> listeIntersection)
	{
		demandelivraison.reset();
		
		Document document = null;
		try
		{
			/* on parse le fichier */
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

		/* on s'assure que l'objet � la racine est bien la demande de livraison */
		assert (racine.getNodeName().equals("demandeDeLivraisons"));

		NodeList racineNoeuds = racine.getChildNodes();
		int nbRacineNoeuds = racineNoeuds.getLength();

		/*on boucle sur tous les enfants de la racine */
		for (int i = 0; i<nbRacineNoeuds; i++) {
			if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) // l'element i est un noeud
			{
				Element monElement = (Element) racineNoeuds.item(i);

				/* l'element contient les infos sur l'entrepot 
				 * on set ses valeurs
				 */
				LocalTime heureDepart; Long adresse;
				int heure, minute, seconde;
				if ( monElement.getNodeName().equals("entrepot")) 
				{
					/* on recupere l'heure de depart */
					/* on parse nous meme comme leur heure ne sont pas ISO (00:00:00) */
					heure = Integer.parseInt(monElement.getAttribute("heureDepart").split(":")[0]);
					minute = Integer.parseInt(monElement.getAttribute("heureDepart").split(":")[1]);
					seconde = Integer.parseInt(monElement.getAttribute("heureDepart").split(":")[2]);
					heureDepart = LocalTime.of(heure,minute,seconde);

					//heureDepart = LocalTime.parse(monElement.getAttribute("heureDepart"));

					/* on recupere l'id de l'intersection de depart */
					adresse = Long.parseLong(monElement.getAttribute("adresse"), 10);

					/* on remplit les parametres */
					demandelivraison.setHeureDepart(heureDepart);
					demandelivraison.setEntrepot(listeIntersection.get(adresse));
				}

				/* le noeud est celui contenant les informations sur une livraison */
				Long idAdresse; int duree; LocalTime debutPlage, finPlage; Intersection adresseLivraison;
				if (monElement.getNodeName().equals("livraison"))
				{
					/* on recupere l'id de la livraison */
					idAdresse = Long.parseLong(monElement.getAttribute("adresse"), 10);
					adresseLivraison = listeIntersection.get(idAdresse);
					/* on recupere la duree de dechargement */
					duree =  Integer.parseInt(monElement.getAttribute("duree"));
					/* on recupere l'heure de debut si elle existe */
					if (!monElement.getAttribute("debutPlage").equals(""))
					{
						heure = Integer.parseInt(monElement.getAttribute("debutPlage").split(":")[0]);
						minute = Integer.parseInt(monElement.getAttribute("debutPlage").split(":")[1]);
						seconde = Integer.parseInt(monElement.getAttribute("debutPlage").split(":")[2]);
						debutPlage = LocalTime.of(heure,minute,seconde);
						//debutPlage = LocalTime.parse(monElement.getAttribute("debutPlage"));
					} else debutPlage = null;
					/* on recupere l'heure de fin */
					if (!monElement.getAttribute("finPlage").equals(""))
					{
						heure = Integer.parseInt(monElement.getAttribute("finPlage").split(":")[0]);
						minute = Integer.parseInt(monElement.getAttribute("finPlage").split(":")[1]);
						seconde = Integer.parseInt(monElement.getAttribute("finPlage").split(":")[2]);
						finPlage = LocalTime.of(heure,minute,seconde);
						//finPlage = LocalTime.parse(monElement.getAttribute("finPlage"));
					} else finPlage = null;
					
					/* on ajoute la livraison a la demande */
					demandelivraison.ajouterLivraison(adresseLivraison, duree, debutPlage, finPlage);
				}
			}				
		}
	}


	/**
	 * Charge le plan de la ville
	 * @param cheminDuFichier chemin d'acces sur le disque du fichier XML contenant le plan de livraison
	 * @return
	 */
	public void chargerPlan (Plan plan, String cheminDuFichier)
	{
		plan.reset();
		
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

		/* on s'assure que l'objet a la racine est bien le plan*/
		assert (racine.getNodeName().equals("reseau"));

		NodeList racineNoeuds = racine.getChildNodes();
		int nbRacineNoeuds = racineNoeuds.getLength();

		/*on boucle sur tous les enfants de la racine */
		for (int i = 0; i<nbRacineNoeuds; i++) {
			if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) // l'element i est un noeud
			{
				Element monElement = (Element) racineNoeuds.item(i);

				/* on ajoute les intersections au plan*/
				Long id; int x, y;
				if (monElement.getNodeName().equals("noeud"))
				{
					id = Long.parseLong(monElement.getAttribute("id"), 10);
					x = Integer.parseInt(monElement.getAttribute("x"));
					y = Integer.parseInt(monElement.getAttribute("y"));

					plan.ajouterIntersection(id, x, y);
				}

				/* on ajoute les troncons au plan*/
				String nomRue; Long idDepart, idArrivee; double longueur; Intersection origine, destination;
				if (monElement.getNodeName().equals("troncon"))
				{					
					nomRue = monElement.getAttribute("nomRue");
					idDepart = Long.parseLong(monElement.getAttribute("origine"), 10);
					idArrivee = Long.parseLong(monElement.getAttribute("destination"), 10);
					longueur = Double.parseDouble(monElement.getAttribute("longueur"));

					origine = plan.getListeIntersection().get(idDepart);
					destination = plan.getListeIntersection().get(idArrivee);
					
					plan.ajouterTroncon(nomRue, origine, destination, longueur);
					
				}
			}				
		}
	}
}

package donnees;

import modeles.*;
import vue.Fenetre;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

	public XMLParseur () throws ParseurException
	{
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new ParseurException(e.getMessage(), e);
		}
	}

	/**
	 * charge la livraison
	 * @param cheminDuFichier chemin d'acces sur le disque du fichier XML contenant la demande de livraison
	 * @param listeIntersection les intersections du plan - la map associe les id des intersections avec leur objet Intersection
	 * @return la demande de livraisons
	 * @throws ParseurException 
	 */

	public void chargerLivraison (DemandeLivraison demandelivraison, String cheminDuFichier, HashMap<Long, Intersection> listeIntersection) throws ParseurException
	{
		//demandelivraison.reset();
		
		Document document = null;
		try
		{
			/* on parse le fichier */
			document= builder.parse(new File(cheminDuFichier));
		}
		catch (SAXException e)
		{
			throw new ParseurException(e.getMessage(), e);

		}
		catch (IOException e)
		{
			throw new ParseurException(e.getMessage(), e);
		}

		final Element racine = document.getDocumentElement();

		/* on s'assure que l'objet a la racine est bien la demande de livraison */
		if ( !racine.getNodeName().equals("demandeDeLivraisons") )
		{
			throw new ParseurException("Le fichier n'est pas une demande de livraison...");
		}

		NodeList racineNoeuds = racine.getChildNodes();
		int nbRacineNoeuds = racineNoeuds.getLength();

		if ( nbRacineNoeuds == 0 )
		{
			throw new ParseurException("Le fichier ne contient aucune information");
		}

		int nbEntrepot = 0, nbLivraison = 0;
		/*on boucle sur tous les enfants de la racine */
		for (int i = 0; i<nbRacineNoeuds; i++) {
			if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) // l'element i est un noeud
			{
				Element monElement = (Element) racineNoeuds.item(i);

				/* l'element contient les infos sur l'entrepot 
				 * on set ses valeurs
				 */
				LocalTime heureDepart; Long adresse;
				String heurededepart;
				int heure, minute, seconde;
				if ( monElement.getNodeName().equals("entrepot")) 
				{
					/* on recupere l'heure de depart */
					/* on parse nous meme comme leur heure ne sont pas ISO (00:00:00) */
					try {
						heurededepart = monElement.getAttribute("heureDepart");
						heure = Integer.parseInt(heurededepart.split(":")[0]);
						minute = Integer.parseInt(heurededepart.split(":")[1]);
						seconde = Integer.parseInt(heurededepart.split(":")[2]);
						heureDepart = LocalTime.of(heure,minute,seconde);
					}
					catch (Exception e)
					{
						throw new ParseurException("L'heure de depart est incorrecte (format = h:m:s)", e);
					}
					//heureDepart = LocalTime.parse(monElement.getAttribute("heureDepart"));

					/* on recupere l'id de l'intersection de depart */
					try
					{
						adresse = Long.parseLong(monElement.getAttribute("adresse"), 10);
					}
					catch ( Exception e)
					{
						throw new ParseurException("L'adresse de l'entrepot est incorrecte (format = Long)", e);
					}

					nbEntrepot ++;
					if ( nbEntrepot > 1 )
					{
						throw new ParseurException("Le fichier contient plus d'1 entrepot");
					}
					/* on remplit les parametres */
					demandelivraison.setHeureDepart(heureDepart);
					demandelivraison.setEntrepot(listeIntersection.get(adresse));
				}

				/* le noeud est celui contenant les informations sur une livraison */
				Long idAdresse; int duree; LocalTime debutPlage, finPlage; Intersection adresseLivraison;
				if (monElement.getNodeName().equals("livraison"))
				{
					/* on recupere l'id de la livraison */
					try
					{
						idAdresse = Long.parseLong(monElement.getAttribute("adresse"), 10);
					}
					catch (Exception e)
					{
						throw new ParseurException("L'adresse de la "+ ++nbLivraison +" livraison est incorrecte (format = adresse = LONG)", e);
					}

					adresseLivraison = listeIntersection.get(idAdresse);
					if (adresseLivraison == null)
					{
						throw new ParseurException("L'adresse de la "+ ++nbLivraison +" livraison n'existe pas");
					}
					/* on recupere la duree de dechargement */
					try
					{
						duree =  Integer.parseInt(monElement.getAttribute("duree"));
					}
					catch (Exception e)
					{
						throw new ParseurException("La duree de la "+ ++nbLivraison +" livraison est incorrecte", e);
					}
					if (duree < 0)
					{
						throw new ParseurException("Une duree ne peut pas etre negative!");
					}
					/* on recupere l'heure de debut si elle existe */
					if (!monElement.getAttribute("debutPlage").equals(""))
					{
						try
						{
							String heurededebutPlage = monElement.getAttribute("debutPlage");
							heure = Integer.parseInt(heurededebutPlage.split(":")[0]);
							minute = Integer.parseInt(heurededebutPlage.split(":")[1]);
							seconde = Integer.parseInt(heurededebutPlage.split(":")[2]);
							debutPlage = LocalTime.of(heure,minute,seconde);
						}
						catch (Exception e)
						{
							throw new ParseurException("L'heure de debut de plage de la livraison "+ ++nbLivraison +"est incorrecte (format = h:m:s)", e);
						}
					} else debutPlage = null;
					/* on recupere l'heure de fin */
					if (!monElement.getAttribute("finPlage").equals(""))
					{
						try
						{
							String heuredefinPlage = monElement.getAttribute("finPlage");
							heure = Integer.parseInt(heuredefinPlage.split(":")[0]);
							minute = Integer.parseInt(heuredefinPlage.split(":")[1]);
							seconde = Integer.parseInt(heuredefinPlage.split(":")[2]);
							finPlage = LocalTime.of(heure,minute,seconde);
						}
						catch (Exception e)
						{
							throw new ParseurException("L'heure de fin de plage de la livraison "+ ++nbLivraison +"est incorrecte (format = h:m:s)", e);
						}
					} 
					else finPlage = null;

					++nbLivraison;
					/* on ajoute la livraison a la demande */
					demandelivraison.ajouterLivraison(adresseLivraison, duree, debutPlage, finPlage);
				}
			}				
		}

		if (nbLivraison == 0 )
		{
			throw new ParseurException("Aucune livraison a affectuer ! \n Un jour de repos");
		}
		if (nbLivraison == 0 )
		{
			throw new ParseurException("Aucun entrepot !");
		}
	}


	/**
	 * Charge le plan de la ville
	 * @param cheminDuFichier chemin d'acces sur le disque du fichier XML contenant le plan de livraison
	 * @return
	 * @throws ParseurException 
	 */
	public void chargerPlan (Plan plan, String cheminDuFichier) throws ParseurException
	{
		//plan.reset();
		
		Document document = null;
		try
		{
			document= builder.parse(new File(cheminDuFichier));
		}
		catch (SAXException e)
		{
			throw new ParseurException(e.getMessage(), e);
		}
		catch (IOException e)
		{
			throw new ParseurException(e.getMessage(), e);
		}

		final Element racine = document.getDocumentElement();

		/* on s'assure que l'objet a la racine est bien le plan*/
		if ( !racine.getNodeName().equals("reseau") )
		{
			throw new ParseurException("Le fichier n'est pas une demande de livraison...");
		}

		NodeList racineNoeuds = racine.getChildNodes();
		int nbRacineNoeuds = racineNoeuds.getLength();

		if ( nbRacineNoeuds == 0 )
		{
			throw new ParseurException("Le fichier ne contient aucune information");
		}

		int nbNoeud = 0, nbTroncon = 0;
		/*on boucle sur tous les enfants de la racine */
		for (int i = 0; i<nbRacineNoeuds; i++) {
			if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) // l'element i est un noeud
			{
				Element monElement = (Element) racineNoeuds.item(i);

				/* on ajoute les intersections au plan*/
				Long id; int x, y;
				if (monElement.getNodeName().equals("noeud"))
				{
					try
					{
						id = Long.parseLong(monElement.getAttribute("id"), 10);
						x = Integer.parseInt(monElement.getAttribute("x"));
						y = Integer.parseInt(monElement.getAttribute("y"));
					}
					catch (Exception e)
					{
						throw new ParseurException("Les attributs du"+ ++nbNoeud +"noeud ne sont pas correctements renseignes \n (format = id:Long, x:int, y:int)", e);
					}
					++nbNoeud;
					plan.ajouterIntersection(id, x, y);
				}

				/* on ajoute les troncons au plan*/
				String nomRue; Long idDepart, idArrivee; double longueur; Intersection origine, destination;
				if (monElement.getNodeName().equals("troncon"))
				{
					if( nbNoeud == 0 )
					{
						throw new ParseurException("Veuillez renseigner les noeuds avant de renseigner des troncons!!");
					}

					try {
						nomRue = monElement.getAttribute("nomRue");
						idDepart = Long.parseLong(monElement.getAttribute("origine"), 10);
						idArrivee = Long.parseLong(monElement.getAttribute("destination"), 10);
						longueur = Double.parseDouble(monElement.getAttribute("longueur"));
						if (longueur < 0)
						{
							throw new ParseurException("Une longueur ne peut etre negative!");
						}
					}
					catch (Exception e)
					{
						throw new ParseurException("Les attributs du "+ nbTroncon++ +" troncon sont incorrectes (format = nomRue:string, origine:long, destination:long, longueur:double)", e);
					}

					origine = plan.getListeIntersection().get(idDepart);
					if (origine == null)
					{
						throw new ParseurException("L'intersection d'origine du "+ nbTroncon++ +"eme troncon n'existe pas....");
					}

					destination = plan.getListeIntersection().get(idArrivee);
					if (destination == null)
					{
						throw new ParseurException("L'intersection de destination du +"+ nbTroncon++ +" eme troncon n'existe pas....");
					}

					nbTroncon++;
					plan.ajouterTroncon(nomRue, origine, destination, longueur);
				}
			}				
		}
	}
}

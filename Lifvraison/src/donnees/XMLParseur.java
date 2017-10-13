package donnees;

import modeles.*;

import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

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


	public Livraison chargerLivraison (String cheminDuFichier)
	{
		return null;
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
				Long id; int x, y;
				if (monElement.getNodeName().equals("noeud"))
				{
					System.out.println(monElement.getNodeName());
					System.out.print("id : " + monElement.getAttribute("id"));
					System.out.print(" x : " + monElement.getAttribute("x"));
					System.out.println(" y : " + monElement.getAttribute("y"));
					// pas oublier les tronçons sortants et entrants ?
					id = Long.parseLong(monElement.getAttribute("id"), 10);
					x = Integer.parseInt(monElement.getAttribute("x"));
					y = Integer.parseInt(monElement.getAttribute("y"));
					
					monPlan.ajouterIntersection( id, x, y);
				}
				if (monElement.getNodeName().equals("troncon"))
				{
					System.out.println(monElement.getNodeName());
					System.out.print("destination : " + monElement.getAttribute("destination"));
					System.out.print(" nomRue : " + monElement.getAttribute("nomRue"));
				}
			}				
		}

		return null;
	}

	public static void main (String[] args)
	{
		XMLParseur monparseur = new XMLParseur();
		monparseur.chargerPlan("C:\\Users\\heyhey\\Desktop\\4IF\\AGILE\\fichiersXML\\planLyonPetit.xml");
	}

}

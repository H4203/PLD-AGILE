package vue;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import controleur.Controleur;

/**
 * Classe EcouteurDeSouris
 * Implemente MouseListener, MouseMotionListener, et MouseWheelListener
 * @author H4203
 */
public class EcouteurDeSouris implements MouseListener, MouseMotionListener, MouseWheelListener 
{
	/**
	 * Point de debut et de fin pour la gestion du glisser deposer
	 */
	private Point startPoint;
	private Point endPoint;
	
	private MapPanel mapPanel;
	
	private Controleur controleur;
	
	/**
	 * Constructeur de EcouteurDeSouris
	 * Initialise les points de debut et de fin
	 * @param mapPanel
	 * @param controleur
	 */
	public EcouteurDeSouris(MapPanel mapPanel, Controleur controleur)
	{
		startPoint = new Point();
		endPoint = new Point();
		
		this.mapPanel = mapPanel;
		
		this.controleur = controleur;
	}
	
	/**
	 * Gestion du glisser deposer
	 */
	@Override
	public void mouseDragged(MouseEvent event) 
	{
		endPoint = event.getPoint();
		
		controleur.mouseDrag(new Point(endPoint.x - startPoint.x, endPoint.y - startPoint.y));
		
		startPoint = endPoint;
	}

	@Override
	public void mouseMoved(MouseEvent event) 
	{
		
	}

	/**
	 * Gestion du clic
	 */
	@Override
	public void mouseClicked(MouseEvent event) 
	{
		controleur.clicGauche(mapPanel.convertPoint(event.getPoint()));
	}

	@Override
	public void mouseEntered(MouseEvent event) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent event) 
	{
	
	}

	/**
	 * Gestion de l'appui sur le clic
	 */
	@Override
	public void mousePressed(MouseEvent event) 
	{
		startPoint = event.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent event) 
	{

	}

	/**
	 * Gestion de la moette
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent event) 
	{
		controleur.mouseWheel(event.getWheelRotation(), event.getPoint());
	}
}

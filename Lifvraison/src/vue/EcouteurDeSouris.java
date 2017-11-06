package vue;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import controleur.Controleur;

public class EcouteurDeSouris implements MouseListener, MouseMotionListener, MouseWheelListener 
{
	private Point startPoint;
	private Point endPoint;
	
	private MapPanel mapPanel;
	
	private Controleur controleur;
	
	public EcouteurDeSouris(MapPanel mapPanel, Controleur controleur)
	{
		startPoint = new Point();
		endPoint = new Point();
		
		this.mapPanel = mapPanel;
		
		this.controleur = controleur;
	}
	
	@Override
	public void mouseDragged(MouseEvent event) 
	{
		endPoint = event.getPoint();
	    //System.out.println("Mouse From " + startPoint + " Dragged to " + endPoint);
	    //System.out.println("Delta : X = " + (endPoint.x - startPoint.x) + ", Y = " + (endPoint.y - startPoint.y));
		
		controleur.mouseDrag(new Point(endPoint.x - startPoint.x, endPoint.y - startPoint.y));
		
		startPoint = endPoint;
	}

	@Override
	public void mouseMoved(MouseEvent event) 
	{
		
	}

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

	@Override
	public void mousePressed(MouseEvent event) 
	{
		startPoint = event.getPoint();
	    //System.out.println("Mouse Pressed at " + startPoint);
	}

	@Override
	public void mouseReleased(MouseEvent event) 
	{

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent event) 
	{
		controleur.mouseWheel(event.getWheelRotation(), event.getPoint());
	}
}

package vue;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class EcouteurDeSouris implements MouseListener, MouseMotionListener
{
	private Point startPoint;
	private Point endPoint;
	
	public EcouteurDeSouris()
	{
		startPoint = new Point();
		endPoint = new Point();
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		endPoint = arg0.getPoint();
	    //System.out.println("Mouse From " + startPoint + " Dragged to " + endPoint);
	    System.out.println("Delta : X = " + (endPoint.x - startPoint.x) + ", Y = " + (endPoint.y - startPoint.y));
	}

	@Override
	public void mouseMoved(MouseEvent arg0) 
	{

	}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
	
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
	
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		startPoint = arg0.getPoint();
	    //System.out.println("Mouse Pressed at " + startPoint);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{

	}
}

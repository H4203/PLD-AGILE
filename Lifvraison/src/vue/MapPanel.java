package vue;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Iterator;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JPanel;

import modeles.Plan;
import modeles.Troncon;
import modeles.Intersection;

public class MapPanel extends JPanel 
{
	private Plan plan;
	private int xMin = 999999;
	private int yMin = 999999;
	private double coefX;
	private double coefY;
	
	public MapPanel(Plan plan)
	{
		this.plan = plan;
		
		int xMax = 0;
		int yMax = 0;
		
		for (Map.Entry mapentry : plan.getListeIntersection().entrySet()) 
        {
			//System.out.println(mapentry.getKey());
			
			if (((Intersection) mapentry.getValue()).getX() > xMax)
			{
				xMax = ((Intersection) mapentry.getValue()).getX();
			}
			else if (((Intersection) mapentry.getValue()).getX() < xMin)
			{
				xMin = ((Intersection) mapentry.getValue()).getX();
			}
			
			if (((Intersection) mapentry.getValue()).getY() > yMax)
			{
				yMax = ((Intersection) mapentry.getValue()).getY();
			}
			else if (((Intersection) mapentry.getValue()).getY() < yMin)
			{
				yMin = ((Intersection) mapentry.getValue()).getY();
			}
        }
		
		coefX = 500.0 / (xMax - xMin);
		coefY = 750.0 / (yMax - yMin);
		
		//System.out.println(coefY);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
        for (Map.Entry mapentry : plan.getListeTroncons().entrySet()) 
        {
        	/*System.out.println((((Troncon) mapentry.getValue()).getIntersectionDepart().getX() - xMin) * coefX);
        	System.out.println((((Troncon) mapentry.getValue()).getIntersectionDepart().getY() - yMin) * coefY);
        	System.out.println((((Troncon) mapentry.getValue()).getIntersectionArrive().getX() - xMin) * coefX);
        	System.out.println((((Troncon) mapentry.getValue()).getIntersectionArrive().getY() - yMin) * coefY);*/
        	
        	if ((((Troncon) mapentry.getValue()).getNomDeRue()).equals("Cours Émile Zola"))
        	{
        		g.setColor(Color.RED);
        		
        		System.out.println("ok");
        	}
        	else
        	{
        		g.setColor(Color.BLUE);
        	}
        	
        	g.drawLine(25 + (int)Math.round((((Troncon) mapentry.getValue()).getIntersectionDepart().getY() - yMin) * coefY),
        			25 + 500 - (int)Math.round((((Troncon) mapentry.getValue()).getIntersectionDepart().getX() - xMin) * coefX),
        			25 + (int)Math.round((((Troncon) mapentry.getValue()).getIntersectionArrive().getY() - yMin) * coefY),
    				25 + 500 - (int)Math.round((((Troncon) mapentry.getValue()).getIntersectionArrive().getX() - xMin) * coefX));
        } 
	}
}

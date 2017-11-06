package modeles;

import java.util.List;

public class Itineraire {
	
	private List<Troncon> troncons;
	private Intersection depart;
	private Intersection arrivee;
	private double longueur;
	
	/**
	 * @return the troncons
	 */
	public List<Troncon> getTroncons() 
	{
		return troncons;
	}
	/**
	 * @param troncons the troncons to set
	 */
	public void setTroncons(List<Troncon> troncons) 
	{
		this.troncons = troncons;
		
		updateLongueur();
	}
	/**
	 * @return the depart
	 */
	public Intersection getDepart() {
		return depart;
	}
	/**
	 * @param depart the depart to set
	 */
	public void setDepart(Intersection depart) {
		this.depart = depart;
	}
	/**
	 * @return the arrivee
	 */
	public Intersection getArrivee() {
		return arrivee;
	}
	/**
	 * @param arrivee the arrivee to set
	 */
	public void setArrivee(Intersection arrivee) {
		this.arrivee = arrivee;
	}
	
	public double getLongueur()
	{
		return longueur;
	}
	
	public Itineraire(List<Troncon> troncons, Intersection depart, Intersection arrivee) 
	{
		super();
		
		this.troncons = troncons;
		this.depart = depart;
		this.arrivee = arrivee;
		
		updateLongueur();
	}
	
	private void updateLongueur()
	{
		longueur = 0;
		
		for (Troncon troncon : troncons)
		{
			longueur = longueur + troncon.getLongueur();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str="";
		String ruePrecedente = null ;
		double longueurRue = 0;
		Troncon tronconPrecedent = null ;
		for (Troncon troncon : troncons) {
			if (ruePrecedente == null) {
				ruePrecedente = troncon.getNomDeRue();
				longueurRue = troncon.getLongueur();
				str = str + "Continuez ";
				
			}
			else if (ruePrecedente.equals(troncon.getNomDeRue())){
				longueurRue = longueurRue + troncon.getLongueur();
			}
			else {
				Intersection i1,i2,i3;
				i1 = tronconPrecedent.getIntersectionDepart();
				i2 = troncon.getIntersectionDepart();
				i3 = troncon.getIntersectionArrive();
				Integer x1,x2,x3,y1,y2,y3;
				x1 = i1.getX();y1 = i1.getY();
				x2 = i2.getX();y2 = i2.getY();
				x3 = i3.getX();y3 = i3.getY();
				Integer xI = x3-2*x2+x1;
				Integer yI = y3-2*y2+y1;
				double r = Math.sqrt(xI*xI+yI*yI);
				double x = xI/r;
				double y = yI/r;
				double B;
				if (x==0) {
					if (y>0) {
						B = Math.PI/2;
					}
					else if (y<0){
						B = 3*Math.PI/2;
					}
					else {
						B = 0;
					}
				}
				else if (x>0){
					B = Math.atan(y/x);
				}
				else if (y>=0) {
					B = Math.atan(y/x) + Math.PI;
				}
				else {
					B = Math.atan(y/x) - Math.PI;					
				}
				
				if (ruePrecedente.equals("")) {
					ruePrecedente = "Rue sans nom";
				}
				
				str = str +  (int)longueurRue + "m dans "+ ruePrecedente +"\n";
				if (Math.PI/3<=B && B<=2*Math.PI/3) {
					str = str + "Continuez ";
				}
				else if (2*Math.PI/3<=B && B<=3*Math.PI/2) {
					str = str + "Tournez a gauche et continuez ";
				}
				else {
					str = str + "Tournez a droite et continuez ";
				}
				ruePrecedente = troncon.getNomDeRue();
				longueurRue = troncon.getLongueur();
			}
			tronconPrecedent = troncon;
		}
		

		str = str +  (int)longueurRue + "m dans "+ ruePrecedente +"\n";

		return str;
	}
	
	
	
	
	
	
}


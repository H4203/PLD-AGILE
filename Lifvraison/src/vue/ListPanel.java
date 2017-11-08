package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.TextArea;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import controleur.Controleur;
import modeles.DemandeLivraison;
import modeles.Livraison;
import modeles.PlageHoraire;
import modeles.Plan;
import modeles.Tournee;

public class ListPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	private EcouteurDeListes ecouteurDeListes;
	private EcouteurDeBoutons ecouteurDeBoutons;
	private DemandeLivraison demandeLivraison;
	private Tournee tournee;

	private boolean affichageDemandeLivraison;
	private boolean affichageTournee;

	private JList<String> listTexteLivraison;
	private ListSelectionModel listSelectionModel;
	private JScrollPane scrollPane;

	private JPanel splitPanel;

	private JPanel detailsPanel;
	private JLabel detailsTextArea;
	private JPanel detailsButtonPanel;

	private JPanel detailsTronconPanel;
	private JTextArea detailsTronconArea;

	public ListPanel(Fenetre fenetre, DemandeLivraison demandeLivraison, Tournee tournee, Controleur controleur)
	{
		super();

		setLayout(new BorderLayout());

		ecouteurDeListes = new EcouteurDeListes(controleur, this);

		this.demandeLivraison = demandeLivraison;
		this.tournee = tournee;

		affichageDemandeLivraison = false;
		affichageTournee = false;

		scrollPane = new JScrollPane();

		listTexteLivraison = new JList<String>();

		listTexteLivraison.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTexteLivraison.setLayoutOrientation(JList.VERTICAL);
		listTexteLivraison.setVisibleRowCount(10);

		listSelectionModel = listTexteLivraison.getSelectionModel();
		listSelectionModel.addListSelectionListener( ecouteurDeListes );

		//listTexteLivraison.setCellRenderer(new DefaultListCellRenderer() );

		detailsPanel = new JPanel();
		detailsPanel.setLayout(new BorderLayout());

		detailsTextArea = new JLabel();
		detailsTextArea.setLayout(new FlowLayout());
		detailsPanel.add(detailsTextArea, BorderLayout.NORTH);

		detailsButtonPanel = new JPanel();
		detailsButtonPanel.setLayout(new FlowLayout());
		detailsPanel.add(detailsButtonPanel);

		/*ecouteurDeBoutons = new EcouteurDeBoutons(controleur);

		JButton buttonValiderModification = new JButton("Valider modif");
		buttonValiderModification.addActionListener(ecouteurDeBoutons);
		detailsButtonPanel.add(buttonValiderModification);
		buttonValiderModification.setVisible(false);

		JButton buttonMonter = new JButton("Monter");
		buttonMonter.addActionListener(ecouteurDeBoutons);
		detailsButtonPanel.add(buttonMonter);

		JButton buttonDescendre = new JButton("Descendre");
		buttonDescendre.addActionListener(ecouteurDeBoutons);
		detailsButtonPanel.add(buttonDescendre);*/

		detailsTronconPanel = new JPanel();
		detailsTronconPanel.setLayout(new BorderLayout());

		detailsTronconArea = new JTextArea();
		detailsTronconArea.setLayout(new FlowLayout());
		detailsTronconPanel.add(detailsTronconArea, BorderLayout.NORTH);

		splitPanel = new JPanel();
		splitPanel.setLayout(new BoxLayout(splitPanel, BoxLayout.PAGE_AXIS));
		splitPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		add(splitPanel, BorderLayout.CENTER);

		splitPanel.setVisible(false);

		splitPanel.add(scrollPane);
		splitPanel.add(detailsPanel);
		splitPanel.add(detailsTronconPanel);

		splitPanel.setSize(splitPanel.getPreferredSize());
		validate();

	}

	public void remplirListe ()
	{
		String color = "black";
		detailsTextArea.setVisible(false);
		detailsTextArea.repaint();
		//listTexteLivraison.setVisible(false);

		if ( affichageTournee && tournee != null && !tournee.getLivraisonsOrdonnees().isEmpty() ) {
			String tableauTexteList[] = new String[tournee.getLivraisonsOrdonnees().size()+2]; 

			int i = 0;
			for(PlageHoraire plgrhoraire : tournee.getListeHoraire()) {
				if (i == 0)
				{
					tableauTexteList[i] = "Depart de l'entrepot a " + plgrhoraire.getHeureDebut().toString();
				}
				else if ( i == tournee.getListeHoraire().size()-1 )
				{
					tableauTexteList[i]= "Retour a l'entrepot a " + plgrhoraire.getHeureFin().toString();
				}
				else
				{
					if (tournee.getLivraisonsOrdonnees().get(i-1).getPlagehoraire()!=null) {
						if (tournee.getLivraisonsOrdonnees().get(i-1).getPlagehoraire().getHeureDebut().compareTo(plgrhoraire.getHeureDebut()) > 0 ) { //attente
							color = "orange";
						}
					}
					if (tournee.getLivraisonsOrdonnees().get(i-1).getPlagehoraire()!=null) {
						if (tournee.getLivraisonsOrdonnees().get(i-1).getPlagehoraire().getHeureFin().compareTo(plgrhoraire.getHeureDebut()) < 0 ) { //en retard
							color = "red";
						}
					}
					tableauTexteList[i] = "<html><font color='"+color+"'>Livraison " + i + " - de " + plgrhoraire.getHeureDebut().toString()
							+ " a " + plgrhoraire.getHeureFin().toString() + " - duree de livraison: " + (int)(tournee.getLivraisonsOrdonnees().get(i-1).getDureeDechargement()/60) + " mins";

				}
				i = i + 1;
				color="black";
			}

			listTexteLivraison.setListData(tableauTexteList);
			scrollPane.setBorder(new TitledBorder(null, "Details Tournee", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
			scrollPane.setViewportView(listTexteLivraison);
			splitPanel.setVisible(true);
			detailsPanel.setVisible(false);
			detailsTronconPanel.setVisible(false);
			this.repaint();
		}
		else if (affichageDemandeLivraison && demandeLivraison != null && !demandeLivraison.getLivraisons().isEmpty()) {
			String tableauTexteList[] = new String[demandeLivraison.getLivraisons().size()+1]; //+1 pour l'entrepot

			int i = 0;
			tableauTexteList[i] = " Heure de depart de l'entrepot : " + demandeLivraison.getHeureDepart();
			for(Livraison livraison: demandeLivraison.getLivraisons()) {
				i++;
				if (livraison.getPlagehoraire() != null && livraison.getPlagehoraire().getHeureDebut() != null ) {
					tableauTexteList[i] = "Livraison " + i + " - de " + livraison.getPlagehoraire().getHeureDebut().toString() 
							+ " a " + livraison.getPlagehoraire().getHeureFin().toString();
				}
				else {
					tableauTexteList[i] = "Livraison " + i + "- sans plage horaire";
				}
			}

			listTexteLivraison.setListData(tableauTexteList);
			scrollPane.setBorder(new TitledBorder(null, "Details Demande Livraison", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
			scrollPane.setViewportView(listTexteLivraison);
			splitPanel.setVisible(true);
			detailsPanel.setVisible(false);
			detailsTronconPanel.setVisible(false);
			this.repaint();
		}
		else {
			splitPanel.setVisible(false);
			this.repaint();
		}

	}

	public void remplirDetails () {
		splitPanel.setVisible(false);
		detailsPanel.setVisible(false);

		detailsPanel.setBorder(new TitledBorder(null, "Details Livraison", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		detailsTextArea.setText(listTexteLivraison.getSelectedValue());
		if(tournee != null)
		{
			int index = listTexteLivraison.getSelectedIndex()-1;
			if ( !tournee.getLivraisonsOrdonnees().isEmpty() && index > 0 && index < tournee.getLivraisonsOrdonnees().size() ) {
				Livraison livraison = tournee.getLivraisonsOrdonnees().get(index);
				if(livraison.getPlagehoraire() != null ) {
					detailsTextArea.setText(detailsTextArea.getText()+"<br> Plage horaire : " + livraison.getPlagehoraire().getHeureDebut() + " - " + livraison.getPlagehoraire().getHeureFin());
				}
				detailsTextArea.setText(detailsTextArea.getText()+"<br> Duree de dechargement :" + livraison.getDureeDechargement()/60 + " minutes");
			}
		}
		detailsPanel.setVisible(true);
		splitPanel.setVisible(true);
		detailsPanel.repaint();
		splitPanel.repaint();
		repaint();
	}

	public void cacherDetails()
	{
		detailsPanel.setVisible(false);
	}

	public void remplirTronconDetails (String nomDeRue, int longueur) {

		splitPanel.setVisible(false);
		detailsTronconPanel.setVisible(true);

		detailsTronconPanel.setBorder(new TitledBorder(null, "Details Troncon", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		detailsTronconArea.setText("Rue " + nomDeRue + " - longueur: "+ longueur);

		splitPanel.setVisible(true);
		repaint();
	}

	public void cacherDetailsTroncon() {
		detailsTronconPanel.setVisible(false);
	}

	public void setAffichageDemandeLivraison(boolean etat)
	{
		affichageDemandeLivraison = etat;
	}

	public void setAffichageTournee(boolean etat)
	{
		affichageTournee = etat;
	}
	public void setDemandeLivraison (DemandeLivraison demandeLivraison)
	{
		this.demandeLivraison = demandeLivraison;
		setTournee(null);
	}
	public void setTournee (Tournee tournee)
	{
		this.tournee = tournee;
	}

	public int getCurrentSelection() {
		return listTexteLivraison.getSelectedIndex();
	}

	public void setSelectedIndex(int index) {
		listTexteLivraison.setSelectedIndex(index);
	}

	public class Rendu extends JLabel implements ListCellRenderer {

		Color depassePlageHoraireCouleur = Color.RED;
		Color attenteCouleur = Color.ORANGE;
		Color aUnePHCouleur = Color.YELLOW;
		Color selectionnerCouleur = Color.BLUE;

		public  Rendu(){
		}

		public Component getListCellRendererComponent(JList list, 
				Object value, // valeur à afficher
				int index, // indice d'item
				boolean isSelected, // l'item est-il sélectionné
				boolean cellHasFocus) // La liste a-t-elle le focus
		{
			String s = value.toString();
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(selectionnerCouleur);
			}else{
				setBackground(list.getBackground());
				setForeground(list.getForeground());
				setText(s);
			}
			setEnabled(list.isEnabled());
			setFont(list.getFont());
			setOpaque(true);
			return this;
		}
	}
}
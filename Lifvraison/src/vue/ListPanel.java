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
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

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
	private JTextArea detailsTextArea;
	private JPanel detailsButtonPanel;
	
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
		
		detailsTextArea = new JTextArea();
		detailsTextArea.setLayout(new FlowLayout());
		detailsPanel.add(detailsTextArea, BorderLayout.NORTH);
		
		detailsButtonPanel = new JPanel();
		detailsButtonPanel.setLayout(new FlowLayout());
		detailsPanel.add(detailsButtonPanel);
		
		ecouteurDeBoutons = new EcouteurDeBoutons(controleur);
		
		JButton buttonValiderModification = new JButton("Valider modif");
		buttonValiderModification.addActionListener(ecouteurDeBoutons);
		detailsButtonPanel.add(buttonValiderModification);
		buttonValiderModification.setVisible(false);
		
		JButton buttonMonter = new JButton("Monter");
		buttonMonter.addActionListener(ecouteurDeBoutons);
		detailsButtonPanel.add(buttonMonter);
		
		JButton buttonDescendre = new JButton("Descendre");
		buttonDescendre.addActionListener(ecouteurDeBoutons);
		detailsButtonPanel.add(buttonDescendre);
		
		splitPanel = new JPanel();
		splitPanel.setLayout(new BorderLayout(0,10));
		add(splitPanel, BorderLayout.CENTER);
		
		splitPanel.setVisible(false);
		
		splitPanel.add(scrollPane, BorderLayout.NORTH);
		splitPanel.add(detailsPanel);
		
	}
	
	public void remplirListe ()
	{
		detailsTextArea.removeAll();
		detailsTextArea.repaint();
		
		if (tournee != null && !tournee.getLivraisonsOrdonnees().isEmpty() && affichageTournee)
		{
			String tableauTexteList[] = new String[tournee.getLivraisonsOrdonnees().size()+2]; //+2 pour entrepot depart et arrive
			
			int i = 0;
			for(PlageHoraire plgrhoraire : tournee.getListeHoraire())
			{
				if (i == 0)
				{
					tableauTexteList[0] = "Depart de l'entrepot a " + plgrhoraire.getHeureDebut().toString();
				}
				else if ( i == tournee.getListeHoraire().size()-1 )
				{
					tableauTexteList[i] = "Retour a l'entrepot a " + plgrhoraire.getHeureFin().toString();
				}
				else
				{
					
					tableauTexteList[i] = + i + " - de " + plgrhoraire.getHeureDebut().toString()
							+ " a " + plgrhoraire.getHeureFin().toString();
					
				}
				i = i + 1;
			}

			listTexteLivraison.setListData(tableauTexteList);

			scrollPane.setViewportView(listTexteLivraison);
			splitPanel.setVisible(true);
			detailsPanel.setVisible(false);
			this.repaint();
		}
		else if (demandeLivraison != null && affichageDemandeLivraison)
		{
			String tableauTexteList[] = new String[demandeLivraison.getLivraisons().size()+1]; //+1 pour l'entrepot

			int i = 0;
			tableauTexteList[i] = " Heure de depart de l'entrepot : " + demandeLivraison.getHeureDepart();
			for(Livraison livraison: demandeLivraison.getLivraisons())
			{
				i++;
				if (livraison.getPlagehoraire() != null && livraison.getPlagehoraire().getHeureDebut() != null ) {
				tableauTexteList[i] = "Livraison " + i + " - de " + livraison.getPlagehoraire().getHeureDebut().toString() 
						+ " a " + livraison.getPlagehoraire().getHeureFin().toString();
				}
				else
				{
					tableauTexteList[i] = "Livraison " + i + "- sans plage horaire";
				}
					
				}

			listTexteLivraison.setListData(tableauTexteList);
			
			scrollPane.setViewportView(listTexteLivraison);
			splitPanel.setVisible(true);
			detailsPanel.setVisible(false);
			this.repaint();
		}
		else
		{
			//splitPanel.setVisible(false);
			this.repaint();
		}
	}
	
	public void remplirDetails ()
	{
		splitPanel.setVisible(false);
		detailsPanel.setVisible(true);
		
		detailsTextArea.setText(listTexteLivraison.getSelectedValue());
		
		if(tournee != null)
		{
			int index = listTexteLivraison.getSelectedIndex()-1;
			if ( !tournee.getLivraisonsOrdonnees().isEmpty() && index > 0 && index < tournee.getLivraisonsOrdonnees().size() ) {
			Livraison livraison = tournee.getLivraisonsOrdonnees().get(index);
			if(livraison.getPlagehoraire() != null ) {
				detailsTextArea.append("\n Plage horaire : " + livraison.getPlagehoraire().getHeureDebut() + " - " + livraison.getPlagehoraire().getHeureFin());
			}
			detailsTextArea.append("\n Duree de dechargement :" + livraison.getDureeDechargement()/60 + " minutes");
			}
		}
		
		splitPanel.setVisible(true);
		repaint();
	}
	
	public void cacherDetails()
	{
		detailsPanel.setVisible(false);
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
	
	public int getCurrentSelection()
	{
		return listTexteLivraison.getSelectedIndex();
	}
        
}
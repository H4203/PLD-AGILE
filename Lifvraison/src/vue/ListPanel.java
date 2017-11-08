package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import controleur.Controleur;
import modeles.DemandeLivraison;
import modeles.Livraison;
import modeles.PlageHoraire;
import modeles.Tournee;

/**
 * Contenant de la liste de la vue textuelle et ses details
 * 
 * @author H4203
 *
 */
public class ListPanel extends JPanel {
	/**
	 * Ecouteur de la liste
	 */
	private EcouteurDeListes ecouteurDeListes;
	/**
	 * La demande de livraison de la session en cours
	 */
	private DemandeLivraison demandeLivraison;
	/**
	 * La tournee de la session en cours
	 */
	private Tournee tournee;

	/**
	 * indique si la demande de livraison doit etre affichee
	 */
	private boolean affichageDemandeLivraison;
	/**
	 * indique si la tournee doit etre affichee
	 */
	private boolean affichageTournee;

	/**
	 * Liste de string chacun decrivant une livraison
	 */
	private JList<String> listTexteLivraison;
	/**
	 * le model de selection de la liste
	 */
	private ListSelectionModel listSelectionModel;
	/**
	 * Contenant de la liste permettant de pouvoir defiler
	 */
	private JScrollPane scrollPane;

	/**
	 * Contenant la liste et ses details
	 */
	private JPanel splitPanel;

	/**
	 * Contenant les details de l'element de la liste selectionne
	 */
	private JPanel detailsPanel;
	/**
	 * Contenant le texte des details
	 */
	private JTextArea detailsTextArea;

	/**
	 * Contenant les details du troncon selectionne
	 */
	private JPanel detailsTronconPanel;
	/**
	 * Contenant le texte des troncons
	 */
	private JTextArea detailsTronconArea;

	/**
	 * Constructeur
	 * 
	 * @param fenetre
	 *            fenetre ou elle est contenue
	 * @param demandeLivraison
	 *            la demande de livraison de la session en cours
	 * @param tournee
	 *            la tournee de la session en cours
	 * @param controleur
	 *            le controlleur de la session en cours
	 */
	public ListPanel(Fenetre fenetre, DemandeLivraison demandeLivraison, Tournee tournee, Controleur controleur) {
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
		listTexteLivraison.setVisibleRowCount(20);

		listSelectionModel = listTexteLivraison.getSelectionModel();
		listSelectionModel.addListSelectionListener(ecouteurDeListes);

		detailsPanel = new JPanel();
		detailsPanel.setLayout(new BorderLayout());

		detailsTextArea = new JTextArea();
		detailsTextArea.setLineWrap(true);
		detailsTextArea.setLayout(new FlowLayout());
		detailsPanel.add(detailsTextArea, BorderLayout.CENTER);

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

	/**
	 * Remplit la liste selon le mode de l'affichage
	 */
	public void remplirListe() {
		String color = "black";
		detailsTextArea.removeAll();
		detailsTextArea.repaint();
		listTexteLivraison.removeAll();

		if (affichageTournee && tournee != null && !tournee.getLivraisonsOrdonnees().isEmpty()) {
			String tableauTexteList[] = new String[tournee.getLivraisonsOrdonnees().size() + 2];

			int i = 0;
			for (PlageHoraire plgrhoraire : tournee.getListeHoraire()) {
				if (i == 0) {
					tableauTexteList[i] = "Depart de l'entrepot a " + plgrhoraire.getHeureDebut().toString();
				} else if (i == tournee.getListeHoraire().size() - 1) {
					tableauTexteList[i] = "Retour a l'entrepot a " + plgrhoraire.getHeureFin().toString();
				} else {
					if (tournee.getLivraisonsOrdonnees().get(i - 1).getPlagehoraire() != null) {
						if (tournee.getLivraisonsOrdonnees().get(i - 1).getPlagehoraire().getHeureDebut()
								.compareTo(plgrhoraire.getHeureDebut()) > 0) { // attente
							color = "orange";
						}
					}
					if (tournee.getLivraisonsOrdonnees().get(i - 1).getPlagehoraire() != null) {
						if (tournee.getLivraisonsOrdonnees().get(i - 1).getPlagehoraire().getHeureFin()
								.compareTo(plgrhoraire.getHeureDebut()) < 0) { // en retard
							color = "red";
						}
					}
					tableauTexteList[i] = "<html><font color='" + color + "'>Livraison " + i + " - de "
							+ plgrhoraire.getHeureDebut().toString() + " a " + plgrhoraire.getHeureFin().toString()
							+ "<br>duree de dechargement: "
							+ (int) (tournee.getLivraisonsOrdonnees().get(i - 1).getDureeDechargement() / 60)
							+ " mins</font></html>";

				}
				i = i + 1;
				color = "black";
			}

			listTexteLivraison.setListData(tableauTexteList);
			scrollPane.setBorder(
					new TitledBorder(null, "Details Tournee", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
			scrollPane.setViewportView(listTexteLivraison);
			splitPanel.setVisible(true);
			detailsPanel.setVisible(false);
			detailsTronconPanel.setVisible(false);
			this.repaint();
		} else if (affichageDemandeLivraison && demandeLivraison != null
				&& !demandeLivraison.getLivraisons().isEmpty()) {
			String tableauTexteList[] = new String[demandeLivraison.getLivraisons().size() + 1]; // +1 pour l'entrepot

			int i = 0;
			tableauTexteList[i] = " Heure de depart de l'entrepot : " + demandeLivraison.getHeureDepart();
			for (Livraison livraison : demandeLivraison.getLivraisons()) {
				i++;
				if (livraison.getPlagehoraire() != null && livraison.getPlagehoraire().getHeureDebut() != null) {
					tableauTexteList[i] = "<html>Livraison " + i + " - de "
							+ livraison.getPlagehoraire().getHeureDebut().toString() + " a "
							+ livraison.getPlagehoraire().getHeureFin().toString();
				} else {
					tableauTexteList[i] = "<html>Livraison " + i + "- sans plage horaire";
				}
				tableauTexteList[i] += "<br>duree de dechargement: " + (int) (livraison.getDureeDechargement() / 60)
						+ " mins</html>";
			}

			listTexteLivraison.setListData(tableauTexteList);

			scrollPane.setBorder(new TitledBorder(null, "Details Demande De Livraison", TitledBorder.CENTER,
					TitledBorder.DEFAULT_POSITION));
			scrollPane.setViewportView(listTexteLivraison);
			splitPanel.setVisible(true);
			detailsPanel.setVisible(false);
			detailsTronconPanel.setVisible(false);
			this.repaint();
		} else {
			splitPanel.setVisible(false);
			this.repaint();
		}

	}

	/**
	 * Remplit les details de ce qui est selectionne sur la liste
	 */
	public void remplirDetails() {
		splitPanel.setVisible(false);
		detailsPanel.setVisible(false);

		detailsPanel.setBorder(
				new TitledBorder(null, "Details Livraison", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		detailsTextArea
				.setText(listTexteLivraison.getSelectedValue().replaceAll("<br>", "\n").replaceAll("\\<.*?>", ""));
		if (tournee != null) {
			int index = listTexteLivraison.getSelectedIndex() - 1;
			if (!tournee.getLivraisonsOrdonnees().isEmpty() && index >= 0
					&& index < tournee.getLivraisonsOrdonnees().size()) {
				Livraison livraison = tournee.getLivraisonsOrdonnees().get(index);
				if (livraison.getPlagehoraire() != null) {
					detailsTextArea.setText(detailsTextArea.getText().replaceAll("<br>", "\n").replaceAll("\\<.*?>", "")
							+ '\r' + '\n' + "Plage horaire : " + livraison.getPlagehoraire().getHeureDebut() + " - "
							+ livraison.getPlagehoraire().getHeureFin());
				}
			}
		}

		detailsPanel.setVisible(true);
		splitPanel.setVisible(true);
		detailsPanel.repaint();
		splitPanel.repaint();
		repaint();
	}

	/**
	 * Cache le contenant des details de ce qui est selectionne dans la liste
	 */
	public void cacherDetails() {
		detailsPanel.setVisible(false);
	}

	/**
	 * Remplit le contenant des details sur le troncon selectionne
	 * 
	 * @param nomDeRue
	 *            nom de la rue du troncon selectionne
	 * @param longueur
	 *            longueur du troncon selectionne
	 */
	public void remplirTronconDetails(String nomDeRue, int longueur) {

		splitPanel.setVisible(false);
		detailsTronconPanel.setVisible(true);

		detailsTronconPanel.setBorder(
				new TitledBorder(null, "Details Troncon", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		detailsTronconArea.setText("Rue " + nomDeRue + " - longueur: " + longueur);

		splitPanel.setVisible(true);
		repaint();
	}

	/**
	 * Cache les details du troncon selectionne
	 */
	public void cacherDetailsTroncon() {
		detailsTronconPanel.setVisible(false);
	}

	/**
	 * Met a jour si la demande de livraison doit etre affiche
	 * 
	 * @param etat
	 *            definit si l'affichage est fait ou non
	 */
	public void setAffichageDemandeLivraison(boolean etat) {
		affichageDemandeLivraison = etat;
	}

	/**
	 * Met a jour si la tournee doit etre affiche
	 * 
	 * @param etat
	 *            definit si l'affichage est fait ou non
	 */
	public void setAffichageTournee(boolean etat) {
		affichageTournee = etat;
	}

	/**
	 * Met a jour la demande de livraison
	 * 
	 * @param demandeLivraison
	 *            la nouvelle demande de livraison
	 */
	public void setDemandeLivraison(DemandeLivraison demandeLivraison) {
		this.demandeLivraison = demandeLivraison;
		setTournee(null);
	}

	/**
	 * Met a jour la tournee
	 * 
	 * @param tournee
	 *            la nouvelle tournee
	 */
	public void setTournee(Tournee tournee) {
		this.tournee = tournee;
	}

	/**
	 * renvoie l'indice de ce qui est selectionne dans la liste
	 * 
	 * @return l'indice de ce qui est selctionne dans la liste
	 */
	public int getCurrentSelection() {
		return listTexteLivraison.getSelectedIndex();
	}

	/**
	 * set l'indice de ce qui doit etre selectionne dans la liste
	 * 
	 * @param index
	 *            le nouvel indice
	 */
	public void setSelectedIndex(int indice) {
		listTexteLivraison.setSelectedIndex(indice);
	}

}
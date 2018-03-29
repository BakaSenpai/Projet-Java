package hud;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import accessdb.AccessBDGen;

public class FenetrePrincipale extends JFrame {
	private Container cont;
	private JPanel panneauPrincipal;
	private JLabel labelBienvenue;
	private JMenuBar barreMenu;
	private JMenu menuConn, menuInst, menuRech;
	private JMenuItem itemConn, itemDeco, itemNouvInst, itemListInst, itemSuppInst, itemDate, itemEdit;

	public FenetrePrincipale() {
		super("Projet Java : Petit - Massin");

		this.setBounds(100, 100, 500, 500);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		barreMenu = new JMenuBar();
		setJMenuBar(barreMenu);

		menuConn = new JMenu("Connexion");
		barreMenu.add(menuConn);
		menuInst = new JMenu("Installation");
		barreMenu.add(menuInst);
		menuRech = new JMenu("Rechercher");
		barreMenu.add(menuRech);

		itemConn = new JMenuItem("Se connecter");
		menuConn.add(itemConn);
		itemDeco = new JMenuItem("Se déconnecter");
		menuConn.add(itemDeco);

		itemNouvInst = new JMenuItem("Nouvelle installation");
		menuInst.add(itemNouvInst);
		itemListInst = new JMenuItem("Liste des installation");
		menuInst.add(itemListInst);
		itemSuppInst = new JMenuItem("Supression d'une installation");
		menuInst.add(itemSuppInst);

		itemDate = new JMenuItem("Software entre 2 dates");
		menuRech.add(itemDate);
		itemEdit = new JMenuItem("Software par  un éditeur");
		menuRech.add(itemEdit);

		panneauPrincipal = new JPanel();
		labelBienvenue = new JLabel("Bienvenue dans le projet Java de Benjamin Petit et Lionel Massin");
		panneauPrincipal.add(labelBienvenue);

		cont = getContentPane();
		cont.setLayout(new BorderLayout());
		cont.add(panneauPrincipal, BorderLayout.CENTER);

		setVisible(true);

		GestionnaireAction ga = new GestionnaireAction();
		itemConn.addActionListener(ga);

	}

	private class GestionnaireAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == itemConn){ // S'il clique sur connection
				try {
					//Etablie une connection 
					Connection connection = accessdb.AccessBDGen.connecter("DbInstallations", "root", "pwMySQLie2017");
					System.out.println("ok");
					//Instruction
					String sqlInstruction = "insert into FamilleSoftware (IDFamSoft,Libelle) values (?,?)";
					//PreparedStatement
					PreparedStatement myPrepStat = connection.prepareStatement(sqlInstruction);
					
					//Remplace ??
					myPrepStat.setInt(1, 202);
					myPrepStat.setString(2, "Coucou ma biche");
					
					//EXe
					int nbUpdatedLines = myPrepStat.executeUpdate();
					System.out.println("nbligne" + nbUpdatedLines);
					
				}catch (SQLException e1){
					System.out.println(e1.getMessage());	
					JOptionPane.showMessageDialog(null, "Erreur");
				}
				
			}else{
				if (e.getSource() == itemDeco){ // S'il clique sur déconnection
					
					try {
						Connection connection;
						connection.close(); // Ferme la connection à la db
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,"Nous n'avons pas pu vous déconnecter");
					}
					
				}else {
					if (e.getSource() == itemNouvInst){ 	// S'il clique sur Nouvelle Installation
					
					}else {
						if (e.getSource() == itemListInst){  // S'il clique sur liste d'installation
							
						}else {
							if (e.getSource() == itemSuppInst){ // S'il clique sur Supprimer une Installation
								
							}else {
								if (e.getSource() == itemDate){ // S'il clique sur rechercher par un temps
									
								}else {
									if (e.getSource() == itemEdit){ // S'il clique sur rechercher par un editeur
										
									}
								}
							}
						}
					}
				}
			}
		}
	} // Ferme le gestionnaire Action
	
	
	
} // Ferme la classe


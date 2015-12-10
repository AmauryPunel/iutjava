package edu.iut.gui.frames;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.iut.app.ExamEvent;
import edu.iut.gui.listeners.*;
import edu.iut.io.*;

import edu.iut.gui.widget.agenda.AgendaPanelFactory;
import edu.iut.gui.widget.agenda.ControlAgendaViewPanel;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;

public class SchedulerFrame extends JFrame {
	JPanel contentPane;
	CardLayout layerLayout;
	AgendaPanelFactory agendaPanelFactory;
	JPanel dayView;
	JPanel weekView;
	JPanel monthView;
	public static ArrayList<ExamEvent> listEvent = new ArrayList<ExamEvent>();

	protected void setupUI() {

		contentPane = new JPanel();
		layerLayout = new CardLayout();
		contentPane.setLayout(layerLayout);
		ControlAgendaViewPanel agendaViewPanel = new ControlAgendaViewPanel(layerLayout, contentPane);
		agendaPanelFactory = new AgendaPanelFactory();
		dayView = agendaPanelFactory.getAgendaView(ActiveView.DAY_VIEW);
		weekView = agendaPanelFactory.getAgendaView(ActiveView.WEEK_VIEW);
		monthView = agendaPanelFactory.getAgendaView(ActiveView.MONTH_VIEW);

		contentPane.add(dayView, ActiveView.DAY_VIEW.name());
		contentPane.add(weekView, ActiveView.WEEK_VIEW.name());

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, agendaViewPanel, contentPane);
		this.setContentPane(splitPane);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		/* File Menu */
		menu = new JMenu("File");

		menuItem = new JMenuItem("Load");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// JOptionPane.showMessageDialog(null, "Not yet implemented",
				// "info", JOptionPane.INFORMATION_MESSAGE,null);
				JFileChooser choix = new JFileChooser();
				choix.setFileSelectionMode(JFileChooser.FILES_ONLY);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
				choix.setFileFilter(filter);
				int retour = choix.showOpenDialog(null);
				if (retour == JFileChooser.APPROVE_OPTION) {
					// un fichier a été choisi (sortie par OK)
					// nom du fichier choisi
					System.out.println("Nom du fichier :" + choix.getSelectedFile().getName());
					// chemin absolu du fichier choisi
					System.out.println("Chemin du fichier : " + choix.getSelectedFile().getAbsolutePath());
					XMLProjectReader xmlRead = new XMLProjectReader();
					try {
						listEvent = xmlRead.load(new File(choix.getSelectedFile().getAbsolutePath()));
						JOptionPane.showMessageDialog(null, "Fichier chargé", "info",JOptionPane.INFORMATION_MESSAGE, null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				} else {
					System.out.println("Pas de fichiers choisi");
				} // pas de fichier choisi

			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser choix = new JFileChooser();
				choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int retour = choix.showOpenDialog(null);
				if (retour == JFileChooser.APPROVE_OPTION) {
					// un fichier a été choisi (sortie par OK)
					// nom du fichier choisi
					System.out.println("Nom du dossier :" + choix.getSelectedFile().getName());
					// chemin absolu du fichier choisi
					System.out.println("Chemin du dossier : " + choix.getSelectedFile().getAbsolutePath());
					
					XMLProjectWriter xmlWrit = new XMLProjectWriter();
					xmlWrit.save(listEvent, new File(choix.getSelectedFile().getAbsolutePath()+"\\sauvegarde.xml"));
				
					JOptionPane.showMessageDialog(null, "Fichier sauvegardé dans le dossier "+choix.getSelectedFile().getAbsolutePath(), "info",JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					System.out.println("Pas de dossier choisi");
				} // pas de fichier choisi

				
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Quit");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Not yet implemented", "info", JOptionPane.INFORMATION_MESSAGE,
						null);
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);

		/* Edit Menu */
		menu = new JMenu("Edit");
		JMenu submenu = new JMenu("View");
		menuItem = new JMenuItem("Day");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				layerLayout.show(contentPane, ActiveView.DAY_VIEW.name());
			}
		});
		submenu.add(menuItem);
		menuItem = new JMenuItem("Week");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				layerLayout.show(contentPane, ActiveView.WEEK_VIEW.name());
			}
		});
		submenu.add(menuItem);
		menu.add(submenu);
		menuBar.add(menu);

		/* Help Menu */
		menu = new JMenu("Help");
		menuItem = new JMenuItem("Display");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// EX 4 : ajouter l'aide
				JOptionPane.showMessageDialog(null, "Not yet implemented", "info", JOptionPane.INFORMATION_MESSAGE,
						null);
			}
		});
		menu.add(menuItem);
		menuItem = new JMenuItem("About");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Not yet implemented", "info", JOptionPane.INFORMATION_MESSAGE,
						null);
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		this.pack();
		layerLayout.next(contentPane);
	}

	public SchedulerFrame() {
		super();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		contentPane = null;
		dayView = null;
		weekView = null;
		monthView = null;
		agendaPanelFactory = null;
		setupUI();

	}

	public SchedulerFrame(String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setupUI();
	}

}

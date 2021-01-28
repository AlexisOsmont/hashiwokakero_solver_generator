package hashi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class Display extends JComponent {
		
	// ATTRIBUTS
		    
	private JButton grapheFileButton;
	private JButton creationButton;
	private JLabel grapheFile;
	private JLabel ou;
	private JTextField grapheFileName;
	private GraphicHashi graphe;
	private JFileChooser chooser;
	private JButton solveButton;
	private JFrame mainFrame;
	private File f;
	private DisplayModel model;
	    
	// CONSTRUCTEURS
	    
	public Display() {
		createModel();
		createView();
	    placeComponents();
		createController();
	}
	    
	// COMMANDES
	    
	/**
	* Rend l'application visible au centre de l'écran.
	*/ 
	public void display() {
		refresh();

	    mainFrame.pack();
	    mainFrame.setLocationRelativeTo(null);
	    mainFrame.setVisible(true);
	}
	    
	// OUTILS
	    
	private void createModel() {
		graphe = new GraphicHashi();
		model = graphe.getModel();
	}
	    
		//	CREATION DES ELEMENTS
	private void createView() {
		final int frameWidth = 525;
	    final int frameHeight = 525;
	    final int nbColumns = 10;

	    mainFrame = new JFrame("Hashi");
	    mainFrame.setPreferredSize(new Dimension(frameWidth,
	      		frameHeight));
        grapheFile = new JLabel("Fichier du graphe: ");
        ou = new JLabel("ou");
	    grapheFileButton = new JButton("Parcourir...");
	    creationButton = new JButton("Générer");
	    solveButton = new JButton("Résoudre");
	    grapheFileName = new JTextField();
	    grapheFileName.setColumns(nbColumns);
	}
	
	//	PLACEMENT DES ELEMENTS
	private void placeComponents() {
		
		// -------------- NORTH --------------------
		JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER)); {
			p.add(grapheFile);
			p.add(grapheFileName);
			p.add(grapheFileButton);
			p.add(ou);
			p.add(creationButton);
		}
	   	mainFrame.add(p, BorderLayout.NORTH);
	   	
	   		// -------------- SOUTH --------------------
   		JPanel q = new JPanel(new FlowLayout(FlowLayout.CENTER)); {
        	q.add(solveButton);
		}
		mainFrame.add(q, BorderLayout.SOUTH);
		
			// -------------- CENTER --------------------
		JScrollPane pC = new JScrollPane(graphe);
		mainFrame.add(pC, BorderLayout.CENTER);
		
		mainFrame.add(graphe, FlowLayout.CENTER);
    }
   
	//	CREATION DES ACTIONS DES BUTTONS
	private void createController() {

    	mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		model.addObserver(new Observer() {
			@Override
    		public void update(Observable o, Object arg) {
  		
			}
		});
		solveButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		model.solve();
        	}
		});
	
		grapheFileName.addActionListener(new ActionListener() {
			@Override
	    	public void actionPerformed(ActionEvent e) {
				f = new File(grapheFileName.getText());
				model.changeFor(f);
			}
		});
	
		grapheFileButton.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				 chooser = new JFileChooser();
				 int v = chooser.showOpenDialog(mainFrame);
				 if (v == JFileChooser.APPROVE_OPTION) {
					 model.changeFor(chooser.
							 getSelectedFile());
				 }
			}
		});
		
		creationButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String m = JOptionPane.showInputDialog(null,
        				"Entrez une dimension entre 5 et 15", 
                        "Choisissez une taille",
                        JOptionPane.INFORMATION_MESSAGE);
        		int x = Integer.parseInt(m);
        		model.doGeneration(x);
        	}
		});
	}
	
	private void refresh() {
		if (model.getFile() != null) {
			grapheFileName.setText(model.
					getFile().getAbsolutePath());
		} else {
			graphe = null;
		}
	}
	// POINT D'ENTREE
	    
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
	    	public void run() {
				new Display().display();
	        }
	    });
	}
}

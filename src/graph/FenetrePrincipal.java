package graph;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import affrontement.Bataille;
import equipement.Epee;
import equipement.StockArmes;
import etreVivant.Homme;
import etreVivant.Orc;
import plateau.Coordonne;

public class FenetrePrincipal extends JFrame {
	
	FenetrePrincipal(){
		Bataille p = new Bataille();
		p.initialisation();
		Coordonne c = new Coordonne(3,3);
		Coordonne c1 = new Coordonne(3,1);	
		Coordonne c2 = new Coordonne(1,1);
		Coordonne c3 = new Coordonne(2,2);
		Homme Jaina = new Homme("Jaina",50);
		Homme Jainaa = new Homme("Squalala",150);
		Epee pa = new Epee("Epee des milles vérités");
		StockArmes sk = new StockArmes();
		p.setsk(sk);
		sk.ajouterArme(pa);
		Orc potiron = new Orc("lalala", 300);
		Orc pottiron = new Orc("lololo", 300);
		Jaina.rejointBataille(p, c);
		
		Jainaa.rejointBataille(p, c1);
		potiron.rejointBataille(p, c2);
		pottiron.rejointBataille(p, c3);
	setTitle("titre de la fenetre");
	setSize(1720,900);
	setResizable(true);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setLayout(null);
	
	
	JLabel lblTour = new JLabel("noir");
	lblTour.setBounds(675,50,200,25);
	lblTour.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,18));
	Plateau  plt = new Plateau(p);
	plt.setBounds(10,10,1000,1000);
	add(plt);
	JPanel panel = new JPanel();
	panel.addMouseListener(new MouseAdapter() {
		@Override 
	    public void mousePressed(MouseEvent e) {
	      System.out.println(e.getX() + "," + e.getY());
	    }
	});
	
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				new FenetrePrincipal().setVisible(true);
				
			}
		});
	}
}

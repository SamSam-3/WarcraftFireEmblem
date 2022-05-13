package graph;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import affrontement.Bataille;
import plateau.Case;

public class Plateau extends JComponent implements MouseListener {
	private Bataille bataille;
	public Bataille getBataille() {
		return bataille;
	}
	public void setBataille(Bataille bataille) {
		this.bataille = bataille;
	}
	Plateau(Bataille p)
	{
		this.setBataille(p);
		
	}
	protected void paintComponent(Graphics g) {
		ImageIcon imgCase = new ImageIcon(getClass().getResource("/images/plaineBord.png"));
		ImageIcon imgCase2 = new ImageIcon(getClass().getResource("/images/personnage.png"));
		Graphics2D g2 = (Graphics2D) g;
		int CASE_DIM = 50;
		int i = 0;
		for(Case b : this.getBataille().getPt())
		{
			System.out.print(""+ i);
			imgCase.paintIcon(null,g2, (b.getPosition().getX()+1)*CASE_DIM,  (b.getPosition().getY()+1)*CASE_DIM);
			if(b.getOccupant() != null) {
				imgCase2.paintIcon(null,g2, (b.getPosition().getX()+1)*CASE_DIM,  (b.getPosition().getY()+1)*CASE_DIM);
			}
			i++;
		}
		addMouseListener(this);
		g2.dispose();
 	}
	@Override
    public void mouseClicked( MouseEvent e )
    {
          // Clique gauche de la souris
           if(e.getButton()==MouseEvent.BUTTON1)
           {
                // Récupération de la position
                Point position = e.getPoint();
                JLabel lblCamp = new JLabel("Tour de jeu : "+ position.x +"/"+position.y );
            	lblCamp.setBounds(500,50,200,25);
            	lblCamp.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
            	add(lblCamp);
            	this.setVisible(true);
            	
           }}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

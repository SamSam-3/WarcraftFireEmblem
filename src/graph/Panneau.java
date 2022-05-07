package graph;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class Panneau extends JPanel implements MouseListener {
 
	@Override
	public void paintComponent(final Graphics g) {
 
		try {
			Image img = ImageIO.read(new File("image.jpg"));
			this.setSize(1366, 768);
			g.drawImage(img, 0, 0, null);
 
		} catch (IOException e) {
 
			e.printStackTrace();
		}
 
		addMouseListener(this);
 
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
	            }
	    }

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
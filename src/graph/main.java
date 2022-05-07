package graph;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.*; 
import javax.swing.*;
 
public class main {
  public static void main(String[] argv)
  {  
  JFrame f = new JFrame();
  f.setSize(1000, 1000);
  JPanel panel = new JPanel();
  panel.setSize(1000,1000);
  f.add(panel);
  panel.addMouseListener(new MouseAdapter() {
    @Override 
    public void mousePressed(MouseEvent e) {
      System.out.println(e.getX() + "," + e.getY());
   // Récupération de la position
      Point position = e.getPoint();
      JLabel lblCamp = new JLabel("Tour de jeu : "+ position.x +"/"+position.y );
  	lblCamp.setBounds(50,50,200,50);
  	lblCamp.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
  	f.add(lblCamp);
    }
  });
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  f.setSize(200, 200);
  f.setVisible(true);
  }
}
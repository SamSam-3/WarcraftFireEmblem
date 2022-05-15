package plateau;

import affrontement.Bataille;
import etreVivant.EtreVivant;
import etreVivant.Homme;
import etreVivant.Orc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class Plateau extends Canvas implements MouseListener{
    private Bataille bataille;
    final int NBCASE = 21;
    public Plateau(Bataille bataille){
        this.bataille = bataille;
        setSize(840,840);
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {

        Image image = null;

        // Dessine le plateau de jeu
        for(int i=0;i<21;i++){ //21 cases en y
            for(int j=0;j<21;j++){ // 21 cases en x

                try {
                    image = ImageIO.read(getClass().getResourceAsStream("../images/plaineBord.png")); // Set l'image du plateau
                } catch (IOException e) {
                    e.printStackTrace();
                }

                g.drawImage(image, j*40, i*40,40,40,this); // (Re)dessine l'image sur le canvas

                // Récupère la case qui correspond
                Case selectedCase = this.bataille.getPt().get(i*NBCASE+j);

                if(selectedCase.getOccupant() != null) {
                    System.out.println(selectedCase.getOccupant() instanceof Homme);
                    if(selectedCase.getOccupant() instanceof Homme) { // Si c'est un homme
                        try {
                            image = ImageIO.read(getClass().getResourceAsStream("../images/homme.png")); // Set l'image homme
                        } catch (IOException e) {
                        e.printStackTrace();
                    }
                    } else {
                        try {
                            image = ImageIO.read(getClass().getResourceAsStream("../images/orc.png")); // Set l'image orc
                        } catch (IOException e) {
                        e.printStackTrace();
                    }
                    }

                    g.drawImage(image, j*40, i*40,40,40,this); // (Re)dessine l'image sur le canvas
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX()/40;
        int y = e.getY()/40;

        if(0 <= x & x <= 20 & 0 <= y & y <= 20){ //Si x et y sont bien dans le plateau de jeu
            Case selectedCase = this.bataille.getPt().get(y*NBCASE+x); //récupère les infos de la case
            if(selectedCase.getOccupant() != null){ // Si la case appartient à un être vivant
                // Afficher actions
                EtreVivant occupant = selectedCase.getOccupant();

                System.out.println("~~>  "+occupant.getNom());
                System.out.println("Vie : "+occupant.getVie()+"\nEnergie : "+occupant.getMouvement());

            } else {
                System.out.println("Cette case n'appartient à personne");
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

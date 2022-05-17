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
    private InfoCaracter infos;
    final int NBCASE = 10;
    public Plateau(Bataille bataille, InfoCaracter infos){
        this.infos = infos;
        this.bataille = bataille;
        setSize(600,600);
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        System.out.println(this.bataille.getPt().size());
        Image image = null;

        // Dessine le plateau de jeu
        for(int i=0;i<10;i++){ // 10 cases en y
            for(int j=0;j<10;j++){ // 10 cases en x

                try {
                    image = ImageIO.read(getClass().getResourceAsStream("../images/plaineBord.png")); // Set l'image du plateau
                } catch (IOException e) {
                    e.printStackTrace();
                }

                g.drawImage(image, j*60, i*60,60,60,this); // (Re)dessine l'image sur le canvas

                // Récupère la case qui correspond
                Case selectedCase = this.bataille.getPt().get(i*NBCASE+j);

                if(selectedCase.getOccupant() != null) {

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

                    g.drawImage(image, j*60, i*60,60,60,this); // (Re)dessine l'image sur le canvas
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
        int x = e.getX()/60;
        int y = e.getY()/60;

        System.out.println(e.getButton());

        if(0 <= x & x <= 9 & 0 <= y & y <= 9){ //Si x et y sont bien dans le plateau de jeu
            Case selectedCase = this.bataille.getPt().get(y*NBCASE+x); //récupère les infos de la case
            if(selectedCase.getOccupant() != null){ // Si la case appartient à un être vivant
                // Afficher actions
                EtreVivant occupant = selectedCase.getOccupant();
                infos.updateCaracter(occupant);

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


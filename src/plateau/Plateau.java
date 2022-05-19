package plateau;

import affrontement.Bataille;
import etreVivant.EtreVivant;
import etreVivant.Homme;
import etreVivant.Orc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Plateau extends Canvas implements MouseListener{
    private Bataille bataille;
    private InfoCaracter infos;
    private JTextField tour;
    final int NBCASE = 10;
    private EtreVivant occupant;
    private boolean ennemyChoosing = false;
    public Plateau(Bataille bataille, InfoCaracter infos, JTextField tour){
        this.infos = infos;
        this.bataille = bataille;
        this.tour = tour;
        setSize(600,600);
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        this.tour.setText(bataille.getTour());
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
        int x = e.getX() / 60;
        int y = e.getY() / 60;

        System.out.println(bataille.getTour());


        if (e.getButton() == 3) {
            if (0 <= x & x <= 9 & 0 <= y & y <= 9) { //Si x et y sont bien dans le plateau de jeu
                Case selectedCase = this.bataille.getPt().get(y * NBCASE + x); //récupère les infos de la case
                try {
                    occupant.sedeplacer(new Coordonne(x, y));
                } catch (NullPointerException neo){
                    neo.printStackTrace();
                }
                    /*
                if(selectedCase.getOccupant() != null){ // Si la case appartient à un être vivant
                    // Afficher actions

                    if(selectedCase.getOccupant() instanceof  Homme){
                        if(occupant instanceof Orc){
                            occupant.attaquer(selectedCase.getOccupant()); //Attaque l'occupant de la case
                            System.out.print(occupant.getNom());
                        }
                    } else {
                        if(occupant instanceof Homme){
                            occupant.attaquer(selectedCase.getOccupant()); //Attaque l'occupant de la case
                            System.out.print(occupant.getNom());
                        }
                    }
                } else {
                    System.out.println("Cette case est vide");
                    occupant.sedeplacer(new Coordonne(x,y));
                }
            }
            */
                occupant = null;
                this.repaint();
            }
            }

            if (e.getButton() == 1) {
                if (0 <= x & x <= 9 & 0 <= y & y <= 9) { //Si x et y sont bien dans le plateau de jeu
                    Case selectedCase = this.bataille.getPt().get(y * NBCASE + x); //récupère les infos de la case
                    if (selectedCase.getOccupant() != null) { // Si la case appartient à un être vivant
                        // Afficher actions
                        occupant = selectedCase.getOccupant();
                        infos.updateCaracter(occupant);

                    /*if(occupant instanceof Homme){
                        if(Objects.equals(bataille.getTour(), "Homme")){
                            System.out.print("Homme "+ occupant.getNom());
                        }
                        else {
                            System.out.println("Ce n'est pas votre tour ");
                        }

                    } else {
                        if(Objects.equals(bataille.getTour(), "Orc")){
                            System.out.print("Orc "+ occupant.getNom()+" VS ");
                        }
                        else {
                            System.out.println("Ce n'est pas votre tour ");
                        }
                    }*/
                    } else {
                        System.out.println("Cette case n'appartient à personne");
                        occupant = null;
                    }
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


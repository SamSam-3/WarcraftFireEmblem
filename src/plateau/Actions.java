package plateau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Actions {

    private JPanel frame;
    public JButton annuler;
    public JButton attack;
    public JButton inventaire;
    public Actions(){

        Dimension dim = new Dimension(150,30);

        this.frame = new JPanel();

        JButton annuler = new JButton();
        annuler.setText("Annuler");
        annuler.setSize(dim);
        annuler.setVisible(false);
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annuler.setVisible(!annuler.isVisible());

            }
        });

        // Bouton d'attaque
        JButton attack = new JButton();
        attack.setText("Attaquer");
        attack.setSize(dim);
        attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annuler.setVisible(!annuler.isVisible());
            }
        });

        //Bouton d'inventaire
        JButton inventaire = new JButton();
        inventaire.setText("Inventaire");
        inventaire.setSize(dim);



        this.frame.setSize(200,840);
        this.frame.setBackground(Color.gray);
        this.frame.setVisible(true);

        Box box = Box.createVerticalBox();
        box.add(attack);
        box.add(inventaire);
        box.add(annuler);

        this.frame.add(box, BorderLayout.NORTH);

    }
    public JPanel setPanel(){
        return this.frame;
    }


}

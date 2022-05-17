package plateau;

import etreVivant.EtreVivant;

import javax.swing.*;
import java.awt.*;

public class InfoCaracter {

    private JPanel frame;
    private Box box;

    public JButton annuler;
    public JTextField Cname = new JTextField("aaaaaaaaaaaa"); // Caracter's name
    public JTextField pv = new JTextField("aaaaaaaaaaaa");
    public JTextField stamina = new JTextField("aaaaaaaaaaaa");
    public InfoCaracter(){

        Dimension dim = new Dimension(150,50);

        this.frame = new JPanel();

        JButton annuler = new JButton();
        annuler.setText("Annuler");
        annuler.setSize(dim);
        annuler.setVisible(false);

        this.frame.setSize(400,600);
        this.frame.setBackground(Color.gray);
        this.frame.setVisible(true);

        box = Box.createVerticalBox();

        box.add(Cname);
        box.add(pv);
        box.add(stamina);

        box.add(annuler);

        this.frame.add(box, BorderLayout.NORTH);

    }
    public JPanel setPanel(){
        return this.frame;
    }

    public void updateCaracter(EtreVivant combattant){
        Cname.setText(combattant.getNom());
        pv.setText(String.valueOf(combattant.getVie()));
        stamina.setText(String.valueOf(combattant.getMouvement()));
    }

}

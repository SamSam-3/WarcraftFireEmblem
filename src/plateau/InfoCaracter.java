package plateau;

import etreVivant.EtreVivant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InfoCaracter {

    private JPanel frame;
    private Box box;

    public JButton annuler;
    public JTextField Cname = new JTextField("            "); // Caracter's name
    public JTextField pv = new JTextField("            ");
    public JTextField stamina = new JTextField("            ");
    public InfoCaracter(){

        Dimension dim = new Dimension(150,50);

        this.frame = new JPanel();

        annuler = new JButton();
        annuler.setText("Annuler");
        annuler.setSize(dim);
        annuler.setVisible(false);

        this.frame.setSize(400,600);
        this.frame.setBackground(Color.gray);
        this.frame.setVisible(true);

        box = Box.createVerticalBox();
        box.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        Cname.setSize(dim);
        pv.setSize(dim);
        stamina.setSize(dim);

        Cname.setFont(new Font("Arial",Font.BOLD, 20));
        Cname.setHorizontalAlignment(JTextField.CENTER);

        pv.setFont(new Font("Arial",Font.BOLD, 20));
        pv.setHorizontalAlignment(JTextField.CENTER);

        stamina.setFont(new Font("Arial",Font.BOLD, 20));
        stamina.setHorizontalAlignment(JTextField.CENTER);

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
        try {
            Cname.setText(combattant.getNom());
            pv.setText(String.valueOf(combattant.getVie()));
            stamina.setText(String.valueOf(combattant.getMouvement()));
            annuler.setVisible(true);

        } catch (NullPointerException neo){
            Cname.setText("            ");
            pv.setText("            ");
            stamina.setText("            ");
            annuler.setVisible(false);
        }
    }

    public boolean annuler(){
        annuler.setVisible(false);
        return false;
    }

}

package Tests;

import affrontement.Bataille;
import plateau.InfoCaracter;
import plateau.Plateau;

import javax.accessibility.Accessible;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class TestGUI extends JComponent implements Accessible {

    public TestGUI(Bataille bataille) {
        JFrame f = new JFrame();

        JPanel panel1 = new JPanel(); // Le plateau de jeu
        JTextField tour = new JTextField();
        tour.setMaximumSize(new Dimension(400,100));
        tour.setText("Tour : Orc");
        tour.setFont(new Font("Arial",Font.BOLD, 30));
        tour.setHorizontalAlignment(JTextField.CENTER);
        tour.setAlignmentX(Component.CENTER_ALIGNMENT);

        InfoCaracter infos = new InfoCaracter();
        JPanel panel2 = infos.setPanel(); // La bar des actions

        panel1.add(new Plateau(bataille,infos,tour));

        f.setSize(1100, 900);
        f.setTitle("Plateau");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBackground(Color.blue);
        f.setLocationRelativeTo(null);
        f.setResizable(false);

        Box box = Box.createHorizontalBox();
        Box boxplateau = Box.createVerticalBox();

        boxplateau.add(tour);
        boxplateau.add(panel1);

        box.add(boxplateau);
        box.add(panel2);

        f.add(box, BorderLayout.CENTER);

    }


    public static void main(String[] args) throws IOException {

        Bataille p = new Bataille();
        Path chemin = Paths.get("sauvegarde.txt");
        p.reprise(chemin);
        p.passerletour();

        TestGUI test1 = new TestGUI(p);
    }
}
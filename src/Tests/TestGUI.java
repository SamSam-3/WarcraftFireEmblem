package Tests;

import affrontement.Bataille;
import org.w3c.dom.events.MouseEvent;
import plateau.Actions;
import plateau.Plateau;

import javax.accessibility.Accessible;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class TestGUI extends JComponent implements Accessible {

    public TestGUI(Bataille bataille){
        JFrame f = new JFrame();

        JPanel panel1 = new JPanel(); // Le plateau de jeu
        JPanel panel2 = new JPanel(); // La bar des actions

        f.setSize(1280,900);
        f.setTitle("Plateau");
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBackground(Color.blue);
        f.setLocationRelativeTo(null);
        f.setResizable(false);

        panel1.add(new Plateau(bataille));
        panel1.setBounds(200,200,840,840);

        panel2.add(new Actions());
        panel2.setBounds(860,860,1240,1240);

        f.add(panel1, BorderLayout.WEST);
        f.add(panel2, BorderLayout.EAST);
    }


    public static void main(String[] args) throws IOException {


        Bataille p = new Bataille();
        Path chemin = Paths.get("sauvegarde.txt");
        p.reprise(chemin);

        TestGUI test1 = new TestGUI(p);
    }
}
package graph;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.*;

public class swing1 extends JFrame {

 
      public static void main(String[] args) 
      {
          // Définissez le frame
          JFrame frame = new JFrame("Hello World");
          //L'en-têtes du JTable
          String[] column = {"ID", "Nom", "Age", "Adresse"};
      
          //Les lignes du JTable
          String[][] data = {
                  {"01", "Thomas", "55", "Paris"}, 
                  {"02", "Emily", "45", "Marseille"}, 
                  {"03", "Yohan", "12", "Lyon"}, 
                  {"04", "Jean", "29", "Toulouse"},
                  {"05", "Bob", "30", "Nice"}
          };
          
          // Créer le JTable
          JTable table = new JTable(data, column);
          JScrollPane scroll = new JScrollPane(table); 
          frame.add(scroll);
  
          
          JPanel panel = new JPanel();
          JMenuBar menu = new JMenuBar();
          JMenu file = new JMenu("Fichier");
          JMenu edit = new JMenu("Edition");
          JMenu help = new JMenu("Aide");
           
          // Définir le sous-menu pour Fichier
          JMenuItem newf = new JMenuItem("Nouveau");
          JMenuItem quit = new JMenuItem("Ouvrir");
          JMenuItem save = new JMenuItem("Enregistrer");
   
          file.add(newf);
          file.add(save);
          file.add(quit);
           
          menu.add(file);
          menu.add(edit);
          menu.add(help);
           
      
      
          frame.add(menu);
          frame.add(panel);
          frame.setLayout(new GridLayout(3,2));
          frame.pack();
          frame.setSize(1000, 1000);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setVisible(true);
      }
}

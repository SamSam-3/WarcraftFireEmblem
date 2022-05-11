package plateau;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;

public class Plateau extends Canvas {

    public Plateau(){
        setSize(840,840);
    }

    @Override
    public void paint(Graphics g) {

        Image image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("../images/plaineBord.png")); //Set l'image du plateau
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<21;i++){ //21 cases en y
            for(int j=0;j<21;j++){ // 21 cases en x
                g.drawImage(image, j*40, i*40,40,40,this); // Dessine l'image sur le canvas
            }
        }

    }
}

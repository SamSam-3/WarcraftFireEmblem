package Tests;

import org.w3c.dom.events.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class TestGUI extends Frame {

    public TestGUI(){

        setSize(840,840); // Block de 40*40
        setTitle("Plateau");
        setVisible(true);

        Canvas canvas = new Canvas();
        canvas.setSize(this.getSize());
        canvas.setVisible(true);

        canvas.repaint();
    }

    @Override
    public void paint(Graphics g) {
        Image image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("../images/plaineBord.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<21;i++){
            for(int j=0;j<21;j++){
                g.drawImage(image, j*40, i*40,40,40,this);
            }
        }

    }

    public static void main(String[] args) {

        TestGUI test1 = new TestGUI();
    }
}
import java.awt.*;

public class MyCanvas extends Canvas {

    public void paint(Graphics g) {

        Toolkit t=Toolkit.getDefaultToolkit();
        Image i=t.getImage("aaa.png");
        g.drawImage(i, 260,300,this);

    }
}
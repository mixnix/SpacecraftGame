import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;

/**
 * Created by user_name on 20.02.2017.
 */
public class Craft extends Sprite {
    private int dx;
    private int dy;
    private ArrayList<Missile> missiles;

    public Craft(int x, int y){
        super(x, y);

        initCraft();
    }

    private void initCraft(){
        missiles = new ArrayList();
        loadImage("craft.png");
        getImageDimension();
    }

    public void move(){
        x += dx;
        y += dy;
    }

    public ArrayList getMissiles(){
        return missiles;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Image getImage(){
        return image;
    }

    public void fire(){
        missiles.add(new Missile(x + width, y + height / 2));
    }
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){
            dx = -1;
        }

        if(key == KeyEvent.VK_RIGHT){
            dx = 1;
        }

        if(key == KeyEvent.VK_UP){
            dy = -1;
        }

        if(key == KeyEvent.VK_DOWN){
            dy = 1;
        }

        if(key == KeyEvent.VK_SPACE){
            fire();
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){
            dx = 0;
        }

        if(key == KeyEvent.VK_RIGHT){
            dx = 0;
        }

        if(key == KeyEvent.VK_UP){
            dy = 0;
        }

        if(key == KeyEvent.VK_DOWN){
            dy = 0;
        }
    }
}

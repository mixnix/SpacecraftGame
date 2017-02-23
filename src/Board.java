import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by user_name on 20.02.2017.
 */
public class Board extends JPanel implements ActionListener {

    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 15;
    private Timer timer;
    private Craft craft;
    private ArrayList<Alien> aliens;
    private boolean ingame;

    private final int[][] pos = {
            {2380, 29},{2500, 59},{1380,89},
            {890, 109},{580, 139},{680, 239},
            {790, 259}, {760, 50}, {790, 150},
            {980, 209}, {560, 45}, {510, 70},
            {930, 159}, {590, 80}, {530, 60},
            {940, 59}, {990, 30}, {920, 200},
            {900, 259}, {660, 50}, {540, 90},
            {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30}
    };

    public Board(){
        initBoard();
    }

    private void initBoard(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        craft = new Craft(ICRAFT_X, ICRAFT_Y);

        //aliens initialisation
        aliens = new ArrayList<Alien>();
        for(int[] p : pos){
            aliens.add(new Alien(p[0],p[1]));
        }

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(ingame){
            //draw objects normally
            if(craft.isVisible()){
                g.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
            }

            ArrayList<Missile> ms = craft.getMissiles();

            for(Missile m : ms){
                if(m.isVisible()){
                    g.drawImage(m.getImage(), m.getX(), m.getY(), this);
                }
            }

            for(Alien a : aliens){
                if(a.isVisible()){
                    g.drawImage(a.getImage(), a.getX(), a.getY(), this);
                }
            }
            g.setColor(Color.white);
            g.drawString("Aliens left: " + aliens.size(), 5, 15);
        }else{
            //draw Game Over
            String msg = "Game Over";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics fm = getFontMetrics(small);

            g.setColor(Color.white);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - fm.stringWidth(msg))/2, B_HEIGHT / 2);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(!ingame){
            timer.stop();
        }

        //updates missiles
        ArrayList<Missile> ms = craft.getMissiles();
        for(int i = 0; i < ms.size(); i++){
            Missile m = ms.get(i);

            if(m.isVisible()){
                m.move();
            }else {
                ms.remove(i);
            }
        }

        //updates craft
        if(craft.isVisible()){
            craft.move();
        }

        //updates aliens
        if(aliens.isEmpty()){
            ingame = false;
        }else{
            for (int i = 0; i < aliens.size(); i++){
                Alien a = aliens.get(i);
                if(a.isVisible()){
                    a.move();
                }else{
                    aliens.remove(i);
                }
            }
        }

        //check collisions
        Rectangle r3 = craft.getBounds();
        for(Alien alien : aliens){
            Rectangle r2 = alien.getBounds();

            if(r3.intersects(r2)){
                craft.setVisible(false);
                alien.setVisible(false);
                ingame = false;
            }
        }
        ArrayList<Missile> ms1 = craft.getMissiles();
        for(Missile m : ms1){
            Rectangle r1 = m.getBounds();

            for(Alien alien : aliens){
                Rectangle r2 = alien.getBounds();
                if(r1.intersects(r2)){
                    m.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e){
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e){
            craft.keyPressed(e);
        }
    }
}

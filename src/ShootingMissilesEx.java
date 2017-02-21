import javax.swing.*;
import java.awt.*;

/**
 * Created by user_name on 21.02.2017.
 */
public class ShootingMissilesEx extends JFrame{
    public ShootingMissilesEx(){
        initUI();
    }

    private void initUI(){
        add(new Board());

        setSize(400,300);
        setResizable(false);

        setTitle("Shooting missiles");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ShootingMissilesEx ex = new ShootingMissilesEx();
                ex.setVisible(true);
            }
        });
    }
}

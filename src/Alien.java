/**
 * Created by user_name on 21.02.2017.
 */
public class Alien extends Sprite {
    private final int INITIAL_X = 400;

    public Alien(int x, int y){
        super(x, y);

        initAlien();
    }

    private void initAlien(){
        loadImage("alien.png");
        getImageDimension();
    }

    public void move(){
        if(x < 0){
            x = INITIAL_X;
        }

        x -= 1;
    }
}

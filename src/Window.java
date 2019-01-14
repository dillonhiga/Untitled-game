import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    private static final long serialVersionUID = -8255319694373975038L;

    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //Starts game in the middle of the screen instead of the top Corner
        frame.setLocationRelativeTo(null);
        //adding game class into our frame
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}

import java.awt.*;

public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = -6112428091888191314L;
    //16:9 Ratio
    public static final int WIDTH = 640, HEIGHT = WIDTH/12 * 9;
    private Thread thread;
    private boolean running = false;

    public Game(){
        new Window(WIDTH, HEIGHT,"Untitled", this);
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
    new Game();
    }


    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try{
            thread.join();      //ends thread
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

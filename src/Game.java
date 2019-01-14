import java.awt.*;
import java.awt.image.BufferStrategy;

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
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if ((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render(){
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if(bufferStrategy == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.red);
        graphics.fillRect(0,0,WIDTH,HEIGHT);


        graphics.dispose();
        bufferStrategy.show();
    }

    private void tick(){

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

// import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
// import java.awt.event.KeyListener;

// import java.awt.image.BufferedImage;
// import java.security.Key;

public class Game implements Runnable {  
    private Display display;
    public String title;
    private int width, height; 
    
    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g; 

    //STATES
    private State gameState;
    private State menuState;

    //Input
    private KeyManager keyManager;
    
    //Camera
    private GameCamera gameCamera; 

    //Handler
    private Handler handler;

    public Game(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
    }
    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        handler =  new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(gameState);
    }

    private void tick()/*upDate*/ {
        keyManager.tick();

        if (State.getState() != null)
        State.getState().tick();
    }

    private void render() {

        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here !
        if (State.getState() != null)
        State.getState().render(g);
        // g.drawImage(Assets.rock, 20, 20, null);
        //End Here !
        bs.show();
        g.dispose();

    }
    public void run() {

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        int timer = 0;
        int ticks = 0;

        while(running) {

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                System.out.println("Ticks and Frames" + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop() {
        if (!running)
            return;
        thread = new Thread();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
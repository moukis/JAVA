import java.awt.Graphics;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
        player = new Player(handler, 100, 100);


    }
    @Override
    public void tick() {
        world.tick();
        player.tick();
    }
    
    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
        // Tile.tiles[1].render(g, 0, 0); 
        // g.drawImage(Assets.fire, 0, 0, null);
    }

}
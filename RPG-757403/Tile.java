import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile rockTile = new RockTile(1);
    public static Tile treeTile = new TreeTile(2);
    public static Tile fenceTile = new FenceTile(3);
    public static Tile paveTile = new PaveTile(4);
    public static Tile fireTile = new FireTile(5);

public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick() {

    }
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public int getId() {
        return id;
    }

    public boolean isSolid() {
        return false;
    }
}

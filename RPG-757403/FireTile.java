public class FireTile extends Tile {

    public FireTile(int id) {
        super(Assets.fire, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
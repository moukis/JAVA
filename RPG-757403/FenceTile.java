public class FenceTile extends Tile {

    public FenceTile(int id) {
        super(Assets.fence, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
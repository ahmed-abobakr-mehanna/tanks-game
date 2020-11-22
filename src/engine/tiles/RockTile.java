package engine.tiles;

import engine.gfx.Assests;

public class RockTile  extends Tile {

    public RockTile(int id) {
        super(Assests.rock, id);
    }

    @Override
    public boolean isSolid () {
        return true;
    }
}

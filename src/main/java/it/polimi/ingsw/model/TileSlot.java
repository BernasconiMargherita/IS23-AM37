package it.polimi.ingsw.model;

public class TileSlot {
    Tile assignedTile;

    public void AssignTile(Tile assignedTile) {
        this.assignedTile = assignedTile;
    }

    public boolean IsFree(){
        return this.assignedTile != null;
    }
}

package it.polimi.ingsw.model;

public class TileSlot {
    private Tile assignedTile;
    private boolean free=true;

    public void AssignTile(Tile assignedTile) {
        this.assignedTile = assignedTile;
        this.free=false;
    }

    public boolean IsFree(){
        return this.free;
    }

    public Tile getAssignedTile() {
        return assignedTile;
    }

    public void RemoveAssignedTile(){
        this.assignedTile=null;
        this.free=true;
    }
}

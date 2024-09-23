package chess;

import java.util.List;
import java.util.Set;

abstract public class ChessPiece {
    private Boolean color;
    private Boolean moved = false;
    private String position;
    public ChessPiece(Boolean color, String position) {
        this.color = color;
        this.position = position;
    }
    abstract public Set<String> possibleMoves(List<ChessPiece> allPieces);

    public Boolean getColor() {
        return color;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getMoved() {
        return moved;
    }

    public void setMoved(Boolean moved) {
        this.moved = moved;
    }

    @Override
    abstract public String toString();

    public static int[] positionToint(String position){
        int[] ij = new int[2];
        ij[0] = Integer.parseInt(position.split("_")[0]);
        ij[1] = Integer.parseInt(position.split("_")[1]);
        return ij;
    }
}

package chess;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Knight extends ChessPiece{
    public Knight(Boolean color, String position) {
        super(color, position);
    }

    @Override
    public Set<String> possibleMoves(List<ChessPiece> allPieces) {
        Set<String> possible = new HashSet<>();
        int[] current = ChessPiece.positionToint(this.getPosition());
        possible.add((current[0]-2)+"_"+(current[1]+1));
        possible.add((current[0]-2)+"_"+(current[1]-1));
        possible.add((current[0]+2)+"_"+(current[1]+1));
        possible.add((current[0]+2)+"_"+(current[1]-1));
        possible.add((current[0]+1)+"_"+(current[1]+2));
        possible.add((current[0]-1)+"_"+(current[1]+2));
        possible.add((current[0]+1)+"_"+(current[1]-2));
        possible.add((current[0]-1)+"_"+(current[1]-2));
        for (ChessPiece one:allPieces) {
            if(possible.contains(one.getPosition()) && one.getColor() == this.getColor()){
                possible.remove(one.getPosition());
            }
        }
        possible.removeIf(i -> ChessPiece.positionToint(i)[0] < 0 || ChessPiece.positionToint(i)[0] > 7 || ChessPiece.positionToint(i)[1] < 0 || ChessPiece.positionToint(i)[1] > 7);
        return possible;
    }
    @Override
    public String toString() {
        return (this.getColor())? "\u265E":"\u2658";
    }
}

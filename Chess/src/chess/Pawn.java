package chess;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pawn extends ChessPiece{
    String startPosition;

    public Pawn(Boolean color, String position) {
        super(color, position);
        startPosition = this.getPosition();
    }

    @Override
    public Set<String> possibleMoves(List<ChessPiece> allPieces) {
        Set<String> possible = new HashSet<>();
        int[] current = ChessPiece.positionToint(this.getPosition());
        int plus = (ChessPiece.positionToint(startPosition)[0] == 1)? 1:-1;

        if(current[0]+plus >= 0 && current[0]+plus <= 7){
            possible.add(current[0]+plus+"_"+current[1]);
        }
        if(!this.getMoved()){
            possible.add(current[0]+2*plus+"_"+current[1]);
        }

        for (ChessPiece one:allPieces) {
            possible.remove(one.getPosition());
            if(one.getColor() != this.getColor() && (one.getPosition().equals((current[0]+plus)+"_"+(current[1]-1)) || one.getPosition().equals((current[0]+plus)+"_"+(current[1]+1)))){
                possible.add(one.getPosition());
            }
        }
        if(possible.size() == 0 && (current[0] == 0 || current[0] == 7)){
            possible.add("9_9");
        }
        return possible;
    }

    @Override
    public String toString() {
        return (this.getColor())? "\u265F":"\u2659";
    }
}
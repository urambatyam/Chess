package chess;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bishop extends ChessPiece{
    public Bishop(Boolean color, String position) {
        super(color, position);
    }

    @Override
    public Set<String> possibleMoves(List<ChessPiece> allPieces) {
        Set<String> possible = new HashSet<>();
        int[] current = ChessPiece.positionToint(this.getPosition());

        for (int i = current[0], j = current[1]; i >= 0 && j >=0; i--,j--) {
            possible.add(i+"_"+j);
        }
        for (int i = current[0], j = current[1]; i <= 7 && j <=7; i++,j++) {
            possible.add(i+"_"+j);
        }
        for (int i = current[0], j = current[1]; i >= 0 && j <=7; i--,j++) {
            possible.add(i+"_"+j);
        }
        for (int i = current[0], j = current[1]; i <= 7 && j >= 0; i++,j--) {
            possible.add(i+"_"+j);
        }

        possible.remove(this.getPosition());

        for (ChessPiece one:allPieces) {
            if(possible.contains(one.getPosition())){
                if(ChessPiece.positionToint(one.getPosition())[0] > current[0] && ChessPiece.positionToint(one.getPosition())[1] > current[1]){
                    for (int i = ChessPiece.positionToint(one.getPosition())[0]+1,j=ChessPiece.positionToint(one.getPosition())[1]+1; i <= 7 && j <= 7; i++,j++) {
                        possible.remove(i+"_"+j);
                    }
                } else if (ChessPiece.positionToint(one.getPosition())[0] > current[0] && ChessPiece.positionToint(one.getPosition())[1] < current[1]) {
                    for (int i = ChessPiece.positionToint(one.getPosition())[0]+1,j=ChessPiece.positionToint(one.getPosition())[1]-1; i <= 7 && j >= 0; i++,j--) {
                        possible.remove(i+"_"+j);
                    }
                } else if (ChessPiece.positionToint(one.getPosition())[0] < current[0] && ChessPiece.positionToint(one.getPosition())[1] > current[1]) {
                    for (int i = ChessPiece.positionToint(one.getPosition())[0]-1,j=ChessPiece.positionToint(one.getPosition())[1]+1; i >= 0 && j <= 7; i--,j++) {
                        possible.remove(i+"_"+j);
                    }
                } else if (ChessPiece.positionToint(one.getPosition())[0] < current[0] && ChessPiece.positionToint(one.getPosition())[1] < current[1]) {
                    for (int i = ChessPiece.positionToint(one.getPosition())[0]-1,j=ChessPiece.positionToint(one.getPosition())[1]-1; i >= 0 && j >= 0; i--,j--) {
                        possible.remove(i+"_"+j);
                    }
                }
                if(one.getColor() == this.getColor()){
                    possible.remove(one.getPosition());
                }
            }
        }
        return possible;
    }

    @Override
    public String toString() {
        return (this.getColor())? "\u265D":"\u2657";
    }
}

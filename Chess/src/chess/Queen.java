package chess;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Queen extends ChessPiece{
    public Queen(Boolean color, String position) {
        super(color, position);
    }

    @Override
    public Set<String> possibleMoves(List<ChessPiece> allPieces) {
        Set<String> possible = new HashSet<>();
        int[] current = ChessPiece.positionToint(this.getPosition());

        for (int i = 0; i < 8; i++) {
            possible.add(i+"_"+current[1]);
            possible.add(current[0]+"_"+i);
        }

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

        for (ChessPiece one:allPieces) {
            if(possible.contains(one.getPosition())){
                if(possible.contains(one.getPosition())){
                    if(current[0] < ChessPiece.positionToint(one.getPosition())[0]){
                        for (int i = 7; i > ChessPiece.positionToint(one.getPosition())[0]; i--) {
                            possible.remove(i+"_"+current[1]);
                        }
                    }else if(current[0] > ChessPiece.positionToint(one.getPosition())[0]){
                        for (int i = 0; i < ChessPiece.positionToint(one.getPosition())[0]; i++) {
                            possible.remove(i+"_"+current[1]);
                        }
                    }else if(current[1] < ChessPiece.positionToint(one.getPosition())[1]){
                        for (int i = 7; i > ChessPiece.positionToint(one.getPosition())[1]; i--) {
                            possible.remove(current[0]+"_"+i);
                        }
                    }else if(current[1] > ChessPiece.positionToint(one.getPosition())[1]){
                        for (int i = 0; i < ChessPiece.positionToint(one.getPosition())[1]; i++) {
                            possible.remove(current[0]+"_"+i);
                        }
                    }

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
            }}
        }
        possible.remove(this.getPosition());
        return possible;
    }

    @Override
    public String toString() {
        return (this.getColor())? "\u265B":"\u2655";
    }
}

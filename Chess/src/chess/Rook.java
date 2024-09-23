package chess;

import chess.ChessPiece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rook extends ChessPiece {
    public Rook(Boolean color, String position) {
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

        if(!this.getMoved()){
            boolean possibleRose = true;
            for (ChessPiece one:allPieces) {
                if(one.getColor() == this.getColor() && !one.getMoved() && one instanceof King){
                    if(ChessPiece.positionToint(one.getPosition())[1] < current[1]){
                        for (int i = 6; i > ChessPiece.positionToint(one.getPosition())[1]; i--) {
                            for (ChessPiece two:allPieces) {
                                if(two.getPosition().equals(current[0]+"_"+i)){
                                    possibleRose = false;
                                    break;
                                }
                            }
                        }
                    } else if (ChessPiece.positionToint(one.getPosition())[1] > current[1]) {
                        for (int i = 1; i < ChessPiece.positionToint(one.getPosition())[1]; i++) {
                            for (ChessPiece two:allPieces) {
                                if(two.getPosition().equals(current[0]+"_"+i)){
                                    possibleRose = false;
                                    break;
                                }
                            }
                        }
                    }
                    if(possibleRose){
                        possible.add("6_9");
                    }

                }
            }
        }

        for (ChessPiece one:allPieces) {
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
                if(one.getColor() == this.getColor()){
                    possible.remove(one.getPosition());
                }
            }
        }
        possible.remove(this.getPosition());
        return possible;
    }
    @Override
    public String toString() {
        return (this.getColor())? "\u265C":"\u2656";
    }
}

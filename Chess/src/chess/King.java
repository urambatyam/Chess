package chess;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class King extends ChessPiece{
    boolean inChess = false;
    boolean inMatt = false;
    public King(Boolean color, String position) {
        super(color, position);
    }

    @Override
    public Set<String> possibleMoves(List<ChessPiece> allPieces) {
        Set<String> possible = new HashSet<>();
        int[] current = ChessPiece.positionToint(this.getPosition());
        possible.add(this.getPosition());
        possible.add((current[0]-1)+"_"+(current[1]));
        possible.add((current[0]+1)+"_"+(current[1]));

        possible.add((current[0])+"_"+(current[1]+1));
        possible.add((current[0])+"_"+(current[1]-1));

        possible.add((current[0]-1)+"_"+(current[1]-1));
        possible.add((current[0]-1)+"_"+(current[1]+1));

        possible.add((current[0]+1)+"_"+(current[1]-1));
        possible.add((current[0]+1)+"_"+(current[1]+1));


        for (ChessPiece one:allPieces) {
            if(!this.getMoved()){
                boolean possibleRose0 = true;
                boolean possibleRose7 = true;
                if(one.getColor() == this.getColor() && !one.getMoved() && one instanceof Rook){
                    if(ChessPiece.positionToint(one.getPosition())[1] < 4){
                        for (int i = 3; i > 0; i--) {
                            for (ChessPiece two:allPieces) {
                                if(two.getPosition().equals(current[0]+"_"+i)){
                                    possibleRose0 = false;
                                    break;
                                }
                            }
                        }
                    } else if (ChessPiece.positionToint(one.getPosition())[1] > 4) {
                        for (int i = 5; i < 7; i++) {
                            for (ChessPiece two:allPieces) {
                                if(two.getPosition().equals(current[0]+"_"+i)){
                                    possibleRose7 = false;
                                    break;
                                }
                            }
                        }
                    }
                    if(possibleRose0){
                        possible.add("9_0");
                    } else if (possibleRose7) {
                        possible.add("9_7");
                    }

                }
            }



            if(possible.contains(one.getPosition())){
                if(one.getColor() == this.getColor()){
                    possible.remove(one.getPosition());
                }
            }
            if(one.getColor() != this.getColor()){
                possible.removeAll(one.possibleMoves(allPieces));
            }
        }
        if(!possible.contains(this.getPosition())){
            inChess = true;
        }else{
            possible.remove(this.getPosition());
        }

        if(inChess && possible.size()==0){
            inMatt = true;
        }
        return possible;
    }
    @Override
    public String toString() {
        return (this.getColor())? "\u265A":"\u2654";
    }

    public boolean isInChess() {
        return inChess;
    }

    public void setInChess(boolean inChess) {
        this.inChess = inChess;
    }

    public boolean isInMatt() {
        return inMatt;
    }

    public void setInMatt(boolean inMatt) {
        this.inMatt = inMatt;
    }
}

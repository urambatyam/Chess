import chess.*;

import java.util.*;

public class Game {
    private  ChessPiece[][] ChessBoard;
    private List<ChessPiece> all;
    private int round = 1;

    public Game() {
        all = new LinkedList<>();
        ChessBoard = new ChessPiece[8][8];
        ChessBoard[0] = new ChessPiece[]{
                new Rook(false,"0_0"),
                new Knight(false,"0_1"),
                new Bishop(false,"0_2"),
                new Queen(false,"0_3"),
                new King(false,"0_4"),
                new Bishop(false,"0_5"),
                new Knight(false,"0_6"),
                new Rook(false,"0_7")
        };
        ChessBoard[1] = new ChessPiece[]{
                new Pawn(false,"1_0"),
                new Pawn(false,"1_1"),
                new Pawn(false,"1_2"),
                new Pawn(false,"1_3"),
                new Pawn(false,"1_4"),
                new Pawn(false,"1_5"),
                new Pawn(false,"1_6"),
                new Pawn(false,"1_7")
        };
        for (int i = 2; i < 6; i++) {
            ChessBoard[i] = new ChessPiece[8];
        }
        ChessBoard[6] = new ChessPiece[]{
                new Pawn(true,"6_0"),
                new Pawn(true,"6_1"),
                new Pawn(true,"6_2"),
                new Pawn(true,"6_3"),
                new Pawn(true,"6_4"),
                new Pawn(true,"6_5"),
                new Pawn(true,"6_6"),
                new Pawn(true,"6_7")
        };
        ChessBoard[7] = new ChessPiece[]{
                new Rook(true,"7_0"),
                new Knight(true,"7_1"),
                new Bishop(true,"7_2"),
                new Queen(true,"7_3"),
                new King(true,"7_4"),
                new Bishop(true,"7_5"),
                new Knight(true,"7_6"),
                new Rook(true,"7_7")
        };
        Collections.addAll(all, ChessBoard[0]);
        Collections.addAll(all, ChessBoard[1]);
        Collections.addAll(all, ChessBoard[6]);
        Collections.addAll(all, ChessBoard[7]);
    }

    public ChessPiece getSquare(String position){
        if(ChessPiece.positionToint(position)[0] >=0 && ChessPiece.positionToint(position)[0]<=7 &&ChessPiece.positionToint(position)[1] >=0 && ChessPiece.positionToint(position)[1]<=7){
            return ChessBoard[Integer.parseInt(position.split("_")[0])][Integer.parseInt(position.split("_")[1])];
        }
        return null;
    }

    public boolean move(String from, String to){
        if(getSquare(from) != null &&  getSquare(from).possibleMoves(all).contains(to)){
            switch (to) {
                case "6_9" -> {
                    getSquare(from).setMoved(true);
                    getSquare(ChessPiece.positionToint(from)[0] + "_4").setMoved(true);
                    getSquare(from).setPosition(ChessPiece.positionToint(from)[0] + "_4");
                    getSquare(ChessPiece.positionToint(from)[0] + "_4").setPosition(from);
                    ChessPiece temp = ChessBoard[ChessPiece.positionToint(from)[0]][ChessPiece.positionToint(from)[1]];
                    ChessBoard[ChessPiece.positionToint(from)[0]][ChessPiece.positionToint(from)[1]] = ChessBoard[ChessPiece.positionToint(from)[0]][4];
                    ChessBoard[ChessPiece.positionToint(from)[0]][4] = temp;
                    return true;
                }
                case "9_0" -> {
                    getSquare(from).setMoved(true);
                    getSquare(ChessPiece.positionToint(from)[0] + "_0").setMoved(true);
                    getSquare(from).setPosition(ChessPiece.positionToint(from)[0] + "_0");
                    getSquare(ChessPiece.positionToint(from)[0] + "_0").setPosition(from);
                    ChessPiece temp = ChessBoard[ChessPiece.positionToint(from)[0]][ChessPiece.positionToint(from)[1]];
                    ChessBoard[ChessPiece.positionToint(from)[0]][ChessPiece.positionToint(from)[1]] = ChessBoard[ChessPiece.positionToint(from)[0]][0];
                    ChessBoard[ChessPiece.positionToint(from)[0]][0] = temp;
                    return true;
                }
                case "9_7" ->{
                    getSquare(from).setMoved(true);
                    getSquare(ChessPiece.positionToint(from)[0] + "_7").setMoved(true);
                    getSquare(from).setPosition(ChessPiece.positionToint(from)[0] + "_7");
                    getSquare(ChessPiece.positionToint(from)[0] + "_7").setPosition(from);
                    ChessPiece temp = ChessBoard[ChessPiece.positionToint(from)[0]][ChessPiece.positionToint(from)[1]];
                    ChessBoard[ChessPiece.positionToint(from)[0]][ChessPiece.positionToint(from)[1]] = ChessBoard[ChessPiece.positionToint(from)[0]][7];
                    ChessBoard[ChessPiece.positionToint(from)[0]][7] = temp;
                    return true;
                }
                case "9_9" -> {
                    Scanner pawnToSomethingelse = new Scanner(System.in);
                    System.out.println("Írd be hogy mire változtatod a parasztot(R->bástya, K->ló, B->futó, Q->vezér)");
                    switch (pawnToSomethingelse.nextLine()){
                        case "R" ->{
                            all.remove(getSquare(from));
                            ChessPiece Pawn = new Rook(getSquare(from).getColor(),getSquare(from).getPosition());
                            ChessBoard[ChessPiece.positionToint(from)[0]][ChessPiece.positionToint(from)[1]] = Pawn;
                            all.add(Pawn);
                        }
                        case "K" -> {
                            all.remove(getSquare(from));
                            ChessPiece Pawn = new Knight(getSquare(from).getColor(),getSquare(from).getPosition());
                            ChessBoard[ChessPiece.positionToint(from)[0]][ChessPiece.positionToint(from)[1]] = Pawn;
                            all.add(Pawn);
                        }
                        case "B" -> {
                            all.remove(getSquare(from));
                            ChessPiece Pawn = new Bishop(getSquare(from).getColor(),getSquare(from).getPosition());
                            ChessBoard[ChessPiece.positionToint(from)[0]][ChessPiece.positionToint(from)[1]] = Pawn;
                            all.add(Pawn);
                        }
                        case "Q" ->{
                            all.remove(getSquare(from));
                            ChessPiece Pawn = new Queen(getSquare(from).getColor(),getSquare(from).getPosition());
                            ChessBoard[ChessPiece.positionToint(from)[0]][ChessPiece.positionToint(from)[1]] = Pawn;
                            all.add(Pawn);
                        }
                    }
                    return true;
                }
                default -> {
                    getSquare(from).setMoved(true);
                    all.remove(getSquare(to));
                    getSquare(from).setPosition(to);
                    ChessBoard[Integer.parseInt(to.split("_")[0])][Integer.parseInt(to.split("_")[1])] = getSquare(from);
                    ChessBoard[Integer.parseInt(from.split("_")[0])][Integer.parseInt(from.split("_")[1])] = null;
                    return true;
                }
            }

        }
        return false;
    }

    public void printAll(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(ChessBoard[i][j] != null){
                    System.out.print(ChessBoard[i][j]+" ");
                } else if (i%2==0 && j%2==0) {
                    System.out.print("\u25A0  ");
                }else if(i%2==0 && j%2==1){
                    System.out.print("\u25A1  ");
                } else if (i%2!=0 && j%2==0) {
                    System.out.print("\u25A1  ");
                }else {
                    System.out.print("\u25A0  ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public void teszt(){
        while (1>0){
            printAll();
            Scanner in = new Scanner(System.in);
            do {
                System.out.println("add meg a honan majd hova:");
            }while (!move(in.nextLine(),in.nextLine()));
            round++;
        }
    }

    public static void main(String[] args) {
        Game a = new Game();
        a.teszt();
    }
}

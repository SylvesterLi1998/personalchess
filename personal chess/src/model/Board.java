package model;

import java.util.HashMap;

public class Board{
    private static HashMap<ChessPosition,Piece> board= new HashMap<ChessPosition,Piece>();
    public static final int BOARD_SIZE=10;


    public static HashMap<ChessPosition, Piece> getBoard() {
        return board;
    }

    public static Piece getElement(ChessPosition chessPosition){
        return board.get(chessPosition);
    }

    public String toString(){
        String map="";
        for(ChessPosition position:board.keySet()) {
            map+=board.get(position).toString();
        }
        return map;
    }

}

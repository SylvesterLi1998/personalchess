package model;

import static java.lang.Math.abs;
import static model.Board.getBoard;


public class Commander extends Piece {

    public Commander(ChessPosition chessPosition, Player player) {
        super(chessPosition, player);
    }

    public Commander(ChessPosition chessPosition) {
        super(chessPosition);
    }

    @Override
    public boolean validMove(int targetX, int targetY){
        if (this.getChessPosition().getX()== targetX && this.getChessPosition().getY()== targetY)
            return false;
        else
            return (abs(this.getChessPosition().getY()- targetY)==0|| abs(this.getChessPosition().getX()- targetX)==0||checkSquares(new ChessPosition(targetX,targetY),2))&& !(getBoard().containsKey(new ChessPosition(targetX,targetY)));
    }

    @Override
    public boolean validCapture(Piece piece) {
        return (checkLine(piece)||checkSquares(piece,2))&& checkCaptureDiffPlayer(piece);
    }

}

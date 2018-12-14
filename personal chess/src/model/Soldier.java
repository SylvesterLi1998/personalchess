package model;

import static model.Board.getBoard;

public class Soldier extends Piece {
    public Soldier(ChessPosition chessPosition, Player player) {
        super(chessPosition, player);
    }

    public Soldier(ChessPosition chessPosition) {
        super(chessPosition);
    }


    @Override
    public boolean validMove(int targetX, int targetY) {
        if (this.getChessPosition().getY()== targetY && this.getChessPosition().getX()== targetX)
            return false;
        else
            return checkSquares(new ChessPosition(targetX,targetY),1) && !(getBoard().containsKey(new ChessPosition(targetX,targetY)));
    }

    @Override
    public boolean validCapture(Piece piece) {
        return checkSquares(piece,1)&& checkCaptureDiffPlayer(piece);
    }

}

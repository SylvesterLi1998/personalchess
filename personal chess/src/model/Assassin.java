package model;


import static java.lang.Math.abs;
import static model.Board.getBoard;

public class Assassin extends Piece {

    public Assassin(ChessPosition chessPosition, Player player) {
        super(chessPosition, player);
    }

    public Assassin(ChessPosition chessPosition) {
        super(chessPosition);
    }

    @Override
    // same row or same col
    public boolean validMove(int targetX, int targetY) {
        if (this.getChessPosition().getX()== targetX && this.getChessPosition().getY()== targetY)
            return false;
        else
            return (abs(this.getChessPosition().getX()- targetX)==0|| abs(this.getChessPosition().getY()- targetY)==0)&&!(getBoard().containsKey(new ChessPosition(targetX,targetY)));
    }

    @Override
    // capture the target piece unless the piece is a commander
    public boolean validCapture(Piece piece) {
        return (!piece.gettype().equals("General"))&& checkCaptureDiffPlayer(piece);
    }



    @Override
    public Piece capture(Piece piece) {
        super.capture(piece);
        this.getPlayer().getOwn().remove(this);
        this.getPlayer().getCaptured().add(this);
        return piece;
    }

}

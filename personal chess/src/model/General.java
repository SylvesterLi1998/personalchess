package model;


import static model.Board.getBoard;

public class General extends Piece {
    public General(ChessPosition chessPosition, Player player) {
        super(chessPosition, player);
    }

    public General(ChessPosition chessPosition) {
        super(chessPosition);
    }

    @Override
    public boolean validMove(int targetX, int targetY) {
        if (this.getChessPosition().getX()== targetX && this.getChessPosition().getY()== targetY)
            return false;
        else
            return checkSquares(new ChessPosition(targetX,targetY),1)&&!(getBoard().containsKey(new ChessPosition(targetX,targetY)));
    }

    @Override
    public boolean validCapture(Piece piece) {
        return checkSquares(piece,3)&& checkCaptureDiffPlayer(piece);
    }

}

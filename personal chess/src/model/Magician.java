package model;

import static model.Board.getBoard;



public class Magician extends Piece {
    public Magician(ChessPosition chessPosition, Player player) {
        super(chessPosition, player);
    }

    public Magician(ChessPosition chessPosition) {
        super(chessPosition);
    }

    @Override
    public boolean validMove(int targetX, int targetY) {
        if (this.getChessPosition().getY()== targetY && this.getChessPosition().getX()== targetX)
            return false;
        else
            return checkSquares(new ChessPosition(targetX,targetY),1)&&!(getBoard().containsKey(new ChessPosition(targetX,targetY)));
    }

    @Override
    public boolean validCapture(Piece piece) {
        return (checkSquares(piece,1)&& checkCaptureDiffPlayer(piece));
    }

    //switch the position of two piece in 3*3 block around it
    public boolean castSpell(Piece pieceA, Piece pieceB) {
        if(checkSquares(pieceA,3)&& checkSquares(pieceB,3)){
            // for each object
            ChessPosition tempPosition= new ChessPosition(pieceA.getChessPosition().getX(),pieceA.getChessPosition().getY());
            pieceA.getChessPosition().setX(pieceB.getChessPosition().getX());
            pieceA.getChessPosition().setY(pieceB.getChessPosition().getY());
            pieceB.getChessPosition().setX(tempPosition.getX());
            pieceB.getChessPosition().setY(tempPosition.getY());

            return true;
        }
        else{
            System.out.println("Cast spell invalid!");
            return false;
        }

    }

}

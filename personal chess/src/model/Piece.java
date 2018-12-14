package model;

import java.util.Objects;

import static java.lang.Math.abs;
import static model.Board.getBoard;
import static ui.PlayChess.*;


public abstract class Piece{

    private ChessPosition chessPosition;
	private Player player;

    public Piece(ChessPosition chessPosition, Player player) {
        this.chessPosition = chessPosition;
        this.player = player;
    }

    public Piece(ChessPosition chessPosition){
        this.chessPosition= chessPosition;
    }

    public ChessPosition getChessPosition() {
        return chessPosition;
    }

    public void setChessPosition(ChessPosition chessPosition) {
        this.chessPosition = chessPosition;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    // return the real type of the piece
	public String gettype(){
    	String temp=this.getClass().getName();
		return temp.substring(temp.lastIndexOf('.')+1);
	}

	public abstract boolean validMove(int targetX, int targetY);

	public abstract boolean validCapture(Piece piece);

	// A capture B
	// return the captured piece
    // if the capture is not valid, return null
	public Piece capture( Piece piece){
	    if (validCapture(piece)){
            this.chessPosition.setX(piece.chessPosition.getX());
            this.chessPosition.setY(piece.chessPosition.getY());
            piece.getPlayer().getOwn().remove(piece);
            piece.getPlayer().getCaptured().add(piece);
            getBoard().remove(piece.chessPosition);
            return piece;
        }
		return null;
	}

    // if this piece is in distance y with the given piece return true
    public boolean checkSquares(ChessPosition chessPosition, int distance){
	    return(abs(this.chessPosition.getY()-chessPosition.getY())<=distance&& abs(this.chessPosition.getX()-chessPosition.getX())<=distance);
    }
    public boolean checkSquares(Piece piece, int distance){
        return(abs(this.chessPosition.getY()-piece.chessPosition.getY())<=distance&& abs(this.chessPosition.getX()-piece.chessPosition.getX())<=distance);
    }
    public boolean checkLine(Piece piece){
        return abs(this.chessPosition.getX()-piece.chessPosition.getX())==0|| abs(this.chessPosition.getY()-piece.chessPosition.getY())==0;
    }

    // return true if this and piece have same player
    public boolean checkCaptureDiffPlayer(Piece piece){
	    return !(piece.getPlayer().equals(this.player));
    }

    // if it is red's turn only red's piece can move, same for blue
    public boolean checkControlPlayer(){
	    if (whoseTurn){
	        return this.getPlayer().getName().equals(playerBName);
        }else
            return this.getPlayer().getName().equals(playerAName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(chessPosition, piece.chessPosition) &&
                Objects.equals(player, piece.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chessPosition, player);
    }

    @Override
    public String toString() {
        return this.gettype()+" "+chessPosition.toString()+this.player.getName();
    }

    public void move(ChessPosition chessPosition){
	    if (validMove(chessPosition.getX(),chessPosition.getY())){
	        this.setChessPosition(chessPosition);
        }
    }

    public static void loadPieceToPlayer(Piece piece, String player){
        if (player.equals(playerAName)){
            playerA.getOwn().add(piece);
            piece.setPlayer(playerA);
        }
        else if(player.equals(playerBName)){
            playerB.getOwn().add(piece);
            piece.setPlayer(playerB);
        }
        else
            // todo: player not found
            System.out.println("player not found!");
    }
}


package ui;

import model.ChessPosition;
import model.Piece;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static model.Board.getBoard;
import static ui.BoardUI.showAllMoves;
import static ui.PlayChess.tempPiece;



public class PieceUI{
    private JButton pieceUI;
    private ChessPosition chessPosition;
    public static ArrayList<ChessPosition> validCapturePositions= new ArrayList<ChessPosition>();
    public static ArrayList<ChessPosition> validMovePositions= new ArrayList<ChessPosition>();
    public boolean actived=false;


    public PieceUI(ChessPosition chessPosition){
        this.chessPosition=chessPosition;
    }

    public PieceUI(JButton pieceUI) {
        this.pieceUI = pieceUI;
        pieceUI.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // if the game is not started, then nothing happened
                // if the game has start and it is the first time to click, turn on the flag that indicate first has clicked, and change the first clicked position
                // if the game has start and it is the not the first time to click, turn on the flag that indicate second clicked


//                if (started){
//                    if (!firstClicked){
//                        firstClicked=true;
//                        firstClickedPosition=chessPosition;
//
                        Piece pieceTest= getBoard().get(chessPosition);
                        actived=!actived;
                        try{
                            String soundFile = ("sound/piece_click_sound.wav");
                            InputStream in = new FileInputStream(soundFile);
                            AudioStream audioStream = new AudioStream(in);
                            // play the audio clip with the audioplayer class
                            AudioPlayer.player.start(audioStream);
                        }catch (IOException el){
                            el.printStackTrace();
                        }


//                        if ((!whoseTurn&&(pieceTest.getPlayer().getName().equals("Red")))||((whoseTurn)&&(pieceTest.getPlayer().getName().equals("Blue")))){
                            //if (!actived){
                                tempPiece=getBoard().get(chessPosition);
                                //System.out.println("");
                                showAllMoves(tempPiece);
                            //}else{
                                //cancelShades();
                            //}

                            // choose one from it,
                            // if choose the same piece, nothing happens,
                            // else move or capture
//                        }
//                    }
//                    else{
//                        Piece pieceTest= getBoard().get(chessPosition);
//                        if((whoseTurn&&pieceTest.getPlayer().getName().equals("Red"))||((!whoseTurn)&&pieceTest.getPlayer().getName().equals("Blue"))){
//                            secondClicked=true;
//                            secondClickedPosition=chessPosition;
//                        }
//
//                    }
//                }
            }
        });
        //todo: do I have to cast the board
        //addObserver(new Piece());
    }
    public void setChessPosition(ChessPosition chessPosition) {
        this.chessPosition=chessPosition;
    }

    public ChessPosition getChessPosition() {
        return chessPosition;
    }

    public void cancelShades(){

    }
}

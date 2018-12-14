package ui;

import model.*;

import javax.swing.*;
import java.io.*;

import static model.Board.getBoard;
import static model.Piece.loadPieceToPlayer;
import static ui.BoardUI.cancelAllShades;


public class PlayChess{
    public static final String playerAName= "Red";
    public static final String playerBName= "Blue";

    public static Player playerA=new Player(playerAName);
    public static Player playerB=new Player(playerBName);
    public static boolean continueGmae=true;
    private static boolean gameOver= false;
    // false for red, true for blue
    public static boolean whoseTurn=false;
    // first clicked piece in the board
    public static Piece tempPiece;
    public static boolean started= false;
    // pieceUI clicked
    public static boolean firstClicked=false;
    public static boolean secondClicked=false;
    public static final File SOUND_FILENAME = new File("sound/piece_click_sound.mp3");

    protected static ChessPosition firstClickedPosition;
    protected static ChessPosition secondClickedPosition;

    // todo: second pressed button

    public static void main(String[] args){

        BoardUI cb = new BoardUI();

        JFrame f = new JFrame("Chess");
        f.add(cb.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);

        f.setResizable(false);
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
        setup();

//        System.out.println(f.getSize().height+" "+f.getSize().width);
//        while(continueGmae){
//            started=true;
//            while(!gameOver){
//                boolean didMove=false;
//                 // did the player move
//                 while(!didMove){
//                     // todo: click first, if not press button yet, just wait
//                     // todo: how to initialize the clickbutton
//
//                         if (firstClicked && secondClicked) {
//                             didMove = action(tempPiece);
//                             firstClicked = false;
//                             secondClicked = false;
//                         }
//
//                     try {
//                         sleep(200);
//                     } catch (InterruptedException e) {
//                         e.printStackTrace();
//                     }
//                 }
//                whoseTurn=!whoseTurn;
//            }
//            wantToQuit();
//        }
    }


    public static void setup(){
        //playerA's pieces
        Commander cA1= new Commander(new ChessPosition(1,0),playerA);
        Commander cA2= new Commander(new ChessPosition(1,8),playerA);
        Soldier sA1= new Soldier(new ChessPosition(2,0),playerA);
        Soldier sA2= new Soldier(new ChessPosition(2,4),playerA);
        Soldier sA3= new Soldier(new ChessPosition(2,6),playerA);
        Soldier sA4= new Soldier(new ChessPosition(2,8),playerA);
        Magician mA1= new Magician(new ChessPosition(3,3),playerA);
        Magician mA2= new Magician(new ChessPosition(3,7),playerA);
        General gA= new General(new ChessPosition(0,4),playerA);
        Assassin aA= new Assassin(new ChessPosition(0,6),playerA);

        playerA.getOwn().add(cA1);
        playerA.getOwn().add(cA2);
        playerA.getOwn().add(sA1);
        playerA.getOwn().add(sA2);
        playerA.getOwn().add(sA3);
        playerA.getOwn().add(sA4);
        playerA.getOwn().add(mA1);
        playerA.getOwn().add(mA2);
        playerA.getOwn().add(gA);
        playerA.getOwn().add(aA);



        // playerB's pieces
        Commander cB1= new Commander(new ChessPosition(8,1),playerB);
        Commander cB2= new Commander(new ChessPosition(8,9),playerB);
        Soldier sB1= new Soldier(new ChessPosition(7,1),playerB);
        Soldier sB2= new Soldier(new ChessPosition(7,3),playerB);
        Soldier sB3= new Soldier(new ChessPosition(7,5),playerB);
        Soldier sB4= new Soldier(new ChessPosition(7,9),playerB);
        Magician mB1= new Magician(new ChessPosition(6,2),playerB);
        Magician mB2= new Magician(new ChessPosition(6,6),playerB);
        General gB= new General(new ChessPosition(9,5),playerB);
        Assassin aB= new Assassin(new ChessPosition(9,3),playerB);

        playerB.getOwn().add(cB1);
        playerB.getOwn().add(cB2);
        playerB.getOwn().add(sB1);
        playerB.getOwn().add(sB2);
        playerB.getOwn().add(sB3);
        playerB.getOwn().add(sB4);
        playerB.getOwn().add(mB1);
        playerB.getOwn().add(mB2);
        playerB.getOwn().add(gB);
        playerB.getOwn().add(aB);

        // render all the piece on board
        getBoard().put(cA1.getChessPosition(),cA1);
        getBoard().put(cA2.getChessPosition(),cA2);
        getBoard().put(sA1.getChessPosition(),sA1);
        getBoard().put(sA2.getChessPosition(),sA2);
        getBoard().put(sA3.getChessPosition(),sA3);
        getBoard().put(sA4.getChessPosition(),sA4);
        getBoard().put(mA1.getChessPosition(),mA1);
        getBoard().put(mA2.getChessPosition(),mA2);
        getBoard().put(gA.getChessPosition(),gA);
        getBoard().put(aA.getChessPosition(),aA);


        getBoard().put(cB1.getChessPosition(),cB1);
        getBoard().put(cB2.getChessPosition(),cB2);
        getBoard().put(sB1.getChessPosition(),sB1);
        getBoard().put(sB2.getChessPosition(),sB2);
        getBoard().put(sB3.getChessPosition(),sB3);
        getBoard().put(sB4.getChessPosition(),sB4);
        getBoard().put(mB1.getChessPosition(),mB1);
        getBoard().put(mB2.getChessPosition(),mB2);
        getBoard().put(gB.getChessPosition(),gB);
        getBoard().put(aB.getChessPosition(),aB);

    }

    private static boolean action(Piece piece){

        // the position of the second piece/ space
        ChessPosition key= secondClickedPosition;
        // the position of the first piece
        ChessPosition tempPieceChessPosition= piece.getChessPosition();
        if (getBoard().containsKey(key)){
            //if the second click is a piece
            if (tempPieceChessPosition.equals(key)){
                // same piece
                cancelAllShades();
                return false;
            }else if (piece.validCapture(getBoard().get(key))){
                //capture
                Piece captured=piece.capture(getBoard().get(key));
                getBoard().get(tempPieceChessPosition).setChessPosition(key);
                // todo: show on board


                if (captured.gettype().equals("General")){
                    getBoard().get(tempPieceChessPosition).getPlayer().win();
                }

                // did act
                return true;
            }else{
                // invalid capture, and there is a piece in the position
                cancelAllShades();
                return false;
            }
        }
        // then the target place is not a piece
        else if (tempPiece.validMove(key.getX(),key.getY())){
            tempPiece.move(key);
            getBoard().get(tempPieceChessPosition).setChessPosition(key);
            return true;
        }else {
            // cancel the green shades, and do nothing.
            // invalid move
            cancelAllShades();
            return false;
        }
    }


    //read the file and restore the current
    public static void restore(File file) throws IOException {
        FileReader fileReader= new FileReader(file);
        BufferedReader br= new BufferedReader(fileReader);
        String temp;
        String tempType;
        int tempX;
        int tempY;
        String tempPlayer;
        Piece tempPiece;

        while((temp=br.readLine())!="\r"){

            String[] buff=temp.split(" ");
            tempType=buff[0];
            tempX=Integer.parseInt(buff[1]);
            tempY=Integer.parseInt(buff[1]);
            tempPlayer=buff[3];

            switch (tempType){
                case "Commander": {
                    tempPiece=new Commander(new ChessPosition(tempX,tempY));
                    loadPieceToPlayer(tempPiece, tempPlayer);
                }
                case "Assassin": {
                    tempPiece=new Assassin(new ChessPosition(tempX,tempY));
                    loadPieceToPlayer(tempPiece, tempPlayer);
                }
                case "General": {
                    tempPiece=new General(new ChessPosition(tempX,tempY));
                    loadPieceToPlayer(tempPiece, tempPlayer);
                }
                case "Magician":{
                    tempPiece=new Magician(new ChessPosition(tempX,tempY));
                    loadPieceToPlayer(tempPiece, tempPlayer);
                }
                case "Soldier":{
                    tempPiece=new Soldier(new ChessPosition(tempX,tempY));
                    loadPieceToPlayer(tempPiece, tempPlayer);
                }
            }

            //todo: file not readable exception

            fileReader.close();
            br.close();


        }
    }

    public static void save() throws IOException {
        PrintWriter printWriter= new PrintWriter("file.txt");
        for(ChessPosition position:getBoard().keySet()) {
            printWriter.println(getBoard().get(position).toString());
        }
        printWriter.close();
    }

    public static void newGame(){
        getBoard().clear();
        playerA.getOwn().clear();
        playerA.getCaptured().clear();
        playerB.getOwn().clear();
        playerB.getCaptured().clear();
        setup();
        cancelAllShades();
    }

    public static void setGameOver(boolean gameOver) {
        PlayChess.gameOver = gameOver;
    }

    public  static void wantToQuit(){
        int q1 = JOptionPane.showConfirmDialog(null, "Do you want to play new game?", "Question",JOptionPane.YES_NO_OPTION);
        if (q1==0)
            newGame();
        else{
            int q2 = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Question",JOptionPane.YES_NO_OPTION);
            if (q2==0){
                continueGmae=false;
            }else{
                // do nothing
            }
        }


    }
    private static void quit(){
        gameOver=true;
    }

}

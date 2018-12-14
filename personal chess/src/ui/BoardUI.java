package ui;

import model.ChessPosition;
import model.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static model.Board.BOARD_SIZE;
import static model.Board.getBoard;
import static ui.FileFinder.findFile;
import static ui.PieceUI.validCapturePositions;
import static ui.PieceUI.validMovePositions;
import static ui.PlayChess.*;

public class BoardUI{

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private static JButton[][] chessBoardSquares = new JButton[BOARD_SIZE][BOARD_SIZE];
    private static JPanel chessBoard;

    public BoardUI() {
        initializeGui();
    }

    public static JButton[][] getChessBoardSquares() {
        return chessBoardSquares;
    }

    public final void initializeGui() {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);

        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });
        tools.add(newGame);

        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    save();
                } catch (IOException e1) {
                    //todo
                    e1.printStackTrace();
                }
            }
        });
        tools.add(save);

        JButton load = new JButton("Load");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address=findFile(e);

                try {
                    restore(new File(address));
                } catch (FileNotFoundException e1) {
                    // todo: what to do if the file is not found
                    JOptionPane.showMessageDialog(null, "alert", "alert", JOptionPane.ERROR_MESSAGE);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        tools.add(load);


        tools.addSeparator();
        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wantToQuit();
            }
        });
        tools.add(quit);

        tools.addSeparator();


        chessBoard = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int i = 0; i < chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {

                JButton b = new JButton();
                b.setMargin(buttonMargin);


                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);

                b.setBackground(Color.WHITE);
                b.addActionListener(new ButtonListener(i, j));
                chessBoardSquares[j][i] = b;
            }
        }



        setupUI();

        // fill the black non-pawn piece row
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                chessBoard.add(chessBoardSquares[i][j]);
            }
        }
    }

    public static ChessPosition mathDecision(Point point){
        int x= (int)(point.getX()/49.6);
        int y= (int)(point.getY()/66);
        return new ChessPosition(x,y);
    }


    public final JComponent getGui() {
        return gui;
    }


    //todo: move pieceUI
    public void movePieceUI(){

    }


    public void setupUI(){

        //todo: change the Jbutton to PieceUI
        try {
            //assassin
            JButton AA = new JButton();
            Image redA = ImageIO.read(new File("pic/redA.png"));
            AA.setIcon(new ImageIcon(redA));
            PieceUI aAUI= new PieceUI(AA);
            aAUI.setChessPosition(new ChessPosition(0,6));
            chessBoardSquares[aAUI.getChessPosition().getX()][aAUI.getChessPosition().getY()]=AA;

            JButton BA = new JButton();
            Image blueA = ImageIO.read(new File("pic/blueA.png"));
            BA.setIcon(new ImageIcon(blueA));
            PieceUI aBUI= new PieceUI(BA);
            aBUI.setChessPosition(new ChessPosition(9,3));
            chessBoardSquares[aBUI.getChessPosition().getX()][aBUI.getChessPosition().getY()]=BA;



            //general
            Image redG = ImageIO.read(new File("pic/redG.png"));
            JButton AG = new JButton();
            AG.setIcon(new ImageIcon(redG));
            PieceUI gAUI=new PieceUI(AG);
            gAUI.setChessPosition(new ChessPosition(0,4));
            chessBoardSquares[gAUI.getChessPosition().getX()][gAUI.getChessPosition().getY()]=AG;

            Image blueG = ImageIO.read(new File("pic/blueG.png"));
            JButton BG = new JButton();
            BG.setIcon(new ImageIcon(blueG));
            PieceUI gBUI=new PieceUI(BG);
            gBUI.setChessPosition(new ChessPosition(9,5));
            chessBoardSquares[gBUI.getChessPosition().getX()][gBUI.getChessPosition().getY()]=BG;

            //magician
            Image redM = ImageIO.read(new File("pic/redM.png"));
            JButton AM1 = new JButton();
            AM1.setIcon(new ImageIcon(redM));
            PieceUI mA1UI= new PieceUI(AM1);
            mA1UI.setChessPosition(new ChessPosition(3,3));
            chessBoardSquares[mA1UI.getChessPosition().getX()][mA1UI.getChessPosition().getY()]=AM1;
            JButton AM2 = new JButton();
            AM2.setIcon(new ImageIcon(redM));
            PieceUI mA2UI= new PieceUI(AM2);
            mA2UI.setChessPosition(new ChessPosition(3,7));
            chessBoardSquares[mA2UI.getChessPosition().getX()][mA2UI.getChessPosition().getY()]=AM2;

            Image blueM = ImageIO.read(new File("pic/blueM.png"));
            JButton BM1 = new JButton();
            BM1.setIcon(new ImageIcon(blueM));
            PieceUI mB1UI= new PieceUI(BM1);
            mB1UI.setChessPosition(new ChessPosition(6,2));
            chessBoardSquares[mB1UI.getChessPosition().getX()][mB1UI.getChessPosition().getY()]=BM1;
            JButton BM2 = new JButton();
            BM2.setIcon(new ImageIcon(blueM));
            PieceUI mB2UI= new PieceUI(BM2);
            mB2UI.setChessPosition(new ChessPosition(6,6));
            chessBoardSquares[mB2UI.getChessPosition().getX()][mB2UI.getChessPosition().getY()]=BM2;

            //commander
            Image redC = ImageIO.read(new File("pic/redC.png"));
            JButton AC1 = new JButton();
            AC1.setIcon(new ImageIcon(redC));
            PieceUI cA1UI= new PieceUI(AC1);
            cA1UI.setChessPosition(new ChessPosition(1,0));
            chessBoardSquares[cA1UI.getChessPosition().getX()][cA1UI.getChessPosition().getY()]=AC1;
            JButton AC2 = new JButton();
            AC2.setIcon(new ImageIcon(redC));
            PieceUI cA2UI= new PieceUI(AC2);
            cA2UI.setChessPosition(new ChessPosition(1,8));
            chessBoardSquares[cA2UI.getChessPosition().getX()][cA2UI.getChessPosition().getY()]=AC2;

            Image blueC = ImageIO.read(new File("pic/blueC.png"));
            JButton BC1 = new JButton();
            BC1.setIcon(new ImageIcon(blueC));
            PieceUI cB1UI= new PieceUI(BC1);
            cB1UI.setChessPosition(new ChessPosition(8,1));
            chessBoardSquares[cB1UI.getChessPosition().getX()][cB1UI.getChessPosition().getY()]=BC1;
            JButton BC2 = new JButton();
            BC2.setIcon(new ImageIcon(blueC));
            PieceUI cB2UI= new PieceUI(BC2);
            cB2UI.setChessPosition(new ChessPosition(8,9));
            chessBoardSquares[cB2UI.getChessPosition().getX()][cB2UI.getChessPosition().getY()]=BC2;

            //soldier
            Image redS = ImageIO.read(new File("pic/redS.png"));
            JButton AS1 = new JButton();
            AS1.setIcon(new ImageIcon(redS));
            PieceUI sA1UI= new PieceUI(AS1);
            sA1UI.setChessPosition(new ChessPosition(2,0));
            chessBoardSquares[sA1UI.getChessPosition().getX()][sA1UI.getChessPosition().getY()]=AS1;
            JButton AS2 = new JButton();
            AS2.setIcon(new ImageIcon(redS));
            PieceUI sA2UI= new PieceUI(AS2);
            sA2UI.setChessPosition(new ChessPosition(2,4));
            chessBoardSquares[sA2UI.getChessPosition().getX()][sA2UI.getChessPosition().getY()]=AS2;
            JButton AS3 = new JButton();
            AS3.setIcon(new ImageIcon(redS));
            PieceUI sA3UI= new PieceUI(AS3);
            sA3UI.setChessPosition(new ChessPosition(2,6));
            chessBoardSquares[sA3UI.getChessPosition().getX()][sA3UI.getChessPosition().getY()]=AS3;
            JButton AS4 = new JButton();
            AS4.setIcon(new ImageIcon(redS));
            PieceUI sA4UI= new PieceUI(AS4);
            sA4UI.setChessPosition(new ChessPosition(2,8));
            chessBoardSquares[sA4UI.getChessPosition().getX()][sA4UI.getChessPosition().getY()]=AS4;


            Image blueS = ImageIO.read(new File("pic/blueS.png"));
            JButton BS1 = new JButton();
            BS1.setIcon(new ImageIcon(blueS));
            PieceUI sB1UI= new PieceUI(BS1);
            sB1UI.setChessPosition(new ChessPosition(7,1));
            chessBoardSquares[sB1UI.getChessPosition().getX()][sB1UI.getChessPosition().getY()]=BS1;
            JButton BS2 = new JButton();
            BS2.setIcon(new ImageIcon(blueS));
            PieceUI sB2UI= new PieceUI(BS2);
            sB2UI.setChessPosition(new ChessPosition(7,3));
            chessBoardSquares[sB2UI.getChessPosition().getX()][sB2UI.getChessPosition().getY()]=BS2;
            JButton BS3 = new JButton();
            BS3.setIcon(new ImageIcon(blueS));
            PieceUI sB3UI= new PieceUI(BS3);
            sB3UI.setChessPosition(new ChessPosition(7,5));
            chessBoardSquares[sB3UI.getChessPosition().getX()][sB3UI.getChessPosition().getY()]=BS3;
            JButton BS4 = new JButton();
            BS4.setIcon(new ImageIcon(blueS));
            PieceUI sB4UI= new PieceUI(BS4);
            sB4UI.setChessPosition(new ChessPosition(7,9));
            chessBoardSquares[sB4UI.getChessPosition().getX()][sB4UI.getChessPosition().getY()]=BS4;



        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

//    public void setupUI(){
//
//        //todo: change the Jbutton to PieceUI
//        try {
//            //assassin
//            JButton AA = new JButton();
//            Image redA = ImageIO.read(new File("pic/redA.png"));
//            AA.setIcon(new ImageIcon(redA));
//            PieceUI aA= new PieceUI(AA);
//            aA.setChessPosition(new ChessPosition(0,6));
//            chessBoardSquares[aA.getChessPosition().getX()][aA.getChessPosition().getY()]=aA;
//
//            JButton BA = new JButton();
//            Image blueA = ImageIO.read(new File("pic/blueA.png"));
//            BA.setIcon(new ImageIcon(blueA));
//            PieceUI aB= new PieceUI(BA);
//            aB.setChessPosition(new ChessPosition(9,3));
//            chessBoardSquares[aB.getChessPosition().getX()][aB.getChessPosition().getY()]=aB;
//
//
//
//            //general
//            Image redG = ImageIO.read(new File("pic/redG.png"));
//            JButton AG = new JButton();
//            AG.setIcon(new ImageIcon(redG));
//            PieceUI gA=new PieceUI(AG);
//            gA.setChessPosition(new ChessPosition(0,4));
//            chessBoardSquares[gA.getChessPosition().getX()][gA.getChessPosition().getY()]=gA;
//
//            Image blueG = ImageIO.read(new File("pic/blueG.png"));
//            JButton BG = new JButton();
//            BG.setIcon(new ImageIcon(blueG));
//            PieceUI gB=new PieceUI(BG);
//            gB.setChessPosition(new ChessPosition(9,5));
//            chessBoardSquares[gB.getChessPosition().getX()][gB.getChessPosition().getY()]=gB;
//
//            //magician
//            Image redM = ImageIO.read(new File("pic/redM.png"));
//            JButton AM1 = new JButton();
//            AM1.setIcon(new ImageIcon(redM));
//            PieceUI mA1= new PieceUI(AM1);
//            mA1.setChessPosition(new ChessPosition(3,3));
//            chessBoardSquares[mA1.getChessPosition().getX()][mA1.getChessPosition().getY()]=mA1;
//            JButton AM2 = new JButton();
//            AM2.setIcon(new ImageIcon(redM));
//            PieceUI mA2= new PieceUI(AM2);
//            mA2.setChessPosition(new ChessPosition(3,7));
//            chessBoardSquares[mA2.getChessPosition().getX()][mA2.getChessPosition().getY()]=mA2;
//
//            Image blueM = ImageIO.read(new File("pic/blueM.png"));
//            JButton BM1 = new JButton();
//            BM1.setIcon(new ImageIcon(blueM));
//            PieceUI mB1= new PieceUI(BM1);
//            mB1.setChessPosition(new ChessPosition(6,2));
//            chessBoardSquares[mB1.getChessPosition().getX()][mB1.getChessPosition().getY()]=mB1;
//            JButton BM2 = new JButton();
//            BM2.setIcon(new ImageIcon(blueM));
//            PieceUI mB2= new PieceUI(BM2);
//            mB2.setChessPosition(new ChessPosition(6,6));
//            chessBoardSquares[mB2.getChessPosition().getX()][mB2.getChessPosition().getY()]=mB2;
//
//            //commander
//            Image redC = ImageIO.read(new File("pic/redC.png"));
//            JButton AC1 = new JButton();
//            AC1.setIcon(new ImageIcon(redC));
//            PieceUI cA1= new PieceUI(AC1);
//            cA1.setChessPosition(new ChessPosition(1,0));
//            chessBoardSquares[cA1.getChessPosition().getX()][cA1.getChessPosition().getY()]=cA1;
//            JButton AC2 = new JButton();
//            AC2.setIcon(new ImageIcon(redC));
//            PieceUI cA2= new PieceUI(AC2);
//            cA2.setChessPosition(new ChessPosition(1,8));
//            chessBoardSquares[cA2.getChessPosition().getX()][cA2.getChessPosition().getY()]=cA2;
//
//            Image blueC = ImageIO.read(new File("pic/blueC.png"));
//            JButton BC1 = new JButton();
//            BC1.setIcon(new ImageIcon(blueC));
//            PieceUI cB1= new PieceUI(BC1);
//            cB1.setChessPosition(new ChessPosition(8,1));
//            chessBoardSquares[cB1.getChessPosition().getX()][cB1.getChessPosition().getY()]=cB1;
//            JButton BC2 = new JButton();
//            BC2.setIcon(new ImageIcon(blueC));
//            PieceUI cB2= new PieceUI(BC2);
//            cB2.setChessPosition(new ChessPosition(8,9));
//            chessBoardSquares[cB2.getChessPosition().getX()][cB2.getChessPosition().getY()]=cB2;
//
//            //soldier
//            Image redS = ImageIO.read(new File("pic/redS.png"));
//            JButton AS1 = new JButton();
//            AS1.setIcon(new ImageIcon(redS));
//            PieceUI sA1= new PieceUI(AS1);
//            sA1.setChessPosition(new ChessPosition(2,0));
//            chessBoardSquares[sA1.getChessPosition().getX()][sA1.getChessPosition().getY()]=sA1;
//            JButton AS2 = new JButton();
//            AS2.setIcon(new ImageIcon(redS));
//            PieceUI sA2= new PieceUI(AS2);
//            sA2.setChessPosition(new ChessPosition(2,4));
//            chessBoardSquares[sA2.getChessPosition().getX()][sA2.getChessPosition().getY()]=sA2;
//            JButton AS3 = new JButton();
//            AS3.setIcon(new ImageIcon(redS));
//            PieceUI sA3= new PieceUI(AS3);
//            sA3.setChessPosition(new ChessPosition(2,6));
//            chessBoardSquares[sA3.getChessPosition().getX()][sA3.getChessPosition().getY()]=sA3;
//            JButton AS4 = new JButton();
//            AS4.setIcon(new ImageIcon(redS));
//            PieceUI sA4= new PieceUI(AS4);
//            sA4.setChessPosition(new ChessPosition(2,8));
//            chessBoardSquares[sA4.getChessPosition().getX()][sA4.getChessPosition().getY()]=sA4;
//
//
//            Image blueS = ImageIO.read(new File("pic/blueS.png"));
//            JButton BS1 = new JButton();
//            BS1.setIcon(new ImageIcon(blueS));
//            PieceUI sB1= new PieceUI(BS1);
//            sB1.setChessPosition(new ChessPosition(7,1));
//            chessBoardSquares[sB1.getChessPosition().getX()][sB1.getChessPosition().getY()]=sB1;
//            JButton BS2 = new JButton();
//            BS2.setIcon(new ImageIcon(blueS));
//            PieceUI sB2= new PieceUI(BS2);
//            sB2.setChessPosition(new ChessPosition(7,3));
//            chessBoardSquares[sB2.getChessPosition().getX()][sB2.getChessPosition().getY()]=sB2;
//            JButton BS3 = new JButton();
//            BS3.setIcon(new ImageIcon(blueS));
//            PieceUI sB3= new PieceUI(BS3);
//            sB3.setChessPosition(new ChessPosition(7,5));
//            chessBoardSquares[sB3.getChessPosition().getX()][sB3.getChessPosition().getY()]=sB3;
//            JButton BS4 = new JButton();
//            BS4.setIcon(new ImageIcon(blueS));
//            PieceUI sB4= new PieceUI(BS4);
//            sB4.setChessPosition(new ChessPosition(7,9));
//            chessBoardSquares[sB4.getChessPosition().getX()][sB4.getChessPosition().getY()]=sB4;
//
//
//
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }


    public static void showAllMoves(Piece piece){
        int counter=0;
        for(int i=0; i<chessBoardSquares.length;i++){
            for (int j=0; j<chessBoardSquares[i].length;j++){
                counter++;
                if (piece.validMove(i,j)){

                    // make the button green
                    //chessBoardSquares[i][j].setBackground(Color.green);
                    chessBoard.getComponent(counter-1).setBackground(Color.green);
                    validCapturePositions.add(new ChessPosition(i,j));
                    System.out.println(i+" "+j+" move");


                }
                else if (getBoard().containsKey(new ChessPosition(i,j))){
                    if (piece.validCapture(getBoard().get(new ChessPosition(i,j)))){
                        chessBoardSquares[i][j].setBackground(Color.gray);
                        validMovePositions.add(new ChessPosition(i,j));
                        System.out.println(i+" "+j+" capture");
                    }
                }
            }
            chessBoard.repaint();
        }
    }

    public static void cancelAllShades(){
        int counter=0;
        for(int i=0; i<chessBoardSquares.length;i++){
            for (int j=0; j<chessBoardSquares[i].length;j++){
                counter++;
                //chessBoard.getComponent(counter-1).setBackground(Color.green);
                chessBoardSquares[i][j].setBackground(Color.white);
            }
            chessBoard.repaint();
        }
    }

    private class ButtonListener implements ActionListener{
        private int i;
        private int j;

        public ButtonListener(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

//                if (firstClicked) {
//                    //second click
//                    //todo
//                    secondClicked = true;
//                    secondClickedPosition = new ChessPosition(i, j);
//
//                }

        }
    }

}


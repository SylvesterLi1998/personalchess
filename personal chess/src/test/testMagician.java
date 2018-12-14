package test;

import model.Commander;
import model.Magician;
import model.Piece;
import model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testMagician {
/*
    private int x;
    private int y;
    private Player player;


    @Test
    void testConstructor(){
        ArrayList<Piece> own= new ArrayList<>();
        ArrayList<Piece> captured = new ArrayList<>();
        Player player= new Player(own,captured,"");
        Magician magican= new Magician(x,y,player);

        assertEquals(magican.getPlayer().getOwn(), new ArrayList<>());
        assertEquals(magican.getPlayer().getCaptured(), new ArrayList<>());
    }

    @Test
    void testValidMove(){
        x=5;
        y=4;
        Magician magician= new Magician(x,y,player);
        assertEquals(magician.validMove(5,4), false);
        assertEquals(magician.validMove(4,5), true);
        assertEquals(magician.validMove(4,4), true);
        assertEquals(magician.validMove(6,4), true);

    }

    @Test
    void testValidCapture(){
        x=5;
        y=4;
        ArrayList<Piece> own= new ArrayList<>();
        ArrayList<Piece> captured = new ArrayList<>();
        Player player= new Player(own,captured,"");

        ArrayList<Piece> ownM= new ArrayList<>();
        ArrayList<Piece> capturedM = new ArrayList<>();
        Player playerM= new Player(ownM,capturedM,"1");
        Magician magician= new Magician(x,y,playerM);

        Player playerOther= new Player(own,captured,"1");



        Magician magician1= new Magician(3,1,player);
        assertEquals(magician.validCapture(magician1),false);

        Magician magician2= new Magician(4,5,player);
        assertEquals(magician.validCapture(magician2),true);

        Magician magician3= new Magician(5,5,player);
        assertEquals(magician.validCapture(magician3),true);

        Magician magician4= new Magician(5,5,playerM);
        assertEquals(magician.validCapture(magician4),false);

    }

    @Test
    void testGetType(){
        x=5;
        y=4;
        Piece magician= new Magician(x,y,player);
        assertEquals(magician.gettype(),"Magician");
    }


*/
}

package model;

import java.util.HashSet;

import static ui.PlayChess.setGameOver;

public class Player{
    private String name;
    private HashSet<Piece> own;
    private HashSet<Piece> captured;

    public Player(String name){
        this.name=name;
        this.own= new HashSet<Piece>();
        this.captured= new HashSet<Piece>();
    }

    public HashSet<Piece> getOwn() {
        return own;
    }

    public void setOwn(HashSet<Piece> own) {
        this.own = own;
    }

    public HashSet<Piece> getCaptured() {
        return captured;
    }

    public void setCaptured(HashSet<Piece> captured) {
        this.captured = captured;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void win() {
        setGameOver(true);
        System.out.println(this.getName()+" Wins!");
    }

}

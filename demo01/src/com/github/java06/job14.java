package com.github.java06;

/*
如果job14 表示一段更为复杂的代码
这种模式将可以更多的复用代码
job14.palygame(一个新的工厂对象(新的Game接口的实现))
核心思想依然是解耦
 */

public class job14 {
    public static void main(String[] args) {
        palyGame(new CheckersFactory());
        palyGame(new ChessFacTORY());
    }

    public static void palyGame(GameFacTORY facTORY){
        Game game = facTORY.getGame();
        while (game.move()) {
        }
    }
}

interface Game{
    boolean move();
}


interface GameFacTORY{
    Game getGame();
}


class Checkers implements Game{
    private int moves = 0;
    private static final  int MOVES = 3;

    @Override
    public boolean move() {
        System.out.println("Checkers move" + moves);
        return ++moves != MOVES;
    }
}


class CheckersFactory implements GameFacTORY{

    @Override
    public Game getGame() {
        return new Checkers();
    }
}


class Chess implements Game{
    private int moves = 0;
    private static final int MOVES = 3;


    @Override
    public boolean move() {
        System.out.println("Chess move" + moves);
        return ++moves != MOVES;
    }
}


class ChessFacTORY implements GameFacTORY{

    @Override
    public Game getGame() {
        return new Chess();
    }
}
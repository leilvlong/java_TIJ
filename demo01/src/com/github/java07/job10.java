package com.github.java07;

public class job10 {
    public static void paleGame(GameFactory getGame){
        Game game = getGame.getGame();
        while (game.move());
    }

    public static void main(String[] args) {
        paleGame(Checkers.gameFactory);
        paleGame(Chess.gameFactory);
    }
}


interface Game{
    boolean move();
}


interface GameFactory{
    Game getGame();
}


class Checkers implements Game{
    private Checkers() {
    }

    private int moves = 0;

    private final static int  MOVES = 3;

    @Override
    public boolean move() {
        System.out.println("Checkers moves : " + moves);
        return ++moves != MOVES;
    }

    public static GameFactory gameFactory = new GameFactory() {
        @Override
        public Game getGame() {
            return new Checkers();
        }
    };
}


class Chess implements Game{
    private Chess() {
    }

    private int moves = 0;

    private final static int  MOVES = 4;

    @Override
    public boolean move() {
        System.out.println("Chess moves : " + moves);
        return ++moves != MOVES;
    }

    public static GameFactory gameFactory = new GameFactory() {
        @Override
        public Game getGame() {
            return new Chess();
        }
    };
}
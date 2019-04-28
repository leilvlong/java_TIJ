package com.github.java06;

/*
如果job14 表示一段更为复杂的代码
这种模式将可以更多的复用代码
job14.palygame(一个新的工厂对象(新的Game接口的实现))
核心思想依然是解耦

总结:
    class 是一套数据类型规范
    而接口则是一套行为模式规范
        任何方法或构造器都可以接收参数,其参数都可以是接口类型或数据类型
        这意味着任何类都都可以对该方法(构造器)的参数进行适配
        在编写方法或设计类时更应当思考好你所要接收的是一个行为还是一种数据类型
    接口具有一定是抽象性的性质 而任何抽象性都应是真正的需求产生的
    只要有可能就去创建接口和工厂,这种逻辑看起来好像是因为需要使用不同的具体实现而添加的抽象性
    由此带来更多的接口和工厂的间接性
    当必需时,应当考虑重构接口而非到处添加额外级别的间接性 这种间接性会带来额外的复杂性

接口是一种重要的工具,但因易于使用而导致滥用



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
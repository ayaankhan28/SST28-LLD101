package com.example.snakeladder;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Snake> snakes = new ArrayList<>();
        snakes.add(new Snake(99, 54));
        snakes.add(new Snake(70, 55));
        snakes.add(new Snake(52, 42));
        snakes.add(new Snake(25, 2));
        snakes.add(new Snake(95, 72));

        List<Ladder> ladders = new ArrayList<>();
        ladders.add(new Ladder(6, 25));
        ladders.add(new Ladder(11, 40));
        ladders.add(new Ladder(60, 85));
        ladders.add(new Ladder(46, 90));
        ladders.add(new Ladder(17, 69));

        Board board = new Board(100, snakes, ladders);

        Dice dice = new Dice(1);

        List<Player> players = new ArrayList<>();
        players.add(new Player("Alice"));
        players.add(new Player("Bob"));
        players.add(new Player("Charlie"));

        Game game = new Game(board, dice, players);
        game.play();
    }
}

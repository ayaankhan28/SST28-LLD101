package com.example.snakeladder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final int size;
    private final Map<Integer, Snake> snakes;
    private final Map<Integer, Ladder> ladders;

    public Board(int size, List<Snake> snakeList, List<Ladder> ladderList) {
        this.size = size;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();

        for (Snake snake : snakeList) {
            snakes.put(snake.getHead(), snake);
        }

        for (Ladder ladder : ladderList) {
            ladders.put(ladder.getStart(), ladder);
        }
    }

    public int getSize() {
        return size;
    }

    public int getNewPosition(int position) {
        if (snakes.containsKey(position)) {
            Snake snake = snakes.get(position);
            System.out.println("  Bitten by snake at " + position + "! Going down to " + snake.getTail());
            return snake.getTail();
        }

        if (ladders.containsKey(position)) {
            Ladder ladder = ladders.get(position);
            System.out.println("  Climbing ladder at " + position + "! Going up to " + ladder.getEnd());
            return ladder.getEnd();
        }

        return position;
    }

    public boolean isValidPosition(int position) {
        return position >= 0 && position <= size;
    }
}

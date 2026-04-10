package com.example.snakeladder;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private final Board board;
    private final Dice dice;
    private final Queue<Player> players;
    private Player winner;

    public Game(Board board, Dice dice, List<Player> playerList) {
        this.board = board;
        this.dice = dice;
        this.players = new LinkedList<>(playerList);
        this.winner = null;
    }

    public void play() {
        System.out.println("=== Game Started ===");
        System.out.println("Board size: " + board.getSize());
        System.out.println("Players: " + players.size());
        System.out.println();

        while (winner == null) {
            Player currentPlayer = players.poll();
            playTurn(currentPlayer);

            if (currentPlayer.getPosition() == board.getSize()) {
                winner = currentPlayer;
            } else {
                players.offer(currentPlayer);
            }
        }

        System.out.println("\n=== Game Over ===");
        System.out.println("Winner: " + winner.getName());
    }

    private void playTurn(Player player) {
        int diceRoll = dice.roll();
        int currentPosition = player.getPosition();
        int newPosition = currentPosition + diceRoll;

        System.out.println(player.getName() + " rolled " + diceRoll);

        if (newPosition > board.getSize()) {
            System.out.println("  Cannot move - would exceed board size");
            return;
        }

        newPosition = board.getNewPosition(newPosition);
        player.setPosition(newPosition);

        System.out.println("  " + player.getName() + " moved from " + currentPosition + " to " + newPosition);
        System.out.println();
    }

    public Player getWinner() {
        return winner;
    }
}

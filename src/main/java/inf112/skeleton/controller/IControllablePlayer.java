package inf112.skeleton.controller;

import inf112.skeleton.model.GameState;

public interface IControllablePlayer {
    /**
     * moves the player a spesified number of rows and columns, if possible
     * 
     * @param row the row that is moved
     * @param col the column that is moved
     * @return true if move is possible
     */
    boolean movePlayer(int row, int col);

    /**
     * @return the game state
     *         Active Game
     *         Game Over
     */
    GameState getGameState();
}
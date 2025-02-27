package inf112.skeleton.controller;

import inf112.skeleton.model.GameState;

public interface iControllableGameModel {
    /**
     * moves the player y a spesified number of rows and columns, if possible
     * 
     * @param row the row that is moved
     * @param col the column that is moved
     * @return true if move is possible
     */
    boolean movePlayer(int row, int col);

    /**
     * moves the player upwards if possible
     * 
     * @return the new position
     */
    boolean upPlayer();

    /**
     * @return the game state
     *         Active Game
     *         Game Over
     */
    GameState getGameState();

    /**
     * setting the game state
     * 
     * @param gameState, the new game state
     */
    void setGameState(GameState gameState);

    /**
     * @return the speed of the jumping player
     */
    int getSpeed();
}
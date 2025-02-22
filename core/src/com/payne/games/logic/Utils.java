package com.payne.games.logic;

import com.badlogic.gdx.math.GridPoint2;
import com.payne.games.gameObjects.GameObject;
import com.payne.games.map.tiles.Tile;


/**
 * Purely static class of Utilities.
 */
public final class Utils { /* Can't be inherited. */

    private Utils() { } /* Can't be instantiated. (Can't touch this!) */


    /**
     * To obtain the Pixel coordinate equivalent of a Tile coordinate.
     *
     * @param inputCoord A Tile coordinate.
     * @return Its corresponding World-Pixel coordinate.
     */
    public static int tileToPixels(int inputCoord) {
        return GameLogic.AESTHETIC_OFFSET + inputCoord*GameLogic.TILE_SIZE;
    }

    /**
     * To obtain the difference between two tiles, when moving from the first to the second Tile.
     * For example: first=(2,3) and second=(5,2) give output=(3,-1).
     *
     * @param first origin Tile.
     * @param second destination Tile.
     * @return the GridPoint2 object that contains the 2D distance that separates the input.
     */
    public static GridPoint2 deltaOfTiles(Tile first, Tile second) {
        return new GridPoint2(second.getX() - first.getX(), second.getY() - first.getY());
    }

    /**
     * Gives the straight-line distance, in tile units, between two points.
     *
     * @param from the From coordinate.
     * @param to the To coordinate.
     * @return straight-line distance.
     */
    public static double straightDistanceBetweenMiddleOfPoints(GridPoint2 from, GridPoint2 to) {
        double deltaX = Math.pow(to.x - from.x, 2);
        double deltaY = Math.pow(to.y - from.y, 2);

        return Math.sqrt(deltaX + deltaY);
    }

    /**
     * Gives the straight-line distance, in tile units, between two Tiles.
     *
     * @param from the From coordinate.
     * @param to the To coordinate.
     * @return straight-line distance.
     */
    public static double straightDistanceBetweenMiddleOfPoints(Tile from, Tile to) {
        double deltaX = Math.pow(to.getX() - from.getX(), 2);
        double deltaY = Math.pow(to.getY() - from.getY(), 2);

        return Math.sqrt(deltaX + deltaY);
    }

    /**
     * Gives the straight-line distance, in tile units, between two GameObjects.
     * Uses the middle point of the Tile on which the Actors are standing.
     *
     * @param from the From object.
     * @param to the To object.
     * @return straight-line distance.
     */
    public static double straightDistanceBetweenObjects(GameObject from, GameObject to) {
        double deltaX = Math.pow(to.getX() - from.getX(), 2);
        double deltaY = Math.pow(to.getY() - from.getY(), 2);

        return Math.sqrt(deltaX + deltaY);
    }

    /**
     * To know if two GameObjects occupy the same Tile.<br>
     * Uses the "movingTo()" variable to get the source's position.
     *
     * @param from the From object.
     * @param to the To object.
     * @return 'true' only if they are on the same Tile.
     */
    public static boolean occupySameTile(GameObject from, GameObject to) {
        return (from.getMovingToX() == to.getX()) && (from.getMovingToY() == to.getY());
    }
}

package com.payne.games.inputProcessors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.payne.games.logic.GameLogic;
import com.payne.games.logic.MapController;


/**
 * The `float x` are the same as MyInputProcessor's `screenX` and others.
 *
 * Left-click:              Button = 0
 * Right-click:             Button = 1
 * Middle-scroll-click:     Button = 2
 */
public class MyGestureListener implements GestureDetector.GestureListener {
    private OrthographicCamera camera;
    private MapController mapController;

    private final boolean DEBUG_PRINT = false;


    public MyGestureListener(OrthographicCamera camera, MapController mapController) {
        this.camera = camera;
        this.mapController = mapController;
    }


    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if(DEBUG_PRINT) System.out.println("touchDown: " + x + "," + y + " | pointer: " + pointer + " | button: " + button);
        return false;
    }

    /**
     * Converts a click's position into a Tile coordinate.
     *
     * @param x screen x-coordinate
     * @param y screen y-coordinate
     * @param count ?
     * @param button left(0), right(1), and middle(2)         mouse-click.
     * @return 'true' only if the event shouldn't be passed to the next InputProcessor.
     */
    @Override
    public boolean tap(float x, float y, int count, int button) {
        if(DEBUG_PRINT) System.out.println("tap: " + x + "," + y + " | count: " + count + " | button: " + button);

//        final double OFFSET = GameLogic.AESTHETIC_OFFSET;
//        final double ZOOM = camera.zoom;
//
//        double coordX = (camera.position.x + x*ZOOM - OFFSET - ((double)camera.viewportWidth/2)*ZOOM)  / GameLogic.TILE_WIDTH;
//        double coordY = (camera.position.y - y*ZOOM - OFFSET + ((double)camera.viewportHeight/2)*ZOOM) / GameLogic.TILE_HEIGHT;

        final double OFFSET = GameLogic.AESTHETIC_OFFSET / GameLogic.TILE_WIDTH;
        Vector3 vec = camera.unproject(new Vector3(x, y, 1));
        double coordX = ((double)vec.x / GameLogic.TILE_WIDTH) - OFFSET;
        double coordY = ((double)vec.y / GameLogic.TILE_HEIGHT) - OFFSET;

        System.out.println("Tile coordinate: (" + (int)coordX + ", " + (int)coordY + ")");

        // todo: be wiser! tap.location: if monster -> AttackAction, if Door -> DoorAction, if Item -> PickUpAction
        mapController.playerTapped((int)coordX, (int)coordY);

        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        if(DEBUG_PRINT) System.out.println("longPress: " + x + "," + y);
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if(DEBUG_PRINT) System.out.println("fling | velocityX: " + velocityX + " | velocityY: " + velocityY + " | button: " + button);
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if(DEBUG_PRINT) System.out.println("pan: " + x + "," + y + " | deltaX: " + deltaX + " | deltaY: " + deltaY);

//        // todo: prevent drag outside of map regions
//        if(camera.position.x + dX > camera.viewportWidth/2) { // prevent going further left
//            System.out.println("YES");
//            down_relativePixelCoord_TL_X = screenX;
//        } else {
//            System.out.println("NO");
//            dX = 0;
//        }

        camera.translate((-deltaX)*camera.zoom, deltaY*camera.zoom);
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if(DEBUG_PRINT) System.out.println("panStop: " + x + "," + y + " | pointer: " + pointer + " | button: " + button);
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        if(DEBUG_PRINT) System.out.println("zoom | initialDistance: " + initialDistance + " | distance: " + distance);
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        // todo: implement "Zoom" for mobiles
        if(DEBUG_PRINT) System.out.println("pinch");
        return false;
    }

    @Override
    public void pinchStop() {
        if(DEBUG_PRINT) System.out.println("pinchStop");
    }
}

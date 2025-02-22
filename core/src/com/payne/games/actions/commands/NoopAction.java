package com.payne.games.actions.commands;

import com.payne.games.actions.Action;
import com.payne.games.gameObjects.actors.Actor;


/**
 * Wastes a turn.
 */
public class NoopAction extends Action {


    public NoopAction(Actor source) {
        super(source);
    }


    @Override
    public boolean executeAction() {
        return true;
    }

    @Override
    public float getFatigueCostMultiplier() {
        return 1;
    }

    @Override
    public String toString() {
        return "NoopAction{" +
                "source=" + source +
                '}';
    }
}

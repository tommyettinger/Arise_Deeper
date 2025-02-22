package com.payne.games.actions.commands;

import com.payne.games.actions.Action;
import com.payne.games.gameObjects.actors.Actor;
import com.payne.games.gameObjects.actors.ActorState;


/**
 * Awakens a sleeping Actor.
 */
public class WakeUpAction extends Action {


    public WakeUpAction(Actor source) {
        super(source);
    }


    @Override
    public boolean executeAction() {
        System.out.println("Awakened: " + getSource().toString());
        source.setState(ActorState.IDLE);
        return true;
    }

    @Override
    public float getFatigueCostMultiplier() {
        return 1.4f;
    }

    @Override
    public String toString() {
        return "WakeUpAction{" +
                "source=" + source +
                '}';
    }
}

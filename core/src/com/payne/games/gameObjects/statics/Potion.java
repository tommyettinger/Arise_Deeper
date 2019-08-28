package com.payne.games.gameObjects.statics;

import com.payne.games.gameObjects.actors.Actor;
import com.payne.games.inventory.IStackable;
import com.payne.games.logic.Utils;
import com.payne.games.turns.actions.PickUpAction;


public abstract class Potion extends Static implements IStackable, IConsumable {


    public Potion(int x, int y) {
        super(x, y);
    }


    @Override
    public boolean tryInteractionFrom(Actor source) {
        boolean onSameTile = Utils.occupySameTile(source, this);
        if (onSameTile) // Actor is in range: pick up
            source.addAction(new PickUpAction(source, this));
        return onSameTile;
    }

    @Override
    public int getMaximumStackAmount() {
        return 5;
    }
}

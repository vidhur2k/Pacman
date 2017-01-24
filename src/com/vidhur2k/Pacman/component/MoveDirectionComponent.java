package com.vidhur2k.Pacman.component;

import com.almasb.ents.component.ObjectComponent;
import com.almasb.ents.component.Required;
import com.vidhur2k.Pacman.control.MoveDirection;

/**
 * Created by vidhur2k on 1/17/2017.
 */
public class MoveDirectionComponent extends ObjectComponent<MoveDirection> {

    public MoveDirectionComponent(MoveDirection initialValue) {
        super(initialValue);
    }

}

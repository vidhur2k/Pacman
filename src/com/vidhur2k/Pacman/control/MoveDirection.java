package com.vidhur2k.Pacman.control;

/**
 * Created by vidhur2k on 1/13/2017.
 */
public enum MoveDirection {

    UP, RIGHT, DOWN, LEFT;

    MoveDirection next()
    {
        return values()[(ordinal() + 1) % values().length];
    }

    MoveDirection previous()
    {
        return values()[(ordinal() - 1) % values().length];
    }
}

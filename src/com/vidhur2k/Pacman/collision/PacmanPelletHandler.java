package com.vidhur2k.Pacman.collision;

import com.almasb.ents.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.vidhur2k.Pacman.type.EntityType;
import static com.vidhur2k.Pacman.PacmanApp.*;
/**
 * Created by vidhur2k on 1/11/2017.
 */
public class PacmanPelletHandler extends CollisionHandler {

    public PacmanPelletHandler()
    {
        super(EntityType.PLAYER, EntityType.PELLET);
    }

    @Override
    protected void onCollisionBegin(Entity player, Entity pellet) {

        pellet
                .removeFromWorld();

        score
                .set(score.get() + 10);
    }
}

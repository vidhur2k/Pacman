package com.vidhur2k.Pacman.control;

import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.BoundingBoxComponent;
import com.almasb.fxgl.entity.component.MainViewComponent;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.time.LocalTimer;
import com.vidhur2k.Pacman.Config.*;
import com.vidhur2k.Pacman.component.MoveDirectionComponent;
import javafx.util.Duration;


import static com.vidhur2k.Pacman.Config.*;

/**
 * Created by vidhur2k on 1/13/2017.
 */
public class PlayerControl extends AbstractControl {

    private GameEntity gameEntity;
    private MoveDirection moveDirection;
    private MainViewComponent mainViewComponent;
    private PhysicsComponent physicsComponent;
    private PositionComponent positionComponent;
    private BoundingBoxComponent boundingBoxComponent;

    private Input input = FXGL.getInput();

    private boolean hasStarted = input.isHeld(UP_KEY) || input.isHeld(DOWN_KEY) || input.isHeld(LEFT_KEY) ||  input.isHeld(RIGHT_KEY);

    private LocalTimer localTimer;
    private int i;
    private String[] textures;

    // TODO: Implement boolean flag for texture animation.

    @Override
    public void onAdded(Entity entity) {

        physicsComponent = Entities.getPhysics(entity);
        positionComponent = Entities.getPosition(entity);
        boundingBoxComponent = Entities.getBBox(entity);
        mainViewComponent = Entities.getMainView(entity);

        moveDirection = MoveDirection.RIGHT;

        entity.addComponent(new MoveDirectionComponent(moveDirection));

        localTimer = FXGL.newLocalTimer();
        localTimer.capture();

        i = 0;

        textures = null;
    }

    private double speed = 0;

    @Override
    public void onUpdate(Entity entity, double v) {

        switch (moveDirection)
        {
            case UP:
                textures = PACMAN_UP_TEXTURES;
                break;

            case DOWN:
                textures = PACMAN_DOWN_TEXTURES;
                break;

            case LEFT:
                textures = PACMAN_LEFT_TEXTURES;
                break;

            case RIGHT:
                textures = PACMAN_RIGHT_TEXTURES;
                break;
        }

        if(localTimer.elapsed(Duration.millis(150)))
        {
            mainViewComponent
                    .setTexture(textures[i++ % textures.length]);

            localTimer.capture();
        }

//
//        if (hasStarted) {
//
//            moveDirection = input.isHeld(UP_KEY) ? MoveDirection.UP :
//                            input.isHeld(DOWN_KEY) ? MoveDirection.DOWN :
//                            input.isHeld(LEFT_KEY) ? MoveDirection.LEFT :
//                            input.isHeld(RIGHT_KEY) ? MoveDirection.RIGHT :
//                                            null;
//
//            entity
//                    .addComponent(new MoveDirectionComponent(moveDirection));
//
//        }



        speed = v * 3600;

        if (positionComponent.getX() < 0) {
            positionComponent.setX(BLOCK_SIZE * MAP_SIZE - boundingBoxComponent.getWidth() - 5);
        }

        if (boundingBoxComponent.getMaxXWorld() > BLOCK_SIZE * MAP_SIZE) {
            positionComponent.setX(0);
        }
    }

//    public void initSwallowAnimation()
//    {

//
////        String var = "PACMAN_" + moveDirection.name() + "_TEXTURES";
////
////        try {
////
////            Field field = Config.class.getField(var);
////
////            textures = (String[]) field.get(new Config());
////
////        } catch (Exception e) {}
//
//        localTimer.capture();
//
//    }

    public void moveUp()
    {
        moveDirection = MoveDirection.UP;

        physicsComponent
                .setLinearVelocity(0, -5 * speed);

        mainViewComponent
                .setTexture(PACMAN_UP_TEXTURES[0]);
    }

    public void moveDown()
    {
        moveDirection = MoveDirection.DOWN;

        physicsComponent
                .setLinearVelocity(0, 5 * speed);

        mainViewComponent
                .setTexture(PACMAN_DOWN_TEXTURES[0]);
    }

    public void moveLeft()
    {
        moveDirection = MoveDirection.LEFT;

        physicsComponent
                .setLinearVelocity(-5 * speed, 0);

        mainViewComponent
                .setTexture(PACMAN_LEFT_TEXTURES[0]);
    }

    public void moveRight()
    {
        moveDirection = MoveDirection.RIGHT;

        physicsComponent
                .setLinearVelocity(5 * speed, 0);

        mainViewComponent
                .setTexture(PACMAN_RIGHT_TEXTURES[0]);
    }

    public void stop()
    {
        physicsComponent
                .setLinearVelocity(0, 0);
    }
}

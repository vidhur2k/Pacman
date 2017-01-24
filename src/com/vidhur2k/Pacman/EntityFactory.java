package com.vidhur2k.Pacman;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.entity.component.MainViewComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.vidhur2k.Pacman.component.MoveDirectionComponent;
import com.vidhur2k.Pacman.control.MoveDirection;
import com.vidhur2k.Pacman.control.PlayerControl;
import com.vidhur2k.Pacman.type.EntityType;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jbox2d.dynamics.BodyType;


import static com.vidhur2k.Pacman.Config.*;

/**
 * Created by vidhur2k on 1/10/2017.
 */
public class EntityFactory {

    public static GameEntity makePacman(double x, double y)
    {
        PhysicsComponent physicsComponent = new PhysicsComponent();

        physicsComponent
                .setBodyType(BodyType.DYNAMIC);

        return Entities
                .builder()
                .at(x * BLOCK_SIZE, y * BLOCK_SIZE)
                .bbox(new HitBox("BODY", BoundingShape.circle(25)))
                .type(EntityType.PLAYER)
                .viewFromTexture(PACMAN_RIGHT_TEXTURES[0])
                .with(physicsComponent)
                .with(new CollidableComponent(true))
                .with(new PlayerControl())
                .build();
    }

    public static GameEntity makePellet(double x, double y)
    {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent
                .setBodyType(BodyType.STATIC);

        GameEntity candy = Entities
                                    .builder()
                                    .at(x * BLOCK_SIZE, y * BLOCK_SIZE)
                                    .viewFromTextureWithBBox(CANDY_TEXTURE)
                                    .with(new CollidableComponent(true))
                                    .with(physicsComponent)
                                    .type(EntityType.PELLET)
                                    .build();

        candy
                .translateX(BLOCK_SIZE / 4);
        candy
                .translateY(BLOCK_SIZE / 4);

        return candy;
    }

    public static GameEntity makeWall(double x, double y)
    {
        PhysicsComponent physicsComponent = new PhysicsComponent();
        physicsComponent
                .setBodyType(BodyType.STATIC);

        return Entities
                .builder()
                .at(x * BLOCK_SIZE, y * BLOCK_SIZE)
                .with(new CollidableComponent(true))
                .with(physicsComponent)
                .type(EntityType.BLOCK)
                .viewFromTextureWithBBox(WALL_TEXTURE)
                .build();

    }
}

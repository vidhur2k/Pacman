package com.vidhur2k.Pacman;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.BoundingBoxComponent;
import com.almasb.fxgl.entity.component.MainViewComponent;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.vidhur2k.Pacman.component.MoveDirectionComponent;
import com.vidhur2k.Pacman.control.PlayerControl;
import com.vidhur2k.Pacman.type.EntityType;
import javafx.scene.input.KeyCode;

import java.security.Key;

/**
 * Created by vidhur2k on 1/10/2017.
 */
public class Config {

    public static final int BLOCK_SIZE = 50;

    public static final int MAP_SIZE = 21;

    public static final int UI_SIZE = 200;


    public static final KeyCode UP_KEY = KeyCode.W;

    public static final KeyCode DOWN_KEY = KeyCode.S;

    public static final KeyCode LEFT_KEY = KeyCode.A;

    public static final KeyCode RIGHT_KEY = KeyCode.D;


    public static final String PACMAN_DEFAULT_TEXTURE = "pacmanUp.png";

    public static final String[] PACMAN_RIGHT_TEXTURES = {"pacmanRight.png", "pacman2Right.png"};

    public static final String[] PACMAN_LEFT_TEXTURES = {"pacmanLeft.png", "pacman2Left.png"};

    public static final String[] PACMAN_UP_TEXTURES = {"pacmanUp.png", "pacman2Up.png"};

    public static final String[] PACMAN_DOWN_TEXTURES = {"pacmanDown.png", "pacman2Down.png"};


    public static final String CANDY_TEXTURE = "pellet.png";

    public static final String WALL_TEXTURE = "wall.png";

}

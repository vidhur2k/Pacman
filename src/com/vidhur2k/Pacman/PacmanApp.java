package com.vidhur2k.Pacman;

import com.almasb.ents.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.RenderLayer;
import com.almasb.fxgl.gameplay.Level;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.parser.TextLevelParser;
import com.almasb.fxgl.settings.GameSettings;
import com.vidhur2k.Pacman.collision.PacmanPelletHandler;
import com.vidhur2k.Pacman.control.PlayerControl;
import com.vidhur2k.Pacman.type.EntityType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


import static com.vidhur2k.Pacman.Config.*;
/**
 * Created by vidhur2k on 1/10/2017.
 */
public class PacmanApp extends GameApplication {

    /**
     * This is my attempt to create a replica of PacmanApp.
     * The textures were obtained from opengameart.org/content/pacman.
     *
     */

    public static IntegerProperty score;

    private PlayerControl playerControl;

    public static GameEntity pacman()
    {
       return (GameEntity) FXGL
                            .getApp()
                            .getGameWorld()
                            .getEntitiesByType(EntityType.PLAYER)
                            .get(0);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {

        gameSettings
                .setWidth(BLOCK_SIZE * MAP_SIZE + UI_SIZE);
        gameSettings
                .setHeight(BLOCK_SIZE * MAP_SIZE - UI_SIZE  / 2);
        gameSettings
                .setTitle("PACMAN");
        gameSettings
                .setIntroEnabled(false);
        gameSettings
                .setMenuEnabled(false);
        gameSettings
                .setVersion("0.1.1");
        gameSettings
                .setProfilingEnabled(false);
    }


    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("UP") {
            @Override
            protected void onAction() {
                playerControl.moveUp();
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                playerControl.stop();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("DOWN") {
            @Override
            protected void onAction() {
                playerControl.moveDown();
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                playerControl.stop();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("LEFT") {
            @Override
            protected void onAction() {
                playerControl.moveLeft();
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                playerControl.stop();

            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("RIGHT") {
            @Override
            protected void onAction() {
                playerControl.moveRight();
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
                playerControl.stop();
            }
        }, KeyCode.D);
    }


    @Override
    protected void initAssets() {

        getAssetLoader()
                .cache();
    }

    @Override
    protected void initGame() {

        TextLevelParser textLevelParser = new TextLevelParser();

        textLevelParser
                .setEmptyChar(' ');

        textLevelParser.addEntityProducer('0', EntityFactory::makePellet);

        textLevelParser.addEntityProducer('1', EntityFactory::makeWall);

        textLevelParser.addEntityProducer('P', EntityFactory::makePacman);

        Level level = textLevelParser
                .parse("level0.txt");

        getGameWorld()
                .setLevel(level);

        Entity player = level
                            .getEntities()
                            .stream()
                            .filter(x -> x.hasControl(PlayerControl.class))
                            .findAny()
                            .get();

        getGameWorld()
                .addEntities(Entities.makeScreenBounds(50));

        playerControl = player.getControlUnsafe(PlayerControl.class);

        score = new SimpleIntegerProperty();

        initBackground();
    }

    private void initBackground()
    {
        GameEntity background =
                Entities
                .builder()
                .type(EntityType.BACKGROUND)
                .viewFromNode(new Rectangle(getWidth(), getHeight(), Color.rgb(165, 169, 175)))
                .buildAndAttach(getGameWorld());

        background
                .setRenderLayer(RenderLayer.BACKGROUND);
    }

    @Override
    protected void initPhysics() {

        getPhysicsWorld()
                .setGravity(0, 0);

        getPhysicsWorld()
                .addCollisionHandler(new PacmanPelletHandler());
    }

    @Override
    protected void initUI() {

        Text gameTitle = new Text();

        gameTitle
                .setText("PACMAN");
        gameTitle
                .setTranslateX(21 * BLOCK_SIZE);

        gameTitle
                .setTranslateY(50);

        gameTitle
                .setFont(Font.font(36));

        gameTitle
                .setFill(Color.GREEN);


        Text scoreText = new Text();

        scoreText
                .setTranslateX(20.5 * BLOCK_SIZE);
        scoreText
                .setTranslateY(100);
        scoreText
                .setFont(Font.font(36));
        scoreText
                .setFill(Color.AQUAMARINE);
        scoreText
                .textProperty()
                .bind(score.asString("Score: %d"));

        getGameScene()
                .addUINodes(gameTitle, scoreText);
    }

    @Override
    protected void onUpdate(double v) {

    }


    public static void main(String[] args) {

        try {
            launch(args);
        }
        catch (Exception e) {

            e.printStackTrace();
        }
    }
}

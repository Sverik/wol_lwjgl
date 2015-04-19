package com.po.conbanned.screens;

import com.badlogic.gdx.Screen;

import com.po.conbanned.ConBanned;
import com.po.conbanned.controller.AttackerController;
import com.po.conbanned.controller.HoverController;
import com.po.conbanned.model.World;
import com.po.conbanned.view.WorldRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen, InputProcessor {

    private ConBanned game;
    private World 			world;
    private WorldRenderer 	renderer;
    private AttackerController attackerController;
    private HoverController landmineController;

    private int width, height;

    public GameScreen(ConBanned conBanned) {
        this.game = conBanned;
    }

    @Override
    public void show() {
        world = new World();
        renderer = new WorldRenderer(world);
        attackerController = new AttackerController(world);
        landmineController = new HoverController(world, renderer);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        boolean running = attackerController.update(delta);
        if (! running) {
            game.setScreen(new GameOverScreen(game, world.killCount));
        }
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        renderer.setSize(width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }

    // * InputProcessor methods ***************************//

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        landmineController.hover(x, y);
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        landmineController.place(x, y);
        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        landmineController.hover(x, y);
        return true;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }
}

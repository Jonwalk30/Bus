package com.badlogic.bus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Game extends ApplicationAdapter {

    private SpriteBatch staticBatch;
    private SpriteBatch mobileBatch;

    private U1Service bath;

    private long startCounter = 0;

    @Override
    public void create () {

        Gdx.graphics.setWindowedMode(1200,600);

        staticBatch = new SpriteBatch();
        mobileBatch = new SpriteBatch();

        bath = new U1Service();

        drawStaticBatch();

    }

    private void drawLines() {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.PURPLE);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        Gdx.gl.glLineWidth(2);
        for (Road road : bath.busRoute.roads) {

            shapeRenderer.line((float) (w * road.start.x)/100, (float)  (h * road.start.y)/100,
                    (float)  (w * road.end.x)/100, (float) (h * road.end.y)/100);

        }

        shapeRenderer.end();

    }

    private void drawStaticBatch() {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        ArrayList<Sprite> staticSprites = new ArrayList<Sprite>();

        BitmapFont font = new BitmapFont();

        for (Road road : bath.busRoute.roads) {

            for (BusStop busStop : road.busStops) {
                Sprite sprite = new Sprite(busStop.image);
                sprite.setSize(w / 25, h / 15);
                float xPos = ((w * (float) busStop.location(road).x)/100  - (w/50));
                float yPos = ((h * (float) busStop.location(road).y)/100 - (h/80));
                sprite.setPosition(xPos, yPos);
                staticSprites.add(sprite);
            }

        }

        staticBatch.begin();
        for (Sprite sprite : staticSprites) {
            sprite.draw(staticBatch);
        }

        drawWaitingStudents(w, h, font);

        drawClock(w, h, font);

        staticBatch.end();

    }

    private void drawClock(float w, float h, BitmapFont font) {

        float xPos = w * (float) 0.1;
        float yPos = h * (float) 0.9;

        String timeAsString = bath.busRoute.time.hour+":"+bath.busRoute.time.minute+":"+bath.busRoute.time.second;

        GlyphLayout layout = new GlyphLayout(
                font,
                timeAsString,
                Color.BLACK,
                0,
                Align.center,
                false
        );
        font.draw(staticBatch, layout, xPos, yPos);

    }

    private void drawWaitingStudents(float w, float h, BitmapFont font) {
        // Draw the number of people waiting at the stop to screen
        for (Road road : bath.busRoute.roads) {
            for (BusStop busStop : road.busStops) {
                float modifier = 45;
                float xPos = ((w * (float) busStop.location(road).x)/100  - (w/50));
                float yPos = ((h * (float) busStop.location(road).y)/100 - (h/80));
                GlyphLayout layout = new GlyphLayout(
                        font,
                        Integer.toString(busStop.studentCnt()),
                        busStop.color(),
                        0,
                        Align.center,
                        false
                );
                font.draw(staticBatch, layout, xPos + modifier, yPos + modifier);
            }
        }
    }

    private void drawMobileBatch() {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        BitmapFont font = new BitmapFont();

        ArrayList<Sprite> mobileSprites = new ArrayList<Sprite>();

        for (Bus bus : bath.busRoute.buses) {
            Sprite sprite = new Sprite(bus.image);
            sprite.setSize(w / 25, h / 15);
            float xPos = (((w * (float) bus.location().x)/100) - (w/50));
            float yPos = (((h * (float) bus.location().y)/100) - (h/30));
            sprite.setPosition(xPos, yPos);
            mobileSprites.add(sprite);
        }

        mobileBatch.begin();
        for (Sprite sprite : mobileSprites) {
            sprite.draw(mobileBatch);
        }
        drawTravellingStudents(w, h, font);

        mobileBatch.end();

    }

    private void drawTravellingStudents(float w, float h, BitmapFont font) {
        // Draw the number of people sat on each bus
        for (Bus bus : bath.busRoute.buses) {
            float modifier = 30;
            float xPos = (((w * (float) bus.location().x)/100) - (w/50));
            float yPos = (((h * (float) bus.location().y)/100) - (h/30));
            GlyphLayout layout = new GlyphLayout(
                    font,
                    Integer.toString(bus.studentCnt()),
                    bus.color(),
                    0,
                    Align.center,
                    false
            );
            font.draw(mobileBatch, layout, xPos - modifier, yPos - modifier);
        }
    }


    @Override
    public void render () {

        Gdx.gl.glClearColor((float) 224/255, (float) 224/255, (float) 224/255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawLines();
        drawStaticBatch();
        drawMobileBatch();

        // 1 billion nanoseconds = 1 sec.
        long timeBetweenSeconds = 100000000;
        if (TimeUtils.timeSinceNanos(startCounter) > timeBetweenSeconds) {

            bath.busRoute.simulateASecond();

            startCounter = TimeUtils.nanoTime();
        }

    }

    @Override
    public void dispose () {
        staticBatch.dispose();
        mobileBatch.dispose();
        U1Service.busImg.dispose();
        U1Service.busStopImg.dispose();
    }

}
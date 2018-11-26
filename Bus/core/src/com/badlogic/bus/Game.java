package com.badlogic.bus;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

public class Game extends ApplicationAdapter {

    private SpriteBatch staticBatch;
    private SpriteBatch mobileBatch;

    private BusRoute bath;

    private Texture busImg;
    private Texture busStopImg;

    private ArrayList<Sprite> staticSprites = new ArrayList<Sprite>();
    private ArrayList<Sprite> mobileSprites = new ArrayList<Sprite>();

	private Time startTime = new Time(0,0,0);
    private ArrayList<Road> roads = new ArrayList<Road>();
    private ArrayList<Bus> buses = new ArrayList<Bus>();

    private long startCounter = 0;
    private long timeBetweenSeconds = 100000000; // 1 billion nanoseconds = 1 sec.

    @Override
	public void create () {

        Gdx.graphics.setWindowedMode(1200,600);

		staticBatch = new SpriteBatch();
        mobileBatch = new SpriteBatch();

		busImg = new Texture("bus.png");
        busStopImg = new Texture("bus stop.png");

        defineBath();

        drawStaticBatch();

    }

    public void drawLines() {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.PURPLE);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        Gdx.gl.glLineWidth(2);
        for (Road road : bath.roads) {

            shapeRenderer.line((float) (w * road.start.x)/100, (float)  (h * road.start.y)/100,
                    (float)  (w * road.end.x)/100, (float) (h * road.end.y)/100);

        }

        shapeRenderer.end();

    }

    public void drawStaticBatch() {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        staticSprites = new ArrayList<Sprite>();

        BitmapFont font = new BitmapFont();

	    for (Road road : bath.roads) {

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

        // Draw the number of people waiting at the stop to screen
        for (Road road : bath.roads) {
          for (BusStop busStop : road.busStops) {
            float modifier = 45;
            float xPos = ((w * (float) busStop.location(road).x)/100  - (w/50));
            float yPos = ((h * (float) busStop.location(road).y)/100 - (h/80));
            font.draw(staticBatch, Integer.toString(busStop.studentCnt()), xPos + modifier, yPos + modifier);
          }
        }

        staticBatch.end();

    }

    public void drawMobileBatch() {

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        mobileSprites = new ArrayList<Sprite>();

        for (Bus bus : bath.buses) {
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
        mobileBatch.end();

    }


    @Override
	public void render () {

		Gdx.gl.glClearColor((float) 224/255, (float) 224/255, (float) 224/255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		drawLines();
        drawStaticBatch();
		drawMobileBatch();

        if (TimeUtils.timeSinceNanos(startCounter) > timeBetweenSeconds) {

            bath.simulateASecond();
            startTime.increment();

            startCounter = TimeUtils.nanoTime();
        }

	}
	
	@Override
	public void dispose () {
		staticBatch.dispose();
		mobileBatch.dispose();
		busImg.dispose();
		busStopImg.dispose();
	}


    private void defineBath() {

        roads.add(norwoodAve());
        roads.add(bathwickRoad());
        roads.add(pultneyRoad());
        roads.add(clavertonSt());
        roads.add(ambury());
        roads.add(greenPark());
        roads.add(westmorelandRoad());
        roads.add(lowerOldfieldPark());
        roads.add(broughamHayes());
        roads.add(pinesWay());
        roads.add(greenParkBack());
        roads.add(amburyBack());
        roads.add(clavertonStBack());
        roads.add(pultneyRoadBack());
        roads.add(bathwickRoadBack());
        roads.add(norwoodAveBack());

        buses.add(new Bus(1, 100, norwoodAve(), busImg));
        buses.add(new Bus(1, 100, greenPark(), busImg));
        buses.add(new Bus(1, 100, clavertonStBack(), busImg));

        bath = new BusRoute(
                roads,
                buses,
                startTime);

        bath.buses.get(0).roadPosition = 0;
    }

    private Road norwoodAve() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        DestBusStop tempBusStop1 = new DestBusStop("-", 100, 0, busStopImg);

        tempBusStops.add(tempBusStop1);

        BusStop tempAveBusStop2 = new BusStop("-", 10, 50, busStopImg);

        tempBusStops.add(tempAveBusStop2);

        Road tempRoad = new Road(
                "-",
                new Location(90,50),
                new Location(90,25),
                (double) 10,
                tempBusStops,
                tempBusStop1);

        return tempRoad;
    }

    private Road norwoodAveBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        Road tempRoad = new Road(
                "-",
                new Location(90,25),
                new Location(90,50),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road bathwickRoad() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 20, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 25, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 50, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 75, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(90,25),
                new Location(50,75),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road bathwickRoadBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 5, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 35, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 65, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 95, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(50,75),
                new Location(90,25),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road pultneyRoad() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 75, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(50,75),
                new Location(50,35),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road pultneyRoadBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 5, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(50,35),
                new Location(50,75),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road clavertonSt() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 75, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(50,35),
                new Location(35,35),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road clavertonStBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 30, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(35,35),
                new Location(50,35),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road ambury() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 75, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(35,35),
                new Location(35,45),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road amburyBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        Road tempRoad = new Road(
                "-",
                new Location(35,45),
                new Location(35,35),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road greenPark() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 90, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(35,45),
                new Location(25,55),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road greenParkBack() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 80, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(25,55),
                new Location(35,45),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road westmorelandRoad() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 70, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(25,55),
                new Location(20,35),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road lowerOldfieldPark() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 20, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 60, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(20,35),
                new Location(10,45),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road broughamHayes() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        Road tempRoad = new Road(
                "-",
                new Location(10,45),
                new Location(12,55),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

    private Road pinesWay() {

        ArrayList<BusStop> tempBusStops = new ArrayList<BusStop>();

        BusStop tempAveBusStop = new BusStop("-", 10, 10, busStopImg);

        tempBusStops.add(tempAveBusStop);

        tempAveBusStop = new BusStop("-", 10, 40, busStopImg);

        tempBusStops.add(tempAveBusStop);

        Road tempRoad = new Road(
                "-",
                new Location(12,55),
                new Location(25,55),
                (double) 10,
                tempBusStops);

        return tempRoad;
    }

}

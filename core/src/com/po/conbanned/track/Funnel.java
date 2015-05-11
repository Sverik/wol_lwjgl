package com.po.conbanned.track;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.po.conbanned.model.Obstacle;
import com.po.conbanned.model.World;

public class Funnel extends TrackPiece {
	public Funnel(World world) {
		super(world);
		for (int x : new int[]{0, World.GRID_WIDTH}) {
			Rectangle rect = new Rectangle(x, 10, 50, 50);
			PolygonShape shape = new PolygonShape();
			shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2, new Vector2(-25, 0), (float) (Math.PI / 4d));

			add(new ObstacleDef(shape, rect.getCenter(new Vector2())));
		}

		Rectangle rect = new Rectangle(World.GRID_WIDTH / 2 - 2, 8, 4, 4);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2, new Vector2(-2, 0), 0);
		ObstacleDef trigger = new ObstacleDef(Obstacle.Type.TRIGGER, shape, rect.getCenter(new Vector2()));
		add(trigger);
	}

	@Override
	public float getLength() {
		return 100f;
	}
}

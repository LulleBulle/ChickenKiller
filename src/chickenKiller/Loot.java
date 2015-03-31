package chickenKiller;

import java.util.concurrent.TimeUnit;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;

public class Loot extends Task<ClientContext> {

	public Loot(ClientContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		return !ctx.groundItems.select().id(314).isEmpty()
				&& !ctx.players.local().inCombat()
				&& !ctx.players.local().inMotion();
	}

	public void execute() {
		GroundItem feather = ctx.groundItems.nearest().poll();
		if (feather.inViewport()) {
			feather.interact("Take");
			sleep(1);
			while (ctx.players.local().inMotion()) {
				sleep(200);

			}
		} else {
			ctx.camera.turnTo(feather);
			ctx.movement.step(feather);
		}

	}

	public void sleep(int time) {
		try {
			TimeUnit.MILLISECONDS.sleep(time);
		} catch (InterruptedException e) {

		}
	}
}

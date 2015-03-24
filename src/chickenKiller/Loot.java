package chickenKiller;

import java.util.concurrent.TimeUnit;


import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;

public class Loot extends Task<ClientContext> {

	public Loot(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return !ctx.groundItems.select().id(314).isEmpty();
	}

	@Override
	public void execute() {
		GroundItem feather = ctx.groundItems.nearest().poll();
		if (feather.inViewport()) {
			feather.interact("Take");
		} else {
			ctx.camera.turnTo(feather);
			ctx.movement.step(feather);
		}
	try{
		TimeUnit.SECONDS.sleep(2);
	} catch (InterruptedException e){
		
	}
		
	}

}

package chickenKiller;

import java.util.concurrent.TimeUnit;

import org.powerbot.script.rt4.*;


public class Kill extends Task<ClientContext> {
	private int[] chickenID = { 2692, 2693 };

	public Kill(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		
		return !ctx.npcs.select().id(chickenID).isEmpty() && !ctx.players.local().inCombat();
	}

	@Override
	public void execute() {
		Npc chicken = ctx.npcs.nearest().poll();
		if(chicken.inCombat()){
			return;
		}
		else if (chicken.inViewport()) {
			chicken.interact("Attack");
			while(ctx.players.local().inMotion()){
				
				try {
					TimeUnit.MILLISECONDS.sleep(20);
				} catch (InterruptedException e) {
					
				}
			}
			while(chicken.inCombat()){
				try {
					TimeUnit.MILLISECONDS.sleep(200);
				} catch (InterruptedException e) {

				}
			}
		} else {
			ctx.camera.turnTo(chicken);
			ctx.movement.step(chicken);
		}
	}

}

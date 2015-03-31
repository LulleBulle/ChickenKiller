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
		if(ctx.movement.energyLevel() > 30){
			ctx.movement.running(true);
			
		}
		Npc chicken = ctx.npcs.nearest().poll();
		if(chicken.inCombat()){
			return;
		}
		else if (chicken.inViewport()) {
			chicken.interact("Attack");
			sleep(2000);

			while(ctx.players.local().inMotion()){		
				sleep(200);
			}
			while(chicken.inCombat() && ctx.players.local().inCombat() && chicken.health() != 0){
				sleep(200);
			}
		} else {
			ctx.camera.turnTo(chicken);
			ctx.movement.step(chicken);
		}
	}
	
	public void sleep(int time){
		try {
			TimeUnit.MILLISECONDS.sleep(time);
		} catch (InterruptedException e) {
			
		}
	}
	}



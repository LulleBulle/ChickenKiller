package chickenKiller;

import org.powerbot.script.rt4.*;


public class Kill extends Task<ClientContext> {
	private int[] chickenID = { 2692, 2693 };

	public Kill(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		

		return !ctx.objects.select().id(chickenID).isEmpty();
	}

	@Override
	public void execute() {
		Npc chicken = ctx.npcs.nearest().poll();
		if (chicken.inViewport()) {
			chicken.interact("Attack");
		} else {
			ctx.camera.turnTo(chicken);
			ctx.movement.step(chicken);
		}
	}

}

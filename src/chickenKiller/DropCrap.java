package chickenKiller;

import java.util.concurrent.TimeUnit;

import org.powerbot.script.rt4.*;

public class DropCrap extends Task<ClientContext> {
	private int[] craps = { 526, 2138 };

	public DropCrap(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {

		return !ctx.inventory.select().id(craps).isEmpty()
				&& !ctx.players.local().inCombat();
	}

	@Override
	public void execute() {
		for(Item i: ctx.inventory.id(craps)){
			i.interact("Drop");
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {

			}
		}
	}

}

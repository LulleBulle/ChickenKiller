package chickenKiller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

@Script.Manifest(name = "Lulles ChickenKiller", description = "Kills chickens at Lumbridge Farm and loots them")
public class ChickenMain extends PollingScript<ClientContext> implements PaintListener {
	private Paint paint;
	private List<Task> tasks = new ArrayList<>();

	public void start() {
		tasks.addAll(Arrays.asList(new Kill(ctx), new Loot(ctx), new DropCrap(
				ctx)));
		paint = new Paint(ctx);

	}

	public void poll() {
		for (Task t : tasks) {
			if (t.activate()) {
				t.execute();
			}
		}

	}
	public void repaint(Graphics g){
		if(paint != null) paint.repaint((Graphics2D) g);
	}

}

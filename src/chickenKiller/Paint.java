package chickenKiller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Skills;

public class Paint implements MessageListener {

	private final Color color3 = new Color(255, 255, 255);
	private final Font font1 = new Font("Arial", 1, 15);
	private final Font font2 = new Font("Arial", 0, 14);
	ClientContext ctx;
	private final long initTime;
	private int hours = 0, minutes = 0, seconds = 0;
	private long currentTime = 0;
	private double runtimeHours = 0;
	private int strXpStart;
	private int hpXpStart;
	private int currentStr;
	private int currentHP;
	private int startFeathers;
	private int feathersGained;
	private int strLvls;
	private int hpLvls;

	public Paint(ClientContext ctx) {
		this.ctx = ctx;
		initTime = System.currentTimeMillis();
		strXpStart = ctx.skills.experience(Skills.STRENGTH);
		hpXpStart = ctx.skills.experience(Skills.HITPOINTS);
		startFeathers = ctx.inventory.id(314).count(true);
		feathersGained = 0;
	}

	public void updateValues() {
		currentTime = System.currentTimeMillis();
		runtimeHours = (double) (currentTime - initTime) / 3600000;
		hours = (int) (currentTime - initTime) / 3600000;
		minutes = (int) (currentTime - initTime) / 60000 - hours * 60;
		seconds = (int) (currentTime - initTime) / 1000 - hours * 3600
				- minutes * 60;
		currentHP = ctx.skills.experience(Skills.HITPOINTS) - hpXpStart;
		currentStr = ctx.skills.experience(Skills.STRENGTH) - strXpStart;
		feathersGained = ctx.inventory.id(314).count(true) - startFeathers;
	}

	public void repaint(Graphics2D g) {
		updateValues();

		Image background = getImage("http://i.imgur.com/UamKqAa.jpg");
		g.drawImage(background, 0, 339, null);
		g.setFont(font1);
		g.setColor(color3);
		g.setFont(font2);
		g.drawString("Runtime: " + hours + ":" + minutes + ":" + seconds, 10,
				400);
		g.drawString("Strength xp gained: " + currentStr, 10, 415);
		g.drawString("Strength levels gained: " + strLvls, 180, 415);
		g.drawString("Hitpoint xp gained: " + currentHP, 10, 430);
		g.drawString("Hitpoint levels gained: " + hpLvls, 180, 430);
		g.drawString("Feathers gained: " + feathersGained, 10, 445);

	}

	private Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void messaged(MessageEvent arg0) {
		String msg = arg0.getMessage();
		if (msg.contains("Strength")) {
			strLvls++;
		} else if (msg.contains("Hitp")) {
			hpLvls++;
		}

	}
}

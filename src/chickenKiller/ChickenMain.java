package chickenKiller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

@Script.Manifest(name="Lulles ChickenKiller", description="Kills chickens at Lumbridge Farm and loots them")

public class ChickenMain extends PollingScript<ClientContext> {
	private List<Task> tasks = new ArrayList<>();
	
	public void start(){
		tasks.addAll(Arrays.asList(new Kill(ctx), new Loot(ctx)));
	}
	
	
	public void poll() {
		for( Task t: tasks){
			if(t.activate()){
				t.execute();
			}
		}
		
	}

}

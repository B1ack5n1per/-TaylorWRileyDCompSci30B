package client;
// Asynchronous controller prevents interuptions

import java.net.URI;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

public class HttpController {
	public boolean stop = false;
	private Thread thread;
	
	public HttpController() {
		Runnable run = new Runnable() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					if (Main.readyData != null) {
						Main.client.send(HttpRequest.newBuilder()
							.uri(new URI(HttpSettings.uri + "/ready"))
							.PUT(HttpRequest.BodyPublisher.fromString(Main.readyData.toJSONString()))
							.header("Content-Type", "application/json")
							.build(),
							HttpResponse.BodyHandler.asString());
						Main.httpReqRecieved = true;
					}
					if (Main.httpReqRecieved) {
						JSONObject req = new JSONObject();
						req.put("mapId", Main.map.id);
						
						JSONObject playerData = (JSONObject) (new JSONParser()).parse(Main.client.send(HttpRequest.newBuilder()
								.uri(new URI(HttpSettings.uri + "/getData"))
								.POST(HttpRequest.BodyPublisher.fromString(req.toJSONString()))
								.header("Content-Type", "application/json")
								.build(),
								HttpResponse.BodyHandler.asString()).body());
						if ((boolean) playerData.get("ready")) Main.playerData = playerData;
					}
					if (Main.animationFinished) {
						System.out.println("ready cancelled");
						JSONObject req = new JSONObject();
						req.put("mapId", Main.map.id);
						req.put("player", Main.player.toJSON());
						Main.client.send(HttpRequest.newBuilder()
								.uri(new URI(HttpSettings.uri + "/finished"))
								.PUT(HttpRequest.BodyPublisher.fromString(req.toJSONString()))
								.header("Content-Type", "application/json")
							.build(),
							HttpResponse.BodyHandler.asString());
						Main.animationFinished = false;
					}
				} catch(Exception e) {
					stop = true;
					e.printStackTrace();
				}

				if (!stop) run();
			}
		};
		
		this.thread = new Thread(run);
	}
	
	public void start() {
		thread.start();
	}
	
	public void stop() {
		stop = true;
	}
}

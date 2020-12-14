package mapCreator;

import java.util.HashSet;

import client.TileType;

public class TileData {
	public TileType type;
	public HashSet<String> dirs;
	public TileData(TileType type, HashSet<String> dirs) {
		this.type = type;
		this.dirs = dirs;
	}
	
	public String toString() {
		return "{\"type\":" + type.str + ", \"dirs\":" + dirs.toString() + "}";
	}
}

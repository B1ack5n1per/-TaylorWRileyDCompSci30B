package client;

public enum TileType {
	MOUNTAIN("Mountain"), ROAD("Road"), GRASS("Grass"), BUILDINGSMALL("BuildingSmall"), BUILDINGLARGE("BuildingLarge"), FACTORY("Factory"), FOREST("Forest"), FARM("Farm");
	
	public String str;
	
	private TileType(String str) {
		this.str = str;	
	}
}

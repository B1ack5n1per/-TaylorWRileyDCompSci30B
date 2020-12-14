package mapCreator;

import client.FileSettings;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TileButton extends Button {
	private ImageView img;
	private TileData tile;
	
	public TileButton() {
		super();
		this.img = new ImageView();
		this.setGraphic(img);
		this.setStyle("-fx-border-stroke: 1; -fx-border-color: #333;-fx-padding: 0");
		this.setMinSize(32, 32);
		this.setMaxSize(32, 32);
		this.setOnAction(e -> {
			tile = TileSelector.display();
			img.setImage(new Image(FileSettings.assets + "/Tiles/" + tile.type.str + ".png"));
		});
	}
	
	public String getTile() {
		if (tile != null) return tile.toString();
		return null;
	}
}

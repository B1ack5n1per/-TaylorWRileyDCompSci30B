package mapCreator;

import java.util.HashSet;

import client.FileSettings;
import client.TileType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TileSelector {
	
	private static TileType type;
	public static TileData display() {
		Stage window = new Stage();
		type = TileType.GRASS;
		HashSet<String> dirs = new HashSet<String>();
		
		// Set Up Direction Buttons
		Button up = new Button("^");
		up.setOnAction(e -> {
			if (dirs.contains("up")) {
				dirs.remove("up");
				up.setTextFill(Color.BLACK);
			} else {
				dirs.add("up");
				up.setTextFill(Color.RED);
			}
		});
		up.setMinSize(32, 32);
		
		Button down = new Button("v");
		down.setOnAction(e -> {
			if (dirs.contains("down")) {
				dirs.remove("down");
				down.setTextFill(Color.BLACK);
			} else {
				dirs.add("down");
				down.setTextFill(Color.RED);
			}
		});
		down.setMinSize(32, 32);
		
		Button left = new Button("<");
		left.setOnAction(e -> {
			if (dirs.contains("left")) {
				dirs.remove("left");
				left.setTextFill(Color.BLACK);
			} else {
				dirs.add("left");
				left.setTextFill(Color.RED);
			}
		});
		left.setMinSize(32, 32);
		
		Button right = new Button(">");
		right.setOnAction(e -> {
			if (dirs.contains("right")) {
				dirs.remove("right");
				right.setTextFill(Color.BLACK);
			} else {
				dirs.add("right");
				right.setTextFill(Color.RED);
			}
		});
		right.setMinSize(32, 32);
		
		
		HBox lrButtons = new HBox();
		lrButtons.getChildren().addAll(left, right);
		
		VBox buttons = new VBox();
		buttons.getChildren().addAll(up, lrButtons, down);
		buttons.setMaxWidth(64);
		buttons.setAlignment(Pos.CENTER);
		
		Label curType = new Label("Grass");
		
		HBox menu = new HBox();
		// Add All Tile Type Buttons
		for (TileType tileType: TileType.values()) {
			Button btn = new Button();
			btn.setGraphic(new ImageView(new Image(FileSettings.assets + "/Tiles/" + tileType.str + ".png")));
			btn.setOnAction(e ->  {
				type = tileType;
				curType.setText(tileType.str);
			});
			btn.setMinSize(32, 32);
			btn.setMaxSize(32, 32);
			menu.getChildren().add(btn);
		}
		
		Button add = new Button("Add");
		add.setOnAction(e -> {
			window.close();
		});

		VBox container = new VBox();
		container.setAlignment(Pos.CENTER);
		container.getChildren().addAll(curType, menu, buttons, add);		
		Scene scene = new Scene(container, 400, 200);
		
		window.setScene(scene);
		window.initModality(Modality.APPLICATION_MODAL);
		window.initStyle(StageStyle.TRANSPARENT);
		window.showAndWait();
		return new TileData(type, dirs);
	}
}

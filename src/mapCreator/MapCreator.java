package mapCreator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MapCreator extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		GridPane map = new GridPane();
		TileButton[][] tiles = new TileButton[23][17];
		for (int i = 0; i < 23; i++) {
			for (int j = 0; j < 17; j++) {
				TileButton btn = new TileButton();
				map.add(btn, i, j);
				tiles[i][j] = btn;
			}
		}
		
		TextArea output = new TextArea();
		Button generateMap = new Button("Generate");
		generateMap.setOnAction(e -> {
			// Get Formated JSON Tile Data
			output.setText("{");
			try {
				for (int i = 0; i < 23; i++) {
					for (int j = 0; j < 17; j++) {
						if (tiles[i][j].getTile() == null) throw new Exception();
						output.setText(output.getText() + tiles[i][j].getTile());
						if (1 < 23 && j < 16) output.setText(output.getText() + ",");
					}
				}
				output.setText(output.getText() + "}");
			} catch(Exception err) {
				output.setText("Error please fill out all tiles");
			}
		});
		
		HBox out = new HBox();
		out.getChildren().addAll(generateMap, output);

		VBox container = new VBox();
		container.getChildren().addAll(map, out);
		
		window.setScene(new Scene(container, 32 * 23, 800));
		window.setTitle("Map Creator");
		window.show();
	}

}

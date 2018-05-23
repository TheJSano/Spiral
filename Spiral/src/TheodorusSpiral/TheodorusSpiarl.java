package TheodorusSpiral;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TheodorusSpiarl extends Application {
	Point2D[] coordinates = new Point2D[18];

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Generate center
		Pane root = new Pane();

		// Draw spiral
		int sideLength = 1;
		
		drawSpiral(600, 450, 180d, 17, root, sideLength);
		drawLink(root);

		Scene scene = new Scene(root);

		// PrimaryStage operations
		primaryStage.setMinHeight(900);
		primaryStage.setMinWidth(1200);
		primaryStage.setTitle("Theodorus Spiral");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private void drawSpiral(int x1, int y1, double angle, int depth, Pane p, int temp) {
		if (depth == 0)
			return;

		int x2 = x1 - (int) (Math.cos(Math.toRadians(angle)) * 100 * Math.sqrt(temp));
		int y2 = y1 - (int) (Math.sin(Math.toRadians(angle)) * 100 * Math.sqrt(temp));

		coordinates[temp] = new Point2D(x2, y2);

		p.getChildren().add(new Line(x1, y1, x2, y2));

		temp++;
		angle -= Math.toDegrees(Math.acos(Math.sqrt(temp - 1) / Math.sqrt(temp)));
		
		
		PrintWriter writer;
		try {
			writer = new PrintWriter("data.txt", "UTF-8");
			writer.println("Angle: " + angle);
			writer.println("X2: " + x2);
			writer.println("Y2 :" + y2);
			writer.println("Temp :" + temp + "\n");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		

	

		drawSpiral(x1, y1, angle, depth - 1, p, temp);

	}

	private void drawLink(Pane p) {
		for (int i = 1; i < coordinates.length - 1; i++) {
			int x1 = (int) coordinates[i].getX();
			int y1 = (int) coordinates[i].getY();
			int x2 = (int) coordinates[i + 1].getX();
			int y2 = (int) coordinates[i + 1].getY();

			Line line = new Line(x1, y1, x2, y2);

			line.setStroke(Color.RED);

			p.getChildren().addAll(line);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

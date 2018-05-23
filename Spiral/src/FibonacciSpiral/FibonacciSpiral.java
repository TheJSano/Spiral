package FibonacciSpiral;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FibonacciSpiral extends Application {
	public int[] fibonacciNumbers = new int[8];

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Generate center
		Pane root = new Pane();

		// Draw spiral
		setFibonacciFirstNumbers();
		fibonacci(1, 1, 2);

		int i = fibonacciNumbers.length - 1;

		
		Point2D coord = new Point2D(600 - 210, 450 - 210);

		drawRectangles(root, i, coord);

		Scene scene = new Scene(root);

		// PrimaryStage operations
		primaryStage.setMinHeight(900);
		primaryStage.setMinWidth(1200);
		primaryStage.setTitle("Fibonacci Spiral");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void drawRectangles(Pane p, int i, Point2D coordinates) {
		if (i == 5)
			return;
		int dimentions = fibonacciNumbers[i] * 10;

		Rectangle r = new Rectangle(dimentions, dimentions);

		r.relocate(coordinates.getX(),coordinates.getY());
		r.setFill(Color.WHITE);
		r.setStroke(Color.BLACK);

		System.out.println("Pos X: " + coordinates.getX());
		System.out.println("Pos Y: " + coordinates.getY());

		p.getChildren().add(r);
		
		i--;
		
		coordinates = new Point2D(coordinates.getX() + dimentions, coordinates.getY());
		
		drawRectangles(p, i, coordinates);
	}

	private void fibonacci(int a, int b, int i) {
		if (i == fibonacciNumbers.length)
			return;
		this.fibonacciNumbers[i] = a + b;

		System.out.println(fibonacciNumbers[i]);

		a = fibonacciNumbers[i - 1];
		b = fibonacciNumbers[i];
		i++;
		fibonacci(a, b, i);
	}

	public void setFibonacciFirstNumbers() {
		this.fibonacciNumbers[0] = 1;
		this.fibonacciNumbers[1] = 1;
	}

	public static void main(String[] args) {
		launch(args);
	}
}

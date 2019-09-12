
import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Birthday extends Application {

	public void start(Stage primaryStage) throws Exception {

		GuessBirthday birthday = new GuessBirthday();
		Button enterBtn = new Button("Enter");
		TextField input = new TextField();
		Label output = new Label("Is your birthday in Set 1?\n " + "\t" + birthday.getSet(0));
		Button resetBtn = new Button("Reset");
		Label direction = new Label("Please enter 1 for Yes or 0 for No");

		output.setFont(Font.font("Times New Roman", 40));
		output.setId("output");

		input.setFont(Font.font("Arial", 20));
		input.setAlignment(Pos.CENTER);
		input.prefWidth(800);

		direction.setFont(Font.font("Times New Roman", 30));
		direction.setId("direction");

		resetBtn.setPrefHeight(100);
		resetBtn.setPrefWidth(100);
		resetBtn.setFont(Font.font("Times new Roman", 20));

		enterBtn.setPrefHeight(100);
		enterBtn.setPrefWidth(100);
		enterBtn.setFont(Font.font("Times new Roman", 20));

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				updateEvent(birthday, input, output, direction);
			}
		};

		resetBtn.setOnAction(e -> {
			birthday.setIndex(0);
			birthday.setDay(0);
			input.setText("");
			output.setText("Is your birthday in Set 1?\n\t" + birthday.getSet(0));
			direction.setText("Please enter 1 for Yes or 0 for No");
		});
		enterBtn.setOnAction(event);

		VBox root = new VBox();
		root.getChildren().addAll(output, direction, input, enterBtn, resetBtn);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(15);

		Scene scene = new Scene(root, 1000, 600);

		scene.getStylesheets().add((new File("src/resources/Birthday.css")).toURI().toString());

		primaryStage.setTitle("Birthday Guesser");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void computeDay(String text, int index, GuessBirthday birthday) {
		if (text.equals("1")) {
			birthday.incrementDay(index);
		}
	}

	private void updateEvent(GuessBirthday birthday, TextField input, Label output, Label direction) {
		if (birthday.getIndex() == 4) {
			if (input.getText().isEmpty()) {
				direction.setText("Invalid input, enter 1 for Yes or 0 for No");
				return;
			}
			computeDay(input.getText(), birthday.getIndex(), birthday);
			input.setText("");
			output.setText("Your birthday is on: " + Integer.toString(birthday.getDay()) + " and in binary: "
					+ Integer.toBinaryString(birthday.getDay()));
			birthday.setIndex(birthday.getIndex() + 1);
			direction.setText("Click Reset to play again!");
		} else if (birthday.getIndex() == 5) {
			output.setText("Your birthday is on: " + Integer.toString(birthday.getDay()) + " and in binary: "
					+ Integer.toBinaryString(birthday.getDay()));
			direction.setText("Click Reset to play again!");
		} else {
			if ("1".equals(input.getText()) || "0".equals(input.getText())) {
				computeDay(input.getText(), birthday.getIndex(), birthday);
				birthday.setIndex(birthday.getIndex() + 1);
				input.setText("");
				direction.setText("Please enter 1 for Yes or 0 for No");
				output.setText("Is your birthday in Set " + (birthday.getIndex() + 1) + "?\n\t"
						+ birthday.getSet(birthday.getIndex()));
			} else {
				direction.setText("Invalid input, enter 1 for Yes or 0 for No");
			}
		}
	}

}
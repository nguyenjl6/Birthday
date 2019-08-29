
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Birthday extends Application {

	public void start(Stage primaryStage) throws Exception {

		GuessBirthday birthday = new GuessBirthday();
		Button enterBtn = new Button("Enter");
		TextField input = new TextField();
		TextArea output = new TextArea("Is your birthday in Set1?\n" + birthday.getSet(0));
		Button resetBtn = new Button("Reset");
		Label direction = new Label("Please enter 1 for Yes or 0 for No");

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
			output.setText("Is your birthday in Set1?\n" + birthday.getSet(0));
		});
		enterBtn.setOnAction(event);

		VBox root = new VBox();
		root.getChildren().addAll(output, direction, input, enterBtn, resetBtn);
		Scene scene = new Scene(root, 600, 600);

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

	private void updateEvent(GuessBirthday birthday, TextField input, TextArea output, Label direction) {
		if (birthday.getIndex() == 4) {
			computeDay(input.getText(), birthday.getIndex(), birthday);
			input.setText("");
			output.setText("Your birthday is on the " + Integer.toString(birthday.getDay())
					+ " and your birthday in binary is " + Integer.toBinaryString(birthday.getDay()));
			birthday.setIndex(birthday.getIndex() + 1);
		} else if (birthday.getIndex() == 5) {
			output.setText("Your birthday is on the " + Integer.toString(birthday.getDay())
					+ " and your birthday in binary is " + Integer.toBinaryString(birthday.getDay()));
		} else {
			if ("1".equals(input.getText()) || "0".equals(input.getText())) {
				computeDay(input.getText(), birthday.getIndex(), birthday);
				birthday.setIndex(birthday.getIndex() + 1);
				input.setText("");
				direction.setText("Please enter 1 for Yes or 0 for No");
				output.setText("Is your birthday in Set" + (birthday.getIndex() + 1) + "?\n"
						+ birthday.getSet(birthday.getIndex()));
			} else {
				direction.setText("Invalid input, try again.");
			}
		}
	}
}
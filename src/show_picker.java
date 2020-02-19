import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class show_picker extends Application {
	
	public String picker() {
		BufferedReader inputReader;
		ArrayList<String> add = new ArrayList<String>();
		try {
			inputReader = new BufferedReader(new FileReader(new File("Shows.txt")));
			String inputs;
			String[] shows = null;
		    	while((inputs = inputReader.readLine())!=null) {
		    		shows = inputs.split("\\s+");
		    		add.add(shows[0]);
		    	}
		    int number=add.size()-1;
		    String show=random(add,number);	
		    inputReader.close();
		    return show;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	    
	public String random(ArrayList<String> shows, int num) {
		Random r = new Random();
		int send= r.nextInt((num - 1) + 1) + 1;//first number is the number of shows
		return shows.get(send);		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: green;");
		Button go = new Button("GO!!!");
		borderPane.setCenter(go);//creates buttons on gui for selection
		
		go.setOnAction(e -> results(primaryStage));
		
		Scene scene = new Scene(borderPane, 240, 50);
		primaryStage.setTitle("Show Picker"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	
	public void results(Stage primaryStage) {
		//BorderPane borderPane = new BorderPane();
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: purple;");
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setMinSize(300, 300);
		grid.setVgap(5);
		grid.setHgap(5);
		
		String Show=picker();
		TextField b = new TextField();
		b.setText(Show);
		grid.add(b, 0, 1);
		
		Button go = new Button("reset");
		grid.add(go, 1, 1);
		
		go.setOnAction(e -> {
			try {
				start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
				
		Scene scene = new Scene(grid, 240, 50);
		primaryStage.setTitle("Show Picker"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
}

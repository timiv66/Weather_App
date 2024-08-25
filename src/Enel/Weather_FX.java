package Enel;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.RowIdLifetime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Weather_FX extends Application{
	
	Font titleFont = new Font("Elephant",25);
	Font logFont = new Font("Book Antiqua",20);
	String city;
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	Api api = new Api();
	
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		// TODO Auto-generated method stub
		Pane p1 = new Pane();
		Scene t = new Scene(p1,350,350);
		t.setFill(Color.BLUE);
		t.setRoot(login(t));
		mainStage.setScene(t);
		mainStage.show();
		mainStage.setTitle("What's the Weather");
	}
	
	public Pane login(Scene t) {
		Label titlLbl = new Label("Timi's Weather \n            App");
		titlLbl.setFont(titleFont);
		titlLbl.setTranslateX(78);
		
		ImageView logImg = new ImageView("https://static.wikia.nocookie.net/onepiece/images/a/a8/Darken_Zeus_While_Attacking.png/revision/latest?cb=20180107062950");
		logImg.setFitHeight(150);
		logImg.setFitWidth(150);
		logImg.setTranslateX(95);
		logImg.setTranslateY(70);
		
		Label cityLbl = new Label("Please enter city");
		cityLbl.setFont(logFont);
		cityLbl.setTranslateX(98);
		cityLbl.setTranslateY(230);
		
		TextField cityTxtF = new TextField();
		cityTxtF.setTranslateX(99);
		cityTxtF.setTranslateY(260);
		
		Button srchBtn = new Button("Search");
		srchBtn.setTranslateX(150);
		srchBtn.setTranslateY(295);
		
		srchBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
			city = cityTxtF.getText();
			try {
				t.setRoot(forecast(t));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		
		Pane logPane = new Pane();
		
		//Sets background color
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		logPane.setBackground(background);
		
		logPane.getChildren().addAll(titlLbl,logImg,cityLbl,cityTxtF,srchBtn);
		return logPane;
	}
	
	public Pane forecast(Scene t) throws JsonSyntaxException, IOException, InterruptedException {
		
		Weather weather = gson.fromJson(api.fetchApi(city), Weather.class);
		
		
		
		Pane forePane = new Pane();
		
		//Sets background color
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		forePane.setBackground(background);
		
		forePane.getChildren().addAll();
		return forePane;
	}

}

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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Weather_FX extends Application{
	
	//Fonts for text and labels
	Font titleFont = new Font("Elephant",25);
	Font logFont = new Font("Book Antiqua",20);
	Font tempFont = new Font("Elephant",55);
	
	//Variable to hold city input from user
	String city;
	
	//Variable to hold desired temperature unit (Default is fahrenheit)
	String unit;
	
	//Creation of GSON and API objects
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	Api api = new Api();
	
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		// TODO Auto-generated method stub
		mainStage.setResizable(false);
		Pane p1 = new Pane();
		Scene t = new Scene(p1,350,370);
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
		
		//Image
		ImageView logImg = new ImageView("https://static.wikia.nocookie.net/onepiece/images/a/a8/Darken_Zeus_While_Attacking.png/revision/latest?cb=20180107062950");
		logImg.setFitHeight(150);
		logImg.setFitWidth(150);
		logImg.setTranslateX(95);
		logImg.setTranslateY(70);
		
		//City text and text field
		Label cityLbl = new Label("Please enter city");
		cityLbl.setFont(logFont);
		cityLbl.setTranslateX(98);
		cityLbl.setTranslateY(230);
		
		TextField cityTxtF = new TextField();
		cityTxtF.setTranslateX(99);
		cityTxtF.setTranslateY(260);
		
		//Search Button
		Button srchBtn = new Button("Search");
		srchBtn.setTranslateX(150);
		srchBtn.setTranslateY(330);
		
		//Buttons for choosing temperature unit
		ToggleGroup group = new ToggleGroup();

		RadioButton fahBtn = new RadioButton("Fahrenheit");
		fahBtn.setToggleGroup(group);
		fahBtn.setTranslateX(100);
		fahBtn.setTranslateY(300);
				
		RadioButton celBtn = new RadioButton("Celsius");
		celBtn.setToggleGroup(group);
		celBtn.setTranslateX(190);
		celBtn.setTranslateY(300);
				 
		
		srchBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
			//Sets inputed text to the city variable
			city = cityTxtF.getText();
			
			//Sets the temperature unit
			if(fahBtn.isSelected()) {
				unit = "f";
			}else if(celBtn.isSelected()) {
				unit = "c";
			}else if(!fahBtn.isSelected() && !celBtn.isSelected()){
				unit = "f";
			}
			
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
		
		
		//Pane to store all the elements
		Pane logPane = new Pane();
		
		//Sets background color
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		logPane.setBackground(background);
		
		//Adding all javaFX elements to pane
		logPane.getChildren().addAll(titlLbl,logImg,cityLbl,cityTxtF,srchBtn,fahBtn,celBtn);
		return logPane;
	}
	
	public Pane forecast(Scene t) throws JsonSyntaxException, IOException, InterruptedException {
		
		//Creation of weather object and storing API request data inside
		Weather weather = gson.fromJson(api.fetchApi(city,unit), Weather.class);
		
		//Images
		ImageView sunnyImg = new ImageView();
		ImageView partlyCldImg = new ImageView();
		ImageView cloudyImg = new ImageView();
		ImageView thunderImg = new ImageView();
		ImageView rainImg = new ImageView();
		
		//Displays current city name
		Text cityNametTxt = new Text("City name: "+weather.getLocation().getCity());
		cityNametTxt.setTextAlignment(TextAlignment.LEFT);
		cityNametTxt.setX(80);
		cityNametTxt.setY(30);
		cityNametTxt.setFont(logFont);
		
		//Displays current temperature
		String curTempStr = Integer.toString(weather.getCurrentObservation().getCondition().getTemperature());
		Text curTempTxt = new Text(curTempStr);
		curTempTxt.setY(100);
		curTempTxt.setX(140);
		curTempTxt.setFont(tempFont);
		
		Pane forePane = new Pane();
		
		//Sets background color
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		forePane.setBackground(background);
		
		forePane.getChildren().addAll(cityNametTxt,curTempTxt);
		return forePane;
	}

}

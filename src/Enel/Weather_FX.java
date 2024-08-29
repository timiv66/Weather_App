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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
		Scene t = new Scene(p1,350,380);
		t.setFill(Color.BLUE);
		t.setRoot(login(t));
		mainStage.setScene(t);
		mainStage.show();
		mainStage.setTitle("What's the Weather");
	}
	
	public Pane login(Scene t) {
		//Name of application
		Label titlLbl = new Label("Timi's Weather \n            App");
		titlLbl.setFont(titleFont);
		titlLbl.setTranslateX(78);
		titlLbl.setTranslateY(-155);
		
		//Image
		ImageView logImg = new ImageView("https://static.wikia.nocookie.net/onepiece/images/a/a8/Darken_Zeus_While_Attacking.png/revision/latest?cb=20180107062950");
		logImg.setFitHeight(150);
		logImg.setFitWidth(150);
		logImg.setTranslateX(95);
		logImg.setTranslateY(-40);
		
		//City text and text field
		Label cityLbl = new Label("Please enter city");
		cityLbl.setFont(logFont);
		cityLbl.setTranslateX(98);
		cityLbl.setTranslateY(53);
		
		TextField cityTxtF = new TextField();
		cityTxtF.setTranslateX(99);
		cityTxtF.setTranslateY(83);
		cityTxtF.setMaxWidth(150);
		
		//Buttons for choosing temperature unit
		ToggleGroup group = new ToggleGroup();

		RadioButton fahBtn = new RadioButton("Fahrenheit");
		fahBtn.setToggleGroup(group);
		fahBtn.setTranslateX(100);
		fahBtn.setTranslateY(113);
				
		RadioButton celBtn = new RadioButton("Celsius");
		celBtn.setToggleGroup(group);
		celBtn.setTranslateX(190);
		celBtn.setTranslateY(113);
		
		//Error message
		Text errTxt = new Text("");
		errTxt.setVisible(false);
		errTxt.setTranslateX(127);
		errTxt.setTranslateY(140);
		errTxt.setFill(Color.RED);
		
		//Search Button
		Button srchBtn = new Button("Search");
		srchBtn.setTranslateX(150);
		srchBtn.setTranslateY(170);		 
		
		srchBtn.setOnAction(new EventHandler <ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
			//Sets inputed text to the city variable
			city = cityTxtF.getText().replaceAll(" ", "_");
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
			}catch (NullPointerException e) {
				errTxt.setVisible(true);
				errTxt.setText("Please enter a city");
			}
			}
		});
		
		
		//Pane to store all the elements
		StackPane logPane = new StackPane();
		logPane.setAlignment(Pos.CENTER_LEFT);
		
		//Sets background color
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		logPane.setBackground(background);
		
		//Adding all javaFX elements to pane
		logPane.getChildren().addAll(titlLbl,logImg,cityLbl,cityTxtF,srchBtn,fahBtn,celBtn,errTxt);
		return logPane;
	}
	
	public Pane forecast(Scene t) throws IOException, InterruptedException {
		
		//Creation of weather object and storing API request data inside
		Weather weather = gson.fromJson(api.fetchApi(city,unit), Weather.class);
		
		//Displays current city name
		Text cityNametTxt = new Text("City name: "+city.replaceAll(" ", "_"));
		cityNametTxt.setTranslateY(-170);
		cityNametTxt.setFont(logFont);
		
		//Displays current temperature
		String curTempStr = Integer.toString(weather.getCurrentObservation().getCondition().getTemperature());
		Text curTempTxt = new Text(curTempStr+"°");
		curTempTxt.setTranslateY(-130);
		curTempTxt.setFont(tempFont);
		
		//Display current condition
		String curCondStr = weather.getCurrentObservation().getCondition().getText();
		Text curCondTxt = new Text(curCondStr);
		curCondTxt.setTranslateY(-95);
		
		//Image
		ImageView sunImg = new ImageView("https://cdn-icons-png.flaticon.com/512/979/979585.png");
		sunImg.setVisible(false);
		sunImg.setFitHeight(150);
		sunImg.setFitWidth(150);
		sunImg.setTranslateY(-10);
		
		ImageView cloudyImg = new ImageView("https://cdn-icons-png.flaticon.com/512/7084/7084486.png");
		cloudyImg.setVisible(false);
		cloudyImg.setFitHeight(150);
		cloudyImg.setFitWidth(150);
		cloudyImg.setTranslateY(-10);
		
		ImageView rainImg = new ImageView("https://cdn-icons-png.flaticon.com/512/4150/4150897.png");
		rainImg.setVisible(false);
		rainImg.setFitHeight(150);
		rainImg.setFitWidth(150);
		rainImg.setTranslateY(-10);
		
		//Determines picture based on current condition
		if(curCondStr.matches("Sunny") || curCondStr.matches("Mostly Sunny") || curCondStr.matches("Fair")) {
			sunImg.setVisible(true);
		}else if(curCondStr.matches("Cloudy") || curCondStr.matches("Mostly Cloudy")) {
			cloudyImg.setVisible(true);
		}else if(curCondStr.matches("Rain") || curCondStr.matches("Showers") || curCondStr.matches("Scattered Showers")) {
			rainImg.setVisible(true);
		}
		
		//Pane to store all the elements
		StackPane forePane = new StackPane();
		forePane.setAlignment(Pos.CENTER);
		
		//Sets background color
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		forePane.setBackground(background);
		
		//Adding all javaFX elements to pane
		forePane.getChildren().addAll(cityNametTxt,curTempTxt,curCondTxt,sunImg,cloudyImg);
		return forePane;
	}

}

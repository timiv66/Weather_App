package Enel;

import javafx.application.Application;
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
		
		Pane logPane = new Pane();
		
		//Sets background color
		BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE,CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(background_fill);
		logPane.setBackground(background);
		
		logPane.getChildren().addAll(titlLbl,logImg,cityLbl,cityTxtF,srchBtn);
		return logPane;
	}

}

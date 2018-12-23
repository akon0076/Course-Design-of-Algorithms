/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find24;


import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 李红
 */
public class AlertBox {

    public void display(String title , String message){
    Stage window = new Stage();
    window.setTitle("title");
    //modality要使用Modality.APPLICATION_MODEL
    window.initModality(Modality.APPLICATION_MODAL);
    window.setMinWidth(300);
    window.setMinHeight(150);

    Button button = new Button("返回上一级");
    button.setOnAction(e -> window.close());

    Label label = new Label(message);

    VBox layout = new VBox(10);
    layout.getChildren().addAll(label , button);
    layout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(layout);
    window.setScene(scene);
    //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
    window.showAndWait();
    }
    
    public void display(String title , ArrayList<String> list){
    Stage window = new Stage();
    window.setTitle(title);
    
    //modality要使用Modality.APPLICATION_MODEL
    window.initModality(Modality.APPLICATION_MODAL);
    window.setMinWidth(500);
    window.setMinHeight(300);

    Button button = new Button("返回上一级");
    button.setPrefSize(100, 60);
    button.setFont(new Font(15));
    button.setOnAction(e -> window.close());

    Label[] label = new Label[list.size()];
    for (int i = 0 ; i < label.length; i++) {
        label[i] = new Label(list.get(i));
        label[i].setFont(new Font(20));
    }

    VBox layout = new VBox(10);
    Text text = new Text("所有结果");
    text.setFont(new Font(30));
    layout.getChildren().add(text);
    layout.setPadding(new Insets(0, 0, 0, 180));
    for (int i = 0; i < label.length; i++) {
        layout.getChildren().add(label[i]);
    }
    layout.getChildren().add(button);
    
    layout.setAlignment(Pos.CENTER);
    ScrollPane pane = new ScrollPane();
    pane.setContent(layout);
    pane.setPrefViewportWidth(400);
    pane.setPrefViewportHeight(600);
    Scene scene = new Scene(pane);
    window.setScene(scene);
    //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
    window.showAndWait();
    }
    
    public void diyBox(){
    Stage window = new Stage();
    window.setTitle("Diy A 24 Point");
    //modality要使用Modality.APPLICATION_MODEL
    window.initModality(Modality.APPLICATION_MODAL);
    window.setMinWidth(300);
    window.setMinHeight(150);

    Button button = new Button("返回上一级");
    button.setOnAction(e -> window.close());


    VBox layout = new VBox(10);
    layout.getChildren().addAll(button);
    layout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(layout);
    window.setScene(scene);
    //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
    window.showAndWait();
    }
}

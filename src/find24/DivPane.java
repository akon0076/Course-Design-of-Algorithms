/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find24;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Administrator
 */
public class DivPane extends BorderPane {

    Text text = new Text("请输入1-9的数字");

    TextField[] input = new TextField[4];

    TextField solution = new TextField();
    Button find = getButton("搜索表达式");

    HBox head = new HBox(20);
    HBox center = new HBox(20);
    HBox foot = new HBox(20);

    DivPane() {
          for (int i = 0; i < input.length; i++) {
            input[i] = getTextFiled();
            TextField t = input[i];
        }

        text.setFont(new Font(50));
        head.getChildren().add(text);
        head.setPadding(new Insets(20, 0, 20, 20));

        for (int i = 0; i < input.length; i++) {
            center.getChildren().add(input[i]);
        }
        center.setPadding(new Insets(20, 0, 20, 90));

        solution.setPrefSize(250, 60);
        solution.setFont(new Font(25));
        foot.getChildren().add(solution);
        foot.getChildren().add(find);
        foot.setPadding(new Insets(20, 0, 20, 115));

        setCenter(center);
        setTop(head);
        setBottom(foot);
    }

    public TextField getTextFiled() {
        TextField text = new TextField();
        text.setPrefSize(125, 100);
        text.setFont(new Font(50));
        return text;
    }

    public Button getButton(String name) {
        Button bt = new Button(name);
        bt.setPrefSize(250, 60);
        bt.setFont(new Font(25));
        return bt;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find24;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author 李红
 */
public class MainPane extends BorderPane {

	/**
	 * 主面板
	 */
	BorderPane mainPane = new BorderPane();

	/**
	 * 顶部区域
	 */
	Button find = getButton("Find A Solution");
	Button findAllSolution = getButton("Finad All Solution");
	TextField result = getTextField();
	Button refresh = getButton("Refresh");
	HBox head = new HBox(40);

	/**
	 * 图片
	 */
	HBox imageHBox = new HBox(20);

	/**
	 * 底部区域
	 */
	Text enter = new Text("Enter a expression:");
	TextField inputArea = getTextField();
	Button verify = getButton("Verify");
	Button diy = getButton("DIY A 24 Point");
	HBox foot = new HBox(40);

	public MainPane() {

		enter.setFont(new Font(30));

		head.getChildren().add(findAllSolution);
		head.getChildren().add(find);
		head.getChildren().add(result);
		head.getChildren().add(refresh);
		head.setPadding(new Insets(20, 0, 0, 20));

		foot.getChildren().add(enter);
		foot.getChildren().add(inputArea);
		foot.getChildren().add(verify);
		foot.getChildren().add(diy);
		foot.setPadding(new Insets(20, 20, 20, 20));

		setCenter(imageHBox);
		setTop(head);
		setBottom(foot);
	}

	/**生成指定样式的button*/
	public Button getButton (String name) {
		Button button = new Button(name);
		button.setPrefSize(250, 40);
		button.setFont(new Font(20));
		return button;
	}

	public TextField getTextField() {
		TextField textField = new TextField();
		textField.setPrefSize(230, 40);
		textField.setFont(new Font(20));
		return textField;
	}

	public ImageView getImageView(String url){
		ImageView image = new ImageView(new Image(url));
		image.setFitWidth(200);
		image.setFitHeight(320);
		return image;
	}
}

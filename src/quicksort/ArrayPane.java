package quicksort;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ArrayPane extends Pane {
    int lineY = 300;
    int stringY = lineY + 50;
    public ArrayPane(int[] array) {
        for (int i = 0; i < array.length; i++) {
            Line line = new Line(i * 30 + 20, lineY, i * 30 + 20, lineY - array[i]);
            line.setStrokeWidth(20);
            getChildren().add(line);
        }
    }

    public ArrayPane(int[] array,int index, int left, int right, String text) {
        for (int i = 0; i < array.length; i++) {
            Line line = new Line(i * 30 + 20, lineY, i * 30 + 20, lineY - array[i]);
            if (i == left) {
                line.setStroke(Color.ORANGE);
            }
            if (i == right) {
                line.setStroke(Color.ORANGE);
            }
            if (i == index) {
                line.setStroke(Color.GREEN);
            }
            line.setStrokeWidth(20);
            getChildren().add(line);
        }
        text += ", 主元index = " + index + " , i = " + left + " , j = " + right;
        if (right < left) {
            text += ",但是j < i";
        }
        Text text1 = new Text(text);
        text1.setX(20);
        text1.setY(stringY);
        text1.setFont(Font.font(20));
        getChildren().add(text1);
    }

    public ArrayPane(int[] array, int right,String text) {
        for (int i = 0; i < array.length; i++) {
            Line line = new Line(i * 30 + 20, 100, i * 30 + 20, 100 - array[i]);
            if (i == right) {
                line.setStroke(Color.GREEN);
            }
            line.setStrokeWidth(20);
            getChildren().add(line);
        }
        Text text1 = new Text(text);
        text1.setX(200);
        text1.setY(200);
        getChildren().add(text1);
    }

}

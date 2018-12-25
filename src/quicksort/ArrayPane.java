package quicksort;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class ArrayPane extends Pane {
    public ArrayPane(int[] array) {
        for (int i = 0; i < array.length; i++) {
            Line line = new Line(i * 30 + 20, 100, i * 30 + 20, 100 - array[i]);
            line.setStrokeWidth(20);
            getChildren().add(line);
        }
        System.out.println("ddd");
    }

    public ArrayPane(int[] array,int index, int left, int right, boolean leftIsBigger, boolean rightIsBigger) {
        for (int i = 0; i < array.length; i++) {
            Line line = new Line(i * 30 + 20, 100, i * 30 + 20, 100 - array[i]);
            if (i == index) {
                line.setStroke(Color.GREEN);
            }
            if (i == left || i == right) {
                if (i == left && leftIsBigger == true ) {
                    line.setStroke(Color.RED);
                } else {
                    line.setStroke(Color.ORANGE);
                }
                if (i == right && rightIsBigger == true ) {
                    line.setStroke(Color.RED);
                } else {
                    line.setStroke(Color.ORANGE);
                }
            }
            line.setStrokeWidth(20);
            getChildren().add(line);
        }
        System.out.println("ddd");
    }

    public ArrayPane(int[] array,int left, int right) {
        for (int i = 0; i < array.length; i++) {
            Line line = new Line(i * 30 + 20, 100, i * 30 + 20, 100 - array[i]);
            if (i == left || i == right) {
                line.setStroke(Color.GREEN);
            }
            line.setStrokeWidth(20);
            getChildren().add(line);
        }
        System.out.println("ddd");
    }

}

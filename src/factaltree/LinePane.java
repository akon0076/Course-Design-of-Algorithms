package factaltree;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LinePane extends Pane {
    public LinePane(double angle, double addAngle, int startX, int startY, int length, int cutLength) {
        angle = angle - addAngle;
        //画树根
        Line line = new Line(startX, startY, startX, startY + length);
        getChildren().add(line);
        //开始递归
        paintTree(angle, addAngle, startX, startY, length, cutLength);
    }

    private void paintTree(double angle, double addAngle, int startX, int startY, int length, int cutLength) {
        if (length < 0) {
            return;
        }
        //计算左偏角和右偏角
        double leftAngle = angle + addAngle;
        double rightAngle = angle  - addAngle;
        //计算下一个左边点位并构造线
        int endXL = (int) (startX + length * Math.sin(leftAngle));
        int endYL = (int) (startY - length * Math.cos(leftAngle));
        Line lineL = new Line(startX, startY, endXL, endYL);
        //计算下一个右边点位并构造线
        int endXR = (int) (startX + length * Math.sin(rightAngle));
        int endYR = (int) (startY - length * Math.cos(rightAngle));
        Line lineR = new Line(startX, startY, endXR, endYR);
        //检查是否是最后三个树枝，如果是就修改颜色
        if (length - cutLength < 0 || length - 2 * cutLength < 0 || length - 3 * cutLength < 0) {
            lineL.setStroke(Color.valueOf("cc6699"));
            lineR.setStroke(Color.valueOf("cc6699"));
        }
        //把线添加到Pane
        getChildren().add(lineR);
        getChildren().add(lineL);
        //画下一层的左树枝和右树枝
        paintTree(leftAngle, addAngle, endXL, endYL, length - cutLength, cutLength);
        paintTree(rightAngle, addAngle, endXR, endYR, length - cutLength, cutLength);
    }
}

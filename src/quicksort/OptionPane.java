package quicksort;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class OptionPane extends Pane {
    Button previous = new Button("上一步");
    Button next = new Button("下一步");
    Button auto = new Button("自动播放");
    Button init = new Button("重置");
    Button stop = new Button("暂停");
    Button go = new Button("继续");
    OptionPane () {
        stop.setLayoutX(100);
        go.setLayoutX(200);
        previous.setLayoutX(300);
        next.setLayoutX(400);
        init.setLayoutX(500);
        getChildren().add(previous);
        getChildren().add(next);
        getChildren().add(auto);
        getChildren().add(init);
        getChildren().add(go);
        getChildren().add(stop);
    }
}

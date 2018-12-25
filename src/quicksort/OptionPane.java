package quicksort;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class OptionPane extends Pane {
    Button next = new Button("下一步");
    OptionPane () {
        getChildren().add(next);
    }
}

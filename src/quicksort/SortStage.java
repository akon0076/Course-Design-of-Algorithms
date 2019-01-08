package quicksort;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;

public class SortStage extends Application {
    private static int[] array = {40, 50, 20, 25, 45, 35, 5, 80, 100, 85, 10, 55, 20, 30, 60};
    private static ArrayList<ArrayPane> paneList = new ArrayList<>();
    Timeline timeline;
    int i = 0;

    @Override
    public void start(Stage primaryStage) {
        BorderPane mainPane = new BorderPane();
        OptionPane option = new OptionPane();

        option.init.setOnAction(event -> {
            this.i = 0;
            mainPane.setCenter(paneList.get(i));
            if (timeline != null)
                this.timeline.stop();
        });

        option.previous.setOnAction(event -> {
            if (i > 0) {
                i--;
                mainPane.setCenter(paneList.get(i));
            }
        });

        option.stop.setOnAction(event -> {
            if (timeline != null)
                timeline.stop();
        });

        option.go.setOnAction(event -> {
            if (timeline != null)
                timeline.play();
        });

        option.next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    i++;
                if (paneList.size() > i) {
                    mainPane.setCenter(paneList.get(i));
                }
                else {
                    i = 0;
                    mainPane.setCenter(paneList.get(i));
                }
            }
        });
        option.auto.setOnAction(event -> {
            System.out.println("dddd");
            timeline = new Timeline(new KeyFrame(Duration.millis(1000), event1 -> {
                i++;
                if (paneList.size() > i) {
                    mainPane.setCenter(paneList.get(i));
                } else {
                    i = 0;
                    mainPane.setCenter(paneList.get(i));
                }
            }));
            timeline.setCycleCount(timeline.INDEFINITE);
            timeline.play();

        });
        //创建主面板
        paneList.add(new ArrayPane(array));
        quickSort(array);
        paneList.add(new ArrayPane(array));
        mainPane.setTop(option);
        mainPane.setCenter(paneList.get(0));
        Scene scene = new Scene(mainPane, 800, 480);

        primaryStage.setTitle("MyPoker");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    public static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    /**
     * Partition the array list[first..last]
     */
    public static int partition(int[] list, int first, int last) {
        int leftSide = first;
        int rightSide = last;
        int pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search
        paneList.add(new ArrayPane(list, first, low, high, "开始查找"));
        while (high > low) {
            // Search forward from left
            while (low <= high && list[low] <= pivot) {
                low++;
            }
            // Search backward from right
            while (low <= high && list[high] > pivot) {
                high--;
            }
            // Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot) {
            high--;
        }


        // Swap pivot with list[high]
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            paneList.add(new ArrayPane(list, high, leftSide, rightSide, "交换完成"));
            return high;
        } else {
            return first;
        }
    }
}

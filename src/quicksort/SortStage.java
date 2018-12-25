package quicksort;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.util.ArrayList;

public class SortStage extends Application {
    private static int[] array = {10, 50, 20, 20, 40, 30, 5, 80};
    private static int[] array1 = {10, 50, 20, 20, 40, 30, 5, 80};
    private static ArrayList<ArrayPane> paneList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        quickSort(array);
        int i = 0;
        BorderPane mainPane = new BorderPane();
        OptionPane option = new OptionPane();
        option.next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (paneList.size() > 0)
                    mainPane.setCenter(paneList.remove(0));
            }
        });
        //创建主面板
        ArrayPane arrayPane = new ArrayPane(array1);
        mainPane.setTop(option);
        mainPane.setCenter(arrayPane);
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
        int pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search

        while (high > low) {
            paneList.add(new ArrayPane(list, first, low, high, false, false));
            // Search forward from left
            while (low <= high && list[low] <= pivot) {
                low++;
                paneList.add(new ArrayPane(list, first, low, high, false, false));
            }
            if (list[low] > pivot)
                paneList.add(new ArrayPane(list, first, low, high, true, false));
            else
                paneList.add(new ArrayPane(list, first, low, high,  false, false));

            // Search backward from right
            while (low <= high && list[high] > pivot) {
                high--;
                if (list[low] > pivot)
                    paneList.add(new ArrayPane(list, first, low, high, true, false));
                else
                    paneList.add(new ArrayPane(list, first, low, high, false, false));
            }

            if (list[high] < pivot) {
                if (list[low] > pivot) {
                    paneList.add(new ArrayPane(list, first, low, high, true, true));
                } else {
                    paneList.add(new ArrayPane(list, first, low, high, false, true));
                }
            } else {
                if (list[low] > pivot) {
                    paneList.add(new ArrayPane(list, first, low, high, true, false));
                } else {
                    paneList.add(new ArrayPane(list, first, low, high, false, false));
                }
            }
            // Swap two elements in the list
            if (high > low) {
                paneList.add(new ArrayPane(list, first, low, high, true, true));
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
                paneList.add(new ArrayPane(list, first, low, high, true, true));
            }
        }

        while (high > first && list[high] >= pivot)
            high--;

        // Swap pivot with list[high]
        if (pivot > list[high]) {
            paneList.add(new ArrayPane(list, first, high));
            list[first] = list[high];
            list[high] = pivot;
            paneList.add(new ArrayPane(list, high, first));
            return high;
        } else {
            return first;
        }
    }
}

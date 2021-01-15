import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executors;

public class ProgressDemo extends Application {

    public static void main(String[] args) {
        Application.launch(ProgressDemo.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                for(int i = 1; i <= 10000; i++){
                    updateProgress(i, 10000);
                    try{
                        Thread.sleep(1);
                    }catch (InterruptedException e){

                    }
                }
                return null;
            }
        };

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.progressProperty().bind(task.progressProperty());
        ProgressBar progressBar = new ProgressBar();
        progressBar.progressProperty().bind(task.progressProperty());
        Button button = new Button("Start");
        button.setOnAction(e -> Executors.newSingleThreadExecutor().execute(task));
        VBox box = new VBox(progressIndicator, progressBar, button);
        box.setSpacing(12);
        box.setPadding(new Insets(12));
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Shephard");
        primaryStage.setWidth(300);
        primaryStage.setHeight(200);
        primaryStage.show();

    }
}

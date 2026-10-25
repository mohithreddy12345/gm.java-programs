import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import java.nio.file.Files;
import java.nio.file.Path;

public class DisplayTextAndImage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Step 3: Set the title of the stage
        primaryStage.setTitle("Text and Image Display");

        // Step 4: Create a Label with the text "Hello, JavaFX!"
        Label label = new Label("Hello, JavaFX!");

        // Step 5: Load an image from the first argument or use sample.jpg
        Path imagePath = getParameters().getRaw().isEmpty()
                ? Path.of("sample.jpg")
                : Path.of(getParameters().getRaw().get(0));
        imagePath = imagePath.toAbsolutePath().normalize();
        if (!Files.isRegularFile(imagePath)) {
            new Alert(Alert.AlertType.ERROR,
                    "Image file not found:\n" + imagePath).showAndWait();
            return;
        }

        Image image = new Image(imagePath.toUri().toString());
        if (image.isError()) {
            Throwable cause = image.getException();
            String message = "Could not load image:\n" + imagePath;
            if (cause != null && cause.getMessage() != null) {
                message += "\n" + cause.getMessage();
            }
            new Alert(Alert.AlertType.ERROR, message).showAndWait();
            return;
        }
        ImageView imageView = new ImageView(image);
        
        // Optional: Set sizing and preserve aspect ratio for better display
        imageView.setFitWidth(400);
        imageView.setFitHeight(250);
        imageView.setPreserveRatio(true);

        // Step 6: Create a VBox layout with 10 pixels spacing
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(label, imageView);

        // Step 7: Create a Scene with dimensions 600x400 pixels
        Scene scene = new Scene(vbox, 600, 400);

        // Step 8: Set the scene on the stage and show it
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Step 9: Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}

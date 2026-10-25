import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TipCalculator extends Application {

    private TextField billAmountField;
    private TextField tipPercentageField;
    private Label tipAmountLabel;
    private Label errorLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tip Calculator");

        // UI Components
        Label titleLabel = new Label("Tip Calculator");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        billAmountField = new TextField();
        billAmountField.setPromptText("Bill Amount");

        tipPercentageField = new TextField();
        tipPercentageField.setPromptText("Tip Percentage (e.g., 15)");

        Button calculateButton = new Button("Calculate Tip");
        calculateButton.setMaxWidth(Double.MAX_VALUE);
        calculateButton.setOnAction(e -> calculateTip());

        Button resetButton = new Button("Reset");
        resetButton.setMaxWidth(Double.MAX_VALUE);
        resetButton.setOnAction(e -> reset());

        tipAmountLabel = new Label("Tip Amount: $0.00");
        tipAmountLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        // Layout Setup
        VBox vbox = new VBox(12.0);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20.0));
        vbox.getChildren().addAll(
            titleLabel,
            billAmountField,
            tipPercentageField,
            calculateButton,
            resetButton,
            tipAmountLabel,
            errorLabel
        );

        // Scene and Stage
        Scene scene = new Scene(vbox, 320, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateTip() {
        try {
            errorLabel.setText("");

            String billText = billAmountField.getText() == null ? "" : billAmountField.getText().trim();
            String tipText = tipPercentageField.getText() == null ? "" : tipPercentageField.getText().trim();

            if (billText.isEmpty() || tipText.isEmpty()) {
                errorLabel.setText("Error: Please enter both bill amount and tip percentage.");
                tipAmountLabel.setText("Tip Amount: $0.00");
                return;
            }

            double billAmount = Double.parseDouble(billText);
            double tipPercentage = Double.parseDouble(tipText);

            if (!Double.isFinite(billAmount) || !Double.isFinite(tipPercentage)) {
                errorLabel.setText("Error: Values must be valid numbers.");
                tipAmountLabel.setText("Tip Amount: $0.00");
                return;
            }

            if (billAmount < 0 || tipPercentage < 0) {
                errorLabel.setText("Error: Values cannot be negative.");
                tipAmountLabel.setText("Tip Amount: $0.00");
                return;
            }

            if (tipPercentage > 100) {
                errorLabel.setText("Error: Tip percentage cannot exceed 100%.");
                tipAmountLabel.setText("Tip Amount: $0.00");
                return;
            }

            double tipAmount = billAmount * (tipPercentage / 100.0);
            tipAmountLabel.setText(String.format("Tip Amount: $%.2f", tipAmount));

        } catch (NumberFormatException e) {
            errorLabel.setText("Error: Invalid input. Please enter numbers.");
            tipAmountLabel.setText("Tip Amount: $0.00");
        }
    }

    private void reset() {
        billAmountField.clear();
        tipPercentageField.clear();
        tipAmountLabel.setText("Tip Amount: $0.00");
        errorLabel.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
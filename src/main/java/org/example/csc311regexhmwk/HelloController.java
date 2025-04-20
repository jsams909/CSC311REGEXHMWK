package org.example.csc311regexhmwk;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class HelloController implements Initializable {
    // Regex code for formatting what can go into the text fields
    private static final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z]+");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[A-Z,a-z]{1}+[A-Z,a-z,0-9]+@farmingdale.edu");
    private static final Pattern DOB_PATTERN = Pattern.compile("[0-9{2}]+/[0-9{2}]+/[1-2]{1}[0-9]{3}+");
    private static final Pattern ZIP_PATTERN = Pattern.compile("[0-9]{5}");

    // Background image URL
    private static final String BACKGROUND_IMAGE_URL = "https://wallpapers.com/images/featured-full/doodle-background-0zqcavlknvk8aths.jpg";

    // GUI components
    @FXML private StackPane rootPane;
    @FXML private GridPane grid;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField dobField;
    @FXML private TextField zipField;
    @FXML private Button addButton;
    @FXML private Text firstNameError;
    @FXML private Text lastNameError;
    @FXML private Text emailError;
    @FXML private Text dobError;
    @FXML private Text zipError;


    private boolean isFirstNameValid = false;
    private boolean isLastNameValid = false;
    private boolean isEmailValid = false;
    private boolean isDobValid = false;
    private boolean isZipValid = false;

    /** Sets what happens on launching the program **/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupBackground();
        setupValidation();
        setupButtonAction();
    }

    /** Sets the background image **/
    private void setupBackground() {
        try {
            BackgroundImage backgroundImage = new BackgroundImage(
                    new Image(BACKGROUND_IMAGE_URL, true),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
            );
            rootPane.setBackground(new Background(backgroundImage));

            // Makes the background slightly transparent and removes the color to make it easier to see
            grid.setBackground(new Background(new BackgroundFill(
                    Color.rgb(255, 255, 255, 0.8),
                    new CornerRadii(10),
                    Insets.EMPTY
            )));
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            rootPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    /** The overall method that is called to change screens **/
    private void setupButtonAction() {
        addButton.setOnAction(e -> showSuccessScreen());
    }

    /** The method that checks the fields and makes sure they are filled out **/
    private void setupValidation() {
        firstNameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateFirstName();
            }
        });

        lastNameField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateLastName();
            }
        });

        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateEmail();
            }
        });

        dobField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateDob();
            }
        });

        zipField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateZipCode();
            }
        });
    }

    /** Checks the firstName and makes sure it matches the regex and gives alerts if not **/
    private void validateFirstName() {
        String firstName = firstNameField.getText().trim();

        if (firstName.isEmpty()) {
            firstNameError.setText("Required field");
            isFirstNameValid = false;
        } else if (!NAME_PATTERN.matcher(firstName).matches()) {
            firstNameError.setText("2-25 alphabetic characters only");
            isFirstNameValid = false;
        } else {
            firstNameError.setText("✓");
            firstNameError.setFill(Color.GREEN);
            isFirstNameValid = true;
        }
        updateAddButtonState();
    }

    /** Checks the lastname and makes sure it matches the regex and gives alerts if not **/
    private void validateLastName() {
        String lastName = lastNameField.getText().trim();

        if (lastName.isEmpty()) {
            lastNameError.setText("Required field");
            isLastNameValid = false;
        } else if (!NAME_PATTERN.matcher(lastName).matches()) {
            lastNameError.setText("2-25 alphabetic characters only");
            isLastNameValid = false;
        } else {
            lastNameError.setText("✓");
            lastNameError.setFill(Color.GREEN);
            isLastNameValid = true;
        }
        updateAddButtonState();
    }

    /**
     * Checks the email and makes sure it specifically is a farmingdale.edu email and adds alerts if it is not
     * @throws NullPointerException if the emailField is null
     */
    private void validateEmail() {
        String email = emailField.getText().trim();

        if (email.isEmpty()) {
            emailError.setText("Required field");
            isEmailValid = false;
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            emailError.setText("Must be a valid Farmingdale email");
            isEmailValid = false;
        } else {
            emailError.setText("✓");
            emailError.setFill(Color.GREEN);
            isEmailValid = true;
        }
        updateAddButtonState();
    }

    /** Checks the DOB and makes sure it matches the regex and gives alerts if not (/ included) **/
    private void validateDob() {
        String dob = dobField.getText().trim();

        if (dob.isEmpty()) {
            dobError.setText("Required field");
            isDobValid = false;
        } else if (!DOB_PATTERN.matcher(dob).matches()) {
            dobError.setText("Format: MM/DD/YYYY");
            isDobValid = false;
        } else {
            dobError.setText("✓");
            dobError.setFill(Color.GREEN);
            isDobValid = true;
        }
        updateAddButtonState();
    }

    /** Checks the Zip and makes sure it matches the regex and gives alerts if not **/
    private void validateZipCode() {
        String zip = zipField.getText().trim();

        if (zip.isEmpty()) {
            zipError.setText("Required field");
            isZipValid = false;
        } else if (!ZIP_PATTERN.matcher(zip).matches()) {
            zipError.setText("Must be 5 digits");
            isZipValid = false;
        } else {
            zipError.setText("✓");
            zipError.setFill(Color.GREEN);
            isZipValid = true;
        }
        updateAddButtonState();
    }

    /** Unfreezes the add button once all fields are filled correctly **/
    private void updateAddButtonState() {
        addButton.setDisable(!(isFirstNameValid && isLastNameValid && isEmailValid && isDobValid && isZipValid));
    }

    /** Method that loads the success screen from FXML **/
    private void showSuccessScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SuccessScreen.fxml"));
            Parent successRoot = loader.load();

            // Get the controller and set the user information
            SuccessController successController = loader.getController();
            successController.setUserInfo(firstNameField.getText(), lastNameField.getText());

            // Create a new scene with the success screen
            Scene successScene = new Scene(successRoot, 400, 300);

            // Get the current stage and set the new scene
            Stage primaryStage = (Stage) addButton.getScene().getWindow();
            primaryStage.setScene(successScene);

        } catch (IOException e) {
            System.err.println("Error loading success screen: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
package org.example.csc311regexhmwk;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

    public class SuccessController implements Initializable {
        // Background image URL
        private static final String BACKGROUND_IMAGE_URL = "https://wallpapers.com/images/featured-full/doodle-background-0zqcavlknvk8aths.jpg";

        @FXML private StackPane successRoot;
        @FXML private GridPane successGrid;
        @FXML private Text successMessage;
        @FXML private Text userInfo;
        @FXML private Button closeButton;


        private String firstName;
        private String lastName;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            setupBackground();
        }

        /**
         * Sets up the background image for the success screen
         */
        private void setupBackground() {
            try {
                BackgroundImage backgroundImage = new BackgroundImage(
                        new Image(BACKGROUND_IMAGE_URL, true),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
                );
                successRoot.setBackground(new Background(backgroundImage));

                // Makes the background slightly transparent to make it easier to see the text fields
                successGrid.setBackground(new Background(new BackgroundFill(
                        Color.rgb(255, 255, 255, 0.8),
                        new CornerRadii(10),
                        Insets.EMPTY
                )));
            } catch (Exception e) {
                System.err.println("Error loading background image: " + e.getMessage());
                successRoot.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }

        /**
         * Prints the user information in the success screen
         * @param firstName the user's first name
         * @param lastName the user's last name
         */
        public void setUserInfo(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
            userInfo.setText("Welcome, " + firstName + " " + lastName + "!");
        }

        /**
         * Handler for the close button action
         */
        @FXML
        private void handleCloseButton() {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }


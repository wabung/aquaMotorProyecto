package mechanic.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;

// 1. Quitamos "extends BorderPane"
public class TitleCardController {

    @FXML private Label titleLabel;
    @FXML private ImageView imageTitle;

    // 2. HEMOS BORRADO EL CONSTRUCTOR.
    // JavaFX inyectará los elementos automáticamente.

    /**
     * Sets the content of the card safely.
     * If the image is not found, it logs a warning instead of crashing.
     */
    public void setContent(String text, String imagePath) {
        if (titleLabel != null) {
            titleLabel.setText(text);
        }

        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                // Use getResourceAsStream to check if file exists first
                InputStream resourceStream = getClass().getResourceAsStream(imagePath);

                if (resourceStream == null) {
                    System.err.println("⚠️ WARNING: Image not found at path: " + imagePath);
                } else {
                    Image img = new Image(resourceStream);
                    if (imageTitle != null) {
                        imageTitle.setImage(img);
                    }
                }
            } catch (Exception e) {
                System.err.println("❌ ERROR: Could not load image: " + e.getMessage());
            }
        }
    }
}
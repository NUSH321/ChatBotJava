import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChatDialog();
        });
    }

    public static String getResponseFromAI(String input) {
        String yoloUrl = "http://yolo-service:8081/predict"; // Replace with the actual YOLO endpoint

        try {
            // Create a unique filename for the image
            String filename = UUID.randomUUID().toString() + ".png";

            // Convert input text to an image and save it
            createImageFromText(input, filename);

            // Create an HTTP client
            OkHttpClient client = new OkHttpClient();

            // Load the image file
            File imageFile = new File(filename);

            // Create a request body with the image
            RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", imageFile.getName(), RequestBody.create(imageFile, MediaType.parse("image/png")))
                .build();

            // Create an HTTP request
            Request request = new Request.Builder()
                .url(yoloUrl)
                .post(requestBody)
                .build();

            // Send the request to the YOLO service
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return "Detected Objects: " + responseBody; // Assuming the YOLO service returns a list of detected objects
            } else {
                return "Error: " + response.code();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public static void createImageFromText(String text, String filename) {
        int width = 400;
        int height = 100;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.getGraphics();

        // Set background color
        graphics.setColor(java.awt.Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        // Set text color and font
        graphics.setColor(java.awt.Color.BLACK);
        graphics.setFont(new Font("Arial", Font.PLAIN, 16));

        // Draw text
        graphics.drawString(text, 10, 50);

        // Save the image to a file
        try {
            ImageIO.write(bufferedImage, "PNG", new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

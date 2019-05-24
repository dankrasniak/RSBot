package rsbot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyFile {

    private static int positiveCount = 0;
    private static int negativeCount = 0;

    public static void save(BufferedImage bufferedImage, boolean positiveImage) {
        File outputFile = new File (
                "src/main/resources/" + (positiveImage ?
                        "positive/image" + positiveCount++ + ".bmp" :
                        "negative/image" + negativeCount++ + ".bmp"));
        try {
            ImageIO.write(bufferedImage, "bmp", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

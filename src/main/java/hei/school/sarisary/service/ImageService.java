package hei.school.sarisary.service;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageService {
  private final String IMAGE_FORMAT = "png";

  public File toBlackAndWhite(MultipartFile multipartFile) throws IOException {
    BufferedImage bufferedimage = this.multipartFileToBufferedImage(multipartFile);

    ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
    ColorConvertOp op = new ColorConvertOp(cs, null);

    BufferedImage bufferedImageBNW = op.filter(bufferedimage, null);
    return this.bufferedImageToMultipartFile(bufferedImageBNW);
  }

  private BufferedImage multipartFileToBufferedImage(MultipartFile file) throws IOException {
    return ImageIO.read(file.getInputStream());
  }

  private File bufferedImageToMultipartFile(BufferedImage bufferedImage) throws IOException {
    File outputFile = File.createTempFile("image", "." + this.IMAGE_FORMAT);
    ImageIO.write(bufferedImage, this.IMAGE_FORMAT, outputFile);
    return outputFile;
  }
}

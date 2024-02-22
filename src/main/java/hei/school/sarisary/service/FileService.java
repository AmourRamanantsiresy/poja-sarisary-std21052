package hei.school.sarisary.service;

import hei.school.sarisary.PojaGenerated;
import hei.school.sarisary.file.BucketComponent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@PojaGenerated
@Service
@AllArgsConstructor
@Slf4j
public class FileService {
  private ImageService imageService;
  private final int URL_EXPIRATION = 3_600;
  private BucketComponent bucketComponent;

  public String getUrlById(String id) {
    URL presignUrl = bucketComponent.presign(id, Duration.ofSeconds(this.URL_EXPIRATION));
    return presignUrl.toString();
  }

  public void upload(String id, MultipartFile multipartFile) throws IOException {
    File blackAndWhiteImage = imageService.toBlackAndWhite(multipartFile);
    bucketComponent.upload(blackAndWhiteImage, id);
  }
}

package hei.school.sarisary.endpoint.rest.controller;

import hei.school.sarisary.service.FileService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/black-and-white")
public class FileController {
  private final FileService fileService;

  @GetMapping("/{id}")
  public String getBlackAndWhite(@PathVariable String id) {
    return fileService.getUrlById(id);
  }

  @PutMapping(
      value = "/{id}",
      produces = {MediaType.IMAGE_PNG_VALUE})
  public void putBlackAndWhite(@PathVariable String id, @RequestPart("file") MultipartFile file)
      throws IOException {
    fileService.upload(id, file);
  }
}

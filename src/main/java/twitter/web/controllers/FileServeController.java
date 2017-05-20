package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import twitter.service.storage.StorageFileNotFoundException;
import twitter.service.storage.StorageService;

/**
 * Created by Nikolay on 16.04.2017.
 */
@Controller
@RequestMapping("/files")
public class FileServeController {

  private final StorageService storageService;

  @Autowired
  public FileServeController(StorageService storageService) {
    this.storageService = storageService;
  }

  @RequestMapping("/{filename:.+}")
  public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
    Resource file = storageService.loadAsResource(filename);
    return ResponseEntity
        .ok()
        .header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + file.getFilename() + "\"")
        .body(file);
  }

  @ExceptionHandler(StorageFileNotFoundException.class)
  public String handleStorageFileNotFound(WebRequest request, StorageFileNotFoundException exc) {
    return "redirect:/404" + "?lang=" + request.getLocale().getCountry();
  }
}

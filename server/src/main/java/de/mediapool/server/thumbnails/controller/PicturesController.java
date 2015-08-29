package de.mediapool.server.thumbnails.controller;

import java.io.File;

import javax.ws.rs.PathParam;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.controller.MPController;

@RestController
@RequestMapping("/rest/picture")
public class PicturesController implements MPController {

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public FileSystemResource getFormPDF(@PathParam(value = "id") String file_id) {
		return new FileSystemResource(new File("D:\\projekt_mediapool\\pictures\\vector_tux.864e6cdcc23e.png"));
	}
}

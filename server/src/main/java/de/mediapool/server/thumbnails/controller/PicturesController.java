package de.mediapool.server.thumbnails.controller;

import javax.ws.rs.PathParam;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.controller.MPController;

@RestController
@RequestMapping("/rest/picture")
public class PicturesController implements MPController {

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "image/png")
	public ClassPathResource getFormPDF(@PathParam(value = "id") String file_id) {
		return new ClassPathResource("thumbnails/bDpi3sixe9YwWB5KTPwmjhqZQGk.jpg");
	}
}

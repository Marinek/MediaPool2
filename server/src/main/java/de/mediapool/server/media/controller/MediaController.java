package de.mediapool.server.media.controller;

import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.domain.DocumentUtil;
import de.mediapool.server.core.domain.json.DocumentDTO;
import de.mediapool.server.media.domain.MediaAttributeDTO;

@RestController
@RequestMapping("/media")
public class MediaController {

	private static final Logger logger = LoggerFactory.getLogger(MediaController.class);

	@PostConstruct
	public void init () {
		logger.debug("Invoking: init()");
	}
	
	@RequestMapping("/details")
	public DocumentDTO<MediaAttributeDTO> getMediaDetails() {
		MediaAttributeDTO mediaAttributeDTO = new MediaAttributeDTO();
		
		mediaAttributeDTO.setId(UUID.randomUUID().toString());
		mediaAttributeDTO.setName("Das ist meine Testmedia");
		mediaAttributeDTO.setPublished(new Date());
		
		return DocumentUtil.getDocument(mediaAttributeDTO);
		
	}
}

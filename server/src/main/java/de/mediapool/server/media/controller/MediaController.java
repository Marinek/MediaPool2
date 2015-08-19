package de.mediapool.server.media.controller;

import java.nio.file.Files;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.mediapool.server.core.domain.DocumentUtil;
import de.mediapool.server.core.domain.json.DocumentDTO;
import de.mediapool.server.media.domain.MediaAttributeDTO;
import de.mediapool.server.media.repository.MediaRepository;

@RestController
@RequestMapping("/media")
public class MediaController {

	private static final Logger logger = LoggerFactory.getLogger(MediaController.class);

	@Autowired MediaRepository mediaRepository;

	@Autowired GraphDatabase graphDatabase;
	
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
		
		mediaRepository.save(mediaAttributeDTO);
		
		MediaAttributeDTO findByName = mediaRepository.findByName("Das ist meine Testmedia");
		
		return DocumentUtil.getDocument(findByName);
		
	}
	
}

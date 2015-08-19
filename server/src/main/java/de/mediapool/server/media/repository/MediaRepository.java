package de.mediapool.server.media.repository;

import org.springframework.data.repository.CrudRepository;

import de.mediapool.server.media.domain.MediaAttributeDTO;

public interface MediaRepository extends CrudRepository<MediaAttributeDTO, String> {

	MediaAttributeDTO findByName(String name);

	Iterable<MediaAttributeDTO> findByTeammatesName(String name);

}

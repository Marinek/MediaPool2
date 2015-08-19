package de.mediapool.server.media.repository;

import org.springframework.data.repository.CrudRepository;

import de.mediapool.server.media.domain.MediaNodeDTO;

public interface MediaRepository extends CrudRepository<MediaNodeDTO, String> {

	public MediaNodeDTO findByName(String name);

	public Iterable<MediaNodeDTO> findByActorsName(String name);

}

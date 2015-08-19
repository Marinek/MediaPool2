package de.mediapool.server.media.repository;

import org.springframework.data.repository.CrudRepository;

import de.mediapool.server.media.domain.MediaNodeDTO;

public interface ActorRepository  extends CrudRepository<MediaNodeDTO, String> {

	public ActorRepository findByName(String name);

}

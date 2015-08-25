package de.mediapool.server.entities.media.movies.repository;

import org.springframework.data.repository.CrudRepository;

import de.mediapool.server.entities.media.domain.MediaNodeDTO;

public interface ActorRepository  extends CrudRepository<MediaNodeDTO, String> {

	public ActorRepository findByName(String name);

}

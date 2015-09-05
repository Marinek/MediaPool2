package de.mediapool.server.core.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * This interface marks a DTO (DataTransfereObject). DTOs are used for external
 * communication (e.g. via a restcontroller). Thus it extends the {@link Serializable} interface.
 *  
 * @author marcinek
 * @since 1.0.0
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public interface DataTransfereObject extends Serializable {

}

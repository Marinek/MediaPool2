package de.mediapool.server.core.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This interface marks a DTO (DataTransfereObject). DTOs are used for external
 * communication (e.g. via a restcontroller). Thus it extends the {@link Serializable} interface.
 *  
 * @author marcinek
 * @since 1.0.0
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.WRAPPER_OBJECT, property="type")
public interface DataTransfereObject extends Serializable {

	
}

package io.github.astrapi69.crypt.info.viewmodel;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * The class {@link Resource}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Resource
{
	/** The checksum from this resource. */
	String checksum;
	/** The binary data from this resource. */
	byte[] content;
	/** The content type from this resource. */
	String contentType;
	/** The date when this resource is created in the database. */
	Date created;
	/**
	 * A flag that indicates that the resource is deleted from the user. Will be deleted in batch
	 * process.
	 */
	Boolean deletedFlag;
	/** A description for this resource. */
	String description;
	/** The filename from this resource. */
	String filename;
	/** The size from this resource. */
	long filesize;
}

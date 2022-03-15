/**
 * The MIT License
 *
 * Copyright (C) 2020 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.crypt.info.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.github.astrapi69.crypt.info.enumtype.AuthenticationType;
import io.github.astrapi69.data.enums.DatabasePrefix;
import io.github.astrapi69.data.identifiable.Identifiable;
import io.github.astrapi69.entity.uniqueable.UUIDEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = AuthenticationInfos.TABLE_NAME)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class AuthenticationInfos extends UUIDEntity
{

	public static final String SINGULAR_ENTITY_NAME = "authentication_info";
	public static final String TABLE_NAME = SINGULAR_ENTITY_NAME + "s";

	/** The type for the sign in */
	static final String CONVERTER_NAME_TYPE = "authenticationTypeConverter";

	static final String COLUMN_NAME_APPLICATION_FILE = "application_file";
	static final String COLUMN_NAME_KEY_FILE = "key_file";
	static final String COLUMN_NAME_TYPE = "type";
	static final String JOIN_COLUMN_FOREIGN_KEY_AUTHENTICATION_INFOS_FILE_CREATION_INFOS_ID = DatabasePrefix.FOREIGN_KEY_PREFIX
		+ TABLE_NAME + DatabasePrefix.UNDERSCORE + AuthenticationInfos.SINGULAR_ENTITY_NAME
		+ DatabasePrefix.UNDERSCORE + Identifiable.COLUMN_NAME_ID;
	/** The file name for the encrypted data file for the application */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = COLUMN_NAME_APPLICATION_FILE, foreignKey = @ForeignKey(name = JOIN_COLUMN_FOREIGN_KEY_AUTHENTICATION_INFOS_FILE_CREATION_INFOS_ID))
	FileCreationInfos applicationFile;

	/** The file name for the key file */
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = COLUMN_NAME_KEY_FILE, foreignKey = @ForeignKey(name = JOIN_COLUMN_FOREIGN_KEY_AUTHENTICATION_INFOS_FILE_CREATION_INFOS_ID))
	FileCreationInfos keyFile;

	/** The master password */
	@Column(length = 1024)
	String password;

	/** The auth type */
	@Enumerated(EnumType.STRING)
	@Column(name = COLUMN_NAME_TYPE)
	AuthenticationType type;
}

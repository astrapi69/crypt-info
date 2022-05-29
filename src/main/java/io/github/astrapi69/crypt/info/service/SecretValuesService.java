package io.github.astrapi69.crypt.info.service;

import io.github.astrapi69.crypt.info.jpa.entity.SecretValues;
import io.github.astrapi69.crypt.info.jpa.repository.SecretValuesRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;

import io.github.astrapi69.crypt.info.jpa.entity.FileCreationInfos;
import io.github.astrapi69.crypt.info.jpa.repository.FileCreationInfosRepository;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecretValuesService
{
	SecretValuesRepository repository;


	public SecretValues save(SecretValues entity)
	{
		return repository.save(entity);
	}
}

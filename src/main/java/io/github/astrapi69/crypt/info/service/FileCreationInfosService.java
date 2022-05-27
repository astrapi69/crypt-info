package io.github.astrapi69.crypt.info.service;

import io.github.astrapi69.crypt.info.jpa.entity.AuthenticationInfos;
import io.github.astrapi69.crypt.info.jpa.entity.FileCreationInfos;
import io.github.astrapi69.crypt.info.jpa.repository.FileCreationInfosRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileCreationInfosService
{
	FileCreationInfosRepository repository;


	public FileCreationInfos save(FileCreationInfos entity)
	{
		return repository.save(entity);
	}
}

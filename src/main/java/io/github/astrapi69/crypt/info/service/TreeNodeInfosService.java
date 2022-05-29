package io.github.astrapi69.crypt.info.service;

import io.github.astrapi69.crypt.info.jpa.entity.TreeNodeInfos;
import io.github.astrapi69.crypt.info.jpa.repository.TreeNodeInfosRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;

import io.github.astrapi69.crypt.info.jpa.entity.SecretValues;
import io.github.astrapi69.crypt.info.jpa.repository.SecretValuesRepository;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TreeNodeInfosService
{
	TreeNodeInfosRepository repository;

	public TreeNodeInfos save(TreeNodeInfos entity)
	{
		return repository.save(entity);
	}
}

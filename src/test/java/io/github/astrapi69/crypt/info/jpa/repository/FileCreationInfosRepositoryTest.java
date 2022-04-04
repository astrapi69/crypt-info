package io.github.astrapi69.crypt.info.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.astrapi69.crypt.info.integration.AbstractIntegrationTest;
import io.github.astrapi69.crypt.info.jpa.entity.FileCreationInfos;

class FileCreationInfosRepositoryTest extends AbstractIntegrationTest
{
	@Autowired
	FileCreationInfosRepository repository;


	@Test
	public void testSave()
	{
		FileCreationInfos entity = FileCreationInfos.builder().filePath("/home/user/.config/")
			.fileName("secret.mcdb").build();

		FileCreationInfos saved = repository.save(entity);
		assertNotNull(saved);

		AtomicReference<FileCreationInfos> atomicReference = new AtomicReference<>();
		repository.findById(saved.getId()).ifPresent(ent -> atomicReference.set(ent));
		assertEquals(saved, atomicReference.get());
	}
}

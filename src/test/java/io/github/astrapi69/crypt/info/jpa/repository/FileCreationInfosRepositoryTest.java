package io.github.astrapi69.crypt.info.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.atomic.AtomicReference;

import io.github.astrapi69.crypt.info.mapper.FileCreationInfosMapper;
import io.github.astrapi69.crypt.info.viewmodel.FileCreationInfo;
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
		FileCreationInfosMapper mapper;
		FileCreationInfos actual;
		FileCreationInfos expected;
		FileCreationInfos entity = FileCreationInfos.builder().filePath("/home/user/.config/")
			.fileName("secret.mcdb").build();

		expected= repository.save(entity);
		assertNotNull(expected);

		AtomicReference<FileCreationInfos> atomicReference = new AtomicReference<>();
		repository.findById(expected.getId()).ifPresent(ent -> atomicReference.set(ent));
		actual = atomicReference.get();
		assertEquals(expected, actual);
		mapper = new FileCreationInfosMapper();
		FileCreationInfo dtoActual = mapper.map(actual, FileCreationInfo.class);
		FileCreationInfo dtoExpected = FileCreationInfo.builder().filePath("/home/user/.config/")
			.fileName("secret.mcdb").build();
		assertEquals(dtoExpected, dtoActual);
	}
}

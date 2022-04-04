package io.github.astrapi69.crypt.info.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.astrapi69.crypt.info.enumtype.AuthenticationType;
import io.github.astrapi69.crypt.info.integration.AbstractIntegrationTest;
import io.github.astrapi69.crypt.info.jpa.entity.AuthenticationInfos;
import io.github.astrapi69.crypt.info.jpa.entity.FileCreationInfos;

class AuthenticationInfosRepositoryTest extends AbstractIntegrationTest
{
	@Autowired
	AuthenticationInfosRepository repository;

	@Autowired
	FileCreationInfosRepository fileCreationInfosRepository;

	@Test
	public void testSave()
	{
		FileCreationInfos applicationFile = fileCreationInfosRepository.save(FileCreationInfos
			.builder().filePath("/home/user/.config/").fileName("secret.mcdb").build());
		FileCreationInfos keyFile = fileCreationInfosRepository.save(FileCreationInfos.builder()
			.filePath("/home/user/.config/").fileName("secret.key").build());
		AuthenticationInfos entity = AuthenticationInfos.builder().applicationFile(applicationFile)
			.keyFile(keyFile).password("top-secret")
			.type(AuthenticationType.PASSWORD_AND_PRIVATE_KEY).build();

		AuthenticationInfos saved = repository.save(entity);
		assertNotNull(saved);

		AtomicReference<AuthenticationInfos> atomicReference = new AtomicReference<>();
		repository.findById(saved.getId()).ifPresent(ent -> atomicReference.set(ent));
		assertEquals(saved, atomicReference.get());
	}
}

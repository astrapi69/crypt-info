package io.github.astrapi69.crypt.info.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.astrapi69.crypt.info.integration.AbstractIntegrationTest;
import io.github.astrapi69.crypt.info.jpa.entity.SecretValues;

class SecretValuesRepositoryTest extends AbstractIntegrationTest
{

	@Autowired
	SecretValuesRepository repository;


	@Test
	public void testSave()
	{
		SecretValues entity = SecretValues.builder().notes("some note").password("is encrypted")
			.title("gmail").username("foo@gmail.com").build();
		SecretValues saved = repository.save(entity);
		assertNotNull(saved);

		AtomicReference<SecretValues> atomicReference = new AtomicReference<>();
		repository.findById(saved.getId()).ifPresent(ent -> atomicReference.set(ent));
		assertEquals(saved, atomicReference.get());
	}
}

package io.github.astrapi69.crypt.info.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.astrapi69.crypt.info.integration.AbstractIntegrationTest;
import io.github.astrapi69.crypt.info.jpa.entity.TreeNodeInfos;

class TreeNodeInfosRepositoryTest extends AbstractIntegrationTest
{

	@Autowired
	TreeNodeInfosRepository repository;


	@Test
	public void testSave()
	{
		AtomicReference<TreeNodeInfos> atomicReference;

		atomicReference = new AtomicReference<>();

		TreeNodeInfos root = TreeNodeInfos.builder().parent(null).depth(0).node(true)
			.value("I'm root").build();


		TreeNodeInfos aChild = TreeNodeInfos.builder().parent(root).depth(1).node(true)
			.value("I'm a child").build();

		TreeNodeInfos saved = repository.save(root);
		assertNotNull(saved);


		repository.findById(saved.getId()).ifPresent(ent -> atomicReference.set(ent));
		assertEquals(saved, atomicReference.get());

		saved = repository.save(aChild);

		repository.findById(saved.getId()).ifPresent(ent -> atomicReference.set(ent));
		assertEquals(saved, atomicReference.get());
	}
}

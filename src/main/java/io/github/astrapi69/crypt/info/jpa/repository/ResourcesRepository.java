package io.github.astrapi69.crypt.info.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.github.astrapi69.crypt.info.jpa.entity.Resources;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, UUID>
{
	List<Resources> findByDescription(String description);
	List<Resources> findByFilename(String filename);
}

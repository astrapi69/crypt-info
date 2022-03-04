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
package io.github.astrapi69.crypt.info.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import io.github.astrapi69.crypt.info.jpa.entity.TreeNodeInfos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TreeNodesRepository extends JpaRepository<TreeNodeInfos, UUID>
{

	List<TreeNodeInfos> findByValue(String value);

	@Transactional
	@Query("select entity from TreeNodeInfos entity where entity.depth=:depth "
		+ " and entity.value=:value")
	List<TreeNodeInfos> findByDepthAndValue(@Param("depth") int depth,
		@Param("value") String value);

	@Transactional
	@Query("select entity from TreeNodeInfos entity where entity.depth=:depth "
		+ " and entity.value=:value " + " and entity.parent=:parent")
	List<TreeNodeInfos> findByDepthAndValueAndParent(@Param("depth") int depth,
		@Param("value") String value, @Param("parent") TreeNodeInfos parent);

	@Transactional
	@Query("select entity from TreeNodeInfos entity where entity.value=:value "
		+ " and entity.parent is null")
	Optional<TreeNodeInfos> findRootByValue(@Param("value") String value);

	@Query("select entity from TreeNodeInfos entity where entity.depth=:depth "
		+ " and entity.value=:value " + " and entity.parent=:parent " + "and entity.node=:node")
	Optional<TreeNodeInfos> findByDepthAndValueAndParentAndNode(@Param("depth") int depth,
		@Param("value") String value, @Param("parent") TreeNodeInfos parent,
		@Param("node") boolean node);

	@Query(value = "WITH RECURSIVE ancestors(id, parent_id, value, level) AS ("
		+ " SELECT pkp.id, pkp.parent_id, pkp.value, 1 AS level " + " FROM tree_nodes pkp "
		+ " WHERE pkp.id = :treeId " + " UNION ALL "
		+ " SELECT parent.id, parent.parent_id, parent.value, child.level + 1 AS level "
		+ " FROM tree_nodes parent " + " JOIN ancestors child " + " ON parent.id = child.parent_id "
		+ " )" + "SELECT * from ancestors ORDER BY level DESC", nativeQuery = true)
	List<TreeNodeInfos> findAncestors(@Param("treeId") UUID treeId);

	@Query(value = "WITH RECURSIVE children(id, parent_id, value) AS ("
		+ " SELECT pkp.id, pkp.parent_id, pkp.value, 1 AS level " + " FROM tree_nodes pkp "
		+ " WHERE pkp.id=:treeId " + " UNION ALL "
		+ " SELECT parent.id, parent.parent_id, parent.value, child.level + 1 AS level "
		+ " FROM tree_nodes parent " + " JOIN children child " + " ON child.id = parent.parent_id) "
		+ " SELECT * FROM children ", nativeQuery = true)
	List<TreeNodeInfos> getAllChildrenWithParent(@Param("treeId") UUID treeId);

	@Query(value = "select * from tree_nodes pkp where pkp.parent_id =:parent", nativeQuery = true)
	List<TreeNodeInfos> getChildren(@Param("parent") UUID parent);
}

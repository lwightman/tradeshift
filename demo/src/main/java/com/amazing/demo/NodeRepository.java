package com.amazing.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node, Long> {
    List<Node> findAllByParent(Node parent);

    Node findByRoot(boolean root);
}

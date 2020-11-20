package com.amazing.demo;

import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Accessors(prefix = "m")
@AllArgsConstructor
public class NodeService {
    final NodeRepository mNodeRepository;

    public void createTree() {
        Node root = mNodeRepository.findByRoot(true);

        if(root == null) {
            root = createNode("root", null, true);
            Node a = createNode("a", root, false);
            Node b = createNode("b", root, false);
            createNode("c", a, false);
            createNode("d", b, false);
            createNode("e", b, false);
            createNode("f", b, false);
            createNode("z", root, false);
        }
    }

    private Node createNode(String name, Node parent, boolean isRoot) {
        Node node = new Node(name, parent, isRoot);
        return mNodeRepository.save(node);
    }

    public Optional<Node> reparent(String nodeId, String newParentId) {
        Optional<Node> node = mNodeRepository.findById(Long.parseLong(nodeId));
        if (node.isPresent()) {
            Node tempNode = node.get();
            Optional<Node> parent = mNodeRepository.findById(Long.parseLong(newParentId));
            if (parent.isPresent()) {
                tempNode.setParent(parent.get());
                tempNode.calculateHeight();
                return Optional.of(mNodeRepository.save(tempNode));
            }
        }
        return Optional.empty();
    }

    public Optional<List<Node>> getDescendants(String nodeId) {
        Optional<Node> node = mNodeRepository.findById(Long.parseLong(nodeId));
        if (node.isPresent()) {
            Node parent = node.get();
            return Optional.of(getDescendants(parent));
        }
        return Optional.empty();
    }

    List<Node> getDescendants(Node parent) {
        List<Node> descendants = new ArrayList<>();

        List<Node> children = mNodeRepository.findAllByParent(parent);
        if (children.size() > 0) {
            descendants.addAll(children);
            for (Node child : children) {
              descendants.addAll(getDescendants(child));
            }
        }
        return descendants;
    }
}

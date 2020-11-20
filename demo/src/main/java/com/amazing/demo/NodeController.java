package com.amazing.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class NodeController {
    private final NodeService mNodeService;

    public NodeController(final NodeService nodeService) {
        mNodeService = nodeService;
        mNodeService.createTree();
    }

    @GetMapping("/reparent")
    public ResponseEntity<?> reparent(@RequestParam String nodeId, @RequestParam String newParentId) {
        Optional<Node> results = mNodeService.reparent(nodeId, newParentId);
        if(results.isPresent()){
            return new ResponseEntity<>(results.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failure reparenting node.", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/getDescendants")
    public ResponseEntity<?> getDescendants(@RequestParam(value = "node") String nodeId) {
        Optional<List<Node>> results = mNodeService.getDescendants(nodeId);
        if(results.isPresent()){
            return new ResponseEntity<>(results.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failure getting descendants.", HttpStatus.EXPECTATION_FAILED);
        }
    }
}

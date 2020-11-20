package com.amazing.demo;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(
        indexes = {
                @Index(name = "uidx_Node_Id", columnList = "id"),
        }
)

@EqualsAndHashCode
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column
    @Getter
    private final String name;

    @OneToOne
    @Getter
    @Setter
    private Node parent;

    @Getter
    private boolean root;

    @Column
    @Getter
    private int height;


    public Node(){
        this.name = UUID.randomUUID().toString();
        this.parent = null;
        this.root = false;
        calculateHeight();
    }

    public Node(String name, Node parent, boolean isRoot) {
        this.name = name;
        this.parent = parent;
        this.root = isRoot;
        calculateHeight();
    }

    void calculateHeight() {
        Node node = this;
        int height = 0;

        while (!node.root && node.parent != null) {
            height++;
            node = node.parent;
        }

        this.height = height;
    }
}

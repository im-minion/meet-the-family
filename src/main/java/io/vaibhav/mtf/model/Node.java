package io.vaibhav.mtf.model;

import io.vaibhav.mtf.constants.Constants;
import io.vaibhav.mtf.constants.Gender;
import io.vaibhav.mtf.exception.ExecutorException;

import java.util.LinkedHashSet;

public class Node {
    private static final Node unknown = new Node("Unknown");
    private String name;
    private Gender gender;
    private Node mother;
    private Node father;
    private Node spouse;
    private final LinkedHashSet<Node> children = new LinkedHashSet<>();

    private Node(String name) {
        this.name = name;
    }

    public Node(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        this.mother = unknown;
        this.father = unknown;
        this.spouse = unknown;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Node getMother() {
        return mother;
    }

    public void setMother(Node mother) {
        this.mother = mother;
    }

    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    public Node getSpouse() {
        return spouse;
    }

    public void setSpouse(Node spouse) {
        this.spouse = spouse;
    }

    public LinkedHashSet<Node> getChildren() {
        return children;
    }

    public void addChild(Node node) {
        if (this.gender == Gender.FEMALE) {
            this.children.add(node);
            this.spouse.children.add(node);
            node.mother = this;
            node.father = this.spouse;
        } else {
            throw new ExecutorException(Constants.CHILD_NOT_ADDED, new RuntimeException());
        }
    }
}

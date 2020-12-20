package io.vaibhav.mtf.executor;

import io.vaibhav.mtf.constants.Constants;
import io.vaibhav.mtf.constants.Gender;
import io.vaibhav.mtf.exception.ExecutorException;
import io.vaibhav.mtf.model.Family;
import io.vaibhav.mtf.model.Node;

import java.util.LinkedHashMap;

public class Executor implements IExecutor {
    private final Family family;
    private final LinkedHashMap<String, Node> currentFamilyMembers;

    public Executor(Family family) {
        this.family = family;
        this.currentFamilyMembers = this.family.getFamilyMembers();
    }

    @Override
    public void addChild(String motherName, String name, Gender gender) {
        Node motherNode = this.family.getFamilyMembers().get(motherName);

        if (null == motherNode) {
            throw new ExecutorException(Constants.PERSON_NOT_FOUND, new RuntimeException());
        }
        Node nodeToAdd = new Node(name, gender);
        motherNode.addChild(nodeToAdd);
        this.family.getFamilyMembers().put(name, nodeToAdd);
        System.out.println(Constants.CHILD_ADDED);
    }

    @Override
    public void getRelation(String name, String relation) {
        try {
            StringBuilder result = new StringBuilder();
            if (null == this.currentFamilyMembers || null == this.currentFamilyMembers.get(name)) {
                result.append(Constants.PERSON_NOT_FOUND);
                // Ideally should throw exception but as per the requirement just logging it
                // throw new ExecutorException(String.format("No Family Member Exists with name %s.", name), new RuntimeException());
            } else {
                switch (relation) {
                    case Constants.SON:
                        getChild(result, name, Gender.MALE);
                        break;
                    case Constants.DAUGHTER:
                        getChild(result, name, Gender.FEMALE);
                        break;
                    case Constants.SIBLINGS:
                        getSiblings(result, name);
                        break;
                    case Constants.PATERNAL_UNCLE: //Father’s brothers
                        Node fatherNode = this.currentFamilyMembers.get(name).getFather();
                        getSiblingsWithGender(fatherNode, result, name, Gender.MALE);
                        break;
                    case Constants.PATERNAL_AUNT: //Father’s sisters
                        Node fatherNode2 = this.currentFamilyMembers.get(name).getFather();
                        getSiblingsWithGender(fatherNode2, result, name, Gender.FEMALE);
                        break;
                    case Constants.MATERNAL_UNCLE: //Mother’s brothers
                        Node motherNode = this.currentFamilyMembers.get(name).getMother();
                        getSiblingsWithGender(motherNode, result, name, Gender.MALE);
                        break;
                    case Constants.MATERNAL_AUNT: //Mother’s sisters
                        Node motherNode2 = this.currentFamilyMembers.get(name).getMother();
                        getSiblingsWithGender(motherNode2, result, name, Gender.FEMALE);
                        break;
                    case Constants.SISTER_IN_LAW: //Spouse’s sisters,  Wives of siblings
                        Node spouseNode = this.currentFamilyMembers.get(name).getSpouse();
                        getSiblingsWithGender(spouseNode, result, name, Gender.FEMALE);
                        getSiblingsWives(result, name);
                        break;
                    case Constants.BROTHER_IN_LAW: //Spouse’s brothers,  Husbands of siblings
                        Node spouseNode2 = this.currentFamilyMembers.get(name).getSpouse();
                        getSiblingsWithGender(spouseNode2, result, name, Gender.MALE);
                        getSiblingsHusbands(result, name);
                        break;
                    default:
                        System.out.println("INVALID INPUT");
                        break;
                }
            }
            if (result.length() > 0) {
                System.out.println(result.toString().trim());
            } else {
                System.out.println(Constants.NONE);
            }
        } catch (Exception e) {
            throw new ExecutorException("Could not get Relation", e);
        }
    }

    private void getSiblingsWives(StringBuilder result, String name) {
        this.currentFamilyMembers.get(name).getMother().getChildren().
                forEach(sib -> {
                    if (!name.equals(sib.getName()) && sib.getGender() == Gender.MALE) {
                        result.append(sib.getSpouse()).append(" ");
                    }
                });
    }

    private void getSiblingsHusbands(StringBuilder result, String name) {
        this.currentFamilyMembers.get(name).getMother().getChildren().
                forEach(sib -> {
                    if (!name.equals(sib.getName()) && sib.getGender() == Gender.FEMALE) {
                        result.append(sib.getSpouse()).append(" ");
                    }
                });
    }

    private void getSiblingsWithGender(Node node, StringBuilder result, String name, Gender g) {
        node.getMother().getChildren()
                .forEach(sib -> {
                    if (!node.getName().equals(sib.getName()) && sib.getGender() == g) {
                        result.append(sib.getName()).append(" ");
                    }
                });
    }

    private void getSiblings(StringBuilder result, String name) {
        this.currentFamilyMembers.get(name).getMother().getChildren()
                .forEach(sib -> {
                    if (!name.equals(sib.getName())) {
                        result.append(sib.getName()).append(" ");
                    }
                });
    }

    private void getChild(StringBuilder result, String name, Gender g) {
        this.currentFamilyMembers.get(name).getChildren().stream()
                .filter(child -> child.getGender() == g)
                .forEach(daughter -> result.append(daughter.getName()).append(" "));
    }
}

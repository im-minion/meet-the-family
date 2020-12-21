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
            System.out.println(Constants.PERSON_NOT_FOUND);
            return;
            // Ideally should throw exception but as per the requirement just logging it
            // throw new ExecutorException(Constants.PERSON_NOT_FOUND, new RuntimeException());
        }
        if (Gender.FEMALE != motherNode.getGender()) {
            System.out.println(Constants.CHILD_NOT_ADDED);
            return;
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
                        getChildWithSpecificGender(result, name, Gender.MALE);
                        break;
                    case Constants.DAUGHTER:
                        getChildWithSpecificGender(result, name, Gender.FEMALE);
                        break;
                    case Constants.SIBLINGS:
                        getSiblings(result, name);
                        break;
                    case Constants.PATERNAL_UNCLE: //Father’s brothers
                        Node fatherNode = this.currentFamilyMembers.get(name).getFather();
                        if (isNodePresent(fatherNode)) {
                            getSiblingsWithSpecificGender(fatherNode, result, name, Gender.MALE);
                        }
                        break;
                    case Constants.PATERNAL_AUNT: //Father’s sisters
                        Node fatherNode2 = this.currentFamilyMembers.get(name).getFather();
                        if (isNodePresent(fatherNode2)) {
                            getSiblingsWithSpecificGender(fatherNode2, result, name, Gender.FEMALE);
                        }
                        break;
                    case Constants.MATERNAL_UNCLE: //Mother’s brothers
                        Node motherNode = this.currentFamilyMembers.get(name).getMother();
                        getSiblingsWithSpecificGender(motherNode, result, name, Gender.MALE);
                        break;
                    case Constants.MATERNAL_AUNT: //Mother’s sisters
                        Node motherNode2 = this.currentFamilyMembers.get(name).getMother();
                        getSiblingsWithSpecificGender(motherNode2, result, name, Gender.FEMALE);
                        break;
                    case Constants.SISTER_IN_LAW: //Spouse’s sisters,  Wives of siblings
                        Node spouseNode = this.currentFamilyMembers.get(name).getSpouse();
                        if (isNodePresent(spouseNode)) {
                            getSiblingsWithSpecificGender(spouseNode, result, name, Gender.FEMALE);
                        }
                        getSiblingsSpouseWithGender(result, name, Gender.MALE);
                        break;
                    case Constants.BROTHER_IN_LAW: //Spouse’s brothers,  Husbands of siblings
                        Node spouseNode2 = this.currentFamilyMembers.get(name).getSpouse();
                        if (isNodePresent(spouseNode2)) {
                            getSiblingsWithSpecificGender(spouseNode2, result, name, Gender.MALE);
                        }
                        getSiblingsSpouseWithGender(result, name, Gender.FEMALE);
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

    private void getSiblingsSpouseWithGender(StringBuilder result, String name, Gender g) {
        this.currentFamilyMembers.get(name).getMother().getChildren().
                forEach(sib -> {
                    if (!name.equals(sib.getName())
                            && sib.getGender() == g
                            && !Constants.UNKNOWN.equals(sib.getSpouse().getName())) {
                        result.append(sib.getSpouse().getName()).append(" ");
                    }
                });
    }

    private void getSiblingsWithSpecificGender(Node node, StringBuilder result, String name, Gender g) {
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

    private void getChildWithSpecificGender(StringBuilder result, String name, Gender g) {
        this.currentFamilyMembers.get(name).getChildren().stream()
                .filter(child -> child.getGender() == g)
                .forEach(daughter -> result.append(daughter.getName()).append(" "));
    }

    private boolean isNodePresent(Node node) {
        return !Constants.UNKNOWN.equals(node.getName());
    }

}

package io.vaibhav.mtf.model;

import java.util.LinkedHashMap;

public class Family {
    private final LinkedHashMap<String, Node> familyMembers;

    public Family(LinkedHashMap<String, Node> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public LinkedHashMap<String, Node> getFamilyMembers() {
        return familyMembers;
    }
}

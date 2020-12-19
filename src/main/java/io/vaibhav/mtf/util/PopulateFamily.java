package io.vaibhav.mtf.util;

import io.vaibhav.mtf.constants.Gender;
import io.vaibhav.mtf.model.Node;

import java.util.LinkedHashMap;

public class PopulateFamily {
    public static LinkedHashMap<String, Node> populateFamily() {
        LinkedHashMap<String, Node> familyNodes = new LinkedHashMap<>();

        Node shan = new Node("King Shan", Gender.MALE);
        Node anga = new Node("Queen Anga", Gender.FEMALE);
        Node chit = new Node("Chit", Gender.MALE);
        Node ish = new Node("Ish", Gender.MALE);
        Node vich = new Node("Vich", Gender.MALE);
        Node aras = new Node("Aras", Gender.MALE);
        Node satya = new Node("Satya", Gender.FEMALE);
        Node amba = new Node("Amba", Gender.FEMALE);
        Node dritha = new Node("Dritha", Gender.FEMALE);
        Node tritha = new Node("Tritha", Gender.FEMALE);
        Node vritha = new Node("Vritha", Gender.MALE);
        Node jaya = new Node("Jaya", Gender.MALE);
        Node yodhan = new Node("Yodhan", Gender.MALE);
        Node lika = new Node("Lika", Gender.FEMALE);
        Node vila = new Node("Vila", Gender.FEMALE);
        Node chika = new Node("Chika", Gender.FEMALE);
        Node chitra = new Node("Chitra", Gender.FEMALE);
        Node jnki = new Node("Jnki", Gender.FEMALE);
        Node ahit = new Node("Ahit", Gender.MALE);
        Node arit = new Node("Arit", Gender.MALE);
        Node laki = new Node("Laki", Gender.MALE);
        Node lavnya = new Node("Lavnya", Gender.FEMALE);
        Node vyan = new Node("Vyan", Gender.MALE);
        Node asva = new Node("Asva", Gender.MALE);
        Node vyas = new Node("Vyas", Gender.MALE);
        Node atya = new Node("Atya", Gender.FEMALE);
        Node satvy = new Node("Satvy", Gender.FEMALE);
        Node vasa = new Node("Vasa", Gender.MALE);
        Node krpi = new Node("Krpi", Gender.FEMALE);
        Node kriya = new Node("Kriya", Gender.MALE);
        Node krithi = new Node("Krithi", Gender.FEMALE);
//        vaib
        Node meg = new Node("Meg", Gender.MALE);

        familyNodes.put("King Shan", shan);
        familyNodes.put("Queen Anga", anga);
        familyNodes.put("Chit", chit);
        familyNodes.put("Ish", ish);
        familyNodes.put("Vich", vich);
        familyNodes.put("Aras", aras);
        familyNodes.put("Satya", satya);
        familyNodes.put("Amba", amba);
        familyNodes.put("Dritha", dritha);
        familyNodes.put("Tritha", tritha);
        familyNodes.put("Vritha", vritha);
        familyNodes.put("Jaya", jaya);
        familyNodes.put("Yodhan", yodhan);
        familyNodes.put("Lika", lika);
        familyNodes.put("Vila", vila);
        familyNodes.put("Chika", chika);
        familyNodes.put("Chitra", chitra);
        familyNodes.put("Jnki", jnki);
        familyNodes.put("Ahit", ahit);
        familyNodes.put("Arit", arit);
        familyNodes.put("Laki", laki);
        familyNodes.put("Lavnya", lavnya);
        familyNodes.put("Vyan", vyan);
        familyNodes.put("Asva", asva);
        familyNodes.put("Vyas", vyas);
        familyNodes.put("Atya", atya);
        familyNodes.put("Satvy", satvy);
        familyNodes.put("Vasa", vasa);
        familyNodes.put("Krpi", krpi);
        familyNodes.put("Kriya", kriya);
        familyNodes.put("Krithi", krithi);
//        vaib
        familyNodes.put("Meg", meg);

        shan.setSpouse(anga);
        anga.addChild(chit);
        anga.addChild(ish);
        anga.addChild(vich);
        anga.addChild(aras);
        anga.addChild(satya);

        chit.setSpouse(amba);
        amba.addChild(dritha);
        amba.addChild(tritha);
        amba.addChild(vritha);
        dritha.setSpouse(jaya);
        dritha.addChild(yodhan);

        vich.setSpouse(lika);
        lika.addChild(vila);
        lika.addChild(chika);

        aras.setSpouse(chitra);
        chitra.addChild(jnki);
        chitra.addChild(ahit);
        jnki.setSpouse(arit);
        jnki.addChild(laki);
        jnki.addChild(lavnya);

        satya.setSpouse(vyan);
        satya.addChild(asva);
        satya.addChild(vyas);
        satya.addChild(atya);
        asva.setSpouse(satvy);
        satvy.addChild(vasa);

        vyas.setSpouse(krpi);
        krpi.addChild(kriya);
        krpi.addChild(krithi);

        return familyNodes;
    }
}

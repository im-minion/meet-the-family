package io.vaibhav.mtf.executor;

import io.vaibhav.mtf.constants.Constants;
import io.vaibhav.mtf.constants.Gender;
import io.vaibhav.mtf.model.Family;
import io.vaibhav.mtf.model.Node;
import io.vaibhav.mtf.util.PopulateFamily;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertTrue;

public class ExecutorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    LinkedHashMap<String, Node> royalFamilyMembers = PopulateFamily.populateFamily();
    Family royalFamily = new Family(royalFamilyMembers);
    Executor royalFamilyExecutor = new Executor(royalFamily);

    @Before
    public void init() {

        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUp() {
        System.setOut(null);
    }

    @Test
    public void addChildPositive() {
        royalFamilyExecutor.addChild("Chitra", "Kurta", Gender.MALE);
        assertTrue(Constants.CHILD_ADDED.equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
    }

    @Test
    public void addChildNegative() {
        royalFamilyExecutor.addChild("Dummy", "Kurta", Gender.MALE);
        assertTrue(Constants.PERSON_NOT_FOUND.equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
    }

    @Test
    public void getPaternalUnclePositive() {
        royalFamilyExecutor.getRelation("Vritha", Constants.PATERNAL_UNCLE);
        assertTrue("Ish Vich Aras".equalsIgnoreCase(outContent.toString().trim()));
    }

    @Test
    public void getPaternalAuntPositive() {
        royalFamilyExecutor.getRelation("Vritha", Constants.PATERNAL_AUNT);
        assertTrue("Satya".equalsIgnoreCase(outContent.toString().trim()));
    }

    @Test
    public void getMaternalUnclePositive() {
        royalFamilyExecutor.getRelation("Vyas", Constants.MATERNAL_UNCLE);
        assertTrue("Chit Ish Vich Aras".equalsIgnoreCase(outContent.toString().trim()));
    }

    @Test
    public void getMaternalAuntPositive() {
        royalFamilyExecutor.getRelation("Vyas", Constants.MATERNAL_AUNT);
        assertTrue(Constants.NONE.equalsIgnoreCase(outContent.toString().trim()));
    }

    @Test
    public void getSisInLawPositive() {
        royalFamilyExecutor.getRelation("Jaya", Constants.SISTER_IN_LAW);
        assertTrue("Tritha".equalsIgnoreCase(outContent.toString().trim()));
    }

    @Test
    public void getBroInLawPositive() {
        royalFamilyExecutor.getRelation("Jaya", Constants.BROTHER_IN_LAW);
        assertTrue("Vritha".equalsIgnoreCase(outContent.toString().trim()));
    }

    @Test
    public void getSonPositive() {
        royalFamilyExecutor.getRelation("Aras", Constants.SON);
        assertTrue("Ahit".equalsIgnoreCase(outContent.toString().trim()));
    }

    @Test
    public void getSonNegative() {
        royalFamilyExecutor.getRelation("sdIsh", Constants.SON);
        assertTrue(Constants.PERSON_NOT_FOUND.equalsIgnoreCase(outContent.toString().trim()));
    }

    @Test
    public void getDaughterPositive() {
        royalFamilyExecutor.getRelation("Vyan", Constants.DAUGHTER);
        assertTrue("Atya".equalsIgnoreCase(outContent.toString().trim()));
    }

    @Test
    public void getDaughterNegative() {
        royalFamilyExecutor.getRelation("sdIsh", Constants.DAUGHTER);
        assertTrue(Constants.PERSON_NOT_FOUND.equalsIgnoreCase(outContent.toString().trim()));
    }
}

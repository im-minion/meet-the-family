package io.vaibhav.mtf;

import io.vaibhav.mtf.executor.Executor;
import io.vaibhav.mtf.model.Family;
import io.vaibhav.mtf.model.Node;
import io.vaibhav.mtf.parser.InputParser;
import io.vaibhav.mtf.util.PopulateFamily;

import java.util.LinkedHashMap;

public class MeetTheFamily {
    public static void main(String[] args) {
        LinkedHashMap<String, Node> royalFamilyMembers = PopulateFamily.populateFamily();
        Family royalFamily = new Family(royalFamilyMembers);
        Executor royalFamilyExecutor = new Executor(royalFamily);
        final InputParser inputParser = new InputParser(royalFamilyExecutor);

        switch (args.length) {
            case 0:
                break;
            case 1:
                inputParser.parseFileInput(args[0]);
                break;
        }
    }
}

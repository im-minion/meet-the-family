package io.vaibhav.mtf.parser;

import io.vaibhav.mtf.constants.Gender;
import io.vaibhav.mtf.executor.IExecutor;

import java.io.*;

import static io.vaibhav.mtf.constants.Constants.ADD_CHILD;
import static io.vaibhav.mtf.constants.Constants.GET_RELATION;

public class InputParser {

    //    private static final Commands commands = new Commands();
    private final IExecutor executor;// = new Executor();

    public InputParser(IExecutor executor) {
        this.executor = executor;
    }

    public void parseFileInput(String filePath) {
        File inputFile = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    parseTextInput(line.trim());
                }
            } catch (IOException ex) {
                System.out.println("Error in reading the input file. :(");
                ex.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the path specified. :(");
            e.printStackTrace();
        }
    }

    private void parseTextInput(String input) {
        String[] inputArray = input.split("\\s+");
//        Method method = commands.commandsMap.get(inputArray[0]);

//        try {
        //                case CREATE_FAMILY -> method.invoke(executor, inputArray[1]);
        switch (inputArray[0]) {
            case ADD_CHILD:
                executor.addChild(inputArray[1], inputArray[2], Gender.valueOf(inputArray[3].toUpperCase()));
                break;
            case GET_RELATION:
//                GET_RELATIONSHIP Vasa Siblings
                executor.getRelation(inputArray[1], inputArray[2]);
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
    }
}

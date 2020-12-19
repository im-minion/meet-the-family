//package io.vaibhav.mtf.constants;
//
//import io.vaibhav.mtf.executor.IExecutor;
//
//import java.lang.reflect.Method;
//import java.util.HashMap;
//import java.util.Map;
//
//import static io.vaibhav.mtf.constants.Constants.ADD_CHILD;
//import static io.vaibhav.mtf.constants.Constants.GET_RELATION;
//
//public class Commands {
//    public Map<String, Method> commandsMap;
//
//    public Commands() {
//        commandsMap = new HashMap<>();
//        try {
//            populateCommandsMap();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void populateCommandsMap() throws NoSuchMethodException {
//        commandsMap.put(ADD_CHILD, IExecutor.class.getMethod("addChild", String.class, String.class));
//        commandsMap.put(GET_RELATION, IExecutor.class.getMethod("getRelation", String.class, String.class));
//    }
//}

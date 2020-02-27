package com.salesforce;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

public class PackageManager {

    // key is the component name, value is the set of components it depends in
    private static final Map<String, Set<String>> dependsOnMap = new HashMap<>();

    // key is the component name and value is set of components which depends in the key;
    private static final Map<String, Set<String>> dependedOnMap = new HashMap<>();

    private static final Set<String> currentlyInstalled = new LinkedHashSet<>();

    static abstract class CommandHandler {
        void handle(String command, List<String> output) {
            //System.out.println(command);
            output.add(command);
            handleCommand(command, output);
        }
        abstract void handleCommand(String arr, List<String> output);
    }

    static class DependsCommandHandler extends CommandHandler {

        @Override
        public void handleCommand(String input, List<String> output) {
            String[] arr = input.split("\\s+");
            String componentDepends = arr[1];

            // iterate to populate both the maps, if the inverse dependency is already installed we will print it
            for (int i = 2 ; i < arr.length ; i ++) {
                String componentDependedOn = arr[i];
                if (dependsOnMap.containsKey(componentDependedOn) && dependsOnMap.get(componentDependedOn).contains(componentDepends)) {
                    //System.out.println(componentDependedOn + " depends on " + componentDepends + ", ignoring command");
                    output.add(componentDependedOn + " depends on " + componentDepends + ", ignoring command");
                } else {
                    if (dependsOnMap.containsKey(componentDepends)) {
                        dependsOnMap.get(componentDepends).add(componentDependedOn);
                    } else {
                        Set<String> set = new HashSet<>();
                        set.add(componentDependedOn);
                        dependsOnMap.put(componentDepends, set);
                    }

                    if (dependedOnMap.containsKey(componentDependedOn)) {
                        dependedOnMap.get(componentDependedOn).add(componentDepends);
                    } else {
                        Set<String> set = new HashSet<>();
                        set.add(componentDepends);
                        dependedOnMap.put(componentDependedOn, set);
                    }
                }
            }
        }
    }

    static class CommandFactory {

        public static CommandHandler getCommandHandler(String input) {
            String[] arr = input.split("\\s+");
            if (arr.length >= 1) {
                String command = arr[0];
                switch (command) {
                    case "DEPEND":
                        return new DependsCommandHandler();
                    case "INSTALL":
                        return new InstallCommandHandler();
                    case "LIST":
                        return new ListCommandHandler();
                    case "REMOVE":
                        return new RemoveCommandHandler();
                    case "END":
                        return new EndCommadHandler();

                }
            }
            return null;
        }

    }
    // INSTALL NETCARD
    static class InstallCommandHandler extends CommandHandler {

        @Override
        public void handleCommand(String input, List<String> output) {
            String[] arr = input.split("\\s+");
            String componentToInstall = arr[1];
            if (currentlyInstalled.contains(componentToInstall)) {
                //System.out.println(componentToInstall+" is already installed");
                output.add(componentToInstall+" is already installed");
                return;
            }
            recursiveInstall(componentToInstall, output);
        }

        // do a DFS
        private void recursiveInstall(String componentToInstall, List<String> output) {
            if (currentlyInstalled.contains(componentToInstall)) {
                return;
            }
            if (!dependsOnMap.containsKey(componentToInstall) || allDependenciesInstalled(componentToInstall)) {
                //System.out.println("Installing "+componentToInstall);
                output.add("Installing "+componentToInstall);
                currentlyInstalled.add(componentToInstall);
                return;
            }

            for (String componentDependsOn : dependsOnMap.get(componentToInstall)) {
                recursiveInstall(componentDependsOn, output);
            }
            currentlyInstalled.add(componentToInstall);
            //System.out.println("Installing "+componentToInstall);
            output.add("Installing "+componentToInstall);
        }

        //check if all dependencies installed.
        private boolean allDependenciesInstalled(String componentToInstall) {
            Set<String> dependencies = dependsOnMap.get(componentToInstall);
            if (currentlyInstalled.containsAll(dependencies)) {
                return true;
            }
            return false;
        }
    }

    static class RemoveCommandHandler extends CommandHandler {

        @Override
        public void handleCommand(String input, List<String> output) {
            String[] arr = input.split("\\s+");
            Set<String> dependedOn = dependedOnMap.get(arr[1]);
            if (!currentlyInstalled.contains(arr[1])) {
                //System.out.println(arr[1]+" is not installed");
                output.add(arr[1]+" is not installed");
                return;
            }
            if (dependedOn != null) {
                for (String depended : dependedOn) {
                    boolean canNotBeDeleted = recursiveCheck(depended);
                    if (canNotBeDeleted) {
                        //System.out.println(arr[1] + " is still needed");
                        output.add(arr[1] + " is still needed");
                        return;
                    }
                }
            }
            if (!dependedOnMap.containsKey(arr[1]) || allDependeciesRemoved(arr[1])) {
                //System.out.println("Removing "+arr[1]);
                output.add("Removing "+arr[1]);
                currentlyInstalled.remove(arr[1]);
                //return;
            }
            if (dependsOnMap.containsKey(arr[1])) {
                for (String dependsOnComp : dependsOnMap.get(arr[1])) {
                    if (canBeDeleted(dependsOnComp)) {
                        currentlyInstalled.remove(dependsOnComp);
                        //System.out.println("Removing "+dependsOnComp);
                        output.add("Removing "+dependsOnComp);
                    }
                }
            }
        }

        private boolean canBeDeleted(String component) {
            // dependencies are still installed
            if (dependedOnMap.containsKey(component) && !allDependeciesRemoved(component)) {
                return false;
            }
            if (!dependedOnMap.containsKey(component) || allDependeciesRemoved(component)) {
                return true;
                //return;
            }
            if (dependsOnMap.containsKey(component)) {
                for (String dependedOn : dependsOnMap.get(component)) {
                    if (!canBeDeleted(dependedOn)) {
                        return false;
                    }
                }
            }
            return false;
        }

        private boolean allDependeciesRemoved(String component) {
            Set<String> dependedOn = dependedOnMap.get(component);
            for (String current: dependedOn) {
                if (currentlyInstalled.contains(current)) {
                    return false;
                }
            }
            return true;
        }

        private boolean recursiveCheck(String componentToRemove) {
            if (currentlyInstalled.contains(componentToRemove)) {
                return true;
            }
            if (!dependedOnMap.containsKey(componentToRemove)) {
                return false;
            }
            for (String depends : dependedOnMap.get(componentToRemove)) {
                if (recursiveCheck(depends)) {
                    return true;
                }
            }
            return false;
        }
    }

    static class ListCommandHandler extends CommandHandler {

        @Override
        public void handleCommand(String input, List<String> output) {
            for (String str : currentlyInstalled) {
                //System.out.println(str);
                output.add(str);
            }
        }
    }

    static class EndCommadHandler extends CommandHandler {

        @Override
        public void handleCommand(String input, List<String> output) {
        }
    }

    static void doIt(String[] input) {
        List<String> result = new LinkedList<>();
        for (String inp : input) {
            String[] arr = inp.split("\\s+");
            CommandHandler handler = CommandFactory.getCommandHandler(arr[0]);
            handler.handle(inp, result);
        }
        for (String str: result) {
            System.out.println(str);
        }

    }

    static void testA() {
         String[] input = {"DEPEND A B C",
                           "INSTALL B",
                           "INSTALL A",
                           "LIST",
                           "END"};
         List<String> result = new LinkedList<>();
        for (String inp : input) {
            String[] arr = inp.split("\\s+");
            CommandHandler handler = CommandFactory.getCommandHandler(arr[0]);
            handler.handle(inp, result);
        }
        assert (result.get(0).equals("DEPEND A B C"));

    }

    static void testB() {
        String[] input = {"DEPEND A B ",
                          "DEPEND B A",
                          "END"};
        List<String> result = new LinkedList<>();
        for (String inp : input) {
            String[] arr = inp.split("\\s+");
            CommandHandler handler = CommandFactory.getCommandHandler(arr[0]);
            handler.handle(inp, result);
        }
        System.out.println(result);
        assert (result.get(2).equals("A depends on B, ignoring command"));
    }

    static void testC() {
        String[] input = {"DEPEND A B ",
                          "REMOVE C",
                          "END"};
        List<String> result = new LinkedList<>();
        for (String inp : input) {
            String[] arr = inp.split("\\s+");
            CommandHandler handler = CommandFactory.getCommandHandler(arr[0]);
            handler.handle(inp, result);
        }
        System.out.println(result);
        assert (result.get(2).equals("C is not installed"));
    }

    static void testD() {
        String[] input = {"LIST",
                          "END"};
        List<String> result = new LinkedList<>();
        for (String inp : input) {
            String[] arr = inp.split("\\s+");
            CommandHandler handler = CommandFactory.getCommandHandler(arr[0]);
            handler.handle(inp, result);
        }
        System.out.println(result);
        assert (result.get(0).equals("LIST"));
    }

    static void testE() {
        String[] input = {"DEPEND A B",
                          "DEPEND B C",
                          "INSTALL A",
                          "REMOVE C",
                          "END"};
        List<String> result = new LinkedList<>();
        for (String inp : input) {
            String[] arr = inp.split("\\s+");
            CommandHandler handler = CommandFactory.getCommandHandler(arr[0]);
            handler.handle(inp, result);
        }
        System.out.println(result);
        assert (result.get(3).equals("Installing C"));
        assert (result.get(7).equals("C is still needed"));
    }

    public static void main(String[] args) {
//        String[] input = {"DEPEND A B C",
//                          "INSTALL B",
//                          "INSTALL A",
//                          "LIST",
//                          "END"};
        String[] input = {
                "DEPEND TELNET TCPIP NETCARD",
                "DEPEND TCPIP NETCARD",
                "DEPEND NETCARD TCPIP",
                "DEPEND DNS TCPIP NETCARD",
                "DEPEND BROWSER TCPIP HTML",
                "INSTALL NETCARD",
                "INSTALL TELNET",
                "INSTALL foo",
                "REMOVE NETCARD",
                "INSTALL BROWSER",
                "INSTALL DNS",
                "LIST",
                "REMOVE TELNET",
                "REMOVE NETCARD",
                "REMOVE DNS",
                "REMOVE NETCARD",
                "INSTALL NETCARD",
                "REMOVE TCPIP",
                "REMOVE BROWSER",
                "REMOVE TCPIP",
                "LIST",
                "END"
        };
        doIt(input);
        //testE();
        int n = 12;
        List<List<String>> List = new LinkedList<>();

        int maxElement = n*n;
        int maxLength = (maxElement+"").length();
        System.out.println(maxLength);
        int maxGap = maxLength + 1;




    }
}

package net.redheademile.year2015;

import net.redheademile.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

public class Day7 {
    private static String[] getInput() {
        return Main.getInput("2015_7.txt").split("\n");
    }

    public static void partOne() {
        String[] instructions = getInput();
        Map<String, Integer> variables = new HashMap<>();
        final AtomicReference<Consumer<String>> evaluate = new AtomicReference<>(null);

        Function<String, String> findInstructionAssigningVariable = variable -> {
            for (String instruction : instructions)
                if (instruction.replace("\r", "").endsWith("-> " + variable))
                    return instruction;
            return null;
        };

        Function<String, Integer> getOrComputeVariableValue = variable -> {
            try {
                return Integer.parseInt(variable);
            } catch (NumberFormatException ignore) {
                Integer value = variables.get(variable);
                if (value != null)
                    return value;

                evaluate.get().accept(findInstructionAssigningVariable.apply(variable));
                return variables.get(variable);
            }
        };

        evaluate.set(instruction -> {
            String[] detail = instruction.replace("\r", "").split(" ");

            String outputVariable = detail[detail.length  - 1];
            if (detail.length == 3) {
                int value = getOrComputeVariableValue.apply(detail[0]);
                variables.put(outputVariable, value);
            }
            else if (detail[0].endsWith("NOT")) {
                int value = getOrComputeVariableValue.apply(detail[1]);
                variables.put(outputVariable, ~value);
            }
            else if (detail[1].equals("AND")) {
                int firstValue = getOrComputeVariableValue.apply(detail[0]);
                int secondValue = getOrComputeVariableValue.apply(detail[2]);

                variables.put(outputVariable, (firstValue & secondValue));
            }
            else if (detail[1].equals("OR")) {
                int firstValue = getOrComputeVariableValue.apply(detail[0]);
                int secondValue = getOrComputeVariableValue.apply(detail[2]);

                variables.put(outputVariable, (firstValue | secondValue));
            }
            else if (detail[1].equals("RSHIFT")) {
                int firstValue = getOrComputeVariableValue.apply(detail[0]);
                int offset = getOrComputeVariableValue.apply(detail[2]);

                variables.put(outputVariable, (firstValue >> offset));
            }
            else if (detail[1].equals("LSHIFT")) {
                int firstValue = getOrComputeVariableValue.apply(detail[0]);
                int offset = getOrComputeVariableValue.apply(detail[2]);

                variables.put(outputVariable, (firstValue << offset));
            }
        });

        evaluate.get().accept(findInstructionAssigningVariable.apply("a"));

        System.out.println("[Part 1] Value of `a` at the end: " + Short.toUnsignedInt(variables.get("a").shortValue()));
    }

    public static void partTwo() {
        String[] instructions = getInput();
        Map<String, Integer> variables = new HashMap<>();
        final AtomicReference<Consumer<String>> evaluate = new AtomicReference<>(null);

        Function<String, String> findInstructionAssigningVariable = variable -> {
            for (String instruction : instructions)
                if (instruction.replace("\r", "").endsWith("-> " + variable))
                    return instruction;
            return null;
        };

        Function<String, Integer> getOrComputeVariableValue = variable -> {
            try {
                return Integer.parseInt(variable);
            } catch (NumberFormatException ignore) {
                Integer value = variables.get(variable);
                if (value != null)
                    return value;

                evaluate.get().accept(findInstructionAssigningVariable.apply(variable));
                return variables.get(variable);
            }
        };

        evaluate.set(instruction -> {
            String[] detail = instruction.replace("\r", "").split(" ");

            String outputVariable = detail[detail.length  - 1];
            if (detail.length == 3) {
                int value = getOrComputeVariableValue.apply(detail[0]);
                variables.put(outputVariable, value);
            }
            else if (detail[0].endsWith("NOT")) {
                int value = getOrComputeVariableValue.apply(detail[1]);
                variables.put(outputVariable, ~value);
            }
            else if (detail[1].equals("AND")) {
                int firstValue = getOrComputeVariableValue.apply(detail[0]);
                int secondValue = getOrComputeVariableValue.apply(detail[2]);

                variables.put(outputVariable, firstValue & secondValue);
            }
            else if (detail[1].equals("OR")) {
                int firstValue = getOrComputeVariableValue.apply(detail[0]);
                int secondValue = getOrComputeVariableValue.apply(detail[2]);

                variables.put(outputVariable, firstValue | secondValue);
            }
            else if (detail[1].equals("RSHIFT")) {
                int firstValue = getOrComputeVariableValue.apply(detail[0]);
                int offset = getOrComputeVariableValue.apply(detail[2]);

                variables.put(outputVariable, firstValue >> offset);
            }
            else if (detail[1].equals("LSHIFT")) {
                int firstValue = getOrComputeVariableValue.apply(detail[0]);
                int offset = getOrComputeVariableValue.apply(detail[2]);

                variables.put(outputVariable, firstValue << offset);
            }
        });

        evaluate.get().accept(findInstructionAssigningVariable.apply("a"));

        Integer aValue = variables.get("a");
        variables.clear();
        variables.put("b", aValue);

        evaluate.get().accept(findInstructionAssigningVariable.apply("a"));
        
        System.out.println("[Part 1] Value of `a` at the end: " + Short.toUnsignedInt(variables.get("a").shortValue()));
    }
}

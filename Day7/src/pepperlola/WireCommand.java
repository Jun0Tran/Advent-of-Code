package pepperlola;

import java.util.Arrays;

public class WireCommand {
    private Operation operation;
    private String target;
    private String name1;
    private String name2;
    private int shiftAmount;

    public WireCommand(Operation operation, String name1, String name2, String target) {
        this.operation = operation;
        this.name1 = name1;
        this.name2 = name2;
        this.target = target;
        this.shiftAmount = 0;
    }

    public WireCommand(Operation operation, String target, String name1, String name2, int shiftAmount) {
        this.operation = operation;
        this.target = target;
        this.name1 = name1;
        this.name2 = name2;
        this.shiftAmount = shiftAmount;
    }

    public static WireCommand parse(String target, String command) {
        String[] split = command.split(" ");
        String name1 = "";
        String name2 = "";
        String operation = "";
        int shiftAmount = 0;

        if (split[0].equals("NOT")) {
            operation = "NOT";
            name1 = split[1];
        } else if (split[1].contains("SHIFT")) {
            name1 = split[0];
            operation = split[1];
            shiftAmount = Integer.parseInt(split[2]);
        } else if (split[1].equals("OR") || split[1].equals("AND")) {
            name1 = split[0];
            operation = split[1];
            name2 = split[2];
        } else {
            name1 = split[0];
            operation = "SET";
        }

        return new WireCommand(Operation.fromString(operation), name1, name2, target, shiftAmount);
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public int getShiftAmount() {
        return shiftAmount;
    }

    public void setShiftAmount(int shiftAmount) {
        this.shiftAmount = shiftAmount;
    }

    enum Operation {
        AND,
        OR,
        NOT,
        LSHIFT,
        RSHIFT,
        SET;

        static Operation fromString(String operation) {
            switch (operation.toUpperCase()) {
                case "AND":
                    return AND;
                case "OR":
                    return OR;
                case "NOT":
                    return NOT;
                case "LSHIFT":
                    return LSHIFT;
                case "RSHIFT":
                    return RSHIFT;
                case "SET":
                    return SET;
                default:
                    return null;
            }
        }
    }
}

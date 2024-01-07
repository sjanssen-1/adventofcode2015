import java.util.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class Puzzle {
  public static char findSignalForWire(List<String> instructions, String wire) {
    final Map<String, Character> wires = new HashMap<>();
    var gates = instructions.stream().map(Gate::parse).toList();

    while (!gates.stream().allMatch(gate -> gate.isExecuted() || !gate.canExecute(wires))) {
      for (Gate gate : gates) {
        if (gate.canExecute(wires)) {
          var result = gate.execute(wires);
          gate.setExecuted(true);
          wires.put(result.wire(), result.result());
        }
      }
    }

    return wires.get(wire);
  }

  static boolean isNotNumber(String s) {
    try {
      Integer.parseInt(s);
      return false;
    } catch (NumberFormatException e) {
      return true;
    }
  }

  @SuperBuilder
  @Getter
  @Setter
  abstract static class Gate {

    private boolean executed;
    private List<String> requiredWires;
    private String leftArgument;
    private String rightArgument;
    private String target;

    static Gate parse(String instruction) {
      var split = instruction.split(" -> ");
      var target = split[1];
      var expr = split[0].split(" ");

      if (split[0].contains("AND")) {
        var left = expr[0];
        var right = expr[2];
        var requiredWires = new ArrayList<String>();

        if (isNotNumber(left)) {
          requiredWires.add(left);
        }
        if (isNotNumber(right)) {
          requiredWires.add(right);
        }

        return And.builder()
            .target(target)
            .requiredWires(requiredWires)
            .leftArgument(left)
            .rightArgument(right)
            .build();
      } else if (split[0].contains("OR")) {
        var left = expr[0];
        var right = expr[2];
        var requiredWires = new ArrayList<String>();

        if (isNotNumber(left)) {
          requiredWires.add(left);
        }
        if (isNotNumber(right)) {
          requiredWires.add(right);
        }

        return Or.builder()
            .target(target)
            .requiredWires(requiredWires)
            .leftArgument(left)
            .rightArgument(right)
            .build();
      } else if (split[0].contains("RSHIFT")) {
        var left = expr[0];
        var right = expr[2];
        var requiredWires = new ArrayList<String>();

        if (isNotNumber(left)) {
          requiredWires.add(left);
        }
        if (isNotNumber(right)) {
          requiredWires.add(right);
        }

        return RShift.builder()
            .target(target)
            .requiredWires(requiredWires)
            .leftArgument(left)
            .rightArgument(right)
            .build();
      } else if (split[0].contains("LSHIFT")) {
        var left = expr[0];
        var right = expr[2];
        var requiredWires = new ArrayList<String>();

        if (isNotNumber(left)) {
          requiredWires.add(left);
        }
        if (isNotNumber(right)) {
          requiredWires.add(right);
        }

        return LShift.builder()
            .target(target)
            .requiredWires(requiredWires)
            .leftArgument(left)
            .rightArgument(right)
            .build();
      } else if (split[0].contains("NOT")) {
        var right = expr[1];
        var requiredWires = new ArrayList<String>();

        if (isNotNumber(right)) {
          requiredWires.add(right);
        }

        return Not.builder()
            .target(target)
            .requiredWires(requiredWires)
            .rightArgument(right)
            .build();
      } else {
        var left = expr[0];

        var requiredWires = new ArrayList<String>();
        if (isNotNumber(left)) {
          requiredWires.add(left);
        }

        return Assign.builder()
            .target(target)
            .leftArgument(left)
            .requiredWires(requiredWires)
            .build();
      }
    }

    GateResult execute(Map<String, Character> wires) {
      throw new RuntimeException();
    }

    boolean canExecute(Map<String, Character> wires) {
      return wires.keySet().containsAll(requiredWires);
    }

    char convertLeftArgument(Map<String, Character> wires) {
      try {
        return (char) Integer.parseInt(leftArgument);
      } catch (NumberFormatException e) {
        return wires.get(leftArgument);
      }
    }

    char convertRightArgument(Map<String, Character> wires) {
      try {
        return (char) Integer.parseInt(rightArgument);
      } catch (NumberFormatException e) {
        return wires.get(rightArgument);
      }
    }
  }

  @Builder
  record GateResult(char result, String wire) {}

  @SuperBuilder
  static class And extends Gate {
    @Override
    GateResult execute(Map<String, Character> wires) {
      var value1 = convertLeftArgument(wires);
      var value2 = convertRightArgument(wires);
      return GateResult.builder().result((char) (value1 & value2)).wire(this.getTarget()).build();
    }
  }

  @SuperBuilder
  static class Or extends Gate {
    @Override
    GateResult execute(Map<String, Character> wires) {
      var value1 = convertLeftArgument(wires);
      var value2 = convertRightArgument(wires);
      return GateResult.builder().result((char) (value1 | value2)).wire(this.getTarget()).build();
    }
  }

  @SuperBuilder
  static class RShift extends Gate {
    @Override
    GateResult execute(Map<String, Character> wires) {
      var value1 = convertLeftArgument(wires);
      var value2 = convertRightArgument(wires);
      return GateResult.builder().result((char) (value1 >> value2)).wire(this.getTarget()).build();
    }
  }

  @SuperBuilder
  static class LShift extends Gate {
    @Override
    GateResult execute(Map<String, Character> wires) {
      var value1 = convertLeftArgument(wires);
      var value2 = convertRightArgument(wires);
      return GateResult.builder().result((char) (value1 << value2)).wire(this.getTarget()).build();
    }
  }

  @SuperBuilder
  static class Not extends Gate {
    @Override
    GateResult execute(Map<String, Character> wires) {
      var value = convertRightArgument(wires);
      return GateResult.builder().result((char) (~value)).wire(this.getTarget()).build();
    }
  }

  @SuperBuilder
  static class Assign extends Gate {
    @Override
    GateResult execute(Map<String, Character> wires) {
      var value = convertLeftArgument(wires);
      return GateResult.builder().result(value).wire(this.getTarget()).build();
    }
  }
}

import java.util.Arrays;
import java.util.List;
import lombok.Builder;
import lombok.Setter;

public class Puzzle {
  public static int findEnabledLights(List<String> startingGrid, int steps) {
    final var grid = parseGrid(startingGrid);

    Light[][] gridSnapshot;
    for (int i = 0; i < steps; i++) {
      gridSnapshot = takeGridSnapshot(grid);

      for (int y = 0; y < grid.length; y++) {
        for (int x = 0; x < grid[0].length; x++) {
          if (shouldTransition(gridSnapshot, x, y)) {
            grid[y][x].setOn(!gridSnapshot[y][x].isOn);
          }
        }
      }
    }

    return Arrays.stream(grid)
        .flatMap(Arrays::stream)
        .map(light -> light.isOn ? 1 : 0)
        .reduce(Integer::sum)
        .orElseThrow();
  }

  private static Light[][] parseGrid(List<String> startingGrid) {
    final var columns = startingGrid.size();
    final var rows = startingGrid.getFirst().length();

    final var grid = new Light[columns][rows];

    for (int y = 0; y < columns; y++) {
      for (int x = 0; x < rows; x++) {
        if (startingGrid.get(y).charAt(x) == '.') {
          grid[y][x] = Light.builder().isOn(false).build();
        } else {
          grid[y][x] = Light.builder().isOn(true).build();
        }
      }
    }
    return grid;
  }

  private static Light[][] takeGridSnapshot(Light[][] grid) {
    final var columns = grid.length;
    final var rows = grid[0].length;

    final var snapshot = new Light[columns][rows];

    for (int y = 0; y < columns; y++) {
      for (int x = 0; x < rows; x++) {
        snapshot[y][x] = Light.builder().isOn(grid[y][x].isOn).build();
      }
    }
    return snapshot;
  }

  private static boolean shouldTransition(Light[][] grid, int x, int y) {
    if (grid[y][x].isOn) {
      int onNeighbours = 0;
      for (int j = -1; j < 2; j++) {
        for (int i = -1; i < 2; i++) {
          int nx = x + i;
          int ny = y + j;
          if (nx < 0 || nx >= grid[0].length || ny < 0 || ny >= grid.length || (i == 0 && j == 0)) {
            continue;
          }

          if (grid[ny][nx].isOn) {
            onNeighbours++;
          }
        }
      }
      return onNeighbours < 2 || onNeighbours > 3;
    } else {
      int onNeighbours = 0;
      for (int j = -1; j < 2; j++) {
        for (int i = -1; i < 2; i++) {
          int nx = x + i;
          int ny = y + j;
          if (nx < 0 || nx >= grid[0].length || ny < 0 || ny >= grid.length || (i == 0 && j == 0)) {
            continue;
          }

          if (grid[ny][nx].isOn) {
            onNeighbours++;
          }
        }
      }
      return onNeighbours == 3;
    }
  }

  @Builder
  @Setter
  static class Light {
    private boolean isOn;
  }
}

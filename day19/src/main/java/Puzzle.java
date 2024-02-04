import java.util.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class Puzzle {
  public static int findDistinctMolecules(List<String> input) {
    final var rnrnffp = RedNosedReindeerNuclearFusionFissionPlant.parse(input);
    return rnrnffp.generateMolecules().size();
  }

  @Getter
  @Setter
  static class RedNosedReindeerNuclearFusionFissionPlant {
    private MultiValuedMap<String, String> replacements;
    private String molecule;

    public static RedNosedReindeerNuclearFusionFissionPlant parse(List<String> input) {
      final var rnrnffp = new RedNosedReindeerNuclearFusionFissionPlant();

      final var replacements = new ArrayListValuedHashMap<String, String>();
      var reachedMolecule = false;
      for (String s : input) {
        if (s.isEmpty()) {
          reachedMolecule = true;
          continue;
        }

        if (!reachedMolecule) {
          final var split = s.split(" => ");
          replacements.put(split[0], split[1]);
        } else {
          rnrnffp.setMolecule(s);
        }
      }
      rnrnffp.setReplacements(replacements);
      return rnrnffp;
    }

    public Set<String> generateMolecules() {
      final var generatedMolecules = new HashSet<String>();

      final var it = getReplacements().mapIterator();
      while (it.hasNext()) {
        it.next();
        System.out.println(it.getKey());
        System.out.println(it.getValue());

        var pre = "";
        var current = getMolecule();
        var matchIndex = getMolecule().indexOf(it.getKey());
        while (matchIndex != -1) {

          final var replacedSubstring = current.replaceFirst(it.getKey(), it.getValue());
          generatedMolecules.add(pre + replacedSubstring);

          if (matchIndex + 1 >= current.length()) {
            break;
          }

          pre += current.substring(0, matchIndex + 1);
          current = current.substring(matchIndex + 1);
          matchIndex = current.indexOf(it.getKey());
        }
      }

      return generatedMolecules;
    }
  }
}

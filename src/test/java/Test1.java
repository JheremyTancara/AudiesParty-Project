import static org.junit.jupiter.api.Assertions.assertEquals;

import main.AudiesParty;
import org.junit.jupiter.api.Test;


public class Test1 {

  @Test
  void testCase1() {
    AudiesParty audie = new AudiesParty("input1.txt");
    int x = 7, k = 2;
    String expectedGuests = "Cube Judy Kitt P X";
    String guests = audie.getNodesAboveWtString(x);
    assertEquals(expectedGuests, guests);

    String expectedGroups =
            """
            P Kitt Judy Cube
            X""";
    String groups = audie.getGroupsOf(k);
    assertEquals(expectedGroups, groups);

    String expectedStrongestRelation = "Group with strongest friendly relationship: P Kitt Judy Cube";
    String strongestRelation = audie.getSrg();
    assertEquals(expectedStrongestRelation, strongestRelation);

    String expectedWeakestRelation = "Group with least friendly relationship: P Kitt Judy Cube";
    String weakestRelation = audie.getWrg();
    assertEquals(expectedWeakestRelation, weakestRelation);
  }
}

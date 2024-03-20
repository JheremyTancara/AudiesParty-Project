import static org.junit.jupiter.api.Assertions.assertEquals;

import main.AudiesParty;
import org.junit.jupiter.api.Test;


public class Test1 {

  @Test
  void testCase1() {
    AudiesParty audie = new AudiesParty("input1.txt");
    int x = 7, k = 2;

    String guests = audie.getNodesAboveWtString(x);
    assertEquals("Cube Judy Kitt P X", guests);

    String expected = // Expected Result
            """
            P Kitt Judy Cube
            X""";
    String groups = audie.getGroupsOf(k);
    assertEquals(expected, groups);

    String strongestRelation = audie.calculateSrg();
    assertEquals("Group with strongest friendly relationship: P Kitt Judy Cube", strongestRelation);

    String weakestRelation = audie.calculateWrg();
    assertEquals("Group with least friendly relationship: P Kitt Judy Cube", weakestRelation);
  }
}

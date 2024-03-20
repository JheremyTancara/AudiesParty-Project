import static org.junit.jupiter.api.Assertions.assertEquals;

import main.AudiesParty;
import org.junit.jupiter.api.Test;

public class Test2 {

  @Test
  void testCase1() {
    AudiesParty audie = new AudiesParty("input2.txt");
    int x = 10, k = 4;

    String guests = audie.getNodesAboveWtString(x);
    assertEquals("A B C M N R X", guests);

    String expected =
            """
            B A
            R C M
            X
            N""";
    String groups = audie.getGroupsOf(k);
    assertEquals(expected, groups);

    String strRelation = audie.calculateSrg();
    assertEquals("Group with strongest friendly relationship: R C M", strRelation);

    String wkRelation = audie.calculateWrg();
    assertEquals("Group with least friendly relationship: B A", wkRelation);
  }

  @Test
  void testCase2() {
    AudiesParty audie = new AudiesParty("input2.txt");
    int x =10, k = 3;

    String guests = audie.getNodesAboveWtString(x);
    assertEquals("A B C M N R X", guests);

    String expected =
            """
            B A
            R C M N
            X""";
    String groups = audie.getGroupsOf(k);
    assertEquals(expected, groups);

    String strRelation = audie.calculateSrg();
    assertEquals("Group with strongest friendly relationship: R C M N", strRelation);

    String wkRelation = audie.calculateWrg();
    assertEquals("Group with least friendly relationship: R C M N", wkRelation);
  }

  @Test
  void testCase3() {
    AudiesParty audie = new AudiesParty("input2.txt");
    int x = 14, k = 3;
    
    String guests = audie.getNodesAboveWtString(x);
    assertEquals("C M R", guests);

    String expected =
            """
            R
            C
            M""";
    String groups = audie.getGroupsOf(k);
    assertEquals(expected, groups);

    String strRelation = audie.calculateSrg();
    assertEquals("Group with strongest friendly relationship: None", strRelation);

    String wkRelation = audie.calculateWrg();
    assertEquals("Group with least friendly relationship: None", wkRelation);
  }
}

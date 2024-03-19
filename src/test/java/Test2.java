import static org.junit.jupiter.api.Assertions.assertEquals;

import main.AudiesParty;
import org.junit.jupiter.api.Test;

public class Test2 {

  @Test
  void testCase1() {
    AudiesParty audie = new AudiesParty("input2.txt");
    int x = 10, k = 4;
    String expectedGuests = "A B C M N R X";
    String guests = audie.getNodesAboveWtString(x);
    assertEquals(expectedGuests, guests);

    String expectedGroups =
            """
            B A
            R C M
            X
            N""";
    String groups = audie.getGroupsOf(k);
    assertEquals(expectedGroups, groups);

    String expectedStrongestRelation = "Group with strongest friendly relationship: R C M";
    String strongestRelation = audie.getSrg();
    assertEquals(expectedStrongestRelation, strongestRelation);

    String expectedWeakestRelation = "Group with least friendly relationship: B A";
    String weakestRelation = audie.getWrg();
    assertEquals(expectedWeakestRelation, weakestRelation);
  }

  @Test
  void testCase2() {
    AudiesParty audie = new AudiesParty("input2.txt");
    int x =10, k = 3;
    String expectedGuests = "A B C M N R X";
    String guests = audie.getNodesAboveWtString(x);
    assertEquals(expectedGuests, guests);

    String expectedGroups =
            """
            B A
            R C M N
            X""";
    String groups = audie.getGroupsOf(k);
    assertEquals(expectedGroups, groups);

    String expectedStrongestRelation = "Group with strongest friendly relationship: R C M N";
    String strongestRelation = audie.getSrg();
    assertEquals(expectedStrongestRelation, strongestRelation);

    String expectedWeakestRelation = "Group with least friendly relationship: R C M N";
    String weakestRelation = audie.getWrg();
    assertEquals(expectedWeakestRelation, weakestRelation);
  }

  @Test
  void testCase3() {
    AudiesParty audie = new AudiesParty("input2.txt");
    int x = 14, k = 3;
    String expectedGuests = "C M R";
    String guests = audie.getNodesAboveWtString(x);
    assertEquals(expectedGuests, guests);

    String expectedGroups =
            """
            R
            C
            M""";
    String groups = audie.getGroupsOf(k);
    assertEquals(expectedGroups, groups);

    String expectedStrongestRelation = "Group with strongest friendly relationship: None";
    String strongestRelation = audie.getSrg();
    assertEquals(expectedStrongestRelation, strongestRelation);

    String expectedWeakestRelation = "Group with least friendly relationship: None";
    String weakestRelation = audie.getWrg();
    assertEquals(expectedWeakestRelation, weakestRelation);
  }
}

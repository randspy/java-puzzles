package test.findcommonancestor;

import main.findcommonancestor.FindCommonAncestor;
import main.findcommonancestor.MyFindCommonAncestor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class MyFindCommonAncestorTest {

    private FindCommonAncestor findCommonAncestor;

    private void assertFind(String result,
                            String[] commits,
                            String[][] parents,
                            String commitHash1,
                            String commitHash2) {
        assertEquals(result, findCommonAncestor.findCommonAncestor(commits, parents, commitHash1, commitHash2));
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp(){
        findCommonAncestor = new MyFindCommonAncestor();
    }

    @Test
    public void whenInputsIsNullThrowIllegalArgumentException(){
        exception.expect(IllegalArgumentException.class);
        findCommonAncestor.findCommonAncestor(null, null, null, null);
    }

    @Test
    public void whenArraysAreEmptyThrowIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        findCommonAncestor.findCommonAncestor(new String[]{}, new String[][]{}, "", "");
    }

    @Test
    public void whenParentHashesSmallerAreShorterThanCommitHashesThrowIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        findCommonAncestor.findCommonAncestor(new String[]{"D", "C", "B", "A"}, new String[][]{{"C"}, {"A"}, null}, "D", "B");;
    }

    @Test
    public void whenParentsAreEmptyThrowIllegalArgumentException() {
        exception.expect(IllegalArgumentException.class);
        findCommonAncestor.findCommonAncestor(new String[]{"A", "B"}, new String[][]{{""}, {""}}, "A", "B");
    }

    @Test
    public void whenCommonAncestorNotFoundReturnsEmptyString(){
        assertFind("", new String[]{"D", "C", "B", "A"}, new String[][]{{"C"}, {"A"}, null, null}, "D", "B");
    }

    @Test
    public void findCommonAncestor(){
        assertFind("A", new String[]{"A"}, new String[][]{{"A"}, null}, "A", "A");
        assertFind("A", new String[]{"B", "A"}, new String[][]{{"A"}, null}, "A", "B");
        assertFind("A", new String[]{"C", "B", "A"}, new String[][]{{"A"}, {"A"}, null}, "C", "B");
        assertFind("A", new String[]{"C", "D", "B", "A"}, new String[][]{{"A"}, {"B"}, {"A"}, null}, "C", "D");
        assertFind("A", new String[]{"C", "D", "B", "A", "Q"}, new String[][]{{"A"}, {"B"}, {"A"}, {"Q"}, null}, "C", "D");
        assertFind("B", new String[]{"G", "F", "E", "D", "C", "B", "A"}, new String[][]{{"F","D"},{"E"}, {"B"}, {"C"}, {"B"}, {"A"}, null}, "D", "F");
        assertFind("A", new String[]{"G", "F", "E", "D", "C", "B", "A"}, new String[][]{{"D", "F"}, {"E"}, {"A"}, {"B", "C"}, {"A"}, {"A"}, null}, "D", "F");
    }


}
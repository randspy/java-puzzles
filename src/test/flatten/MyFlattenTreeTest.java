package test.flatten;

import main.flatten.FlattenTree;
import main.flatten.MyFlattenTree;
import main.flatten.Tree;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyFlattenTreeTest {

    private List<Integer> listOf(Integer... numbers) {
        return  new ArrayList<Integer>(Arrays.asList(numbers));
    }

    private List<Integer> flatten(Tree<Integer> tree) {
        return flattenTree.flattenInOrder(tree);
    }

    private Tree<Integer> leaf(Integer value) {
        return new Tree.Leaf<Integer>(value);
    }

    private Tree<Integer> node(Tree<Integer> left, Tree<Integer> middle, Tree<Integer> right) {
        return new Tree.Node<Integer>(left, middle, right);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private FlattenTree<Integer> flattenTree;

    @Before
    public void setUp(){
        flattenTree = new MyFlattenTree<Integer>();
    }

    @Test
    public void whenTreeIsNullThrowIllegalArgumentException(){
        exception.expect(IllegalArgumentException.class);
        flattenTree.flattenInOrder(null);
    }

    @Test
    public void flattenTree(){
        assertEquals(listOf(1), flatten(leaf(1)));
        assertEquals(listOf(1,2,3), flatten(node(leaf(1), leaf(2), leaf(3))));
        assertEquals(listOf(1,2,3), flatten(node(leaf(1), leaf(2), leaf(3))));
        assertEquals(listOf(1,2,3), flatten(node(leaf(1), leaf(2), leaf(3))));
        assertEquals(listOf(1,5,4,9,6), flatten(node(leaf(1), node(leaf(5), leaf(4), leaf(9)), leaf(6))));
        assertEquals(listOf(5,9), flatten(node(null, node(leaf(5), null, leaf(9)), null)));
    }
}
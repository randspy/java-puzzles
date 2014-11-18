package main.flatten;

import java.util.ArrayList;
import java.util.List;

class MyFunction<P, R> implements Function<P, R> {
    @Override
    public R apply(P p) {
        return (R)p;
    }
}

public class MyFlattenTree<T> implements FlattenTree<T> {
    @Override
    public List<T> flattenInOrder(Tree<T> tree) {
        if (tree == null)
        {
            throw new IllegalArgumentException();
        }
        return flattenRecursively(tree);
    }

    private List<T> flattenRecursively(Tree<T> tree) {

        List<T> flattenTree = new ArrayList<T>();
        if (tree.get().isLeft())
        {
            flattenTree.add(tree.get().ifLeft(new MyFunction<T, T>()));
        }
        else
        {
            Triple<Tree<T>> node = (Triple<Tree<T>>) tree.get().ifRight(new MyFunction<Triple<Tree<T>>, T>());
            if(node.left() != null) {
                flattenTree.addAll(flattenRecursively(node.left()));
            }
            if(node.middle() != null) {
                flattenTree.addAll(flattenRecursively(node.middle()));
            }
            if(node.right() != null) {
                flattenTree.addAll(flattenRecursively(node.right()));
            }
        }

        return flattenTree;
    }
}

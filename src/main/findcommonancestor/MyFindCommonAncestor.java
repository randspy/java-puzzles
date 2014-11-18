package main.findcommonancestor;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MyFindCommonAncestor implements FindCommonAncestor {
    private Map<String, Integer> changeSetsToIndex;
    private String[] commitHashes;
    private String[][] parentHashes;
    private String commitHash1;
    private String commitHash2;

    /*
     * In case of invalid input throws IllegalArgumentException
     */
    @Override
    public String findCommonAncestor(String[] commitHashes, String[][] parentHashes,
                                     String commitHash1, String commitHash2) {

        init(commitHashes, parentHashes, commitHash1, commitHash2);
        throwIfInvalidInput();
        return commitHash1 == commitHash2 ?
                commitHash1 : hashesNotEqual();
    }

    private void init(String[] commitHashes, String[][] parentHashes, String commitHash1, String commitHash2) {
        this.commitHashes = commitHashes;
        this.parentHashes = parentHashes;
        this.commitHash1 = commitHash1;
        this.commitHash2 = commitHash2;
    }

    private void throwIfInvalidInput() {
        if(commitHashes == null || parentHashes == null || commitHash1 == null || commitHash2 == null) {
            throw new IllegalArgumentException();
        }

        if(commitHash1.isEmpty() || commitHash2.isEmpty()) {
            throw new IllegalArgumentException();
        }

        for(String hash: commitHashes) {
            if (hash == null || hash == "") {
                throw new IllegalArgumentException();
            }
        }

        for(String[] hashGroup: parentHashes) {
            if (hashGroup != null) {
                for (String hash : hashGroup) {
                    if (hash == null || hash == "") {
                        throw new IllegalArgumentException();
                    }
                }
            }
        }

        if (commitHashes.length > parentHashes.length)
        {
            throw new IllegalArgumentException();
        }

    }

    private String hashesNotEqual() {
        mapChangesetsToIndexesInParentHashes();

        Stack<String> hash1Stack = changesetsAncestorsToStack(commitHash1);
        Stack<String> hash2Stack = changesetsAncestorsToStack(commitHash2);

        return commonAncestor(hash1Stack, hash2Stack);
    }

    private void mapChangesetsToIndexesInParentHashes() {
        changeSetsToIndex = new HashMap<String, Integer>();
        for(int index = 0; index < commitHashes.length; index++) {
            changeSetsToIndex.put(commitHashes[index], index);
        }
    }

    private Stack<String> changesetsAncestorsToStack(String commitHash) {
        Stack<String> stackHash = new Stack<String>();

        String[] currentChangeset = {commitHash};
        while (currentChangeset != null)
        {
            if(currentChangeset.length > 1) {
                stackHash = stackWhenManyParents(stackHash, currentChangeset);
                break;
            }
            else {
                stackHash.push(currentChangeset[0]);
                int index = changeSetsToIndex.get(currentChangeset[0]);
                currentChangeset = parentHashes[index];
            }
        }

        return stackHash;
    }

    private Stack<String> stackWhenManyParents(Stack<String> stackHash, String[] currentChangeset) {
        for(int idx = 0; idx < currentChangeset.length; idx++) {
            Stack<String> strings = changesetsAncestorsToStack(currentChangeset[idx]);
            for(String str: strings) {
                stackHash.push(str);
            }
        }
        return stackHash;
    }

    private String commonAncestor(Stack<String> hash1Stack, Stack<String> hash2Stack) {
        String commonAncestor = "";

        while (true){
            if(hash1Stack.size() == 1 && hash2Stack.size() == 1) {
                break;
            }

            if(hash1Stack.peek() == hash2Stack.peek()) {
                commonAncestor = hash1Stack.peek();
            }

            if(hash1Stack.size() > 1) {
                hash1Stack.pop();
            }

            if(hash2Stack.size() > 1){
                hash2Stack.pop();
            }
        }
        return commonAncestor;
    }
}

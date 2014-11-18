package test.iteration;

import main.iteration.Function2;
import main.iteration.Function2Impl;
import main.iteration.MyFolder;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class MyFolderTest {

    private String generateAssertData(int number, String content, String end) {
        String result = "";

        for(int idx = 0; idx < number; idx++)
        {
            result += content;
        }

        return result + end;
    }

    private Queue<String> generateQuery(int number, String content) {
        Queue<String> queue = new LinkedList<String>();

        for (int idx= 0; idx < number; idx++)
        {
            queue.add(content);
        }

        return queue;
    }

    @Test
    public void stackDoesNotOverflowWhenExecuted(){
        final int numberOfQueries = 10000;
        final String content = "c";
        final String end = "End";

        Queue<String> queue = generateQuery(numberOfQueries, "c");

        MyFolder<String, String> myFolder = new MyFolder<String, String>();

        Function2<String, String, String> function2 = new Function2Impl<String, String, String>();

        assertEquals(generateAssertData(numberOfQueries,content, end),
                myFolder.fold(end, queue, function2));
    }
}
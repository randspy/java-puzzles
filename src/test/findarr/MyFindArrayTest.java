package test.findarr;

import main.findarr.MyFindArray;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyFindArrayTest {

    public static final int ERROR_CODE = -1;
    private MyFindArray myFindArray;

    private void assertFindArray(int result, int[] array, int[] subArray) {
        assertEquals(result, myFindArray.findArray(array, subArray));
    }

    private int[] intArrayOf(int... i) {
        return i;
    }

    @Before
    public void setUp() throws Exception {
        myFindArray = new MyFindArray();
    }

    @Test
    public void findIfSecondArrayIsSubArrayOfTheFirstOne(){

        assertFindArray(ERROR_CODE, null, null);
        assertFindArray(ERROR_CODE, intArrayOf(1), null);
        assertFindArray(ERROR_CODE, null, intArrayOf(1));
        assertFindArray(ERROR_CODE, intArrayOf(), intArrayOf());
        assertFindArray(ERROR_CODE, intArrayOf(1), intArrayOf());
        assertFindArray(ERROR_CODE, intArrayOf(1), intArrayOf(2));
        assertFindArray(0, intArrayOf(1), intArrayOf(1));
        assertFindArray(1, intArrayOf(1,2), intArrayOf(2));
        assertFindArray(2, intArrayOf(1,2,3), intArrayOf(3));
        assertFindArray(1, intArrayOf(1,2,3), intArrayOf(2,3));
        assertFindArray(ERROR_CODE, intArrayOf(1,2,3,4), intArrayOf(2,4));
        assertFindArray(2, intArrayOf(1,2,3,4,5), intArrayOf(3,4,5));
        assertFindArray(ERROR_CODE, intArrayOf(4,9,3,7,2,8), intArrayOf(3,7,8));
        assertFindArray(2, intArrayOf(4,9,3,7,8), intArrayOf(3,7));
        assertFindArray(0, intArrayOf(1,3,5), intArrayOf(1));
        assertFindArray(ERROR_CODE, intArrayOf(7,8,9), intArrayOf(8,9,10));
        assertFindArray(ERROR_CODE, intArrayOf(8,9), intArrayOf(8,9,10));

    }
}
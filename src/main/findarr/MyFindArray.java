package main.findarr;


/*
 * This algorithm does not implements the fastest possible version.
 * It was developed based on concept presented in
 * https://cleancoders.com/episode/clean-code-episode-24-p1/show by Uncle Bob
 */
public class MyFindArray implements FindArray {

    private static final int SUB_ARRAY_NOT_FOUND_IN_ARRAY = -1;
    private int[] array;
    private int[] candidateForSubArray;

    @Override
    public int findArray(int[] array, int[] subArray) {
        init(array, subArray);

        if (areArraysNotValid()) return SUB_ARRAY_NOT_FOUND_IN_ARRAY;

        for (int arrayIdx = 0; arrayIdx < array.length; arrayIdx++) {

            if(isSubArrayBiggerThanArrayStartingFromIndex(arrayIdx)){
                return SUB_ARRAY_NOT_FOUND_IN_ARRAY;
            }
            else if (isSubArrayInArrayStaringFrom(arrayIdx)) {
                return arrayIdx;
            }
        }

        return SUB_ARRAY_NOT_FOUND_IN_ARRAY;
    }

    private void init(int[] array, int[] subArray) {
        this.array = array;
        this.candidateForSubArray = subArray;
    }

    private boolean areArraysNotValid() {
        return  array == null || array.length == 0 ||
                candidateForSubArray == null || candidateForSubArray.length == 0;
    }

    private boolean isSubArrayBiggerThanArrayStartingFromIndex(int arrayIdx) {
        return candidateForSubArray.length > array.length - arrayIdx;
    }

    private boolean isSubArrayInArrayStaringFrom(int arrayIdx) {

        for (int subArrayIdx = 0; subArrayIdx < candidateForSubArray.length; subArrayIdx++) {

            if (array[arrayIdx] != candidateForSubArray[subArrayIdx]) {
                return false;
            }
            ++arrayIdx;
        }
        return true;
    }
}

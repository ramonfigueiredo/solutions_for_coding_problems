class PalindromePermutationUtils {
    /* Map each character to a number. a -> 0, b -> 1, c -> 2, etc.
    * This is case insensitive. Non-letter characters map to -1. */
    int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if (a <= val && val <= z) {
            return val - a;
        }
        return -1;
   }
}

/**
 * This algorithm takes O(N) time, where N is the length of the string.
 */
class PalindromePermutationSolution1 extends PalindromePermutationUtils {
    boolean isPermutationOfPalindrome(String phrase) {
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }

    /* Check that no more than one character has an odd count. */
    boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int count : table) {
            if(count % 2 == 1) {
                if(foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }

    /* Count how many times each character appears. */
    int[] buildCharFrequencyTable(String phrase) {
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
            }
        }
        return table;
    }
}

/**
 * This algorithm takes O(N) time. However, we make some smaller incremental improvements.
 */
class PalindromePermutationSolution2 extends PalindromePermutationUtils {
    boolean isPermutationOfPalindrome(String phrase) {
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if(x != -1) {
                table[x]++;
                if (table[x] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;
    }
}

/**
 * Like the other solution this is O(N).
 */
class PalindromePermutationSolution3 extends PalindromePermutationUtils {
    boolean isPermutationOfPalindrome(String phrase) {
        int bitVector = createBitVector(phrase);
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

    /* Create a bit vector for the string. For each letter with value i, toggle the ith bit. */
    int createBitVector(String phrase) {
        int bitVector = 0;
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            bitVector = toogle(bitVector, x);
        }
        return bitVector;
    }

    /* Toggle the ith bit in the integer. */
    int toogle(int bitVector, int index) {
        if(index < 0) 
            return bitVector;

        int mask = 1 << index;
        if((bitVector & mask) == 0) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }

    /** Check that exatly one bit is set by subtraction one from the integer and
     * ANDing it with the original integer.
     */
    boolean checkExactlyOneBitSet(int bitVector) {
         return (bitVector & (bitVector - 1)) == 0;
    }
}

public class PalindromePermutation {
    public static void main(String[] args) {
        PalindromePermutationSolution1 palindromePermutationSolution1 = new PalindromePermutationSolution1();
        PalindromePermutationSolution2 palindromePermutationSolution2 = new PalindromePermutationSolution2();
        PalindromePermutationSolution3 palindromePermutationSolution3 = new PalindromePermutationSolution3();

        String phase = "Tact Coa";
        System.out.println("palindromePermutationSolution1.isPermutationOfPalindrome(" + phase + "): " + palindromePermutationSolution1.isPermutationOfPalindrome(phase));
        System.out.println("palindromePermutationSolution2.isPermutationOfPalindrome(" + phase + "): " + palindromePermutationSolution2.isPermutationOfPalindrome(phase));
        System.out.println("palindromePermutationSolution3.isPermutationOfPalindrome(" + phase + "): " + palindromePermutationSolution3.isPermutationOfPalindrome(phase));
    }
}
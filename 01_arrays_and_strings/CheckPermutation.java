/*
* Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
* */

/**
 * If two strings are permutations, then we know they have the same characters, but in different orders. 
 * Therefore, sorting the strings will put the characters from two permutations in the same order. 
 * We just need to compare the sorted versions of the strings.
 * 
 * Though this algorithm is not as optimal in some senses, it may be preferable in one sense: it's clean, simple, and easy to understand. 
 * In a practical sense, this may very well be a superior way to implement the problem. 
 * 
 * However, if efficiency is very important, we can implement it in a different way.
 */
class CheckPermutationSolution1 {
    String sort(String s) {
        char[] content = s.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);
    }

    boolean permutation(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
        return sort(s).equals(sort(t));
    }
}

/**
 * We can also use the definition of a permutation - two words with the same characters counts - to implement this algorithm. 
 * We create an array that operates somewhat like a hash table, mapping each character to its frequency. 
 * We increment thought the first string, then decrement though the second string. 
 * If the strings are permutations, then the array will be all zeros at the end.
 * 
 * We can terminate early if a value ever turns negative (once negative, the value will stay negative and therefore non-zero). 
 * If we don't terminate early, then the array must be all zeros. 
 * This is because the strings are the same lengths and we incremented the same number of times we decremented. 
 * The array cannot have any positive values if it doesn't have any negative values.
 * 
 * Note double check if the size of the character set. We assumed that the character set as ASCII.
 */
class CheckPermutationSolution2 {
    boolean permutation(String s, String t) {
        if (s.length() != t.length()) 
            return false; // Permutations must be same length

        int[] letters = new int[128]; // Assumption: ASCII
        for(int i=0; i < s.length(); i++) {
            letters[s.charAt(i)]++;
        }

        for(int i=0; i < t.length(); i++) {
            letters[t.charAt(i)]--;
            if(letters[t.charAt(i)] < 0) {
                return false;
            }
        }
        return true; // letters has no neg values, and therefore no pos values either
    }
}

public class CheckPermutation {
    public static void main(String[] args) {
        CheckPermutationSolution1 checkPermutationSolution1 = new CheckPermutationSolution1();
        System.out.println("Solution 1: Check Permutation (computerscience, sciencecomputer): " + checkPermutationSolution1.permutation("computerscience", "sciencecomputer"));
        System.out.println("Solution 1: Check Permutation (computerscience, scicomputer): " + checkPermutationSolution1.permutation("computerscience", "scicomputer"));

        CheckPermutationSolution2 checkPermutationSolution2 = new CheckPermutationSolution2();
        System.out.println("Solution 2: Check Permutation (canada, aaacdn): " + checkPermutationSolution1.permutation("canada", "aaacdn"));
        System.out.println("Solution 2: Check Permutation (canada, aaacdn2): " + checkPermutationSolution1.permutation("canada", "aaacdn2"));
    }
}
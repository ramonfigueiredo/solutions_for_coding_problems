/*
* Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
* */

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
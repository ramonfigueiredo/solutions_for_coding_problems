/*
* Is Unique: Implement an algorithm to determine if a string has all unique characters.
* What if you cannot use additional data structures.
* */

public class IsUnique {

    private static boolean isUniqueCharsSolution1(String str) {
        if (str.length() > 128)
            return false;

        boolean[] char_set = new boolean[128];

        for(int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    private static boolean isUniqueCharsSolution2(String str) {
        int checker = 0;

        for(int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("IS UNIQUE: SOLUTION 1");
        System.out.println("Is Unique Chars (Java): " + isUniqueCharsSolution1("Java"));
        System.out.println("Is Unique Chars (Python): " + isUniqueCharsSolution1("Python"));

        System.out.println("\n\nIS UNIQUE: SOLUTION 2");
        System.out.println("Is Unique Chars (Java): " + isUniqueCharsSolution2("Java"));
        System.out.println("Is Unique Chars (Python): " + isUniqueCharsSolution2("Python"));
    }
}
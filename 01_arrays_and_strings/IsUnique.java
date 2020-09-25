/*
* Is Unique: Implement an algorithm to determine if a string has all unique characters.
* What if you cannot use additional data structures.
* */
public class IsUnique {

    /**
    * The time complexity for this code is O(n), where n is the length of the string. The space complexity is O(1).
    * We can also say that the time complexity is O(1), since the for loop will never iterate through more than 128 characters.
    * If we don't assume that character set is fixed, we can express the complexity as O(c) space and O(min(c, n)) or O(c) time, 
    * where c is the size of the character set.
    */
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

    /**
     * This solution reduces our space usage by a factor of eight by using a bit vector.
     * We will assume, in the below code, that the string only uses the lowercase letters a through z. 
     * This will allow us to use just a single int.
     */
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

    /**
     * If we can't use additional data structures, we can do the following:
     * 
     * 1. Compare every character of the string to every other character of the string. This will take O(n^2) time and O(1) space.
     * 
     * 2. If we are allowed to modify the input string, we could sort the string in O(n log(n)) time and then 
     * linearly check the string for neighboring characters that are identical. Careful, thoughÇ many sorting algorithms take up extra space.
     * 
     * These solutions are not as optimal in some respects but might be better depending on the constraints of the problem.
     */
    public static void main(String[] args) {
        System.out.println("IS UNIQUE: SOLUTION 1");
        System.out.println("Is Unique Chars (Java): " + isUniqueCharsSolution1("Java"));
        System.out.println("Is Unique Chars (Python): " + isUniqueCharsSolution1("Python"));

        System.out.println("\n\nIS UNIQUE: SOLUTION 2");
        System.out.println("Is Unique Chars (Java): " + isUniqueCharsSolution2("Java"));
        System.out.println("Is Unique Chars (Python): " + isUniqueCharsSolution2("Python"));
    }
}
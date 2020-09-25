import java.util.Arrays;

/**
 * A common approach in the string manipulation problem is to edit the string starting from the end and working backward. This is useful because we have an extra buffer at the end, which allows us to change characters without worrying about what we're overwriting.
 *
 * We will use this approach in this problem. The algorithm employs a two-scan approach. In the first scan, we count the number of spaces. By tripling this number, we can compute how many extra characters we will have in the final string. In the second pass. which is done in reverse order, we actually edit the string. When we see a space, we replace it with %20. If there is no space, then we copy the original characters.
 *
 * This problem is implemented using character arrays because Java strings are immutable. If we used strings directly, the function would have to return a new copy of the string, but it would allow us to implement this in just one pass.
 */
public class URLify {

    // Assume string has sufficient free space at the end
    public static void replaceSpaces(char[] str, int trueLength) {
        int spaceCount = 0, index, i = 0;
        for (i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }

        index = trueLength + spaceCount * 2;
        if (trueLength < str.length) {
            str[trueLength] = '\0';
        }
            
        for (i = trueLength - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index = index - 3;
            } else {
                str[index - 1] = str[i];
                index--;
            }
        }
    }

    public static int findLastCharacter(char[] str) {
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] != ' ') {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String str = "A B C    ";
        char[] arr = str.toCharArray();
        int trueLength = findLastCharacter(arr) + 1;
        replaceSpaces(arr, trueLength);
        System.out.println("\"" + Arrays.toString(arr) + "\"");
    }
}

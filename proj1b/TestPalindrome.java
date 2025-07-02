import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } // Uncomment this class once you've created your Palindrome class.

    @Test
    public void testIsPalindrome() {
        Assert.assertFalse(palindrome.isPalindrome("cat"));
        Assert.assertFalse(palindrome.isPalindrome("Aa"));
        Assert.assertTrue(palindrome.isPalindrome("b"));
        Assert.assertTrue(palindrome.isPalindrome("bAb"));
        Assert.assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testIsPalindromeByOne() {
        CharacterComparator cc = new OffByOne();
        Assert.assertTrue(palindrome.isPalindrome("", cc));
        Assert.assertTrue(palindrome.isPalindrome("a", cc));
        Assert.assertTrue(palindrome.isPalindrome("b", cc));
        Assert.assertTrue(palindrome.isPalindrome("bAc", cc));
        Assert.assertTrue(palindrome.isPalindrome("aabb", cc));
        Assert.assertTrue(palindrome.isPalindrome("aaabb", cc));
        Assert.assertTrue(palindrome.isPalindrome("flake", cc));

        Assert.assertFalse(palindrome.isPalindrome("ac", cc));
        Assert.assertFalse(palindrome.isPalindrome("aba", cc));
    }
}

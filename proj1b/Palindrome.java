public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        if (word != null && word.equals("")) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != deque.removeLast()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word != null && word.equals("")) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        boolean odd = word.length() % 2 == 1;
        for (int i = 0; i < word.length(); i++) {
            if (odd && i == word.length() / 2) {
                deque.removeLast();
                continue;
            }
            if (!cc.equalChars(word.charAt(i), deque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> q = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            q.addLast(word.charAt(i));
        }
        return q;
    }
    private boolean isPalindrome(Deque<Character> wordInDeque) {
        while (wordInDeque.size() > 1) {
            return wordInDeque.removeFirst() == wordInDeque.removeLast() && isPalindrome(wordInDeque);
        }
        return true;
    }
    public boolean isPalindrome(String word) {
//        int i = 0;
//        int j = word.length() - 1;
//
//        while (i < word.length()) {
//            char a = word.charAt(i);
//            char b = word.charAt(j);
//            if (a != b) {
//                return false;
//            }
//            i++;
//            j--;
//        }
//
//        return true;
        return isPalindrome(wordToDeque(word));
    }

    private boolean isPalindrome(Deque<Character> wordInDeque, CharacterComparator cc) {
        while (wordInDeque.size() > 1) {
            return cc.equalChars(wordInDeque.removeFirst(), wordInDeque.removeLast()) && isPalindrome(wordInDeque, cc);
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }
}

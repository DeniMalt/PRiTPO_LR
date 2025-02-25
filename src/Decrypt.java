package src;

import java.util.*;


public class Decrypt {

    public static List<String> decrypt(List<String> testCase) {
        return decrypt(testCase, "the quick brown fox jumps over the lazy dog");
    }

    public static List<String> decrypt(List<String> testCase, String openText) {
        String[] openWords = openText.split("\\s+");

        for (String encrypted : testCase) {
            String[] encryptedWords = encrypted.split("\\s+");
            if (encryptedWords.length != openWords.length) {
                continue;
            }

            Map<Character, Character> mapping = new HashMap<>();
            Map<Character, Character> reverseMapping = new HashMap<>();
            boolean isValid = true;

            for (int i = 0; i < encryptedWords.length; i++) {
                String ew = encryptedWords[i];
                String ow = openWords[i];

                if (ew.length() != ow.length()) {
                    isValid = false;
                    break;
                }

                for (int j = 0; j < ew.length(); j++) {
                    char eChar = ew.charAt(j);
                    char oChar = ow.charAt(j);

                    if (mapping.containsKey(eChar)) {
                        if (mapping.get(eChar) != oChar) {
                            isValid = false;
                            break;
                        }
                    }
                    if (reverseMapping.containsKey(oChar)) {
                        if (reverseMapping.get(oChar) != eChar) {
                            isValid = false;
                            break;
                        }
                    }

                    mapping.put(eChar, oChar);
                    reverseMapping.put(oChar, eChar);
                }
                if (!isValid) break;
            }

            if (isValid) {
                List<String> decryptedText = new ArrayList<>();
                for (String line : testCase) {
                    StringBuilder sb = new StringBuilder();
                    for (char c : line.toCharArray()) {
                        sb.append(mapping.getOrDefault(c, c));
                    }
                    decryptedText.add(sb.toString());
                }
                return decryptedText;
            }
        }

        return Collections.singletonList("No solution");
    }
}

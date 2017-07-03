package org.wdd.handler;

import org.wdd.domain.Dictionary;

import java.util.List;
import java.util.stream.Collectors;

public class DictionaryHandler {

    /**
     * Combine words to form phrase and join it with space.
     *
     * @param wordsNumbers indexes of words from {@link Dictionary#WORDS}
     * @return phrase, based on words indexes.
     */
    public String generatePhrase(List<Integer> wordsNumbers) {
        validateIndexList(wordsNumbers);

        List<String> words = wordsNumbers.stream()
                .map(index -> Dictionary.WORDS[index])
                .collect(Collectors.toList());
        return String.join(" ", words);
    }

    private void validateIndexList(List<Integer> wordsIndexes) {
        if (wordsIndexes == null || wordsIndexes.isEmpty()) {
            throw new IllegalArgumentException("Words indexes can't be empty");
        }
    }
}

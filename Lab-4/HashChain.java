import java.util.*;

public class Main {
    public static void main(String[] args) {
        int m = 13;

        List<String> words = Arrays.asList("як", "добре", "вчишся", "то", "легко", "працюється", "а", "лінуєшся", "бідується");

        Map<Character, Integer> letterPositions = new HashMap<>();
        String alphabet = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
        for (int i = 0; i < alphabet.length(); i++) {
            letterPositions.put(alphabet.charAt(i), i + 1);
        }

        //Ініціалізація хеш-таблиці з ланцюжками
        List<List<String>> hashTable = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            hashTable.add(new ArrayList<>());
        }

        //Обробка кожного слова та заповнення таблиці
        for (String word : words) {
            int sumOfPositions = 0;
            String lowerWord = word.toLowerCase();

            //Обчислення суми позицій букв у слові
            for (int i = 0; i < lowerWord.length(); i++) {
                char c = lowerWord.charAt(i);
                sumOfPositions += letterPositions.getOrDefault(c, 0);
            }

            //Хеш-функція методом ділення
            int address = sumOfPositions % m;

            //Додавання слова до відповідного ланцюжка
            if (!hashTable.get(address).contains(word)) {
                hashTable.get(address).add(word);
            }
        }

        //Виведення результату у заданому форматі
        System.out.println("\n--- Результат хешування (Таблиця M=13) ---");
        for (int i = 0; i < hashTable.size(); i++) {
            System.out.printf("Індекс %02d: %s%n", i, hashTable.get(i));
        }
    }
}

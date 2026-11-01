import java.util.*;

public class Main {
    public static void main(String[] args) {
        int m = 13;

        List<String> words = Arrays.asList(
                "як", "добре", "вчишся", "то", "легко", "працюється", "а", "лінуєшся", "бідується");

        Map<Character, Integer> letterPositions = new HashMap<>();
        String alphabet = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
        for (int i = 0; i < alphabet.length(); i++) {
            letterPositions.put(alphabet.charAt(i), i + 1);
        }

        //Побудова хеш-таблиці з відкритою адресацією
        String[] hashTable = new String[m];

        //Обробка кожного слова
        for (String word : words) {
            int sumOfPositions = 0;
            String lowerWord = word.toLowerCase();

            //Обчислення суми позицій букв у слові
            for (int i = 0; i < lowerWord.length(); i++) {
                char c = lowerWord.charAt(i);
                sumOfPositions += letterPositions.getOrDefault(c, 0);
            }

            //Первинна хеш-функція
            int startAddress = sumOfPositions % m;
            boolean inserted = false;

            //Лінійне дослідження
            for (int i = 0; i < m; i++) {
                int address = (startAddress + i) % m;

                //Перевірка, чи комірка вільна
                if (hashTable[address] == null) {
                    hashTable[address] = word;
                    inserted = true;
                    break;
                }
            }

            if (!inserted) {
                System.out.println("Таблиця заповнена. Не вдалося додати слово: " + word);
            }
        }

        System.out.println("\n--- Хеш-таблиця (Відкрита адресація, M=13) ---");
        System.out.println("Індекс | Слово");
        System.out.println("-------|-------");
        for (int i = 0; i < hashTable.length; i++) {
            String value = (hashTable[i] != null) ? hashTable[i] : "(NULL)";
            System.out.printf("%02d     | %s%n", i, value);
        }
    }
}

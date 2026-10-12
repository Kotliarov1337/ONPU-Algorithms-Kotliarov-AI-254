import java.util.Arrays;

public class Main {

    //Клас для зберігання результатів сортування та статистики
    public static class Result {
        int[] sortedArr;
        long comparisons;
        long assignments;
        long recursiveCalls;

        public Result(int[] sortedArr, long comparisons, long assignments, long recursiveCalls) {
            this.sortedArr = sortedArr;
            this.comparisons = comparisons;
            this.assignments = assignments;
            this.recursiveCalls = recursiveCalls;
        }
    }

    //Рекурсивна функція сортування з підрахунком операцій та викликів
    public static Result mergeSortRecursive(int[] arr) {
        long comparisons = 0;
        long assignments = 0;
        long recursiveCalls = 0;

        System.out.println("Розділяємо масив: " + Arrays.toString(arr));

        int n = arr.length;
        if (n <= 1) {
            return new Result(arr, comparisons, assignments, recursiveCalls);
        }

        int mid = n / 2;
        assignments++; //Підрахунок присвоювання mid

        //Розбиваємо масив на дві половини
        int[] leftHalf = Arrays.copyOfRange(arr, 0, mid);
        int[] rightHalf = Arrays.copyOfRange(arr, mid, n);
        assignments += n; //Облік копіювання елементів у нові масиви

        //Підрахунок рекурсивних викликів
        recursiveCalls += 2;

        //Рекурсивно сортуємо ліву та праву частини
        Result leftResult = mergeSortRecursive(leftHalf);
        Result rightResult = mergeSortRecursive(rightHalf);

        comparisons += leftResult.comparisons + rightResult.comparisons;
        assignments += leftResult.assignments + rightResult.assignments;
        recursiveCalls += leftResult.recursiveCalls + rightResult.recursiveCalls;

        //Зливаємо відсортовані половини
        MergeResult mergeRes = merge(leftResult.sortedArr, rightResult.sortedArr);
        comparisons += mergeRes.comparisons;
        assignments += mergeRes.assignments;

        return new Result(mergeRes.mergedArr, comparisons, assignments, recursiveCalls);
    }

    //Допоміжний клас для результату злиття
    public static class MergeResult {
        int[] mergedArr;
        long comparisons;
        long assignments;

        public MergeResult(int[] mergedArr, long comparisons, long assignments) {
            this.mergedArr = mergedArr;
            this.comparisons = comparisons;
            this.assignments = assignments;
        }
    }

    //Допоміжна функція для злиття
    public static MergeResult merge(int[] left, int[] right) {
        System.out.println("Зливаємо " + Arrays.toString(left) + " та " + Arrays.toString(right));

        int[] mergedArr = new int[left.length + right.length];
        long comparisons = 0;
        long assignments = 0;
        int i = 0;
        int j = 0;
        int k = 0;

        assignments += 3; //ініціалізація i, j, k

        while (i < left.length && j < right.length) {
            comparisons++;
            boolean condition = left[i] <= right[j];
            System.out.print("Порівняння: " + left[i] + " <= " + right[j] + " -> " + condition + ". ");

            if (condition) {
                mergedArr[k] = left[i];
                i++;
                System.out.println("Додаємо " + left[i - 1]);
            } else {
                mergedArr[k] = right[j];
                j++;
                System.out.println("Додаємо " + right[j - 1]);
            }
            assignments++; //Запис у mergedArr[k]
            k++;
            assignments++; //Інкремент k
        }

        while (i < left.length) {
            mergedArr[k] = left[i];
            i++;
            k++;
            assignments += 2; //Запис у mergedArr та інкремент k
            System.out.println("Додаємо залишок з лівого масиву: " + left[i - 1]);
        }

        while (j < right.length) {
            mergedArr[k] = right[j];
            j++;
            k++;
            assignments += 2; //Запис у mergedArr та інкремент k
            System.out.println("Додаємо залишок з правого масиву: " + right[j - 1]);
        }

        System.out.println("Злиття завершено. Результат: " + Arrays.toString(mergedArr));
        return new MergeResult(mergedArr, comparisons, assignments);
    }

    //Головний метод для запуску прикладу
    public static void main(String[] args) {
        int[] myList = {77, 89, 74, 68, 70, 49, 5, 62, 51};

        System.out.println("--- РЕКУРСИВНА ВЕРСІЯ ---");
        System.out.println("Початковий список: " + Arrays.toString(myList));
        System.out.println("--------------------------------------------------");

        Result result = mergeSortRecursive(myList);

        System.out.println("--------------------------------------------------");
        System.out.println("Фінальний відсортований список: " + Arrays.toString(result.sortedArr));
        System.out.println("Загальна кількість порівнянь: " + result.comparisons);
        System.out.println("Загальна кількість присвоювань: " + result.assignments);
        System.out.println("Загальна кількість рекурсивних викликів: " + result.recursiveCalls);
    }
}

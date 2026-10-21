import java.util.Arrays;

public class Main {

    static long comparisons = 0;
    static long assignments = 0;
    static long recursiveCalls = 0;

    public static void quicksort(int[] a, int l, int r) {
        System.out.println("Quicksort виклик: масив = " + Arrays.toString(a) + ", l = " + l + ", r = " + r);
        if (l < r) {
            recursiveCalls++; //Облік поточного рекурсивного виклику

            int q = partition(a, l, r);

            quicksort(a, l, q);       //Рекурсивний виклик для лівого підмасиву
            quicksort(a, q + 1, r);   //Рекурсивний виклик для правого підмасиву
            System.out.println("Після Quicksort для l=" + l + ", r=" + r + ": масив = " + Arrays.toString(a));
        }
    }

    public static int partition(int[] a, int l, int r) {
        int pivot = a[l];
        assignments += 1;

        int i = l - 1;
        int j = r + 1;
        assignments += 2;

        System.out.println("Вибираємо опорний елемент (pivot): " + pivot);

        while (true) {
            //Шукаємо елемент, що більший за опорний
            i += 1;
            assignments += 1;

            while (a[i] < pivot) {
                comparisons += 1;
                i += 1;
                assignments += 1;
            }
            comparisons += 1; //Порівняння для виходу з циклу

            //Шукаємо елемент, що менший за опорний
            j -= 1;
            assignments += 1;

            while (a[j] > pivot) {
                comparisons += 1;
                j -= 1;
                assignments += 1;
            }
            comparisons += 1; //Порівняння для виходу з циклу

            comparisons += 1; //Порівняння i >= j
            if (i >= j) {
                System.out.println("Поточні індекси: i = " + i + ", j = " + j + ". Масив: " + Arrays.toString(a));
                System.out.println("Індекси перетнулися. Поділ завершено. Повертаємо j=" + j + ".");
                return j;
            }

            //Обмін елементів
            System.out.println("Поточні індекси: i = " + i + ", j = " + j + ". Обмінюємо a[" + i + "] (" + a[i] + ") і a[" + j + "] (" + a[j] + "). Масив: " + Arrays.toString(a));
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            assignments += 3;
        }
    }

    public static void main(String[] args) {
        int[] myList = {77, 89, 74, 68, 70, 49, 5, 62, 51};
        int[] originalList = myList.clone();

        comparisons = 0;
        assignments = 0;
        recursiveCalls = 0;

        System.out.println("--- Quicksort за схемою Хоара з трасуванням ---");
        System.out.println("Оригінальний список: " + Arrays.toString(originalList));

        quicksort(myList, 0, myList.length - 1);

        System.out.println("Фінальний відсортований список: " + Arrays.toString(myList));
        System.out.println("Загальна кількість порівнянь: " + comparisons);
        System.out.println("Загальна кількість присвоювань: " + assignments);
        System.out.println("Загальна кількість рекурсивних викликів: " + recursiveCalls);
    }
}

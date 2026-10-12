import java.util.Arrays;

public class Main {

    static long comparisons = 0;
    static long assignments = 0;

    public static void main(String[] args) {
        int[] myList = {77, 89, 74, 68, 70, 49, 5, 62, 51};

        System.out.println("--- ІТЕРАТИВНА ВЕРСІЯ ---");
        System.out.println("Початковий масив: " + Arrays.toString(myList));
        System.out.println("--------------------------------------------------");

        int[] a = myList.clone();
        comparisons = 0;
        assignments = 0;

        int n = a.length;
        int i = 1;
        while (i < n) {
            int j = 0;
            while (j < n - i) {
                int left = j;
                int mid = j + i;
                int right = Math.min(j + 2 * i, n);

                int[] leftSub = Arrays.copyOfRange(a, left, mid);
                int[] rightSub = Arrays.copyOfRange(a, mid, right);
                System.out.println("Об'єднуємо підмасиви: a[" + left + ":" + mid + "] (" + Arrays.toString(leftSub) +
                        ") і a[" + mid + ":" + right + "] (" + Arrays.toString(rightSub) + ")");

                merge(a, left, mid, right);

                System.out.println("Масив після об'єднання: " + Arrays.toString(a));
                System.out.println("--------------------------------------------------");

                j += 2 * i;
            }
            i *= 2;
        }

        System.out.println("Фінальний відсортований список: " + Arrays.toString(a));
        System.out.println("Загальна кількість порівнянь: " + comparisons);
        System.out.println("Загальна кількість присвоювань: " + assignments);
    }

    public static void merge(int[] a, int left, int mid, int right) {
        int n1 = mid - left;
        int n2 = right - mid;

        int[] L = Arrays.copyOfRange(a, left, mid);
        int[] R = Arrays.copyOfRange(a, mid, right);
        assignments += (n1 + n2);

        int it1 = 0;
        int it2 = 0;
        int k = left;

        assignments += 3;

        while (it1 < n1 && it2 < n2) {
            comparisons++;
            System.out.println("Порівняння: " + L[it1] + " < " + R[it2]);

            if (L[it1] < R[it2]) {
                a[k] = L[it1];
                it1++;
            } else {
                a[k] = R[it2];
                it2++;
            }
            assignments++;
            k++;
            assignments++;
        }

        while (it1 < n1) {
            a[k] = L[it1];
            it1++;
            k++;
            assignments += 2;
        }

        while (it2 < n2) {
            a[k] = R[it2];
            it2++;
            k++;
            assignments += 2;
        }
    }
}

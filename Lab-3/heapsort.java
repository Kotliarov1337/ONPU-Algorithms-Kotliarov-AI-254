import java.util.Arrays;

void main() {
    int[] A = {77, 89, 74, 68, 70, 49, 5, 62, 51};

    System.out.println("Початковий масив: " + Arrays.toString(A) + "\n");

    heapSort(A);

    System.out.println("Відсортований масив: " + Arrays.toString(A));
}

void heapSort(int[] A) {
    int n = A.length;

    System.out.println("--- Фаза 1: Побудова максимальної купи ---");
    for (int i = (n / 2) - 1; i >= 0; i--) {
        System.out.println("Занурюємо елемент з індексу " + i + ": " + A[i]);
        sink(A, i, n);
    }
    System.out.println("\nМасив після побудови купи: " + Arrays.toString(A) + "\n");

    System.out.println("--- Фаза 2: Сортування ---");
    for (int i = n - 1; i >= 1; i--) {
        System.out.println("Міняємо місцями корінь (" + A[0] + ") та останній елемент (" + A[i] + ")");
        swap(A, 0, i);

        n -= 1;
        System.out.println("Розмір купи зменшився до " + n + ". Відновлюємо властивості купи.");
        sink(A, 0, n);
        System.out.println("Масив на поточному кроці: " + Arrays.toString(A) + "\n");
    }
}

void sink(int[] A, int i, int n) {
    int k = i;
    while (true) {
        int j = 2 * k + 1;

        if (j >= n) {
            break;
        }

        if (j + 1 < n && A[j + 1] > A[j]) {
            j = j + 1;
        }

        if (A[k] >= A[j]) {
            break;
        } else {
            swap(A, k, j);
            k = j;
        }
    }
}

void swap(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
}

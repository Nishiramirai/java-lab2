import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Главное меню ---");
            System.out.println("1. Одномерные массивы");
            System.out.println("2. Двумерные и рваные массивы");
            System.out.println("0. Выход");

            int choice = readInt("Выбор: ");
            switch (choice) {
                case 1: menu1D(); break;
                case 2: menu2D(); break;
                case 0: return;
            }
        }
    }

    private static void menu1D() {
        int[] arr = null;

        while (true) {
            System.out.println("\n--- Одномерные массивы ---");
            System.out.println("1. Создать Random / 2. Вручную / 3. Вывод");
            System.out.println("4. Удалить N элементов с номера K");
            System.out.println("5. Добавить K элементов в начало");
            System.out.println("6. Поменять Мин и Макс");
            System.out.println("7. Поиск первого четного");
            System.out.println("8. Бинарный поиск");
            System.out.println("0. Назад");

            int ch = readInt("Выбор: ");
            switch (ch) {
                case 1: arr = createRandomArray(); break;
                case 2: arr = createManualArray(); break;
                case 3: printArray(arr);; break;
                case 4: arr = deleteElementsFromArr(arr); break;
                case 5: arr = insertToArrBeginning(arr); break;
                case 6: arr = replaceMinAndMax(arr); break;
                case 7: findFirstEven(arr);; break;
                case 8: binarySearch(arr);; break;
                case 0: return;
            }
        }
    }

    private static void menu2D() {
        int[][] matrix = null;
        int[][] jaggedArray = null;

        while (true) {
            System.out.println("\n--- Многомерные массивы ---");
            System.out.println("1. Создать и вывести двумерный массив");
            System.out.println("2. Удалить строки с K1 по K2");
            System.out.println("3. Создать и вывести рваный массив");
            System.out.println("4. Добавить строку по индексу");
            System.out.println("0. Назад");

            int ch = readInt("Выбор: ");
            switch (ch) {
                case 1: 
                    matrix = createMatrix();
                    printMatrix(matrix);
                    break;
                case 2:
                    matrix = deleteRowsRange(matrix);
                    printMatrix(matrix);
                    break;
                case 3: 
                    jaggedArray = createJagged();
                    printMatrix(jaggedArray);
                    break;
                case 4:
                    jaggedArray = addRowToJagged(jaggedArray);
                    printMatrix(jaggedArray); 
                    break;
                case 0: return;
            }
        }
    }

    // ЧАСТЬ 1

    // 1. a) Сформировать массив из n элементов с помощью датчика случайных чисел
    private static int[] createRandomArray() {
        int n = readInt("Размер массива: ");

        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Random().nextInt(100);
        }

        return a;
    }

    // 1. б) Сформировать массив из n элементов, пользователь вводит элементы с клавиатуры
    private static int[] createManualArray() {
        int n = readInt("Размер массива: ");

        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = readInt("[" + i + "]: ");
        }

        return a;
    }

    // 2. Распечатать массив
    private static void printArray(int[] a) {
        System.out.println(Arrays.toString(a));
    }

    // 3. Выполнить удаление N элементов из массива, начиная с номера K
    private static int[] deleteElementsFromArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int k = readInt("С какого индекса (K) удалять? ");
        int n = readInt("Сколько элементов (N) удалить? ");

        if (k < 0 || k >= arr.length || n < 0 || (k + n) > arr.length) {
            System.out.println("Ошибка: выход за границы массива");
            return arr;
        }

        int[] newArr = new int[arr.length - n];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i < k || i >= (k + n)) {
                newArr[j] = arr[i];
                j++;
            }
        }
        return newArr;
    }

    // 4. Добавить K элементов в начало массива:
    private static int[] insertToArrBeginning(int[] arr) {
        int k = readInt("Сколько элементов добавить в начало? ");
        if (k <= 0 && arr != null) return arr; 

        int currentLen = (arr == null) ? 0 : arr.length;
        int[] newArr = new int[currentLen + k];
        for (int i = 0; i < k; i++) {
            newArr[i] = readInt("Введите новый элемент [" + i + "]: ");
        }

        if (currentLen > 0) {
            System.arraycopy(arr, 0, newArr, k, currentLen);
        }

        return newArr;
    }

    // 5. Поменять местами минимальный и максимальный элементы
    private static int[] replaceMinAndMax(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        int minIdx = 0, maxIdx = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[minIdx]) {
                minIdx = i;
            }
            if (arr[i] > arr[maxIdx]) {
                maxIdx = i;
            }
        }

        if (minIdx != 0 || maxIdx != 0) {
            int tmp = arr[minIdx];
            arr[minIdx] = arr[maxIdx];
            arr[maxIdx] = tmp;
        }

        return arr;
    }

    // 6. Выполнить поиск первого четного элемента в массиве и подсчитать количество сравнений,
    // необходимых для поиска нужного элемента
    private static void findFirstEven(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Массив пуст или не существует.");
            return;
        }

        int count = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            count++;
            if (arr[i] % 2 == 0) {
                System.out.println("Элемент: " + arr[i] + " на индексе " + i + ". Сравнений: " + count);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Четных элементов не найдено. Сравнений: " + count);
        }
    }

    // 7.Выполнить поиск элемента, который вводит пользователь с клавиатуры, в отсортированном массиве (бинарный поиск)
    //  и подсчитать количество сравнений, необходимых для поиска нужного элемента
    private static void binarySearch(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Массив пуст или не существует");
            return;
        }

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        int key = readInt("Введите элемент для поиска: ");

        int count = 0;
        int low = 0, high = sorted.length - 1;
        while (low <= high) {
            count++;
            int mid = (low + high) / 2;

            if (sorted[mid] == key) {
                System.out.println("Элемент найден. Сравнений: " + count);
                return;
            }

            if (sorted[mid] < key) {
                low = mid + 1;
            } else {
                high = mid  - 1;
            }
        }
    }


    // ВСПОМОГАТЕЛЬНЫЕ ФУНКЦИИ

    private static int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.next());
            } catch (Exception e) {
                System.out.println("Ошибка! Введите целое число");
            }
        }
    }


    // ЧАСТЬ 2

    // 1. Создание двумерного массива, заполненного случайными числами
    private static int[][] createMatrix() {
        int m = readInt("Количество строк: ");
        int n = readInt("Количество столбцов: ");

        if (m == 0 || n == 0) {
            System.out.println("Ошибка: количество строк и столбцов должно быть больше нуля");
            return new int[0][0];
        }

        int matrix[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = new Random().nextInt(100);
            }
        }

        return matrix;
    }

    // 1. Вывод двумерного массива на печать
    private static void printMatrix(int[][] matrix) {
        if (matrix == null) {
            return;
        }

        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    // 2. Удалить строки, начиная со строки К1 и до строки К2 включительно
    private static int[][] deleteRowsRange(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }

        int k1 = readInt("Удалять с индекса (K1)");
        int k2 = readInt("Удалять по индекс (K2)");

        if (k1 < 0 || k2 >= matrix.length || k1 > k2) {
            System.out.println("Ошибка: неверный диапозон");
            return matrix;
        }

        int rowsToRemove = k2 - k1 + 1;
        
        int[][] newMatrix = new int[matrix.length - rowsToRemove][];
        for (int i = 0, j = 0; i < matrix.length; i++) {
            if (i < k1 || i > k2) {
                newMatrix[j] = matrix[i];
                j++;
            }
        }
        return newMatrix;
    }

    // 3. Сформировать рваный массив, заполненный случайными числами.
    private static int[][] createJagged() {
        int m = readInt("Введите количество строк: ");
        int [][]jagged = new int[m][];

        for (int i = 0; i < m; i++) {
            jagged[i] = new int[new Random().nextInt(5) + 1];
            for (int j = 0; j < jagged[i].length; j++) {
                jagged[i][j] = new Random().nextInt(100);
            }
        }

        return jagged;
    }

    // 4. Добавить строку с заданным номером
    private static int[][] addRowToJagged(int[][] m) {
            int idx = readInt("Индекс для вставки строки: ");
            int size = readInt("Размер новой строки: ");
            int[] newRow = new int[size];
            for (int i = 0; i < size; i++) newRow[i] = new Random().nextInt(100);

            if (m == null) {
                return new int[][]{newRow};
            }
            if (idx < 0 || idx > m.length) {
                System.out.println("Индекс вне диапазона, вставка произойдет в конец");
                idx = m.length;
            }

            int[][] newM = new int[m.length + 1][];
            for (int i = 0, j = 0; i < newM.length; i++) {
                if (i == idx) newM[i] = newRow;
                else newM[i] = m[j++];
            }
            return newM;
    }
}

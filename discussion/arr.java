public class arr {


    // public static void deInsert(int[] arr, int item, int position) {
    //     int n = arr.length;
    //     if (position > n) {
    //         arr[n + 1] = item;
    //     }

    //     else {
    //         for (int i = n + 1; i > position; i--) {
    //             arr[i] = arr[i - 1];
    //         }
    //         arr[position] = item;
    //     }

    // }
    public static int[] insert(int[] arr, int item, int position) {
        int n = arr.length;
        int[] res = new int[n + 1];
        for (int i = 0; i < n; i++) {
            res[i] = arr[i];
        }
        if (position > n) {
            res[n + 1] = item;
        }

        else {
            for (int i = n; i > position; i--) {
                res[i] = res[i - 1];
            }
            res[position] = item;
        }

        return res;
    }

    public static void deReverse(int[] arr) {
        int n = arr.length;
        int tmp = 0;
        for (int i = 0; i < n / 2; i++) {
            tmp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = tmp;
        }
    }

    public static int[] replicate(int[] arr) {
        int sum = 0;
        for (int total : arr) {
            sum += total;
        }
        int[] res = new int[sum];
        int i = 0;
        for (int num : arr) {
            int count = num;
            while (count > 0) {
                res[i] = num;
                i++;
                count--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] x  = new int[]{5, 9, 14, 15};
        x = insert(x, 6, 2);
        deReverse(x);
        for (int i : x) {
            System.out.println(i);
        }

        int[] y = new int[]{3, 2, 1};
        y = replicate(y);
        for (int j : y) {
            System.out.println(j);
        }
    }
}
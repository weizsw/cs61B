public class Flatten {
    public static int[] flatten(int[][] x) {
        int totalLength = 0;
        for (int[] arr : x) {
            for (int num : arr) {
                totalLength++;
            }
        }

        int[] a = new int[totalLength];
        int aIndex = 0;
        for (int[] arr : x) {
            for (int num : arr) {
                a[aIndex] = num;
                aIndex++;
            }
        }

        return a;
    }


    public static void main(String[] args) {
        int[][] test = new int[][]{{1,2,3}, {}, {7,8}};
        int[] res = Flatten.flatten(test);
        for (int num : res) {
            System.out.println(num);
        }
    }
}
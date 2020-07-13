package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] cnt = new int[M];
        int N = oomages.size();
        for (int i = 0; i < M; i++) {
            cnt[i] = 0;
        }
        for (Oomage oomage : oomages) {
            int bucketNum = (oomage.hashCode() & 0x7FFFFFFF) % M;
            cnt[bucketNum]++;
        }
        for (int i = 0; i < M; i++) {
            if (cnt[i] < N / 50 || cnt[i] > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}

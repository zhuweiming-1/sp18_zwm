package hw3.hash;


import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        List<Oomage>[] buckets = new List[M];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (Oomage oomage : oomages) {
            int index = (oomage.hashCode() & 0x7FFFFFFF) % M;
            buckets[index].add(oomage);
        }
        int total = oomages.size();
        int max = (int) (total / 2.5);
        int min = (int) (total / 50);
        for (List<Oomage> bucket : buckets) {
            if (bucket.size() < min || bucket.size() > max) {
                return false;
            }
        }
        return true;
    }
}

package ua.dp.dmma;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dmma
 */
public class AssociativeArray {
    public void createDataForArray() {
        long startTime = new Date().getTime();
        Random random = new Random();

        int[] randomInts = random.ints(1, 1000).limit(3_000_000).toArray();
        System.out.println("generating ints = " + (new Date().getTime() - startTime));

        startTime = new Date().getTime();
        List<Double> randomDoubles = random.doubles().limit(1_000_000).boxed().collect(Collectors.toList());

        System.out.println("generating doubles = " + (new Date().getTime() - startTime));

        Map<CompositeKey, Double> associativeArray = new TreeMap<>();
        startTime = new Date().getTime();


//        while (associativeArray.size() < 1_000_000) {
//            associativeArray.put(new CompositeKey(random.nextInt(1000) + 1, random.nextInt(1000) + 1, random.nextInt(1000) + 1), random.nextDouble());
//        }

        for (int i = 0; i < 3000000; i = i + 3)
            associativeArray.put(new CompositeKey(randomInts[i], randomInts[i + 1], randomInts[i + 2]), randomDoubles.get(i / 3));

        System.out.println("filling of map = " + (new Date().getTime() - startTime));
        System.out.println(associativeArray.size());

        System.out.println();

        List<CompositeKey> compositeKeys = getRandomKeysList(random);
        for (CompositeKey key : compositeKeys) {
            System.out.println(associativeArray.get(key));
        }
        System.out.println(compositeKeys.stream().map(associativeArray::get).filter(aDouble -> aDouble != null).sorted().limit(10)
                .collect(Collectors.toList()));
    }

    private List<CompositeKey> getRandomKeysList(Random random) {
        List<CompositeKey> compositeKeys = new ArrayList<>();

        List<int[]> combinationList = new ArrayList<>();

        int[] keysBuildingSource = random.ints(1, 1000).limit(6).toArray();
        int firstElementOfKey = keysBuildingSource[0];
        keysBuildingSource = Arrays.copyOfRange(keysBuildingSource, 1, keysBuildingSource.length);
        int sequenceLength = 2;

        int[] tmp = {0, 1};
        combinationList.add(getSubset(keysBuildingSource, tmp));

        for (; ; ) {
            int i;
            // find position of item that can be incremented
            for (i = sequenceLength - 1; i >= 0 && tmp[i] == keysBuildingSource.length - sequenceLength + i; i--) ;
            if (i < 0) {
                break;
            }
            tmp[i]++;                    // increment this item
            for (++i; i < sequenceLength; i++) {    // fill up remaining items
                tmp[i] = tmp[i - 1] + 1;
            }
            combinationList.add(getSubset(keysBuildingSource, tmp));
        }

        System.out.println(Arrays.toString(keysBuildingSource));
        compositeKeys.addAll(combinationList.stream().map(combination -> new CompositeKey(firstElementOfKey, combination[0], combination[1]))
                .collect(Collectors.toList()));
        System.out.println(compositeKeys);
        return compositeKeys;
    }

    private int[] getSubset(int[] input, int[] subset) {
        int[] result = new int[subset.length];
        for (int i = 0; i < subset.length; i++)
            result[i] = input[subset[i]];
        return result;
    }
}

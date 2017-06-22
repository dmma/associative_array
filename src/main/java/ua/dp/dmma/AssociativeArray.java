package ua.dp.dmma;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dmma
 */
public class AssociativeArray
{
    private static final int ARRAY_SIZE = 1_000_000;

    public List<Double> getNMaxValuesFromArray(int numberOfElements, int rangeBound)
    {
        Random random = new Random();
        Map<CompositeKey, Double> associativeArray = new TreeMap<>();

        while (associativeArray.size() < ARRAY_SIZE)
        {
            associativeArray.put(new CompositeKey(random.nextInt(rangeBound) + 1, random.nextInt(rangeBound) + 1, random.nextInt(rangeBound) + 1),
                            random.nextDouble());
        }

        return getCheckKeysList(random, rangeBound).stream().map(associativeArray::get).filter(aDouble -> aDouble != null).sorted().limit(numberOfElements)
                        .collect(Collectors.toList());
    }

    private List<CompositeKey> getCheckKeysList(Random random, int rangeBound)
    {
        List<int[]> combinationList = new ArrayList<>();

        int[] keysBuildingSource = random.ints(1, rangeBound).limit(6).toArray();
        int firstElementOfKey = keysBuildingSource[0];
        keysBuildingSource = Arrays.copyOfRange(keysBuildingSource, 1, keysBuildingSource.length);
        int sequenceLength = 2;

        int[] tmp = { 0, 1 };
        combinationList.add(getCombination(keysBuildingSource, tmp));

        for (; ; )
        {
            int i;
            for (i = sequenceLength - 1; i >= 0 && tmp[i] == keysBuildingSource.length - sequenceLength + i; i--)
                ;
            if (i < 0)
            {
                break;
            }
            tmp[i]++;
            for (++i; i < sequenceLength; i++)
            {
                tmp[i] = tmp[i - 1] + 1;
            }
            combinationList.add(getCombination(keysBuildingSource, tmp));
        }

        List<CompositeKey> compositeKeyList = new ArrayList<>();
        combinationList.forEach(combination ->
        {
            compositeKeyList.add(new CompositeKey(firstElementOfKey, combination[0], combination[1]));
            compositeKeyList.add(new CompositeKey(firstElementOfKey, combination[1], combination[0]));
        });
        return compositeKeyList;
    }

    private int[] getCombination(int[] input, int[] subset)
    {
        int[] result = new int[subset.length];
        for (int i = 0; i < subset.length; i++)
            result[i] = input[subset[i]];
        return result;
    }
}

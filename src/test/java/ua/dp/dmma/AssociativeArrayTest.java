package ua.dp.dmma;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author dmma
 */
public class AssociativeArrayTest
{
    private AssociativeArray associativeArray;

    @BeforeClass
    public void SetUp()
    {
        associativeArray = new AssociativeArray();
    }

    @Test
    public void testGetNMaxValuesFromArray()
    {
        associativeArray.getNMaxValuesFromArray(10, 100);
    }
}

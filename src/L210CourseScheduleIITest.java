import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class L210CourseScheduleIITest {

    private L210CourseScheduleII l210CourseScheduleII;

    @BeforeMethod
    public void initialize() {
        l210CourseScheduleII = new L210CourseScheduleII();
    }

    @DataProvider(name = "testCases")
    public static Object[][] testCases() {
        return new Object[][] { { 2, new int[][] { { 1, 0 } }, new int[] { 0, 1 } },
                { 4, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } },
                        new int[][] { { 0, 1, 2, 3 }, { 0, 2, 1, 3 } } },
                { 1, new int[][] {}, new int[] { 0 } },
                { 2, new int[][] { { 0, 1 }, { 1, 0 } }, new int[] {} } };
    }

    @Test(dataProvider = "testCases")
    public void testFindOrder(int numCourses, int[][] prerequisites, int[][] expectedResults) {
        int[] result = l210CourseScheduleII.findOrder(numCourses, prerequisites);
        boolean foundResult = false;
        for (int[] expectedResult : expectedResults) {
            foundResult = Arrays.equals(result, expectedResult);
            if (foundResult) {
                break;
            }
        }
        Assert.assertTrue(foundResult, null);
    }
}
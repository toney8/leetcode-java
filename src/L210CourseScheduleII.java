import java.util.ArrayList;
import java.util.List;

public class L210CourseScheduleII {

    public class Course {
        int num;
        // `N`: not checked yet, `T`: taken, `C`: checking
        char status;
        List<Course> prerequisites;
        public Course(int num) {
            this.num = num;
            this.status = 'N';
            this.prerequisites = new ArrayList<>();
        }
        public String toString() {
            return "["+num+","+status+"]";
        }
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // numCourses = 4, courses are: 0, 1, 2, 3
        // For prerequisites = [[1,0],[2,0],[3,1],[3,2]], 
        // prerequisites[a][b]: need to take `b` before `a`
        // prerequisites[0][0] = 1, [0][1] = 0: Take `0` before `1`
        // prerequisites[1][0] = 2, [1][1] = 0: Take `0` before `2`
        // prerequisites[2][0] = 3, [2][1] = 1: Take `1` before `3`
        // prerequisites[3][0] = 3, [3][1] = 2: Take `2` before `3`
        
        // 2, [[1, 0]]
        List<Course> courses = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            courses.add(new Course(i));
        } // course 0, 1
        for(int i = 0; i < prerequisites.length; i++) {
            courses.get(prerequisites[i][0]).prerequisites.add(courses.get(prerequisites[i][1]));
        } // course 1.prerequisites contains course 0
        

        for(int i = numCourses - 1; i >= 0; i--) {
            // need to do a DFS instead of BFS
            if (!dfs(courses.get(i))) { // course 1 / course 0
                return new int[0];
            }
        }
        int[] resultArray = new int[resultList.size()];
        for(int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }
    
    List<Integer> resultList = new ArrayList<>();
    
    boolean dfs(Course course) { // course 1
        // `N`: not checked yet, `T`: taken, `C`: checking
        if (course.status == 'C') {
            return false;
        }
        if (course.status == 'T') {
            return true;
        }

        course.status = 'C';
        boolean result = true;
        for(Course preCourse: course.prerequisites) {
            if (!dfs(preCourse)) {
                result = false;
                break;
            }
        }
        if (result) {
            course.status = 'T'; // course 0: T / course 1: T
            resultList.add(course.num);
        }
        return result;
    }
}
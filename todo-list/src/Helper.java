import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Helper {
	
	// Function Implementations
	
	// Sorts the list of tasks by due date
	public static List<Task> SortTasks(List<Task> input){
		Collections.sort(input, new DueDateSort());
		return input;
	}
	
	public static void main(String[] args) {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(new Task("Task1", "hello", 3, 15, 2023));
		tasks.add(new Task("Task2", "hi", 2, 2, 2023));
		tasks.add(new Task("Task3", "bye", 11, 27, 2022));
		
		tasks = SortTasks(tasks);
		
		for(int i=0; i<tasks.size(); i++) {
			System.out.println(tasks.get(i).taskName);
		}
	}

}

class DueDateSort implements Comparator<Task>{

	@Override
	public int compare(Task t1, Task t2) {
		
		if(t1.dueYear > t2.dueYear) return -1; 
		else if(t1.dueYear == t2.dueYear) {
			if(t1.dueMonth < t2.dueMonth) return -1;
			else if(t1.dueMonth == t2.dueMonth) {
				if(t1.dueDay < t2.dueDay) return -1;
			}
		}
		return 1;
	}
	
}

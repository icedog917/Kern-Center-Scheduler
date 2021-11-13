import java.util.HashMap;
import java.util.HashSet;

public class Employee{
  //Represent the days of the week
  public static enum Day{M,T,W,R,F,S,U};
  //Integers go 6 to 23, military time
  private HashMap<Day, HashSet<Integer>> availability;
  
  /**
  * Default Constructor
  */
  public Employee(){
    availability = new HashMap<Day, HashSet<Integer>>();
  }

  /**
  * adds a time for a specific day of the week
  * @param day - holding the enum for the day of the week
  * @param hour - int holding the time, between 6 and 23 to add
  */
  public void addTime(Day day, int hour){
    //Create the Set of times for that day if it is missing
    availability.putIfAbsent(day, new HashSet<Integer>());
    //Add the time to that day
    HashSet<Integer> oldSet = availability.get(day);
    oldSet.add(hour);
    availability.put(day,oldSet);
  }

  /**
  * Checks if the employee is available at a specific time
  * @param day - holding the enum for the day of the week
  * @param hour - int holding the time, between 6 and 23 to check for
  * @return whether the employee is available at the specified day and time
  */
  public boolean isAvailable(Day day, int hour){
    
  }
}
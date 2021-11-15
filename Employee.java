import java.util.HashMap;
import java.util.HashSet;
import java.util.Collection;

public class Employee{
  //Represent the days of the week
  public static enum Day{M,T,W,R,F,S,U};
  //Integers in military time
  private HashMap<Day, HashSet<Integer>> availability;
  //Full name of the employee
  private String name;
  //Minimum and maximum amount of hours that employee wants to work
  private int minHours, maxHours;
  
  /**
  * Default Constructor
  */
  public Employee(){
    availability = new HashMap<Day, HashSet<Integer>>();
    minHours = 0;
    maxHours = 0;
  }

  /**
  * Constructor that takes in the employees name
  * @param name - A string representing the name of the 
  */
  public Employee(String name){
    this();
    this.name = name;
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
  * @return whether the employee is available at the specified day and times
  */
  public boolean isAvailable(Day day, int hour){
    if(availability.containsKey(day)){
      return availability.get(day).contains(hour);
    }
    return false;
  }

  /**
  * Basic Getter for the name of the employee
  * @return the name of the employee
  */
  public String getName(){
    return name;
  }

  /**
  * Sets the minimum and maximum hours that an employee wants to work
  * @param min - an int representing the minimum number of hours
  * @param max - an int representing the maximum number of hours
  */
  public void setHourRange(int min, int max){
    minHours = min;
    maxHours = max;
  }

  /**
  * Takes in the hours the employee is scheduled for and returns whether that is within the range
  * @param hours - an int representing how many hours the employee is scheduled for
  * @return whether the hours are within the employee's range
  */
  public boolean hoursInRange(int hours){
    return hours>=minHours && hours<=maxHours;
  }
}
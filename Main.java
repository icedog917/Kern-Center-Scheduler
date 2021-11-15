import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
* Runner class for generating the schedule
*/
class Main {
  private static ArrayList<Employee> employees;

  public static void main(String[] args) {
    employees = new ArrayList<Employee>();
    String path = null;
    do{
      path = fileSelector();
    }while(path==null);

    try (Scanner scanner = new Scanner(new File(path))) {
      String currentLine = scanner.nextLine();
      //Assume that the data accounts for starting in the morning as the earliest time slot
      int bracketIndex = currentLine.indexOf("[");
      //Grab the earliest time
      int currentHour = Integer.parseInt(currentLine.substring(bracketIndex+1, currentLine.indexOf(" ", bracketIndex)));
      while (scanner.hasNextLine()) {
        currentLine = scanner.nextLine();
        currentLine = currentLine.substring(currentLine.indexOf(",")+1);
        Employee currentEmployee = new Employee(currentLine.substring(0, currentLine.indexOf(",")));
        currentLine = currentLine.substring(currentLine.indexOf(",")+1);
        //Look at each column of the row
        parseEmployeeAvailability(currentLine, currentEmployee, currentHour);
        currentLine = currentLine.substring(currentLine.indexOf(",")+1);
        ++currentHour;
        //TODO: add how many hours people want to work to employee
        int minHours = 0;
        int maxHours = 0;
        currentEmployee.setHourRange(minHours, maxHours);
        
        

        employees.add(currentEmployee);
      }
    }catch(FileNotFoundException e){
      System.err.println("The file could not be found!");
    }catch(IllegalArgumentException e){
      System.err.println(e.getMessage());
    }
    
  }

  /**
  * Helper function to get an enum of the day of the week from the string of the day
  * @param day - a string representing the day of the week
  * @throws IllegalFormatException - If the string passed in is not in the proper format
  * @return The enum to be used for an employee
  */
  private static Employee.Day parseDay(String day) throws IllegalArgumentException{
    if(day==null){
      System.out.println("The Day was null");
    }
    day = day.toLowerCase().replaceAll("\\W","");
    Employee.Day resultingDay = null;
    switch(day){
      case "monday":
        resultingDay = Employee.Day.M;
        break;
      case "tuesday":
        resultingDay = Employee.Day.T;
        break;
      case "wednesday":
        resultingDay = Employee.Day.W;
        break;
      case "thursday":
        resultingDay = Employee.Day.R;
        break;
      case "friday":
        resultingDay = Employee.Day.F;
        break;
      case "saturday":
        resultingDay = Employee.Day.S;
        break;
      case "sunday":
        resultingDay = Employee.Day.U;
        break;
      default:
        System.out.println("Other Weekday found: " + day);
        break;
    }
    if(resultingDay!=null){
      return resultingDay;
    }
    throw new IllegalArgumentException("The day of the week was in an invalid format");
  }
  
  /**
  * parses the days that an employee is available for each time
  * @param currentLine - the remainder of the current row for the specified employee
  * @param currentEmployee - an Employee to add on times to
  * @param currentHour - an int representing the current time of day being used for adding availablity
  */
  private static void parseEmployeeAvailability(String currentLine, Employee currentEmployee, int currentHour) throws IllegalArgumentException{
    while (currentLine.indexOf(",") >= 0) {
      //Check if there are multiple days for this time
      if (currentLine.indexOf("\"") == 0 && currentLine.indexOf("\"") != currentLine.lastIndexOf("\"")) {
        String currentDays = currentLine.substring(1, currentLine.indexOf("\"", currentLine.indexOf("\"")+1));
        String[] days = currentDays.split(", ");
        for(String each: days){
          currentEmployee.addTime(parseDay(each), currentHour);
        }
      } else if(currentLine.indexOf(",") != 0) { //Add single day for time
        currentEmployee.addTime(parseDay(currentLine.substring(0,currentLine.indexOf(","))), currentHour);
      }
      currentLine = currentLine.substring(currentLine.indexOf(",", 1)+1);
    }
  }

  private static String fileSelector() {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("csv files", "csv");
    chooser.setFileFilter(filter);
    int dialogueCode = chooser.showOpenDialog(null);
    String returnValue = null;
    if(dialogueCode == JFileChooser.APPROVE_OPTION) {
      returnValue = chooser.getSelectedFile().getName();
    }
    return returnValue;
  }
}
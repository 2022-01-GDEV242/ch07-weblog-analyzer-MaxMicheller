/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling. (original)
 * @version    2016.02.29 (original)
 * 
 * @author Max Micheller
 * @version 3/6/2022
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     * @param hourCounts is the amount of hours in a day
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader("weblog.txt");
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        //  for(int hour = 0; hour < hourCounts.length; hour++) {
        //     System.out.println(hour + ": " + hourCounts[hour]);
        //  }
        
        //replaced the for loop with a while loop as per homework 7.10
        int hour = 0;
        while (hour < hourCounts.length){
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }
    
    /**
     * Return the number of accesses recorded in the log file.
     */
    public int numberOfAccesses(){
        int total = 0;
        int hour = 0;
        while (hour < hourCounts.length){
            total += hourCounts[hour];
            hour++;
        }
        return total;
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Takes the name of the log file to be analyzed
     */
    public void fileReader(String logFile){
        LogfileReader read = new LogfileReader(logFile);
        read.printData();
    }
    
    /**
     * Returns the busiest Hour in a year (weblog adjusted to a year of hours)
     */
    public int busiestHour(){
        int busiest = 0;
        int busiestHour = 50;
        for (int hour = 0; hour < hourCounts.length;hour++){
            if (busiest < hourCounts[hour]){
                busiest = hourCounts[hour];
                busiestHour = hour;
            }
        }   
        return busiestHour;
    }
    
    /**
     * Returns the busiest Hour in a year (weblog adjusted to a year of hours)
     */
    public int quietestHour(){
        int hourx = 0;
        int quietest = hourCounts[hourx];
        int quietestHour = 50;
        for (int hour = 0; hour < hourCounts.length;hour++){
            if (quietest > hourCounts[hour]){
                quietest = hourCounts[hour];
                quietestHour = hour;
            }
        }   
        return quietestHour;
    }
    
    /**
     * finds which two-hour period is the busiest
     * returns the value of the first hour of this period
     */
    public int busiestTwoHour(){
        int busiestPeriod = 50;
        int busiest = 0;
        for (int hour = 0; hour < (hourCounts.length - 1);hour++){
            int twoHourPeriod = hourCounts[hour] + (hourCounts[(hour+1)]);
            if (busiest < twoHourPeriod){
                busiest = twoHourPeriod;
                busiestPeriod = hour;
            }
        }
        return busiestPeriod;
    }
}

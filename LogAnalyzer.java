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
    private int[] dayCounts;
    private int[] monthCounts;
    private int[] yearCounts;
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
        dayCounts = new int[29];
        monthCounts = new int[13];
        yearCounts = new int[5];
        // Create the reader to obtain the data.
        reader = new LogfileReader("weblog.txt");
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        // Create the reader to obtain the data.
        reader = new LogfileReader("weblog.txt");
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
    public int totalAccessesPerHour(){
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
     * Returns the quietest Hour in a year (weblog adjusted to a year of hours)
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
        if(quietestHour == 50){
            quietestHour = 0;
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
    
    //Days
    
    /**
     * Analyze the dayly access data from the log file.
     */
    public void analyzeDaylyData()
    {
        // Create the reader to obtain the data.
        reader = new LogfileReader("weblog.txt");
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }
    
    /**
     * Print the dayly counts.
     * These should have been set with a prior
     * call to analyzeDayData.
     */
    public void printDaylyCounts()
    {
        System.out.println("Day: Count");
        int day = 1;
        while (day < dayCounts.length){
            System.out.println(day + ": " + dayCounts[day]);
            day++;
        }
    }
    
    /**
     * Returns the busiest day in the data se
     */
    public int busiestDay(){
        int busiest = 0;
        int busiestDay = 50;
        for (int day = 0; day < dayCounts.length;day++){
            if (busiest < dayCounts[day]){
                busiest = dayCounts[day];
                busiestDay = day;
            }
        }   
        return busiestDay;
    }
    
    /**
     * Returns the quietest Day in a year
     */
    public int quietestDay(){
        int dayx = 1;
        int quietest = dayCounts[dayx];
        int quietestDay = 50;
        for (int day = 1; day < dayCounts.length;day++){
            if (quietest > dayCounts[day]){
                quietest = dayCounts[day];
                quietestDay = day;
            }
        }
        if (quietestDay == 0){
            quietestDay = 1;
        }
        
        return quietestDay;
    }
    
    //Month stuff
    
    /**
     * Analyze the monthly access data from the log file.
     */
    public void analyzeMonthlyData()
    {
        // Create the reader to obtain the data.
        reader = new LogfileReader("weblog.txt");
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
    }
    
    /**
     * Return the number of accesses per month recorded in the log file.
     */
    public int totalAccessesPerMonth(){
        int total = 0;
        int month = 1;
        while (month < monthCounts.length){
            total += monthCounts[month];
            month++;
        }
        return total;
    }
    
            /**
     * Print the monthly counts.
     * These should have been set with a prior
     * call to analyzeMonthData.
     */
    public void printMonthlyCountsByYear()
    {
        for (int year = 0; year < yearCounts.length;year++){
        System.out.println("Year: Month: Count");
        int month = 1;
            while (month < monthCounts.length){
                System.out.println((2015 +year) + ": " + month + ": " + monthCounts[month]);
                month++;
            }
        }
    }
    
     /**
     * Returns the busiest month in the dataset per year
     */
    public void busiestMonth(){
        for (int year = 0; year < yearCounts.length;year++){
        System.out.println("Year: Busiest Month");
        int busiest = 0;
        int busiestMonth = 50;
            for (int month = 0; month < monthCounts.length;month++){
                if (busiest < monthCounts[month]){
                    busiest = monthCounts[month];
                    busiestMonth = month;
                }
            }
        System.out.println((2015 +year) + ": " + busiestMonth);
        }
    }
    
    /**
     * Returns the quietest Month in a year
     */
    public void quietestMonth(){
        for (int year = 0; year < yearCounts.length;year++){
        System.out.println("Year: quietest Month");
        int monthx = 1;
        int quietest = monthCounts[monthx];
        int quietestMonth = 50;
            for (int month = 1; month < monthCounts.length;month++){
                if (quietest > monthCounts[month]){
                    quietest = monthCounts[month];
                    quietestMonth = month;
                }
            }
            if (quietestMonth == 50){
                quietestMonth = 1;
            }
        System.out.println((2015 +year) + ": " + quietestMonth);
        }
    }
}

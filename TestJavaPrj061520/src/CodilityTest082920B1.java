import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class CodilityTest082920B1 {
	public static void main(String[] args) {
		CodilityTest082920B1  c = new CodilityTest082920B1();
		String s = "Sun 10:00-20:00\r\nTue 11:00-20:00\r\nMon 10:00-20:00"; 
		c.solution(s);
	} 
    public int solution(String str)  {
       String [] sArr = str.split("\\r?\\n"); // split by new line
       int nMeetings = sArr.length;
       ArrayList <Meeting> arrayList = new ArrayList();
       Meeting m = null;
       for ( int i = 0; i < sArr.length; i++ )   
       {
    	   m = new Meeting(sArr[i]);
    	   arrayList.add(m);
       }
       // Collections.sort(arrayList, new MeetingComparator()); // Both Sorting approaches Work
       Collections.sort(arrayList); // Both Sorting approaches Work
       printArrayList(arrayList);
       int maxMins = 0; Meeting m1; Meeting nextM; int tempMins = 0;
       for ( int i = 0; i < arrayList.size()-1; i++ ) 
       {
    	   m1 = arrayList.get(i);
    	   nextM = arrayList.get(i+1);
    	   tempMins = m1.getDifference(nextM);
    	   if (maxMins < tempMins)
    		   maxMins = tempMins;
       }
       return maxMins;
    }
    void printArrayList(ArrayList <CodilityTest082920B1.Meeting> arrayList)
    {
        for ( int i = 0; i < arrayList.size(); i++ ) 
        {
     	   System.out.println(arrayList.get(i));
        }    	
    }
    static class Meeting implements Comparable <Meeting>
    {
	       	int dayOfWeek; int hourFrom; int minFrom; int hourTo; int minTo;
	       	String sMeeting;
	       	Meeting() {}
	       	Meeting(String meetingDateTime)
	       	{
	       		sMeeting = meetingDateTime;
	       		String dayTime [] = meetingDateTime.split(" "); 
	       		dayOfWeek = getDayofWeek(dayTime[0].trim());
	       		String timeFromTo[] = dayTime[1].split("-"); 
	       		String hhmm [] = timeFromTo[0].split(":"); 
	       		hourFrom = Integer.valueOf(hhmm[0].trim());
	       		minFrom = Integer.valueOf(hhmm[1].trim());
	       		hhmm = timeFromTo[1].split(":"); 
	       		hourTo = Integer.valueOf(hhmm[0].trim());
	       		minTo = Integer.valueOf(hhmm[1].trim());    		
	       	}
	       	int getDifference(Meeting m)
	       	{
	       		System.out.println(this);
	       		System.out.println(m);
	       		int diff = 0; int temp = 0;
	       		if (dayOfWeek == m.dayOfWeek)
	       		{
	       			if (m.hourFrom == hourTo) //"Sun 10:00-20:00"  "Sun 20:35-23:15"
	       			{
	       				temp = m.minFrom- minTo;
	       				return temp;
	       			} 
	       			else //"Sun 10:00-20:00"  "Sun 22:35-23:15"
	       			{
	       				temp = hourTo - m.hourFrom ;
	       				temp = temp * 60;
	       				temp = temp  + minTo - m.minFrom;
	       				return temp;
	       			}
	       		}
	       		else // assuming Next Day //"Sun 10:00-20:00"  "Mon 20:35-23:15"
	       		{ // find hours till end of current day and then hours in next day.
    				temp = 24 - hourTo;
    				temp = temp * 60; // 240
    				if (minTo > 0)
    					temp = temp  + 60 - minTo;	  // 
    				temp = temp + (m.hourFrom * 60 );
    				temp = temp + m.minFrom;
	       		}
	       		return temp;
	       	}
	       	//a negative integer - if this object is less than; zero - if equal to, or a positive integer - if greater than the specified object.
	       	public int compareTo( Meeting m )
	       	{
	       		if (dayOfWeek < m.dayOfWeek)
	       			return -1;
	       		else if (dayOfWeek > m.dayOfWeek)
	       		    return 1;
	       		// same day of week
	       		if (hourFrom < m.hourFrom)
	       			return -1;
	       		else if (hourFrom > m.hourFrom)
	       		    return 1;	      
	       		// same day of week and same hour
	       		if (minFrom < m.minFrom)
	       			return -1;
	       		else if (minFrom > m.minFrom)
	       		    return 1;	 	       		
	       		return 0;
	       	}
	        int getDayofWeek(String weekDay)
	        {
	        	if (weekDay.equals("Sun"))
	        		return 0;
	        	else if (weekDay.equals("Mon"))
	        		return 1;    	
	        	else if (weekDay.equals("Tue"))
	        		return 2;    	
	        	else if (weekDay.equals("Wed"))
	        		return 3;    	
	        	else if (weekDay.equals("Thu"))
	        		return 4;    	
	        	else if (weekDay.equals("Fri"))
	        		return 5;    	 
	        	else if (weekDay.equals("Sat"))
	        		return 6;       	
	        	return -1;
	        }   
	        public String toString()
	        {
	        	return sMeeting;
	        }
    } // End of Local Inner Class
    class MeetingComparator implements Comparator<Meeting>
    {//a negative integer - if this object is less than; zero - if equal to, or a positive integer - if greater than the specified object.
		@Override        //"Sun 10:00-20:00"  "Mon 20:35-23:15"
		public int compare(Meeting m1, Meeting m2) {
       		if (m1.dayOfWeek < m2.dayOfWeek)
       			return -1;
       		else if (m1.dayOfWeek > m2.dayOfWeek)
       		    return 1;
       		// same day of week
       		if (m1.hourFrom < m2.hourFrom)
       			return -1;
       		else if (m1.hourFrom > m2.hourFrom)
       		    return 1;	      
       		// same day of week and same hour
       		if (m1.minFrom < m2.minFrom)
       			return -1;
       		else if (m1.minFrom > m2.minFrom)
       		    return 1;	 	       		
       		return 0;
		}
    }
}

package apps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import planningEntry.*;
import planningEntryCollection.*;
import resource.*;

public class FlightScheduleApp {
	public static void main(String[] args) throws Exception {
		FlightScheduleCollection flightScheduleCollection = new FlightScheduleCollection();
		BufferedReader bReader = new BufferedReader(
				new FileReader(new File("/data/FlightSchedule/FlightSchedule_1.txt")));
		String line = "";
		int cntLine = 0;
		String[] stringInfo = new String[] {};
		while ((line = bReader.readLine()) != null) {
			stringInfo[cntLine] = line;
			cntLine++;
			if (cntLine >= 13) {
				FlightSchedule<Resource> flightSchedule = flightScheduleCollection.addPlanningEntry(stringInfo);
				flightScheduleCollection.allocatePlanningEntry(flightSchedule, stringInfo);
				cntLine = 0;
			}
		}
		bReader.close();
	}
}
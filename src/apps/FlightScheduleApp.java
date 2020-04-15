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
				new FileReader(new File("/data/FlightSchedule/FlightSchedule_5.txt")));
		String line = "";
		int cntLine = 0;
		String stringInfo = "";
		while ((line = bReader.readLine()) != null) {
			stringInfo.concat(line + "\n");
			cntLine++;
			if (cntLine >= 13) {
				FlightSchedule<Resource> flightSchedule = flightScheduleCollection.addPlanningEntry(stringInfo);
				flightScheduleCollection.allocatePlanningEntry(flightSchedule.getPlanningEntryNumber(), stringInfo);
				cntLine = 0;
				stringInfo = "";
			}
		}
		bReader.close();
	}
}
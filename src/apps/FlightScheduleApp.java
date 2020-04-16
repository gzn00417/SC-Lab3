package apps;

import java.io.*;
import javax.swing.*;
import java.awt.*;

import board.*;
import planningEntry.*;
import planningEntryCollection.*;
import resource.*;

public class FlightScheduleApp {
	private static final int INPUT_ROWS_PER_UNIT = 13;
	private static final FlightScheduleCollection flightScheduleCollection = new FlightScheduleCollection();
	private static final FlightBoard board = new FlightBoard();

	public static void main(String[] args) throws Exception {
		readFile("data/FlightSchedule/FlightSchedule_3.txt");
		//main
		JFrame mainFrame = new JFrame("Flight Schedule");
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setLayout(new GridLayout(2, 3, 10, 5));
		mainFrame.setVisible(true);
		mainFrame.setSize(500, 400);
		//visualization
		JButton visualizeButton = new JButton("Visualize");
		Container visualizePane = mainFrame.getContentPane();
		visualizePane.add(visualizeButton);
		visualizeButton.addActionListener((e) -> visualization());
		//add planning entry
	}

	public static void readFile(String strFile) throws Exception {
		BufferedReader bReader = new BufferedReader(new FileReader(new File(strFile)));
		String line = "";
		int cntLine = 0;
		String stringInfo = "";
		while ((line = bReader.readLine()) != null) {
			if (line.equals(""))
				continue;
			stringInfo = stringInfo.concat(line + "\n");
			cntLine++;
			if (cntLine % INPUT_ROWS_PER_UNIT == 0) {
				FlightSchedule<Resource> flightSchedule = flightScheduleCollection.addPlanningEntry(stringInfo);
				if (flightSchedule != null)
					flightScheduleCollection.allocatePlanningEntry(flightSchedule.getPlanningEntryNumber(), stringInfo);
				stringInfo = "";
			}
		}
		bReader.close();
	}

	public static void visualization() {
		// frame
		JFrame visualizeOptionFrame = new JFrame("Visualization");
		visualizeOptionFrame.setLayout(new GridLayout(3, 1));
		visualizeOptionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		visualizeOptionFrame.setVisible(true);
		visualizeOptionFrame.setSize(500, 200);
		//current time
		Container currentTimeContainer = new Container();
		currentTimeContainer.setLayout(new FlowLayout());
		currentTimeContainer.add(new JLabel("Current Time (yyyy-MM-dd HH:mm)"));
		JTextField currentTimeText = new JTextField(16);
		currentTimeContainer.add(currentTimeText);
		visualizeOptionFrame.add(currentTimeContainer);
		//arrival
		Container visualizeArrivalContainer = new Container();
		visualizeArrivalContainer.setLayout(new FlowLayout());
		visualizeArrivalContainer.add(new JLabel("Arrival Airport"));
		JTextField arrivalAirportText = new JTextField(16);
		visualizeArrivalContainer.add(arrivalAirportText);
		JButton arrivalButton = new JButton("Show Arrival Flights");
		visualizeArrivalContainer.add(arrivalButton);
		visualizeOptionFrame.add(visualizeArrivalContainer);
		//leaving
		Container visualizeLeavingContainer = new Container();
		visualizeLeavingContainer.setLayout(new FlowLayout());
		visualizeLeavingContainer.add(new JLabel("Leaving Airport"));
		JTextField leavingAirportText = new JTextField(16);
		visualizeLeavingContainer.add(leavingAirportText);
		JButton leavingButton = new JButton("Show Leaving Flights");
		visualizeLeavingContainer.add(leavingButton);
		visualizeOptionFrame.add(visualizeLeavingContainer);
		//button
		arrivalButton.addActionListener((e_) -> {
			String strCurrentTime = currentTimeText.getText();
			String strAirport = arrivalAirportText.getText();
			board.visualize(flightScheduleCollection, strCurrentTime, strAirport, FlightBoard.ARRIVAL);
		});
		leavingButton.addActionListener((e_) -> {
			String strCurrentTime = currentTimeText.getText();
			String strAirport = leavingAirportText.getText();
			board.visualize(flightScheduleCollection, strCurrentTime, strAirport, FlightBoard.LEAVING);
		});
	}
}
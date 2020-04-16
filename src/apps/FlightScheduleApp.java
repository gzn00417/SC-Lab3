package apps;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

import board.*;
import planningEntry.*;
import planningEntryCollection.*;
import resource.*;

public class FlightScheduleApp {
	private static final int INPUT_ROWS_PER_UNIT = 13, LINE_WIDTH = 16;
	private static final FlightScheduleCollection flightScheduleCollection = new FlightScheduleCollection();
	private static final FlightBoard board = new FlightBoard();

	public static void main(String[] args) throws Exception {
		readFile("data/FlightSchedule/FlightSchedule_3.txt");
		// main
		JFrame mainFrame = new JFrame("Flight Schedule");
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setLayout(new GridLayout(2, 3, 10, 5));
		mainFrame.setVisible(true);
		mainFrame.setSize(500, 400);
		// visualization
		JButton visualizeButton = new JButton("Visualize");
		mainFrame.add(visualizeButton);
		visualizeButton.addActionListener((e) -> visualization());
		// add planning entry
		JButton addPlanningEntryButton = new JButton("Add Planning Entry");
		mainFrame.add(addPlanningEntryButton);
		addPlanningEntryButton.addActionListener((e) -> addPlanningEntry());
		// allocate resource
		// ask state
		// add / delete resources
		// add / delete locations
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
		// current time
		JPanel currentTimePanel = new JPanel();
		currentTimePanel.setLayout(new FlowLayout());
		currentTimePanel.add(new JLabel("Current Time (yyyy-MM-dd HH:mm):"));
		JTextField currentTimeText = new JTextField(LINE_WIDTH);
		currentTimePanel.add(currentTimeText);
		visualizeOptionFrame.add(currentTimePanel);
		// arrival
		JPanel visualizeArrivalPanel = new JPanel();
		visualizeArrivalPanel.setLayout(new FlowLayout());
		visualizeArrivalPanel.add(new JLabel("Arrival Airport:"));
		JTextField arrivalAirportText = new JTextField(LINE_WIDTH);
		visualizeArrivalPanel.add(arrivalAirportText);
		JButton arrivalButton = new JButton("Show Arrival Flights");
		visualizeArrivalPanel.add(arrivalButton);
		visualizeOptionFrame.add(visualizeArrivalPanel);
		// leaving
		JPanel visualizeLeavingPanel = new JPanel();
		visualizeLeavingPanel.setLayout(new FlowLayout());
		visualizeLeavingPanel.add(new JLabel("Leaving Airport:"));
		JTextField leavingAirportText = new JTextField(LINE_WIDTH);
		visualizeLeavingPanel.add(leavingAirportText);
		JButton leavingButton = new JButton("Show Leaving Flights");
		visualizeLeavingPanel.add(leavingButton);
		visualizeOptionFrame.add(visualizeLeavingPanel);
		// button
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

	public static void addPlanningEntry() {
		// frame
		JFrame addPlanningEntryFrame = new JFrame("Add Planning Entry");
		addPlanningEntryFrame.setLayout(new GridLayout(6, 1));
		addPlanningEntryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addPlanningEntryFrame.setVisible(true);
		addPlanningEntryFrame.setSize(600, 200);
		// add panels
		String[] panelsName = new String[] { "Planning Entry Number:", "Departure Airport:", "Arrival Airport:",
				"Departure Time (yyyy-MM-dd HH:mm):", "Arrival Time (yyyy-MM-dd HH:mm):" };
		List<JPanel> panelsList = new ArrayList<>();
		List<JTextField> textList = new ArrayList<>();
		for (int i = 0; i < panelsName.length; i++) {
			JPanel newPanel = new JPanel();
			panelsList.add(newPanel);
			newPanel.setLayout(new FlowLayout());
			newPanel.add(new JLabel(panelsName[i]));
			JTextField newText = new JTextField(LINE_WIDTH);
			textList.add(newText);
			newPanel.add(newText);
			addPlanningEntryFrame.add(newPanel);
		}
		JButton enterButton = new JButton("Enter");
		addPlanningEntryFrame.add(enterButton);
		enterButton.addActionListener((e) -> {
			List<String> gotString = new ArrayList<>();
			for (int i = 0; i < panelsName.length; i++) {
				gotString.add(textList.get(i).getText());
			}
			flightScheduleCollection.addPlanningEntry(gotString.get(0), gotString.get(1), gotString.get(2),
					gotString.get(3), gotString.get(4));
			addPlanningEntryFrame.dispose();
			JOptionPane.showMessageDialog(addPlanningEntryFrame, "Add Successfully", "Add Planning Entry",
					JOptionPane.PLAIN_MESSAGE);
		});
	}
}
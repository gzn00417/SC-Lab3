package board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import planningEntry.*;
import planningEntryCollection.*;
import resource.*;

public class FlightBoard extends JFrame {
    private static final long serialVersionUID = 1L;
    /**
     * choose flights within HOURS_RANGE before or later
     */
    private static final int HOURS_RANGE = 1;
    /**
     * visualization label of arrival
     */
    public static final int ARRIVAL = 1;
    /**
     * visualization label of leaving
     */
    public static final int LEAVING = -1;

    public FlightBoard() {
    }

    /**
     * show the all arrival flights in current time at the airport("" if client chooses every airport)
     * @param flightScheduleCollection
     * @param strCurrentTime
     * @param strAirportName chosen airport name ("" client it chooses every airport)
     * @param intFlightType ARRIVAL if visualize the arrival flights, LEAVING if visualize the leaving flights
     */
    public void visualize(FlightScheduleCollection flightScheduleCollection, String strCurrentTime,
            String strAirportName, int intFlightType) {
        Iterator<PlanningEntry<Resource>> iterator = flightScheduleCollection.getAllPlanningEntries().iterator();
        Vector<Vector<?>> vData = new Vector<>();
        Vector<String> vName = new Vector<>();
        String[] columnsNames = new String[] { "Time", "Entry Number", "Origin", "", "Terminal", "State" };
        for (String name : columnsNames)
            vName.add(name);
        while (iterator.hasNext()) {
            FlightSchedule<Resource> planningEntry = (FlightSchedule<Resource>) iterator.next();
            if (!strAirportName.isEmpty()) {
                if (intFlightType == FlightBoard.ARRIVAL) {
                    if (!planningEntry.getLocationTerminal().toLowerCase().equals(strAirportName.toLowerCase()))
                        continue;
                } else {
                    if (!planningEntry.getLocationOrigin().toLowerCase().equals(strAirportName.toLowerCase()))
                        continue;
                }
            }
            LocalDateTime currentTime = LocalDateTime.parse(strCurrentTime,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime scheduleTime = intFlightType == FlightBoard.ARRIVAL ? planningEntry.getTimeArrival()
                    : planningEntry.getTimeLeaving();
            if (scheduleTime.isBefore(currentTime.plusHours(HOURS_RANGE))
                    && scheduleTime.isAfter(currentTime.minusHours(HOURS_RANGE))) {
                String strScheduleTime = scheduleTime.toString().substring(11);
                String planningEntryNumber = planningEntry.getPlanningEntryNumber();
                String locationOrigin = planningEntry.getLocationOrigin();
                String locationTerminal = planningEntry.getLocationTerminal();
                String state = planningEntry.getState().getStrState();
                Vector<String> vRow = new Vector<>();
                vRow.add(strScheduleTime);
                vRow.add(planningEntryNumber);
                vRow.add(locationOrigin);
                vRow.add("-->");
                vRow.add(locationTerminal);
                vRow.add(state);
                vData.add((Vector<?>) vRow.clone());
            }
        }
        makeTable(vData, vName);
    }

    /**
     * make a table
     * @param vData
     * @param vName
     */
    private void makeTable(Vector<Vector<?>> vData, Vector<String> vName) {
        DefaultTableModel dataModel = new DefaultTableModel(vData, vName);
        JTable jtable = new JTable();
        jtable.setModel(dataModel);
        JScrollPane jscrollpane = new JScrollPane(jtable);
        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtable.getColumn("").setPreferredWidth(0);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        jtable.setDefaultRenderer(Object.class, r);
        setTitle("Arriving Flights");
        setBounds(100, 100, 100, 100);
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(jscrollpane, BorderLayout.CENTER);
    }
}
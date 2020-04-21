package board;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import planningEntry.*;
import planningEntryCollection.*;
import resource.*;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class Board {
    protected final JFrame frame = new JFrame();
    protected final PlanningEntryCollection planningEntryCollection;

    public Board(PlanningEntryCollection planningEntryCollection) {
        this.planningEntryCollection = planningEntryCollection;
    }

    /**
     * make a table
     * @param vData
     * @param vName
     */
    protected void makeTable(Vector<Vector<?>> vData, Vector<String> vName) {
        DefaultTableModel dataModel = new DefaultTableModel(vData, vName);
        JTable jtable = new JTable();
        jtable.setModel(dataModel);
        JScrollPane jscrollpane = new JScrollPane(jtable);
        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtable.getColumn("").setPreferredWidth(0);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        jtable.setDefaultRenderer(Object.class, r);
        frame.setTitle("Arriving Flights");
        frame.setBounds(100, 100, 100, 100);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(jscrollpane, BorderLayout.CENTER);
    }

    public Iterator<PlanningEntry<Resource>> iterator() {
        return planningEntryCollection.getAllPlanningEntries().iterator();
    }
}
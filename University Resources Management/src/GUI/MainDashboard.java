package GUI;

import Main.App;
import Model.*;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Locale;

public class MainDashboard extends JFrame {
    private JPanel panel1;
    private JButton dashboardButton;
    private JButton schedulesButton;
    private JButton roomsButton;
    private JButton Teachers;
    private JButton addRoomButton;
    private JButton deleteRoomButton;
    private JButton updateRoomButton;
    private JTable table1;
    private JButton schduleEntryButton;
    private JButton batchButton;
    private JPanel MainContentPanel;
    private JPanel TeachersPanel;
    private JPanel RoomsPanel;
    private JPanel SchedulePanel;
    private JPanel MainDashBoard;
    private JButton addTeacherButton;
    private JButton updateTeacherButton;
    private JButton deleteTeacherButton;
    private JComboBox comboBox2;
    private JTextField assignSchduleAnIdTextField;
    private JButton scheduleEntryButton;
    private JScrollPane scrollPane1;
    private JButton clashesButton;
    private JTextField searchTeacherByIdTextField;
    private JTable table2;
    private JScrollPane TeachersList;
    private JPanel TeacherSUbPanel;
    private JPanel ActionButtonPanel;
    private JPanel SearchBarPanel;
    private JScrollPane RoomsList;
    private JTable TableRoom;
    private JTextField RoomSearchTextField;
    private JPanel ActionButtonRoomPanel;
    private JPanel RoomSubPanel;
    private JPanel statsPanel;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton checkAvailabilityButton;
    private JTable ClashesTable;
    private JPanel BatchesPanel;
    private JPanel BatchSubPanel;
    private JPanel BatchActionButtonsPanel;
    private JPanel BatchSearchBarPanel;
    private JScrollPane BatchPanel;
    private JButton addBatchButton;
    private JButton updateBatchButton;
    private JButton deleteBatchButton;
    private JTextField searchBarBatch;
    private JTable BatchTable;
    private JButton updateScheduleButton;
    private JButton deleteSchduleButton;
    private JLabel totalBatchValue;
    private JLabel totalRoomValue;
    private JLabel totalTeacherValue;
    private JLabel TeacherListLabel;
    private JComboBox batchComboBox;
    private JComboBox roomComboBox;
    private JSpinner spinnerStarttime;
    private JSpinner spinnerEndtime;
    private JComboBox dayComboBOx;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JComboBox dayBox;
    private JLabel status;
    private JButton searchTRoomButton;
    private JButton searchBatchButton;
    private JButton searchTeacherButton;
    private JLabel numberOfClashes;
    private JButton logoutButton;
    private JPanel titlePanel;
    private JLabel userName;
    private JLabel roleLabel;

    //getters
    JTextField getSearchTeacherByIdTextFieldText() {
        return this.searchTeacherByIdTextField;
    }

    JTextField getSearchBarBatchText() {
        return this.searchBarBatch;
    }

    JTextField getRoomSearchTextFieldText() {
        return this.RoomSearchTextField;
    }

    public MainDashboard() {
        // 1. Mandatory window setup
        $$$setupUI$$$();

        this.setContentPane(panel1); // Connects your GUI design to this window
        this.setTitle("University Resource Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closes app when 'X' is clicked
        this.setSize(1200, 800); // Set a good starting size
        this.setLocationRelativeTo(null); // Centers the window on your screen

        //Table1------------------------
        table1.setIntercellSpacing(new Dimension(0, 0));

        scrollPane1.setBorder(BorderFactory.createLineBorder(Color.decode("#E2E8F0")));
        // 2. Style the Table Header
        table1.getTableHeader().setBackground(Color.decode("#1E293B"));
        table1.getTableHeader().setForeground(Color.WHITE);
        table1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        // 3. Style the Table Rows
        table1.setRowHeight(35);
        table1.setSelectionBackground(Color.decode("#DBEAFE")); // Soft blue highlight
        table1.setSelectionForeground(Color.decode("#1E293B"));
        //------------------------------------------
        //Table2------------------------------------

        table2.setIntercellSpacing(new Dimension(0, 0));

        TeachersList.setBorder(BorderFactory.createLineBorder(Color.decode("#E2E8F0")));
        table2.getTableHeader().setBackground(Color.decode("#1E293B"));
        table2.getTableHeader().setForeground(Color.WHITE);
        table2.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table2.setRowHeight(35);
        table2.setSelectionBackground(Color.decode("#DBEAFE")); // Soft blue highlight
        table2.setSelectionForeground(Color.decode("#1E293B"));

        //-------------------------------------------
        //Table3-------------------------------------
        TableRoom.setIntercellSpacing(new Dimension(0, 0));

        TableRoom.getTableHeader().setBackground(Color.decode("#1E293B"));
        TableRoom.getTableHeader().setForeground(Color.WHITE);
        TableRoom.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        TableRoom.setRowHeight(35);
        TableRoom.setSelectionBackground(Color.decode("#DBEAFE")); // Soft blue highlight
        TableRoom.setSelectionForeground(Color.decode("#1E293B"));
        RoomsList.setBorder(BorderFactory.createLineBorder(Color.decode("#E2E8F0")));

        //------------------------------------
        //Table4------------------------------

        ClashesTable.setIntercellSpacing(new Dimension(0, 0));
        ClashesTable.getTableHeader().setBackground(Color.decode("#1E293B"));
        ClashesTable.getTableHeader().setForeground(Color.WHITE);
        ClashesTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        ClashesTable.setRowHeight(35);
        ClashesTable.setSelectionBackground(Color.decode("#DBEAFE")); // Soft blue highlight
        ClashesTable.setSelectionForeground(Color.decode("#1E293B"));
        //-------------------------------------------
        //Table5---------------------------------------
        BatchTable.setIntercellSpacing(new Dimension(0, 0));
        BatchTable.getTableHeader().setForeground(Color.WHITE);
        BatchTable.getTableHeader().setBackground(Color.decode("#1E293B"));
        BatchTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        BatchPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#E2E8F0")));
        BatchTable.setRowHeight(35);
        BatchTable.setSelectionBackground(Color.decode("#DBEAFE"));
        BatchTable.setSelectionForeground(Color.decode("#1E293B"));

        //Buttons Action Listener
        Teachers.addActionListener(e -> switchPanel("TeachersPanel"));
        roomsButton.addActionListener(e -> switchPanel("RoomsPanel"));
        schedulesButton.addActionListener(e -> switchPanel("SchedulePanel"));
        dashboardButton.addActionListener(e -> switchPanel("MainDashBoard"));
        batchButton.addActionListener(e -> switchPanel("BatchesPanel"));

        //Listeners of CRUD operation Buttons
        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTeacherDialog addTeacherDialog = new AddTeacherDialog();
                addTeacherDialog.setMinimumSize(new Dimension(550, 300));
                addTeacherDialog.setLocationRelativeTo(null);
                addTeacherDialog.setModal(true);
                addTeacherDialog.setVisible(true);
            }
        });
        deleteTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteTeacherDialog deleteTeacherDialog = new DeleteTeacherDialog();
                deleteTeacherDialog.setMinimumSize(new Dimension(600, 300));
                deleteTeacherDialog.setLocationRelativeTo(null);
                deleteTeacherDialog.setModal(true);
                deleteTeacherDialog.setVisible(true);
            }
        });
        updateTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateTeacherDialog updateTeacherDialog = new UpdateTeacherDialog();
                updateTeacherDialog.setMinimumSize(new Dimension(700, 397));
                updateTeacherDialog.setLocationRelativeTo(null);
                updateTeacherDialog.setModal(true);
                updateTeacherDialog.setVisible(true);
            }
        });

        addRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRoomDialog addRoomDialog = new AddRoomDialog();
                addRoomDialog.setMinimumSize(new Dimension(550, 300));
                addRoomDialog.setLocationRelativeTo(null);
                addRoomDialog.setModal(true);
                addRoomDialog.setVisible(true);
            }
        });
        deleteRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteRoomDialog deleteRoomDialog = new DeleteRoomDialog();
                deleteRoomDialog.setMinimumSize(new Dimension(600, 266));
                deleteRoomDialog.setLocationRelativeTo(null);
                deleteRoomDialog.setModal(true);
                deleteRoomDialog.setVisible(true);
            }
        });
        updateRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateRoomDialog updateRoomDialog = new UpdateRoomDialog();
                updateRoomDialog.setMinimumSize(new Dimension(656, 397));
                updateRoomDialog.setLocationRelativeTo(null);
                updateRoomDialog.setModal(true);
                updateRoomDialog.setVisible(true);
            }
        });

        addBatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBatchDialog addBatchDialog = new AddBatchDialog();
                addBatchDialog.setMinimumSize(new Dimension(550, 300));
                addBatchDialog.setLocationRelativeTo(null);
                addBatchDialog.setModal(true);
                addBatchDialog.setVisible(true);
            }
        });
        deleteBatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteBatchDialog deleteBatchDialog = new DeleteBatchDialog();
                deleteBatchDialog.setMinimumSize(new Dimension(600, 300));
                deleteBatchDialog.setLocationRelativeTo(null);
                deleteBatchDialog.setModal(true);
                deleteBatchDialog.setVisible(true);
            }
        });
        updateBatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateBatchDialog updateBatchDialog = new UpdateBatchDialog();
                updateBatchDialog.setMinimumSize(new Dimension(656, 397));
                updateBatchDialog.setLocationRelativeTo(null);
                updateBatchDialog.setModal(true);
                updateBatchDialog.setVisible(true);
            }
        });

        deleteSchduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteSchedule deleteScheduleDialog = new DeleteSchedule();
                deleteScheduleDialog.setMinimumSize(new Dimension(600, 266));
                deleteScheduleDialog.setLocationRelativeTo(null);
                deleteScheduleDialog.setModal(true);
                deleteScheduleDialog.setVisible(true);
            }
        });
        updateScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateSchedule updateScheduleDialog = new UpdateSchedule();
                updateScheduleDialog.setMinimumSize(new Dimension(656, 397));
                updateScheduleDialog.setLocationRelativeTo(null);
                updateScheduleDialog.setModal(true);
                updateScheduleDialog.setVisible(true);
            }
        });

        //Styling of Buttons
        Styling.styleButton(Teachers);
        Styling.styleButton(roomsButton);
        Styling.styleButton(schedulesButton);
        Styling.styleButton(dashboardButton);
        Styling.styleButton(clashesButton, "#DC2626", "#B91C1C");
        Styling.styleButton(schduleEntryButton);
        Styling.styleButton(scheduleEntryButton);
        Styling.styleButton(addTeacherButton, "#059669", "#047857");
        Styling.styleButton(deleteTeacherButton, "#DC2626", "#B91C1C");
        Styling.styleButton(updateTeacherButton, "#2563EB", "#1D4ED8");
        Styling.styleButton(addBatchButton, "#059669", "#047857");
        Styling.styleButton(deleteBatchButton, "#DC2626", "#B91C1C");
        Styling.styleButton(updateBatchButton, "#2563EB", "#1D4ED8");
        Styling.styleButton(deleteSchduleButton, "#DC2626", "#B91C1C");
        Styling.styleButton(updateScheduleButton, "#2563EB", "#1D4ED8");
        Styling.styleButton(addRoomButton, "#059669", "#047857");
        Styling.styleButton(deleteRoomButton, "#DC2626", "#B91C1C");
        Styling.styleButton(updateRoomButton, "#2563EB", "#1D4ED8");
        Styling.styleButton(checkAvailabilityButton);
        Styling.styleButton(searchBatchButton);
        Styling.styleButton(searchTeacherButton);
        Styling.styleButton(searchTRoomButton);
        Styling.styleButton(batchButton);

        //adding tables data
        Schedule schedule = new Schedule();
        Room room = new Room();
        Teacher teacher = new Teacher();
        Batch batch = new Batch();
        ClashesChecker clashesChecker = new ClashesChecker();
        clashesChecker.setDashBoardTable(ClashesTable);

        schedulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                schedule.showScheduleDataInGUITable(table1);
            }
        });
        roomsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                room.showRoomDataInGUITable(TableRoom);
            }
        });
        Teachers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacher.showTeachersDataInGUITable(table2);
            }
        });
        batchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                batch.showBatchDataInGUITable(BatchTable);
            }
        });
        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clashesChecker.setDashBoardTable(ClashesTable);
                room.totalRooms(totalRoomValue);
                teacher.totalTeachers(totalTeacherValue);
                batch.totalBatch(totalBatchValue);
            }
        });

        //setting labels text
        batch.totalBatch(totalBatchValue);
        room.totalRooms(totalRoomValue);
        teacher.totalTeachers(totalTeacherValue);

        //adding list to jcomboBoxes
        schedule.addScheduleComboBoxValues(comboBox2, batchComboBox, roomComboBox);

        //time Input
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spinnerStarttime.setModel(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerStarttime, "HH:mm:ss");
        spinnerStarttime.setEditor(editor);

        SpinnerDateModel endTimeModel = new SpinnerDateModel();
        spinnerEndtime.setModel(endTimeModel);
        JSpinner.DateEditor editorEndTime = new JSpinner.DateEditor(spinnerEndtime, "HH:mm:ss");
        spinnerEndtime.setEditor(editorEndTime);

        SpinnerDateModel resourceStartTimeModel = new SpinnerDateModel();
        spinner1.setModel(resourceStartTimeModel);
        JSpinner.DateEditor editorResourceStartTime = new JSpinner.DateEditor(spinner1, "HH:mm:ss");
        spinner1.setEditor(editorResourceStartTime);

        SpinnerDateModel resourceEndTimeModel = new SpinnerDateModel();
        spinner2.setModel(resourceEndTimeModel);
        JSpinner.DateEditor editorResourceEndTime = new JSpinner.DateEditor(spinner2, "HH:mm:ss");
        spinner2.setEditor(editorResourceEndTime);

        scheduleEntryButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList list;
                if (room.roomManage((String) batchComboBox.getSelectedItem(), (String) roomComboBox.getSelectedItem())) {
                    JOptionPane.showMessageDialog(null, "Room not suitable! Batch Strength Exceeding Room Capacity");
                    return;
                }
                ClashesChecker clashesChecker = new ClashesChecker();
                if (clashesChecker.batchClashWarning(spinnerStarttime, spinnerEndtime, dayComboBOx, batchComboBox)) {
                    JOptionPane.showMessageDialog(null, "Clash! Batch is already occupied");
                    list = schedule.freeBatchSlotList(dayComboBOx, batchComboBox);
                    if (list.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No Batch Free For Selected Day");
                    } else {
                        FreeSlotsRepresenter freeSlotsRepresenter = new FreeSlotsRepresenter(list);
                    }
                } else if (clashesChecker.teacherClashWarning(spinnerStarttime, spinnerEndtime, dayComboBOx, comboBox2)) {
                    JOptionPane.showMessageDialog(null, "Clash! Teacher is not available in this time period");
                    list = schedule.freeTeacherSlotList(dayComboBOx, comboBox2, "teacherId");
                    if (list.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No Teacher Free For Selected Day");
                    } else {
                        FreeSlotsRepresenter freeSlotsRepresenter = new FreeSlotsRepresenter(list);
                    }
                } else if (clashesChecker.roomClashWarning(spinnerStarttime, spinnerEndtime, dayComboBOx, roomComboBox)) {
                    JOptionPane.showMessageDialog(null, "Clash! Room is already occupied");
                    list = schedule.freeRoomSlotList(dayComboBOx, roomComboBox, "roomId");
                    if (list.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No Room Free For Selected Day");
                    } else {
                        FreeSlotsRepresenter freeSlotsRepresenter = new FreeSlotsRepresenter(list);
                    }
                } else {
                    schedule.addSchedule(assignSchduleAnIdTextField, comboBox2, batchComboBox, roomComboBox, dayComboBOx, spinnerStarttime, spinnerEndtime);
                }
            }
        });
        checkAvailabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClashesChecker clashesChecker = new ClashesChecker();
                clashesChecker.checkResources(comboBox1, spinner2, spinner1, dayBox, textField1, status);
            }
        });

        searchTRoomButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (RoomSearchTextField.getText().isEmpty() || RoomSearchTextField.getText().equalsIgnoreCase("Search Room by Id")) {
                    JOptionPane.showMessageDialog(null, "Please Fill the required field");
                    return;
                }
                ShowOnSearchRoomDialog roomDialog = new ShowOnSearchRoomDialog(RoomSearchTextField);
                if (!roomDialog.shouldShowRoom) {
                    System.out.println("Invalid ID");
                    return;
                }

                roomDialog.setSize(new Dimension(300, 200));
                roomDialog.setLocationRelativeTo(null);
                roomDialog.setModal(true);
                roomDialog.setVisible(true);
            }
        });

        searchTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchTeacherByIdTextField.getText().isEmpty() || searchTeacherByIdTextField.getText().equalsIgnoreCase("Search Teacher By Id")) {
                    JOptionPane.showMessageDialog(null, "Please Fill the required field");
                    return;
                }

                ShowOnSearchTeacherForm teacherDialog = new ShowOnSearchTeacherForm(searchTeacherByIdTextField);
                if (!teacherDialog.shouldShowTeacher) {
                    System.out.println("Invalid ID");
                    return;
                }
                teacherDialog.setSize(new Dimension(350, 300));
                teacherDialog.setLocationRelativeTo(null);
                teacherDialog.setModal(true);
                teacherDialog.setVisible(true);
            }
        });

        searchBatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchBarBatch.getText().isEmpty() || searchBarBatch.getText().equalsIgnoreCase("Search Batch by Id")) {
                    JOptionPane.showMessageDialog(null, "Please Fill the required field");
                    return;
                }
                ShowOnSearchBatchDialog batchDialog = new ShowOnSearchBatchDialog(searchBarBatch);
                if (!batchDialog.shouldShow) {
                    System.out.println("Invalid ID");
                    return;
                }
                batchDialog.setSize(new Dimension(300, 200));
                batchDialog.setLocationRelativeTo(null);
                batchDialog.setModal(true);
                batchDialog.setVisible(true);
            }
        });
        clashesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BatchAndTeacherWiseTimeTable GetTable = new BatchAndTeacherWiseTimeTable();
                GetTable.setSize(new Dimension(600, 400));
                GetTable.setLocationRelativeTo(null);
                GetTable.setModal(true);
                GetTable.setVisible(true);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                App.main(new String[]{});
            }
        });

        userName.setText(Login.NameUser);
    }


    private void switchPanel(String cardName) {
        System.out.println("Switching to: " + cardName); // Check your console when you click!
        CardLayout cl = (CardLayout) MainContentPanel.getLayout();
        cl.show(MainContentPanel, cardName);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        MainContentPanel = new JPanel();
        MainContentPanel.setLayout(new CardLayout());

        schduleEntryButton = new JButton();
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        panel1.setBackground(new Color(-2761236));
        panel1.setForeground(new Color(-16777216));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 25));
        panel2.setBackground(new Color(-14800581));
        panel2.setPreferredSize(new Dimension(200, -1));
        panel1.add(panel2, BorderLayout.WEST);
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Sidebar", TitledBorder.CENTER, TitledBorder.BELOW_TOP, this.$$$getFont$$$(null, Font.BOLD, 20, panel2.getFont()), new Color(-460036)));
        dashboardButton = new JButton();
        dashboardButton.setBackground(new Color(-12877066));
        dashboardButton.setBorderPainted(false);
        dashboardButton.setFocusPainted(false);
        dashboardButton.setFocusable(true);
        Font dashboardButtonFont = this.$$$getFont$$$(null, Font.BOLD, 15, dashboardButton.getFont());
        if (dashboardButtonFont != null) dashboardButton.setFont(dashboardButtonFont);
        dashboardButton.setForeground(new Color(-460036));
        dashboardButton.setPreferredSize(new Dimension(180, 50));
        dashboardButton.setSelected(false);
        dashboardButton.setText("Dashboard");
        panel2.add(dashboardButton);
        schedulesButton = new JButton();
        schedulesButton.setBackground(new Color(-12877066));
        schedulesButton.setBorderPainted(false);
        schedulesButton.setFocusPainted(false);
        Font schedulesButtonFont = this.$$$getFont$$$(null, Font.BOLD, 15, schedulesButton.getFont());
        if (schedulesButtonFont != null) schedulesButton.setFont(schedulesButtonFont);
        schedulesButton.setForeground(new Color(-460036));
        schedulesButton.setPreferredSize(new Dimension(180, 50));
        schedulesButton.setText("Schedules");
        panel2.add(schedulesButton);
        roomsButton = new JButton();
        roomsButton.setBackground(new Color(-12877066));
        roomsButton.setBorderPainted(false);
        roomsButton.setFocusPainted(false);
        Font roomsButtonFont = this.$$$getFont$$$(null, Font.BOLD, 15, roomsButton.getFont());
        if (roomsButtonFont != null) roomsButton.setFont(roomsButtonFont);
        roomsButton.setForeground(new Color(-460036));
        roomsButton.setPreferredSize(new Dimension(180, 50));
        roomsButton.setText("Rooms");
        panel2.add(roomsButton);
        Teachers = new JButton();
        Teachers.setBackground(new Color(-12877066));
        Teachers.setBorderPainted(false);
        Teachers.setFocusPainted(false);
        Font TeachersFont = this.$$$getFont$$$(null, Font.BOLD, 15, Teachers.getFont());
        if (TeachersFont != null) Teachers.setFont(TeachersFont);
        Teachers.setForeground(new Color(-460036));
        Teachers.setPreferredSize(new Dimension(180, 50));
        Teachers.setText("Teachers");
        panel2.add(Teachers);
        batchButton = new JButton();
        batchButton.setBackground(new Color(-12877066));
        batchButton.setBorderPainted(false);
        batchButton.setFocusPainted(false);
        Font batchButtonFont = this.$$$getFont$$$(null, Font.BOLD, 15, batchButton.getFont());
        if (batchButtonFont != null) batchButton.setFont(batchButtonFont);
        batchButton.setForeground(new Color(-460036));
        batchButton.setPreferredSize(new Dimension(180, 50));
        batchButton.setText("Batch");
        panel2.add(batchButton);
        logoutButton = new JButton();
        logoutButton.setBackground(new Color(-12877066));
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        Font logoutButtonFont = this.$$$getFont$$$(null, Font.BOLD, 15, logoutButton.getFont());
        if (logoutButtonFont != null) logoutButton.setFont(logoutButtonFont);
        logoutButton.setForeground(new Color(-460036));
        logoutButton.setPreferredSize(new Dimension(180, 50));
        logoutButton.setText("Logout");
        panel2.add(logoutButton);
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 18, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-1));
        label1.setText("Logged In As:");
        panel2.add(label1);
        userName = new JLabel();
        Font userNameFont = this.$$$getFont$$$(null, Font.ITALIC, 18, userName.getFont());
        if (userNameFont != null) userName.setFont(userNameFont);
        userName.setForeground(new Color(-1));
        userName.setText("[Name]");
        panel2.add(userName);
        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout(0, 0));
        titlePanel.setBackground(new Color(-14800581));
        titlePanel.setName("University");
        titlePanel.setPreferredSize(new Dimension(-1, 60));
        panel1.add(titlePanel, BorderLayout.NORTH);
        titlePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-13418155)), "", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, -1, -1, titlePanel.getFont()), null));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, Font.BOLD, 35, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-1));
        label2.setHorizontalAlignment(0);
        label2.setText("UNIVERSITY RESOURCES MANAGER");
        titlePanel.add(label2, BorderLayout.CENTER);
        MainContentPanel.setLayout(new CardLayout(0, 0));
        MainContentPanel.setBackground(new Color(-15788246));
        panel1.add(MainContentPanel, BorderLayout.CENTER);
        MainContentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        MainDashBoard = new JPanel();
        MainDashBoard.setLayout(new BorderLayout(0, 0));
        MainDashBoard.setAutoscrolls(false);
        MainDashBoard.setBackground(new Color(-920071));
        MainDashBoard.setForeground(new Color(-3025959));
        MainContentPanel.add(MainDashBoard, "MainDashBoard");
        MainDashBoard.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Main Dashboard", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, 20, MainDashBoard.getFont()), new Color(-14800581)));
        statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 15, 0), 20, -1));
        statsPanel.setBackground(new Color(-1));
        statsPanel.setPreferredSize(new Dimension(-1, 90));
        MainDashBoard.add(statsPanel, BorderLayout.NORTH);
        statsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-1906448)), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel3.setAlignmentY(0.5f);
        panel3.setBackground(new Color(-1));
        statsPanel.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-1906448)), "Total Teachers", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, 14, panel3.getFont()), new Color(-10193781)));
        totalTeacherValue = new JLabel();
        Font totalTeacherValueFont = this.$$$getFont$$$(null, Font.BOLD, 25, totalTeacherValue.getFont());
        if (totalTeacherValueFont != null) totalTeacherValue.setFont(totalTeacherValueFont);
        totalTeacherValue.setForeground(new Color(-12877066));
        totalTeacherValue.setText("value");
        panel3.add(totalTeacherValue);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel4.setBackground(new Color(-1));
        panel4.setToolTipText("ToolTip");
        statsPanel.add(panel4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Total Clashes", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, 14, panel4.getFont()), new Color(-6743269)));
        numberOfClashes = new JLabel();
        Font numberOfClashesFont = this.$$$getFont$$$(null, Font.BOLD, 25, numberOfClashes.getFont());
        if (numberOfClashesFont != null) numberOfClashes.setFont(numberOfClashesFont);
        numberOfClashes.setForeground(new Color(-1096636));
        numberOfClashes.setText("No Clash");
        panel4.add(numberOfClashes);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel5.setBackground(new Color(-1));
        statsPanel.add(panel5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-1906448)), "Total Rooms", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, 14, panel5.getFont()), new Color(-10193781)));
        totalRoomValue = new JLabel();
        Font totalRoomValueFont = this.$$$getFont$$$(null, Font.BOLD, 25, totalRoomValue.getFont());
        if (totalRoomValueFont != null) totalRoomValue.setFont(totalRoomValueFont);
        totalRoomValue.setForeground(new Color(-12877066));
        totalRoomValue.setText("value");
        panel5.add(totalRoomValue);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel6.setBackground(new Color(-1));
        statsPanel.add(panel6, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel6.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-1906448)), "Total Batches", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, 14, panel6.getFont()), new Color(-10193781)));
        totalBatchValue = new JLabel();
        Font totalBatchValueFont = this.$$$getFont$$$(null, Font.BOLD, 25, totalBatchValue.getFont());
        if (totalBatchValueFont != null) totalBatchValue.setFont(totalBatchValueFont);
        totalBatchValue.setForeground(new Color(-12877066));
        totalBatchValue.setText("value");
        panel6.add(totalBatchValue);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel7.setAlignmentX(0.5f);
        panel7.setAutoscrolls(true);
        panel7.setBackground(new Color(-920071));
        panel7.setPreferredSize(new Dimension(1200, 130));
        MainDashBoard.add(panel7, BorderLayout.SOUTH);
        panel7.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-1906448)), "Manual Resources Checker", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, 25, panel7.getFont()), new Color(-14800581)));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new BorderLayout(15, 0));
        panel8.setBackground(new Color(-920071));
        panel8.setPreferredSize(new Dimension(260, 34));
        panel7.add(panel8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel8.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label3 = new JLabel();
        label3.setForeground(new Color(-13418155));
        label3.setText("Resource:");
        panel8.add(label3, BorderLayout.WEST);
        comboBox1 = new JComboBox();
        comboBox1.setBackground(new Color(-920071));
        comboBox1.setForeground(new Color(-13418155));
        comboBox1.setMinimumSize(new Dimension(1, 34));
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Teacher");
        defaultComboBoxModel1.addElement("Room");
        defaultComboBoxModel1.addElement("Batch");
        comboBox1.setModel(defaultComboBoxModel1);
        comboBox1.setPreferredSize(new Dimension(200, 34));
        panel8.add(comboBox1, BorderLayout.CENTER);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new BorderLayout(10, 0));
        panel9.setBackground(new Color(-920071));
        panel7.add(panel9, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel9.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label4 = new JLabel();
        label4.setForeground(new Color(-13418155));
        label4.setText("Start Time:");
        panel9.add(label4, BorderLayout.WEST);
        spinner2 = new JSpinner();
        spinner2.setBackground(new Color(-920071));
        spinner2.setForeground(new Color(-13418155));
        spinner2.setPreferredSize(new Dimension(180, 34));
        panel9.add(spinner2, BorderLayout.CENTER);
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new BorderLayout(5, 0));
        panel10.setBackground(new Color(-920071));
        panel7.add(panel10, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel10.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label5 = new JLabel();
        label5.setForeground(new Color(-13418155));
        label5.setText("Resource Id:");
        panel10.add(label5, BorderLayout.WEST);
        textField1 = new JTextField();
        textField1.setBackground(new Color(-920071));
        textField1.setForeground(new Color(-14800581));
        textField1.setPreferredSize(new Dimension(180, 34));
        panel10.add(textField1, BorderLayout.CENTER);
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new BorderLayout(15, 0));
        panel11.setBackground(new Color(-920071));
        panel7.add(panel11, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel11.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label6 = new JLabel();
        label6.setForeground(new Color(-13418155));
        label6.setText("End Time:");
        panel11.add(label6, BorderLayout.WEST);
        spinner1 = new JSpinner();
        spinner1.setBackground(new Color(-920071));
        spinner1.setForeground(new Color(-13418155));
        spinner1.setPreferredSize(new Dimension(180, 34));
        panel11.add(spinner1, BorderLayout.CENTER);
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new BorderLayout(49, 0));
        panel12.setBackground(new Color(-920071));
        panel7.add(panel12, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel12.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label7 = new JLabel();
        label7.setForeground(new Color(-13418155));
        label7.setText("Day:");
        panel12.add(label7, BorderLayout.WEST);
        dayBox = new JComboBox();
        dayBox.setBackground(new Color(-920071));
        dayBox.setForeground(new Color(-13418155));
        dayBox.setMinimumSize(new Dimension(1, 34));
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Monday");
        defaultComboBoxModel2.addElement("Tuesday");
        defaultComboBoxModel2.addElement("Wednesday");
        defaultComboBoxModel2.addElement("Thursday");
        defaultComboBoxModel2.addElement("Friday");
        dayBox.setModel(defaultComboBoxModel2);
        dayBox.setPreferredSize(new Dimension(200, 34));
        panel12.add(dayBox, BorderLayout.CENTER);
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panel13.setBackground(new Color(-920071));
        panel7.add(panel13, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 7, false));
        panel13.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        checkAvailabilityButton = new JButton();
        checkAvailabilityButton.setAlignmentX(0.0f);
        checkAvailabilityButton.setBackground(new Color(-12877066));
        checkAvailabilityButton.setBorderPainted(false);
        checkAvailabilityButton.setFocusPainted(false);
        checkAvailabilityButton.setFocusable(true);
        checkAvailabilityButton.setForeground(new Color(-460036));
        checkAvailabilityButton.setHideActionText(false);
        checkAvailabilityButton.setHorizontalAlignment(0);
        checkAvailabilityButton.setMargin(new Insets(0, 0, 0, 0));
        checkAvailabilityButton.setPreferredSize(new Dimension(180, 34));
        checkAvailabilityButton.setText("Check Availability");
        panel13.add(checkAvailabilityButton);
        status = new JLabel();
        Font statusFont = this.$$$getFont$$$(null, -1, 17, status.getFont());
        if (statusFont != null) status.setFont(statusFont);
        status.setForeground(new Color(-13418155));
        status.setText("[Status]");
        panel13.add(status);
        final JScrollPane scrollPane2 = new JScrollPane();
        MainDashBoard.add(scrollPane2, BorderLayout.CENTER);
        ClashesTable = new JTable();
        ClashesTable.setBackground(new Color(-1));
        ClashesTable.setForeground(new Color(-14800581));
        ClashesTable.setGridColor(new Color(-920071));
        scrollPane2.setViewportView(ClashesTable);
        RoomsPanel = new JPanel();
        RoomsPanel.setLayout(new BorderLayout(0, 0));
        RoomsPanel.setBackground(new Color(-920071));
        MainContentPanel.add(RoomsPanel, "RoomsPanel");
        RoomsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Rooms Menu", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, 20, RoomsPanel.getFont()), new Color(-14800581)));
        RoomSubPanel = new JPanel();
        RoomSubPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        RoomsPanel.add(RoomSubPanel, BorderLayout.CENTER);
        RoomSubPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        ActionButtonRoomPanel = new JPanel();
        ActionButtonRoomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        RoomSubPanel.add(ActionButtonRoomPanel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ActionButtonRoomPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        addRoomButton = new JButton();
        addRoomButton.setActionCommand("Add Room");
        addRoomButton.setBackground(new Color(-15681151));
        addRoomButton.setBorderPainted(false);
        addRoomButton.setFocusPainted(false);
        addRoomButton.setForeground(new Color(-1));
        addRoomButton.setPreferredSize(new Dimension(180, 40));
        addRoomButton.setText("Add Room");
        ActionButtonRoomPanel.add(addRoomButton);
        updateRoomButton = new JButton();
        updateRoomButton.setActionCommand("Update Room");
        updateRoomButton.setBackground(new Color(-12877066));
        updateRoomButton.setBorderPainted(false);
        updateRoomButton.setFocusPainted(false);
        updateRoomButton.setForeground(new Color(-1));
        updateRoomButton.setPreferredSize(new Dimension(180, 40));
        updateRoomButton.setText("Update Room");
        ActionButtonRoomPanel.add(updateRoomButton);
        deleteRoomButton = new JButton();
        deleteRoomButton.setActionCommand("Delete Room");
        deleteRoomButton.setBackground(new Color(-1096636));
        deleteRoomButton.setBorderPainted(false);
        deleteRoomButton.setFocusPainted(false);
        deleteRoomButton.setForeground(new Color(-1));
        deleteRoomButton.setPreferredSize(new Dimension(180, 40));
        deleteRoomButton.setText("Delete Room");
        ActionButtonRoomPanel.add(deleteRoomButton);
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new BorderLayout(0, 0));
        panel14.setBackground(new Color(-920071));
        RoomSubPanel.add(panel14, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel14.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(-1906448)));
        searchTRoomButton = new JButton();
        searchTRoomButton.setBackground(new Color(-12877066));
        searchTRoomButton.setBorderPainted(false);
        searchTRoomButton.setFocusPainted(false);
        searchTRoomButton.setForeground(new Color(-1));
        searchTRoomButton.setHideActionText(false);
        searchTRoomButton.setPreferredSize(new Dimension(150, 34));
        searchTRoomButton.setText("Search Room");
        panel14.add(searchTRoomButton, BorderLayout.EAST);
        RoomSearchTextField = new JTextField();
        RoomSearchTextField.setBackground(new Color(-460036));
        RoomSearchTextField.setCaretColor(new Color(-4473925));
        Font RoomSearchTextFieldFont = this.$$$getFont$$$(null, -1, 14, RoomSearchTextField.getFont());
        if (RoomSearchTextFieldFont != null) RoomSearchTextField.setFont(RoomSearchTextFieldFont);
        RoomSearchTextField.setForeground(new Color(-13418155));
        RoomSearchTextField.setPreferredSize(new Dimension(1000, 34));
        RoomSearchTextField.setText("Search Room By Id");
        RoomSearchTextField.setToolTipText("Search Room by Name or Id");
        panel14.add(RoomSearchTextField, BorderLayout.WEST);
        RoomsList = new JScrollPane();
        RoomSubPanel.add(RoomsList, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        RoomsList.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$("Consolas", Font.BOLD, 20, RoomsList.getFont()), null));
        TableRoom = new JTable();
        TableRoom.setGridColor(new Color(-920071));
        RoomsList.setViewportView(TableRoom);
        final JLabel label8 = new JLabel();
        label8.setBackground(new Color(-14800581));
        Font label8Font = this.$$$getFont$$$(null, Font.BOLD, 20, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        label8.setForeground(new Color(-14800581));
        label8.setText("Rooms List");
        RoomSubPanel.add(label8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BatchesPanel = new JPanel();
        BatchesPanel.setLayout(new BorderLayout(0, 0));
        BatchesPanel.setBackground(new Color(-920071));
        MainContentPanel.add(BatchesPanel, "BatchesPanel");
        BatchesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), " Batch Menu", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, 20, BatchesPanel.getFont()), new Color(-14800581)));
        BatchSubPanel = new JPanel();
        BatchSubPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        BatchesPanel.add(BatchSubPanel, BorderLayout.CENTER);
        BatchSubPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        BatchActionButtonsPanel = new JPanel();
        BatchActionButtonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        BatchSubPanel.add(BatchActionButtonsPanel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        BatchActionButtonsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        addBatchButton = new JButton();
        addBatchButton.setActionCommand("Add Batch");
        addBatchButton.setBackground(new Color(-15681151));
        addBatchButton.setBorderPainted(false);
        addBatchButton.setFocusPainted(false);
        addBatchButton.setForeground(new Color(-1));
        addBatchButton.setPreferredSize(new Dimension(180, 40));
        addBatchButton.setText("Add Batch");
        BatchActionButtonsPanel.add(addBatchButton);
        updateBatchButton = new JButton();
        updateBatchButton.setActionCommand("Update Batch");
        updateBatchButton.setBackground(new Color(-12877066));
        updateBatchButton.setBorderPainted(false);
        updateBatchButton.setFocusPainted(false);
        updateBatchButton.setForeground(new Color(-1));
        updateBatchButton.setPreferredSize(new Dimension(180, 40));
        updateBatchButton.setText("Update Batch");
        BatchActionButtonsPanel.add(updateBatchButton);
        deleteBatchButton = new JButton();
        deleteBatchButton.setActionCommand("Delete Batch");
        deleteBatchButton.setBackground(new Color(-1096636));
        deleteBatchButton.setBorderPainted(false);
        deleteBatchButton.setFocusPainted(false);
        deleteBatchButton.setForeground(new Color(-1));
        deleteBatchButton.setPreferredSize(new Dimension(180, 40));
        deleteBatchButton.setText("Delete Batch");
        BatchActionButtonsPanel.add(deleteBatchButton);
        BatchSearchBarPanel = new JPanel();
        BatchSearchBarPanel.setLayout(new BorderLayout(0, 0));
        BatchSearchBarPanel.setBackground(new Color(-920071));
        BatchSubPanel.add(BatchSearchBarPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        BatchSearchBarPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(-1906448)));
        searchBarBatch = new JTextField();
        searchBarBatch.setBackground(new Color(-460036));
        searchBarBatch.setCaretColor(new Color(-4473925));
        Font searchBarBatchFont = this.$$$getFont$$$(null, -1, 14, searchBarBatch.getFont());
        if (searchBarBatchFont != null) searchBarBatch.setFont(searchBarBatchFont);
        searchBarBatch.setForeground(new Color(-13418155));
        searchBarBatch.setPreferredSize(new Dimension(1000, 34));
        searchBarBatch.setText("Search Batch By Id");
        searchBarBatch.setToolTipText("Search Batch by Name or Id");
        BatchSearchBarPanel.add(searchBarBatch, BorderLayout.CENTER);
        searchBatchButton = new JButton();
        searchBatchButton.setBackground(new Color(-12877066));
        searchBatchButton.setBorderPainted(false);
        searchBatchButton.setFocusPainted(false);
        searchBatchButton.setForeground(new Color(-1));
        searchBatchButton.setPreferredSize(new Dimension(150, 34));
        searchBatchButton.setText("Search Batch");
        BatchSearchBarPanel.add(searchBatchButton, BorderLayout.EAST);
        BatchPanel = new JScrollPane();
        BatchSubPanel.add(BatchPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        BatchPanel.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$("Consolas", Font.BOLD, 20, BatchPanel.getFont()), null));
        BatchTable = new JTable();
        BatchTable.setGridColor(new Color(-920071));
        BatchPanel.setViewportView(BatchTable);
        final JLabel label9 = new JLabel();
        Font label9Font = this.$$$getFont$$$(null, Font.BOLD, 20, label9.getFont());
        if (label9Font != null) label9.setFont(label9Font);
        label9.setForeground(new Color(-14800581));
        label9.setText("Batches List");
        BatchSubPanel.add(label9, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SchedulePanel = new JPanel();
        SchedulePanel.setLayout(new BorderLayout(0, 0));
        SchedulePanel.setBackground(new Color(-920071));
        SchedulePanel.setForeground(new Color(-3025959));
        MainContentPanel.add(SchedulePanel, "SchedulePanel");
        SchedulePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "Schedule Menu", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, 20, SchedulePanel.getFont()), new Color(-14800581)));
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));
        panel15.setAutoscrolls(false);
        panel15.setBackground(new Color(-1906448));
        panel15.setFocusTraversalPolicyProvider(true);
        panel15.setMinimumSize(new Dimension(100, 84));
        panel15.setOpaque(true);
        panel15.setPreferredSize(new Dimension(200, 84));
        SchedulePanel.add(panel15, BorderLayout.WEST);
        panel15.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "ScheduleEntry", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, 17, panel15.getFont()), new Color(-14800591)));
        final JPanel panel16 = new JPanel();
        panel16.setLayout(new BorderLayout(0, 0));
        panel16.setBackground(new Color(-1906448));
        panel16.setPreferredSize(new Dimension(180, 51));
        panel15.add(panel16);
        panel16.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label10 = new JLabel();
        label10.setForeground(new Color(-13418155));
        label10.setText("Teacher:");
        panel16.add(label10, BorderLayout.NORTH);
        comboBox2 = new JComboBox();
        comboBox2.setBackground(new Color(-920071));
        comboBox2.setFocusable(false);
        comboBox2.setForeground(new Color(-14800581));
        comboBox2.setLightWeightPopupEnabled(true);
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        comboBox2.setModel(defaultComboBoxModel3);
        comboBox2.setRequestFocusEnabled(true);
        panel16.add(comboBox2, BorderLayout.CENTER);
        final JPanel panel17 = new JPanel();
        panel17.setLayout(new BorderLayout(0, 0));
        panel17.setBackground(new Color(-1906448));
        panel17.setPreferredSize(new Dimension(180, 51));
        panel15.add(panel17);
        panel17.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label11 = new JLabel();
        label11.setForeground(new Color(-13418155));
        label11.setText("Batch:");
        panel17.add(label11, BorderLayout.NORTH);
        batchComboBox = new JComboBox();
        batchComboBox.setBackground(new Color(-920071));
        batchComboBox.setFocusable(false);
        batchComboBox.setForeground(new Color(-14800581));
        final DefaultComboBoxModel defaultComboBoxModel4 = new DefaultComboBoxModel();
        batchComboBox.setModel(defaultComboBoxModel4);
        panel17.add(batchComboBox, BorderLayout.CENTER);
        final JPanel panel18 = new JPanel();
        panel18.setLayout(new BorderLayout(0, 0));
        panel18.setBackground(new Color(-1906448));
        panel18.setPreferredSize(new Dimension(180, 51));
        panel15.add(panel18);
        panel18.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label12 = new JLabel();
        label12.setForeground(new Color(-13418155));
        label12.setText("Room:");
        panel18.add(label12, BorderLayout.NORTH);
        roomComboBox = new JComboBox();
        roomComboBox.setBackground(new Color(-920071));
        roomComboBox.setFocusable(false);
        roomComboBox.setForeground(new Color(-14800581));
        final DefaultComboBoxModel defaultComboBoxModel5 = new DefaultComboBoxModel();
        roomComboBox.setModel(defaultComboBoxModel5);
        panel18.add(roomComboBox, BorderLayout.CENTER);
        final JPanel panel19 = new JPanel();
        panel19.setLayout(new BorderLayout(0, 0));
        panel19.setBackground(new Color(-1906448));
        panel19.setPreferredSize(new Dimension(180, 51));
        panel15.add(panel19);
        panel19.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label13 = new JLabel();
        label13.setForeground(new Color(-13418155));
        label13.setText("Day:");
        panel19.add(label13, BorderLayout.NORTH);
        dayComboBOx = new JComboBox();
        dayComboBOx.setBackground(new Color(-920071));
        dayComboBOx.setFocusable(false);
        dayComboBOx.setForeground(new Color(-14800581));
        final DefaultComboBoxModel defaultComboBoxModel6 = new DefaultComboBoxModel();
        defaultComboBoxModel6.addElement("Monday");
        defaultComboBoxModel6.addElement("Tuesday");
        defaultComboBoxModel6.addElement("Wednesday");
        defaultComboBoxModel6.addElement("Thursday");
        defaultComboBoxModel6.addElement("Friday");
        dayComboBOx.setModel(defaultComboBoxModel6);
        panel19.add(dayComboBOx, BorderLayout.CENTER);
        final JPanel panel20 = new JPanel();
        panel20.setLayout(new BorderLayout(0, 0));
        panel20.setBackground(new Color(-1906448));
        panel20.setPreferredSize(new Dimension(180, 51));
        panel15.add(panel20);
        panel20.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label14 = new JLabel();
        label14.setForeground(new Color(-13418155));
        label14.setText("Assign Schdule Id:");
        panel20.add(label14, BorderLayout.NORTH);
        assignSchduleAnIdTextField = new JTextField();
        assignSchduleAnIdTextField.setBackground(new Color(-920071));
        assignSchduleAnIdTextField.setForeground(new Color(-14800581));
        assignSchduleAnIdTextField.setText("Assign Schdule an Id");
        assignSchduleAnIdTextField.setToolTipText("Assign this schedule an Id");
        panel20.add(assignSchduleAnIdTextField, BorderLayout.CENTER);
        final JPanel panel21 = new JPanel();
        panel21.setLayout(new BorderLayout(0, 0));
        panel21.setBackground(new Color(-1906448));
        panel21.setPreferredSize(new Dimension(180, 51));
        panel15.add(panel21);
        panel21.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label15 = new JLabel();
        label15.setForeground(new Color(-13418155));
        label15.setText("Start Time:");
        panel21.add(label15, BorderLayout.NORTH);
        spinnerStarttime = new JSpinner();
        spinnerStarttime.setBackground(new Color(-920071));
        spinnerStarttime.setForeground(new Color(-14800581));
        panel21.add(spinnerStarttime, BorderLayout.SOUTH);
        final JPanel panel22 = new JPanel();
        panel22.setLayout(new BorderLayout(0, 0));
        panel22.setBackground(new Color(-1906448));
        panel22.setPreferredSize(new Dimension(180, 51));
        panel15.add(panel22);
        panel22.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label16 = new JLabel();
        label16.setForeground(new Color(-13418155));
        label16.setText("End Time:");
        panel22.add(label16, BorderLayout.NORTH);
        spinnerEndtime = new JSpinner();
        spinnerEndtime.setBackground(new Color(-920071));
        spinnerEndtime.setForeground(new Color(-14800581));
        panel22.add(spinnerEndtime, BorderLayout.SOUTH);
        final JPanel panel23 = new JPanel();
        panel23.setLayout(new BorderLayout(0, 0));
        panel15.add(panel23);
        panel23.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        scheduleEntryButton = new JButton();
        scheduleEntryButton.setBackground(new Color(-12877066));
        scheduleEntryButton.setBorderPainted(false);
        scheduleEntryButton.setFocusPainted(false);
        Font scheduleEntryButtonFont = this.$$$getFont$$$(null, -1, 15, scheduleEntryButton.getFont());
        if (scheduleEntryButtonFont != null) scheduleEntryButton.setFont(scheduleEntryButtonFont);
        scheduleEntryButton.setForeground(new Color(-460036));
        scheduleEntryButton.setHorizontalAlignment(0);
        scheduleEntryButton.setPreferredSize(new Dimension(180, 54));
        scheduleEntryButton.setText("Schedule Entry");
        panel15.add(scheduleEntryButton);
        final JPanel panel24 = new JPanel();
        panel24.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel24.setBackground(new Color(-1906448));
        panel24.setPreferredSize(new Dimension(180, 27));
        panel15.add(panel24);
        panel24.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        scrollPane1 = new JScrollPane();
        scrollPane1.setAutoscrolls(true);
        scrollPane1.setRequestFocusEnabled(false);
        SchedulePanel.add(scrollPane1, BorderLayout.CENTER);
        scrollPane1.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        table1 = new JTable();
        table1.setAutoCreateColumnsFromModel(true);
        table1.setBackground(new Color(-1));
        table1.setDragEnabled(false);
        table1.setDropMode(DropMode.ON);
        table1.setForeground(new Color(-14800581));
        table1.setGridColor(new Color(-920071));
        table1.setSelectionBackground(new Color(-2364674));
        table1.setSelectionForeground(new Color(-14800571));
        scrollPane1.setViewportView(table1);
        final JPanel panel25 = new JPanel();
        panel25.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        panel25.setBackground(new Color(-1906448));
        panel25.setFocusable(true);
        panel25.setMinimumSize(new Dimension(1, 34));
        panel25.setPreferredSize(new Dimension(40, 49));
        SchedulePanel.add(panel25, BorderLayout.SOUTH);
        panel25.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(-1906448)));
        clashesButton = new JButton();
        clashesButton.setBackground(new Color(-15681151));
        clashesButton.setBorderPainted(false);
        clashesButton.setContentAreaFilled(true);
        clashesButton.setFocusPainted(false);
        Font clashesButtonFont = this.$$$getFont$$$(null, Font.BOLD, 15, clashesButton.getFont());
        if (clashesButtonFont != null) clashesButton.setFont(clashesButtonFont);
        clashesButton.setForeground(new Color(-460036));
        clashesButton.setPreferredSize(new Dimension(180, 40));
        clashesButton.setText("Get Time Tables");
        clashesButton.setVerticalAlignment(0);
        panel25.add(clashesButton);
        updateScheduleButton = new JButton();
        updateScheduleButton.setActionCommand("Update Batch");
        updateScheduleButton.setBackground(new Color(-12877066));
        updateScheduleButton.setBorderPainted(false);
        updateScheduleButton.setFocusPainted(false);
        updateScheduleButton.setForeground(new Color(-1));
        updateScheduleButton.setHorizontalTextPosition(11);
        updateScheduleButton.setPreferredSize(new Dimension(180, 40));
        updateScheduleButton.setText("Update Schedule");
        panel25.add(updateScheduleButton);
        deleteSchduleButton = new JButton();
        deleteSchduleButton.setActionCommand("Delete Batch");
        deleteSchduleButton.setBackground(new Color(-1096636));
        deleteSchduleButton.setBorderPainted(false);
        deleteSchduleButton.setFocusPainted(false);
        deleteSchduleButton.setForeground(new Color(-1));
        deleteSchduleButton.setPreferredSize(new Dimension(180, 40));
        deleteSchduleButton.setText("Delete Schdule");
        panel25.add(deleteSchduleButton);
        TeachersPanel = new JPanel();
        TeachersPanel.setLayout(new BorderLayout(0, 0));
        TeachersPanel.setBackground(new Color(-920071));
        MainContentPanel.add(TeachersPanel, "TeachersPanel");
        TeachersPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), " Teacher Menu", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, 20, TeachersPanel.getFont()), new Color(-14800581)));
        TeacherSUbPanel = new JPanel();
        TeacherSUbPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        TeachersPanel.add(TeacherSUbPanel, BorderLayout.CENTER);
        TeacherSUbPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        ActionButtonPanel = new JPanel();
        ActionButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        TeacherSUbPanel.add(ActionButtonPanel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ActionButtonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        addTeacherButton = new JButton();
        addTeacherButton.setBackground(new Color(-15681151));
        addTeacherButton.setBorderPainted(false);
        addTeacherButton.setFocusPainted(false);
        addTeacherButton.setForeground(new Color(-1));
        addTeacherButton.setPreferredSize(new Dimension(180, 40));
        addTeacherButton.setText("Add Teacher");
        ActionButtonPanel.add(addTeacherButton);
        updateTeacherButton = new JButton();
        updateTeacherButton.setBackground(new Color(-12877066));
        updateTeacherButton.setBorderPainted(false);
        updateTeacherButton.setFocusPainted(false);
        updateTeacherButton.setForeground(new Color(-1));
        updateTeacherButton.setPreferredSize(new Dimension(180, 40));
        updateTeacherButton.setText("Update Teacher");
        ActionButtonPanel.add(updateTeacherButton);
        deleteTeacherButton = new JButton();
        deleteTeacherButton.setBackground(new Color(-1096636));
        deleteTeacherButton.setBorderPainted(false);
        deleteTeacherButton.setFocusPainted(false);
        deleteTeacherButton.setForeground(new Color(-1));
        deleteTeacherButton.setPreferredSize(new Dimension(180, 40));
        deleteTeacherButton.setText("Delete Teacher");
        ActionButtonPanel.add(deleteTeacherButton);
        SearchBarPanel = new JPanel();
        SearchBarPanel.setLayout(new BorderLayout(0, 0));
        SearchBarPanel.setBackground(new Color(-920071));
        TeacherSUbPanel.add(SearchBarPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        SearchBarPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(-1906448)));
        searchTeacherButton = new JButton();
        searchTeacherButton.setBackground(new Color(-12877066));
        searchTeacherButton.setBorderPainted(false);
        searchTeacherButton.setFocusPainted(false);
        searchTeacherButton.setForeground(new Color(-1));
        searchTeacherButton.setPreferredSize(new Dimension(150, 34));
        searchTeacherButton.setText("Search Teacher");
        SearchBarPanel.add(searchTeacherButton, BorderLayout.EAST);
        searchTeacherByIdTextField = new JTextField();
        searchTeacherByIdTextField.setBackground(new Color(-460036));
        searchTeacherByIdTextField.setCaretColor(new Color(-4473925));
        Font searchTeacherByIdTextFieldFont = this.$$$getFont$$$(null, -1, 14, searchTeacherByIdTextField.getFont());
        if (searchTeacherByIdTextFieldFont != null) searchTeacherByIdTextField.setFont(searchTeacherByIdTextFieldFont);
        searchTeacherByIdTextField.setForeground(new Color(-13418155));
        searchTeacherByIdTextField.setPreferredSize(new Dimension(1000, 34));
        searchTeacherByIdTextField.setText("Search Teacher By Id");
        searchTeacherByIdTextField.setToolTipText("Search Teacher by Name or Id");
        SearchBarPanel.add(searchTeacherByIdTextField, BorderLayout.WEST);
        TeachersList = new JScrollPane();
        TeachersList.setBackground(new Color(-920071));
        TeacherSUbPanel.add(TeachersList, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        TeachersList.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$("Consolas", Font.BOLD, 20, TeachersList.getFont()), null));
        table2 = new JTable();
        table2.setGridColor(new Color(-920071));
        TeachersList.setViewportView(table2);
        TeacherListLabel = new JLabel();
        Font TeacherListLabelFont = this.$$$getFont$$$(null, Font.BOLD, 20, TeacherListLabel.getFont());
        if (TeacherListLabelFont != null) TeacherListLabel.setFont(TeacherListLabelFont);
        TeacherListLabel.setForeground(new Color(-14800581));
        TeacherListLabel.setText("Teachers List");
        TeacherSUbPanel.add(TeacherListLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}

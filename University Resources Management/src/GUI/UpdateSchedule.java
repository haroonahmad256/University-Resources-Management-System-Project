package GUI;

import Model.ClashesChecker;
import Model.Room;
import Model.Schedule;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class UpdateSchedule extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel teacherIdLabel;
    private JTextField textField1;
    private JLabel teacherDepartmentLable;
    private JLabel specilizationTeacher;
    private JTextField textField4;
    private JLabel emailteacherlable;
    private JTextField textField5;
    private JLabel weeklyhourslabel;
    private JButton fetchSchduleDataButton;
    private JTextField textField2;
    private JComboBox dayComboBOx;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JComboBox comboBox2;
    private JComboBox batchComboBox;
    private JComboBox roomComboBox;
    private JSpinner spinnerStarttime;
    private JSpinner spinnerEndtime;
    Schedule schedule = new Schedule();

    public UpdateSchedule() {

        Styling.styleButton(buttonOK, "#059669", "#047857");
        Styling.styleButton(buttonCancel, "#738599", "#6A7584");
        Styling.styleButton(fetchSchduleDataButton, "#2563EB", "#1D4ED8");

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        SpinnerDateModel dateModel = new SpinnerDateModel();
        spinnerStarttime.setModel(dateModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerStarttime, "HH:mm:ss");
        spinnerStarttime.setEditor(editor);

        SpinnerDateModel endTimeModel = new SpinnerDateModel();
        spinnerEndtime.setModel(endTimeModel);
        JSpinner.DateEditor editorEndTime = new JSpinner.DateEditor(spinnerEndtime, "HH:mm:ss");
        spinnerEndtime.setEditor(editorEndTime);

        schedule.addScheduleComboBoxValues(comboBox2, batchComboBox, roomComboBox);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        fetchSchduleDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                schedule.FetchScheduleInfo(textField1, comboBox2, batchComboBox, roomComboBox, dayComboBOx, spinnerStarttime, spinnerEndtime);
            }
        });
    }

    private void onOK() {
        if (textField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Fill All the required Fields!");
            return;
        }
        Room room = new Room();
        if (room.roomManage((String) batchComboBox.getSelectedItem(), (String) roomComboBox.getSelectedItem())) {
            JOptionPane.showMessageDialog(null, "Room not suitable! Batch Strength Exceeding Room Capacity");
            return;
        }
        ClashesChecker clashesChecker = new ClashesChecker();
        if (clashesChecker.batchClashWarning(spinnerStarttime, spinnerEndtime, dayComboBOx, batchComboBox)) {
            JOptionPane.showMessageDialog(null, "Clash! Batch is already occupied");
        } else if (clashesChecker.teacherClashWarning(spinnerStarttime, spinnerEndtime, dayComboBOx, comboBox2)) {
            JOptionPane.showMessageDialog(null, "Clash! Teacher is not available in this time period");
        } else if (clashesChecker.roomClashWarning(spinnerStarttime, spinnerEndtime, dayComboBOx, roomComboBox)) {
            JOptionPane.showMessageDialog(null, "Clash! Room is already occupied");
        } else {
            schedule.updateScheduleInfo(textField1, comboBox2, batchComboBox, roomComboBox, dayComboBOx, spinnerStarttime, spinnerEndtime);
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setBackground(new Color(-920071));
        contentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1));
        contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "UDPATE A Schdule", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, 20, panel1.getFont()), new Color(-14800581)));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), 15, 20));
        panel2.setBackground(new Color(-1));
        panel2.setPreferredSize(new Dimension(509, 230));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        teacherIdLabel = new JLabel();
        teacherIdLabel.setForeground(new Color(-10193781));
        teacherIdLabel.setText("Schdule ID:");
        panel2.add(teacherIdLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        textField1.setAlignmentY(1.0f);
        textField1.setBackground(new Color(-1));
        textField1.setForeground(new Color(-10193781));
        panel2.add(textField1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        teacherDepartmentLable = new JLabel();
        teacherDepartmentLable.setForeground(new Color(-10193781));
        teacherDepartmentLable.setText("Teacher ID:");
        panel2.add(teacherDepartmentLable, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        specilizationTeacher = new JLabel();
        specilizationTeacher.setForeground(new Color(-10193781));
        specilizationTeacher.setText("Room ID:");
        panel2.add(specilizationTeacher, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emailteacherlable = new JLabel();
        emailteacherlable.setForeground(new Color(-10193781));
        emailteacherlable.setText("Batch ID:");
        panel2.add(emailteacherlable, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        weeklyhourslabel = new JLabel();
        weeklyhourslabel.setForeground(new Color(-10193781));
        weeklyhourslabel.setText("Start Time:");
        panel2.add(weeklyhourslabel, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fetchSchduleDataButton = new JButton();
        fetchSchduleDataButton.setBackground(new Color(-12877066));
        fetchSchduleDataButton.setForeground(new Color(-1));
        fetchSchduleDataButton.setText("Fetch Schdule Data");
        panel2.add(fetchSchduleDataButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setForeground(new Color(-10193781));
        label1.setText("Schdule Found/Not");
        panel2.add(label1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dayComboBOx = new JComboBox();
        dayComboBOx.setAlignmentY(1.0f);
        dayComboBOx.setBackground(new Color(-1));
        dayComboBOx.setForeground(new Color(-10193781));
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Monday");
        defaultComboBoxModel1.addElement("Tuesday");
        defaultComboBoxModel1.addElement("Wednesday");
        defaultComboBoxModel1.addElement("Thursday");
        defaultComboBoxModel1.addElement("Friday");
        dayComboBOx.setModel(defaultComboBoxModel1);
        panel2.add(dayComboBOx, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setForeground(new Color(-10193781));
        label2.setText("Class Day:");
        panel2.add(label2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setForeground(new Color(-10193781));
        label3.setText("End Time:");
        panel2.add(label3, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox2 = new JComboBox();
        comboBox2.setBackground(new Color(-1));
        comboBox2.setForeground(new Color(-10193781));
        panel2.add(comboBox2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        batchComboBox = new JComboBox();
        batchComboBox.setBackground(new Color(-1));
        batchComboBox.setForeground(new Color(-10193781));
        panel2.add(batchComboBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        roomComboBox = new JComboBox();
        roomComboBox.setBackground(new Color(-1));
        roomComboBox.setForeground(new Color(-10193781));
        panel2.add(roomComboBox, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        spinnerStarttime = new JSpinner();
        spinnerStarttime.setBackground(new Color(-1));
        spinnerStarttime.setForeground(new Color(-10193781));
        panel2.add(spinnerStarttime, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        spinnerEndtime = new JSpinner();
        spinnerEndtime.setBackground(new Color(-1));
        spinnerEndtime.setForeground(new Color(-10193781));
        panel2.add(spinnerEndtime, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        panel3.setBackground(new Color(-920071));
        contentPane.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel4.setBackground(new Color(-920071));
        panel3.add(panel4);
        panel4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        buttonOK = new JButton();
        buttonOK.setBackground(new Color(-15681151));
        buttonOK.setBorderPainted(false);
        buttonOK.setFocusPainted(false);
        buttonOK.setForeground(new Color(-1));
        buttonOK.setText("Update");
        panel4.add(buttonOK);
        buttonCancel = new JButton();
        buttonCancel.setBackground(new Color(-7035976));
        buttonCancel.setBorderPainted(false);
        buttonCancel.setFocusPainted(false);
        buttonCancel.setForeground(new Color(-1));
        buttonCancel.setText("Cancel");
        panel4.add(buttonCancel);
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
        return contentPane;
    }

}

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EnchantGUI extends JFrame implements ItemListener {

    // cmdArgs has no use yet for GUI, but here for future purposes
    private final HashSet<Argument> cmdArgs;
    private final HashSet<Enchantment> enchantArgs;

    private JPanel contentPane;
    private JButton calculateButton;

    private JTabbedPane tabbedPane1;
    private JPanel selectEnchantPanel;


    public EnchantGUI(String title, HashSet<Argument> cmdArgs) {
        super(title);

        this.cmdArgs = cmdArgs;
        enchantArgs = new HashSet<>();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(contentPane);
        this.pack();

        calculateButton.addActionListener(e -> {
            if (enchantArgs.size() > 6) {
                showConfirmCalculateDialog();
            } else {
                calculate();
                showEnchantInstructionsDialog();
            }
        });
    }

    public void start() {
        // Setup FlatLightLaf themes
        FlatLightLaf.setup();

        // Rest of UI goes here
        this.setMinimumSize(new Dimension(400, 600));

        selectEnchantPanel.setLayout(new WrapLayout());

        List<JCheckBox> checkBoxes = new ArrayList<>();

        for (Enchantment e : Enchantment.values()) {
            JCheckBox checkBox = new JCheckBox(e.getPrettyName());
            checkBox.addItemListener(this);

            checkBoxes.add(checkBox);
        }

        checkBoxes.forEach(selectEnchantPanel::add);
        setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // Get the enchantment that was checked/unchecked
        JCheckBox item = (JCheckBox) e.getItemSelectable();
        Enchantment enchantment = Enchantment.fromPrettyName(item.getText());

        // Add or remove the enchantment from the HashSet
        // (add if selected, remove if deselected)
        if (e.getStateChange() == ItemEvent.SELECTED)
            enchantArgs.add(enchantment);

        else if (e.getStateChange() == ItemEvent.DESELECTED)
            enchantArgs.remove(enchantment);

        // Print enchantArgs contents to console to check if this worked
        // enchantArgs.forEach((enchant -> System.out.println(enchant.getPrettyName())));
        // System.out.println();
    }

    public void showConfirmCalculateDialog() {
        new ConfirmCalculateDialog(this).showDialog();
    }

    public void showEnchantInstructionsDialog() {
        new EnchantInstructionsDialog(MCEnchant.instructions()).showDialog();
    }

    public void calculate() {
        Enchantment[] enchantsArr = enchantArgs.toArray(new Enchantment[0]);
        List<Enchantment> enchantments = new ArrayList<>(Arrays.asList(enchantsArr));

        MCEnchant.run(enchantments, enchantArgs.size());

//        System.out.println("least expensive by level (" + MCEnchant.minimumEnchantmentCost + " levels): " + MCEnchant.minimumEnchantmentCostList + " " + MCEnchant.minimumEnchantmentCostOrder + " " + MCEnchant.minimumEnchantmentCostLevels);
//        System.out.println(Arrays.toString(MCEnchant.instructions()));
    }

    public static void main(String[] args) {
        EnchantGUI frame = new EnchantGUI("Optimal Enchant Tool", new HashSet<>());
        frame.start();
        frame.setVisible(true);
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
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Please select enchantments from the list below:");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        contentPane.add(label1, gbc);
        calculateButton = new JButton();
        calculateButton.setText("Calculate");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(calculateButton, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        contentPane.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        contentPane.add(spacer2, gbc);
        tabbedPane1 = new JTabbedPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(tabbedPane1, gbc);
        selectEnchantPanel = new JPanel();
        selectEnchantPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        tabbedPane1.addTab("All", selectEnchantPanel);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}

package com.gennadii.overchuk.depositstore.client;

import com.gennadii.overchuk.depositstore.Deposit;
import com.gennadii.overchuk.depositstore.DepositList;
import com.gennadii.overchuk.depositstore.Type;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.IOException;

/**
 * Created by Tehnik on 03.04.2017.
 */
public class ClientForm extends Container {

    private JPanel panel;
    private JTextArea commandTextArea;
    private JRadioButton accountInfoButton;
    private JRadioButton infoDepositorButton;
    private JRadioButton depositsByTypeRadioButton;
    private JRadioButton depositsByBankRadioButton;
    private JRadioButton deleteDepositRadioButton;
    private JTextField inputTextField;
    private JRadioButton sumButton;
    private JRadioButton countButton;
    private JRadioButton listRadioButton;
    private JLabel nameLabel;
    private JLabel countryLabel;
    private JLabel typeLabel;
    private JLabel depositorLabel;
    private JLabel accountIdLabel;
    private JLabel depositAmountLabel;
    private JLabel profitabilityLabel;
    private JLabel timeConstraintsLabel;
    private JPanel fullDepositInfoPanel;
    private JTextField nameField;
    private JTextField countryField;
    private JTextField typeField;
    private JTextField depositorField;
    private JTextField accountIdField;
    private JTextField depositAmountField;
    private JTextField profitabilityField;
    private JTextField timeConstraintsField;
    private JButton addButton;
    private JRadioButton addNewDepositRadioButton;

    public ClientForm() {
        panel.addComponentListener(new ComponentAdapter() {
        });
    }

    public static void main(String[] args) throws IOException, JAXBException {
        final Client client = new Client();
        client.init();

        JFrame jf = new JFrame("depositstore");
        final ClientForm clientForm = new ClientForm();
        //кнопки для комманд
        clientForm.listRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeInvisible(clientForm.inputTextField);
                makeInvisible(clientForm.fullDepositInfoPanel);

                DepositList list = client.list();
                clientForm.commandTextArea.setText(list.toString());
            }
        });
        clientForm.sumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeInvisible(clientForm.inputTextField);
                makeInvisible(clientForm.fullDepositInfoPanel);

                String sum = client.sum();
                clientForm.commandTextArea.setText(sum);
            }
        });
        clientForm.countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeInvisible(clientForm.inputTextField);
                makeInvisible(clientForm.fullDepositInfoPanel);

                String count = client.count();
                clientForm.commandTextArea.setText(count);
            }
        });

        //баттоны для ввода
        clientForm.accountInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeVisible(clientForm.inputTextField);
                makeInvisible(clientForm.fullDepositInfoPanel);
            }
        });
        clientForm.infoDepositorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeVisible(clientForm.inputTextField);
                makeInvisible(clientForm.fullDepositInfoPanel);
            }
        });
        clientForm.depositsByTypeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeVisible(clientForm.inputTextField);
                makeInvisible(clientForm.fullDepositInfoPanel);
            }
        });
        clientForm.depositsByBankRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeVisible(clientForm.inputTextField);
                makeInvisible(clientForm.fullDepositInfoPanel);
            }
        });
        clientForm.addNewDepositRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeInvisible(clientForm.inputTextField);
                makeVisible(clientForm.fullDepositInfoPanel);
            }
        });
        clientForm.deleteDepositRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeVisible(clientForm.inputTextField);
                makeInvisible(clientForm.fullDepositInfoPanel);
            }
        });

        clientForm.inputTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputData = clientForm.inputTextField.getText();
                if (clientForm.accountInfoButton.isSelected()) {
                    if (!inputData.matches("\\d+$")) {
                        clientForm.commandTextArea.setText("Invalid input, number expected!");
                        return;
                    }
                    Deposit deposit = client.accountInfo(Long.valueOf(inputData));
                    clientForm.commandTextArea.setText(deposit.toString());
                } else if (clientForm.infoDepositorButton.isSelected()) {
                    DepositList depositList = client.infoDepositor(inputData);
                    clientForm.commandTextArea.setText(depositList.toString());
                } else if (clientForm.depositsByTypeRadioButton.isSelected()) {
                    Type type;
                    try {
                        type = Type.valueOf(inputData.toUpperCase());
                    } catch (IllegalArgumentException ex) {
                        clientForm.commandTextArea.setText("Invalid deposit type, expected one of : POSTE_RESTANTE,URGENT,RATED,CUMULATIVE,SAVINGS,METAL!");
                        return;
                    }
                    DepositList depositList = client.showType(type);
                    clientForm.commandTextArea.setText(depositList.toString());
                } else if (clientForm.depositsByBankRadioButton.isSelected()) {
                    DepositList depositList = client.showBank(inputData);
                    clientForm.commandTextArea.setText(depositList.toString());
                } else if (clientForm.deleteDepositRadioButton.isSelected()) {
                    if (!inputData.matches("\\d+$")) {
                        clientForm.commandTextArea.setText("Invalid input, number expected!");
                        return;
                    }
                    client.delete(Long.valueOf(inputData));
                    clientForm.commandTextArea.setText("Deposit " + inputData + " has been deleted.");
                }
            }
        });
        clientForm.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Type type;
                try {
                    type = Type.valueOf(clientForm.typeField.getText().toUpperCase());
                } catch (IllegalArgumentException ex) {
                    clientForm.commandTextArea.setText("Invalid deposit type, expected one of : POSTE_RESTANTE,URGENT,RATED,CUMULATIVE,SAVINGS,METAL!");
                    return;
                }
                if (!clientForm.accountIdField.getText().matches("\\d+$")) {
                    clientForm.commandTextArea.setText("Invalid accountId, number expected!");
                    return;
                }
                if (!clientForm.depositAmountField.getText().matches("\\d*\\.?\\d+")) {
                    clientForm.commandTextArea.setText("Invalid amount, double expected!");
                    return;
                }
                if (!clientForm.profitabilityField.getText().matches("\\d+$")) {
                    clientForm.commandTextArea.setText("Invalid profitability value, number expected!");
                    return;
                }
                if (!clientForm.timeConstraintsField.getText().matches("\\d+$")) {
                    clientForm.commandTextArea.setText("Invalid constraints value, number expected!");
                    return;
                }

                Deposit deposit = new Deposit();
                deposit.setName(clientForm.nameField.getText());
                deposit.setCountry(clientForm.countryField.getText());
                deposit.setType(type);
                deposit.setDepositor(clientForm.depositorField.getText());
                deposit.setAccountId(Long.valueOf(clientForm.accountIdField.getText()));
                deposit.setAmountOnDeposit(Double.valueOf(clientForm.depositAmountField.getText()));
                deposit.setProfitability(Integer.valueOf(clientForm.profitabilityField.getText()));
                deposit.setTimeConstraints(Integer.valueOf(clientForm.timeConstraintsField.getText()));
                String message = client.addInfo(deposit);
                clientForm.commandTextArea.setText(message);
            }
        });


        jf.setContentPane(clientForm.panel);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);

    }

    private static void makeInvisible(JComponent component) {
        if (component.isVisible()) {
            component.setVisible(false);
            component.getParent().revalidate();
        }
    }

    private static void makeVisible(JComponent component) {
        if (!component.isVisible()) {
            component.setVisible(true);
            component.getParent().revalidate();
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
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
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(9, 7, new Insets(0, 0, 0, 0), -1, -1));
        panel.setEnabled(true);
        panel.setInheritsPopupMenu(true);
        panel.setMaximumSize(new Dimension(1940, 1540));
        panel.setMinimumSize(new Dimension(125, 244));
        panel.setPreferredSize(new Dimension(720, 360));
        panel.setRequestFocusEnabled(false);
        panel.setToolTipText("");
        panel.setVisible(true);
        accountInfoButton = new JRadioButton();
        accountInfoButton.setEnabled(true);
        accountInfoButton.setHideActionText(true);
        accountInfoButton.setText("Info by account id");
        panel.add(accountInfoButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        infoDepositorButton = new JRadioButton();
        infoDepositorButton.setText("Info by depositor");
        panel.add(infoDepositorButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        depositsByTypeRadioButton = new JRadioButton();
        depositsByTypeRadioButton.setText("Deposits by type");
        panel.add(depositsByTypeRadioButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, 1, 1, null, null, null, 0, false));
        depositsByBankRadioButton = new JRadioButton();
        depositsByBankRadioButton.setText("Deposits by bank");
        depositsByBankRadioButton.setVerifyInputWhenFocusTarget(false);
        panel.add(depositsByBankRadioButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteDepositRadioButton = new JRadioButton();
        deleteDepositRadioButton.setText("Delete deposit");
        panel.add(deleteDepositRadioButton, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inputTextField = new JTextField();
        inputTextField.setEnabled(true);
        inputTextField.setVisible(false);
        panel.add(inputTextField, new GridConstraints(3, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        sumButton = new JRadioButton();
        sumButton.setText("Deposits amounts sum");
        panel.add(sumButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        countButton = new JRadioButton();
        countButton.setText("Deposits count");
        panel.add(countButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        listRadioButton = new JRadioButton();
        listRadioButton.setBorderPainted(false);
        listRadioButton.setText("List  of deposits");
        panel.add(listRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fullDepositInfoPanel = new JPanel();
        fullDepositInfoPanel.setLayout(new GridLayoutManager(8, 3, new Insets(0, 0, 0, 0), -1, -1));
        fullDepositInfoPanel.setVisible(false);
        panel.add(fullDepositInfoPanel, new GridConstraints(4, 1, 5, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        nameLabel = new JLabel();
        nameLabel.setText("Name");
        fullDepositInfoPanel.add(nameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        countryLabel = new JLabel();
        countryLabel.setText("Country");
        fullDepositInfoPanel.add(countryLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        typeLabel = new JLabel();
        typeLabel.setText("Type");
        fullDepositInfoPanel.add(typeLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        depositorLabel = new JLabel();
        depositorLabel.setText("Depositor");
        fullDepositInfoPanel.add(depositorLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        accountIdLabel = new JLabel();
        accountIdLabel.setText("Account Id");
        fullDepositInfoPanel.add(accountIdLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        depositAmountLabel = new JLabel();
        depositAmountLabel.setText("Deposit amount");
        fullDepositInfoPanel.add(depositAmountLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        profitabilityLabel = new JLabel();
        profitabilityLabel.setText("Profitability");
        fullDepositInfoPanel.add(profitabilityLabel, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        timeConstraintsLabel = new JLabel();
        timeConstraintsLabel.setText("Time constraints");
        fullDepositInfoPanel.add(timeConstraintsLabel, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField = new JTextField();
        fullDepositInfoPanel.add(nameField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        countryField = new JTextField();
        fullDepositInfoPanel.add(countryField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        typeField = new JTextField();
        fullDepositInfoPanel.add(typeField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        depositorField = new JTextField();
        fullDepositInfoPanel.add(depositorField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        accountIdField = new JTextField();
        fullDepositInfoPanel.add(accountIdField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        depositAmountField = new JTextField();
        fullDepositInfoPanel.add(depositAmountField, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        timeConstraintsField = new JTextField();
        fullDepositInfoPanel.add(timeConstraintsField, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        profitabilityField = new JTextField();
        fullDepositInfoPanel.add(profitabilityField, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addButton = new JButton();
        addButton.setText("ADD");
        fullDepositInfoPanel.add(addButton, new GridConstraints(0, 2, 8, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel.add(scrollPane1, new GridConstraints(0, 1, 3, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        commandTextArea = new JTextArea();
        commandTextArea.setEditable(false);
        scrollPane1.setViewportView(commandTextArea);
        addNewDepositRadioButton = new JRadioButton();
        addNewDepositRadioButton.setText("Add new deposit");
        addNewDepositRadioButton.setVerifyInputWhenFocusTarget(false);
        panel.add(addNewDepositRadioButton, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(listRadioButton);
        buttonGroup.add(sumButton);
        buttonGroup.add(countButton);
        buttonGroup.add(accountInfoButton);
        buttonGroup.add(infoDepositorButton);
        buttonGroup.add(depositsByTypeRadioButton);
        buttonGroup.add(depositsByBankRadioButton);
        buttonGroup.add(addNewDepositRadioButton);
        buttonGroup.add(deleteDepositRadioButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}

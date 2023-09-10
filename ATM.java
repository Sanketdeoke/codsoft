package task2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;



class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class ATM extends JFrame implements ActionListener {
    private BankAccount userAccount;
    private JTextField amountField;
    private JLabel balanceLabel;

    public ATM(BankAccount account) {
        userAccount = account;

        setTitle("ATM Machine");
        setSize(440, 494);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel instructionLabel = new JLabel("Select an option:");
        instructionLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        instructionLabel.setBounds(28, 163, 170, 40);
        getContentPane().add(instructionLabel);

        JButton depositButton = new JButton("Deposit");
        depositButton.setForeground(Color.BLUE);
        depositButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        depositButton.setBounds(41, 214, 119, 35);
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setForeground(Color.GREEN);
        withdrawButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        withdrawButton.setBounds(41, 289, 144, 40);
        JButton balanceButton = new JButton("Check Balance");
        balanceButton.setForeground(Color.GRAY);
        balanceButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        balanceButton.setBounds(41, 367, 210, 36);

        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        balanceButton.addActionListener(this);

        getContentPane().add(depositButton);
        getContentPane().add(withdrawButton);
        getContentPane().add(balanceButton);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        amountLabel.setBounds(251, 173, 103, 21);
        amountField = new JTextField(10);
        amountField.setFont(new Font("Tahoma", Font.BOLD, 20));
        amountField.setBounds(221, 248, 163, 31);
        getContentPane().add(amountLabel);
        getContentPane().add(amountField);

        balanceLabel = new JLabel("Balance: $" + userAccount.getBalance());
        balanceLabel.setForeground(Color.DARK_GRAY);
        balanceLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        balanceLabel.setBounds(93, 90, 234, 40);
        getContentPane().add(balanceLabel);
        
        JLabel lblNewLabel = new JLabel("Welcome to ATM!");
        lblNewLabel.setForeground(Color.DARK_GRAY);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setBounds(93, 27, 221, 35);
        getContentPane().add(lblNewLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if ("Deposit".equals(actionCommand)) {
            double amount = Double.parseDouble(amountField.getText());
            userAccount.deposit(amount);
            balanceLabel.setText("Balance: $" + userAccount.getBalance());
            JOptionPane.showMessageDialog(this, "Deposited $" + amount);
        } else if ("Withdraw".equals(actionCommand)) {
            double amount = Double.parseDouble(amountField.getText());
            if (userAccount.withdraw(amount)) {
                balanceLabel.setText("Balance: $" + userAccount.getBalance());
                JOptionPane.showMessageDialog(this, "Withdrawn $" + amount);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid withdrawal amount or insufficient balance.");
            }
        } else if ("Check Balance".equals(actionCommand)) {
            JOptionPane.showMessageDialog(this, "Your balance is: $" + userAccount.getBalance());
        }
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0);
        ATM atm = new ATM(userAccount);
        atm.setVisible(true);
    }
}


package Budget;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Main.App;

import java.util.ArrayList;

public class Menu<US1, US2> {
    private double income1=2200;
    private double income2=2750;
    private double savings=40000;
    private double amount;
    private double balance=calcBalance();
    private double remain=calcRemain();
    private double tot=calcTot();
    private final US1 userName;
    private final US1 userPassword;

    ArrayList<Double> expenses = new ArrayList<Double>();

   
    
    public Menu(US1 userName, US1 userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;    
    }

    public US1 getUserName() {
        return userName;
    }
    public US1 getUserPassword() {
        return userPassword;
    }

    // METHODS to calculate the formulas

public double calcBalance() {
    return income1 + income2;
}

public double calcRemain() {
    return ((income1 + income2) * 0.2);
}

public double calcTot() {
    return (calcBalance() - calcRemain());
}

public void updateBalance(double value) {
    balance += value;
}

// A - Total balance method
void tbalance() {
    App.clrscr();
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("\nSavings= " + savings);
    System.out.println("Balance= " + balance);

}

// B - Remaining budget for the month

void remain() {
    App.clrscr();
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    if ((balance < remain) && (balance > 0)) {
        System.out.println("You spent more than your recommended budget. Your current balance is: " + balance);
    } else if (balance >= remain) {
        System.out.println(
                "It's recommended you save at least 20% of your income.\nTherefore you still have available: "
                        + (balance - (balance*0.2)));
    } else {
        System.out.println("You spent all your monthly income! " + (balance));
    }
}

// C - extract method

void extract() {
    if (expenses.isEmpty() == true) {
        System.out.println("You haven't reported any transactions yet.");
    } else {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println(expenses.get(i));
        }
    }
}

void transfer(double amount) {
    savings = savings + amount;
    balance = balance - amount;
    System.out.println("Current Savings: " + savings);
    System.out.println("Current Balance: " + balance);
}

public void mainMenu() {
    char opt;
    Scanner scan = new Scanner(System.in);

    do {
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Choose an option below:");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("  A - Check total balance");
        System.out.println("  B - Check remaining budget for the month");
        System.out.println("  C - Check extract (expenses and extra income)");
        System.out.println("  D - Input expenses");
        System.out.println("  E - Input extra income");
        System.out.println("  F - Update income - Husband");
        System.out.println("  G - Update income - Wife");
        System.out.println("  H - Update your Savings");
        System.out.println("  Q - Exit");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        opt = scan.next().charAt(0);

        App.clrscr();

        switch (opt) {
            case 'a':
                System.out.println("-----------------------------------------------");
                tbalance();
                break;

            case 'b':
                System.out.println("-----------------------------------------------");
                remain();
                break;

            case 'c':
                extract();
                break;

            case 'd':
                try {

                    if ((savings == 0) && (balance == 0)) {
                        System.out.println("You are broke. Go to work!");
                        break;
                    }
                    System.out.println("-----------------------------------------------");
                    System.out.println("Type below your expenses. Type [0] if you finish to input.");
                    do {
                        amount = scan.nextDouble();
                        if (amount == 0) {
                            break;
                        } else {
                            expenses.add(-amount);
                            balance = balance - amount;
                            tot = tot - amount;
                            System.out.println("Budget: " + (balance - remain) + "\n");
                            if (((balance - remain) <= 0) && ((balance) > 0)) {
                                App.clrscr();
                                System.out.println("You have spent your monthly budget!");
                                System.out.println("   Budget: " + (balance - remain) + "\n");
                                System.out.println("Current Balance: " + balance);
                            } else if ((balance <= 0) && (savings > 0)) {
                                App.clrscr();
                                System.out.println("You have spent your monthly income! Your savings will be deduct!");
                                savings = savings + balance;
                                System.out.println("   Budget: " + (tot) + "\n");
                                System.out.println("   Current savings: " + (savings));
                                System.out.println("   Current balance: " + "0\n");
                                balance = 0;
                                if (savings <= 0) {
                                    App.clrscr();
                                    System.out.println("You broke! Go to work!\n");
                                    savings = 0;
                                    balance = 0;
                                    System.out.println("   Current savings: " + savings);
                                    System.out.println("   Current balance: 0");
                                    break;
                                }
                            } else if ((balance <= 0) && (savings <= 0)) {
                                App.clrscr();
                                System.out.println("You broke! Go to work!\n");
                                savings = 0;
                                balance = 0;
                                System.out.println("   Current savings: " + savings);
                                System.out.println("   Current balance: 0");
                                break;
                            }

                        }
                        System.out.println("Type [0] if you finish to input.");
                        System.out.println("---------------------------------\n");
                    } while (amount != 0);
                } catch (Exception e) {
                    System.out.println("An unexpected character was typed. Please type a number!\n");
                }
                break;
            case 'e':
                System.out.println("-----------------------------------------------");
                System.out.println("Type below your extra income. Type [0] if you finish to input.");
                try {
                    do {
                        amount = scan.nextDouble();
                        if (amount == 0) {
                            break;
                        } else {
                            expenses.add(amount);
                            balance = balance + amount;
                            tot = tot + amount;
                            System.out.println("Balance: " + balance + "\n");
                        }
                        System.out.println("Type [0] if you finish to input.");
                    } while (amount != 0);
                    
                } catch (Exception e) {
                    System.out.println("An unexpected character was typed. Please type a number!\n");
                }
                break;
            case 'f':
                try {
                    updateBalance(-income1);
                    System.out.println("-----------------------------------------------");
                    System.out.println("Type below the new Husband's income: ");
                    System.out.println("   Current income 1: " + income1);
                    income1 = scan.nextDouble();
                    updateBalance(income1); // update the balance
                    System.out.println("\n Updated successfully! The new income 1 is: " + income1);
                } catch (Exception e) {
                    System.out.println("An unexpected character was typed. Please type a number!\n");
                }
                
                break;
            case 'g':
                try {
                    updateBalance(-income2);
                    System.out.println("-----------------------------------------------");
                    System.out.println("Type below the new Wife's income: ");
                    System.out.println("   Current income 2: " + income2);
                    income2 = scan.nextDouble();
                    updateBalance(income2); // update the balance
                    System.out.println("\n Updated successfully! The new income 2 is: " + income2);
                } catch (Exception e) {
                    System.out.println("An unexpected character was typed. Please type a number!\n");
                }
                break;
            case 'h':
                try {
                    App.clrscr();
                    System.out.println("-----------------------------------------------");
                    System.out.println("Type the amount you would like to transfer into your Savings: ");
                    System.out.println("   Savings: " + savings);
                    System.out.println("   Balance: " + balance);
                    amount = scan.nextDouble();
                    App.clrscr();
                    System.out.println("Updated successfully!");
                    transfer(amount);
                } catch (Exception e) {
                    System.out.println("An unexpected character was typed. Please type a number!\n");
                }
                break;
            case 'q':
                // System.out.println("-----------------------------------------------");
                break;

        default:
            System.out.println(opt + " is NOT an option in the menu. Please type an available option.");
            break;
        }

    } while (Character.toLowerCase(opt) != 'q');
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("System closed. Your current finance status is:");
    System.out.println("\n   Savings: " + savings);
    System.out.println("   Balance: " + balance);

    // if((balance <remain)&&(balance>0)){
    // System.out.println(" You have exceeded your budget: "+balance);
    // }else if(balance<0){
    // System.out.println(" You ");
    // }
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    scan.close();
}

public void dateForm() {
    Date d = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("E dd.MM.yyyy '-' hh:mm:ss a");
    System.out.println("Date of access: " + ft.format(d));
    System.out.println("-----------------------------------------------");
    // lastacc = d; (trying to get the last time I updated something)
}

    













}

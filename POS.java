import java.io.*;

public class POS {

        // [Menu Items]
        static String[] Burger_Sandwich = { "Regular Burger", "Bacon cheeseburger", "Chicken burger", "Hotdog sandwich",
                        "Tuna sandwich" };
        static String[] Meals = { "Chicken", "Burger Steak", "Sisig", "Spaghetti", "Palabok" };
        static String[] Drinks = { "Coke", "Sprite", "Fanta", "Purified Water", "Lemonade", "Nestea" };
        static String[] Desserts = { "Ice cream", "Sundaes", "Shakes" };
        static String[] Extras = { "Fries", "pies", "Potato wedges" };

        // [Menu Prices]
        static double[] Burger_Sandwich_Price = { 35.00, 100.00, 50.00, 55.00, 70.00 };
        static double[] Meals_Price = { 99.00, 75.00, 70.00, 65.00, 65.00 };
        static double[] Drinks_Price = { 14.00, 14.00, 16.00, 0.00, 16.00, 18.00 };
        static double[] Desserts_Price = { 15.00, 25.00, 35.00 };
        static double[] Extras_Price = { 50.00, 50.00, 50.00 };

        // [Menu Quantity]
        static int[] Burger_Sandwich_Stock = { 25, 25, 25, 25, 25 };
        static int[] Meals_Stock = { 25, 25, 25, 25, 25 };
        static int[] Drinks_Stock = { 50, 50, 50, 50, 50, 50 };
        static int[] Desserts_Stock = { 15, 15, 15 };
        static int[] Extras_Stock = { 10, 10, 10 };

        // [user ordered items]
        static int[] Burger_Sandwich_Order = { 0, 0, 0, 0, 0 };
        static int[] Meals_Order = { 0, 0, 0, 0, 0 };
        static int[] Drinks_Order = { 0, 0, 0, 0, 0, 0 };
        static int[] Desserts_Order = { 0, 0, 0 };
        static int[] Extras_Order = { 0, 0, 0 };

        // [User Choices]
        static int[] Burger_Sandwich_Choice = { 0, 0, 0, 0, 0 };
        static int[] Meals_Choice = { 0, 0, 0, 0, 0 };
        static int[] Drinks_Choice = { 0, 0, 0, 0, 0, 0 };
        static int[] Desserts_Choice = { 0, 0, 0 };
        static int[] Extras_Choice = { 0, 0, 0 };

        static int choice = 0;
        static int Payment_Options = 0;
        static String name = "";
        static boolean end_program = false;
        static boolean Return_from_Transaction = false;
        static boolean Return_to_Order = false;
        static boolean Trans_to_menu = false;

        static int[] Product_Total_Price = { 0, 0, 0, 0, 0 };

        // [Overall Total]
        static double Subtotal = 0;
        static double Tax = 0.10;
        static double Total = 0;
        static double cash = 0;
        static double change = 0;

        static boolean[] Soldout = { false, false, false, false, false };
        static boolean[] Check_for_Order = { false, false, false, false, false };
        static boolean[] Empty_Choice = { true, true, true, true, true };
        static boolean[] Return_Empty_Category = { true, true, true, true, true };

        // change to true if you run it on cmd, else false if you run it on Terminal
        // that supports ANSI escape codes
        static boolean EnableforCMD = true;

        /*
         * It only appears if the method has a function called try-catch
         * "G4" - Group number | "M" - Method | 1 - 1st Method
         * -------------------------------------------------------------
         * Menu Error - G4M3
         * Category Error - G4M4
         * transaction Error - G4M6
         * return Error - G4M10
         */

        public static void main(String[] args) throws IOException, InterruptedException {
                BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));

                // add 5 columns to the terminal with a 9 space between each column
                // to align the menu items
                String format = "%1$-10s %2$-10s %3$-10s %4$-10s %5$-10s";
                /*
                 * %1$ is the first column, etc.
                 * -9s is the width of the column, 9 is for how many
                 * character before the next column
                 */

                // Clear the screen
                // NOTE: [\033\143] only works on terminals that support ANSI escape codes
                // like windows termnal, linux terminal, onlinegdb.com
                // System.out.println("\033\143");
                clearConsole();

                // calling Information method
                Information();

                // ask the user to enter their name
                System.out.println("\n\n");
                System.out.print("Please enter your name: ");
                name = breader.readLine().toUpperCase();

                clearConsole();

                // add 4 blank lines to the terminal
                System.out.println("\n\n\n\n\n");

                // print welcome message with the user's name
                System.out.println(String.format(format, "", "", "Welcome " + name + "!", "", ""));
                System.out.println("\n\n\n\n\n");

                // Delay the program for 1.9 seconds
                // the Thread.sleep() is using the milliseconds as the unit
                Thread.sleep(1900);

                clearConsole();

                // calling the Menu method
                Menu();

        }

        public static void Information() throws InterruptedException {

                // NOTE: This method is for esthetic purposes only

                String format = "%1$-10s %2$-13s %3$-3s %4$-23s";

                // List of Members
                String[] members = { "Ryan James V. Capadocia", "James Matthew Veloria", "Joseph Contador",
                                "Trina Mae Par ", "Lorenzo Asis", "Cesar Isaac" };

                // other information
                String Prof = "Ms. Mariella Leyba";
                String Sub = "DCIT23A";
                String Proj = "Point of Sale";

                // if end_program is false, print the welcome message
                if (end_program == false) {
                        System.out.println("+---------------------- WELCOME --------------------+");
                } else {
                        System.out.println("+---------------------------------------------------+");
                }

                // print the information
                System.out.println("|        Group 4 [Fast Food Point of Sale]          |");
                System.out.println("+---------------------------------------------------+");
                System.out.println("_____________________________________________________");
                System.out.println(String.format(format, "Instructor", "", "", Prof));
                System.out.println(String.format(format, "Subject", "", "", Sub));
                System.out.println(String.format(format, "Project", "", "", Proj));
                System.out.println("-----------------------------------------------------");
                Thread.sleep(1000);
                System.out.println(":=========[ Group Leader ]==========================:");
                System.out.println(String.format(format, "", "", "", members[0]));

                // loop through the members array and print the rest of the members except the
                // first one
                System.out.println(":=========[ Group Members ]=========================:");
                for (int i = 1; i < members.length; i++) {
                        System.out.println(String.format(format, "", "", "", members[i]));
                        Thread.sleep(300);
                }
                System.out.println("-----------------------------------------------------");

                // if end_program is false, the program loading will be displayed
                if (end_program == false) {
                        for (int i = 1; i <= 100; i++) {
                                System.out.print("\rPlease Wait while we load the Program: " + i + " %");
                                // random number from 1 to 150 to simulate the loading
                                int random = (int) (Math.random() * 150) + 1;
                                // asign the random number to the sleep time
                                Thread.sleep(random);
                        }
                        clearConsole();
                        System.out.println("+---------------------------------------------------+");
                        System.out.println("|                        DONE                       |");
                        System.out.println("+---------------------------------------------------+");
                        // Delay the program for 1 millisecond or 1 second
                        Thread.sleep(1000);
                        clearConsole();
                } else {
                        clearConsole();
                        System.out.println("+---------------------------------------------------+");
                        System.out.println("|                      GOOD-BYE                     |");
                        System.out.println("+---------------------------------------------------+");
                        Thread.sleep(1800);
                        clearConsole();
                }

        }

        public static void Menu() throws IOException, InterruptedException {
                BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));
                String format = "%1$-21s %2$-10s %3$-20s";

                // try-catch block to catch the error if the user enters a wrong input
                try {
                        System.out.println("+---------------------------------------------------+");
                        System.out.println("|                        MENU                       |");
                        System.out.println("+---------------------------------------------------+");
                        System.out.println("\nPlease select the category you want to buy from\n");

                        // if Soldout is true for the first category, print the category name and
                        // Soldout
                        // same for the other categories
                        if (Soldout[0] == true) {
                                System.out.println(String.format(format, "[ 1 ] Burger/Sandwich", "(Sold Out)", ""));
                        } else {
                                System.out.println(String.format(format, "[ 1 ] Burger/Sandwich", "", ""));
                        }

                        if (Soldout[1] == true) {
                                System.out.println(String.format(format, "[ 2 ] Meals", "(Sold Out)", ""));
                        } else {
                                System.out.println(String.format(format, "[ 2 ] Meals", "", ""));
                        }

                        if (Soldout[2] == true) {
                                System.out.println(String.format(format, "[ 3 ] Drinks", "(Sold Out)", ""));
                        } else {
                                System.out.println(String.format(format, "[ 3 ] Drinks", "", ""));
                        }

                        if (Soldout[3] == true) {
                                System.out.println(String.format(format, "[ 4 ] Desserts", "(Sold Out)", ""));
                        } else {
                                System.out.println(String.format(format, "[ 4 ] Desserts", "", ""));
                        }

                        if (Soldout[4] == true) {
                                System.out.println(String.format(format, "[ 5 ] Extra", "(Sold Out)", ""));
                        } else {
                                System.out.println(String.format(format, "[ 5 ] Extra", "", ""));
                        }

                        System.out.println(String.format(format, "", "", "--------------------"));

                        // this return item category will only show if the user have ordered
                        for (int i = 0; i < Check_for_Order.length; i++) {
                                if (Check_for_Order[i] == true) {
                                        System.out.println(String.format(format, "", "", "[ 6 ] Return an Item"));
                                        break;
                                }
                        }

                        // same as return item category, this will only show if the user have soldout an
                        // category
                        for (int i = 0; i < Soldout.length; i++) {
                                if (Soldout[i] == true) {
                                        System.out.println(String.format(format, "", "", "[ 7 ] Checkout"));
                                        break;
                                }
                        }
                        System.out.println(String.format(format, "", "", "[ 0 ] Exit"));

                        // prompt the user to enter the category number
                        System.out.println("-----------------------------------------------------");
                        System.out.print("Enter your choice: ");
                        choice = Integer.parseInt(breader.readLine());
                        clearConsole();

                        // after the user enter the category number, the program will go to the
                        // corresponding method
                        Category(choice);
                } catch (Exception e) {
                        clearConsole();
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Error " + e.getMessage());
                        System.out.println("Error Code: [G4M3]");
                        System.out.println("-----------------------------------------------------");
                        System.out.println("You can only enter a numeric value between 0-7");
                        Thread.sleep(3000);
                        clearConsole();
                        Menu();
                }
        }

        public static void Category(int choice) throws IOException, InterruptedException {
                BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));
                String format = "%1$-4s %2$-18s %3$-3s %4$-12s %5$-5s %6$-3s";
                try {
                        switch (choice) {
                                case 0:
                                        clearConsole();
                                        System.out.println("+---------------------------------------------------+");
                                        System.out.println("|          Thank you for using our program          |");
                                        System.out.println("+---------------------------------------------------+");
                                        Thread.sleep(1000);
                                        end_program = true;
                                        Information();
                                        break;
                                case 1:
                                        // before the user can order the item, the program will check if the category is
                                        // soldout
                                        if (Burger_Sandwich_Stock[0] == 0 && Burger_Sandwich_Stock[1] == 0 &&
                                                        Burger_Sandwich_Stock[2] == 0 &&
                                                        Burger_Sandwich_Stock[3] == 0 &&
                                                        Burger_Sandwich_Stock[4] == 0) {
                                                Soldout[0] = true;

                                                // if the category is soldout, the program will display the Soldout
                                                // message
                                                clearConsole();
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "|               This item is sold out               |");
                                                System.out.println(
                                                                "+---------------------------------------------------+\n");

                                                // prompt the user if they want to order more item
                                                System.out.print("Do you want to add more items? (Y/N): ");
                                                String add = breader.readLine();

                                                // if the user enter Y, the program will go to the Menu method
                                                // NOTE: this add String is not case sensitive so the user can enter Y
                                                // or y
                                                if (add.equalsIgnoreCase("Y")) {
                                                        clearConsole();
                                                        Menu();
                                                        // if the user enter N, the program will go to the Receipt
                                                        // method to process the order
                                                } else if (add.equalsIgnoreCase("N")) {
                                                        clearConsole();
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        System.out.println(
                                                                        "| Thank you Please wait while we process your order |");
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        // Random delay between 0.1 to 2 seconds
                                                        int random = (int) (Math.random() * (2000 - 100 + 1) + 100);
                                                        Thread.sleep(random);
                                                        Order_List();

                                                        // if the user enter any other input,
                                                        // the program will display the error message and go back to the
                                                        // Last choice user choose
                                                } else {
                                                        clearConsole();
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        System.out.println(
                                                                        "|             You can only enter Y or N             |");
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        Thread.sleep(3000);
                                                        clearConsole();
                                                        Category(choice);
                                                }
                                        } else {
                                                // this will display the category name
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "|                 Burger/Sandwich                   |");
                                                System.out.println(
                                                                "+---------------------------------------------------+\n");

                                                // this will display the item name, price, and stock
                                                System.out.println(String.format(format, "", "Item", "", "Qty", "Price",
                                                                ""));
                                                System.out.println(
                                                                "-----------------------------------------------------");
                                                for (int i = 0; i < Burger_Sandwich.length; i++) {
                                                        if (Burger_Sandwich_Stock[i] == 0) {
                                                                // if the specific item Stock is 0, the program will
                                                                // display the Soldout message
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Burger_Sandwich[i], "-",
                                                                                "Out of Stock", "", ""));

                                                        } else if (Burger_Sandwich_Price[i] == 0.0) {
                                                                // If the specific item price is 0.0, the program will
                                                                // display the free message
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Burger_Sandwich[i], "-",
                                                                                Burger_Sandwich_Stock[i], "Free", ""));

                                                        } else {
                                                                // if the item price or Stock is not 0, the program will
                                                                // display the item name,
                                                                // Stock and price
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Burger_Sandwich[i], "-",
                                                                                Burger_Sandwich_Stock[i],
                                                                                Burger_Sandwich_Price[i], "Php"));
                                                        }
                                                }
                                                System.out.println(String.format(format, "", "", "", "", "------------",
                                                                ""));
                                                System.out.println(String.format(format, "", "", "", "", "[0] Back",
                                                                ""));
                                                System.out.println(
                                                                "_____________________________________________________");

                                                // prompt the user to enter choice
                                                System.out.print("Enter your choice: ");
                                                int Bs_choice = Integer.parseInt(breader.readLine());

                                                // set the index to user choice - 1
                                                // ex: if the user choose 1, the index will be 0 to access the first
                                                // item
                                                int index = Bs_choice - 1;

                                                // set the Bs_quantity to 0
                                                int Bs_quantity = 0;

                                                // if the user enter 0, the program will go to the Menu method
                                                if (Bs_choice == 0) {
                                                        clearConsole();
                                                        Menu();

                                                } else {
                                                        // the program will check if the user enter a smaller or equal
                                                        // to the list of item
                                                        if (Bs_choice <= Burger_Sandwich.length) {

                                                                // if the item is soldout, the program will display the
                                                                // Out of Stock message
                                                                if (Burger_Sandwich_Stock[index] == 0) {
                                                                        clearConsole();
                                                                        System.out.println(
                                                                                        "+---------------------------------------------------+");
                                                                        System.out.println(
                                                                                        "|   Sorry, the item you selected is Out of Stock    |");
                                                                        System.out.println(
                                                                                        "+---------------------------------------------------+");
                                                                        Thread.sleep(3000);
                                                                        clearConsole();

                                                                        // after the message is displayed, the program
                                                                        // will go back to the Category 1
                                                                        Category(1);
                                                                } else {
                                                                        // if the item is not soldout, the program will
                                                                        // display the item name,
                                                                        clearConsole();
                                                                        System.out.println(
                                                                                        "----------------- You have selected -----------------");
                                                                        System.out.println(String.format(format, "",
                                                                                        "Item", "", "Qty", "Price",
                                                                                        ""));
                                                                        System.out.println(
                                                                                        "-----------------------------------------------------");
                                                                        System.out.println(String.format(format, "",
                                                                                        Burger_Sandwich[index], "",
                                                                                        Burger_Sandwich_Stock[index],
                                                                                        Burger_Sandwich_Price[index],
                                                                                        "Php"));
                                                                        System.out.println(
                                                                                        "-----------------------------------------------------");
                                                                        System.out.print(
                                                                                        "Enter the quantity you want to buy: ");
                                                                        Bs_quantity = Integer.parseInt(breader
                                                                                        .readLine());

                                                                        // if the user enter a bigger number than the
                                                                        // Stock,
                                                                        // the program will display the available Stock
                                                                        // and go back to the Category 1
                                                                        if (Bs_quantity > Burger_Sandwich_Stock[index]) {
                                                                                clearConsole();
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.println(
                                                                                                "Sorry, we only have [" +
                                                                                                                Burger_Sandwich_Stock[index] +
                                                                                                                "] " +
                                                                                                                Burger_Sandwich[index] +
                                                                                                                " in stock");
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                Thread.sleep(2000);
                                                                                clearConsole();
                                                                                Category(1);
                                                                        } else {
                                                                                // if the user enter a smaller or equal
                                                                                // to the Stock, the item will be bought
                                                                                clearConsole();
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.println("You ordered [" +
                                                                                                Bs_quantity + "] " +
                                                                                                Burger_Sandwich[index] +
                                                                                                "");

                                                                                // add 1 to the catergory choice to
                                                                                // recored the item that the user choose
                                                                                Burger_Sandwich_Choice[index]++;

                                                                                // the Bs_quantity will be subtracted
                                                                                // from the Stock
                                                                                Burger_Sandwich_Stock[index] -= Bs_quantity;

                                                                                // the Bs_quantity will be Added to the
                                                                                // order
                                                                                Burger_Sandwich_Order[index] += Bs_quantity;

                                                                                // set the check for order to true
                                                                                Check_for_Order[0] = true;

                                                                                // prompt the user to enter the next
                                                                                // choice
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.print(
                                                                                                "[1] Add more items\n[2] Choose a different category\n[0] Checkout\n");
                                                                                System.out.println(
                                                                                                "_____________________________________________________");
                                                                                System.out.print("Enter your choice: ");
                                                                                int add = Integer.parseInt(breader
                                                                                                .readLine());

                                                                                // if the user enter 1, the program will
                                                                                // go to the Category 1
                                                                                if (add == 1) {
                                                                                        clearConsole();
                                                                                        Category(1);
                                                                                        // if the user enter 2, the
                                                                                        // program will go to menu
                                                                                } else if (add == 2) {
                                                                                        clearConsole();

                                                                                        // before the program go to the
                                                                                        // menu, the program will check
                                                                                        // if item has stock
                                                                                        if (Burger_Sandwich_Stock[0] == 0 &&
                                                                                                        Burger_Sandwich_Stock[1] == 0 &&
                                                                                                        Burger_Sandwich_Stock[2] == 0 &&
                                                                                                        Burger_Sandwich_Stock[3] == 0 &&
                                                                                                        Burger_Sandwich_Stock[4] == 0) {

                                                                                                // if the item has no
                                                                                                // stock, set the
                                                                                                // soldout to true and
                                                                                                // the program will go
                                                                                                // to the menu
                                                                                                Soldout[0] = true;
                                                                                                Menu();
                                                                                        } else {
                                                                                                Menu();
                                                                                        }
                                                                                        // if the user enter 0, the
                                                                                        // program will go to the
                                                                                        // Checkout method
                                                                                } else if (add == 0) {
                                                                                        clearConsole();
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        System.out.println(
                                                                                                        "| Thank you Please wait while we process your order |");
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        int random = (int) (Math
                                                                                                        .random() *
                                                                                                        (100 - 2000 + 1)) +
                                                                                                        2000;
                                                                                        Thread.sleep(random);
                                                                                        Order_List();

                                                                                        // if the user enter a number
                                                                                        // that is not 0, 1 or 2,
                                                                                        // the program will display the
                                                                                        // error message and go back to
                                                                                        // the Category 1
                                                                                } else {
                                                                                        clearConsole();
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        System.out.println(
                                                                                                        "|     You can only enter either [1],[2] or [0]      |");
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        Thread.sleep(3000);
                                                                                        clearConsole();
                                                                                        Category(choice);
                                                                                }
                                                                        }
                                                                }

                                                                // if the user enter more than the number of the item,
                                                                // the program will display the error message and go
                                                                // back to the Category 1
                                                        } else {
                                                                clearConsole();
                                                                System.out.println(
                                                                                "+---------------------------------------------------+");
                                                                System.out.println(
                                                                                "|   Sorry, the item you selected is Not Available   |");
                                                                System.out.println(
                                                                                "+---------------------------------------------------+");
                                                                Thread.sleep(3000);
                                                                clearConsole();
                                                                Category(1);
                                                        }
                                                }
                                        }
                                        break;
                                case 2:
                                        if (Meals_Stock[0] == 0 && Meals_Stock[1] == 0 && Meals_Stock[2] == 0 &&
                                                        Meals_Stock[3] == 0 && Meals_Stock[4] == 0) {
                                                Soldout[1] = true;

                                                clearConsole();
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "|               This item is sold out               |");
                                                System.out.println(
                                                                "+---------------------------------------------------+\n");
                                                System.out.print("Do you want to add more items? (Y/N): ");
                                                String add = breader.readLine();

                                                if (add.equalsIgnoreCase("Y")) {
                                                        clearConsole();
                                                        Menu();
                                                } else if (add.equalsIgnoreCase("N")) {
                                                        clearConsole();
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        System.out.println(
                                                                        "| Thank you Please wait while we process your order |");
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        // Random delay between 0.1 to 2 seconds
                                                        int random = (int) (Math.random() * (2000 - 100 + 1) + 100);
                                                        Thread.sleep(random);
                                                        Order_List();
                                                } else {
                                                        clearConsole();
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        System.out.println(
                                                                        "|             You can only enter Y or N             |");
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        Thread.sleep(3000);
                                                        clearConsole();
                                                        Category(choice);
                                                }
                                        } else {
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "|                       Meals                       |");
                                                System.out.println(
                                                                "+---------------------------------------------------+\n");
                                                System.out.println(String.format(format, "", "Item", "", "Qty", "Price",
                                                                ""));
                                                System.out.println(
                                                                "-----------------------------------------------------");
                                                for (int i = 0; i < Meals.length; i++) {
                                                        if (Meals_Stock[i] == 0) {
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Meals[i], "-", "Out of Stock", "",
                                                                                ""));

                                                        } else if (Meals_Price[i] == 0.0) {
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Meals[i], "-", Meals_Stock[i],
                                                                                "Free", ""));

                                                        } else {
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Meals[i], "-", Meals_Stock[i],
                                                                                Meals_Price[i], "Php"));
                                                        }
                                                }
                                                System.out.println(String.format(format, "", "", "", "", "------------",
                                                                ""));
                                                System.out.println(String.format(format, "", "", "", "", "[0] Back",
                                                                ""));
                                                System.out.println(
                                                                "_____________________________________________________");
                                                System.out.print("Enter your choice: ");
                                                int M_choice = Integer.parseInt(breader.readLine());
                                                int index = M_choice - 1;
                                                int M_quantity = 0;
                                                if (M_choice == 0) {
                                                        clearConsole();
                                                        Menu();
                                                } else {
                                                        if (M_choice <= Meals.length) {
                                                                if (Meals_Stock[index] == 0) {
                                                                        clearConsole();
                                                                        System.out.println(
                                                                                        "+---------------------------------------------------+");
                                                                        System.out.println(
                                                                                        "|   Sorry, the item you selected is Out of Stock    |");
                                                                        System.out.println(
                                                                                        "+---------------------------------------------------+");
                                                                        Thread.sleep(3000);
                                                                        clearConsole();
                                                                        Category(2);
                                                                } else {
                                                                        clearConsole();
                                                                        System.out.println(
                                                                                        "----------------- You have selected -----------------");
                                                                        System.out.println(String.format(format, "",
                                                                                        "Item", "", "Qty", "Price",
                                                                                        ""));
                                                                        System.out.println(
                                                                                        "-----------------------------------------------------");
                                                                        System.out.println(String.format(format, "",
                                                                                        Meals[index], "",
                                                                                        Meals_Stock[index],
                                                                                        Meals_Price[index], "Php"));
                                                                        System.out.println(
                                                                                        "-----------------------------------------------------");
                                                                        System.out.print(
                                                                                        "Enter the quantity you want to buy: ");
                                                                        M_quantity = Integer.parseInt(breader
                                                                                        .readLine());

                                                                        if (M_quantity > Meals_Stock[index]) {
                                                                                clearConsole();
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.println(
                                                                                                "Sorry, we only have [" +
                                                                                                                Meals_Stock[index] +
                                                                                                                "] " +
                                                                                                                Meals[index] +
                                                                                                                " in stock");
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                Thread.sleep(2000);
                                                                                clearConsole();
                                                                                Category(2);
                                                                        } else {
                                                                                clearConsole();
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.println("You ordered [" +
                                                                                                M_quantity + "] " +
                                                                                                Meals[index] + "\n");
                                                                                Meals_Choice[index]++;
                                                                                Meals_Stock[index] -= M_quantity;
                                                                                Meals_Order[index] += M_quantity;
                                                                                Check_for_Order[1] = true;

                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.print(
                                                                                                "[1] Add more items\n[2] Choose a different category\n[0] Checkout\n");
                                                                                System.out.println(
                                                                                                "_____________________________________________________");
                                                                                System.out.print("Enter your choice: ");
                                                                                int add = Integer.parseInt(breader
                                                                                                .readLine());
                                                                                if (add == 1) {
                                                                                        clearConsole();
                                                                                        Category(2);
                                                                                } else if (add == 2) {
                                                                                        clearConsole();
                                                                                        if (Meals_Stock[0] == 0 &&
                                                                                                        Meals_Stock[1] == 0 &&
                                                                                                        Meals_Stock[2] == 0 &&
                                                                                                        Meals_Stock[3] == 0 &&
                                                                                                        Meals_Stock[4] == 0) {
                                                                                                Soldout[1] = true;
                                                                                                Menu();
                                                                                        } else {
                                                                                                Menu();
                                                                                        }
                                                                                } else if (add == 0) {
                                                                                        clearConsole();
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        System.out.println(
                                                                                                        "| Thank you Please wait while we process your order |");
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        int random = (int) (Math
                                                                                                        .random() *
                                                                                                        (100 - 2000 + 1)) +
                                                                                                        2000;
                                                                                        Thread.sleep(random);
                                                                                        Order_List();
                                                                                } else {
                                                                                        clearConsole();
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        System.out.println(
                                                                                                        "|     You can only enter either [1],[2] or [0]      |");
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        Thread.sleep(3000);
                                                                                        clearConsole();
                                                                                        Category(choice);
                                                                                }
                                                                        }
                                                                }

                                                        } else {
                                                                clearConsole();
                                                                System.out.println(
                                                                                "+---------------------------------------------------+");
                                                                System.out.println(
                                                                                "|   Sorry, the item you selected is Not Available   |");
                                                                System.out.println(
                                                                                "+---------------------------------------------------+");
                                                                Thread.sleep(3000);
                                                                clearConsole();
                                                                Category(2);
                                                        }
                                                }
                                        }
                                        break;
                                case 3:
                                        if (Drinks_Stock[0] == 0 && Drinks_Stock[1] == 0 && Drinks_Stock[2] == 0 &&
                                                        Drinks_Stock[3] == 0 && Drinks_Stock[4] == 0 &&
                                                        Drinks_Stock[5] == 0) {
                                                Soldout[2] = true;

                                                clearConsole();
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "|               This item is sold out               |");
                                                System.out.println(
                                                                "+---------------------------------------------------+\n");
                                                System.out.print("Do you want to add more items? (Y/N): ");
                                                String add = breader.readLine();

                                                if (add.equalsIgnoreCase("Y")) {
                                                        clearConsole();
                                                        Menu();
                                                } else if (add.equalsIgnoreCase("N")) {
                                                        clearConsole();
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        System.out.println(
                                                                        "| Thank you Please wait while we process your order |");
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        // Random delay between 0.1 to 2 seconds
                                                        int random = (int) (Math.random() * (2000 - 100 + 1) + 100);
                                                        Thread.sleep(random);
                                                        Order_List();
                                                } else {
                                                        clearConsole();
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        System.out.println(
                                                                        "|             You can only enter Y or N             |");
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        Thread.sleep(3000);
                                                        clearConsole();
                                                        Category(choice);
                                                }
                                        } else {
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "|                      Drinks                       |");
                                                System.out.println(
                                                                "+---------------------------------------------------+\n");
                                                System.out.println(String.format(format, "", "Item", "", "Qty", "Price",
                                                                ""));
                                                System.out.println(
                                                                "-----------------------------------------------------");

                                                for (int i = 0; i < Drinks.length; i++) {
                                                        if (Drinks_Stock[i] == 0) {
                                                                // if the stock is 0, the item is sold out and print out
                                                                // the out of stock message
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Drinks[i], "-", "Out of Stock",
                                                                                "", ""));

                                                        } else if (Drinks_Price[i] == 0.0) {
                                                                // If the price is 0.0, the item is free and print out
                                                                // the free message
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Drinks[i], "-", Drinks_Stock[i],
                                                                                "Free", ""));

                                                        } else {
                                                                // if the stock is not 0, and or the price is not 0.0,
                                                                // print out the item
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Drinks[i], "-", Drinks_Stock[i],
                                                                                Drinks_Price[i], "Php"));
                                                        }
                                                }
                                                System.out.println(String.format(format, "", "", "", "", "------------",
                                                                ""));
                                                System.out.println(String.format(format, "", "", "", "", "[0] Back",
                                                                ""));
                                                System.out.println(
                                                                "_____________________________________________________");
                                                System.out.print("Enter your choice: ");
                                                int Ds_choice = Integer.parseInt(breader.readLine());
                                                int index = Ds_choice - 1;
                                                int Ds_quantity = 0;
                                                if (Ds_choice == 0) {
                                                        clearConsole();
                                                        Menu();
                                                } else {
                                                        if (Ds_choice <= Drinks.length) {
                                                                if (Drinks_Stock[index] == 0) {
                                                                        clearConsole();
                                                                        System.out.println(
                                                                                        "+---------------------------------------------------+");
                                                                        System.out.println(
                                                                                        "|   Sorry, the item you selected is Out of Stock    |");
                                                                        System.out.println(
                                                                                        "+---------------------------------------------------+");
                                                                        Thread.sleep(3000);
                                                                        clearConsole();
                                                                        Category(3);
                                                                } else {
                                                                        clearConsole();
                                                                        System.out.println(
                                                                                        "----------------- You have selected -----------------");
                                                                        System.out.println(String.format(format, "",
                                                                                        "Item", "", "Qty", "Price",
                                                                                        ""));
                                                                        System.out.println(
                                                                                        "-----------------------------------------------------");
                                                                        System.out.println(String.format(format, "",
                                                                                        Drinks[index], "",
                                                                                        Drinks_Stock[index],
                                                                                        Drinks_Price[index], "Php"));
                                                                        System.out.println(
                                                                                        "-----------------------------------------------------");
                                                                        System.out.print(
                                                                                        "Enter the quantity you want to buy: ");
                                                                        Ds_quantity = Integer.parseInt(breader
                                                                                        .readLine());

                                                                        if (Ds_quantity > Drinks_Stock[index]) {
                                                                                clearConsole();
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.println(
                                                                                                "Sorry, we only have [" +
                                                                                                                Drinks_Stock[index] +
                                                                                                                "] " +
                                                                                                                Drinks[index] +
                                                                                                                " in stock");
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                Thread.sleep(2000);
                                                                                clearConsole();
                                                                                Category(3);
                                                                        } else {
                                                                                clearConsole();
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.println("You ordered [" +
                                                                                                Ds_quantity + "] " +
                                                                                                Drinks[index] + "\n");
                                                                                Drinks_Choice[index]++;
                                                                                Drinks_Stock[index] -= Ds_quantity;
                                                                                Drinks_Order[index] += Ds_quantity;
                                                                                Check_for_Order[2] = true;

                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.print(
                                                                                                "[1] Add more items\n[2] Choose a different category\n[0] Checkout\n");
                                                                                System.out.println(
                                                                                                "_____________________________________________________");
                                                                                System.out.print("Enter your choice: ");
                                                                                int add = Integer.parseInt(breader
                                                                                                .readLine());
                                                                                if (add == 1) {
                                                                                        clearConsole();
                                                                                        Category(3);
                                                                                } else if (add == 2) {
                                                                                        clearConsole();
                                                                                        if (Drinks_Stock[0] == 0 &&
                                                                                                        Drinks_Stock[1] == 0 &&
                                                                                                        Drinks_Stock[2] == 0 &&
                                                                                                        Drinks_Stock[3] == 0 &&
                                                                                                        Drinks_Stock[4] == 0 &&
                                                                                                        Drinks_Stock[5] == 0) {
                                                                                                Soldout[2] = true;
                                                                                                Menu();
                                                                                        } else {
                                                                                                Menu();
                                                                                        }
                                                                                } else if (add == 0) {
                                                                                        clearConsole();
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        System.out.println(
                                                                                                        "| Thank you Please wait while we process your order |");
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        int random = (int) (Math
                                                                                                        .random() *
                                                                                                        (100 - 2000 + 1)) +
                                                                                                        2000;
                                                                                        Thread.sleep(random);
                                                                                        Order_List();
                                                                                } else {
                                                                                        clearConsole();
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        System.out.println(
                                                                                                        "|     You can only enter either [1],[2] or [0]      |");
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        Thread.sleep(3000);
                                                                                        clearConsole();
                                                                                        Category(choice);
                                                                                }
                                                                        }
                                                                }

                                                        } else {
                                                                clearConsole();
                                                                System.out.println(
                                                                                "+---------------------------------------------------+");
                                                                System.out.println(
                                                                                "|   Sorry, the item you selected is Not Available   |");
                                                                System.out.println(
                                                                                "+---------------------------------------------------+");
                                                                Thread.sleep(3000);
                                                                clearConsole();
                                                                Category(3);
                                                        }
                                                }
                                        }
                                        break;
                                case 4:
                                        if (Desserts_Stock[0] == 0 && Desserts_Stock[1] == 0 &&
                                                        Desserts_Stock[2] == 0) {
                                                Soldout[3] = true;

                                                clearConsole();
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "|               This item is sold out               |");
                                                System.out.println(
                                                                "+---------------------------------------------------+\n");
                                                System.out.print("Do you want to add more items? (Y/N): ");
                                                String add = breader.readLine();

                                                if (add.equalsIgnoreCase("Y")) {
                                                        clearConsole();
                                                        Menu();
                                                } else if (add.equalsIgnoreCase("N")) {
                                                        clearConsole();
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        System.out.println(
                                                                        "| Thank you Please wait while we process your order |");
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        // Random delay between 0.1 to 2 seconds
                                                        int random = (int) (Math.random() * (2000 - 100 + 1) + 100);
                                                        Thread.sleep(random);
                                                        Order_List();
                                                } else {
                                                        clearConsole();
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        System.out.println(
                                                                        "|             You can only enter Y or N             |");
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        Thread.sleep(3000);
                                                        clearConsole();
                                                        Category(choice);
                                                }
                                        } else {
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "|                      Dessert                      |");
                                                System.out.println(
                                                                "+---------------------------------------------------+\n");
                                                System.out.println(String.format(format, "", "Item", "", "Qty", "Price",
                                                                ""));
                                                System.out.println(
                                                                "-----------------------------------------------------");
                                                for (int i = 0; i < Desserts.length; i++) {
                                                        if (Desserts_Stock[i] == 0) {
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Desserts[i], "-", "Out of Stock",
                                                                                "", ""));

                                                        } else if (Meals_Price[i] == 0.0) {
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Desserts[i], "-",
                                                                                Desserts_Stock[i], "Free", ""));

                                                        } else {
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Desserts[i], "-",
                                                                                Desserts_Stock[i], Desserts_Price[i],
                                                                                "Php"));
                                                        }
                                                }
                                                System.out.println(String.format(format, "", "", "", "", "------------",
                                                                ""));
                                                System.out.println(String.format(format, "", "", "", "", "[0] Back",
                                                                ""));
                                                System.out.println(
                                                                "_____________________________________________________");
                                                System.out.print("Enter your choice: ");
                                                int D_choice = Integer.parseInt(breader.readLine());
                                                int index = D_choice - 1;
                                                int D_quantity = 0;
                                                if (D_choice == 0) {
                                                        clearConsole();
                                                        Menu();
                                                } else {
                                                        if (D_choice <= Desserts.length) {
                                                                if (Desserts_Stock[index] == 0) {
                                                                        clearConsole();
                                                                        System.out.println(
                                                                                        "+---------------------------------------------------+");
                                                                        System.out.println(
                                                                                        "|   Sorry, the item you selected is Out of Stock    |");
                                                                        System.out.println(
                                                                                        "+---------------------------------------------------+");
                                                                        Thread.sleep(3000);
                                                                        clearConsole();
                                                                        Category(4);
                                                                } else {
                                                                        clearConsole();
                                                                        System.out.println(
                                                                                        "----------------- You have selected -----------------");
                                                                        System.out.println(String.format(format, "",
                                                                                        "Item", "", "Qty", "Price",
                                                                                        ""));
                                                                        System.out.println(
                                                                                        "-----------------------------------------------------");
                                                                        System.out.println(String.format(format, "",
                                                                                        Desserts[index], "",
                                                                                        Desserts_Stock[index],
                                                                                        Desserts_Price[index], "Php"));
                                                                        System.out.println(
                                                                                        "-----------------------------------------------------");
                                                                        System.out.print(
                                                                                        "Enter the quantity you want to buy: ");
                                                                        D_quantity = Integer.parseInt(breader
                                                                                        .readLine());

                                                                        if (D_quantity > Desserts_Stock[index]) {
                                                                                clearConsole();
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.println(
                                                                                                "Sorry, we only have [" +
                                                                                                                Desserts_Stock[index] +
                                                                                                                "] " +
                                                                                                                Desserts[index] +
                                                                                                                " in stock");
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                Thread.sleep(2000);
                                                                                clearConsole();
                                                                                Category(4);
                                                                        } else {
                                                                                clearConsole();
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.println("You ordered [" +
                                                                                                D_quantity + "] " +
                                                                                                Desserts[index] + "\n");
                                                                                Desserts_Choice[index]++;
                                                                                Desserts_Stock[index] -= D_quantity;
                                                                                Desserts_Order[index] += D_quantity;
                                                                                Check_for_Order[3] = true;

                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.println(
                                                                                                "[1] Add more items\n[2] Choose a different category\n[0] Checkout\n");
                                                                                System.out.println(
                                                                                                "_____________________________________________________");
                                                                                System.out.print("Enter your choice: ");
                                                                                int add = Integer.parseInt(breader
                                                                                                .readLine());
                                                                                if (add == 1) {
                                                                                        clearConsole();
                                                                                        Category(4);
                                                                                } else if (add == 2) {
                                                                                        clearConsole();
                                                                                        if (Desserts_Stock[0] == 0 &&
                                                                                                        Desserts_Stock[1] == 0 &&
                                                                                                        Desserts_Stock[2] == 0) {
                                                                                                Soldout[3] = true;
                                                                                                Menu();
                                                                                        } else {
                                                                                                Menu();
                                                                                        }
                                                                                } else if (add == 0) {
                                                                                        clearConsole();
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        System.out.println(
                                                                                                        "| Thank you Please wait while we process your order |");
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        int random = (int) (Math
                                                                                                        .random() *
                                                                                                        (100 - 2000 + 1)) +
                                                                                                        2000;
                                                                                        Thread.sleep(random);
                                                                                        Order_List();
                                                                                } else {
                                                                                        clearConsole();
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        System.out.println(
                                                                                                        "|     You can only enter either [1],[2] or [0]      |");
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        Thread.sleep(3000);
                                                                                        clearConsole();
                                                                                        Category(choice);
                                                                                }
                                                                        }
                                                                }

                                                        } else {
                                                                clearConsole();
                                                                System.out.println(
                                                                                "+---------------------------------------------------+");
                                                                System.out.println(
                                                                                "|   Sorry, the item you selected is Not Available   |");
                                                                System.out.println(
                                                                                "+---------------------------------------------------+");
                                                                Thread.sleep(3000);
                                                                clearConsole();
                                                                Category(4);
                                                        }
                                                }
                                        }
                                        break;
                                case 5:
                                        if (Extras_Stock[0] == 0 && Extras_Stock[1] == 0 && Extras_Stock[2] == 0) {
                                                Soldout[4] = true;

                                                clearConsole();
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "|               This item is sold out               |");
                                                System.out.println(
                                                                "+---------------------------------------------------+\n");
                                                System.out.print("Do you want to add more items? (Y/N): ");
                                                String add = breader.readLine();

                                                if (add.equalsIgnoreCase("Y")) {
                                                        clearConsole();
                                                        Menu();
                                                } else if (add.equalsIgnoreCase("N")) {
                                                        clearConsole();
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        System.out.println(
                                                                        "| Thank you Please wait while we process your order |");
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        // Random delay between 0.1 to 2 seconds
                                                        int random = (int) (Math.random() * (2000 - 100 + 1) + 100);
                                                        Thread.sleep(random);
                                                        Order_List();
                                                } else {
                                                        clearConsole();
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        System.out.println(
                                                                        "|              You can only enter Y or N            |");
                                                        System.out.println(
                                                                        "+---------------------------------------------------+");
                                                        Thread.sleep(3000);
                                                        clearConsole();
                                                        Category(choice);
                                                }
                                        } else {
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "|                      Extras                       |");
                                                System.out.println(
                                                                "+---------------------------------------------------+\n");
                                                System.out.println(String.format(format, "", "Item", "", "Qty", "Price",
                                                                ""));
                                                System.out.println(
                                                                "-----------------------------------------------------");
                                                for (int i = 0; i < Extras.length; i++) {
                                                        if (Extras_Stock[i] == 0) {
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Extras[i], "-", "Out of Stock",
                                                                                "", ""));
                                                        } else if (Extras_Price[i] == 0.0) {
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Extras[i], "-", Extras_Stock[i],
                                                                                "Free", ""));
                                                        } else {
                                                                System.out.println(String.format(format, "[" + (i + 1) +
                                                                                "] ", Extras[i], "-", Extras_Stock[i],
                                                                                Extras_Price[i], "Php"));
                                                        }
                                                }
                                                System.out.println(String.format(format, "", "", "", "", "------------",
                                                                ""));
                                                System.out.println(String.format(format, "", "", "", "", "[0] Back",
                                                                ""));
                                                System.out.println(
                                                                "_____________________________________________________");
                                                System.out.print("Enter your choice: ");
                                                int E_choice = Integer.parseInt(breader.readLine());
                                                int index = E_choice - 1;
                                                int E_quantity = 0;
                                                if (E_choice == 0) {
                                                        clearConsole();
                                                        Menu();
                                                } else {
                                                        if (E_choice <= Extras.length) {
                                                                if (Extras_Stock[index] == 0) {
                                                                        clearConsole();
                                                                        System.out.println(
                                                                                        "+---------------------------------------------------+");
                                                                        System.out.println(
                                                                                        "|   Sorry, the item you selected is Out of Stock    |");
                                                                        System.out.println(
                                                                                        "+---------------------------------------------------+");
                                                                        Thread.sleep(3000);
                                                                        clearConsole();
                                                                        Category(5);
                                                                } else {
                                                                        clearConsole();
                                                                        System.out.println(
                                                                                        "----------------- You have selected -----------------");
                                                                        System.out.println(String.format(format, "",
                                                                                        "Item", "", "Qty", "Price",
                                                                                        ""));
                                                                        System.out.println(
                                                                                        "-----------------------------------------------------");
                                                                        System.out.println(String.format(format, "",
                                                                                        Extras[index], "",
                                                                                        Extras_Stock[index],
                                                                                        Extras_Price[index], "Php"));
                                                                        System.out.println(
                                                                                        "-----------------------------------------------------");
                                                                        System.out.print(
                                                                                        "Enter the quantity you want to buy: ");
                                                                        E_quantity = Integer.parseInt(breader
                                                                                        .readLine());

                                                                        if (E_quantity > Extras_Stock[index]) {
                                                                                clearConsole();
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.println(
                                                                                                "Sorry, we only have [" +
                                                                                                                Extras_Stock[index] +
                                                                                                                "] " +
                                                                                                                Extras[index] +
                                                                                                                " in stock");
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                Thread.sleep(2000);
                                                                                clearConsole();
                                                                                Category(5);
                                                                        } else {
                                                                                clearConsole();
                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.println("You ordered [" +
                                                                                                E_quantity + "] " +
                                                                                                Extras[index] + "\n");
                                                                                Extras_Choice[index]++;
                                                                                Extras_Stock[index] -= E_quantity;
                                                                                Extras_Order[index] += E_quantity;
                                                                                Check_for_Order[4] = true;

                                                                                System.out.println(
                                                                                                "-----------------------------------------------------");
                                                                                System.out.print(
                                                                                                "[1] Add more items\n[2] Choose a different category\n[0] Checkout\n");
                                                                                System.out.println(
                                                                                                "_____________________________________________________");
                                                                                System.out.print("Enter your choice: ");
                                                                                int add = Integer.parseInt(breader
                                                                                                .readLine());
                                                                                if (add == 1) {
                                                                                        clearConsole();
                                                                                        Category(5);
                                                                                } else if (add == 2) {
                                                                                        clearConsole();
                                                                                        if (Extras_Stock[0] == 0 &&
                                                                                                        Extras_Stock[1] == 0 &&
                                                                                                        Extras_Stock[2] == 0) {
                                                                                                Soldout[4] = true;
                                                                                                Menu();
                                                                                        } else {
                                                                                                Menu();
                                                                                        }
                                                                                } else if (add == 0) {
                                                                                        clearConsole();
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        System.out.println(
                                                                                                        "| Thank you Please wait while we process your order |");
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        int random = (int) (Math
                                                                                                        .random() *
                                                                                                        (100 - 2000 + 1)) +
                                                                                                        2000;
                                                                                        Thread.sleep(random);
                                                                                        Order_List();
                                                                                } else {
                                                                                        clearConsole();
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        System.out.println(
                                                                                                        "|     You can only enter either [1],[2] or [0]      |");
                                                                                        System.out.println(
                                                                                                        "+---------------------------------------------------+");
                                                                                        Thread.sleep(3000);
                                                                                        clearConsole();
                                                                                        Category(choice);
                                                                                }
                                                                        }
                                                                }

                                                        } else {
                                                                clearConsole();
                                                                System.out.println(
                                                                                "+---------------------------------------------------+");
                                                                System.out.println(
                                                                                "|   Sorry, the item you selected is Not Available   |");
                                                                System.out.println(
                                                                                "+---------------------------------------------------+");
                                                                Thread.sleep(3000);
                                                                clearConsole();
                                                                Category(5);
                                                        }
                                                }
                                        }
                                        break;

                                case 6:
                                        // befor proceeding to the category, the program will check if one or more items
                                        // has been ordered
                                        // if none of the items has been ordered, the program will set the category as
                                        // not available
                                        if (Check_for_Order[0] == true || Check_for_Order[1] == true ||
                                                        Check_for_Order[2] == true || Check_for_Order[3] == true ||
                                                        Check_for_Order[4] == true) {

                                                clearConsole();
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "|Please wait...                                     |");
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                // Random delay between 0.1 to 1 seconds
                                                int wait = (int) (Math.random() * (1000 - 100 + 1) + 100);
                                                Thread.sleep(wait);
                                                Return_Menu();
                                        } else {
                                                Category(9);
                                        }
                                        break;
                                case 7:
                                        // same as the category 6, it will set the category as not available if none of
                                        // the items has been sold out
                                        if (Soldout[0] == true || Soldout[1] == true || Soldout[2] == true ||
                                                        Soldout[3] == true || Soldout[4] == true) {
                                                clearConsole();
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "| Thank you Please wait while we process your order |");
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                // Random delay between 0.1 to 2 seconds
                                                int Loading = (int) (Math.random() * (2000 - 100 + 1) + 100);
                                                Thread.sleep(Loading);
                                                Order_List();
                                        } else {
                                                Category(9);
                                        }

                                        break;

                                default:
                                        clearConsole();
                                        System.out.println("+---------------------------------------------------+");
                                        System.out.println("|   Sorry, the item you selected is Not Available   |");
                                        System.out.println("+---------------------------------------------------+");
                                        Thread.sleep(3000);
                                        clearConsole();
                                        Menu();
                                        break;
                        }

                } catch (Exception e) {
                        clearConsole();
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Error " + e.getMessage());
                        System.out.println("Error Code: [G4M4]");
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Please Only Enter a Numiric Value");
                        Thread.sleep(3000);
                        clearConsole();
                        Category(choice);
                }
        }

        private static void Order_List() throws IOException, InterruptedException {
                String format = "%1$-18s %2$-3s %3$-12s %4$-5s %5$-3s";

                // this will recaculate the total price of the order if you return an item
                if (Return_to_Order == true) {
                        Subtotal = 0;
                        Total = 0;
                        for (int i = 0; i < Product_Total_Price.length; i++) {
                                Product_Total_Price[i] = 0;
                        }
                }

                clearConsole();
                System.out.println("+---------------------------------------------------+");
                System.out.println("|                    YOUR ORDER                     |");
                System.out.println("+---------------------------------------------------+");
                System.out.println(String.format(format, "Item", "", "Qty", "Price", ""));
                System.out.println("-----------------------------------------------------");

                // [Find the item category that has order]
                // this will check if the item has been Choose by the user,
                // if 1 or more items has been chosen,
                // it will set the empty choice to false.
                // same goes for the other categories
                for (int i = 0; i < Burger_Sandwich_Choice.length; i++) {
                        if (Burger_Sandwich_Choice[i] != 0) {
                                Empty_Choice[0] = false;
                        }
                }
                for (int i = 0; i < Meals_Choice.length; i++) {
                        if (Meals_Choice[i] != 0) {
                                Empty_Choice[1] = false;
                        }
                }
                for (int i = 0; i < Drinks_Choice.length; i++) {
                        if (Drinks_Choice[i] != 0) {
                                Empty_Choice[2] = false;
                        }
                }
                for (int i = 0; i < Desserts_Choice.length; i++) {
                        if (Desserts_Choice[i] != 0) {
                                Empty_Choice[3] = false;
                        }
                }
                for (int i = 0; i < Extras_Choice.length; i++) {
                        if (Extras_Choice[i] != 0) {
                                Empty_Choice[4] = false;
                        }
                }

                // [Print items User Ordered]
                // if the Empty_Choice is false, it will print the item and the item that user
                // has ordered
                if (Empty_Choice[0] == false) {
                        for (int i = 0; i < Burger_Sandwich_Order.length; i++) {
                                // if the item is have a order, it will print the item and the item that user
                                // has ordered
                                if (Burger_Sandwich_Order[i] != 0) {
                                        // it will print the item and the item that user has ordered
                                        System.out.println(String.format(format, Burger_Sandwich[i], "",
                                                        Burger_Sandwich_Order[i], Burger_Sandwich_Price[i], "Php"));
                                        // it will total the price of the item that user has ordered
                                        Product_Total_Price[0] += Burger_Sandwich_Price[i] * Burger_Sandwich_Order[i];
                                        // Random delay between .2 and .75 seconds
                                        int random = (int) (Math.random() * (750 - 200 + 1)) + 200;
                                        Thread.sleep(random);
                                }
                        }
                } else {
                        // System.out.println("No Items Ordered");
                }
                if (Empty_Choice[1] == false) {
                        for (int i = 0; i < Meals_Order.length; i++) {
                                if (Meals_Order[i] != 0) {
                                        System.out.println(String.format(format, Meals[i], "", Meals_Order[i],
                                                        Meals_Price[i], "Php"));
                                        Product_Total_Price[1] += Meals_Price[i] * Meals_Order[i];
                                        int random = (int) (Math.random() * (750 - 200 + 1)) + 200;
                                        Thread.sleep(random);
                                }
                        }
                } else {
                        // System.out.println("No Items Ordered");
                }

                if (Empty_Choice[2] == false) {
                        for (int i = 0; i < Drinks_Order.length; i++) {
                                if (Drinks_Order[i] != 0) {
                                        System.out.println(String.format(format, Drinks[i], "", Drinks_Order[i],
                                                        Drinks_Price[i], "Php"));
                                        Product_Total_Price[2] += Drinks_Price[i] * Drinks_Order[i];
                                        int random = (int) (Math.random() * (750 - 200 + 1)) + 200;
                                        Thread.sleep(random);
                                }
                        }
                } else {
                        // System.out.println("No Items Ordered");
                }

                if (Empty_Choice[3] == false) {
                        for (int i = 0; i < Desserts_Order.length; i++) {
                                if (Desserts_Order[i] != 0) {
                                        System.out.println(String.format(format, Desserts[i], "", Desserts_Order[i],
                                                        Desserts_Price[i], "Php"));
                                        Product_Total_Price[3] += Desserts_Price[i] * Desserts_Order[i];
                                        int random = (int) (Math.random() * (750 - 200 + 1)) + 200;
                                        Thread.sleep(random);
                                }
                        }
                } else {
                        // System.out.println("No Items Ordered");
                }

                if (Empty_Choice[4] == false) {
                        for (int i = 0; i < Extras_Order.length; i++) {
                                if (Extras_Order[i] != 0) {
                                        System.out.println(String.format(format, Extras[i], "", Extras_Order[i],
                                                        Extras_Price[i], "Php"));
                                        Product_Total_Price[4] += Extras_Price[i] * Extras_Order[i];
                                        int random = (int) (Math.random() * (750 - 200 + 1)) + 200;
                                        Thread.sleep(random);
                                }
                        }
                } else {
                        // System.out.println("No Items Ordered");
                }

                // this will total the price of the item that user has ordered
                Subtotal = Product_Total_Price[0] + Product_Total_Price[1] + Product_Total_Price[2] +
                                Product_Total_Price[3] + Product_Total_Price[4];
                // this will total the subtotal + the tax
                Total = (Subtotal + (int) (Subtotal * Tax));
                // tax is 10% of the subtotal

                // this will print the subtotal, tax, and total
                System.out.println();
                System.out.println("-----------------------------------------------------");
                System.out.println(String.format(format, "Subtotal", "", "", Subtotal, "Php"));
                Thread.sleep(200);
                System.out.println(String.format(format, "Vat (10%)", "", "", (double) (Subtotal * Tax), "Php"));
                Thread.sleep(200);
                System.out.println(String.format(format, "Total", "", "", Total, "Php"));
                Thread.sleep(200);
                Transaction_type();
        }

        private static void Transaction_type() throws IOException, InterruptedException {
                BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));

                try {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("[1] Pay Items   [2] Cancel Order   [3] Return Items");
                        System.out.println("_____________________________________________________");
                        System.out.print("Enter Choice: ");
                        int Trans_Type = Integer.parseInt(breader.readLine());

                        switch (Trans_Type) {
                                case 1:
                                        clearConsole();
                                        System.out.println("+---------------------------------------------------+");
                                        System.out.println("|     At the moment, you can only pay with cash     |");
                                        System.out.println("|        We apologize for any inconvenience.        |");
                                        System.out.println("+---------------------------------------------------+\n");
                                        // after printing items that user has ordered,
                                        // and subtotal, tax, and total, it will go to the transaction method
                                        transaction();
                                        break;
                                case 2:
                                        clearConsole();
                                        System.out.println("+---------------------------------------------------+");
                                        System.out.println("|    If you cancel your order, you will lose all    |");
                                        System.out.println("|   the items you ordered and you will be returned  |");
                                        System.out.println("|                 to the main menu.                 |");
                                        System.out.println("+---------------------------------------------------+\n");
                                        System.out.println("_____________________________________________________");
                                        System.out.print("Are you sure you want to cancel your order? (Y/N): ");
                                        String Cancel_Order = breader.readLine();

                                        if (Cancel_Order.equalsIgnoreCase("Y")) {
                                                clearConsole();
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "|           You have cancelled your order.          |");
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                Thread.sleep(2500);
                                                clearConsole();
                                                Trans_to_menu = true;
                                                Buy_Again();
                                        } else if (Cancel_Order.equalsIgnoreCase("N")) {
                                                clearConsole();
                                                Return_to_Order = true;
                                                Order_List();
                                        } else {
                                                clearConsole();
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                System.out.println(
                                                                "|                   Invalid Input                   |");
                                                System.out.println(
                                                                "+---------------------------------------------------+");
                                                Thread.sleep(2500);
                                                clearConsole();
                                                Transaction_type();
                                        }
                                        break;
                                case 3:
                                        clearConsole();
                                        System.out.println("+---------------------------------------------------+");
                                        System.out.println("|               Please wait a moment                |");
                                        System.out.println("+---------------------------------------------------+");
                                        int random = (int) (Math.random() * (2500 - 1000 + 1)) + 1000;
                                        Thread.sleep(random);
                                        clearConsole();
                                        Return_from_Transaction = true;
                                        Return_Menu();
                                        break;
                                default:
                                        clearConsole();
                                        System.out.println("+---------------------------------------------------+");
                                        System.out.println("|                   Invalid Input                   |");
                                        System.out.println("+---------------------------------------------------+");
                                        Thread.sleep(500);
                                        clearConsole();
                                        Transaction_type();
                                        break;
                        }
                } catch (Exception e) {
                        clearConsole();
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Error " + e.getMessage());
                        System.out.println("Error Code: [G4M6]");
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Please Only Enter a Numiric Value");
                        Thread.sleep(2000);
                        clearConsole();
                        Transaction_type();
                }

        }

        private static void transaction() throws IOException, InterruptedException {
                BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));

                try {
                        // this will ask the user what payment method they want to use
                        System.out.println("+---------------------------------------------------+");
                        System.out.println("|                      PAYMENT                      |");
                        System.out.println("+---------------------------------------------------+");
                        System.out.println("Please select your payment method");
                        System.out.println("1. Cash \n2. Credit Card \n3. Maya \n4. Gcash");
                        System.out.println("-----------------------------------------------------");
                        // prompt the user to enter the payment method
                        System.out.print("Enter your choice: ");
                        Payment_Options = Integer.parseInt(breader.readLine());
                        // after the user enters the payment method, it will go to the payment method
                        payment_method(Payment_Options);
                } catch (Exception e) {
                        // if the user input is not a number, it will show the error message
                        // and go back to the transaction method
                        clearConsole();
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Error " + e.getMessage());
                        System.out.println("Error Code: [G4M6]");
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Please Only Enter a Numiric Value");
                        Thread.sleep(3000);
                        clearConsole();
                        transaction();
                }

        }

        private static void payment_method(int payment) throws IOException, InterruptedException {
                BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));

                switch (payment) {
                        // if the user choose to pay with cash, it will go to the cash method (case 1)
                        case 1:
                                // this will print the amount of cash that user has to pay
                                clearConsole();
                                System.out.println("+---------------------------------------------------+");
                                System.out.println("|                   Pay with Cash                   |");
                                System.out.println("+---------------------------------------------------+\n");
                                System.out.println("-----------------------------------------------------");
                                System.out.println("Your total is: " + Total + " Php");
                                System.out.println("-----------------------------------------------------");
                                // prompt the user to enter the amount of cash that user has to pay
                                System.out.print("Enter the amount: ");
                                cash = Integer.parseInt(breader.readLine());
                                // calculate the change
                                change = cash - Total;

                                if (cash < Total) {
                                        // if the cash that user has entered is less than the total, it will show the
                                        // Insufficient message
                                        // and go back to the payment method (cash)
                                        clearConsole();
                                        System.out.println("+---------------------------------------------------+");
                                        System.out.println("|                Insufficient amount                |");
                                        System.out.println("+---------------------------------------------------+");
                                        Thread.sleep(2000);
                                        clearConsole();
                                        payment_method(1);
                                } else {
                                        Receipt();
                                }
                                break;
                        case 2:
                                // if the user enter credit card payment method, it will only show the
                                // unavailable message
                                // same as Maya and Gcash method, and it will go back to the transaction method
                                clearConsole();
                                System.out.println("+---------------------------------------------------+");
                                System.out.println("|  Thanks for using the Credit Card payment method  |");
                                System.out.println("|     At the moment, this method is unavailable     |");
                                System.out.println("+---------------------------------------------------+\n");
                                Thread.sleep(4500);
                                clearConsole();
                                transaction();

                                break;
                        case 3:
                                clearConsole();
                                System.out.println("+---------------------------------------------------+");
                                System.out.println("|     Thanks for using the Maya payment method      |");
                                System.out.println("|     At the moment, this method is unavailable     |");
                                System.out.println("+---------------------------------------------------+\n");
                                Thread.sleep(4500);
                                clearConsole();
                                transaction();
                                break;
                        case 4:
                                clearConsole();
                                System.out.println("+---------------------------------------------------+");
                                System.out.println("|     Thanks for using the Gcash payment method     |");
                                System.out.println("|     At the moment, this method is unavailable     |");
                                System.out.println("+---------------------------------------------------+\n");
                                Thread.sleep(4500);
                                clearConsole();
                                transaction();
                                break;
                        default:
                                clearConsole();
                                System.out.println("+---------------------------------------------------+");
                                System.out.println("|     Please enter a valid payment method number    |");
                                System.out.println("+---------------------------------------------------+\n");
                                Thread.sleep(3000);
                                clearConsole();
                                transaction();
                                break;
                }
        }

        private static void Receipt() throws InterruptedException, IOException {

                String format = "%1$-18s %2$-15s %3$-5s %4$-5s %5$-3s";

                clearConsole();
                System.out.println("+---------------------------------------------------+");
                System.out.println("|                   YOUR RECIEPT                    |");
                System.out.println("+---------------------------------------------------+");

                // this will print the user name, and what payment method they have chosen
                System.out.println("Costumer Name: " + name);
                System.out.println("Payment Method: Cash");

                System.out.println("_____________________________________________________");
                System.out.println(String.format(format, "Item", "", "Qty", "Price", ""));
                System.out.println("-----------------------------------------------------");

                // [Print the items User Ordered]
                // this will print the items that user has ordered
                // same as the other categories
                if (Empty_Choice[0] == false) {
                        for (int i = 0; i < Burger_Sandwich_Order.length; i++) {
                                if (Burger_Sandwich_Order[i] != 0) {
                                        System.out.println(String.format(format, Burger_Sandwich[i], "",
                                                        Burger_Sandwich_Order[i], Burger_Sandwich_Price[i], "Php"));
                                        int random = (int) (Math.random() * (750 - 200 + 1)) + 200;
                                        Thread.sleep(random);
                                }
                        }
                } else {
                        // System.out.println("No Items Ordered");
                }

                if (Empty_Choice[1] == false) {
                        for (int i = 0; i < Meals_Order.length; i++) {
                                if (Meals_Order[i] != 0) {
                                        System.out.println(String.format(format, Meals[i], "", Meals_Order[i],
                                                        Meals_Price[i], "Php"));
                                        int random = (int) (Math.random() * (750 - 200 + 1)) + 200;
                                        Thread.sleep(random);
                                }
                        }
                } else {
                        // System.out.println("No Items Ordered");
                }

                if (Empty_Choice[2] == false) {
                        for (int i = 0; i < Drinks_Order.length; i++) {
                                if (Drinks_Order[i] != 0) {
                                        System.out.println(String.format(format, Drinks[i], "", Drinks_Order[i],
                                                        Drinks_Price[i], "Php"));
                                        int random = (int) (Math.random() * (750 - 200 + 1)) + 200;
                                        Thread.sleep(random);
                                }
                        }
                } else {
                        // System.out.println("No Items Ordered");
                }

                if (Empty_Choice[3] == false) {
                        for (int i = 0; i < Desserts_Order.length; i++) {
                                if (Desserts_Order[i] != 0) {
                                        System.out.println(String.format(format, Desserts[i], "", Desserts_Order[i],
                                                        Desserts_Price[i], "Php"));
                                        int random = (int) (Math.random() * (750 - 200 + 1)) + 200;
                                        Thread.sleep(random);
                                }
                        }
                } else {
                        // System.out.println("No Items Ordered");
                }

                if (Empty_Choice[4] == false) {
                        for (int i = 0; i < Extras_Order.length; i++) {
                                if (Extras_Order[i] != 0) {
                                        System.out.println(String.format(format, Extras[i], "", Extras_Order[i],
                                                        Extras_Price[i], "Php"));
                                        int random = (int) (Math.random() * (750 - 200 + 1)) + 200;
                                        Thread.sleep(random);
                                }
                        }
                } else {
                        // System.out.println("No Items Ordered");
                }

                String format_for_amounts = "%1$-8s %2$-27s %3$-8s %4$-3s";

                // this will print the total price of the items,
                // and the cash that user has entered and the change
                System.out.println("-----------------------------------------------------");
                System.out.println(String.format(format_for_amounts, "Subtotal", "", Subtotal, "Php"));
                Thread.sleep(200);
                System.out.println(String.format(format_for_amounts, "Vat", "", (Subtotal * Tax), "Php"));
                Thread.sleep(200);
                System.out.println(String.format(format_for_amounts, "Total", "", Total, "Php"));
                Thread.sleep(200);
                System.out.println(String.format(format_for_amounts, "Cash", "", cash, "Php"));
                System.out.println(String.format(format_for_amounts, "", "", "------------", ""));
                Thread.sleep(500);
                System.out.println(String.format(format_for_amounts, "Change", "", change, "Php"));
                System.out.println("-----------------------------------------------------\n");
                Thread.sleep(200);
                System.out.println("+---------------------------------------------------+");
                System.out.println("|            Thank you for your purchase            |");
                System.out.println("+---------------------------------------------------+");
                Thread.sleep(5000);
                // after the user has finished the transaction,
                // the system will ask if the user wants to order again
                Buy_Again();
        }

        private static void Buy_Again() throws IOException, InterruptedException {
                BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));

                String choice = "";

                if (Trans_to_menu == true) {
                        choice = "Y";
                } else {
                        // this will ask the user if he wants to order again
                        System.out.print("Do you want to order again? (Y/N): ");
                        choice = breader.readLine();
                }
                if (choice.equalsIgnoreCase("Y")) {
                        // if the user wants to order again, it will clear the screen and go back to the
                        // main menu
                        clearConsole();
                        System.out.println("+---------------------------------------------------+");
                        System.out.println("|                Please wait a moment               |");
                        System.out.println("+---------------------------------------------------+");

                        // Random delay between .3 and 3 seconds to simulate a loading time
                        int random_Time = (int) (Math.random() * (3000 - 300 + 1)) + 300;
                        Thread.sleep(random_Time);
                        clearConsole();

                        // [Reset all the arrays to thier default values]
                        // this will reset all the arrays to thier default values
                        for (int i = 0; i < Burger_Sandwich_Order.length; i++) {
                                Burger_Sandwich_Order[i] = 0;
                                Burger_Sandwich_Choice[i] = 0;
                                Burger_Sandwich_Stock[i] = 25;
                        }
                        for (int i = 0; i < Meals_Order.length; i++) {
                                Meals_Order[i] = 0;
                                Meals_Choice[i] = 0;
                                Meals_Stock[i] = 25;
                        }
                        for (int i = 0; i < Drinks_Order.length; i++) {
                                Drinks_Order[i] = 0;
                                Drinks_Choice[i] = 0;
                                Drinks_Stock[i] = 50;
                        }
                        for (int i = 0; i < Desserts_Order.length; i++) {
                                Desserts_Order[i] = 0;
                                Desserts_Choice[i] = 0;
                                Desserts_Stock[i] = 15;
                        }
                        for (int i = 0; i < Extras_Order.length; i++) {
                                Extras_Order[i] = 0;
                                Extras_Choice[i] = 0;
                                Extras_Stock[i] = 10;
                        }
                        for (int i = 0; i < Soldout.length; i++) {
                                Soldout[i] = false;
                        }

                        // [Reset the values of the variables]
                        for (int i = 0; i < Soldout.length; i++) {
                                Soldout[i] = false;
                        }
                        for (int i = 0; i < Check_for_Order.length; i++) {
                                Check_for_Order[i] = false;
                        }
                        for (int i = 0; i < Empty_Choice.length; i++) {
                                Empty_Choice[i] = true;
                        }
                        for (int i = 0; i < Return_Empty_Category.length; i++) {
                                Return_Empty_Category[i] = true;
                        }
                        for (int i = 0; i < Product_Total_Price.length; i++) {
                                Product_Total_Price[i] = 0;
                        }

                        Return_from_Transaction = false;
                        Return_to_Order = false;
                        Trans_to_menu = false;

                        Subtotal = 0;
                        Total = 0;
                        cash = 0;
                        change = 0;

                        // 95% chance of getting a out of stock item
                        int Out_of_Stock_Items = (int) (Math.random() * (100 - 1 + 1)) + 1;
                        if (Out_of_Stock_Items <= 95) {
                                // [1 Random item from each category will be out of stock]
                                for (int i = 0; i < Burger_Sandwich_Order.length; i++) {
                                        int random = (int) (Math.random() * (10 - 1 + 1)) + 1;
                                        if (random == 1) {
                                                Burger_Sandwich_Stock[i] = 0;
                                        }
                                }
                                for (int i = 0; i < Meals_Order.length; i++) {
                                        int random = (int) (Math.random() * (10 - 1 + 1)) + 1;
                                        if (random == 1) {
                                                Meals_Stock[i] = 0;
                                        }
                                }
                                for (int i = 0; i < Drinks_Order.length; i++) {
                                        int random = (int) (Math.random() * (10 - 1 + 1)) + 1;
                                        if (random == 1) {
                                                Drinks_Stock[i] = 0;
                                        }
                                }
                                for (int i = 0; i < Desserts_Order.length; i++) {
                                        int random = (int) (Math.random() * (10 - 1 + 1)) + 1;
                                        if (random == 1) {
                                                Desserts_Stock[i] = 0;
                                        }
                                }
                                for (int i = 0; i < Extras_Order.length; i++) {
                                        int random = (int) (Math.random() * (10 - 1 + 1)) + 1;
                                        if (random == 1) {
                                                Extras_Stock[i] = 0;
                                        }
                                }

                        }
                        // back to the main menu
                        Menu();

                } else if (choice.equalsIgnoreCase("N")) {
                        // if the user does not want to order again, it will clear the screen and it
                        // will exit the program
                        clearConsole();
                        System.out.println("+---------------------------------------------------+");
                        System.out.println("|            Thank you for your purchase            |");
                        System.out.println("|             The program will now exit             |");
                        System.out.println("+---------------------------------------------------+");
                        // set the end_program variable to true
                        end_program = true;
                        Thread.sleep(3000);
                        clearConsole();
                        // it will show the startup details before exiting the program
                        Information();
                        Thread.sleep(1500);
                        System.exit(0);
                } else {
                        // if the user enters an invalid choice, it will ask the user to enter a valid
                        // choice
                        clearConsole();
                        System.out.println("+---------------------------------------------------+");
                        System.out.println("|           Please enter a valid choice             |");
                        System.out.println("+---------------------------------------------------+");
                        Thread.sleep(2000);
                        clearConsole();
                        Buy_Again();
                }
        }

        private static void Return_Menu() throws IOException, InterruptedException {
                BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));

                clearConsole();
                // this will show the return menu
                System.out.println("+---------------------------------------------------+");
                System.out.println("|                    Return Item                    |");
                System.out.println("+---------------------------------------------------+\n");
                System.out.println("\nPlease select the category you want to return from");
                System.out.println("-----------------------------------------------------");
                System.out.println("[ 1 ] Burger/Sandwich\n[ 2 ] Meals\n[ 3 ] Drinks\n[ 4 ] Desserts\n[ 5 ] Extra");
                System.out.println("-----------------------------");
                if (Return_from_Transaction == true) {
                        System.out.println("[ 0 ] Return to your Order");
                } else {
                        System.out.println("[ 0 ] Return to the Main Menu");

                }
                System.out.println("-----------------------------------------------------");
                // this will ask the user to enter a valid choice
                System.out.print("Please enter your choice: ");
                int choice = Integer.parseInt(breader.readLine());

                // if the return transaction is true, it will return to the order menu
                if (choice == 0 && Return_from_Transaction == true) {
                        // set the return to order variable to true
                        Return_to_Order = true;

                        // This will check if the user have any item in the order list
                        boolean noItem = true;
                        // loop through the check_for_order array
                        for (int i = 0; i < Check_for_Order.length; i++) {
                                // if 1 or more check_for_order array is equal to true, it will set the noItem
                                // variable to false
                                if (Check_for_Order[i] != false) {
                                        noItem = false;
                                }
                        }
                        // if the noItem variable is false, it will return to the order menu
                        if (noItem == false) {
                                Order_List();
                        } else {
                                clearConsole();
                                System.out.println("+---------------------------------------------------+");
                                System.out.println("|    Your order does not appear to have any items   |");
                                System.out.println("+---------------------------------------------------+");
                                Thread.sleep(3500);
                                clearConsole();
                                Menu();
                        }
                } else {
                        // this will check if the user return an item from sold-out category
                        // if the statement is true it will reset the sold-out variable to false and
                        // remove the sold-out label in the menu
                        for (int Bs = 0; Bs < Burger_Sandwich_Order.length; Bs++) {
                                if (Burger_Sandwich_Order[Bs] != 0) {
                                        Soldout[0] = false;
                                }
                        }
                        for (int M = 0; M < Meals_Order.length; M++) {
                                if (Meals_Order[M] != 0) {
                                        Soldout[1] = false;
                                }
                        }
                        for (int D = 0; D < Drinks_Order.length; D++) {
                                if (Drinks_Order[D] != 0) {
                                        Soldout[2] = false;
                                }
                        }
                        for (int Ds = 0; Ds < Desserts_Order.length; Ds++) {
                                if (Desserts_Order[Ds] != 0) {
                                        Soldout[3] = false;
                                }
                        }
                        for (int E = 0; E < Extras_Order.length; E++) {
                                if (Extras_Order[E] != 0) {
                                        Soldout[4] = false;
                                }
                        }

                        // after the user enters a valid choice, it will show the return choice
                        Return_Choice(choice);
                }

        }

        private static void Return_Choice(int choice) throws IOException, InterruptedException {
                BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));
                String format = "%1$-18s %2$-5s %3$-5s %4$-5s";

                // this wil check if the user have ordered any item from the category
                // same as the other category,
                for (int i = 0; i < Burger_Sandwich_Order.length; i++) {
                        if (Burger_Sandwich_Order[i] != 0) {
                                Return_Empty_Category[0] = false;
                        }
                }

                for (int i = 0; i < Meals_Order.length; i++) {
                        if (Meals_Order[i] != 0) {
                                Return_Empty_Category[1] = false;
                        }
                }

                for (int i = 0; i < Drinks_Order.length; i++) {
                        if (Drinks_Order[i] != 0) {
                                Return_Empty_Category[2] = false;
                        }
                }

                for (int i = 0; i < Desserts_Order.length; i++) {
                        if (Desserts_Order[i] != 0) {
                                Return_Empty_Category[3] = false;
                        }
                }

                for (int i = 0; i < Extras_Order.length; i++) {
                        if (Extras_Order[i] != 0) {
                                Return_Empty_Category[4] = false;
                        }
                }

                try {
                        switch (choice) {

                                case 0:
                                        // if the user wants to go back to the main menu,
                                        // it will clear the screen and it will show the main menu
                                        clearConsole();
                                        Menu();
                                        break;
                                case 1:
                                        // if the user wants to return an item from the Burger/Sandwich category
                                        if (Return_Empty_Category[0] == true) {
                                                // if the user has not ordered any item from the category, it will show
                                                // the following message
                                                // and it will return to the Return menu
                                                clearConsole();
                                                System.out.println("+---------------------------------------------------+");
                                                System.out.println("|           There are no items to return            |");
                                                System.out.println("+---------------------------------------------------+");
                                                Thread.sleep(2000);
                                                clearConsole();
                                                Return_Menu();
                                        } else {
                                                // if the user has ordered any item from the category, it will show the
                                                // following message
                                                clearConsole();
                                                System.out.println("+---------------------------------------------------+");
                                                System.out.println("|                  Burger/Sandwich                  |");
                                                System.out.println("+---------------------------------------------------+\n");
                                                System.out.println("    " + String.format(format, "Item", "", "Qty", "",
                                                                ""));
                                                System.out.println("-----------------------------------------------------");

                                                // it will show the ordered item from the category
                                                for (int i = 0; i < Burger_Sandwich.length; i++) {
                                                        // it will only show the item that has been ordered
                                                        if (Burger_Sandwich_Order[i] != 0) {
                                                                System.out.println("[" + (i + 1) + "] " + String.format(
                                                                                format, Burger_Sandwich[i], "",
                                                                                Burger_Sandwich_Order[i], "", ""));
                                                        }
                                                }
                                                System.out.println("--------");
                                                System.out.println("[0] Back");
                                                System.out.println("-----------------------------------------------------");

                                                // it will ask the user to enter a valid choice
                                                System.out.print("Please enter your choice: ");
                                                int Bs_item = Integer.parseInt(breader.readLine());
                                                int item_index_B = Bs_item - 1;

                                                if (Bs_item == 0) {
                                                        // if the Bs_item is 0, it will return to the Return menu
                                                        clearConsole();
                                                        Return_Menu();
                                                } else {
                                                        // if the Bs_item is less than 0 or greater than the number of
                                                        // items,
                                                        // it will show the following message and it will return to the
                                                        // Return Choice 1
                                                        if (item_index_B < 0 ||
                                                                        item_index_B > Burger_Sandwich_Order.length) {
                                                                clearConsole();
                                                                System.out.println("+---------------------------------------------------+");
                                                                System.out.println("|            Please enter a valid choice            |");
                                                                System.out.println("+---------------------------------------------------+");
                                                                Thread.sleep(2000);
                                                                clearConsole();
                                                                Return_Choice(1);
                                                        } else if (Burger_Sandwich_Order[item_index_B] == 0) {

                                                                // if the item user selected that has not in the list of
                                                                // ordered item,
                                                                // it will show the following message and it will return
                                                                // to the Return Choice 1
                                                                clearConsole();
                                                                System.out.println("+---------------------------------------------------+");
                                                                System.out.println("|            The Item you want to return            |");
                                                                System.out.println("|                is not in your order               |");
                                                                System.out.println("+---------------------------------------------------+");
                                                                Thread.sleep(2000);
                                                                clearConsole();
                                                                Return_Choice(1);
                                                        } else {
                                                                // if the item user selected is in the list of ordered
                                                                // item,
                                                                // it will show the following message
                                                                clearConsole();
                                                                System.out.println("-----------------------------------------------------");
                                                                // it will show the quantity of the item user selected
                                                                System.out.println("Your current item quantity of " +
                                                                                Burger_Sandwich[item_index_B] + " is " +
                                                                                Burger_Sandwich_Order[item_index_B]);
                                                                System.out.println("-----------------------------------------------------");
                                                                // it will ask the user to enter the quantity of the
                                                                // item user selected
                                                                System.out.print("Enter the quantity you want to return: ");
                                                                int qty = Integer.parseInt(breader.readLine());

                                                                if (qty > Burger_Sandwich_Order[item_index_B]) {

                                                                        // if the quantity of the item user selected is
                                                                        // greater than the quantity of the item user
                                                                        // selected,
                                                                        // it will show the following message and it
                                                                        // will return to the Return Choice 1
                                                                        clearConsole();
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        System.out.println("|          You cannot return more than you          |");
                                                                        System.out.println("|          have ordered. Please try again.          |");
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Choice(1);
                                                                }
                                                                if (qty <= 0) {

                                                                        // if the quantity of the item user selected is
                                                                        // less than or equal to 0,
                                                                        // it will show the following message and it
                                                                        // will return to the Return Choice 1
                                                                        clearConsole();
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        System.out.println("|          You cannot return less than you          |");
                                                                        System.out.println("|          have ordered. Please try again.          |");
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Choice(1);
                                                                } else {
                                                                        // it will subtract the quantity of the item
                                                                        // user selected from the quantity of the item
                                                                        // user ordered
                                                                        Burger_Sandwich_Order[item_index_B] -= qty;
                                                                        // the quantity of the item user ordered will
                                                                        // return to the item stock
                                                                        Burger_Sandwich_Stock[item_index_B] += qty;

                                                                        // if all the items in the current category has
                                                                        // been returned, set the check for order to
                                                                        // false
                                                                        if (Burger_Sandwich_Order[0] == 0 &&
                                                                                        Burger_Sandwich_Order[1] == 0 &&
                                                                                        Burger_Sandwich_Order[2] == 0 &&
                                                                                        Burger_Sandwich_Order[3] == 0 &&
                                                                                        Burger_Sandwich_Order[4] == 0) {
                                                                                Check_for_Order[0] = false;
                                                                        }

                                                                        // it will check if item_index_B is greater than
                                                                        // or equal to 5.
                                                                        // if it is greater than or equal to 5, it will
                                                                        // set the soldout status
                                                                        // of the item to false same as the item user
                                                                        // ordered
                                                                        if (item_index_B >= 5) {
                                                                                if (Soldout[0] == true) {
                                                                                        Soldout[0] = false;
                                                                                }

                                                                                if (Drinks_Order[0] == 0) {
                                                                                        Return_Empty_Category[0] = true;
                                                                                }
                                                                        } else {
                                                                                if (Soldout[item_index_B] == true) {
                                                                                        Soldout[item_index_B] = false;
                                                                                }

                                                                                if (Drinks_Order[item_index_B] == 0) {
                                                                                        Return_Empty_Category[item_index_B] = true;
                                                                                }
                                                                        }

                                                                        clearConsole();
                                                                        System.out.println("-----------------------------------------------------");
                                                                        System.out.println("You have successfully returned [" +
                                                                                                        qty + "] " +
                                                                                                        Burger_Sandwich[item_index_B]);
                                                                        System.out.println("-----------------------------------------------------");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        // after printing the message, it will return to
                                                                        // the Return menu
                                                                        Return_Menu();
                                                                }
                                                        }
                                                }
                                        }
                                        break;
                                case 2:
                                        if (Return_Empty_Category[1] == true) {
                                                clearConsole();
                                                System.out.println("+---------------------------------------------------+");
                                                System.out.println("|            There are no items to return           |");
                                                System.out.println("+---------------------------------------------------+");
                                                Thread.sleep(2000);
                                                clearConsole();
                                                Return_Menu();
                                        } else {
                                                clearConsole();
                                                System.out.println("+---------------------------------------------------+");
                                                System.out.println("|                       Meals                       |");
                                                System.out.println("+---------------------------------------------------+\n");
                                                System.out.println("    " + String.format(format, "Item", "", "Qty", "",
                                                                ""));
                                                System.out.println("-----------------------------------------------------");

                                                for (int i = 0; i < Meals.length; i++) {
                                                        if (Meals_Order[i] != 0) {
                                                                System.out.println("[" + (i + 1) + "] " + String.format(
                                                                                format, Meals[i], "", Meals_Order[i],
                                                                                "", ""));
                                                        }
                                                }
                                                System.out.println("--------");
                                                System.out.println("[0] Back");
                                                System.out.println("-----------------------------------------------------");
                                                System.out.print("Please enter your choice: ");
                                                int M_item = Integer.parseInt(breader.readLine());
                                                int item_index_M = M_item - 1;

                                                if (M_item == 0) {
                                                        clearConsole();
                                                        Return_Menu();
                                                } else {
                                                        if (item_index_M < 0 || item_index_M > Meals_Order.length) {
                                                                clearConsole();
                                                                System.out.println("+---------------------------------------------------+");
                                                                System.out.println("|            Please enter a valid choice            |");
                                                                System.out.println("+---------------------------------------------------+");
                                                                Thread.sleep(2000);
                                                                clearConsole();
                                                                Return_Choice(2);

                                                        } else if (Meals_Order[item_index_M] == 0) {
                                                                clearConsole();
                                                                System.out.println("+---------------------------------------------------+");
                                                                System.out.println("|            The Item you want to return            |");
                                                                System.out.println("|                is not in your order               |");
                                                                System.out.println("+---------------------------------------------------+");
                                                                Thread.sleep(2000);
                                                                clearConsole();
                                                                Return_Choice(2);

                                                        } else {
                                                                clearConsole();
                                                                System.out.println("-----------------------------------------------------");
                                                                System.out.println("Your current item quantity of " +
                                                                                Meals[item_index_M] + " is " +
                                                                                Meals_Order[item_index_M]);
                                                                System.out.println("-----------------------------------------------------");
                                                                System.out.print("Enter the quantity you want to return: ");

                                                                int qty = Integer.parseInt(breader.readLine());
                                                                if (qty > Meals_Order[item_index_M]) {
                                                                        clearConsole();
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        System.out.println("|          You cannot return more than you          |");
                                                                        System.out.println("|          have ordered. Please try again.          |");
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Choice(2);

                                                                } else if (qty <= 0) {
                                                                        clearConsole();
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        System.out.println("|          You cannot return less than you          |");
                                                                        System.out.println("|          have ordered. Please try again.          |");
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Choice(2);
                                                                } else {
                                                                        Meals_Order[item_index_M] -= qty;
                                                                        Meals_Stock[item_index_M] += qty;

                                                                        if (Meals_Order[0] == 0 &&
                                                                                        Meals_Order[1] == 0 &&
                                                                                        Meals_Order[2] == 0 &&
                                                                                        Meals_Order[3] == 0 &&
                                                                                        Meals_Order[4] == 0) {
                                                                                Check_for_Order[1] = false;
                                                                        }

                                                                        if (item_index_M >= 5) {
                                                                                if (Soldout[1] == true) {
                                                                                        Soldout[1] = false;
                                                                                }

                                                                                if (Meals_Order[1] == 0) {
                                                                                        Return_Empty_Category[1] = true;
                                                                                }
                                                                        } else {
                                                                                if (Soldout[item_index_M] == true) {
                                                                                        Soldout[item_index_M] = false;
                                                                                }

                                                                                if (Meals_Order[item_index_M] == 0) {
                                                                                        Return_Empty_Category[item_index_M] = true;
                                                                                }
                                                                        }

                                                                        clearConsole();
                                                                        System.out.println("-----------------------------------------------------");
                                                                        System.out.println("You have successfully returned [" +
                                                                                                        qty + "] " +
                                                                                                        Meals[item_index_M]);
                                                                        System.out.println("-----------------------------------------------------");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Menu();
                                                                }
                                                        }
                                                }
                                        }
                                        break;
                                case 3:
                                        if (Return_Empty_Category[2] == true) {
                                                clearConsole();
                                                System.out.println("+---------------------------------------------------+");
                                                System.out.println("|            There are no items to return           |");
                                                System.out.println("+---------------------------------------------------+");
                                                Thread.sleep(2000);
                                                clearConsole();
                                                Return_Menu();
                                        } else {
                                                clearConsole();
                                                System.out.println("+---------------------------------------------------+");
                                                System.out.println("|                      Drinks                       |");
                                                System.out.println("+---------------------------------------------------+\n");
                                                System.out.println("    " + String.format(format, "Item", "", "Qty", "",
                                                                ""));
                                                System.out.println("-----------------------------------------------------");

                                                for (int i = 0; i < Drinks.length; i++) {
                                                        if (Drinks_Order[i] != 0) {
                                                                System.out.println("[" + (i + 1) + "] " + String.format(
                                                                                format, Drinks[i], "", Drinks_Order[i],
                                                                                "", ""));
                                                        }
                                                }
                                                System.out.println("--------");
                                                System.out.println("[0] Back");
                                                System.out.println("-----------------------------------------------------");
                                                System.out.print("Please enter your choice: ");
                                                int Ds_item = Integer.parseInt(breader.readLine());
                                                int item_index_Ds = Ds_item - 1;

                                                if (Ds_item == 0) {
                                                        clearConsole();
                                                        Return_Menu();
                                                } else {
                                                        if (item_index_Ds < 0 || item_index_Ds > Drinks_Order.length) {
                                                                clearConsole();
                                                                System.out.println("+---------------------------------------------------+");
                                                                System.out.println("|             Please enter a valid choice           |");
                                                                System.out.println("+---------------------------------------------------+");
                                                                Thread.sleep(2000);
                                                                clearConsole();
                                                                Return_Choice(3);

                                                        } else if (Drinks_Order[item_index_Ds] == 0) {
                                                                clearConsole();
                                                                System.out.println("+---------------------------------------------------+");
                                                                System.out.println("|            The Item you want to return            |");
                                                                System.out.println("|                is not in your order               |");
                                                                System.out.println("+---------------------------------------------------+");
                                                                Thread.sleep(2000);
                                                                clearConsole();
                                                                Return_Choice(3);

                                                        } else {
                                                                clearConsole();
                                                                System.out.println("-----------------------------------------------------");
                                                                System.out.println("Your current item quantity of " +
                                                                                Drinks[item_index_Ds] + " is " +
                                                                                Drinks_Order[item_index_Ds]);
                                                                System.out.println("-----------------------------------------------------");
                                                                System.out.print("Enter the quantity you want to return: ");
                                                                int qty = Integer.parseInt(breader.readLine());

                                                                if (qty > Drinks_Order[item_index_Ds]) {
                                                                        clearConsole();
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        System.out.println("|          You cannot return more than you          |");
                                                                        System.out.println("|          have ordered. Please try again.          |");
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Choice(3);
                                                                } else if (qty <= 0) {
                                                                        clearConsole();
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        System.out.println("|          You cannot return less than you          |");
                                                                        System.out.println("|          have ordered. Please try again.          |");
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Choice(3);
                                                                } else {
                                                                        Drinks_Order[item_index_Ds] -= qty;
                                                                        Drinks_Stock[item_index_Ds] += qty;

                                                                        if (Drinks_Order[0] == 0 &&
                                                                                        Drinks_Order[1] == 0 &&
                                                                                        Drinks_Order[2] == 0 &&
                                                                                        Drinks_Order[3] == 0 &&
                                                                                        Drinks_Order[4] == 0 &&
                                                                                        Drinks_Order[5] == 0) {
                                                                                Check_for_Order[2] = false;
                                                                        }

                                                                        if (item_index_Ds >= 5) {
                                                                                if (Soldout[2] == true) {
                                                                                        Soldout[2] = false;
                                                                                }

                                                                                if (Drinks_Order[2] == 0) {
                                                                                        Return_Empty_Category[2] = true;
                                                                                }
                                                                        } else {
                                                                                if (Soldout[item_index_Ds] == true) {
                                                                                        Soldout[item_index_Ds] = false;
                                                                                }

                                                                                if (Drinks_Order[item_index_Ds] == 0) {
                                                                                        Return_Empty_Category[item_index_Ds] = true;
                                                                                }
                                                                        }

                                                                        clearConsole();
                                                                        System.out.println("-----------------------------------------------------");
                                                                        System.out.println("You have successfully returned [" +
                                                                                                        qty + "] " +
                                                                                                        Drinks[item_index_Ds]);
                                                                        System.out.println("-----------------------------------------------------");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Menu();

                                                                }
                                                        }
                                                }
                                        }
                                        break;
                                case 4:
                                        if (Return_Empty_Category[3] == true) {
                                                clearConsole();
                                                System.out.println("+---------------------------------------------------+");
                                                System.out.println("|         There are no items to return              |");
                                                System.out.println("+---------------------------------------------------+");
                                                Thread.sleep(2000);
                                                clearConsole();
                                                Return_Menu();
                                        } else {
                                                clearConsole();
                                                System.out.println("+---------------------------------------------------+");
                                                System.out.println("|                     Desserts                      |");
                                                System.out.println("+---------------------------------------------------+\n");
                                                System.out.println("    " + String.format(format, "Item", "", "Qty", "",
                                                                ""));
                                                System.out.println("-----------------------------------------------------");

                                                for (int i = 0; i < Desserts.length; i++) {
                                                        if (Desserts_Order[i] != 0) {
                                                                System.out.println("[" + (i + 1) + "] " + String.format(
                                                                                format, Desserts[i], "",
                                                                                Desserts_Order[i], "", ""));
                                                        }
                                                }

                                                System.out.println("--------");
                                                System.out.println("[0] Back");
                                                System.out.println("-----------------------------------------------------");
                                                System.out.print("Please enter your choice: ");
                                                int D_item = Integer.parseInt(breader.readLine());
                                                int item_index_D = D_item - 1;

                                                if (D_item == 0) {
                                                        clearConsole();
                                                        Return_Menu();
                                                } else {
                                                        if (item_index_D < 0 || item_index_D > Desserts_Order.length) {
                                                                clearConsole();
                                                                System.out.println("+---------------------------------------------------+");
                                                                System.out.println("|            Please enter a valid choice            |");
                                                                System.out.println("+---------------------------------------------------+");
                                                                Thread.sleep(2000);
                                                                clearConsole();
                                                                Return_Choice(4);
                                                        } else if (Desserts_Order[item_index_D] == 0) {
                                                                clearConsole();
                                                                System.out.println("+---------------------------------------------------+");
                                                                System.out.println("|            The Item you want to return            |");
                                                                System.out.println("|                is not in your order               |");
                                                                System.out.println("+---------------------------------------------------+");
                                                                Thread.sleep(2000);
                                                                clearConsole();
                                                                Return_Choice(4);
                                                        } else {
                                                                clearConsole();
                                                                System.out.println("-----------------------------------------------------");
                                                                System.out.println("Your current item quantity of " +
                                                                                Desserts[item_index_D] + " is " +
                                                                                Desserts_Order[item_index_D]);
                                                                System.out.println("-----------------------------------------------------");
                                                                System.out.print("Enter the quantity you want to return: ");
                                                                int qty = Integer.parseInt(breader.readLine());
                                                                if (qty > Desserts_Order[item_index_D]) {
                                                                        clearConsole();
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        System.out.println("|          You cannot return more than you          |");
                                                                        System.out.println("|          have ordered. Please try again.          |");
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Choice(4);
                                                                } else if (qty <= 0) {
                                                                        clearConsole();
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        System.out.println("|          You cannot return less than you          |");
                                                                        System.out.println("|          have ordered. Please try again.          |");
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Choice(4);
                                                                } else {
                                                                        Desserts_Order[item_index_D] -= qty;
                                                                        Desserts_Stock[item_index_D] += qty;

                                                                        if (Desserts_Order[0] == 0 &&
                                                                                        Desserts_Order[1] == 0 &&
                                                                                        Desserts_Order[2] == 0) {
                                                                                Check_for_Order[3] = false;
                                                                        }

                                                                        if (item_index_D >= 3) {
                                                                                if (Soldout[3] == true) {
                                                                                        Soldout[3] = false;
                                                                                }

                                                                                if (Desserts_Order[3] == 0) {
                                                                                        Return_Empty_Category[3] = true;
                                                                                }
                                                                        } else {
                                                                                if (Soldout[item_index_D] == true) {
                                                                                        Soldout[item_index_D] = false;
                                                                                }

                                                                                if (Desserts_Order[item_index_D] == 0) {
                                                                                        Return_Empty_Category[item_index_D] = true;
                                                                                }
                                                                        }

                                                                        clearConsole();
                                                                        System.out.println("-----------------------------------------------------");
                                                                        System.out.println("You have successfully returned [" +
                                                                                                        qty + "] " +
                                                                                                        Desserts[item_index_D]);
                                                                        System.out.println("-----------------------------------------------------");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Menu();
                                                                }
                                                        }
                                                }
                                        }

                                        break;
                                case 5:
                                        if (Return_Empty_Category[4] == true) {
                                                clearConsole();
                                                System.out.println("+---------------------------------------------------+");
                                                System.out.println("|            There are no items to return           |");
                                                System.out.println("+---------------------------------------------------+");
                                                Thread.sleep(2000);
                                                clearConsole();
                                                Return_Menu();
                                        } else {
                                                clearConsole();
                                                System.out.println("+---------------------------------------------------+");
                                                System.out.println("|                      Extras                       |");
                                                System.out.println("+---------------------------------------------------+\n");
                                                System.out.println("    " + String.format(format, "Item", "", "Qty", "",
                                                                ""));
                                                System.out.println("-----------------------------------------------------");

                                                for (int i = 0; i < Extras.length; i++) {
                                                        if (Extras_Order[i] != 0) {
                                                                System.out.println("[" + (i + 1) + "] " + String.format(
                                                                                format, Extras[i], "", Extras_Order[i],
                                                                                "", ""));
                                                        }
                                                }

                                                System.out.println("--------");
                                                System.out.println("[0] Back");
                                                System.out.println("-----------------------------------------------------");
                                                System.out.print("Please enter your choice: ");
                                                int E_item = Integer.parseInt(breader.readLine());
                                                int item_index_E = E_item - 1;

                                                if (E_item == 0) {
                                                        clearConsole();
                                                        Return_Menu();
                                                } else {
                                                        if (item_index_E < 0 || item_index_E > Extras_Order.length) {
                                                                clearConsole();
                                                                System.out.println("+---------------------------------------------------+");
                                                                System.out.println("|            Please enter a valid choice            |");
                                                                System.out.println("+---------------------------------------------------+");
                                                                Thread.sleep(2000);
                                                                clearConsole();
                                                                Return_Choice(5);

                                                        } else if (Extras_Order[item_index_E] == 0) {
                                                                clearConsole();
                                                                System.out.println("+---------------------------------------------------+");
                                                                System.out.println("|            The Item you want to return            |");
                                                                System.out.println("|                is not in your order               |");
                                                                System.out.println("+---------------------------------------------------+");
                                                                Thread.sleep(2000);
                                                                clearConsole();
                                                                Return_Choice(5);
                                                        } else {
                                                                clearConsole();
                                                                System.out.println("-----------------------------------------------------");
                                                                System.out.println("Your current item quantity of " +
                                                                                Extras[item_index_E] + " is " +
                                                                                Extras_Order[item_index_E]);
                                                                System.out.println("-----------------------------------------------------");
                                                                System.out.print("Enter the quantity you want to retun: ");

                                                                int qty = Integer.parseInt(breader.readLine());
                                                                if (qty > Extras_Order[item_index_E]) {
                                                                        clearConsole();
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        System.out.println("|          You cannot return more than you          |");
                                                                        System.out.println("|          have ordered. Please try again.          |");
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Choice(5);

                                                                } else if (qty <= 0) {
                                                                        clearConsole();
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        System.out.println("|          You cannot return less than you          |");
                                                                        System.out.println("|          have ordered. Please try again.          |");
                                                                        System.out.println("+---------------------------------------------------+");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Choice(5);
                                                                } else {
                                                                        Extras_Order[item_index_E] -= qty;
                                                                        Extras_Stock[item_index_E] += qty;

                                                                        if (Extras_Order[0] == 0 &&
                                                                                        Extras_Order[1] == 0 &&
                                                                                        Extras_Order[2] == 0) {
                                                                                Check_for_Order[4] = false;
                                                                        }

                                                                        if (item_index_E >= 3) {
                                                                                if (Soldout[4] == true) {
                                                                                        Soldout[4] = false;
                                                                                }

                                                                                if (Extras_Order[4] == 0) {
                                                                                        Return_Empty_Category[4] = true;
                                                                                }
                                                                        } else {
                                                                                if (Soldout[item_index_E] == true) {
                                                                                        Soldout[item_index_E] = false;
                                                                                }

                                                                                if (Extras_Order[item_index_E] == 0) {
                                                                                        Return_Empty_Category[item_index_E] = true;
                                                                                }
                                                                        }

                                                                        clearConsole();
                                                                        System.out.println("-----------------------------------------------------");
                                                                        System.out.println("You have successfully returned [" +
                                                                                                        qty + "] " +
                                                                                                        Extras[item_index_E]);
                                                                        System.out.println("-----------------------------------------------------");
                                                                        Thread.sleep(2000);
                                                                        clearConsole();
                                                                        Return_Menu();
                                                                }
                                                        }
                                                }
                                        }
                                        break;
                                default:
                                        clearConsole();
                                        System.out.println("+---------------------------------------------------+");
                                        System.out.println("|            Please enter a valid choice            |");
                                        System.out.println("+---------------------------------------------------+");
                                        Thread.sleep(2000);
                                        clearConsole();
                                        Return_Menu();
                                        break;
                        }
                } catch (Exception e) {
                        clearConsole();
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Error " + e.getMessage());
                        System.out.println("Error Code: [G4M10]");
                        System.out.println("-----------------------------------------------------");
                        System.out.println("Please Only Enter a Numiric Value");
                        Thread.sleep(3000);
                        clearConsole();
                        Return_Menu();
                }
        }

        public static void clearConsole() {

                if (EnableforCMD == true) {
                        // if you run this source code using cmd,
                        // the "System.out.println("\033\143");" will not
                        // work except if you run it using windows terminal
                        // that's why I used this method to clear the console screen
                        try {
                                if (System.getProperty("os.name").contains("Windows")) {
                                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                                } else {
                                        Runtime.getRuntime().exec("cls");
                                }
                        } catch (IOException | InterruptedException ex) {
                        }
                } else {
                        System.out.println("\033\143");
                }

        }
}

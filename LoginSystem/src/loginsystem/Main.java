package loginsystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login user = new Login();

       
        System.out.println("--- REGISTRATION ---");
        System.out.print("Enter First Name: ");
        user.setFirstName(scanner.nextLine());
        System.out.print("Enter Last Name: ");
        user.setLastName(scanner.nextLine());

        while (true) {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            user.setUsername(username);
            if (user.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            }
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        }

        while (true) {
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            user.setPassword(password);
            if (user.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            }
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        }

        while (true) {
            System.out.print("Enter Cell Phone Number (+27...): ");
            String cell = scanner.nextLine();
            user.setCellPhoneNumber(cell);
            if (user.checkCellPhoneNumber(cell)) {
                System.out.println("Cell number successfully captured.");
                break;
            }
            System.out.println("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
        }

        
        System.out.println("\n--- LOGIN ---");
        System.out.print("Enter Username: ");
        String loginUser = scanner.nextLine();
        System.out.print("Enter Password: ");
        String loginPass = scanner.nextLine();

        boolean success = user.loginUser(loginUser, loginPass);
        System.out.println(user.returnLoginStatus(success));

        
        if (success) {
            System.out.println("\nWelcome to QuickChat.");
            System.out.print("How many messages would you like to enter? ");
            int totalMessages = Integer.parseInt(scanner.nextLine());
            
            Message[] messages = new Message[totalMessages];
            int sentCount = 0;

            boolean running = true;
            while (running) {
                System.out.println("\n--- QuickChat Menu ---");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        for (int i = 0; i < totalMessages; i++) {
                            System.out.println("\nMessage " + (i + 1) + " of " + totalMessages);
                            System.out.print("Enter recipient cell number: ");
                            String recipient = scanner.nextLine();

                            System.out.print("Enter message content (max 250 characters): ");
                            String content = scanner.nextLine();

                            if (content.length() > 250) {
                                System.out.println("Please enter a message of less than 250 characters.");
                                i--; 
                                continue;
                            } else {
                                System.out.println("Message ready to send.");
                            }

                            Message msg = new Message(i + 1, recipient, content);
                            
                            System.out.println("Choose action: 1) Send Message | 2) Disregard Message | 3) Store Message");
                            int action = Integer.parseInt(scanner.nextLine());
                            String resultMsg = msg.sentMessage(action);
                            System.out.println(resultMsg);

                            messages[i] = msg;
                            if (action == 1) sentCount++;
                            
                            System.out.println("\n--- Final Message Details ---");
                            System.out.println(msg.printMessageDetails());
                        }
                        System.out.println("\nTotal messages processed/sent: " + sentCount);
                        break;

                    case 2:
                        System.out.println("Coming Soon.");
                        break;

                    case 3:
                        System.out.println("Exiting QuickChat. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option, try again.");
                }
            }
        }
        scanner.close();
    }
}
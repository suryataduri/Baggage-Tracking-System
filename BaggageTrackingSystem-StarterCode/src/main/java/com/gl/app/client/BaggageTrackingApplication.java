package com.gl.app.client;
import com.gl.app.service.UserService;
import com.gl.app.service.impl.UserServiceImpl;
import com.gl.app.service.BaggageService;
import com.gl.app.service.impl.BaggageServiceImpl;
import com.gl.app.entity.User;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.entity.Baggage;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class BaggageTrackingApplication {
	  private static final Pattern EMAIL_PATTERN = null;
	  

    public static void main(String[] args) throws Exception {
    
    	
        UserService userService = new UserServiceImpl();
        BaggageService baggageService = new BaggageServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	System.out.println("1. Register User");
            System.out.println("2. Check-in Baggage");
            System.out.println("3. Get Baggage Info");
            System.out.println("4. Get Baggage Status");
            System.out.println("5. Get All Checked-in Baggage");
            System.out.println("6. Update Baggage Status");
            System.out.println("7. Update Baggage Location");
            System.out.println("8. Claim Baggage");
            System.out.println("9. Get Baggage Location");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
				//write the code to register user
                    System.out.println("Enter your userId :");
                    String userid = scanner.next();
                    System.out.println("Enter your First Name :");
                    String firstName = scanner.next();
                    System.out.println("Enter your Last Name :");
                    String lastName = scanner.next();
                    System.out.println("Enter your email :");
                    String email = scanner.next();
                    User user = new User(userid,firstName,lastName,email);
                    userService.registerNewUser(user);
                  break;
                case 2:
                    // Check-in Baggage
                	//write the code to check in baggage
                    System.out.println("Enter your claim id :");
                    String claimid = scanner.next();
                    System.out.println("Enter your baggage location :");
                    String location = scanner.next();
                    System.out.println("Enter your baggage status :");
                    String status = scanner.next();
                    System.out.println("Enter your user id :");
                    String userid1 = scanner.next();
                    Baggage baggage = new Baggage(claimid,location,status,userid1);
                    userService.checkInBaggage(baggage);
                    break;
                case 3:
                    // Get Baggage Info
                	//write the code to get baggage info
                    String claimbaggageinfo = scanner.next();
                    Baggage baggage1 = userService.getBaggageInfo(claimbaggageinfo);
                    System.out.println(baggage1.getClaimId()+" "+baggage1.getLocation()+" "+baggage1.getStatus()+" "+baggage1.getUserId());

                   break;
                case 4:
                    // Get Baggage Status
                	//write the code to get baggage status
                    System.out.println("Enter your claim id :");
                    String claimTagid = scanner.next();
                    System.out.println(baggageService.getBaggageStatus(claimTagid));
                    break;
                case 5:
                    // Get All Checked-in Baggage
                	//write the code to get all checked-in baggage
                    List<Baggage> b = baggageService.getAllCheckedInBaggage();
                    Consumer<Baggage> consumer = b5->{
                        System.out.println("Claimid :"+b5.getClaimId());
                        System.out.println("Location :"+b5.getLocation());
                        System.out.println("Status :"+b5.getStatus());
                        System.out.println("UserId :"+b5.getUserId());
                        System.out.println("----------------------------------------------------------------------------------------");
                    };
                    b.forEach(consumer::accept);

                   break;
                case 6:
                    // Write your code to Update Baggage Status
                    System.out.println("Enter your claim id :");
                    String claimTagid1 = scanner.next();
                    System.out.println("Enter your baggage status :");
                    String status1 = scanner.next();
                	baggageService.updateBaggageStatus(claimTagid1,status1);
                    break;
                case 7:
                    // update Baggage Location
                	//write the code to update baggage location
                    System.out.println("Enter your claim id :");
                    String claimTagid2 = scanner.next();
                    System.out.println("Enter your baggage location :");
                    String location1 = scanner.next();
                    baggageService.updateBaggageLocation(claimTagid2,location1);
                    break;
                case 8:
					// Claim Baggage
					//write the code to claim baggage
                    System.out.println("Enter your claim id :");
                    String claimTagid3 = scanner.next();
                    baggageService.claimBaggage(claimTagid3);
					break;
			case 9:
				// Get Baggage Location
			//write the code to get baggage location
                System.out.println("Enter your claim id :");
                String claimTagid4 = scanner.next();
                System.out.println(baggageService.getBaggageLocation(claimTagid4));
					break;
					
               case 10:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
            }
        }
    }
    private static boolean validateEmail(String email) {
        return false;
    }

}
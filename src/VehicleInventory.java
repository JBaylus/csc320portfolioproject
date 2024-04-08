import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleInventory {
    Scanner sc = new Scanner(System.in);
    private ArrayList<Automobile> carInventory;

    public VehicleInventory() {
        carInventory = new ArrayList<Automobile>();
    } // end default constructor

    /*
    verifyInt checks to see if the input is a number and
    continues to prompt the user for a number until they enter one
    It is used for menus.
    It returns an int for the choice.

    One way to write this would have been for a range of acceptable values.
    I did not write the program this way as the menu switch statements already have a
    default which says the number is not valid.
     */
    public int verifyInt(String input){
        int num = 0;
        Boolean invalidNumber = true;
        while (invalidNumber){
            try{
                num = Integer.parseInt(input);
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a number: ");
                input = sc.next();
                continue;
            }
            invalidNumber = false;
        } // end while
        return num;
    } // end verifyInt

    /*
    verifyYear checks to see if the input is a number and
    continues to prompt the user for a number until they enter one
    It also checks to see if the year entered is between 1880 (when the first
    gasoline-powers automobiles came out) and 2025 (as 2025 model year cars
    will come out this year) and prompts for a new number if not
    It returns an int for the year.
     */
    public int verifyYear(String input){
        int num = 0;
        Boolean invalidNumber = true;
        while (invalidNumber){
            try{
                num = Integer.parseInt(input);
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a number: ");
                input = sc.next();
                continue;
            }
            if (num < 1880) {
                System.out.println("The year should be 1880 or greater.");
                // this is based on a quick search saying the earliest gasoline-powered cars
                // were built in the 1880s
                System.out.println("Please enter a year of the car: ");
                input = sc.next();
                continue;
            }
            if (num > 2025){
                System.out.println("The year should be 2025 or less.");
                System.out.println("Please enter the year of the car: ");
                input = sc.next();
                continue;
            }
            invalidNumber = false;
        } // end while
        return num;
    } // end verifyYear

    /*
    verifyMileage checks to see if the input is a number and
    continues to prompt the user for a number until they enter one
    It checks to see if the number enter is 0 or greater and prompts
    for a new number if not
    It returns an int for the mileage.
     */
    public int verifyMileage(String input){
        int num = 0;
        Boolean invalidNumber = true;
        while (invalidNumber){
            try{
                num = Integer.parseInt(input);
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a number: ");
                input = sc.next();
                continue;
            }
            if (num < 0) {
                System.out.println("The mileage should be 0 or greater.");
                System.out.println("Please enter the mileage: ");
                input = sc.next();
                continue;
            }
            invalidNumber = false;
        } // end while
        return num;
    } // end verifyMileage


    public void PrintInventory(){
        // first check to see if there are any vehicles in the inventory
        if (carInventory.size() == 0){
            System.out.println("No vehicles in inventory.");
        } else {
            System.out.println("Number\tMake\t\tModel\t\tColor\tYear\tMileage\t");
            for (int i = 0; i < carInventory.size(); i++) {
                System.out.print(i + 1 + "\t\t");
                System.out.print(carInventory.get(i).getMake());
                System.out.print("\t");
                if (carInventory.get(i).getMake().length() < 8)
                    System.out.print("\t");
                if (carInventory.get(i).getMake().length() < 4)
                    System.out.print("\t");
                System.out.print(carInventory.get(i).getModel());
                System.out.print("\t");
                if (carInventory.get(i).getModel().length() < 8)
                    System.out.print("\t");
                if (carInventory.get(i).getModel().length() < 4)
                    System.out.print("\t");
                System.out.print(carInventory.get(i).getColor());
                System.out.print("\t");
                System.out.print(carInventory.get(i).getYear());
                System.out.print("\t");
                System.out.println(carInventory.get(i).getMileage());
            } // end for loop printing the vehicle
        } // end if-else block
    } // end PrintInventory

    public String AddVehicle(){
        String tempMake, tempModel, tempColor;
        int tempYear, tempMileage;
        // prompt for the make, model, color, year, and mileage (store in tempMake, etc.)
        System.out.print("Make: ");
        tempMake = sc.nextLine();
        System.out.print("Model: ");
        tempModel = sc.nextLine();
        System.out.print("Color: ");
        tempColor = sc.nextLine();
        System.out.print("Year: ");
        tempYear = verifyYear(sc.nextLine());
        System.out.print("Mileage: ");
        tempMileage = verifyMileage(sc.nextLine());
        // using the parameterized constructor make a new Vehicle and add to the Vehicle array
        Automobile newCar = new Automobile(tempMake, tempModel, tempColor, tempYear, tempMileage);
        carInventory.add(newCar);
        PrintVehicle(carInventory.size()-1, 2);
        return "Success. Vehicle added.";
    } // end AddVehicle

    /*
    GetVehicleInfo returns the vehicles information in a string array
    It was one of the requirements.
    I used it as the basis for another method that prints out the returned array.
     */
    public String[] GetVehicleInfo(int carID) {
        // carID is the index in the array for each car
        String[] carDetails = new String[5];
        try {
            carDetails[0] = carInventory.get(carID).getMake();
            carDetails[1] = carInventory.get(carID).getModel();
            carDetails[2] = carInventory.get(carID).getColor();
            carDetails[3] = carInventory.get(carID).getYearString();
            carDetails[4] = carInventory.get(carID).getMileageString();
        } catch (Exception e) {
            carDetails[0] = "Not Found";
        }
        return carDetails;
    } // end GetVehicleInfo

    /*
    PrintVehicle prints out the details of the vehicle.
    Option 1: Field: value on separate lines
    Option 2: Single line in the format:
    Color Year Make Model "with" Mileage "miles"
    Option 2 is used if any other number than '1' is entered
    It uses GetVehicleInfo to get the vehicle details.
    As it just prints out the the information, it does not return anything
     */
    public void PrintVehicle(int carID, int option){
        String[] vehicleInfo = new String[5];
        vehicleInfo = GetVehicleInfo(carID);
        if (vehicleInfo[0].equals("Not Found")){
            System.out.println(vehicleInfo[0]);
        } else {
            if (option == 1) {
                // print "Make: " + make
                System.out.print("Make: ");
                System.out.println(vehicleInfo[0]);
                // print “Model: ” + Model
                System.out.print("Model: ");
                System.out.println(vehicleInfo[1]);
                // print “Color: ” + Color
                System.out.print("Color: ");
                System.out.println(vehicleInfo[2]);
                // print “Year: ” + Year
                System.out.print("Year: ");
                System.out.println(vehicleInfo[3]);
                // print “Mileage: ” + Mileage
                System.out.print("Mileage: ");
                System.out.println(vehicleInfo[4]);
            } else {
                System.out.print(vehicleInfo[2] + " ");
                System.out.print(vehicleInfo[3] + " ");
                System.out.print(vehicleInfo[0] + " ");
                System.out.print(vehicleInfo[1] + " with ");
                System.out.print(vehicleInfo[4] + " ");
                if (vehicleInfo[4].equals("1")) {
                    System.out.println("mile");
                } else {
                    System.out.println("miles");
                }
            }
        }
    } // end


    /*
    RemoveVehicle displays the vehicles in the inventory and then asks the user
    which one to remove, with 0 being an option not to remove any
     */
    public String RemoveVehicle() {
        // first check to see if there are any vehicles in the inventory
        if (carInventory.size() == 0){
            return "No vehicles in inventory.";
        }
        //try removing the car from the array (based on its index)
        int carID;
        PrintInventory();
        System.out.println("Enter the number of the vehicle to remove. Or 0 to cancel.");
        System.out.print("Choice: ");
        carID = verifyInt(sc.nextLine());
        if (carID == 0){
            return "Returned to menu.";
        } else {
            carID -= 1;
            try {
                carInventory.remove(carID);
                return "Success. Vehicle removed.";
            } catch (Exception e) {
                return "Not found. Invalid car index";
            } // end catch
        } // end else
    }  // end RemoveVehicle

    /*
    UpdateVehicle first displays the vehicles in the inventory, and then prompts
    the user for the field they want to update.
     */
    public String UpdateVehicle() {
        // first check to see if there are any vehicles in the inventory
        if (carInventory.size() == 0){
            return "No vehicles in inventory.";
        }
        int carID;
        PrintInventory();
        System.out.println("Enter the number of the vehicle to update. Or 0 to cancel.");
        System.out.print("Choice: ");
        carID = verifyInt(sc.nextLine());
        if (carID == 0){
            return "Returned to menu.";
        } else {
            carID -= 1;
            try {
                // display car details
                PrintVehicle(carID, 1);
                //prompt user for the attribute they want to modify and ask them to enter a number:
                // “1. Make” “2. Model” “3. Color” “4. Year” “5. Mileage”
                System.out.println("What field do you want to update?");
                System.out.println("1. Make, 2. Model, 3. Color, 4. Year, or 5. Mileage");
                System.out.print("Choice: ");
                int choice = verifyInt(sc.nextLine());
                // Check if input is a number between 1-4 (using switch statements)
                switch (choice) {
                    case 1:
                        System.out.print("New make: ");
                        String newMake = sc.nextLine();
                        carInventory.get(carID).setMake(newMake);
                        break;
                    case 2:
                        System.out.print("New model: ");
                        String newModel = sc.nextLine();
                        carInventory.get(carID).setModel(newModel);
                        break;
                    case 3:
                        System.out.print("New color: ");
                        String newColor = sc.nextLine();
                        carInventory.get(carID).setColor(newColor);
                        break;
                    case 4:
                        System.out.print("New year: ");
                        int newYear = verifyYear(sc.nextLine());
                        carInventory.get(carID).setYear(newYear);
                        break;
                    case 5:
                        System.out.print("New mileage: ");
                        int newMileage = verifyMileage(sc.nextLine());
                        carInventory.get(carID).setMileage(newMileage);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        return "Vehicle not updated.";
                }
                PrintVehicle(carID, 2);
                return "Success. Vehicle updated.";
            } catch (Exception e) {
                return "Not found. Invalid car index";
            } // end catch
        } // end else
    }  // end UpdateVehicle

    /*
    PrintToFile outputs the inventory to a file. It returns a message saying if it was successful
    or if the user decided not to save the file.
    Output: autos.txt
    The file starts with: Make,Model,Color,Year,Mileage
    and then has each Automobile on a new line in the same format
     */
    public String PrintToFile(){
        // first check to see if there are any vehicles in the inventory
        if (carInventory.size() == 0){
            return "No vehicles in inventory.";
        }
        System.out.println("Save the inventory information to a file? (Y/N)");
        String input = sc.nextLine();
        if(input.equals("Y")){
            // Save the information to a file, with each vehicle on a separate line and the attriutes separated by commas (involves creating the file, writing to it, and then closing it)
            File out = new File("autos.txt");
            try {
                FileWriter fw = new FileWriter(out);
                PrintWriter pw = new PrintWriter(fw);
                pw.write("Make, Model, Color, Year, Mileage\n");
                for (int i = 0; i < carInventory.size(); i++) {
                    pw.write(carInventory.get(i).getMake());
                    pw.write(",");
                    pw.write(carInventory.get(i).getModel());
                    pw.write(",");
                    pw.write(carInventory.get(i).getColor());
                    pw.write(",");
                    pw.write(carInventory.get(i).getYearString());
                    pw.write(",");
                    pw.write(carInventory.get(i).getMileageString());
                    pw.write("\n");
                }
                pw.close();
            } catch (IOException e) {
                return "Unable to write to file";
            } // end exception for the file
            return "Success. File saved.";
        } else {
            return "Not saved.";
        } // end if-else block
    } // end PrintToFile
} // end Inventory class
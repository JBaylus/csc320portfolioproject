import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VehicleInventory cars = new VehicleInventory();
        String input;
        Scanner scan = new Scanner(System.in);
        Boolean runProgram = true;
        while (runProgram){
            System.out.println("Menu");
            System.out.println("1. View Cars");
            System.out.println("2. Add Car");
            System.out.println("3. Modify Car");
            System.out.println("4. Delete Car");
            System.out.println("5. Print inventory to file");
            System.out.println("6. Exit");
            System.out.print("Option: ");
            input = scan.nextLine();
            switch (input) {
                case "1":
                    cars.PrintInventory();
                    break;
                case "2":
                    System.out.println(cars.AddVehicle());
                    break;
                case "3":
                    System.out.println(cars.UpdateVehicle());
                    break;
                case "4":
                    System.out.println(cars.RemoveVehicle());
                    break;
                case "5":
                    System.out.println(cars.PrintToFile());
                    break;
                case "6":
                    runProgram = false;
                    break;
                default:
                    System.out.println("Invalid input");
            } // end switch
            System.out.println();
        } // end while loop
    } // end main
} // end Main class
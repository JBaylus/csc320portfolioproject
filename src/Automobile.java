public class Automobile {
    private String make;
    private String model;
    private String color;
    private int year;
    private int mileage;

    // the default constructor is never used in the program
    public Automobile(){
        make = "Make"; // should be set to the make the dealership is for
        model = "Model";
        color = "Color";
        year = 0; // could be set to the current (model) year
        mileage = 0;
    } // end default constructor
    public Automobile(String givenMake, String givenModel, String givenColor, int givenYear, int givenMileage){
        make = givenMake;
        model = givenModel;
        color = givenColor;
        year = givenYear;
        mileage = givenMileage;
    } // end paramterized contructor

    //getters and setters for the variables
    public String getMake(){
        return make;
    }
    public void setMake(String newMake){
        make = newMake;
    }

    public String getModel(){
        return model;
    }
    public void setModel(String newModel){
        model = newModel;
    }

    public String getColor(){
        return color;
    }
    public void setColor(String newColor){
        color = newColor;
    }

    public int getYear(){return year;}
    public String getYearString(){
        return Integer.toString(year);
    } // to display the year
    public void setYear(int newYear){
        year = newYear;
    }

    public int getMileage(){return mileage;}
    public String getMileageString(){ return Integer.toString(mileage); } // to display the mileage
    public void setMileage(int newMileage){
        mileage = newMileage;
    }
} // end Automobile class
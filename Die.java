// CS 2365 Section 001
// Damian Reyes


package bang;


public class Die{
    
    private final int MAX = 6;
    private String faceValue;
    
    //Die constructor
    public Die(){
        faceValue = "";
    }
    
    //Returns string representing the action for each roll
    public String roll(){
        
        int value;
        value = (int)(Math.random()*6)+1;
        switch (value){
            case 1:
                return "Arrow";
            case 2:
                return "Dynamite";
            case 3:
                return "ShootFirst";
            case 4:
                return "ShootSecond";
            case 5:
                return "GatlingGun";
            case 6:
                return "Drink";

                
        }
        return null;
    }
    // Mutator for Die FaceValue
    public void setFaceValue (String value){
        faceValue = value;
    }
    
    //Accessor for Die FaceValue
    public String getFaceValue(){
        return faceValue;
    }
}

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
    public String basicRoll(){
        int value;
        value = (int)(Math.random()*6)+1;
        switch (value){
            case 1:
                return "Arrow";
            case 2:
                return "Dynamite";
            case 3:
                return "Bull's Eye 1";
            case 4:
                return "Bull's Eye 2";
            case 5:
                return "Beer";
            case 6:
                return "Gatling";        
        }
        return "";
    }
    
    public String cowardRoll(){
        int value;
        value = (int)(Math.random()*6)+1;
        switch (value){
            case 1:
                return "Arrow";
            case 2:
                return "Dynamite";
            case 3:
                return "Bull's Eye 1";
            case 4:
                return "Broken Arrow";
            case 5:
                return "Beer";
            case 6:
                return "Double Beer";
        }
        return "";
    }
    
    public String loudmouthRoll(){
        int value;
        value = (int)(Math.random()*6)+1;
        switch (value){
            case 1:
                return "Arrow";
            case 2:
                return "Dynamite";
            case 3:
                return "Double Bull's Eye 1";
            case 4:
                return "Double Bull's Eye 2";
            case 5:
                return "Bullet";
            case 6:
                return "Double Gatling";
        }
        return "";
    }
    
    public String duelRoll(){     
        int value;
        value = (int)(Math.random()*6)+1;
        switch (value){
            case 1:
                return "Arrow";
            case 2:
                return "Dynamite";
            case 3:
                return "Whiskey Bottle";
            case 4:
                return "Gatling";
            case 5:
                return "Fight a Duel";
            case 6:
                return "Fight a Duel";
        }
        return "";
    }
    
    // Mutator for Die FaceValue
    public void setFaceValue (String value){
        faceValue = value;
    }
    
    //Accessor for Die FaceValue
    public String FaceValue(){
        return faceValue;
    }
}

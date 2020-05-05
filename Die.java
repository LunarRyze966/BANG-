// CS 2365 Section 001
// Damian Reyes


package bang;


public class Die{
    
    private final int MAX = 6;
    private String faceValue;
    private String dieType;
    static String[] possibleDieTypes = {"Basic","Coward","Loudmouth","Duel"};
            
    //Die constructor
    public Die(){
        faceValue = "";
        dieType = "";
    }
    
    //Returns string representing the action for each roll
    public void roll(){      
        int value;
        value = (int)(Math.random()*6)+1;
        switch (value){
            case 1:
                if(dieType.equals("Basic")){
                    faceValue = "Arrow";
                }
                else if(dieType.equals("Coward")){
                    faceValue = "Arrow";
                }
                else if(dieType.equals("Loudmouth")){
                    faceValue = "Arrow";
                }
                else if(dieType.equals("Duel")){
                    faceValue = "Arrow";
                }
                break;
            case 2:
                if(dieType.equals("Basic")){
                    faceValue = "Dynamite";
                }
                else if(dieType.equals("Coward")){
                    faceValue = "Dynamite";
                }
                else if(dieType.equals("Loudmouth")){
                    faceValue = "Dynamite";
                }
                else if(dieType.equals("Duel")){
                    faceValue = "Dynamite";
                }
                break;
            case 3:
                if(dieType.equals("Basic")){
                    faceValue = "Bull's Eye 1";
                }
                else if(dieType.equals("Coward")){
                    faceValue = "Bull's Eye 1";
                }
                else if(dieType.equals("Loudmouth")){
                    faceValue = "Double Bull's Eye 1";
                }
                else if(dieType.equals("Duel")){
                    faceValue = "Whiskey Bottle";
                }
                break;
            case 4:
                if(dieType.equals("Basic")){
                    faceValue = "Bulls Eye 2";
                }
                else if(dieType.equals("Coward")){
                    faceValue = "Broken Arrow";
                }
                else if(dieType.equals("Loudmouth")){
                    faceValue = "Double Bull's Eye 2";
                }
                else if(dieType.equals("Duel")){
                    faceValue = "Gatling";
                }
                break;
            case 5:
                if(dieType.equals("Basic")){
                    faceValue = "Beer";
                }
                else if(dieType.equals("Coward")){
                    faceValue = "Beer";
                }
                else if(dieType.equals("Loudmouth")){
                    faceValue = "Bullet";
                }
                else if(dieType.equals("Duel")){
                    faceValue = "Fight a Duel";
                }
                break;
            case 6:
                if(dieType.equals("Basic")){
                    faceValue = "Gatling";
                }
                else if(dieType.equals("Coward")){
                    faceValue = "Double Beer";
                }
                else if(dieType.equals("Loudmouth")){
                    faceValue = "Double Gatling";
                }
                else if(dieType.equals("Duel")){
                    faceValue = "Fight a Duel";
                }
                break;

                
        }
    }
    // Mutator for Die FaceValue
    public void setFaceValue (String value){
        faceValue = value;
    }
    
    //Accessor for Die FaceValue
    public String getFaceValue(){
        return faceValue;
    }
    
    // Mutator for Die DieType
    public void setDieType(String type){
        dieType = type;
    }
}

// CS 2365 Section 001
// Damian Reyes


package bang;

/**
 *
 * @author damre
 */
public class Role {
    
    private String alignment;
    private String playerRole;
    
    public Role(){
        playerRole = "";
        alignment = "";
    }
    
    public String getRole(){
        return playerRole;
    }
    
    public void setRole(String role){
        playerRole = role;
        if(playerRole.equals("Deputy") || playerRole.equals("Sheriff")){
            alignment = "Good";
        }
        else if(playerRole.equals("OutLaw")){
            alignment = "Bad";
        }
        else if(playerRole.equals("Renegade")){
            alignment = "Neutral";
        }
    }
}

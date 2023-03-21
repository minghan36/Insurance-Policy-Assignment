package nz.ac.auckland.se281;
import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  private ArrayList<Profile> profileList = new ArrayList<Profile>();

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {

    int numberOfProfiles = profileList.size();
    
    if (numberOfProfiles == 0){
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");  
    } else if(numberOfProfiles == 1){
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ".");
      MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage("1", profileList.get(0).getUsername(), Integer.toString(profileList.get(0).getAge()));
    } else{
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(Integer.toString(numberOfProfiles), "s", ".");  
      for (int i = 0; i < numberOfProfiles; i++){
        MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(Integer.toString(i), profileList.get(i).getUsername(), Integer.toString(profileList.get(i).getAge()));
      }
    }
    
  }

    
    // TODO: Complete this method.
  

  public void createNewProfile(String userName, String age) {
    // TODO: Complete this method.
  }

  public void loadProfile(String userName) {
    // TODO: Complete this method.
  }

  public void unloadProfile() {
    // TODO: Complete this method.
  }

  public void deleteProfile(String userName) {
    // TODO: Complete this method.
  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }
}

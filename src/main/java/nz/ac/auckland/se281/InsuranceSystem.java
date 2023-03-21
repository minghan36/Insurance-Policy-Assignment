package nz.ac.auckland.se281;

import java.util.ArrayList;

import org.eclipse.jgit.transport.CredentialItem.Username;

import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  private ArrayList<Profile> profileList = new ArrayList<Profile>();

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {

    int numberOfProfiles = profileList.size();

    if (numberOfProfiles == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
    } else if (numberOfProfiles == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ":");
      MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(
          "1", profileList.get(0).getUserName(), profileList.get(0).getAge());
    } else {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(Integer.toString(numberOfProfiles), "s", ":");
      for (int i = 0; i < numberOfProfiles; i++) {
        MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(
            Integer.toString(i + 1),
            profileList.get(i).getUserName(),
            profileList.get(i).getAge());
      }
    }
  }

  public void createNewProfile(String userName, String age) {
    
    userName = userName.toLowerCase();
    char[] userNameChar = userName.toCharArray();
    userNameChar[0] = Character.toUpperCase(userNameChar[0]);
    userName = String.valueOf(userNameChar);
    if (userName.length()<3){

    }

    Profile username = new Profile(userName, age);
    profileList.add(username);
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

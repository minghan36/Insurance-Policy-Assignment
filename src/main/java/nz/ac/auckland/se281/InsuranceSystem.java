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
    // Using if else statements to print Database message depending on size of ArrayList (number of profiles).
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
            Integer.toString(i + 1), profileList.get(i).getUserName(), profileList.get(i).getAge());
      }
    }
  }

  public void createNewProfile(String userName, String age) {

    // Changing userName to Title Case
    userName = userName.toLowerCase();
    char[] userNameChar = userName.toCharArray();
    userNameChar[0] = Character.toUpperCase(userNameChar[0]);
    userName = String.valueOf(userNameChar);

    int numberOfProfiles = profileList.size();
    // For loop to check that the username is unique, iterate through ArrayList
    for (int i = 0; i < numberOfProfiles; i++) {
      String currentProfileName = profileList.get(i).getUserName();
      if (userName.equals(currentProfileName)) {
        MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
        return;
      }
    }
    // Check for error return from parseInt. Meaning there was an Invalid age input.
    try {
      Integer.parseInt(age);
    } catch (NumberFormatException e) {
      MessageCli.INVALID_AGE.printMessage(age, userName);
      return;
    }
    // Check for name less than 3 letters and integer less than to 0 for age. If both false, new
    // profile can be created and added to arrayList.
    if (userName.length() < 3) {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);
    } else if (!(Integer.parseInt(age) >= 0)) {
      MessageCli.INVALID_AGE.printMessage(age, userName);
    } else {
      Profile user = new Profile(userName, age);
      profileList.add(user);
      MessageCli.PROFILE_CREATED.printMessage(userName, age);
    }
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

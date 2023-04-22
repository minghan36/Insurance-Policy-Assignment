package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  private ArrayList<Profile> profileList = new ArrayList<Profile>();
  private int loadedProfileIndex = -1;

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {

    int numberOfProfiles = profileList.size();
    // Using if else statements to print Database message depending on size of ArrayList (number of
    // profiles).
    // Print Database for when there is no profiles and only one profile.
    if (numberOfProfiles == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
    } else if (numberOfProfiles == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ":");
      if (loadedProfileIndex >= 0) { //Checks if the one profile is loaded
        MessageCli.PRINT_DB_PROFILE_HEADER_SHORT.printMessage(
            "*** ", "1", profileList.get(0).getUserName(), profileList.get(0).getAge());
      } else {
        MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(
            "1", profileList.get(0).getUserName(), profileList.get(0).getAge());
      }
    } else { //Printing for more than one profile in the database
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(Integer.toString(numberOfProfiles), "s", ":");
      if (loadedProfileIndex >= 0) { // For statement printing database when a profile is loaded
        for (int i = 0; i < numberOfProfiles; i++) { 
          if (i == loadedProfileIndex) {
            MessageCli.PRINT_DB_PROFILE_HEADER_SHORT.printMessage(
                "*** ",
                Integer.toString(i + 1),
                profileList.get(i).getUserName(),
                profileList.get(i).getAge());
          } else {
            MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(
                Integer.toString(i + 1),
                profileList.get(i).getUserName(),
                profileList.get(i).getAge());
          }
        }
      } else { // For statement printing database when a profile is not loaded
        for (int i = 0; i < numberOfProfiles; i++) {
          MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(
              Integer.toString(i + 1),
              profileList.get(i).getUserName(),
              profileList.get(i).getAge());
        }
      }
    }
  }

  public void createNewProfile(String userName, String age) {

    if (loadedProfileIndex >= 0) {
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(
          profileList.get(loadedProfileIndex).getUserName());
      return;
    }

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

    // Changing userName to Title Case
    userName = userName.toLowerCase();
    char[] userNameChar = userName.toCharArray();
    userNameChar[0] = Character.toUpperCase(userNameChar[0]);
    userName = String.valueOf(userNameChar);

    // For loop to scan for the username and load the profile if found.
    for (int i = 0; i < profileList.size(); i++) {
      if (profileList.get(i).getUserName().equalsIgnoreCase(userName)) {
        loadedProfileIndex = i;
        MessageCli.PROFILE_LOADED.printMessage(userName);
        return;
      }
    }
    MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
  }

  public void unloadProfile() {

    // If statement checking whether a profile is currently loaded. Printing error message if not and unloading if true.
    if (loadedProfileIndex >= 0) {
      MessageCli.PROFILE_UNLOADED.printMessage(profileList.get(loadedProfileIndex).getUserName());
      loadedProfileIndex = -1;
    } else {
      MessageCli.NO_PROFILE_LOADED.printMessage();
    }
  }

  public void deleteProfile(String userName) {
    
    // Changing userName to Title Case
    userName = userName.toLowerCase();
    char[] userNameChar = userName.toCharArray();
    userNameChar[0] = Character.toUpperCase(userNameChar[0]);
    userName = String.valueOf(userNameChar);

    // For loop to scan for the username and remove from the array list if found. (prints error message if index is same as loaded profile)
    for (int i = 0; i < profileList.size(); i++) {
      if (profileList.get(i).getUserName().equalsIgnoreCase(userName)) {
        if (loadedProfileIndex == i) {
          MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(userName);
          return;
        } else {
        profileList.remove(i);
        MessageCli.PROFILE_DELETED.printMessage(userName);
        return;
        }
      }
    }

    //Prints error message if profile is not found.
    MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(userName);
  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }
}

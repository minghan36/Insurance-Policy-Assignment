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
    // profiles). Cases when numberOfProfiles = 0, 1, and more than 1.
    if (numberOfProfiles == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
    } else if (numberOfProfiles == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ":");
      if (loadedProfileIndex >= 0) { // Checks if the one profile is loaded
        if (profileList.get(0).getNumberOfPolicies()
            == 1) { // Checks if the number of policies and prints message accordingly
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "*** ",
              "1",
              profileList.get(0).getUserName(),
              profileList.get(0).getAge(),
              String.valueOf(profileList.get(0).getNumberOfPolicies()),
              "y",
              String.valueOf(totalPremium(profileList.get(0))));
          printPolicyInfo(profileList.get(0));
        } else {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "*** ",
              "1",
              profileList.get(0).getUserName(),
              profileList.get(0).getAge(),
              String.valueOf(profileList.get(0).getNumberOfPolicies()),
              "ies",
              String.valueOf(totalPremium(profileList.get(0))));
          printPolicyInfo(profileList.get(0));
        }
      } else {
        if (profileList.get(0).getNumberOfPolicies() == 1) {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "",
              "1",
              profileList.get(0).getUserName(),
              profileList.get(0).getAge(),
              String.valueOf(profileList.get(0).getNumberOfPolicies()),
              "y",
              String.valueOf(totalPremium(profileList.get(0))));
          printPolicyInfo(profileList.get(0));
        } else {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "",
              "1",
              profileList.get(0).getUserName(),
              profileList.get(0).getAge(),
              String.valueOf(profileList.get(0).getNumberOfPolicies()),
              "ies",
              String.valueOf(totalPremium(profileList.get(0))));
          printPolicyInfo(profileList.get(0));
        }
      }
    } else { // Printing for more than one profile in the database. Searches for if the profile is
      // loaded and how many policies the profile has.
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(Integer.toString(numberOfProfiles), "s", ":");
      for (int i = 0; i < numberOfProfiles; i++) {
        if (i == loadedProfileIndex) {
          if (profileList.get(i).getNumberOfPolicies() == 1) {
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "*** ",
                Integer.toString(i + 1),
                profileList.get(i).getUserName(),
                profileList.get(i).getAge(),
                String.valueOf(profileList.get(i).getNumberOfPolicies()),
                "y",
                String.valueOf(totalPremium(profileList.get(i))));
            printPolicyInfo(profileList.get(i));
          } else {
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "*** ",
                Integer.toString(i + 1),
                profileList.get(i).getUserName(),
                profileList.get(i).getAge(),
                String.valueOf(profileList.get(i).getNumberOfPolicies()),
                "ies",
                String.valueOf(totalPremium(profileList.get(i))));
            printPolicyInfo(profileList.get(i));
          }
        } else {
          if (profileList.get(i).getNumberOfPolicies() == 1) {
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "",
                Integer.toString(i + 1),
                profileList.get(i).getUserName(),
                profileList.get(i).getAge(),
                String.valueOf(profileList.get(i).getNumberOfPolicies()),
                "y",
                String.valueOf(totalPremium(profileList.get(i))));
            printPolicyInfo(profileList.get(i));
          } else {
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "",
                Integer.toString(i + 1),
                profileList.get(i).getUserName(),
                profileList.get(i).getAge(),
                String.valueOf(profileList.get(i).getNumberOfPolicies()),
                "ies",
                String.valueOf(totalPremium(profileList.get(i))));
            printPolicyInfo(profileList.get(i));
          }
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

    // If statement checking whether a profile is currently loaded. Printing error message if not
    // and unloading if true.
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

    // For loop to scan for the username and remove from the array list if found. (prints error
    // message if index is same as loaded profile)
    for (int i = 0; i < profileList.size(); i++) {
      if (profileList.get(i).getUserName().equalsIgnoreCase(userName)) {
        if (loadedProfileIndex == i) {
          MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(userName);
          return;
        } else {
          profileList.remove(i);
          if (loadedProfileIndex > i) {
            loadedProfileIndex--;
          }
          MessageCli.PROFILE_DELETED.printMessage(userName);
          return;
        }
      }
    }

    // Prints error message if profile is not found.
    MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(userName);
  }

  public void createPolicy(PolicyType type, String[] options) {

    if (loadedProfileIndex < 0) {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
      return;
    }

    // Switch cases to check the type of policy
    switch (type) {
      case HOME: {
        int sumInsured = Integer.parseInt(options[0]);
        // Change options to proper types
        boolean rental;
        String address = options[1];
        if (options[2].equalsIgnoreCase("yes")) {
          rental = true;
        } else {
          rental = false;
        }
        // Calculate base premium
        int basePremium;
        if (rental) {
          basePremium = (int) (sumInsured * 0.02);
        } else {
          basePremium = (int) (sumInsured * 0.01);
        }
        // Create policy and add to profile
        InsurancePolicy homePolicy = new HomePolicy(sumInsured, address, rental, basePremium);
        profileList.get(loadedProfileIndex).addPolicy(homePolicy);
      }
      break;
      case CAR: {
        // Change options to proper types
        int sumInsured = Integer.parseInt(options[0]);
        String makeAndModel = options[1];
        String licensePlate = options[2];
        boolean mechanicalBreakdown;
        if (options[3].equalsIgnoreCase("yes")) {
          mechanicalBreakdown = true;
        } else {
          mechanicalBreakdown = false;
        }
        // Calculate base premium
        int basePremium;
        if (Integer.parseInt(profileList.get(loadedProfileIndex).getAge()) < 25) {
          basePremium = (int) (sumInsured * 0.15);
        } else {
          basePremium = (int) (sumInsured * 0.1);
        }
        if (mechanicalBreakdown) {
          basePremium += 80;
        }
        InsurancePolicy carPolicy =
            new CarPolicy(
                sumInsured, makeAndModel, licensePlate, mechanicalBreakdown, basePremium);
        profileList.get(loadedProfileIndex).addPolicy(carPolicy);
      }
      break;
      case LIFE:
        // Checking for restrictions. Age>100 or profile already has a life policy
        if (Integer.parseInt(profileList.get(loadedProfileIndex).getAge()) > 100) {
          MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(
              profileList.get(loadedProfileIndex).getUserName());
          return;
        }
        // Check through list of policies relevant to a profile
        for (int i = 0; i < profileList.get(loadedProfileIndex).getNumberOfPolicies(); i++) {
          if (profileList.get(loadedProfileIndex).getPolicy(i) instanceof LifePolicy) {
            MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(
                profileList.get(loadedProfileIndex).getUserName());
            return;
          }
        }
        {
          // Change options to proper types
          int sumInsured = Integer.parseInt(options[0]);
          // Calculate basePremium
          int basePremium =
              (int)
                  (sumInsured
                      * ((1
                              + (((double)
                                      Integer.parseInt(
                                          profileList.get(loadedProfileIndex).getAge()))
                                  / 100))
                          / 100));
          InsurancePolicy lifePolicy = new LifePolicy(sumInsured, basePremium);
          profileList.get(loadedProfileIndex).addPolicy(lifePolicy);
        }
        break;
    }

    MessageCli.NEW_POLICY_CREATED.printMessage(
        String.valueOf(type).toLowerCase(), profileList.get(loadedProfileIndex).getUserName());
  }

  public int totalPremium(Profile profile) {
    int totalPremium = 0;
    for (int i = 0; i < profile.getNumberOfPolicies(); i++) {
      totalPremium += profile.getPolicy(i).getBasePremium();
    }

    totalPremium = (int) (totalPremium * ((double) discount(profile)) / 100);
    return totalPremium;
  }

  public int discount(Profile profile) {

    // If statement to check the percentage that the totalPremium should be of the basePremium
    if (profile.getNumberOfPolicies() < 2) {
      return 100;
    } else if (profile.getNumberOfPolicies() == 2) {
      return 90;
    } else {
      return 80;
    }
  }

  public void printPolicyInfo(Profile profile) {

    int discount = discount(profile);

    // Prints out the information of each policy depending on the type.
    // For loop to check each policy of a profile. If statement to check whether the policy is of
    // type Home, Car or Life.
    for (int i = 0; i < profile.getNumberOfPolicies(); i++) {
      InsurancePolicy policy = profile.getPolicy(i);
      if (policy instanceof HomePolicy) {
        HomePolicy homePolicy = (HomePolicy) policy;
        MessageCli.PRINT_DB_HOME_POLICY.printMessage(
            homePolicy.getAddress(),
            String.valueOf(policy.getSumInsured()),
            String.valueOf(policy.getBasePremium()),
            String.valueOf(policy.getBasePremium() * discount / 100));
      } else if (policy instanceof CarPolicy) {
        CarPolicy carPolicy = (CarPolicy) policy;
        MessageCli.PRINT_DB_CAR_POLICY.printMessage(
            carPolicy.getMakeAndModel(),
            String.valueOf(policy.getSumInsured()),
            String.valueOf(policy.getBasePremium()),
            String.valueOf(policy.getBasePremium() * discount / 100));
      } else {
        MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
            String.valueOf(policy.getSumInsured()),
            String.valueOf(policy.getBasePremium()),
            String.valueOf(policy.getBasePremium() * discount / 100));
      }
    }
  }
}

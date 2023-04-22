package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profile {
  private String userName;
  private String age;
  // create Arraylist of Insurance Policies relevant to the profile
  private ArrayList<InsurancePolicy> policyList = new ArrayList<InsurancePolicy>();

  public Profile(String userName, String age) {
    this.userName = userName;
    this.age = age;
  }

  public String getUserName() {
    return this.userName;
  }

  public String getAge() {
    return this.age;
  }

  public void addPolicy(InsurancePolicy policy) {
    policyList.add(policy);
  }

  public int getNumberOfPolicies() {
    return policyList.size();
  }

  public InsurancePolicy getPolicy(int policyIndex) {
    return policyList.get(policyIndex);
  }
}

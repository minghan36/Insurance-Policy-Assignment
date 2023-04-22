package nz.ac.auckland.se281;

public abstract class InsurancePolicy {
  protected int sumInsured;
  protected int basePremium;

  public InsurancePolicy(int sumInsured, int basePremium) {
    this.sumInsured = sumInsured;
    this.basePremium = basePremium;
  }

  public int getSumInsured() {
    return sumInsured;
  }

  public int getBasePremium() {
    return basePremium;
  }
}

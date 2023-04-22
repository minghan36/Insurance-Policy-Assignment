package nz.ac.auckland.se281;

public class CarPolicy extends InsurancePolicy {

  private String makeAndModel;
  private String licensePlate;
  private Boolean mechanicalBreakdown;

  public CarPolicy(
      int sumInsured,
      String makeAndModel,
      String licensePlate,
      Boolean mechanicalBreakdown,
      int basePremium) {
    super(sumInsured, basePremium);
    this.makeAndModel = makeAndModel;
    this.licensePlate = licensePlate;
    this.mechanicalBreakdown = mechanicalBreakdown;
  }

  public String getMakeAndModel() {
    return makeAndModel;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public Boolean getMechanicalBreakdown() {
    return mechanicalBreakdown;
  }
}

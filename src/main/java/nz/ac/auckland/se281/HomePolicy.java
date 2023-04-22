package nz.ac.auckland.se281;

public class HomePolicy extends InsurancePolicy {

  private String address;
  private boolean rental;

  public HomePolicy(int sumInsured, String address, boolean rental, int basePremium) {
    super(sumInsured, basePremium);
    this.address = address;
    this.rental = rental;
  }

  public String getAddress() {
    return address;
  }

  public boolean isRental() {
    return rental;
  }
}

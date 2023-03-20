package nz.ac.auckland.se281;

public class Profile {
  private String Username;
  private int Age;

  public Profile(String Username, int Age) {
    this.Username = Username;
    this.Age = Age;
  }

  public String getUsername() {
    return this.Username;
  }

  public int getAge() {
    return this.Age;
  }
}

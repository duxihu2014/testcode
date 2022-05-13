package arithmetic.testsap.javase;

public class Company {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static void main(String[] args) {
    Company company = new Company();
    company.setName("Fujia");
    switchCompany(company);
    System.out.println(company.getName());
  }

  private static void switchCompany(Company company) {
    Company newCom = new Company();
    newCom.setName("Fu");
    company = newCom;
  }

}

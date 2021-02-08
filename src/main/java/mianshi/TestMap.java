package mianshi;

public class TestMap {
    public static void main(String[] args) {

        Company company = new Company();
        company.setName("Fujia");
        switchName(company);
        System.out.println(company.getName());
    }

    public static void switchName(Company com){
//        Company newCompany = new Company();
//        newCompany.setName("Fu");
//        com = newCompany;


        com.setName("Fu");
    }



}
class Company{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
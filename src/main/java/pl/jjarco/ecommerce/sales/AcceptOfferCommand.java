package pl.jjarco.ecommerce.sales;

public class AcceptOfferCommand {

    String fname;

    String lname;

    String email;

    public String getFname() {
        return fname;
    }

    public AcceptOfferCommand setFname(String fname) {
        this.fname = fname;
        return this;
    }

    public String getLname() {
        return lname;
    }

    public AcceptOfferCommand setLname(String lname) {
        this.lname = lname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AcceptOfferCommand setEmail(String email) {
        this.email = email;
        return this;
    }
}

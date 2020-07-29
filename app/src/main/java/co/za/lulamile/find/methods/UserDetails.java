package co.za.lulamile.find.methods;

public class UserDetails {

    private String fullName;
    private String located;
    private String gender;
    private String dateOfBirth;
    private String imageUrl;
    private String cellphone;
    private String regToken;

    public UserDetails() {
    }

    public UserDetails(String fullName, String located, String gender, String dateOfBirth, String imageUrl, String cellphone, String regToken) {
        this.fullName = fullName;
        this.located = located;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.imageUrl = imageUrl;
        this.cellphone = cellphone;
        this.regToken = regToken;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocated() {
        return located;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getRegToken() {
        return regToken;
    }


}

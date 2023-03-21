import java.util.Date;

class User{
    private String name;
    private Date birth;
    User(String userName, Date birthday){
        this.name = userName;
        this.birth = birthday;
    }
    User(){
        this.name = "unknown";
        this.birth = new Date("00 00 0000");
    }

    public Date getBirth() {
        return birth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}

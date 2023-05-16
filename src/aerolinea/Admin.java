package aerolinea;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {

    private String name;
    private String passwd;

    public Admin(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + this.name + '\'' +
                ", passwd='" + this.passwd + '\'' +
                '}';
    }

}

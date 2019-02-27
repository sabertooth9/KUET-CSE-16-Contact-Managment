package com.nuhash.kuetcse16;

public class student {
    String name,nickname;
    String roll;
    String bd;
    String phone,hometown;
    String college;
    String blood;

    public student() {
    }

    public student(String name, String nickname, String roll, String bd, String phone, String hometown, String college, String blood) {
        this.name = name;
        this.nickname = nickname;
        this.roll = roll;
        this.bd = bd;
        this.phone = phone;
        this.hometown = hometown;
        this.college = college;
        this.blood = blood;
    }
    boolean contains(String x){
        if(name.contains(x.toUpperCase()) || nickname.contains(x.toUpperCase()) || hometown.contains(x.toUpperCase()) || college.contains(x.toUpperCase()) || blood.equals(x) || roll.contains(x))return  true;
        return  false;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }
}

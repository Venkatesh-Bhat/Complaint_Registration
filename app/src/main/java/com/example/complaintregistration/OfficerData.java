package com.example.complaintregistration;

public class OfficerData {
    String name,email,id,phone,role;
    OfficerData(){}
    OfficerData(String name,String email,String id,String phone,String role){
        this.name = name;

        this.email = email;
        this.id = id;
        this.role = role;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

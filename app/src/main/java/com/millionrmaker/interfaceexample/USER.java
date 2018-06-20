package com.millionrmaker.interfaceexample;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "student")
class USER {
    ADDRESS address;

    @DatabaseField(generatedId = true)
    public int Id;

    @DatabaseField
    public String name;


    @DatabaseField
    public String email;


    @DatabaseField
    public String studentCity;


    public ADDRESS getAddress() {
        return address;
    }

    public USER() {
    }

    public USER(String name, String email, String city) {
        this.name = name;
        this.email = email;
        this.studentCity=city;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentCity() {
        return studentCity;
    }

    public void setStudentCity(String studentCity) {
        this.studentCity = studentCity;
    }

     class ADDRESS {
        public String city;

        ADDRESS() {
        }


        ADDRESS(String city) {
            this.city = city;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}



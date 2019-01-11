/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Faisal
 */
public class Person {
    String Name, FName, CNIC, Gender, Occupation, Qualification, Address, Contact;
    double Salary; 

    public Person(String Name, String FName, String CNIC, String Gender, String Occupation, String Qualification, String Address, String Contact, double Salary) {
        this.Name = Name;
        this.FName = FName;
        this.CNIC = CNIC;
        this.Gender = Gender;
        this.Occupation = Occupation;
        this.Qualification = Qualification;
        this.Address = Address;
        this.Contact = Contact;
        this.Salary = Salary;
    }

    public Person() {
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setOccupation(String Occupation) {
        this.Occupation = Occupation;
    }

    public void setQualification(String Qualification) {
        this.Qualification = Qualification;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public String getName() {
        return Name;
    }

    public String getFName() {
        return FName;
    }

    public String getCNIC() {
        return CNIC;
    }

    public String getGender() {
        return Gender;
    }

    public String getOccupation() {
        return Occupation;
    }

    public String getQualification() {
        return Qualification;
    }

    public String getAddress() {
        return Address;
    }

    public String getContact() {
        return Contact;
    }

    public double getSalary() {
        return Salary;
    }
    
    
}

package vn.fis.training.service;

public class SummaryCustomerByAgeDTO {
    private int age;
    private int count;
    //TODO: Setters, Getter, Constructors

    public SummaryCustomerByAgeDTO() {

    }

    public SummaryCustomerByAgeDTO(int age, int count) {
        this.age = age;
        this.count = count;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void check(int age){
        if(this.age == age){
            this.count == this.count++;
        }
    }
}

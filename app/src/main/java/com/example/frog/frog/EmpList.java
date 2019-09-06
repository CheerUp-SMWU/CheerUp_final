package com.example.frog.frog;

public class EmpList {

    //공고제목, 기업명, 마감일

    String empTitle, comName, expDate, id;
    public EmpList(String empTitle, String comName, String expDate, String id){
        this.empTitle = empTitle;
        this.comName = comName;
        this.expDate = expDate;
        this.id = id;
    }

    public String getEmpTitle(){
        return empTitle;
    }

    public String getComName(){
        return comName;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getID() {
        return id;
    }

}




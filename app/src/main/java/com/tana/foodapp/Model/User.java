package com.tana.foodapp.Model;

public class User {
    private String mFirstName, mLastName, mEmailAddress, mSex;

    public User() {
    }

    public User(String mFirstName, String mLastName, String mEmailAddress, String mSex) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmailAddress = mEmailAddress;
        this.mSex = mSex;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getmEmailAddress() {
        return mEmailAddress;
    }

    public void setmEmailAddress(String mEmailAddress) {
        this.mEmailAddress = mEmailAddress;
    }

    public String getmSex() {
        return mSex;
    }

    public void setmSex(String mSex) {
        this.mSex = mSex;
    }
}

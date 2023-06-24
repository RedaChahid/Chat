package com.example.borderpane;

import java.io.Serializable;

public abstract class Manager implements Serializable {
    String name;

    String id;

    float Hours;

    public Manager(String name, String ID, float hours) {
        this.name = name;
        this.id=ID;
        this.Hours = hours;
    }

    abstract float calculercout();

}

class senior extends Manager{

    public senior(String name, String ID, float hours) {
        super(name, ID, hours);
    }
    float calculercout(){
        if (this.Hours>2000){
            return 2500 * 2000 + (this.Hours - 2000) * 3500;

        }

      else if (this.Hours<2000) return this.Hours * 2000;
      else {
            return 2500 * 2000;
        }
    }


}

class junior extends Manager{

    public junior(String name, String ID, float hours) {
        super(name, ID, hours);
    }
    float calculercout(){
        if (this.Hours>2000){
            return 2000 * 2000 + (this.Hours - 2000) * 3000;

        }

        else if (this.Hours<2000) return this.Hours * 1500;
        else {
            return 2500 * 2000;
        }
    }


}

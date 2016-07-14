package model.student;

import java.io.Serializable;

/**
 * Created by pasha on 22.04.2016.
 */
public class Course implements Serializable {
    private int numberOfCourse;

    public Course(int numberOfCourse) {
        this.numberOfCourse = numberOfCourse;
    }

    public int getNumberOfCourse() {
        return numberOfCourse;
    }

    public void setNumberOfCourse(int numberOfCourse) {
        this.numberOfCourse = numberOfCourse;
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(getClass() == obj.getClass()))
            return false;
        else {
            Course tmp = (Course) obj;
            if (tmp.numberOfCourse == this.numberOfCourse)
                return true;
            else
                return false;
        }
    }
}

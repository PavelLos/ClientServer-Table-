package model.student;

import java.io.Serializable;

/**
 * Created by pasha on 22.04.2016.
 */
public class Group implements Serializable {
    private int numberOfGroup;

    public Group(int numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }

    public int getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(int numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(getClass() == obj.getClass()))
            return false;
        else {
            Group tmp = (Group) obj;
            if (tmp.numberOfGroup == this.numberOfGroup)
                return true;
            else
                return false;
        }
    }
}

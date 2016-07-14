package model.student;

import java.io.Serializable;

/**
 * Created by pasha on 22.04.2016.
 */
public class ProgramLanguage implements Serializable {
    private String nameOfProgramLanguage;

    public ProgramLanguage(String programLanguage) {
        nameOfProgramLanguage = programLanguage;
    }

    public String getNameOfProgramLanguage() {
        return nameOfProgramLanguage;
    }

    public void setNameOfProgramLanguage(String nameOfProgramLanguage) {
        this.nameOfProgramLanguage = nameOfProgramLanguage;
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(getClass() == obj.getClass()))
            return false;
        else {
            ProgramLanguage tmp = (ProgramLanguage) obj;
            if (tmp.nameOfProgramLanguage == this.nameOfProgramLanguage)
                return true;
            else
                return false;
        }
    }
}

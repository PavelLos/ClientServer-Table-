package model.student;

import java.io.Serializable;

/**
 * Created by pasha on 22.04.2016.
 */
public class Project implements Serializable {
    private int numberOfProjects;
    private int numberOfSolvedProjects;

    public Project(int projects, int solvedProjects) {
        numberOfProjects = projects;
        numberOfSolvedProjects = solvedProjects;
    }

    public int getNumberOfSolvedProjects() {
        return numberOfSolvedProjects;
    }

    public int getNumberOfProjects() {
        return numberOfProjects;
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(getClass() == obj.getClass()))
            return false;
        else {
            Project tmp = (Project) obj;
            if (tmp.numberOfProjects == this.numberOfProjects &&
                    tmp.numberOfSolvedProjects == this.numberOfSolvedProjects)
                return true;
            else
                return false;
        }
    }

}

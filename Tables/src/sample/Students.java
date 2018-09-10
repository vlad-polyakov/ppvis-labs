package sample;

public class Students {
    private String fio;
    private String surname;
    private String name;
    private String numberOfGroup;
    private String course;
    private String works;
    private String totalWorks;
    private String langOfProgram;

    public Students(){
        this.name = "";
        this.numberOfGroup = "";
        this.course = "";
        this.works = "";
        this.totalWorks = "";
        this.langOfProgram = "";
        this.surname = "";
    }

   public Students(String name, String surname, String numberOfGroup, String course, String works, String totalWorks, String langOfProgram){
       this.name = name;
       this.numberOfGroup = numberOfGroup;
       this.course = course;
       this.works = works;
       this.totalWorks = totalWorks;
       this.langOfProgram = langOfProgram;
       this.surname = surname;
       this.fio = name + " " +surname;
   }


    public String getName() { return name;  }

    public void setName(String name) {
        this.name = name;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }


    public String getSurname() { return surname; }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(String numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getWorks(){
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }

    public String getTotalWorks(){
        return totalWorks;
    }

    public void setTotalWorks(String totalWorks) {
        this.totalWorks = totalWorks;
    }
    public String getLangOfProgram() {
        return langOfProgram;
    }

    public void setLangOfProgram(String langOfProgram) {
        this.langOfProgram = langOfProgram;
    }

}

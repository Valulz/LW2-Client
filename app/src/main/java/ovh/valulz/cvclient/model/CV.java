package ovh.valulz.cvclient.model;

import java.util.ArrayList;
import java.util.List;

public class CV {

    public enum Gender{
        MR,
        MRS;
    }

    private String id;
    private Name name;
    private String firstName;
    private Gender gender;
    private String objective;
    private String skill;
    private List<Experience> exps;
    private List<School> schools;
    private List<Language> langs;
    private List<ComputerScienceSkill> skills;

    public CV() {
        exps = new ArrayList<>();
        schools = new ArrayList<>();
        langs = new ArrayList<>();
        skills = new ArrayList<>();
    }

    public CV(String id, Name name, String firstName, Gender gender, String objective, String skill, List<Experience> exps, List<School> schools, List<Language> langs, List<ComputerScienceSkill> skills) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.gender = gender;
        this.objective = objective;
        this.skill = skill;
        this.exps = exps;
        this.schools = schools;
        this.langs = langs;
        this.skills = skills;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public List<Experience> getExps() {
        return exps;
    }

    public void setExps(List<Experience> exps) {
        this.exps = exps;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public List<Language> getLangs() {
        return langs;
    }

    public void setLangs(List<Language> langs) {
        this.langs = langs;
    }

    public List<ComputerScienceSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<ComputerScienceSkill> skills) {
        this.skills = skills;
    }


    public void addExp(Experience exp){
        exps.add(exp);
    }

    public void addScho(School scho){
        schools.add(scho);
    }

    public void addLangs(Language lang){
        langs.add(lang);
    }

    public void addCSS(ComputerScienceSkill css){
        this.skills.add(css);
    }
}

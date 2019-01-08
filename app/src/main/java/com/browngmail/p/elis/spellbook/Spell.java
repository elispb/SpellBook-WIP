package com.browngmail.p.elis.spellbook;

import java.util.List;

public class Spell {
    int id;
    String name;
    Boolean ritual;
    String level;
    String school;
    String castingTime;
    String range;
    Boolean[] components = {false, false, false};
    String materialComponents;
    String duration;
    String description;
    String higherLevelDescription;
    String source;
    String pageNo;

    Spell(String nameIn, Boolean ritualIn, String levelIn, String schoolIn, String castingTimeIn, String rangeIn, Boolean v, Boolean s, Boolean m, String materialIn, String durationIn, String descriptionIn, String higherLevelDescriptionIn, String sourceIn, String pageIn){
        v = v != null ? v : false;
        s = s != null ? s : false;
        m = m != null ? m : false;
        materialIn = materialIn != null ? materialIn : "";
        higherLevelDescriptionIn = higherLevelDescriptionIn != null ? higherLevelDescriptionIn : "";
        sourceIn = sourceIn != null ? sourceIn : "Unknown";
        pageIn = pageIn != null ? pageIn : "Unknown";

        name = nameIn;
        ritual = ritualIn;
        level = levelIn;
        school = schoolIn;
        castingTime = castingTimeIn;
        range = rangeIn;
        components[0] = v;
        components[1] = s;
        components[2] = m;
        materialComponents = materialIn;
        duration = durationIn;
        description = descriptionIn;
        higherLevelDescription = higherLevelDescriptionIn;
        source = sourceIn;
        pageNo = pageIn;
    }

    void SetID(int idIn) throws Exception {
        if(idIn >= 0){
            id = idIn;
        }
        else {
            throw new Exception("ID must be a positive integer");
        }
    }
}

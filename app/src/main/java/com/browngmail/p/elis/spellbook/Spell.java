package com.browngmail.p.elis.spellbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import static java.lang.Math.toIntExact;

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

    String Save(DatabaseContact.SpellDBHelper mDbHelper){

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_SPELL_NAME, this.name);
        contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_RITUAL, this.ritual.toString());
        contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_LEVEL, this.level);
        contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_SCHOOl, this.school);
        contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_CASTING_TIME, this.castingTime);
        contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_RANGE, this.range);
        contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_COMPONENTS, this.materialComponents);
        contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_DURATION, this.duration);
        contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_DESCRIPTION, this.description);
        contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_AT_HIGHER_LEVELS, this.higherLevelDescription);
        contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_SOURCE, this.source);
        contentValues.put(DatabaseContact.SpellEntry.COLUMN_NAME_PAGE, this.pageNo);

        long newRowId = db.insert(DatabaseContact.SpellEntry.TABLE_NAME, null, contentValues);
        try {
            SetID(toIntExact(newRowId));
        }catch(ArithmeticException ae) {
            return null;
        }catch (Exception e){
            return null;
        }
        return String.valueOf(newRowId);
    }
}

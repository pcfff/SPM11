package com.example.teachingplatform.entity;



import java.io.Serializable;


/**
 * (Dict)实体类
 *
 * @author 
 */

public class Dict implements Serializable {
    /**
    * key
    */
    private String dictKey;
    /**
    * value
    */
    private String dictValue;

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
}

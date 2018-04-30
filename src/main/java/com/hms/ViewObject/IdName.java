package com.hms.ViewObject;

/**
 * Created by wb-wyh270612 on 2017/4/19.
 */
public class IdName {
    private Object key;
    private String value;
    private String code;

    public IdName() {
    }

    public IdName(Object key, String value) {
        this.key = key;
        this.value = value;
    }

    public IdName(Object key, String value, String code) {
        this.key = key;
        this.value = value;
        this.code = code;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        IdName other = (IdName) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
}

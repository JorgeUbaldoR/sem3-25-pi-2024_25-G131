package pt.ipp.isep.dei.esoft.project.domain.more;

import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.Objects;

public class ID implements Comparable<ID> {
    private int serial;
    private TypeID typeID;
    private String keyID;

    public ID(int serial, TypeID typeID) {
        this.serial = serial;
        this.typeID = typeID;
        this.keyID = typeID.toString() + "-" + serial;
    }
    //-----


    public TypeID getTypeID() {
        return typeID;
    }

    public int getSerial() {
        return serial;
    }

    public String getKeyID() {
        return keyID;
    }


    //-----
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ID id = (ID) o;

        return Objects.equals(keyID, id.keyID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyID);
    }

    public ID clone() {
        return new ID(serial, typeID);
    }

    public int compareTo(ID otherID) {
        if (!this.typeID.toString().equals(otherID.typeID.toString()))
            throw new IllegalArgumentException("Can't compare two different types of key.");
        if (serial > otherID.serial)
            return 1;
        if (serial < otherID.serial)
            return -1;
        return 0;
    }
}

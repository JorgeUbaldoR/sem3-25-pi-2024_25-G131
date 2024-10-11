package pt.ipp.isep.dei.esoft.project.domain.more;

import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.Objects;

public class ID implements Comparable<ID> {
    private int serial;
    private TypeID typeID;
    private String keyID;

    /**
     * Constructs an ID with a serial number and a type.
     *
     * @param serial the serial number of the ID.
     * @param typeID the type of the ID.
     */
    public ID(int serial, TypeID typeID) {
        this.serial = serial;
        this.typeID = typeID;
        this.keyID = typeID.toString() + "-" + serial;
    }

    /**
     * Returns the type of the ID.
     *
     * @return the typeID of this ID.
     */
    public TypeID getTypeID() {
        return typeID;
    }

    /**
     * Returns the serial number of the ID.
     *
     * @return the serial number of this ID.
     */
    public int getSerial() {
        return serial;
    }

    /**
     * Returns the keyID string representation of the ID.
     *
     * @return the keyID of this ID.
     */
    public String getKeyID() {
        return keyID;
    }


    //---------- Overrides and clone methods

    /**
     * Compares this ID to the specified object for equality.
     * Two IDs are considered equal if they have the same keyID.
     *
     * @param o the object to compare this ID against.
     * @return true if the specified object is equal to this ID; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ID id = (ID) o;

        return Objects.equals(keyID, id.keyID);
    }

    /**
     * Returns a hash code value for this ID.
     * The hash code is based on the keyID.
     *
     * @return a hash code value for this ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(keyID);
    }

    /**
     * Returns a string representation of the ID.
     *
     * @return the keyID as a string.
     */
    @Override
    public String toString() {
        return String.format("%s",keyID);
    }

    /**
     * Compares this ID with another ID for order.
     * The IDs are compared based on their typeID and serial number.
     *
     * @param otherID the ID to be compared.
     * @return a negative integer, zero, or a positive integer as this ID
     *         is less than, equal to, or greater than the specified ID.
     * @throws IllegalArgumentException if the other ID has a different typeID.
     */
    @Override
    public int compareTo(ID otherID) {
        if (!this.typeID.toString().equals(otherID.typeID.toString()))
            throw new IllegalArgumentException("Can't compare two different types of key.");
        return Integer.compare(serial, otherID.serial);
    }

    /**
     * Creates and returns a clone of this ID.
     * The cloned ID has the same serial and type.
     *
     * @return a clone of this ID.
     */
    public ID clone() {
        return new ID(serial, typeID);
    }
}

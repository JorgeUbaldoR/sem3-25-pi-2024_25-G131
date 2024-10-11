package pt.ipp.isep.dei.esoft.project.domain.enumclasses;

/**
 * Represents the priority levels for operations.
 * This enum defines three levels of priority: HIGH, MEDIUM, and LOW.
 */
public enum Priority {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    /**
     * Constructs a Priority with the specified display name.
     *
     * @param displayName the display name of the priority.
     */
    private final String displayName;

    /**
     * Returns the display name of this priority level.
     *
     * @return the display name of this priority level.
     */
    @Override
    public String toString() {
        return displayName;
    }

    /**
     * Constructs a Priority with the specified display name.
     *
     * @param displayName the display name of the priority.
     */
    Priority(String displayName) {
        this.displayName = displayName;
    }


    /**
     * Converts a string to its corresponding Priority enum.
     *
     * @param priority the string representation of the priority.
     * @return the corresponding Priority enum, or null if no match is found or if the input is null.
     */
    public static Priority fromString(String priority) {
        if (priority == null) {
            return null;
        }
        for (Priority p : Priority.values()) {
            if (p.displayName.equalsIgnoreCase(priority)) {
                return p;
            }
        }
        return null;
    }
}
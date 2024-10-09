package pt.ipp.isep.dei.esoft.project.domain.enumclasses;

public enum Priority {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private final String displayName;

    @Override
    public String toString() {
        return displayName;
    }

    Priority(String displayName) {
        this.displayName = displayName;
    }


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
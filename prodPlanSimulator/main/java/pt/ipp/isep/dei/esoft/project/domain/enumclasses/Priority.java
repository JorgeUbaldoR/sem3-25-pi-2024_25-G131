package pt.ipp.isep.dei.esoft.project.domain.enumclasses;

public enum Priority{
    HIGH{
        @Override
        public String toString() {
            return "High";
        }
    },
    MEDIUM{
        @Override
        public String toString() {
            return "Medium";
        }
    },
    LOW{
        @Override
        public String toString() {
            return "Low";
        }
    }
}

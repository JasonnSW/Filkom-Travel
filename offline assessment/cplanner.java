import java.util.*;

class ClassSchedule {
    String className;
    String day;
    int startTime;
    int endTime;

    public ClassSchedule(String className, String day, String time) {
        this.className = className;
        this.day = day;
        // Convert time to minutes since 07:00 for easier comparison
        String[] times = time.split("-");
        this.startTime = convertToMinutes(times[0]);
        this.endTime = convertToMinutes(times[1]);
    }

    // Helper method to convert "HH:MM" to minutes since 07:00
    private int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return (hour - 7) * 60 + minute; // Subtract 7 to start from 0
    }

    // Check if this class conflicts with another class
    public boolean conflictsWith(ClassSchedule other) {
        if (!this.day.equals(other.day))
            return false; // Different days, no conflict
        return !(this.endTime <= other.startTime || this.startTime >= other.endTime);
    }

    // Generate the abbreviated class name
    public String getAbbreviatedName() {
        String[] words = className.split(" ");
        StringBuilder abbreviation = new StringBuilder();
        for (String word : words) {
            abbreviation.append(word.charAt(0));
        }
        abbreviation = abbreviation.deleteCharAt(abbreviation.length() - 1);
        return abbreviation.toString() + " " + className.charAt(className.length() - 1);
    }
}

public class cplanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<ClassSchedule> allClasses = new ArrayList<>();
        List<ClassSchedule> scheduledClasses = new ArrayList<>();
        List<ClassSchedule> conflictedClasses = new ArrayList<>();

        // Read input
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty())
                break; // Stop reading on empty line
            String[] parts = input.split(", ");
            String className = parts[0];
            String day = parts[1];
            String time = parts[2];
            allClasses.add(new ClassSchedule(className, day, time));
        }

        // Schedule classes
        for (ClassSchedule currentClass : allClasses) {
            boolean hasConflict = false;
            for (ClassSchedule scheduledClass : scheduledClasses) {
                if (currentClass.conflictsWith(scheduledClass)) {
                    hasConflict = true;
                    break;
                }
            }
            if (hasConflict) {
                conflictedClasses.add(currentClass);
            } else {
                scheduledClasses.add(currentClass);
            }
        }

        // Output results
        System.out.println("List Kelas Yang Jadi");
        for (ClassSchedule cls : scheduledClasses) {
            System.out.println("- " + cls.getAbbreviatedName());
        }

        System.out.println("List Kelas Yang Batal");
        for (ClassSchedule cls : conflictedClasses) {
            System.out.println("- " + cls.getAbbreviatedName());
        }
    }
}
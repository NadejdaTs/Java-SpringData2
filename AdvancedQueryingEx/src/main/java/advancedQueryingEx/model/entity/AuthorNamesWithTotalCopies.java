package advancedQueryingEx.model.entity;

public interface AuthorNamesWithTotalCopies {

    String getFirstName();
    String getLastName();
    long getTotalCopies();


    /* if the class is not interface - not working in this situation, bug good to try
    private String firstName;
    private String lastName;
    private long totalCopies;
    public AuthorNamesWithTotalCopies() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(long totalCopies) {
        this.totalCopies = totalCopies;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " - " + totalCopies;
    }*/
}

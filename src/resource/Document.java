package resource;

import java.time.LocalDate;

/**
 * immutable object
 */
public class Document implements Resource {
    private final String docName;
    private final String publicDepartment;
    private final LocalDate publicDate;

    public Document(String docName, String publicDepartment, LocalDate publicDate) {
        this.docName = docName;
        this.publicDepartment = publicDepartment;
        this.publicDate = publicDate;
    }

    public String getDocName() {
        return this.docName;
    }

    public String getPublicDepartment() {
        return this.publicDepartment;
    }

    public LocalDate getPublicDate() {
        return this.publicDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Document)) {
            return false;
        }
        Document document = (Document) o;
        return Objects.equals(docName, document.docName) && Objects.equals(publicDepartment, document.publicDepartment)
                && Objects.equals(publicDate, document.publicDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(docName, publicDepartment, publicDate);
    }

    @Override
    public String toString() {
        return "{" + " docName='" + getDocName() + "'" + ", publicDepartment='" + getPublicDepartment() + "'"
                + ", publicDate='" + getPublicDate() + "'" + "}";
    }
}
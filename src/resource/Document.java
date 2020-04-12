package resource;

import java.time.LocalDate;
import java.util.Objects;

/**
 * immutable object
 */
public class Document implements Resource {
    private final String docName;
    private final String publishDepartment;
    private final LocalDate publishDate;

    public Document(String docName, String publishDepartment, LocalDate publishDate) {
        this.docName = docName;
        this.publishDepartment = publishDepartment;
        this.publishDate = publishDate;
    }

    public String getDocName() {
        return this.docName;
    }

    public String getPublishDepartment() {
        return this.publishDepartment;
    }

    public LocalDate getPublishDate() {
        return this.publishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Document)) {
            return false;
        }
        Document document = (Document) o;
        return Objects.equals(docName, document.docName)
                && Objects.equals(publishDepartment, document.publishDepartment)
                && Objects.equals(publishDate, document.publishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(docName, publishDepartment, publishDate);
    }

    @Override
    public String toString() {
        return "{" + " docName='" + getDocName() + "'" + ", publishDepartment='" + getPublishDepartment() + "'"
                + ", publishDate='" + getPublishDate() + "'" + "}";
    }

}
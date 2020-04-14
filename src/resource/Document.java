package resource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * document for activity calendar
 * immutable object
 */
public class Document implements Resource {
    private final String docName;
    private final String strPublishDepartment;
    private final LocalDate strPublishDate;

    /**
     * constructor
     * @param docName
     * @param strPublishDepartment
     * @param strPublishDate
     */
    public Document(String docName, String strPublishDepartment, String strPublishDate) {
        this.docName = docName;
        this.strPublishDepartment = strPublishDepartment;
        this.strPublishDate = LocalDate.parse(strPublishDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * get the String of document name
     * @return the String of document name
     */
    public String getDocName() {
        return this.docName;
    }

    /**
     * get the String of publish department
     * @return the String of publish department
     */
    public String getStrPublishDepartment() {
        return this.strPublishDepartment;
    }

    /**
     * get the LocalDate of publish date
     * @return the LocalDate of publish date
     */
    public LocalDate getStrPublishDate() {
        return this.strPublishDate;
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
                && Objects.equals(strPublishDepartment, document.strPublishDepartment)
                && Objects.equals(strPublishDate, document.strPublishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(docName, strPublishDepartment, strPublishDate);
    }

    @Override
    public String toString() {
        return "{" + " docName='" + getDocName() + "'" + ", strPublishDepartment='" + getStrPublishDepartment() + "'"
                + ", strPublishDate='" + getStrPublishDate() + "'" + "}";
    }

}
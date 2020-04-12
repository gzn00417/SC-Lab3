package resource;

import java.time.LocalDate;

/**
 * an interface represents types of resources
 */
public interface Resource {

    /**
     * generate a new resource of plane
     * @param number
     * @param strType
     * @param intSeats
     * @param age
     * @return a new instance of plane
     */
    public static Plane newResourceOfPlane(String number, String strType, int intSeats, double age) {
        return new Plane(number, strType, intSeats, age);
    }

    /**
     * generate a new resource of train
     * @param trainNumber
     * @param trainType
     * @param trainCapacity
     * @return a new instance of train
     */
    public static Train newResourceOfTrain(String trainNumber, String trainType, int trainCapacity) {
        return new Train(trainNumber, trainType, trainCapacity);
    }

    /**
     * generate a new resource of document
     * @param docName
     * @param publishDepartment
     * @param publishDate
     * @return a new instance of document
     */
    public static Document newResourceOfDocument(String docName, String publishDepartment, LocalDate publishDate) {
        return new Document(docName, publishDepartment, publishDate);
    }
}
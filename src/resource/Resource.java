package resource;

import resource.*;

/**
 * an interface represents types of resources
 */
public interface Resource {
    /**
     * generate a chosen type of resource
     * @param strResourceType String of resource type
     * @return an object of resource
     */
    public static Resource newResource(String strResourceType, String number, String strType, int intSeats,
            double age) {
        if (strResourceType.equals("Plane"))
            return new Plane(number, strType, intSeats, age);
        return null;
    }
}
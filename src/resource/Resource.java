package resource;

/**
 * an interface represents types of resources
 */
public interface Resource {
    /**
     * generate a chosen type of resource
     * @param strResourceType String of resource type
     * @return an object of resource
     */
    public static Resource newResource(String strResourceType) {
        if (strResourceType == "Plane")
            return new Plane();
        return null;
    }
}
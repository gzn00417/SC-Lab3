package resource;

/**
 * immutable object
 */
public class Train implements Resource {
    private final String trainNumber;
    private final String trainType;
    private final String trainCapacity;

    public Train(String trainNumber, String trainType, String trainCapacity) {
        this.trainNumber = trainNumber;
        this.trainType = trainType;
        this.trainCapacity = trainCapacity;
    }

    public String getTrainNumber() {
        return this.trainNumber;
    }

    public String getTrainType() {
        return this.trainType;
    }

    public String getTrainCapacity() {
        return this.trainCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Train)) {
            return false;
        }
        Train train = (Train) o;
        return Objects.equals(trainNumber, train.trainNumber) && Objects.equals(trainType, train.trainType)
                && Objects.equals(trainCapacity, train.trainCapacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainNumber, trainType, trainCapacity);
    }

    @Override
    public String toString() {
        return "{" + " trainNumber='" + getTrainNumber() + "'" + ", trainType='" + getTrainType() + "'"
                + ", trainCapacity='" + getTrainCapacity() + "'" + "}";
    }
}
package resource;

import java.util.Objects;

/**
 * immutable object
 */
public class Train implements Resource {
    private final String trainNumber;
    private final String trainType;
    private final int trainCapacity;

    public Train(String trainNumber, String trainType, int trainCapacity) {
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

    public int getTrainCapacity() {
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
                && trainCapacity == train.trainCapacity;
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
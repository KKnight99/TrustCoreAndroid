package trust.core.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Message implements Parcelable {

    public final String value;
    public final boolean isPersonal;

    public Message(String value) {
        this(value, false);
    }

    public Message(String value, boolean isPersonal) {
        this.value = value;
        this.isPersonal = isPersonal;
    }

    protected Message(Parcel in) {
        value = in.readString();
        isPersonal = in.readByte() != 0;
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
        dest.writeByte((byte) (isPersonal ? 1 : 0));
    }
}

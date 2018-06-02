package trust.core.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigInteger;

public class Transaction implements Parcelable {
    private final Address recipient;
    private final Address contract;
    private final BigInteger value;
    private final BigInteger gasPrice;
    private final long gasLimit;
    private final long nonce;
    private final String payload;

    public Transaction(
            Address recipient,
            Address contract,
            BigInteger value,
            BigInteger gasPrice,
            long gasLimit,
            long nonce,
            String payload) {
        this.recipient = recipient;
        this.contract = contract;
        this.value = value;
        this.gasPrice = gasPrice;
        this.gasLimit = gasLimit;
        this.nonce = nonce;
        this.payload = payload;
    }

    Transaction(Parcel in) {
        recipient = in.readParcelable(Address.class.getClassLoader());
        contract = in.readParcelable(Address.class.getClassLoader());
        value = new BigInteger(in.readString());
        gasPrice = new BigInteger(in.readString());
        gasLimit = in.readLong();
        nonce = in.readLong();
        payload = in.readString();
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(recipient, flags);
        dest.writeParcelable(contract, flags);
        dest.writeString((value == null ? BigInteger.ZERO : value).toString());
        dest.writeString((gasPrice == null ? BigInteger.ZERO : gasPrice).toString());
        dest.writeLong(gasLimit);
        dest.writeLong(nonce);
        dest.writeString(payload);
    }
}

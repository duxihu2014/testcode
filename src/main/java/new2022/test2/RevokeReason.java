package new2022.test2;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RevokeReason {
    DATA_OWNER_CHANGED("Primary User Changed"), //
    VIN_REMOVED("VIN_REMOVED");//

    private final String message;

    RevokeReason(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
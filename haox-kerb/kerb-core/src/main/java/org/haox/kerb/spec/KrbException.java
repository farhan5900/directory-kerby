package org.haox.kerb.spec;

import org.haox.kerb.spec.type.common.KrbErrorCode;

public class KrbException extends Exception {

    public KrbException(String message) {
        super(message);
    }

    public KrbException(String message, Throwable cause) {
        super(message, cause);
    }

    public KrbException(KrbErrorCode errorCode) {
        super(errorCode.getMessage());
    }

    public KrbException(KrbErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
    }
}

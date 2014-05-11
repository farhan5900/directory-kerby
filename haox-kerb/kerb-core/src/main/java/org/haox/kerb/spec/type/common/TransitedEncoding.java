package org.haox.kerb.spec.type.common;

import org.haox.asn1.type.Asn1FieldInfo;
import org.haox.asn1.type.Asn1Integer;
import org.haox.asn1.type.Asn1OctetString;
import org.haox.kerb.spec.type.KrbSequenceType;

/**
 TransitedEncoding       ::= SEQUENCE {
 tr-type         [0] Int32 -- must be registered --,
 contents        [1] OCTET STRING
 }
 */
public class TransitedEncoding extends KrbSequenceType {
    private static int TR_TYPE = 0;
    private static int CONTENTS = 1;

    static Asn1FieldInfo[] fieldInfos = new Asn1FieldInfo[] {
            new Asn1FieldInfo(TR_TYPE, 0, Asn1Integer.class),
            new Asn1FieldInfo(CONTENTS, 1, Asn1OctetString.class)
    };

    public TransitedEncoding() {
        super(fieldInfos);
    }

    public TransitedEncodingType getTrType() {
        Integer value = getFieldAsInteger(TR_TYPE);
        return TransitedEncodingType.fromValue(value);
    }

    public void setTrType(TransitedEncodingType trType) {
        setField(TR_TYPE, trType);
    }

    public byte[] getContents() {
        return getFieldAsOctetBytes(CONTENTS);
    }

    public void setContents(byte[] contents) {
        setFieldAsOctetBytes(CONTENTS, contents);
    }
}

package org.haox.kerb.codec.encoding;

import java.nio.ByteBuffer;

public class HaoxLazyEncodedSequence extends ByteBufferASN1Object
{
    public HaoxLazyEncodedSequence(ByteBuffer byteBuffer, int limit) {
        super(byteBuffer, limit);
    }
}
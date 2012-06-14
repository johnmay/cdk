package org.openscience.cdk.io.stream;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author John May
 * @cdk.module io
 */
public class AbstractInputMarshal {

    /**
     * Read an integer as an unsigned byte value. This is applicable for integers
     * that have a value of 0 < 256
     *
     * @throws java.io.IOException
     */
    public static int readUnsignedByte(DataInput in) throws IOException {
        return in.readByte() + Byte.MAX_VALUE;
    }

    /**
     * Write an integer as an unsigned short value. This is applicable for integers
     * that have a value of 0 < 65,535
     *
     * @throws java.io.IOException
     */
    public static int readUnsignedShort(DataInput in) throws IOException {
        return in.readShort() + Short.MAX_VALUE;
    }

}

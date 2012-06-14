package org.openscience.cdk.io.stream;

import java.io.DataOutput;
import java.io.IOException;

/**
 * Class provides some read/write utilities for output marshals that can help reduce
 * stream size.
 *
 * @author John May
 * @cdk.module io
 */
public class AbstractOutputMarshal {


    /**
     * Write an integer value as an unsigned byte value. This is applicable for integers
     * that have a value between 0 and 256.
     * <p/>
     * Note: no checks are used to determine that the value is in range
     *
     * @param out   data stream that the value will be written to
     * @param value integer value to write
     *
     * @throws IOException low-level io exception
     */
    public static void writeAsUnsignedByte(DataOutput out, int value) throws IOException {
        out.writeByte(value - Byte.MAX_VALUE);
    }

    /**
     * Write an integer as an unsigned short value. This is applicable for integers
     * that have a value between 0 and 65,535.
     * <p/>
     * Note: no checks are used to determine that the value is in range
     *
     * @param out   data stream that the value will be written to
     * @param value integer value to write
     *
     * @throws IOException low-level io exception
     */
    public static void writeAsUnsignedShort(DataOutput out, int value) throws IOException {
        out.writeShort(value - Short.MAX_VALUE);
    }

}

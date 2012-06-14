package org.openscience.cdk.io.stream.bond.marshal;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.io.stream.bond.BondOutputMarshal;

import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author John May
 * @cdk.module io
 */
public class BondStereoOutput extends BondOutputMarshal {

    private static final Map<IBond.Stereo, Integer> STEREO_MAP = new HashMap<IBond.Stereo, Integer>(20) {{
        IBond.Stereo[] stereos = IBond.Stereo.values();
        for (int i = 0; i < stereos.length; i++) {
            put(stereos[i], i);
        }
    }};

    @Override
    public void write(DataOutput out, IAtomContainer container, IBond bond) throws IOException {
        out.writeByte(STEREO_MAP.get(bond.getStereo()).byteValue());
    }

    @Override
    public boolean isDefault(IAtomContainer container, IBond bond) {
        return bond.getStereo() == null || bond.getStereo() == IBond.Stereo.NONE;
    }
}

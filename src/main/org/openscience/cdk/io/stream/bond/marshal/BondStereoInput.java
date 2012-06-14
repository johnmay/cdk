package org.openscience.cdk.io.stream.bond.marshal;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.io.stream.bond.BondInputMarshal;

import java.io.DataInput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author John May
 * @cdk.module io
 */
public class BondStereoInput extends BondInputMarshal {

    private static final Map<Integer, IBond.Stereo> stereo = new HashMap<Integer, IBond.Stereo>(20) {{
        IBond.Stereo[] stereos = IBond.Stereo.values();
        for (int i = 0; i < stereos.length; i++) {
            put(i, stereos[i]);
        }
    }};

    @Override
    public void read(DataInput in, IAtomContainer container, IBond bond) throws IOException {
        bond.setStereo(stereo.get(in.readByte()));
    }

    @Override
    public void setDefault(IBond bond) {
        bond.setStereo(IBond.Stereo.NONE);
    }
}

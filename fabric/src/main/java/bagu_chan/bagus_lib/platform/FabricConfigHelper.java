package bagu_chan.bagus_lib.platform;

import bagu_chan.bagus_lib.platform.services.IConfigHelper;

public class FabricConfigHelper implements IConfigHelper {
    @Override
    public boolean getCoolerEndPoem() {
        return true;
    }
}

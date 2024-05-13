package baguchan.bagus_lib.platform;

import baguchan.bagus_lib.platform.services.IConfigHelper;

public class FabricConfigHelper implements IConfigHelper {
    @Override
    public boolean getCoolerEndPoem() {
        return true;
    }
}

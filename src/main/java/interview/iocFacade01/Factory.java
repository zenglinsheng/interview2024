package interview.iocFacade01;

import java.util.HashMap;
import java.util.Map;

public class Factory {

    private static final Map<SupportType, Facade> FACADE_MAP = new HashMap<>();

    public static void register(SupportType supportType, Facade facade) {
        FACADE_MAP.put(supportType, facade);
    }

    public static <T> T get(SupportType supportType) {
        return (T) FACADE_MAP.get(supportType);
    }

}

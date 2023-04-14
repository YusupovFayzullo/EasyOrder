package uz.tafakkoor.easyorder.utils;

import io.micrometer.common.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;


@Component
public class BaseUtils {
    public static String generateUniqueName(@NonNull String fileName) {
        return UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);
    }
}

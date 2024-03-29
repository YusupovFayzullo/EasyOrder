package uz.tafakkoor.easyorder.utils;

import io.micrometer.common.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.UUID;


@Component
public class BaseUtils {
    public static final String BASE_URL = "http://localhost:8080/api/v1/";

    public String generateUniqueName(@NonNull String fileName) {
        return UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);
    }

    public String generateOTP() {
        return String.format("%06d", (int) (Math.random() * 1000000.0));
    }

    public String generateQRCodeContent(String restaurantId, String tableId) {
        String queryParams = String.format("?restaurantId=%s&tableId=%s", restaurantId, tableId);
        return BASE_URL + "order/" + queryParams;
    }

}

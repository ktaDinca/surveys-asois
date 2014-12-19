package com.springapp.mvc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Catalin Dinca (alexandru.dinca2110@gmail.com)
 * @since 15/Dec/2014
 */
public class CommonUtils {

    private static final String MESSAGE_KEY = "message";
    private static final String MESSAGE_SUCCESS = "success";
    private static final String MESSAGE_FAILED = "failed";
    private static final String ERROR_LIST_KEY = "error_list";

    public static boolean isStringEmptyOrNull(String string) {
        return string == null || "".equals(string);
    }

    public static Map<String, Object> createSuccessfulResponseMap(Map<String, Object> map) {
        return createResponseMap(map, MESSAGE_KEY, MESSAGE_SUCCESS);
    }

    public static Map<String, Object> createFailedResponseMap(Map<String, Object> map) {
        return createResponseMap(map, MESSAGE_KEY, MESSAGE_FAILED);
    }

    public static Map<String, Object> createFailedResponseMap(Map<String, Object> map, List<String> errorList) {
        map = createFailedResponseMap(map);
        map.put("error_list", errorList);

        return map;
    }

    private static Map<String, Object> createResponseMap(Map<String, Object> map, String messageKey, String messageValue) {
        if (map == null) {
            map = new HashMap<String, Object>();
        }
        map.put(messageKey, messageValue);

        return map;
    }

}

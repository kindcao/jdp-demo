package crm.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Apr 27, 2011 10:59:57 PM
 */
public class Constants {

    public static final Map<String, Object> SYS_USER_MAP = new HashMap<String, Object>();

    public static final int DEFAULT_ROWS = 15;

    public static final int DEFAULT_PAGE = 1;

    public static final String CURR_SYS_USER_COOKIE_KEY = "CURR_SYS_USER_COOKIE_KEY";

    public static final String SYS_USER_APPLICATION_KEY = "SYS_USER_APPLICATION_KEY";

    public static final String CURR_SYS_USER_SESSION_KEY = "CURR_SYS_USER_SESSION_KEY";

    public static final String CURR_SYS_USER_COMP_SESSION_KEY = "CURR_SYS_USER_COMP_SESSION_KEY";

    public static final String RESULT_TRANSFORMER_DTO = "RESULT_TRANSFORMER_DTO";

    public static final String STATUS_Y = "Y";

    public static final String STATUS_N = "N";

    public static final String STATUS_R = "R";

    public static final String STATUS_O = "O";

    public static final String STATUS_A = "A";

    public static final String STATUS_D = "D";

    public static final String JSON_DATA_STATUS_YN_0 = "[{\"id\":\"Y\", \"text\":\"是\"},{\"id\":\"N\", \"text\":\"否\"}]";

    public static final String JSON_DATA_STATUS_YN_1 = "[{\"id\":\"Y\", \"text\":\"已删除\"},{\"id\":\"N\", \"text\":\"未删除\"}]";

    public static final String JSON_DATA_STATUS_YN_2 = "[{\"id\":\"Y\", \"text\":\"已实施\"},{\"id\":\"N\", \"text\":\"未实施\"}]";

    public static final String JSON_DATA_STATUS_AD_3 = "[{\"id\":\"A\", \"text\":\"正常\"},{\"id\":\"D\", \"text\":\"禁用\"}]";

    public static final String JSON_DATA_STATUS_RO_4 = "[{\"id\":\"R\", \"text\":\"融聚公司\"},{\"id\":\"O\", \"text\":\"其它公司\"}]";
}

package utils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;

import net.sf.json.JSONObject;


/**
 * Created by Allen on 2017/1/5.
 */
public final class JsonUtils {

	private JsonUtils() {
	}

	public static <T> T fromJson(String json, Class<T> clazz) throws JsonSyntaxException {
		return (new Gson()).fromJson(json, clazz);
	}

	public static <T> T fromJson(String json, Type type) throws JsonSyntaxException {
		return (new Gson()).fromJson(json, type);
	}

	public static String toJson(Object src) {
		return (new Gson()).toJson(src);
	}

	public static boolean isJson(String json) {
		if (json.isEmpty()) {
			return false;
		} else {
			try {
				new JSONObject();
				return true;
			} catch (Exception var2) {
				return false;
			}
		}
	}
}
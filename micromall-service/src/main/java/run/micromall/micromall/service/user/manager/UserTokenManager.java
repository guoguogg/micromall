package run.micromall.micromall.service.user.manager;

import run.micromall.micromall.service.utils.JwtHelper;

/**
 * 维护用户token
 */
public class UserTokenManager {

	public static String generateToken(Long id) {
        JwtHelper jwtHelper = new JwtHelper();
        return jwtHelper.createToken(id);
    }

    public static Long getUserId(String token) {
    	JwtHelper jwtHelper = new JwtHelper();
        Long userId = jwtHelper.verifyTokenAndGetUserId(token);
    	if(userId == null || userId == 0){
    		return null;
    	}
        return userId;
    }
}

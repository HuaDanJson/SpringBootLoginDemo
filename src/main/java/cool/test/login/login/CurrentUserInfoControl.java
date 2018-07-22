package cool.test.login.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/user")
class CurrentUserInfoControl {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DBCurrentUserInfo dbCurrentUserInfo;


    @GetMapping("/getUserInfo/{userId}")
    public CurrentUserInfo getUserInfo(@PathVariable long userId) {
        logger.info("CurrentUserInfoControl getUserInfo  userId = " + userId);
        return dbCurrentUserInfo.findById(userId);
    }

    @PostMapping("/register")
    public CurrentUserInfo register(@RequestBody CurrentUserInfo userInfo) {
        logger.info("CurrentUserInfoControl register  userInfo = " + userInfo);
        return dbCurrentUserInfo.save(userInfo);
    }

    @PostMapping("/login")
    public CurrentUserInfo login(@RequestBody CurrentUserInfo userInfo) {
        logger.info("CurrentUserInfoControl login  CurrentUserInfo = " + userInfo);
        if (userInfo != null) {
            List<CurrentUserInfo> userInfoList = dbCurrentUserInfo.findByUsername(userInfo.getUsername());
            if (userInfoList != null && userInfoList.size() > 0) {
                CurrentUserInfo userInfo1 = userInfoList.get(0);
                if (userInfo1.getPwd().equals(userInfo.getPwd())) {
                    return userInfo1;
                }
            }
        }
        return null;
    }

    @PostMapping("/update")
    public CurrentUserInfo updateUserInfo(@RequestBody CurrentUserInfo userInfo) {
        logger.info("CurrentUserInfoControl updateUserInfo  CurrentUserInfo = " + userInfo);
        return dbCurrentUserInfo.save(userInfo);
    }

    @PostMapping("/search/{username}")
    public List<CurrentUserInfo> searchUserByUserName(@PathVariable String username) {
        logger.info("CurrentUserInfoControl searchUserByUserName  username = " + username);
        return dbCurrentUserInfo.findByUsername(username);
    }


//    @PostMapping("/v1.0/auth/accountkit/{userName}/{passWord}")
//    private CurrentUserInfo register(@PathVariable String userName, @PathVariable String passWord) {
//
//        logger.info("CurrentUserInfoControl userName = " + userName + "  passWord = " + passWord);
//
//        // We need a signing key, so we'll create one just for this example. Usually
//// the key would be read from your application configuration instead.
//
////        Key key = MacProvider.generateKey();
////
////        logger.info("CurrentUserInfoControl compactJws key = " + key);
////
////        String compactJws = Jwts.builder()
////                .setSubject("accountkitToken")
////                .signWith(SignatureAlgorithm.HS512, key)
////                .compact();
////
////        logger.info("CurrentUserInfoControl compactJws = " + compactJws);
////
////        try {
////
////            Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws);
////
////            logger.info("CurrentUserInfoControl  Jws<Claims> parseClaimsJws = " + parseClaimsJws);
////
////            //OK, we can trust this JWT
////
////        } catch (SignatureException e) {
////
////            //don't trust the JWT!
////
////            logger.info("CurrentUserInfoControl  Jws<Claims> SignatureException = " + e);
////        }
////
////
////        String token2 = JwtManager.createToken(userName);
////
////        logger.info("CurrentUserInfoControl  Jws<Claims> token2 = " + token2);
//
//
//        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
//        currentUserInfo.setUsername(userName);
//        currentUserInfo.setPwd(passWord);
//        dbCurrentUserInfo.save(currentUserInfo);
//
//        logger.info("CurrentUserInfoControl  Jws<Claims> 000  currentUserInfo = " + currentUserInfo);
//
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        byte[] secretBytes = DatatypeConverter.parseBase64Binary("JWT-TOKEN");
//        Key signingKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());
//
//        logger.info("CurrentUserInfoControl  Jws<Claims> 9999  signingKey = " + signingKey);
//
//        Map<String, Object> claims = new HashMap<String, Object>();
//        claims.put("username", userName);
//        claims.put("passWord", passWord);
//        JwtBuilder builder = Jwts.builder().setClaims(claims)
//                .setId(currentUserInfo.getId() + "")
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
//                .signWith(signatureAlgorithm, signingKey);
//
//        String token2 = builder.compact();
//
//        currentUserInfo.setToken(token2);
//
//        dbCurrentUserInfo.save(currentUserInfo);
//
//
//        logger.info("CurrentUserInfoControl  Jws<Claims> 11111  currentUserInfo = " + currentUserInfo);
//
//        logger.info("CurrentUserInfoControl  Jws<Claims> token2 = " + token2);
//
//
//        try {
//            Claims claims2 = parseToken(token2);
//            String username = claims2.get("username").toString();
//            String role = claims2.get("passWord").toString();
//            String userId = claims2.getId();
//            System.out.println("[username]:" + username);
//            System.out.println("[passWord]:" + passWord);
//            System.out.println("[userId]:" + userId);
//
//            logger.info("CurrentUserInfoControl  Jws<Claims> 解密 000  username = " + username);
//
//            logger.info("CurrentUserInfoControl  Jws<Claims> 解密 111  role = " + role);
//
//            logger.info("CurrentUserInfoControl  Jws<Claims> 解密 222  userId = " + userId);
//
//
//        } catch (ExpiredJwtException e) {
//            System.out.println("token expired");
//
//            logger.info("CurrentUserInfoControl  Jws<Claims> 解密  failed  000  ExpiredJwtException = " + e);
//        } catch (InvalidClaimException e) {
//            System.out.println("token invalid");
//            logger.info("CurrentUserInfoControl  Jws<Claims> 解密  failed  111  InvalidClaimException = " + e);
//        } catch (Exception e) {
//            System.out.println("token error");
//
//            logger.info("CurrentUserInfoControl  Jws<Claims> 解密  failed  2222  Exception = " + e);
//        }
//
//        return currentUserInfo;
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestBody CurrentUserInfo userInfo) {
//        logger.info("DBUserControl getAllUser()");
//        if (userInfo != null) {
//            List<CurrentUserInfo> userInfoList = dbCurrentUserInfo.findByUsername(userInfo.getUsername());
//            if (userInfoList != null && userInfoList.size() > 0) {
//                CurrentUserInfo userInfo1 = userInfoList.get(0);
//                if (userInfo1.getPwd().equals(userInfo.getPwd())) {
//                    return "success";
//                }
//            }
//        }
//        return "failed";
//    }
//
//    @GetMapping("/v1.0/users/me")
//    public Optional<CurrentUserInfo> getCurrentUserInfo(@RequestHeader String token) {
//
//        logger.info("CurrentUserInfoControl getCurrentUserInfo()  token = " + token);
//        try {
//            Claims claims2 = parseToken(token);
//            String username = claims2.get("username").toString();
//            String passWord = claims2.get("passWord").toString();
//            String userId = claims2.getId();
//            logger.info("CurrentUserInfoControl  getCurrentUserInfo 解密 000  username = " + username);
//            logger.info("CurrentUserInfoControl getCurrentUserInfo> 解密 111  passWord = " + passWord);
//            logger.info("CurrentUserInfoControl  getCurrentUserInfo 解密 222  userId = " + userId);
//
//            return dbCurrentUserInfo.findById(Integer.valueOf(userId));
//
//        } catch (ExpiredJwtException e) {
//            logger.info("CurrentUserInfoControl  getCurrentUserInfo 解密  failed  000  ExpiredJwtException = " + e);
//        } catch (InvalidClaimException e) {
//            logger.info("CurrentUserInfoControl  getCurrentUserInfo 解密  failed  111  InvalidClaimException = " + e);
//        } catch (Exception e) {
//            logger.info("CurrentUserInfoControl  getCurrentUserInfo 解密  failed  2222  Exception = " + e);
//        }
//
//        return null;
//    }


//    @PostMapping("/register")
//    private Result<Users> register(@Valid @RequestBody Users users) {
//        return ResultUtils.success(dbUsers.save(users));
//    }
//
//    @PostMapping("/register222")
//    private Users register2(@Valid @RequestBody Users users) {
//        return dbUsers.save(users);
//    }
//
//    @GetMapping("/getAllUser")
//    public List<Users> getAllUser() {
//        logger.info("DBUserControl getAllUser()");
//        return dbUsers.findAll();
//    }
//
//    @GetMapping("/getUserInfo/{userId}")
//    public Optional<Users> getUserInfo(@PathVariable Integer userId) {
//        logger.info("DBUserControl getUserInfo()");
//        return dbUsers.findById(userId);
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestBody Users userInfo) {
//        logger.info("DBUserControl getAllUser()");
//        if (userInfo != null) {
//            List<Users> userInfoList = dbUsers.findByUsername(userInfo.getUsername());
//            if (userInfoList != null && userInfoList.size() > 0) {
//                Users userInfo1 = userInfoList.get(0);
//                if (userInfo1.getPwd().equals(userInfo.getPwd())) {
//                    return "success";
//                }
//            }
//        }
//        return "failed";
//    }
//
//    @PostMapping("/update")
//    public Users update(@RequestBody Users userInfo) {
//        logger.info("DBUserControl update()");
//        return dbUsers.save(userInfo);
//    }


    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("JWT-TOKEN"))
                .parseClaimsJws(token).getBody();
    }

}

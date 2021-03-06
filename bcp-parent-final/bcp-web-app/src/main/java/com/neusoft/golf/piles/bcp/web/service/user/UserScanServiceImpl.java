package com.neusoft.golf.piles.bcp.web.service.user;

import com.alibaba.csb.sdk.ContentBody;
import com.alibaba.csb.sdk.HttpCaller;
import com.alibaba.csb.sdk.HttpCallerException;
import com.alibaba.csb.sdk.HttpParameters;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neusoft.golf.piles.bcp.core.domain.entity.User;
import com.neusoft.golf.piles.bcp.core.exception.BusinessException;
import com.neusoft.golf.piles.bcp.core.util.JsonMapper;
import com.neusoft.golf.piles.bcp.core.util.LongDateUtils;
import com.neusoft.golf.piles.bcp.core.util.PasswordUtil;
import com.neusoft.golf.piles.bcp.sdk.jwt.JwtHelper;
import com.neusoft.golf.piles.bcp.sdk.jwt.MacSigner;
import com.neusoft.golf.piles.bcp.sdk.jwt.Signer;
import com.neusoft.golf.piles.bcp.sdk.util.AesUtil;
import com.neusoft.golf.piles.bcp.web.aop.BcpControllerLog;
import com.neusoft.golf.piles.bcp.web.domain.menu.MenuDO;
import com.neusoft.golf.piles.bcp.web.domain.role.RoleDO;
import com.neusoft.golf.piles.bcp.web.domain.role.RoleRepositoryInterface;
import com.neusoft.golf.piles.bcp.web.domain.user.UserNewDo;
import com.neusoft.golf.piles.bcp.web.domain.user.UserNewRepositoryInterface;
import com.neusoft.golf.piles.bcp.web.dto.*;
import com.neusoft.golf.piles.bcp.web.mapper.user.UserMapper;
import com.neusoft.golf.piles.bcp.web.service.redis.RedisService;
import com.neusoft.golf.piles.bcp.web.util.AESUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@RestController
public class UserScanServiceImpl implements UserScanService {

    private static final Logger logger = LoggerFactory.getLogger(UserScanServiceImpl.class);

    private static final String CSB_URL = "http://10.1.189.189:8086/CSB/";
    private static final String ACCESS_KEY = "d7b71962888d490ba83eb488f3c08294";
    private static final String SECURITY_KEY = "/qwpuC4lX+Q4I+p2GyupPpHruBc=";
    private static final String ENCRYPT_KEY = "oqmuo8ynci7rztof";

    private static final String API_VERSION ="1.0.0";
    private static final String API_METHOD="POST";
    private static final int SUCCESS = 0;
    private final JdbcTemplate jdbcTemplate;
    @Value("${security.oauth2.client.client-id}")
    String clientId;
    @Value("${security.oauth2.client.client-secret}")
    String clientSecret;
    @Value("${security.server.oauth2}")
    boolean oauth2;
    public UserScanServiceImpl(@Qualifier("dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Resource
    LoginService loginService;
    @Resource
    RedisService redisService;
    @Resource
    RoleRepositoryInterface roleRepositoryInterface;
    @Resource
    UserNewRepositoryInterface userNewRepositoryInterface;

    @Override
    public Response createQrCode()  {
        String qrCode = "";
        Response response = new Response();
        //????????? qrcode_channel_encrypt??????qrcode
        HttpParameters.Builder builder = this.setParam("qrcode_channel_encrypt", null);
        //???????????? ???????????????json??????)
        String result = null;
        try {
            result = HttpCaller.invoke(builder.build());
            logger.info("================sign_info==================");
            //logger.info(result);
            // ?????????json??????
            JSONObject retJson = JSON.parseObject(result);
            response.setCode(retJson.getString("msgCode"));
            response.setMsg(retJson.getString("msg"));
            //msgCode=200?????????????????????
            if (retJson.getIntValue("msgCode") == SUCCESS) {
                JSONObject resultJson = retJson.getJSONObject("result");
                String encryptValue = resultJson.getString("encrypt");
                // ?????????????????????
                qrCode = AESUtils.decrypt(encryptValue, ENCRYPT_KEY);
                response.setData(JSONObject.parseObject(qrCode));

            }
        } catch (HttpCallerException e) {
            logger.error(e.getMessage());
        }
        return response;
    }

    @Override
    public Response getUser(String qrCode) {
        String userResult = "";
        Response response = new Response();
        // ?????? qrcode_channel_query_encrypt
        HttpParameters.Builder builder = this.setParam("qrcode_channel_query_encrypt", qrCode);
        //??? ????????? ???????????????json??????)
        String result = null;
        try {
            result = HttpCaller.invoke(builder.build());
            logger.info("================sign_info==================");
            // ?????????????????????
            logger.info(result);

            //?????????json??????
            JSONObject retJson = JSON.parseObject(result);
            response.setCode(retJson.getString("msgCode"));
            response.setMsg(retJson.getString("msg"));
            //msgCode=200?????????????????????
            if (retJson.getIntValue("msgCode") == SUCCESS) {
                JSONObject resultJson = retJson.getJSONObject("result");
                String encryptValue = resultJson.getString("encrypt");
                userResult = AESUtils.decrypt(encryptValue, ENCRYPT_KEY);
                response.setData(JSONObject.parseObject(userResult));
            }
        } catch (HttpCallerException e) {
            logger.error(e.getMessage());
        }
        return response;
    }

    @Override
    public String scanLogin(LoginScanDTO loginScanDTO) {
        byte[] clientBytes = (this.clientId + ":" + this.clientSecret).getBytes();
        NamedParameterJdbcTemplate jdbcTemplateExtended = new NamedParameterJdbcTemplate(this.jdbcTemplate);
        String aac002 = AesUtil.decrypt(loginScanDTO.getCard(), "852asd1a6s9s8z3w");
        String aac003 = AesUtil.decrypt(loginScanDTO.getUsername(), "852asd1a6s9s8z3w");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Object> params = new HashMap(2);
        params.put("card", aac002);
        params.put("aac003", aac003);
        List<User> user = jdbcTemplateExtended.query("select * from t_user where card = (:card) and name= (:aac003)", params, new UserMapper());
        if (user.size() == 0) {
            throw new BusinessException("???????????????" + aac003 + "?????????????????????????????????");
        }else {
            if (this.oauth2) {
                return this.oauth2(clientBytes, user.get(0).getAccount(), user.get(0).getPassword(), request);
            }else {
                logger.error("JWT???????????????????????????");
                if (null != user.get(0).getLocked() && user.get(0).getLocked()) {
                    throw new BusinessException("?????????????????????????????????????????????????????????????????????" + user.get(0).getLockedReason());
                }else {
                    String jwt = this.setSession(user.get(0).getAccount(), request, user);
                    List<String> sessions = (List)this.redisService.get("AccountSession:" + user.get(0).getAccount());
                    if (null != sessions) {
                        Iterator var13 = sessions.iterator();

                        while(var13.hasNext()) {
                            String sessionId = (String)var13.next();
                            if (!sessionId.equalsIgnoreCase(request.getSession().getId())) {
                                this.redisService.delete(sessionId);
                            }
                        }
                    }

                    List<String> s = new ArrayList();
                    s.add(request.getSession().getId());
                    this.redisService.set("AccountSession:" + user.get(0).getAccount(), s, 1L, TimeUnit.HOURS);
                    return jwt;
                }
            }
        }
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @BcpControllerLog(
            description = "????????????"
    )
    @Override
    public void createNew(UserNewDTO userNewDTO) {
        NamedParameterJdbcTemplate jdbcTemplateExtended = new NamedParameterJdbcTemplate(this.jdbcTemplate);
        Map<String, Object> params = new HashMap(2);
        params.put("account", userNewDTO.getAccount());
        params.put("card",userNewDTO.getCard());
        List<User> user = jdbcTemplateExtended.query("select * from t_user where account= (:account) or card= (:card)", params, new UserMapper());
        if (user.size() > 0) {
            throw new BusinessException("?????????" + userNewDTO.getAccount() + "????????????????????????"+ userNewDTO.getCard() + "???????????????");
        } else if (PasswordUtil.securityLevel(userNewDTO.getPassword()) < 60) {
            throw new BusinessException("??????????????????????????????????????????????????????8?????????????????????????????????");
        } else {
            UserNewDo userCreate = UserNewDo.builder().account(userNewDTO.getAccount().trim()).name(userNewDTO.getName()).card(userNewDTO.getCard()).passwordExpire(LongDateUtils.nowAsMonthLong() + 1L).password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(userNewDTO.getPassword())).build();
            if (null != userNewDTO.getRole() && !"".equalsIgnoreCase(userNewDTO.getRole())) {
                Optional<RoleDO> role = this.roleRepositoryInterface.findById(userNewDTO.getRole());
                if (role.isPresent()) {
                    Set<RoleDO> roles = new HashSet();
                    roles.add(role.get());
                    userCreate.setRoles(roles);
                }
            }

            this.userNewRepositoryInterface.save(userCreate);
        }
    }

    @Override
    public List<MenuDTO> getMenusOne(String account) {
        Optional<UserNewDo> user = this.userNewRepositoryInterface.findByAccount(account);
        if (!user.isPresent()) {
            return new ArrayList();
        } else {
            Set<MenuDO> menus = new HashSet();
            Iterator var4 = ((UserNewDo)user.get()).getRoles().iterator();

            while(var4.hasNext()) {
                RoleDO role = (RoleDO)var4.next();
                menus.addAll(role.getMenus());
            }

            return MenuAssembler.constructMenus(menus);
        }
    }

    @Override
    public List<UserNewDo> findByAccountLike(String account) {
        return this.userNewRepositoryInterface.findByAccountLike("%" + account + "%");
    }

    @Override
    public List<UserNewDo> all() {
        return this.userNewRepositoryInterface.findAll();
    }

    private String setSession(String username, HttpServletRequest request, List<User> users) {
        Signer signer = new MacSigner(users.get(0).getPassword());
        JwtDecodeDTO jwtDecodeDTO = new JwtDecodeDTO();
        jwtDecodeDTO.setUser_name(username);
        jwtDecodeDTO.setAdmin("1".equalsIgnoreCase(users.get(0).getType()));
        String jwt = JwtHelper.encode(JsonMapper.toJson(jwtDecodeDTO), signer).getEncoded();
        this.redisService.set(request.getSession().getId(), jwt, 30L, TimeUnit.MINUTES);
        return jwt;
    }

    private String oauth2(byte[] clientBytes, String username, String password, HttpServletRequest request) {
        logger.error("OAUTH2???????????????");
        OAuth2AccessToken token = this.loginService.obtainToken("Basic " + Base64.getEncoder().encodeToString(clientBytes), username, password);
        this.redisService.set(request.getSession().getId(), token.getValue(), 30L, TimeUnit.MINUTES);
        return token.getValue();
    }

    private HttpParameters.Builder setParam(String apiService, String qrCode) {

        HttpParameters.Builder builder = HttpParameters.newBuilder();
        // ???????????????URL
        builder.requestURL(CSB_URL)
                .api(apiService)
                // ???????????????
                .version(API_VERSION)
                // ??????????????????, get/post
                .method(API_METHOD)
                // ??????accessKey ??? ??????secretKey
                .accessKey(ACCESS_KEY).secretKey(SECURITY_KEY);

        // ?????????????????????json??????)
        Map<String, String> param = new HashMap<>();
        param.put("channelNo","9400000373");
        param.put("accessNo", "0344000001");
        if ("qrcode_channel_query_encrypt".equals(apiService)) {
            param.put("qrCode", qrCode);
        }
        AESUtils.encrypt(JSON.toJSONString(param), ENCRYPT_KEY);
        Map<String, String> encryptParam = new HashMap<>();
        encryptParam.put("security", AESUtils.encrypt(JSON.toJSONString(param), ENCRYPT_KEY));

        ContentBody cb = new ContentBody(JSON.toJSONString(encryptParam));
        builder.contentBody(cb);
        return builder;
    }
}

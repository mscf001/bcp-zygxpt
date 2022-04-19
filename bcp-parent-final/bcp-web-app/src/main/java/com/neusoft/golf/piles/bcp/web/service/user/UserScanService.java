package com.neusoft.golf.piles.bcp.web.service.user;

import com.neusoft.golf.piles.bcp.web.domain.user.UserNewDo;
import com.neusoft.golf.piles.bcp.web.dto.LoginScanDTO;
import com.neusoft.golf.piles.bcp.web.dto.MenuDTO;
import com.neusoft.golf.piles.bcp.web.dto.Response;
import com.neusoft.golf.piles.bcp.web.dto.UserNewDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Administrator
 */
@RequestMapping({"/eesb/userscan"})
public interface UserScanService {

    @GetMapping({"/public/qrcode"})
    Response createQrCode();

    @PostMapping({"/public/getUser"})
    Response getUser(@RequestParam("qrCode") String qrCode);

    @PostMapping({"/public/scanLogin"})
    String scanLogin(@RequestBody LoginScanDTO loginScanDTO);

    @PostMapping({"createNew"})
    void createNew(@RequestBody UserNewDTO userNewDTO);

    @GetMapping({"menus/oneNew"})
    List<MenuDTO> getMenusOne(@RequestParam("account") String account);

    @GetMapping({"account/likeNew"})
    List<UserNewDo> findByAccountLike(@RequestParam("account") String account);

    @GetMapping({"all"})
    List<UserNewDo> all();
}

package cn.ling.common.impl;

import cn.ling.common.CommonService;
import cn.ling.constant.CommonConstants;
import cn.ling.model.temp.LogDTO;
import cn.ling.model.temp.PageDTO;
import cn.ling.util.IpAddressUtils;
import cn.ling.util.UserAgentUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 常用实现类
 */
@Service
public class CommonServiceImpl implements CommonService {
    private final UserAgentUtils userAgentUtils;

    public CommonServiceImpl(UserAgentUtils userAgentUtils) {
        this.userAgentUtils = userAgentUtils;
    }


    @Override
    public LogDTO saveLog(String ip, String userAgent){
        LogDTO logDTO = new LogDTO(ip, userAgent);
        String ipSource = IpAddressUtils.getCityInfo(logDTO.getIp());
        Map<String, String> userAgentMap = userAgentUtils.parseOsAndBrowser(logDTO.getUserAgent());
        String os = userAgentMap.get("os");
        String browser = userAgentMap.get("browser");
        logDTO.setIpSource(ipSource).setOs(os).setBrowser(browser);
        return logDTO;
    }

    @Override
    public PageDTO pageBefore(String[] date) {
        PageDTO pageDTO = new PageDTO();
        if (date.length == CommonConstants.TWO) {
            pageDTO.setStartDate(date[0]).setEndDate(date[1]);
        }
        pageDTO.setOrderBy("create_time desc");
        return pageDTO;
    }
}

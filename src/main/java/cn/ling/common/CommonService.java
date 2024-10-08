package cn.ling.common;

import cn.ling.model.temp.LogDTO;
import cn.ling.model.temp.PageDTO;


public interface CommonService {
    /**
     * 抽取出的，保存日志公共方法
     * @param ip ip
     * @param userAgent userAgent
     * @return LogDTO
     */
    LogDTO saveLog(String ip, String userAgent);

    /**
     * 分页之前的操作
     * @param date 分页数据
     * @return PageDTO
     */
    PageDTO pageBefore(String[] date);
}

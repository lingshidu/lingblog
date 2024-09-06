package cn.ling.model.vo;

import lombok.Data;

import java.util.Map;

@Data
public class QqResultVO {
    private String success;

    private String msg;

    private Map<String, Object> data;

    private String time;

    private  String api_vers;
}

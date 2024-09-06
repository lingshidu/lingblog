package cn.ling.model.temp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class LogDTO {
    /**
     * ip
     */
    private String ip;

    private String userAgent;

    /**
     * ip来源
     */
    private String ipSource;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 浏览器
     */
    private String browser;

    public LogDTO(String ip, String userAgent) {
        this.ip = ip;
        this.userAgent = userAgent;
    }

}

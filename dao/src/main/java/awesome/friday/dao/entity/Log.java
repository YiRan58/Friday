package awesome.friday.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yiran
 * @date: 20.11.1 17:08
 */
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String methodName;
    private String param;
    private String ip;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operationTime;

    public Integer getId() {
        return id;
    }

    public Log setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public Log setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public String getParam() {
        return param;
    }

    public Log setParam(String param) {
        this.param = param;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public Log setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public Log setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
        return this;
    }
}

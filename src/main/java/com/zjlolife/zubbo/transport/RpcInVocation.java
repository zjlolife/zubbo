package com.zjlolife.zubbo.transport;

import java.io.Serializable;

public class RpcInVocation implements Serializable {

    private static final long serialVersionUID = -2562590653517749736L;

    //请求唯一id
    private long uid;

    private String  serviceName;

    private String methodName;

    private Object [] arguments;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
}

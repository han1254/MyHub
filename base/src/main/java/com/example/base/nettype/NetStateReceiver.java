package com.example.base.nettype;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.base.nettype.annotation.Network;
import com.example.base.nettype.bean.MethodManager;
import com.example.base.nettype.type.NetType;
import com.example.base.nettype.utils.NetworkUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author YangZhaoxin.
 * @since 2019/9/28 19:43.
 * email yangzhaoxin@hrsoft.net.
 */

public class NetStateReceiver extends BroadcastReceiver {

    private NetType netType;
    private Map<Object, List<MethodManager>> map;
//    private NetChangeObserver listener;

    public NetStateReceiver() {
        netType = NetType.NONE;
        map = new HashMap<>();
    }

//    public void setListener(NetChangeObserver listener) {
//        this.listener = listener;
//    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            Log.d(Constants.LOG_TAG,"产生异常");
            return;
        }

        if (intent.getAction().equalsIgnoreCase(Constants.ANDROID_NETWORK_CHANGE)) {
            Log.d(Constants.LOG_TAG, "网络发生改变");
            netType = NetworkUtil.getNetType();
            if (NetworkUtil.isNetworkAvailable()) {
                Log.d(Constants.LOG_TAG, "网络连接成功");
//                if (listener != null) listener.onConnect(netType);
            } else {
                Log.d(Constants.LOG_TAG, "网络连接失败");
//                if (listener != null) listener.onDisConnect();
            }
            post(netType);
        }
    }

    public void register(Object object) {
        List<MethodManager> methodManagers = map.get(object);
        if (methodManagers == null) {
            methodManagers = findMethod(object);
            map.put(object, methodManagers);
        }
    }

    /**
     * 找到object中带注解的方法
     * @param object
     * @return
     */
    private List<MethodManager> findMethod(Object object) {
        List<MethodManager> methodManagers = new ArrayList<>();
        Class<?> aClass = object.getClass();
//        Method[] declaredMethods = aClass.getDeclaredMethods();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            Network annotation = method.getAnnotation(Network.class);
            if (annotation == null) {
                continue;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                throw new RuntimeException(method.getName() + "应该有一个参数");
            }
            if (!parameterTypes[0].isAssignableFrom(NetType.class)) {
                throw new RuntimeException(method.getName() + "参数类型应该为 NetType");
            }
            MethodManager methodManager = new MethodManager(parameterTypes[0], annotation.netType(), method);
            methodManagers.add(methodManager);
        }
        return methodManagers;
    }

    /**
     * 发布者
     * @param netType
     */
    public void post(NetType netType) {
        Iterator<Object> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            List<MethodManager> methodManagers = map.get(next);
            for (MethodManager methodManager : methodManagers) {
                switch (methodManager.getNetType()) {
                    case CMNET:
                        if (netType == NetType.CMNET || netType == NetType.NONE) {
                            invoke(next, methodManager, netType);
                        }
                        break;
                    case CMWAP:
                        if (netType == NetType.CMWAP || netType == NetType.NONE) {
                            invoke(next, methodManager, netType);
                        }
                        break;
                    case WIFI:
                        if (netType == NetType.WIFI || netType == NetType.NONE) {
                            invoke(next, methodManager, netType);
                        }
                        break;
                    case AUTO:
                        invoke(next, methodManager, netType);
                        break;
                }
            }
        }
    }

    private void invoke(Object object, MethodManager methodManager, NetType netType) {
        try {
            methodManager.getMethod().invoke(object, netType);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void unRegister(Object object) {
        if (map.get(object) != null) {
            map.remove(object);
        }
    }

}

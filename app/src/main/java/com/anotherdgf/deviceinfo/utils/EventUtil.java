package com.anotherdgf.deviceinfo.utils;

/**
 * Created by denggaofeng on 2018/6/26.
 */

public class EventUtil {

    public static class E {

    }

    public static class AppInstallStateEvent extends E{

    }

    public static class NetWorkStatEvent extends E {

        private int state;

        public NetWorkStatEvent() {
        }

        public NetWorkStatEvent(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }

}

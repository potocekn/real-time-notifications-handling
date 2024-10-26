package com.rabbitmqlistener.notificationslistener.notification;

public enum NotificationChannel {
    EMAIL("email"),
    SMS("sms"),
    IN_APP("in_app");

    private final String bindingName;

    NotificationChannel(String bindingName){
        this.bindingName = bindingName;
    }

    public String getBindingName(){
        return bindingName;
    }
}

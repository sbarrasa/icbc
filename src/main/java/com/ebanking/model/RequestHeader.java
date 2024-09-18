package com.ebanking.model;

import java.io.Serializable;
import java.util.Objects;

public class RequestHeader implements Serializable {
  private static final long serialVersionUID = -2697503027739149572L;
  private String channel;
  private String groupId;
  private String password;
  private String remoteHost;
  private String serviceName;
  private String sessionId;
  private String transactionId;
  private String userId;
  private String taskId;
  private String applicationUserId;

  public RequestHeader() {
  }


  public RequestHeader channel(String channel) {
    this.channel = channel;
    return this;
  }

  public String getChannel() {
    return this.channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public RequestHeader groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

  public String getGroupId() {
    return this.groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public RequestHeader password(String password) {
    this.password = password;
    return this;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public RequestHeader remoteHost(String remoteHost) {
    this.remoteHost = remoteHost;
    return this;
  }

  public String getRemoteHost() {
    return this.remoteHost;
  }

  public void setRemoteHost(String remoteHost) {
    this.remoteHost = remoteHost;
  }

  public RequestHeader serviceName(String serviceName) {
    this.serviceName = serviceName;
    return this;
  }

  public String getServiceName() {
    return this.serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public RequestHeader sessionId(String sessionId) {
    this.sessionId = sessionId;
    return this;
  }

  public String getSessionId() {
    return this.sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public RequestHeader transactionId(String transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  public String getTransactionId() {
    return this.transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public RequestHeader userId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public RequestHeader taskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

  public String getTaskId() {
    return this.taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public RequestHeader applicationUserId(String applicationUserId) {
    this.applicationUserId = applicationUserId;
    return this;
  }

  public String getApplicationUserId() {
    return this.applicationUserId;
  }

  public void setApplicationUserId(String applicationUserId) {
    this.applicationUserId = applicationUserId;
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (o != null && this.getClass() == o.getClass()) {
      RequestHeader requestHeader = (RequestHeader)o;
      return Objects.equals(this.channel, requestHeader.channel) && Objects.equals(this.groupId, requestHeader.groupId) && Objects.equals(this.password, requestHeader.password) && Objects.equals(this.remoteHost, requestHeader.remoteHost) && Objects.equals(this.serviceName, requestHeader.serviceName) && Objects.equals(this.sessionId, requestHeader.sessionId) && Objects.equals(this.transactionId, requestHeader.transactionId) && Objects.equals(this.taskId, requestHeader.taskId) && Objects.equals(this.userId, requestHeader.userId) && Objects.equals(this.applicationUserId, requestHeader.applicationUserId);
    } else {
      return false;
    }
  }

  public int hashCode() {
    return Objects.hash(new Object[]{this.channel, this.groupId, this.password, this.remoteHost, this.serviceName, this.sessionId, this.transactionId, this.userId, this.taskId, this.applicationUserId});
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\"class\": ").append("\"RequestHeader\"");

    if (this.channel != null) {
      sb.append(", ");
      sb.append("\"channel\": ");
      sb.append("\"");
      sb.append(this.channel);
      sb.append("\"");
    }

    if (this.groupId != null) {
      sb.append(", ");
      sb.append("\"groupId\": ");
      sb.append("\"");
      sb.append(this.groupId);
      sb.append("\"");
    }

    if (this.password != null) {
      sb.append(", ");
      sb.append("\"password\": ").append("\"xxx\"");
    }

    if (this.remoteHost != null) {
      sb.append(", ");
      sb.append("\"remoteHost\": ");
      sb.append("\"");
      sb.append(this.remoteHost);
      sb.append("\"");
    }

    if (this.serviceName != null) {
      sb.append(", ");
      sb.append("\"serviceName\": ");
      sb.append("\"");
      sb.append(this.serviceName);
      sb.append("\"");
    }

    if (this.sessionId != null) {
      sb.append(", ");
      sb.append("\"sessionId\": ");
      sb.append("\"");
      sb.append(this.sessionId);
      sb.append("\"");
    }

    if (this.transactionId != null) {
      sb.append(", ");
      sb.append("\"transactionId\": ");
      sb.append("\"");
      sb.append(this.transactionId);
      sb.append("\"");
    }

    if (this.userId != null) {
      sb.append(", ");
      sb.append("\"userId\": ");
      sb.append("\"");
      sb.append(this.userId);
      sb.append("\"");
    }

    if (this.taskId != null) {
      sb.append(", ");
      sb.append("\"taskId\": ");
      sb.append("\"");
      sb.append(this.taskId);
      sb.append("\"");
    }

    if (this.applicationUserId != null) {
      sb.append(", ");
      sb.append("\"applicationUserId\": ");
      sb.append("\"");
      sb.append(this.applicationUserId);
      sb.append("\"");
    }

    sb.append("}");
    return sb.toString();
  }
}
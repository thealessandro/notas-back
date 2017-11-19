package br.com.kyxadious.notas.back.common.response;

import java.util.HashSet;
import java.util.Set;

public class Response<T> {

    private Integer status;
    private Set<String> messages = new HashSet<>();
    private T result;

    public Response() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<String> getMessages() {
        return messages;
    }

    public void setMessages(Set<String> messages) {
        this.messages = messages;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

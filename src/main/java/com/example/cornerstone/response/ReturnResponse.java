package com.example.cornerstone.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author reborntodie
 * @date 2019/8/15 9:27
 */
@Getter
@Setter
@AllArgsConstructor
public class ReturnResponse<T> implements Serializable {

    private int code;

    private String msg;

    private T content;

}

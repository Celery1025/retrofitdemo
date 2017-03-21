package com.example.retrofitdemo;

import com.example.retrofitdemo.http.RetrofitClientUtil;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);


        HashMap<String,String> map = new HashMap<>();
        map.put("key1","11");
        map.put("key2","12");
        map.put("key3","13");
        RetrofitClientUtil.getRequestBody(map);
    }
}
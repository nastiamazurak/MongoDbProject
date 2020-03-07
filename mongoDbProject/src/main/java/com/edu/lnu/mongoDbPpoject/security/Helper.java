package com.edu.lnu.mongoDbPpoject.security;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static List<Cookie> createList(Cookie... cookies) {
        List<Cookie> list = new ArrayList<>();
        for (Cookie k : cookies) {
            list.add(k);
        }
        return list;
    }
}

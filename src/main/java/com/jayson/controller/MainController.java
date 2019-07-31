package com.jayson.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayson.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by michaelod on 2018/7/22.
 */
@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        JSONObject param = mainService.init();

        JSONArray events = param.getJSONArray("events");

        System.out.println(events.toJSONString());

        model.addAttribute("events", events);

        return "index";
    }

    @RequestMapping(value = "/view")
    public String view(Model model) {
        JSONObject param = mainService.view();

        JSONArray events = param.getJSONArray("events");
        JSONArray rooms = param.getJSONArray("rooms");

        System.out.println(events.toJSONString());
        System.out.println(rooms.toJSONString());

        model.addAttribute("events", events);
        model.addAttribute("rooms", rooms);
        return "view";
    }

    @RequestMapping(value = "/viewBack")
    public String viewBack(Model model) {
        JSONObject param = mainService.view();

        JSONArray events = param.getJSONArray("events");
        JSONArray rooms = param.getJSONArray("rooms");

        System.out.println(events.toJSONString());
        System.out.println(rooms.toJSONString());

        model.addAttribute("events", events);
        model.addAttribute("rooms", rooms);
        return "view2";
    }

    @RequestMapping("/viewByRoom/{roomId}")
    public String viewByRoom(@PathVariable("roomId") Integer roomId, Model model) {

        JSONObject param = mainService.viewByRoom(roomId);
        JSONArray events = param.getJSONArray("events");
        JSONArray rooms = param.getJSONArray("rooms");
        System.out.println(events.toJSONString());
        System.out.println(rooms.toJSONString());
        model.addAttribute("events", events);
        model.addAttribute("rooms", rooms);
        model.addAttribute("nowRoomName", param.get("nowRoomName"));
        return "view";
    }

    @RequestMapping("/viewByRoomBack/{roomId}")
    public String viewByRoomBack(@PathVariable("roomId") Integer roomId, Model model) {

        JSONObject param = mainService.viewByRoom(roomId);
        JSONArray events = param.getJSONArray("events");
        JSONArray rooms = param.getJSONArray("rooms");
        System.out.println(events.toJSONString());
        System.out.println(rooms.toJSONString());
        model.addAttribute("events", events);
        model.addAttribute("rooms", rooms);
        model.addAttribute("nowRoomName", param.get("nowRoomName"));
        return "view2";
    }
}

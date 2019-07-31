package com.jayson.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayson.entity.Event;
import com.jayson.entity.Room;
import com.jayson.entity.User;
import com.jayson.mapper.MainMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;


/**
 * Created by michaelod on 2018/7/23.
 */
@Component
@Service
public class MainService {


    @Autowired
    MainMapper mainMapper;


    public JSONObject init() {


        JSONObject param = new JSONObject();


        List<Event> events = mainMapper.getAllEvents();

        JSONArray jsonArrayEvents = new JSONArray();
        for (Event e : events) {
            JSONObject jsonObject = new JSONObject();
            String title = e.getName().substring(0, e.getName().length() - 26);
            String time = e.getName().substring(e.getName().length() - 26);
            jsonObject.put("title", title);
            jsonObject.put("time", time);
            User user = mainMapper.getUserById(e.getUserId());
            jsonObject.put("user", user.getName());
            jsonObject.put("textColor","white");
            if (e.getRoomId() == 1) {
                jsonArrayEvents.add(jsonObject);
            }

        }


        param.put("events", jsonArrayEvents);

        return param;
    }


    public JSONObject view() {


        JSONObject param = new JSONObject();


        List<Event> events = mainMapper.getAllEvents();

        JSONArray jsonArrayEvents = new JSONArray();
        for (Event e : events) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", e.getName());
            jsonObject.put("roomId", e.getRoomId());
            jsonObject.put("textColor","white");
            String startDate = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(e.getStartTime());
            String endDate = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(e.getEndTime());
            jsonObject.put("start", startDate);
            jsonObject.put("end", endDate);
            jsonArrayEvents.add(jsonObject);
        }


        List<Room> rooms = mainMapper.getAllRooms();

        JSONArray jsonArrayRooms = new JSONArray();
        for (Room r : rooms) {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("id", r.getId());
            jsonObject.put("name", r.getName() + "(" + r.getPeopleCount() + ")");
            jsonObject.put("textColor","white");
            jsonArrayRooms.add(jsonObject);
        }

        param.put("rooms", jsonArrayRooms);
        param.put("events", jsonArrayEvents);


        return param;
    }

    public JSONObject viewByRoom(int roomId) {


        JSONObject param = new JSONObject();


        List<Event> events = mainMapper.getAllEvents();

        JSONArray jsonArrayEvents = new JSONArray();
        for (Event e : events) {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", e.getName());
            jsonObject.put("roomId", e.getRoomId());
            jsonObject.put("textColor","white");
            String startDate = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(e.getStartTime());
            String endDate = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(e.getEndTime());
            jsonObject.put("start", startDate);
            jsonObject.put("end", endDate);

            if (e.getRoomId() == roomId) {
                jsonArrayEvents.add(jsonObject);
            }

        }


        List<Room> rooms = mainMapper.getAllRooms();

        JSONArray jsonArrayRooms = new JSONArray();

        String nowRoomName = "";
        for (Room r : rooms) {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("id", r.getId());
            jsonObject.put("name", r.getName() + "(" + r.getPeopleCount() + ")");
            jsonObject.put("textColor","white");
            if (r.getId() == roomId) {
                nowRoomName = r.getName() + "(" + r.getPeopleCount() + ")";
            }
            jsonArrayRooms.add(jsonObject);
        }

        param.put("rooms", jsonArrayRooms);
        param.put("events", jsonArrayEvents);

        param.put("nowRoomName", nowRoomName);

        return param;
    }
}
